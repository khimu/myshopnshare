# SearchEventsController

```java
package com.myshopnshare.webapp.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import com.myshopnshare.model.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.EventStatusType;
import com.myshopnshare.core.service.EventsService;
import com.myshopnshare.core.service.PersonService;

public class SearchEventsController extends MultiActionController {
	@Autowired
	private PersonService personService;
	@Autowired
	private EventsService eventsService;
	
	private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
	static {
		simpleDateFormat.applyPattern("MM/dd/yyyy");
	}
	
	private Person fetchPerson() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User user = (User) principal;

		return personService.findPersonByUsername(user.getUsername());
	}
	
	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String searchString = request.getParameter("searchString");
		String startDate = request.getParameter("startDate");

		request.setAttribute("items", eventsService.findPublicEvents(startDate, searchString, 0));

		return new ModelAndView("events/results");
	}

	public ModelAndView afriends(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String personId = request.getParameter("personId");
		
		String searchString = request.getParameter("searchString");
		String startDate = request.getParameter("startDate");

		// do friend stuff and permission checking
		if (StringUtils.trimToNull(personId) != null && !person.getId().equals(Long.parseLong(personId))) {
			if (person.isFriends(personId)) {
				Person friend = personService.get(Long.parseLong(personId));

				request.setAttribute("events", eventsService.findOneFriendsEvents(friend, person, startDate, searchString, 0));
			}
		}
		
		request.setAttribute("events", eventsService.findOwnEvents(person, searchString, startDate, 0));

		request.setAttribute("eventStatusType", EventStatusType.values());
		return new ModelAndView("events/results");
	}

	public ModelAndView friends(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String searchString = request.getParameter("searchString");
		String startDate = request.getParameter("startDate");

		Person person = fetchPerson();
		request.setAttribute("events", eventsService.findAllFriendsEvents(person, startDate, searchString, 0));
		return new ModelAndView("events/results");
	}

	public ModelAndView all(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String searchString = request.getParameter("searchString");
		String startDate = request.getParameter("startDate");

		Person person = fetchPerson();
		request.setAttribute("events", eventsService.findWorldEvents(person, searchString, startDate, 0));
		return new ModelAndView("events/results");
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void setEventsService(EventsService eventsService) {
		this.eventsService = eventsService;
	}
	
}
```
