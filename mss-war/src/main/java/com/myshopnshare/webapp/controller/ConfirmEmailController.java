package com.myshopnshare.webapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.myshopnshare.core.domain.ConfirmEmail;
import com.myshopnshare.core.service.ConfirmEmailService;
import com.myshopnshare.core.service.RegisterService;
import com.myshopnshare.model.User;
import com.myshopnshare.webapp.form.ConfirmForm;

public class ConfirmEmailController extends SimpleFormController {
	private static final Log log = LogFactory.getLog(ConfirmEmailController.class);
	
	@Autowired
	private ConfirmEmailService confirmEmailService;
	@Autowired
	private RegisterService registerService;
	
	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		ConfirmForm form = (ConfirmForm) command;
		String confirm = form.getConfirm(); // (String)
		// request.getAttribute("confirm");
		ConfirmEmail confirmEmail = confirmEmailService.findByKey(confirm);
		if (confirmEmail != null) {
			
			User user = registerService.completeRegister(confirmEmail);

			/*
	        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
	        		user.getUsername(), user.getPassword(), user.getAuthorities());
	        auth.setDetails(user);
	        SecurityContextHolder.getContext().setAuthentication(auth);
	        */
	        
			Map<String, String> map = new HashMap<String, String>();
			map.put("j_username", confirmEmail.getEmail());
			map.put("j_password", confirmEmail.getPassword());
			map.put("_spring_security_remember_me", "true");

			return new ModelAndView("redirect:j_spring_security_check", map);	        
	        

			//return new ModelAndView("redirect:secure/subscribe.do");
			//return new ModelAndView("redirect:/secure/home.do");
		}
		request.setAttribute("error", 1);
		return new ModelAndView("login/confirmRegister");
	}
	

	/*
	@Override
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		ConfirmForm form = (ConfirmForm) command;
		String confirm = form.getConfirm(); // (String)
		// request.getAttribute("confirm");
		ConfirmEmail confirmEmail = confirmEmailService.findByKey(confirm);
		if (confirmEmail != null) {

			User user = new User();
			user.setEmail(confirmEmail.getEmail());
			user.setVersion(0);
			user.setEnabled(true);
			user.setUsername(confirmEmail.getEmail());
			user.setPassword(confirmEmail.getPassword());

			Authorities authorities = new Authorities();
			authorities.setUsername(confirmEmail.getEmail());
			authorities.setAuthority(Authority.ROLE_MERCHANT);
			authoritiesService.save(authorities);

			Role r = roleService.getRole(confirmEmail.getAuthority().name());
			if (r == null) {
				r = new Role();
				roleService.save(r);
			}
			user.setRoles(new HashSet<Role>());
			user.getRoles().add(r);

			try {
				userService.save(user);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
				Person person = emailService.findPersonByEmail(confirmEmail
						.getEmail());
				personService.delete(person);
				EmailUtil.INSTANCE.sendMail("Error", e.getMessage(),
						"sleekswap@gmail.com", "sleekswap@gmail.com");
				request
						.setAttribute("databaseError",
								"Unable to create user account at this time. Please try again later.");
				return new ModelAndView("login/emailExist");
			}
			Person person = emailService.get(
					confirmEmail.getEmailAddress().getId()).getPerson();
			person.setUserAccount(user);
			person.setSubscriptionType(SubscriptionType.DEFAULT);
			person.setActive(true);

			PageViewed viewed = new PageViewed();
			viewed.setPerson(person);
			person.setViewed(viewed);

			makeNewPerson(person);

			// person.setSubscribed(true);

			News news = new News();
			if (person.getUserType() == UserType.BUSINESS_SERVICE
					|| person.getUserType() == UserType.MERCHANT) {
				news.setAction(Action.NEW_EMPLOYER);
				news.setWorld(Action.NEW_EMPLOYER.isWorld());
				news.setMessage(Action.NEW_EMPLOYER.convert(person, ""));
			} else if (person.getUserType() == UserType.INSTITUTION) {
				news.setAction(Action.NEW_INSTITUTION);
				news.setWorld(Action.NEW_INSTITUTION.isWorld());
				news.setMessage(Action.NEW_INSTITUTION.convert(person, ""));
			} else if (person.getUserType() == UserType.USER) {
				news.setAction(Action.NEW_MEMBER);
				news.setWorld(Action.NEW_MEMBER.isWorld());
				news.setMessage(Action.NEW_MEMBER.convert(person, ""));
			}

			news.setPerson(person); // enter a feed for your recent action

			person.getNewsPermission(news);
			person.getNews().add(news);

			personService.update(person);

			Map<String, String> map = new HashMap<String, String>();
			map.put("j_username", confirmEmail.getEmail());
			map.put("j_password", confirmEmail.getPassword());
			map.put("_spring_security_remember_me", "true");

			return new ModelAndView("redirect:j_spring_security_check", map);
			// return new ModelAndView("confirmSuccessful.do");
		}
		request.setAttribute("error", 1);
		return new ModelAndView("login/confirmRegister");
	}
	*/
}
