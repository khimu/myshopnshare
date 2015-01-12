package com.myshopnshare.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import com.myshopnshare.model.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.Action;
import com.myshopnshare.core.domain.News;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.Scribble;
import com.myshopnshare.core.service.EmailService;
import com.myshopnshare.core.service.NewsService;
import com.myshopnshare.core.service.NewsVisibilityDomainService;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.core.service.ScribbleService;
import com.myshopnshare.core.service.ScribbleVisibilityDomainService;
import com.myshopnshare.utils.EmailUtil;

public class ScribbleController extends MultiActionController {
	@Autowired
	private EmailService emailService;
	@Autowired
	private PersonService personService;
	@Autowired
	private ScribbleService scribbleService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private NewsVisibilityDomainService newsVisibilityService;
	@Autowired
	private ScribbleVisibilityDomainService scribbleVisibilityService;

	private Person fetchPerson() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User user = (User) principal;

		return personService.findPersonByUsername(user.getUsername());
	}

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String personId = request.getParameter("personId");
		String searchString = request.getParameter("searchString");
		String category = request.getParameter("category");

		Person person = fetchPerson();
		if (StringUtils.trimToNull(personId) == null
				|| person.getId().equals(Long.parseLong(personId))) {
			request.setAttribute("person", person);
			request.setAttribute("scribbles", person.getScribbles());
			person.hasReadAllScribble();
			personService.update(person);
			return new ModelAndView("scribble/jsonScribbles");
		}
		Person friend = personService.get(Long.parseLong(personId));
		if (!person.isFriends(personId)
				&& !person.getId().equals(friend.getId())) {
			// return blank
			return new ModelAndView("scribble/jsonScribbles");
		}

		request.setAttribute("scribbles", scribbleService
				.findOneFriendsScribble(friend, person, category, searchString,
						0));
		request.setAttribute("person", friend);
		return new ModelAndView("scribble/jsonScribbles");
	}

	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String start = request.getParameter("start");
		String personId = request.getParameter("personId");

		Person person = fetchPerson();
		if (StringUtils.trimToNull(personId) == null
				|| person.getId().equals(Long.parseLong(personId))) {
			return new ModelAndView("scribble/jsonScribble");
		}
		Person friend = personService.get(Long.parseLong(personId));
		if (!person.isFriends(personId)
				&& !person.getId().equals(friend.getId())) {
			return new ModelAndView("scribble/jsonScribble");
		}

		String scribbleId = request.getParameter("scribbleId");
		if (StringUtils.trimToNull(scribbleId) != null) {
			/** Place holder **/
		} else {

		}
		request.setAttribute("scribble", null);
		return new ModelAndView("scribble/jsonScribble");
	}

	/**
	 * If scribbler == person signed in, allow delete.
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();

		String scribbleId = request.getParameter("scribbleId");
		Scribble scribble = scribbleService.getScribbleForPerson(person, Long
				.parseLong(scribbleId));
		scribbleService.delete(scribble);

		request.setAttribute("scribble", scribble);
		return new ModelAndView("scribble/jsonScribble");
	}

	public ModelAndView scribble(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String personId = request.getParameter("personId");

		Person person = fetchPerson();
		Person friend = person;

		if (StringUtils.trimToNull(personId) != null) {
			friend = personService.get(Long.parseLong(personId));
		}

		if (!person.isFriends(personId)
				&& !person.getId().equals(friend.getId())) {
			return new ModelAndView("scribble/jsonScribble");
		}

		String message = request.getParameter("message");
		String tagStr = request.getParameter("tags");
		String category = request.getParameter("category");

		Scribble scribble = new Scribble();
		scribble.setScribbler(person);
		scribble.setMessage(message);
		scribble.setPerson(friend); // this is the friend you are scribbling on
		
		// friend controls who sees the scribble
		friend.getScribblePermission(scribble);
		friend.getScribbles().add(scribble);

		News news = new News();
		news.setAction(Action.SCRIBBLE);
		news.setWorld(Action.SCRIBBLE.isWorld());
		news.setPerson(person); // enter a feed for your recent action
		news.setMessage(Action.SCRIBBLE.convert(person, friend));

		// you control who sees your news
		person.getNewsPermission(news);
		person.getNews().add(news);
		
		friend.getNewsPermission(news);
		friend.getNews().add(news);

		// newsService.save(news);

		personService.update(friend);
		personService.update(person);

		EmailUtil.INSTANCE
		.sendMail(
				"New Scribble",
				"You have a new scribble from " + person.getFullName()
						, "sleekswap@gmail.com", emailService.getPrimaryEmailForPerson(friend).getEmail());

		request.setAttribute("scribble", scribble);

		return new ModelAndView("scribble/jsonScribble");
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void setScribbleService(ScribbleService scribbleService) {
		this.scribbleService = scribbleService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public void setNewsVisibilityService(
			NewsVisibilityDomainService newsVisibilityService) {
		this.newsVisibilityService = newsVisibilityService;
	}

	public void setScribbleVisibilityService(
			ScribbleVisibilityDomainService scribbleVisibilityService) {
		this.scribbleVisibilityService = scribbleVisibilityService;
	}

}
