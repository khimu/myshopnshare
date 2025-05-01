# ManageFriendsController

```java
package com.myshopnshare.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.myshopnshare.core.domain.Friend;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.service.EmailService;
import com.myshopnshare.core.service.FriendService;
import com.myshopnshare.core.service.NewsService;
import com.myshopnshare.core.service.ScribbleService;
import com.myshopnshare.service.UserManager;

/*
 * Handles all friend actions
 */
public class ManageFriendsController extends BaseController {
	private static Logger logger = Logger
			.getLogger(ManageFriendsController.class);

	@Autowired
	private UserManager userService;
	@Autowired
	private FriendService friendService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private ScribbleService scribbleService;
	@Autowired
	private NewsService newsService;

	/*
	 * Display all friends
	 */
	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person owner = fetchPerson();
		String personId = request.getParameter("personId");
		if (StringUtils.trimToNull(personId) != null
				&& !owner.getId().equals(Long.parseLong(personId))) {
			
			Person person = personService.get(Long.parseLong(personId));
			
			if (!owner.isFriends(personId)) {
				return new ModelAndView("common/blank");
			}
			request.setAttribute("friends", friendService.findOneFriendsFriend(
					person, owner, 0));
		} else {
			request.setAttribute("friends", friendService.findOwnFriend(owner,
					0));
		}
		return new ModelAndView("friends/jsonFriends");
	}

	public ModelAndView friendslist(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person owner = fetchPerson();
		String personId = request.getParameter("personId");
		
		if (StringUtils.trimToNull(personId) != null
				&& !owner.getId().equals(Long.parseLong(personId))) {
			
			Person person = personService.get(Long.parseLong(personId));
			
			if (!owner.isFriends(personId)) {
				return new ModelAndView("common/blank");
			}
			request.setAttribute("friends", friendService.findOneFriendsFriend(
					person, owner, 0));
			// request.setAttribute("friends", person.getFriends());
		} else {
			request.setAttribute("friends", friendService.findOwnFriend(owner,
					0));
			// request.setAttribute("friends", owner.getFriends());
		}
		return new ModelAndView("friends/jsonFriends");
	}
	
	public ModelAndView all(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person owner = setup(request);
		
		String personId = request.getParameter("personId");
		
		if (StringUtils.trimToNull(personId) != null
				&& !owner.getId().equals(Long.parseLong(personId))) {
			
			Person person = personService.get(Long.parseLong(personId));
			
			if (!owner.isFriends(personId)) {
				return new ModelAndView("common/blank");
			}
			request.setAttribute("friends", friendService.findOneFriendsFriend(
					person, owner, 0));
			// request.setAttribute("friends", person.getFriends());
		} else {
			request.setAttribute("friends", friendService.findOwnFriend(owner,
					0));
			// request.setAttribute("friends", owner.getFriends());
		}
		return new ModelAndView("friends/all");
	}

	/**
	 * Shouln't this get one single friend
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person owner = fetchPerson();
		String personId = request.getParameter("personId");
		if (StringUtils.trimToNull(personId) != null
				&& !owner.getId().equals(Long.parseLong(personId))) {
			Person person = personService.get(Long.parseLong(personId));

			if (!owner.isFriends(personId)) {
				return new ModelAndView("common/blank");
			}
			request.setAttribute("friends", friendService.findOneFriendsFriend(
					person, owner, 0));
		} else {
			request.setAttribute("friends", friendService.findOwnFriend(owner,
					0));
		}
		return new ModelAndView("friends/jsonFriends");
	}

	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String friendId = request.getParameter("friendId");

		if (StringUtils.trimToNull(friendId) != null) {
			//Friend friend = owner.findFriend(Long.parseLong(friendId));
			Friend friend = friendService.getFriendForPerson(person, Long.parseLong(friendId));
			if (friend == null) {
				return new ModelAndView("common/blank");
			}
			
			friend.setActive(false);
			friendService.update(friend);
		}
		request.setAttribute("friends", friendService.findOwnFriend(person, 0));
		return new ModelAndView("friends/jsonFriends");
	}

}
```
