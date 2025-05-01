# SettingsController

```java
package com.myshopnshare.webapp.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.Customize;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.PermissionType;
import com.myshopnshare.core.service.FriendService;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.model.User;
import com.myshopnshare.service.UserManager;

public class SettingsController extends MultiActionController {
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private UserManager userService;
	
	@Autowired
	private FriendService friendService;

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		request.setAttribute("person", person);

		request.setAttribute("groups", person.getUserGroups());
		request.setAttribute("permissions", person.getPermissions());
		request.setAttribute("permissionTypes", PermissionType.values());
		request.setAttribute("permission", person.getPermissionForType(PermissionType.NEWS));
		request.setAttribute("friends", friendService.findOwnFriend(person, 0));
		
		return new ModelAndView("settings/settingsLayout");
	}

	public ModelAndView customize(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map map = new HashMap();
		Person person = fetchPerson();
		String backgroundColor = request.getParameter("backgroundColor");
		String backgroundImage = request.getParameter("backgroundImage");
		Customize customize = person.getCustomize();
		customize.setBackgroundColor(backgroundColor);
		customize.setBackgroundImage(backgroundImage);
		person.setCustomize(customize);
		personService.update(person);

		return new ModelAndView("common/blank", map);
	}

	public ModelAndView regenerate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			Person person = fetchPerson();

			String password = request.getParameter("password");

			Random random = new Random(System.currentTimeMillis());
			Integer i = random.nextInt();

			User user = person.getUserAccount();
			user.setPassword(password);
			userService.save(user);

			Map<String, String> map = new HashMap<String, String>();
			map.put("j_username", user.getUsername());
			map.put("j_password", user.getPassword());
			map.put("_spring_security_remember_me", "true");

			return new ModelAndView("redirect:/j_spring_security_check", map);
		} catch (Exception e) {
			return new ModelAndView("login/login");
		}
	}

	private Person fetchPerson() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User user = (User) principal;

		return personService.findPersonByUsername(user.getUsername());
	}

}
```
