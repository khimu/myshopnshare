package com.myshopnshare.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.service.ItemService;
import com.myshopnshare.core.service.ItemVisibilityDomainService;

/**
 * Manage user item page
 * 
 * @author khimung
 *
 */
public class ItemPageController extends BaseController {
	
	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemVisibilityDomainService itemVisibilityDomainService;

	/** View the single item **/
	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setup(request);
		String itemId = request.getParameter("itemId");
		Item item = itemService.get(Long.parseLong(itemId));
		if(item.isActive() == false){
			item.setId(null);
			item.setItemName("DELETED");
			item.setDescription("Item Deleted");
			item.setTitle("Not Available");
		}else{
			item.increment();
			itemService.update(item);
		}
		request.setAttribute("item", item);
		return new ModelAndView("item/itemLayout");
	}

	/** View the single item **/
	public ModelAndView detail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setup(request);
		String itemId = request.getParameter("itemId");
		Item item = itemService.get(Long.parseLong(itemId));
		if(item.isActive() == false){
			item.setId(null);
			item.setItemName("DELETED");
			item.setDescription("Item Deleted");
			item.setTitle("Not Available");
		}else{
			item.increment();
			itemService.update(item);
		}
		request.setAttribute("item", item);
		return new ModelAndView("item/itemPage");
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public void setItemVisibilityDomainService(
			ItemVisibilityDomainService itemVisibilityDomainService) {
		this.itemVisibilityDomainService = itemVisibilityDomainService;
	}

}
