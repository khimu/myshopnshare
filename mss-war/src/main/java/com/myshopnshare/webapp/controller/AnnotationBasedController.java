package com.myshopnshare.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.myshopnshare.core.domain.Bank;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.service.BankService;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.core.service.RequestFriendService;
import com.myshopnshare.model.User;

public abstract class AnnotationBasedController {

	@Autowired
	protected PersonService personService;
	@Autowired
	protected BankService bankService;
	@Autowired
	protected RequestFriendService requestFriendService;

	protected Person fetchPerson() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User user = (User) principal;

		return personService.findPersonByUsername(user.getUsername());
	}

	protected Person setup(HttpServletRequest request) throws Exception {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User user = (User) principal;

		Person person = personService.findPersonByUsername(user.getUsername());
		Bank bank = bankService.get(person.getBank().getId());

		if (bank != null) {
			bankService.updateBank(bank);
		}
		request.setAttribute("bank", bank);
		request.setAttribute("friendRequests", requestFriendService
				.findAllRequestFor(person));

		request.setAttribute("application", "mySHOPnSHARE");

		request.setAttribute("person", person);

		return person;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(false);

		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));

		binder.registerCustomEditor(String.class,
				new StringTrimmerEditor(false));

		binder.registerCustomEditor(Integer.class, null,
				new CustomNumberEditor(Integer.class, null, true));

		binder.registerCustomEditor(Long.class, null, new CustomNumberEditor(
				Long.class, null, true));

		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());

		binder.registerCustomEditor(Boolean.class, null,
				new CustomBooleanEditor("true", "false", true));
	}

}
