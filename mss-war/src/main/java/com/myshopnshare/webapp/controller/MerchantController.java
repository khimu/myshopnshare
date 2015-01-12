package com.myshopnshare.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.UserType;
import com.myshopnshare.core.service.PersonService;

/**
 * Display home page for merchant
 * 
 * @author khimung
 * 
 */
public class MerchantController extends MultiActionController {

	@Autowired
	private PersonService personService;

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String friendId = request.getParameter("friendId");
		Person person = personService.get(Long.parseLong(friendId));
		
		if (person.getUserType() != UserType.MERCHANT) {
			return new ModelAndView("login/login");
		}
		request.setAttribute("person", person);
		return new ModelAndView("merchant/merchantLayout");
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

}
