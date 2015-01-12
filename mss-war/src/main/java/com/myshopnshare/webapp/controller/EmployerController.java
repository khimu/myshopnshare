package com.myshopnshare.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.UserType;

public class EmployerController extends BaseController {

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		if (person.getUserType() != UserType.BUSINESS_SERVICE) {
			return new ModelAndView("login/login");
		}
		request.setAttribute("headline", person.getCurrentHeadline());
		return new ModelAndView("employer/employerLayout");
	}

}
