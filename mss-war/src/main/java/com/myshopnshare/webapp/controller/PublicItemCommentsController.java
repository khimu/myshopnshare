package com.myshopnshare.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.service.ItemService;

public class PublicItemCommentsController extends MultiActionController {
	private final static Logger log = Logger
			.getLogger(PublicItemCommentsController.class);

	@Autowired
	private ItemService itemService;

	/** View the single item **/
	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String itemId = request.getParameter("itemId");
		if (StringUtils.trimToNull(itemId) != null) {
			Item item = itemService.get(Long.parseLong(itemId));
			request.setAttribute("comments", item.getComments());
		}
		return new ModelAndView("item/jsonItemComments");
	}

	public ModelAndView all(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String itemId = request.getParameter("itemId");
		if (StringUtils.trimToNull(itemId) != null) {
			Item item = itemService.get(Long.parseLong(itemId));
			request.setAttribute("comments", item.getComments());
		}
		String isSearch = request.getParameter("isSearch");
		if (StringUtils.split(isSearch) == null) {
			return new ModelAndView("item/itemComments");
		} else {
			return new ModelAndView("item/searchItemComments");
		}
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

}
