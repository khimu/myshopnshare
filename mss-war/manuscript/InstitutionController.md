# InstitutionController

```java
package com.myshopnshare.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.UserType;

public class InstitutionController extends BaseController {

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		if (person.getUserType() != UserType.INSTITUTION) {
			return new ModelAndView("login/login");
		}
		return new ModelAndView("institution/institutionLayout");
	}

}
```
