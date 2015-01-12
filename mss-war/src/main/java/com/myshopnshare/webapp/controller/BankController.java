package com.myshopnshare.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.service.UserManager;

/*
 * Not a page.  Keep track of the last time the user logged in and multiply that by welfare check.
 */
public class BankController extends BaseController {
	private static Logger logger = Logger.getLogger(BankController.class);

	@Autowired
	private UserManager userService;

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = this.setup(request);
		// advertising
		// bank
		// hot items
		// friends
		// house images by color
		application(request);
		request.setAttribute("pageTitle", "Bank");
		return new ModelAndView("secure/bank/landing");
	}
	
}
