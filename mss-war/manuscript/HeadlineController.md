# HeadlineController

```java
package com.myshopnshare.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.Action;
import com.myshopnshare.core.domain.Headline;
import com.myshopnshare.core.domain.News;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.service.EmailService;
import com.myshopnshare.core.service.HeadlineService;
import com.myshopnshare.core.service.NewsService;
import com.myshopnshare.core.service.NewsVisibilityDomainService;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.core.service.VisibilityDomainService;
import com.myshopnshare.model.User;


public class HeadlineController extends MultiActionController {
	private final static Logger log = Logger.getLogger(HeadlineController.class);

	@Autowired
	private PersonService personService;
	@Autowired
	private HeadlineService headlineService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private NewsVisibilityDomainService newsVisibilityService;
	@Autowired
	private VisibilityDomainService visibilityService;
	@Autowired
	private EmailService emailService;

	private Person fetchPerson() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User user = (User) principal;

		return personService.findPersonByUsername(user.getUsername());
	}

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// Person person = fetchPerson();
		// request.setAttribute("emails", person.getActiveEmailAddress());
		return new ModelAndView("home/headline");
	}

	public ModelAndView all(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		request.setAttribute("emails", emailService.getPublicEmailsForPerson(person));
		return new ModelAndView("home/headline");
	}

	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String message = request.getParameter("headline");
		Person person = fetchPerson();
		
		personService.updateHeadline(person, message);
		
		// friend's feed doesn't work
		request.setAttribute("headline", message);
		// request.setAttribute("news", news.getAction().convert(person, message));

		return new ModelAndView("common/jsonHeadline");
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void setHeadlineService(HeadlineService headlineService) {
		this.headlineService = headlineService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public void setNewsVisibilityService(
			NewsVisibilityDomainService newsVisibilityService) {
		this.newsVisibilityService = newsVisibilityService;
	}

	public void setVisibilityService(VisibilityDomainService visibilityService) {
		this.visibilityService = visibilityService;
	}

}
```
