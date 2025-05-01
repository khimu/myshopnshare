# VendorItemPageController

```java
package com.myshopnshare.webapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.myshopnshare.core.domain.VendorItem;
import com.myshopnshare.core.service.VendorItemService;
import com.myshopnshare.core.service.VendorItemVisibilityDomainService;

/**
 * Manage user item page
 * 
 * @author khimung
 * 
 */
public class VendorItemPageController extends BaseController {
	@Autowired
	private VendorItemService vendorItemService;
	@Autowired
	private VendorItemVisibilityDomainService vendorItemVisibilityDomainService;

	/** View the single item **/
	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// NOT SURE WHY VendorPage needs to set up this request but modified
		// 4/7/2009. Everything works fine before this change.
		// setup(request);
		String itemId = request.getParameter("itemId");
		VendorItem item = vendorItemService.get(Long.parseLong(itemId));
		if (item.isActive() == false) {
			item.setId(null);
			item.setItemName("DELETED");
			item.setDescription("Item Deleted");
			item.setTitle("Not Available");
		} else {
			item.increment();
			vendorItemService.update(item);
		}
		request.setAttribute("item", item);
		return new ModelAndView("vendorItem/vendorLayout");
	}

	/** View the single item **/
	public ModelAndView detail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// setup(request);
		String itemId = request.getParameter("itemId");
		VendorItem item = vendorItemService.get(Long.parseLong(itemId));
		if (item.isActive() == false) {
			item.setId(null);
			item.setItemName("DELETED");
			item.setDescription("Item Deleted");
			item.setTitle("Not Available");
		} else {
			item.increment();
			vendorItemService.update(item);
		}
		request.setAttribute("item", item);
		return new ModelAndView("vendorItem/vendorItemPage");
	}

	public void setVendorItemService(VendorItemService vendorItemService) {
		this.vendorItemService = vendorItemService;
	}

	public void setVendorItemVisibilityDomainService(
			VendorItemVisibilityDomainService vendorItemVisibilityDomainService) {
		this.vendorItemVisibilityDomainService = vendorItemVisibilityDomainService;
	}

}
```
