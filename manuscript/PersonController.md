# PersonController

```java
package com.myshopnshare.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.model.User;
import com.myshopnshare.service.UserManager;
import com.myshopnshare.utils.EmailUtil;

public class PersonController extends MultiActionController {
	private static transient final Log log = LogFactory
			.getLog(PersonController.class);

	protected final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
	static {
		simpleDateFormat.applyPattern("MM/dd/yyyy");
	}

	@Autowired
	private PersonService personService;
	@Autowired
	private UserManager userService;

	private Person fetchPerson() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User user = (User) principal;

		return personService.findPersonByUsername(user.getUsername());
	}

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("account/account");
	}

	public ModelAndView edit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			Person person = fetchPerson();
			person.setFirstName(request.getParameter("firstName") == null ? person.getFirstName() : request.getParameter("firstName"));
			person.setLastName(request.getParameter("lastName") == null ? person.getLastName() : request.getParameter("lastName"));
			person.setBirthday(request.getParameter("birthday") == null ? person.getBirthday() : simpleDateFormat.parse(request.getParameter("birthday")));

			personService.update(person);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("person", person);

			// return new ModelAndView(new JSONView(), map);
			return new ModelAndView("account/person/person", map);
		} catch (Exception e) {
			log.equals(e.getMessage());
			return new ModelAndView("account/person/person");
		}
	}

	/**
	 * What to do when the user unactivate their account.
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			Person person = fetchPerson();
			User user = person.getUserAccount();
			user.setEnabled(false);
			
			person.setActive(false);
			personService.update(person);

			return new ModelAndView("redirect:j_spring_security_logout");
		} catch (Exception e) {
			log.equals(e.getMessage());
			return new ModelAndView("account/person/person");
		}
	}
	
	public ModelAndView invites(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String recipients = request.getParameter("recipients");
		EmailUtil.INSTANCE.sendMail("Join SleekSwap Today",
				person.getFullName() +  " has invited you to join SleekSwap.", "sleekswap@gmail.com",
				recipients);
		return null;
	}

}
```
