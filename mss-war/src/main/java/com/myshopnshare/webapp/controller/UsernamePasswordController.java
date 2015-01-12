package com.myshopnshare.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.Action;
import com.myshopnshare.core.domain.News;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.UserType;
import com.myshopnshare.core.service.ConfirmEmailService;
import com.myshopnshare.core.service.EmailService;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.model.User;
import com.myshopnshare.service.UserManager;
import com.myshopnshare.utils.EmailUtil;

public class UsernamePasswordController extends MultiActionController {
	
	@Autowired
	private PersonService personService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private UserManager userService;
	@Autowired
	private ConfirmEmailService confirmEmailService;

	private Person fetchPerson() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User user = (User) principal;

		return personService.findPersonByUsername(user.getUsername());
	}

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return new ModelAndView("redirect: retrieve.do?method=forgotPassword");
	}

	public ModelAndView forgotPassword(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String emailAddress = request.getParameter("emailAddress");
		Person person = emailService.findPersonByEmail(emailAddress);
		User user = person.getUserAccount();

		EmailUtil.INSTANCE.sendMail("Password",
				"Please use the password below.  " + user.getPassword(),
				"sleekswap@gmail.com", emailService.getPrimaryEmailForPerson(person)
						.getEmail());

		request.setAttribute("passwordMessage",
				"Your password has been sent to your email");
		return new ModelAndView("login/forgotPassword");
	}

	public ModelAndView forgotUsername(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String emailAddress = request.getParameter("emailAddress");
		Person person = emailService.findPersonByEmail(emailAddress);
		User user = person.getUserAccount();

		EmailUtil.INSTANCE.sendMail("Username",
				"Please use the username below.  " + user.getUsername(),
				"sleekswap@gmail.com", emailService.getPrimaryEmailForPerson(person)
						.getEmail());

		request.setAttribute("usernameMessage",
				"Your username has been sent to your email");
		return new ModelAndView("login/forgotUsername");
	}

	public ModelAndView reactivate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String email = request.getParameter("email");
				
		Person person = emailService.findPersonByEmail(email);
		person.setActive(true);
		person.getUserAccount().setEnabled(true);
		
		News news = new News();
		if (person.getUserType() == UserType.BUSINESS_SERVICE
				|| person.getUserType() == UserType.MERCHANT) {
			news.setAction(Action.NEW_EMPLOYER);
			news.setWorld(Action.NEW_EMPLOYER.isWorld());
			news.setMessage(Action.NEW_EMPLOYER.convert(person, ""));
		} else if (person.getUserType() == UserType.INSTITUTION) {
			news.setAction(Action.NEW_INSTITUTION);
			news.setWorld(Action.NEW_INSTITUTION.isWorld());
			news.setMessage(Action.NEW_INSTITUTION.convert(person, ""));
		} else if (person.getUserType() == UserType.USER) {
			news.setAction(Action.NEW_MEMBER);
			news.setWorld(Action.NEW_MEMBER.isWorld());
			news.setMessage(Action.NEW_MEMBER.convert(person, ""));
		}

		news.setPerson(person); // enter a feed for your recent action
		person.getNewsPermission(news);
		person.getNews().add(news);
		
		personService.update(person);

		return new ModelAndView("login/login");
	}

}
