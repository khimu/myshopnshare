package com.myshopnshare.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.Rating;
import com.myshopnshare.core.service.FriendService;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.model.User;

public class RatingController extends MultiActionController {
	@Autowired
	private PersonService personService;
	
	@Autowired
	private FriendService friendService;

	private Person fetchPerson() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User user = (User) principal;

		return personService.findPersonByUsername(user.getUsername());
	}

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String personId = request.getParameter("personId");
		// do friend stuff and permission checking
		if (StringUtils.trimToNull(personId) != null) {
			if (friendService.isFriends(person, personId)) {
				person = personService.get(Long.parseLong(personId));
			}
		}
		request.setAttribute("rating", person.getRating().average());
		return new ModelAndView("rating/jsonRating");
	}

	public ModelAndView rate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String personId = request.getParameter("personId");
		// do friend stuff and permission checking
		if (StringUtils.trimToNull(personId) != null) {
			if (friendService.isFriends(person, personId)) {
				person = personService.get(Long.parseLong(personId));
				request.setAttribute("person", person);
				Integer rating = Integer.parseInt(request.getParameter("rating"));

				Rating r = person.getRating();
				r.recalculate(rating);
				r.setPerson(person);
				person.setRating(r);
				
				personService.update(person);
			}
		}
		request.setAttribute("rating", person.getRating().average());
		return new ModelAndView("rating/jsonRating");
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

}
