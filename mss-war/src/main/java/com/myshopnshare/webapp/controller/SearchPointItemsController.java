package com.myshopnshare.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import com.myshopnshare.model.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.core.service.PointItemService;

public class SearchPointItemsController extends MultiActionController {
	
	@Autowired
	private PointItemService pointItemService;
	
	@Autowired
	private PersonService personService;

	protected Person fetchPerson() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User user = (User) principal;

		return personService.findPersonByUsername(user.getUsername());
	}

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Item> items = null;
		String searchString = request.getParameter("searchString");
		String vendorId = request.getParameter("vendorId");

		request.setAttribute("items", pointItemService.findItems(vendorId,
				searchString));

		return new ModelAndView("item/jsonItems");
	}

	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String itemId = request.getParameter("itemId");

		if (StringUtils.trimToNull(itemId) != null) {
			request.setAttribute("item", pointItemService.get(Long
					.parseLong(itemId)));
		}

		return new ModelAndView("vendorItem/vendorItemPage");
	}

	public void setPointItemService(PointItemService pointItemService) {
		this.pointItemService = pointItemService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

}
