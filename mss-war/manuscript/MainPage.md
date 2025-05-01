# MainPage

```java
package com.myshopnshare.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myshopnshare.core.domain.PointItem;
import com.myshopnshare.core.enums.CategoryType;
import com.myshopnshare.core.service.ItemCategoryService;
import com.myshopnshare.core.service.NewsService;
import com.myshopnshare.core.service.PointItemService;
import com.myshopnshare.core.service.VendorItemService;

@Controller
public class MainPage extends AnnotationBasedController{
	
	@Autowired
	private NewsService newsService;

	@Autowired
	private VendorItemService vendorItemService;
	
	@Autowired
	private PointItemService pointItemService;
	
	@Autowired
	private ItemCategoryService itemCategoryService;

	@RequestMapping("/main.do")
	public String view(HttpServletRequest request){
		request.setAttribute("items", vendorItemService.findWorldVendorItems("", CategoryType.SELLING.name(), 0));
		request.setAttribute("itemTotal", vendorItemService.findWorldVendorItemsCount("", CategoryType.SELLING.name()));

		List<PointItem> pointItems = pointItemService.getAllActive();
		request.setAttribute("pointItems", pointItems);
		request.setAttribute("pointItemTotal", pointItems.size());
		
		request.setAttribute("news", newsService.findWorldNews(0));
		request.setAttribute("newsTotal", newsService.findWorldNewsCount());
		
		request.setAttribute("members", personService.getAllActive());
		
		request.setAttribute("merchants", personService.getAllActive());

		return "home";
	}
	
}
```
