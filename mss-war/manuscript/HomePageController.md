# HomePageController

```java
package com.myshopnshare.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.myshopnshare.core.domain.Headline;
import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.AddressType;
import com.myshopnshare.core.enums.CreditCardType;
import com.myshopnshare.core.enums.EmailAddressType;
import com.myshopnshare.core.enums.PhoneType;
import com.myshopnshare.core.enums.SubscriptionType;
import com.myshopnshare.core.service.CartItemService;
import com.myshopnshare.core.service.ItemCategoryService;

/*
 * Displays feed for friend and world
 */
public class HomePageController extends BaseController {
	private static Logger logger = Logger.getLogger(HomePageController.class);



	/*
	 * Default method for displaying news.
	 */
	@Transactional
	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println("HomePageController");
		//Person person = this.setup(request);
		
		setupHomePage(request);
		/*
		request.setAttribute("emailTypes", EmailAddressType.values());
		request.setAttribute("addressTypes", AddressType.values());
		request.setAttribute("phoneTypes", PhoneType.values());
		request.setAttribute("cardTypes", CreditCardType.values());

		request.setAttribute("subscriptionTypes", SubscriptionType.values());
		return new ModelAndView("subscribe/subscribeLayout");		
		*/
		//return new ModelAndView("redirect:secure/subscribe.do");
		return new ModelAndView("home/homeLayout");
	}

}
```
