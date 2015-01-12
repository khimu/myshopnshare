package com.myshopnshare.webapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.domain.ItemCategory;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.CategoryType;
import com.myshopnshare.core.service.ItemCategoryService;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.model.User;

/**
 * @author khimung
 *
 */
public class ItemCategoryController extends MultiActionController {
	private static transient final Log log = LogFactory
	.getLog(ItemCategoryController.class);
	
	@Autowired
	private PersonService personService;
	@Autowired
	private ItemCategoryService itemCategoryService;

	protected Person fetchPerson() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User user = (User) principal;

		return personService.findPersonByUsername(user.getUsername());
	}

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();

		request.setAttribute("person", person);

		String personId = request.getParameter("personId");
		String searchString = request.getParameter("searchString");
		String category = request.getParameter("category");
		String startStr = request.getParameter("start");
		int start = 0;
		if(StringUtils.trimToNull(startStr) != null){
			start =  Integer.parseInt(request.getParameter("start"));
		}

		if (StringUtils.trimToNull(personId) == null
				|| person.getId().equals(Long.parseLong(personId))) {
			request.setAttribute("total", itemCategoryService
					.findOwnItemCategorysCount(person, searchString, category));
			
			log.debug("view: found owner total " + itemCategoryService
					.findOwnItemCategorysCount(person, searchString, category));

			return new ModelAndView("users/itemCategories");
		}

		Person friend = personService.get(Long.parseLong(personId));

		if (!person.isFriends(personId)) {
			return new ModelAndView("item/jsonItemsCategory");
		}

		request.setAttribute("total", itemCategoryService
				.findFriendItemCategorysCount(friend, person, category,
						searchString));
		
		log.debug("view: found friend total " + itemCategoryService
				.findFriendItemCategorysCount(friend, person, category,
						searchString));			
		
		return new ModelAndView("users/itemCategories");
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
		request.setAttribute("person", person);

		if (StringUtils.trimToNull(personId) == null
				|| person.getId().equals(Long.parseLong(personId))) {
			request.setAttribute("total", itemCategoryService
					.findOwnItemCategorysCount(person, searchString, category));
			
			log.debug("all: found owner total " + itemCategoryService
					.findOwnItemCategorysCount(person, searchString, category));
			
			request.setAttribute("items", itemCategoryService
					.findOwnItemCategorys(person, searchString, category, start));
			return new ModelAndView("item/jsonItemsCategory");
		}

		Person friend = personService.get(Long.parseLong(personId));

		if (!person.isFriends(personId)) {
			return new ModelAndView("item/jsonItemsCategory");
		}

		request.setAttribute("total", itemCategoryService
				.findFriendItemCategorysCount(friend, person, category,
						searchString));
		
		log.debug("all: found friend total " + itemCategoryService
				.findFriendItemCategorysCount(friend, person, category,
						searchString));	
		
		request.setAttribute("items", itemCategoryService
				.findFriendItemCategorys(friend, person, category,
						searchString, start));

		return new ModelAndView("item/jsonItemsCategory");
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
		request.setAttribute("person", person);
		
		if (StringUtils.trimToNull(personId) == null
				|| person.getId().equals(Long.parseLong(personId))) {
			request.setAttribute("total", itemCategoryService
					.findOwnItemCategorysCount(person, searchString, category));
			
			log.debug("search: found owner total " + itemCategoryService
					.findOwnItemCategorysCount(person, searchString, category));				
			
			request.setAttribute("items", itemCategoryService
					.findOwnItemCategorys(person, searchString, category, start));
			return new ModelAndView("item/jsonItemsCategory");
		}

		Person friend = personService.get(Long.parseLong(personId));
		if ((!person.isFriends(personId))
				&& (!person.getId().equals(friend.getId()))) {
			
			return new ModelAndView("item/jsonItemsCategory");
		}

		/** Searching on a friend's items **/
		/**
		 * Bug if you send your id, you are not your friend so it doesn't pick
		 * you up
		 **/
		request.setAttribute("total", itemCategoryService
				.findFriendItemCategorysCount(friend, person, category,
						searchString));
		
		log.debug("all: found friend total " + itemCategoryService
				.findFriendItemCategorysCount(friend, person, category,
						searchString));
		
		request.setAttribute("items", itemCategoryService
				.findFriendItemCategorys(friend, person, category,
						searchString, 0));

		return new ModelAndView("item/jsonItemsCategory");
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

	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map map = new HashMap();
		Person person = fetchPerson();
		request.setAttribute("person", person);
		String itemId = request.getParameter("itemId");
		if (StringUtils.trimToNull(itemId) != null) {
			ItemCategory item = itemCategoryService
			.getItemCategoryForPerson(person, Long.parseLong(itemId));
			item.setActive(false);
			itemCategoryService.update(item);
		}
		
		return new ModelAndView("item/jsonItemsCategory");
	}

	/**
	 * @Deprecated Edit not allowed on ItemCategory
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		Map map = new HashMap();
		String itemId = request.getParameter("itemId");
		if (StringUtils.trimToNull(itemId) != null) {
			request.setAttribute("item", itemCategoryService
					.getItemCategoryForPerson(person, Long.parseLong(itemId)));
		}
		return new ModelAndView("item/jsonItemsCategory");
	}
	
	public void setItemCategoryService(ItemCategoryService itemCategoryService) {
		this.itemCategoryService = itemCategoryService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

}
