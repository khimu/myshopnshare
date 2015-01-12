package com.myshopnshare.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.VendorItem;
import com.myshopnshare.core.service.VendorItemService;

public class PublicSearchController extends MultiActionController {
	
	@Autowired
	private VendorItemService vendorItemService;

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String searchString = request.getParameter("searchString");
		List<VendorItem> items = vendorItemService.findWorldVendorItems(searchString,
				null, 0);
		request.setAttribute("items", items);
		return new ModelAndView("public/nonsecuresearch");
	}

	/** FIND NEW ITEMS **/
	public ModelAndView nonsecure(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String searchString = request.getParameter("searchString");
		String category = request.getParameter("category");
		String startStr = request.getParameter("start");
		int start = 0;
		if(StringUtils.trimToNull(startStr) != null){
			start =  Integer.parseInt(request.getParameter("start"));
		}
		if (StringUtils.trimToNull(category) != null && (!category.equals("SERVICE") || !category.equals("SELLING"))) {
			request.setAttribute("items", new ArrayList<VendorItem>());
			return new ModelAndView("item/jsonItems");
		}
		request.setAttribute("total", vendorItemService.findWorldVendorItemsCount(null, "SELLING"));
		List<VendorItem> items = vendorItemService.findWorldVendorItems(
				searchString, category, start);
		if (items.size() == 0) {
			request.setAttribute("searchMessage",
					"No results matched your search");
		}
		request.setAttribute("items", items);
		return new ModelAndView("item/jsonItems");
	}

	public void setVendorItemService(VendorItemService vendorItemService) {
		this.vendorItemService = vendorItemService;
	}

}
