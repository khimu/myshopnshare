# AccountController

```java
package com.myshopnshare.webapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.AddressType;
import com.myshopnshare.core.enums.CreditCardType;
import com.myshopnshare.core.enums.EmailAddressType;
import com.myshopnshare.core.enums.PhoneType;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.model.User;

public class AccountController extends MultiActionController {
	
	@Autowired
	private PersonService personService;

	protected Person fetchPerson() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User user = (User) principal;

		return personService.findPersonByUsername(user.getUsername());
	}

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();

		Map map = new HashMap();
		map.put("emailTypes", EmailAddressType.values());
		map.put("addressTypes", AddressType.values());
		map.put("phoneTypes", PhoneType.values());
		map.put("cardTypes", CreditCardType.values());

		request.setAttribute("person", person);
		return new ModelAndView("account/accountLayout", map);
	}

}
```
