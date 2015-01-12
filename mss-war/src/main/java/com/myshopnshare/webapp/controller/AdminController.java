package com.myshopnshare.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import com.myshopnshare.model.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.service.AuthoritiesService;
import com.myshopnshare.core.service.BankService;
import com.myshopnshare.core.service.CommentsService;
import com.myshopnshare.core.service.ConfirmEmailService;
import com.myshopnshare.core.service.EmailService;
import com.myshopnshare.core.service.FriendService;
import com.myshopnshare.core.service.NewsService;
import com.myshopnshare.core.service.OrderService;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.service.UserManager;

public class AdminController extends MultiActionController {
	@Autowired
	private UserManager userService;
	@Autowired
	private ConfirmEmailService confirmEmailService;
	@Autowired
	private AuthoritiesService authoritiesService;
	@Autowired
	private PersonService personService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private BankService bankService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private FriendService friendService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private CommentsService commentsService;


	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User user = (User) principal;
		Person person = personService.findPersonByUsername(user.getUsername());
		request.setAttribute("persons", personService.getAll());
		request.setAttribute("news", newsService.getAll());
		request.setAttribute("users", newsService.getAll());
		request.setAttribute("pageTitle", "Admin");
		return new ModelAndView("admin/landing");
	}

	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String username = request.getParameter("username");
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User user = (User) principal;
		personService.delete(personService.findPersonByUsername(username));
		Person person = personService.findPersonByUsername(user.getUsername());
		request.setAttribute("persons", personService.getAll());
		request.setAttribute("news", newsService.getAll());
		request.setAttribute("users", newsService.getAll());
		request.setAttribute("pageTitle", "Admin");
		return new ModelAndView("admin/landing");
	}

}