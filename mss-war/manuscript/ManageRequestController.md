# ManageRequestController

```java
package com.myshopnshare.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.myshopnshare.core.domain.Action;
import com.myshopnshare.core.domain.Friend;
import com.myshopnshare.core.domain.News;
import com.myshopnshare.core.domain.NewsVisibilityDomain;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.RequestFriend;
import com.myshopnshare.core.enums.PermissionType;
import com.myshopnshare.core.enums.UserType;
import com.myshopnshare.core.service.FriendService;
import com.myshopnshare.core.service.NewsService;
import com.myshopnshare.core.service.NewsVisibilityDomainService;
import com.myshopnshare.service.UserManager;

public class ManageRequestController extends BaseController {
	private static Logger logger = Logger
			.getLogger(ManageRequestController.class);

	@Autowired
	private UserManager userService;
	@Autowired
	private FriendService friendService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private NewsVisibilityDomainService newsVisibilityService;

	// view friend requests
	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = this.setup(request);

		request.setAttribute("friendRequests", requestFriendService
				.findAllRequestFor(person));

		return new ModelAndView("users/friendRequest");
	}

	/*
	 * This is where you add the requester to the list of friends for the person
	 */
	public ModelAndView confirmFriend(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String requestFriendId = request.getParameter("requestFriendId");
		RequestFriend requestFriend = requestFriendService.get(Long
				.parseLong(requestFriendId));
		Person me = requestFriend.getPotentialFriend();
		Person requester = requestFriend.getRequester();

		requestFriend.setConfirmFriend(true);
		requestFriend.setActive(false);
		requestFriendService.save(requestFriend);

		Friend friend = new Friend(me, requester);
		me.getFriendPermission(friend);
		me.getFriends().add(friend);
		
		Friend reverseFriend = new Friend(requester, me);
		requester.getFriendPermission(reverseFriend);
		requester.getFriends().add(reverseFriend);

		if(me.getUserType() == UserType.USER && requester.getUserType() == UserType.USER){
			// TODO needs to be checked
			News news = new News();
			news.setAction(Action.FRIENDS);
			news.setWorld(Action.FRIENDS.isWorld());
			news.setMessage(Action.FRIENDS.convert(me, requester));
			news.setPerson(me);
			me.getNewsPermission(news);
			me.getNews().add(news);
						
			News newsfriend = new News();
			newsfriend.setAction(Action.FRIENDS);
			newsfriend.setWorld(Action.FRIENDS.isWorld());
			newsfriend.setMessage(Action.FRIENDS.convert(requester, me));
			newsfriend.setPerson(requester);
			requester.getNewsPermission(newsfriend);
			requester.getNews().add(newsfriend);
		}else if(me.getUserType() == UserType.BUSINESS_SERVICE){
			createBusinessNews(requester, me);
		}else if(requester.getUserType() == UserType.BUSINESS_SERVICE){
			createBusinessNews(me, requester);
		}else if(me.getUserType() == UserType.INSTITUTION){
			createInstitutionNews(me, requester);
		}else if(requester.getUserType() == UserType.INSTITUTION){
			createInstitutionNews(me, requester);
		}
				
		personService.update(me);
		personService.update(requester);
		
		return new ModelAndView("users/friendRequest");
	}
	
	private void createBusinessNews(Person joiner, Person business){
		News news = new News();
		news.setAction(Action.JOIN_BUSINESS);
		news.setWorld(Action.JOIN_BUSINESS.isWorld());
		news.setMessage(Action.JOIN_BUSINESS.convert(joiner, business));
		news.setPerson(joiner);
		joiner.getNewsPermission(news);
		joiner.getNews().add(news);
		
		News friendnews = new News();
		friendnews.setAction(Action.MEMBER);
		friendnews.setWorld(Action.MEMBER.isWorld());
		friendnews.setMessage(Action.MEMBER.convert(business, joiner));
		friendnews.setPerson(business);
		business.getNewsPermission(news);
		business.getNews().add(news);		
	}
	
	private void createInstitutionNews(Person joiner, Person institution){
		News news = new News();
		news.setAction(Action.JOIN_INST);
		news.setWorld(Action.JOIN_INST.isWorld());
		news.setMessage(Action.JOIN_INST.convert(joiner, institution));
		news.setPerson(joiner);
		joiner.getNewsPermission(news);
		joiner.getNews().add(news);
		
		news.getNewsVisibility().add(new NewsVisibilityDomain(news, joiner.getVisibilityForType(PermissionType.NEWS)));
		news.getNewsVisibility().add(new NewsVisibilityDomain(news, joiner.getDefaultVisibility()));

		News friendnews = new News();
		friendnews.setAction(Action.MEMBER);
		friendnews.setWorld(Action.MEMBER.isWorld());
		friendnews.setMessage(Action.MEMBER.convert(institution, joiner));
		friendnews.setPerson(institution);
		institution.getNewsPermission(news);
		institution.getNews().add(friendnews);
	}

	public ModelAndView rejectFriend(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//
		String requestFriendId = request.getParameter("requestFriendId");

		RequestFriend requestFriend = requestFriendService.get(Long
				.parseLong(requestFriendId));

		requestFriend.setRejectFriend(true);
		requestFriend.setActive(false);
		requestFriendService.save(requestFriend);

		// Person person = this.setup(request);
		return new ModelAndView("users/friendRequest");
	}

}
```
