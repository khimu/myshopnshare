package com.myshopnshare.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.Counter;
import com.myshopnshare.core.domain.VendorItem;
import com.myshopnshare.core.service.CounterService;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.core.service.VendorItemService;

public class PublicPageController extends MultiActionController {
	@Autowired
	private CounterService counterService;
	@Autowired
	private VendorItemService vendorItemService;
	@Autowired
	private PersonService personService;

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Counter counter = counterService.findGeneralCounter();
		counter.increment();
		counterService.saveOrUpdate(counter);

		request.setAttribute("counter", counter);

		List<VendorItem> items = null;
		String searchString = request.getParameter("searchString");
		request.setAttribute("total", vendorItemService
				.findWorldVendorItemsCount(searchString, "SELLING"));
		items = vendorItemService.findWorldVendorItems(searchString, "SELLING",
				0);

		if (items.size() == 0) {
			request.setAttribute("searchMessage",
					"No results matched your search");
		}
		request.setAttribute("items", items);
		request.setAttribute("merchants", personService.findMerchants());
		return new ModelAndView("public/publicLayout");
	}

	public ModelAndView counter(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Counter counter = counterService.findGeneralCounter();
		request.setAttribute("counter", counter);
		return new ModelAndView("login/counter");
	}

	public void setCounterService(CounterService counterService) {
		this.counterService = counterService;
	}

	public void setVendorItemService(VendorItemService vendorItemService) {
		this.vendorItemService = vendorItemService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

}
