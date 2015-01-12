package com.myshopnshare.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import com.myshopnshare.model.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.service.EmailService;
import com.myshopnshare.core.service.PersonService;

public class PhotoController extends MultiActionController {
	private static transient final Log log = LogFactory
			.getLog(EmailController.class);

	@Autowired
	private PersonService personService;
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
		return new ModelAndView("photos/photoLayout");
	}

	public EmailService getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

}
