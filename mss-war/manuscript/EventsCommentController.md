# EventsCommentController

```java
package com.myshopnshare.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.Action;
import com.myshopnshare.core.domain.Events;
import com.myshopnshare.core.domain.EventsComment;
import com.myshopnshare.core.domain.News;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.service.EventsService;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.model.User;
import com.myshopnshare.utils.EmailUtil;

public class EventsCommentController extends MultiActionController {
	private final static Logger log = Logger
			.getLogger(EventsCommentController.class);

	@Autowired
	private PersonService personService;
	
	@Autowired
	private EventsService eventsService;

	private Person fetchPerson() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User user = (User) principal;

		return personService.findPersonByUsername(user.getUsername());
	}

	/** View the single item **/
	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String eventId = request.getParameter("eventId");
		if (StringUtils.trimToNull(eventId) != null) {
			Events event = eventsService.get(Long.parseLong(eventId));
			request.setAttribute("comments", event.getComments());
		}
		return new ModelAndView("events/jsonEventComments");
	}

	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String eventId = request.getParameter("eventId");
		String commentId = request.getParameter("commentId");
		Person person = fetchPerson();
		if (StringUtils.trimToNull(eventId) != null
				&& StringUtils.trimToNull(commentId) != null) {
			Events event = eventsService.get(Long.parseLong(eventId));

			EventsComment comment = event
					.findComment(Long.parseLong(commentId));
			if (comment == null) {
				return new ModelAndView("common/blank");
			}
			event.getComments().remove(comment);
			comment.setEvent(null);
			comment.setPerson(null);
			personService.update(person);
		}
		return new ModelAndView("common/blank");
	}

	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String eventId = request.getParameter("eventId");
		Person person = fetchPerson();
		if (StringUtils.trimToNull(eventId) != null) {
			Events event = eventsService.get(Long.parseLong(eventId));
			String comment = request.getParameter("comment");

			EventsComment comments = new EventsComment();
			comments.setMessage(comment);
			comments.setPerson(person);
			comments.setEvent(event);
			event.getComments().add(comments);
			eventsService.update(event);

			News itemnews = new News();
			itemnews.setAction(Action.MESSAGE);
			itemnews.setWorld(Action.MESSAGE.isWorld());
			itemnews.setPerson(person);
			itemnews.setMessage(Action.MESSAGE.convert(person,
					" commented on the event " + event.getTitle()));
			person.getNewsPermission(itemnews);
			person.getNews().add(itemnews);
			
			personService.update(person);
			
			EmailUtil.INSTANCE.sendMail("Event comment",
					person.getAlias() + " commented on your event " + event.getTitle() , "sleekswap@gmail.com",
					event.getPerson().getAlias());	
			
			request.setAttribute("comment", comments);
		}
		return new ModelAndView("events/jsonEventComment");
	}

	public void setEventsService(EventsService eventsService) {
		this.eventsService = eventsService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

}
```
