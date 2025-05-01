# LoginRedirectController

```java
package com.myshopnshare.webapp.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import com.myshopnshare.model.User;
import org.springframework.web.servlet.ModelAndView;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.AddressType;
import com.myshopnshare.core.enums.CreditCardType;
import com.myshopnshare.core.enums.EmailAddressType;
import com.myshopnshare.core.enums.PhoneType;
import com.myshopnshare.core.service.AddressService;
import com.myshopnshare.core.service.CreditCardService;
import com.myshopnshare.core.service.EmailService;
import com.myshopnshare.core.service.HeadlineService;
import com.myshopnshare.core.service.PhoneService;

public class LoginRedirectController extends BaseController {
	private static Logger log = Logger.getLogger(LoginRedirectController.class);

	protected Person fetchPerson() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User user = (User) principal;

		return personService.findPersonByUsername(user.getUsername());
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person =  this.setup(request);
		// Merchants have ad capability while ads do not have merchant
		// capability...ads are limited merchants
		/*
		if (person.getUserType() == UserType.MERCHANT) {
			return new ModelAndView("redirect:secure/subscribe.do");
			//return new ModelAndView("redirect:secure/merchant.do");
		} else if (person.getUserType() == UserType.ADS) {
			return new ModelAndView("redirect:secure/subscribe.do");
			//return new ModelAndView("redirect:secure/ads.do");
		} else if (person.getUserType() == UserType.INSTITUTION) {
			return new ModelAndView("redirect:secure/subscribe.do");
		} else if (person.getUserType() == UserType.BUSINESS_SERVICE) {
			return new ModelAndView("redirect:secure/subscribe.do");
		} else {
			return new ModelAndView("redirect:secure/subscribe.do");
		}
		*/
	

		
		return new ModelAndView("redirect:secure/subscribe.do");
		//return new ModelAndView("redirect:secure/home.do");
	}

}
```
