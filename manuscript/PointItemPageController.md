# PointItemPageController

```java
package com.myshopnshare.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.PointItem;
import com.myshopnshare.core.service.PointItemService;

public class PointItemPageController extends MultiActionController {
	
	@Autowired
	private PointItemService pointItemService;

	/** View the single item **/
	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String itemId = request.getParameter("itemId");
		if (StringUtils.trimToNull(itemId) != null) {
			PointItem item = pointItemService.get(Long.parseLong(itemId));
			request.setAttribute("item", item);
		}

		return new ModelAndView("vendorItem/vendorItemPage");
	}

	public void setPointItemService(PointItemService pointItemService) {
		this.pointItemService = pointItemService;
	}

}
```
