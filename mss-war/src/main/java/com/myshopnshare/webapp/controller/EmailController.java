package com.myshopnshare.webapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.EmailAddress;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.EmailAddressType;
import com.myshopnshare.core.enums.VisibilityType;
import com.myshopnshare.core.service.EmailService;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.model.User;

public class EmailController extends MultiActionController {
	private static transient final Log log = LogFactory
			.getLog(EmailController.class);

	@Autowired
	private PersonService personService;
	
	@Autowired
	private EmailService emailService;

	private Person fetchPerson() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User user = (User) principal;

		return personService.findPersonByUsername(user.getUsername());
	}

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// Person person = fetchPerson();
		// request.setAttribute("emails", person.getActiveEmailAddress());
		return new ModelAndView("account/email/email");
	}

	public ModelAndView all(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		request.setAttribute("emails", person.getActiveEmailAddress());
		return new ModelAndView("account/email/jsonEmails");
	}

	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			Person person = fetchPerson();
			String email = request.getParameter("email");
			String primaryEmail = request.getParameter("primaryEmail");
			String type = request.getParameter("emailType");
			String visibility = request.getParameter("visibility");
			
			EmailAddress emailAddress = new EmailAddress();
			emailAddress.setEmail(email);
			emailAddress.setPrimaryEmail(StringUtils.trimToNull(primaryEmail) == null ? false : Boolean
					.parseBoolean(primaryEmail));
			emailAddress.setType(StringUtils.trimToNull(type) == null ? null : EmailAddressType.valueOf(type));
			emailAddress.setVisibility(StringUtils.trimToNull(visibility) == null ? null : VisibilityType.valueOf(visibility));
			emailAddress.setPerson(person);
			emailService.save(emailAddress);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("email", emailAddress);

			// return new ModelAndView(new JSONView(), map);
			return new ModelAndView("account/email/jsonEmail", map);
		} catch (Exception e) {
			log.equals(e.getMessage());
			return new ModelAndView("account/email/jsonEmail");
		}
	}

	public ModelAndView edit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			Person person = fetchPerson();
			String recordId = request.getParameter("id");
			String email = request.getParameter("email");
			String primaryEmail = request.getParameter("primaryEmail");
			String type = request.getParameter("emailType");
			String visibility = request.getParameter("visibility");
			
			EmailAddress emailAddress = emailService.getEmailAddressForPerson(person, Long.parseLong(recordId));
	
			if(emailAddress != null){
				emailAddress.setEmail(StringUtils.trimToNull(email) == null ? emailAddress.getEmail() : email);
				emailAddress.setPrimaryEmail(StringUtils.trimToNull(primaryEmail) == null ? false : Boolean.parseBoolean(primaryEmail));
				emailAddress.setType(StringUtils.trimToNull(type) == null ? emailAddress.getType() : EmailAddressType.valueOf(type));
				emailAddress.setPerson(person);
				emailAddress.setVisibility(StringUtils.trimToNull(visibility) == null ? emailAddress.getVisibility() : VisibilityType.valueOf(visibility));
				emailService.update(emailAddress);
				
				request.setAttribute("email", emailAddress);
			}
			return new ModelAndView("account/email/jsonEmail");
		} catch (Exception e) {
			log.equals(e.getMessage());
			return new ModelAndView("account/email/jsonEmail");
		}
	}

	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			Person person = fetchPerson();
			String recordId = request.getParameter("recordId");
			EmailAddress email = emailService.getEmailAddressForPerson(person, Long.parseLong(recordId));

			Map map = new HashMap();
			map.put("email", email);

			return new ModelAndView("account/email/jsonEmail", map);
		} catch (Exception e) {
			log.equals(e.getMessage());
			return new ModelAndView("account/email/jsonEmail");
		}
	}

	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			Person person = fetchPerson();
			String recordId = request.getParameter("recordId");
			EmailAddress email = emailService.getEmailAddressForPerson(person, Long.parseLong(recordId));

			email.setActive(false);
			emailService.update(email);
			Map<String, Object> map = new HashMap<String, Object>();

			
			// map.put("emails", person.getActiveEmailAddress());

			return new ModelAndView("account/email/jsonEmail", map);
		} catch (Exception e) {
			log.equals(e.getMessage());
			return new ModelAndView("account/email/jsonEmail");
		}
	}

	private String parseEmail(String email) {
		return StringUtils.substring(email, email.indexOf("@") + 1,
				email.indexOf(".")).toUpperCase();
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

}
