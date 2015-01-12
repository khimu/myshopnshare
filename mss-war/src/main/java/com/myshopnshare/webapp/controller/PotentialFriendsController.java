package com.myshopnshare.webapp.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.RequestFriend;
import com.myshopnshare.core.service.EmailService;
import com.myshopnshare.core.service.FriendService;
import com.myshopnshare.core.service.NewsService;
import com.myshopnshare.service.UserManager;
import com.myshopnshare.utils.EmailUtil;

public class PotentialFriendsController extends BaseController {
	private static Logger logger = Logger
			.getLogger(ManageFriendsController.class);

	@Autowired
	private UserManager userService;
	@Autowired
	private FriendService friendService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private NewsService newsService;

	public ModelAndView search(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String searchString = request.getParameter("searchString");

		request.setAttribute("results", personService
				.findPersonsBySearchString(searchString));

		request.setAttribute("person", setup(request));
		return new ModelAndView("search/potentialfriends/searchFriendResults");
	}

	/*
	 * You are request to be friends with someone you found. This should be an
	 * ajax call
	 */
	public ModelAndView requestFriend(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = this.setup(request);
		String friendId = request.getParameter("friendId");
		String message = request.getParameter("message");
		Person friendPerson = personService.get(Long.parseLong(friendId));
		
		RequestFriend anyPreviousRequest = requestFriendService.findFriendRequestBy(person);
		if(anyPreviousRequest != null){
			request.setAttribute("friendRequestSuccessful", "Your friend request has been sent to " + friendPerson.getFullName());
			return new ModelAndView("search/potentialfriends/requestSuccessful");
		}
		
		if(person.isFriends(friendId) || person.getId().equals(Long.parseLong(friendId))){
			request.setAttribute("friendRequestSuccessful", "You are already friends with " + friendPerson.getFullName());
			return new ModelAndView("search/potentialfriends/requestSuccessful");
		}
		
		RequestFriend requestFriend = new RequestFriend();
		requestFriend.setExpired(false);
		requestFriend.setPotentialFriend(friendPerson);
		requestFriend.setRequestDate(new Date(System.currentTimeMillis()));
		requestFriend.setRequester(person);
		requestFriend.setConfirmFriend(false);
		requestFriend.setRejectFriend(false);

		requestFriendService.save(requestFriend);

		EmailUtil.INSTANCE
				.sendMail(
						"Confirm Friend",
						"You have a new friend request from " + person.getFullName() + "\n " + message,
						"sleekswap@gmail.com", emailService.getPrimaryEmailForPerson(friendPerson)
								.getEmail());
		
		request.setAttribute("friendRequestSuccessful", "Your friend request has been sent to " + friendPerson.getFullName());
		return new ModelAndView("search/potentialfriends/requestSuccessful");
	}

}
