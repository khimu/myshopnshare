package com.myshopnshare.webapp.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.service.FriendService;
import com.myshopnshare.core.service.ItemService;
import com.myshopnshare.core.service.ItemVisibilityDomainService;

public class SearchController extends BaseController {
	
	@Autowired
	private FriendService friendService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemVisibilityDomainService itemVisibilityDomainService;

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String startStr = request.getParameter("start");
		int start = 0;
		if(StringUtils.trimToNull(startStr) != null){
			start =  Integer.parseInt(request.getParameter("start"));
		}
		request.setAttribute("total", itemService.findWorldItemsCount(person, null, null));
		List<Item> items = itemService.findWorldItems(person, null, null, 0);
		return new ModelAndView("search/searchItemResult");
	}
	
	public ModelAndView popup(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String startStr = request.getParameter("start");
		int start = 0;
		if(StringUtils.trimToNull(startStr) != null){
			start =  Integer.parseInt(request.getParameter("start"));
		}
		request.setAttribute("total", itemService.findWorldItemsCount(person, null, null));
		List<Item> items = itemService.findWorldItems(person, null, null, 0);
		return new ModelAndView("search/searchLayout");
	}

	public ModelAndView all(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String personId = request.getParameter("personId");
		List<Item> items = null;
		String startStr = request.getParameter("start");
		int start = 0;
		if(StringUtils.trimToNull(startStr) != null){
			start =  Integer.parseInt(request.getParameter("start"));
		}
		String searchString = request.getParameter("searchString");
		String category = request.getParameter("category");
		if (StringUtils.trimToNull(personId) != null) {
			Person friend = personService.get(Long.parseLong(personId));
			request.setAttribute("total",  itemService.findFriendItemsCount(friend, category, person, searchString));
			items = itemService.findFriendItems(friend, category, person, searchString, 0);
		} else {
			request.setAttribute("total", itemService.findWorldItemsCount(person, searchString, category));
			items = itemService.findWorldItems(person, searchString, category, 0);
		}
		if (items.size() == 0) {
			request.setAttribute("searchMessage",
					"No results matched your search");
		}
		request.setAttribute("items", items);
		return new ModelAndView("item/jsonItems");
	}
	
	// Searching on tag value
	public ModelAndView world(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		List<Item> items = null;
		String startStr = request.getParameter("start");
		int start = 0;
		if(StringUtils.trimToNull(startStr) != null){
			start =  Integer.parseInt(request.getParameter("start"));
		}
		String searchString = request.getParameter("searchString");
		String category = request.getParameter("category");
		request.setAttribute("total", itemService.findWorldItemsCount(person, searchString, category));
		items = itemService.findWorldItems(person, searchString, category, 0);
		if (items.size() == 0) {
			request.setAttribute("searchMessage",
					"No results matched your search");
		}
		request.setAttribute("items", items);
		return new ModelAndView("item/jsonItems");
	}

	// Searching on tag value
	public ModelAndView self(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		List<Item> items = null;
		String startStr = request.getParameter("start");
		int start = 0;
		if(StringUtils.trimToNull(startStr) != null){
			start =  Integer.parseInt(request.getParameter("start"));
		}
		String searchString = request.getParameter("searchString");
		String category = request.getParameter("category");
		request.setAttribute("total", itemService.findOwnItemsCount(person, searchString, category));
		items = itemService.findOwnItems(person, searchString, category, 0);
		if (items.size() == 0) {
			request.setAttribute("searchMessage",
					"No results matched your search");
		}else{
			request.setAttribute("searchMessage",
			"Your search returned " + items.size() + " items.");
		}
		request.setAttribute("items", items);

		return new ModelAndView("item/jsonItems");
	}

	/*
	 * You cannot search your friends' friend's items.
	 */
	public ModelAndView allfriends(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		List<Item> items = null;
		String startStr = request.getParameter("start");
		int start = 0;
		if(StringUtils.trimToNull(startStr) != null){
			start =  Integer.parseInt(request.getParameter("start"));
		}
		String searchString = request.getParameter("searchString");
		String category = request.getParameter("category");
		request.setAttribute("total", itemService.findFriendsItemsCount(person, searchString, category));
		items = itemService.findFriendsItems(person, searchString, category, 0);
		if (items.size() == 0) {
			request.setAttribute("searchMessage",
					"No results matched your search");
		}
		request.setAttribute("items", items);
		return new ModelAndView("item/jsonItems");
	}

	/*
	 * You cannot search your friends' friend's items.
	 */
	public ModelAndView afriends(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String friendId = request.getParameter("friendId");
		String searchString = request.getParameter("searchString");
		String category = request.getParameter("category");
		String startStr = request.getParameter("start");
		int start = 0;
		if(StringUtils.trimToNull(startStr) != null){
			start =  Integer.parseInt(request.getParameter("start"));
		}
		if(StringUtils.trimToNull(friendId) != null){
			Person friend = personService.get(Long.parseLong(friendId));
			if(person.isFriends(friendId)){
				List<Item> items = null;
				request.setAttribute("total", itemService.findFriendItemsCount(friend, category, person, searchString));
				items = itemService.findFriendItems(friend, category, person, searchString, 0);
				
				if (items.size() == 0) {
					request.setAttribute("searchMessage",
							"No results matched your search");
				}
				request.setAttribute("items", items);
				return new ModelAndView("item/jsonItems");				
			}
			request.setAttribute("results", Arrays.asList(friend));
		}
		request.setAttribute("person", person);
		request.setAttribute("searchMessage", "You do not have permission to view this person's items.");
		return new ModelAndView("home/noPermission");
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

}
