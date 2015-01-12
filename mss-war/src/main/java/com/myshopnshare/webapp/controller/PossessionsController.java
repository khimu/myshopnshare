package com.myshopnshare.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.Category;
import com.myshopnshare.core.enums.CategoryType;
import com.myshopnshare.core.service.FriendService;
import com.myshopnshare.core.service.ItemCategoryService;
import com.myshopnshare.core.service.ItemService;
import com.myshopnshare.core.service.ItemVisibilityDomainService;

/**
 * Manages your items. Wonder how this is different from the SearchController?
 * You are searching against your own items. The searchcontroller is searching
 * against everyone's items.
 * 
 * 
 * @author khim.ung
 * 
 */
public class PossessionsController extends BaseController {
	private static transient final Log log = LogFactory
	.getLog(PossessionsController.class);
	
	@Autowired
	private FriendService friendService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemVisibilityDomainService itemVisibilityDomainService;
	
	@Autowired
	private ItemCategoryService itemCategoryService;

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String personId = request.getParameter("personId");
		
		Person person = fetchPerson();
		
		request.setAttribute("person", person);
		request.setAttribute("categories", Category.values());
		
		if (StringUtils.trimToNull(personId) == null
				|| person.getId().equals(Long.parseLong(personId))) {
			request.setAttribute("total", itemService.findOwnItemsCount(person,
					null, null));
			
			log.debug("view: found owner total: " + itemService.findOwnItemsCount(person,
					null, null));
			
			return new ModelAndView("users/possessions");
		}

		Person friend = personService.get(Long.parseLong(personId));
		
		
		
		if (!person.isFriends(personId)
				&& !person.getId().equals(friend.getId())) {
			return new ModelAndView("item/jsonItems");
		}
		
		request.setAttribute("total", itemService.findFriendItemsCount(friend,
				null, person, null));
		
		log.debug("view: found friend total: " + itemService.findFriendItemsCount(friend,
				null, person, null));
		
		return new ModelAndView("users/possessions");
	}

	public ModelAndView all(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String personId = request.getParameter("personId");
		String searchString = request.getParameter("searchString");
		String category = request.getParameter("category");
		String startStr = request.getParameter("start");
		int start = 0;
		if(StringUtils.trimToNull(startStr) != null){
			start =  Integer.parseInt(request.getParameter("start"));
		}
		Person person = fetchPerson();
		if (StringUtils.trimToNull(personId) == null
				|| person.getId().equals(Long.parseLong(personId))) {
			request.setAttribute("total", itemService.findOwnItemsCount(person,
					null, null));
			
			log.debug("all: found owner total: " + itemService.findOwnItemsCount(person,
					null, null));
			
			request.setAttribute("items", itemService.findOwnItems(person,
					null, null, start));
			return new ModelAndView("item/jsonItems");
		}

		Person friend = personService.get(Long.parseLong(personId));
		request.setAttribute("categories", CategoryType.values());
		if (!person.isFriends(personId)
				&& !person.getId().equals(friend.getId())) {
			return new ModelAndView("item/jsonItems");
		}
		request.setAttribute("total", itemService.findFriendItemsCount(friend,
				category, person, searchString));
		
		log.debug("all: found friend total: " + itemService.findFriendItemsCount(friend,
				category, person, searchString));
		
		request.setAttribute("items", itemService.findFriendItems(friend,
				category, person, searchString, start));
		return new ModelAndView("item/jsonItems");
	}

	/** This is probably an update on an existing uploaded image **/
	public ModelAndView search(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String personId = request.getParameter("personId");
		String searchString = request.getParameter("searchString");
		String category = request.getParameter("category");
		String startStr = request.getParameter("start");
		int start = 0;
		if(StringUtils.trimToNull(startStr) != null){
			start =  Integer.parseInt(request.getParameter("start"));
		}
		Person person = fetchPerson();
		if (StringUtils.trimToNull(personId) == null
				|| person.getId().equals(Long.parseLong(personId))) {
			request.setAttribute("total", itemService.findOwnItemsCount(person,
					searchString, category));
			
			log.debug("all: found owner total: " + itemService.findOwnItemsCount(person,
					null, null));			
			
			request.setAttribute("items", itemService.findOwnItems(person,
					searchString, category, start));
			return new ModelAndView("item/jsonItems");
		}

		Person friend = personService.get(Long.parseLong(personId));
		if ((!person.isFriends(personId))
				&& (!person.getId().equals(friend.getId()))) {
			return new ModelAndView("item/jsonItems");
		}

		request.setAttribute("total", itemService.findFriendItemsCount(friend,
				category, person, searchString));
		
		log.debug("all: found friend total: " + itemService.findFriendItemsCount(friend,
				category, person, searchString));		
		
		/** Searching on a friend's items **/
		/**
		 * Bug if you send your id, you are not your friend so it doesn't pick
		 * you up
		 **/
		request.setAttribute("items", itemService.findFriendItems(friend,
				category, person, searchString, start));

		return new ModelAndView("item/jsonItems");
	}

	private void doQuery(String category, HttpServletRequest request,
			Person person) {
		if (category != null) {
			List<Item> items = null;
			if (CategoryType.valueOf(category) == CategoryType.SELLING) {
				items = itemCategoryService.findAllItemsFor(person,
						CategoryType.SELLING, 0);
			} else if (CategoryType.valueOf(category) == CategoryType.ADVICE) {
				items = itemCategoryService.findAllItemsFor(person,
						CategoryType.ADVICE, 0);
			} else if (CategoryType.valueOf(category) == CategoryType.WANT) {
				items = itemCategoryService.findAllItemsFor(person,
						CategoryType.WANT, 0);
			} else if (CategoryType.valueOf(category) == CategoryType.BOUGHT) {
				items = itemCategoryService.findAllItemsFor(person,
						CategoryType.BOUGHT, 0);
			} else if (CategoryType.valueOf(category) == CategoryType.RECOMMEND) {
				items = itemCategoryService.findAllItemsFor(person,
						CategoryType.RECOMMEND, 0);
			}
			request.setAttribute("items", items);
		}
	}

	public void setFriendService(FriendService friendService) {
		this.friendService = friendService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public void setItemVisibilityDomainService(
			ItemVisibilityDomainService itemVisibilityDomainService) {
		this.itemVisibilityDomainService = itemVisibilityDomainService;
	}

	public void setItemCategoryService(ItemCategoryService itemCategoryService) {
		this.itemCategoryService = itemCategoryService;
	}

}
