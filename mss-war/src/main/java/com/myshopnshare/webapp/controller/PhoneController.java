package com.myshopnshare.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import com.myshopnshare.model.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.Phone;
import com.myshopnshare.core.enums.PhoneType;
import com.myshopnshare.core.enums.VisibilityType;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.core.service.PhoneService;

public class PhoneController extends MultiActionController {
	private static transient final Log log = LogFactory
			.getLog(PhoneController.class);

	protected final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
	static {
		simpleDateFormat.applyPattern("MM/dd/yyyy");
	}

	@Autowired
	private PhoneService phoneService;
	@Autowired
	private PersonService personService;

	private Person fetchPerson() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User user = (User) principal;

		return personService.findPersonByUsername(user.getUsername());
	}

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("account/phone/phone");
	}

	public ModelAndView all(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		request.setAttribute("phones", person.getActivePhones());
		return new ModelAndView("account/phone/jsonPhones");
	}

	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			Person person = fetchPerson();
			Phone phone = new Phone();
			phone.setId(StringUtils.trimToNull(request.getParameter("id")) == null ? null : Long.parseLong(request.getParameter("id")));
			phone.setArea(StringUtils.trimToNull(request.getParameter("area")) == null ? phone.getArea() : request.getParameter("area"));
			phone.setCountryCode(StringUtils.trimToNull(request.getParameter("countryCode")) == null ? phone.getCountryCode() : request.getParameter("countryCode"));
			phone.setNumber(StringUtils.trimToNull(request.getParameter("number")) == null ? phone.getNumber() : request.getParameter("number"));
			phone.setType(StringUtils.trimToNull(request.getParameter("phoneType")) == null ? phone.getType() : PhoneType.valueOf(request.getParameter("phoneType")));
			phone.setVisibility(StringUtils.trimToNull(request.getParameter("visibility")) == null ? null : VisibilityType.valueOf(request.getParameter("visibility")));
			phone.setPerson(person);

			phoneService.saveOrUpdate(phone);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("phone", phone);

			// return new ModelAndView(new JSONView(), map);
			return new ModelAndView("account/phone/jsonPhone", map);
		} catch (Exception e) {
			log.equals(e.getMessage());
			return new ModelAndView("account/phone/jsonPhone");
		}
	}

	public ModelAndView edit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			Person person = fetchPerson();
			if(StringUtils.trimToNull(request.getParameter("id")) != null){
				Phone phone = phoneService.getPhoneForPerson(person, Long.parseLong(request.getParameter("id")));
				phone.setArea(StringUtils.trimToNull(request.getParameter("area")) == null ? phone.getArea() : request.getParameter("area"));
				phone.setCountryCode(StringUtils.trimToNull(request.getParameter("countryCode")) == null ? phone.getCountryCode() : request.getParameter("countryCode"));
				phone.setNumber(StringUtils.trimToNull(request.getParameter("number")) == null ? phone.getNumber() : request.getParameter("number"));
				phone.setType(StringUtils.trimToNull(request.getParameter("phoneType")) == null ? phone.getType() : PhoneType.valueOf(request.getParameter("phoneType")));
				phone.setVisibility(StringUtils.trimToNull(request.getParameter("visibility")) == null ? phone.getVisibility() : VisibilityType.valueOf(request.getParameter("visibility")));
				phone.setPerson(person);
	
				phoneService.saveOrUpdate(phone);
				request.setAttribute("phone", phone);
			}

			return new ModelAndView("account/phone/jsonPhone");
		} catch (Exception e) {
			log.equals(e.getMessage());
			return new ModelAndView("account/phone/jsonPhone");
		}
	}
	
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			Person person = fetchPerson();
			String recordId = request.getParameter("recordId");
			Phone phone = phoneService.getPhoneForPerson(person, Long.parseLong(recordId));
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("phone", phone);

			return new ModelAndView("account/phone/jsonPhone", map);
		} catch (Exception e) {
			log.equals(e.getMessage());
			return new ModelAndView("account/phone/jsonPhone");
		}
	}

	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			Person person = fetchPerson();
			String recordId = request.getParameter("recordId");
			Phone phone = phoneService.getPhoneForPerson(person, Long.parseLong(recordId));
			phone.setActive(false);
			phoneService.update(phone);

			Map<String, Object> map = new HashMap<String, Object>();

			map.put("phones", person.getActivePhones());

			return new ModelAndView("account/phone/jsonPhones", map);
		} catch (Exception e) {
			log.equals(e.getMessage());
			return new ModelAndView("account/phone/jsonPhone");
		}
	}

	public void setPhoneService(PhoneService phoneService) {
		this.phoneService = phoneService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

}
