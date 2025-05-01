# SearchServicesController

```java
package com.myshopnshare.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.myshopnshare.core.domain.VendorItem;
import com.myshopnshare.core.service.VendorItemService;

public class SearchServicesController extends BaseController {
	
	@Autowired
	private VendorItemService vendorItemService;

	/** FIND NEW ITEMS **/
	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String startStr = request.getParameter("start");
		int start = 0;
		if(StringUtils.trimToNull(startStr) != null){
			start =  Integer.parseInt(request.getParameter("start"));
		}
		request.setAttribute("total", vendorItemService.findWorldVendorItemsCount(null, "SERVICE"));
		List<VendorItem> items = vendorItemService.findWorldVendorItems(null, "SERVICE", start);
		request.setAttribute("items", items);
		return new ModelAndView("search/searchItemResult");
	}

	/** FIND NEW ITEMS **/
	public ModelAndView popup(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String startStr = request.getParameter("start");
		int start = 0;
		if(StringUtils.trimToNull(startStr) != null){
			start =  Integer.parseInt(request.getParameter("start"));
		}
		request.setAttribute("total", vendorItemService.findWorldVendorItemsCount(null, "SERVICE"));
		List<VendorItem> items = vendorItemService.findWorldVendorItems(null, "SERVICE", start);
		request.setAttribute("items", items);
		return new ModelAndView("search/searchLayout");
	}

	/** FIND NEW ITEMS **/
	public ModelAndView all(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String searchString = request.getParameter("searchString");
		String startStr = request.getParameter("start");
		int start = 0;
		if(StringUtils.trimToNull(startStr) != null){
			start =  Integer.parseInt(request.getParameter("start"));
		}
		request.setAttribute("total", vendorItemService.findWorldVendorItemsCount(searchString, "SERVICE"));
		List<VendorItem> items = vendorItemService
				.findWorldVendorItems(searchString, "SERVICE", start);
		if (items.size() == 0) {
			request.setAttribute("searchMessage",
					"No results matched your search");
		}
		request.setAttribute("items", items);
		return new ModelAndView("item/jsonItems");
	}

	// Searching on tag value
	public ModelAndView hottest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String searchString = request.getParameter("searchString");
		String startStr = request.getParameter("start");
		int start = 0;
		if(StringUtils.trimToNull(startStr) != null){
			start =  Integer.parseInt(request.getParameter("start"));
		}
		request.setAttribute("total", vendorItemService.findHotestItemsCount(searchString, "SERVICE"));
		List<VendorItem> items = vendorItemService
				.findHotestItems(searchString, "SERVICE", start);
		if (items.size() == 0) {
			request.setAttribute("searchMessage",
					"No results matched your search");
		}
		request.setAttribute("items", items);
		return new ModelAndView("item/jsonItems");
	}

	// Searching on tag value
	public ModelAndView cheapest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String searchString = request.getParameter("searchString");
		String startStr = request.getParameter("start");
		int start = 0;
		if(StringUtils.trimToNull(startStr) != null){
			start =  Integer.parseInt(request.getParameter("start"));
		}
		request.setAttribute("total", vendorItemService.findCheapestCount(searchString, "SERVICE"));
		List<VendorItem> items = vendorItemService.findCheapest(searchString, "SERVICE", start);
		if (items.size() == 0) {
			request.setAttribute("searchMessage",
					"No results matched your search");
		}
		request.setAttribute("items", items);
		return new ModelAndView("item/jsonItems");
	}

	// Searching on tag value
	public ModelAndView clearance(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String searchString = request.getParameter("searchString");
		String startStr = request.getParameter("start");
		int start = 0;
		if(StringUtils.trimToNull(startStr) != null){
			start =  Integer.parseInt(request.getParameter("start"));
		}
		request.setAttribute("total", vendorItemService.findClearanceCount(searchString, "SERVICE"));
		List<VendorItem> items = vendorItemService.findClearance(searchString, "SERVICE", start);
		if (items.size() == 0) {
			request.setAttribute("searchMessage",
					"No results matched your search");
		}
		request.setAttribute("items", items);
		return new ModelAndView("item/jsonItems");
	}

	// Searching on tag value
	public ModelAndView rebates(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String searchString = request.getParameter("searchString");
		String startStr = request.getParameter("start");
		int start = 0;
		if(StringUtils.trimToNull(startStr) != null){
			start =  Integer.parseInt(request.getParameter("start"));
		}
		request.setAttribute("total", vendorItemService.findRebatesCount(searchString, "SERVICE"));
		List<VendorItem> items = vendorItemService.findRebates(searchString, "SERVICE", start);
		if (items.size() == 0) {
			request.setAttribute("searchMessage",
					"No results matched your search");
		}
		request.setAttribute("items", items);
		return new ModelAndView("item/jsonItems");
	}

	// Searching on tag value
	public ModelAndView free(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String searchString = request.getParameter("searchString");
		String startStr = request.getParameter("start");
		int start = 0;
		if(StringUtils.trimToNull(startStr) != null){
			start =  Integer.parseInt(request.getParameter("start"));
		}
		request.setAttribute("total", vendorItemService.findFreeCount(searchString, "SERVICE"));
		List<VendorItem> items = vendorItemService.findFree(searchString, "SERVICE", start);
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
```
