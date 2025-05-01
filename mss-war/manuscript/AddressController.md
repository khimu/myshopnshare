# AddressController

```java
package com.myshopnshare.webapp.controller;

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

import com.myshopnshare.core.domain.Address;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.AddressType;
import com.myshopnshare.core.enums.VisibilityType;
import com.myshopnshare.core.service.AddressService;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.model.User;

public class AddressController extends MultiActionController {
	private static transient final Log log = LogFactory
			.getLog(AddressController.class);

	@Autowired
	private PersonService personService;
	
	@Autowired
	private AddressService addressService;

	private Person fetchPerson() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User user = (User) principal;

		return personService.findPersonByUsername(user.getUsername());
	}

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("account/address/address");
	}

	public ModelAndView all(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		request.setAttribute("addresses", addressService.getAddressesForPerson(person));
		return new ModelAndView("account/address/jsonAddresses");
	}
	
	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			Person person = fetchPerson();
			Address address = new Address();
			address.setStreet1(request.getParameter("street1"));
			address.setStreet2(request.getParameter("street2"));
			address.setCity(request.getParameter("city"));
			address.setStateOrProvince(request.getParameter("stateOrProvince"));
			address.setPostalCode(request.getParameter("postalCode"));
			address.setPrimaryAddress(request.getParameter("primaryAddress") == null ? false : Boolean.parseBoolean(request.getParameter("primaryAddress")));
			address.setType(AddressType.valueOf(request.getParameter("addressType")));
			address.setUnitNumber(request.getParameter("unitNumber"));
			address.setVisibility(request.getParameter("visibility") == null ? VisibilityType.PRIVATE : VisibilityType.valueOf(request.getParameter("visibility")));
			address.setCountry(request.getParameter("country"));
			address.setPerson(person);
			
			addressService.save(address);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("address", address);
			map.put("person", person);

			// return new ModelAndView(new JSONView(), map);
			return new ModelAndView("account/address/jsonAddress", map);
		} catch (Exception e) {
			log.equals(e.getMessage());
			return new ModelAndView("account/address/jsonAddress");
		}
	}
	
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		try {
			Address address = addressService.getAddressForPerson(person, Long.parseLong(request.getParameter("recordId")));

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("address", address);

			// return new ModelAndView(new JSONView(), map);
			return new ModelAndView("account/address/jsonAddress", map);
		} catch (Exception e) {
			log.equals(e.getMessage());
			return new ModelAndView("account/address/jsonAddress");
		}
	}

	public ModelAndView edit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			Person person = fetchPerson();
			Address address = addressService.getAddressForPerson(person, Long.parseLong(request.getParameter("id")));
			address.setStreet1(request.getParameter("street1") == null ? address.getStreet1() : request.getParameter("street1"));
			address.setStreet2(request.getParameter("street2") == null ? address.getStreet2() : request.getParameter("street2"));
			address.setCity(request.getParameter("city") == null ? address.getCity() : request.getParameter("city"));
			address.setStateOrProvince(request.getParameter("stateOrProvince") == null ? address.getStateOrProvince() : request.getParameter("stateOrProvince"));
			address.setPostalCode(request.getParameter("postalCode") == null ? address.getPostalCode() : request.getParameter("postalCode"));
			address.setPrimaryAddress(request.getParameter("primaryAddress") == null ? false : Boolean.parseBoolean(request.getParameter("primaryAddress")));
			address.setType(request.getParameter("addressType") == null ? address.getType() : AddressType.valueOf(request.getParameter("addressType")));
			address.setUnitNumber(request.getParameter("unitNumber") == null ? address.getUnitNumber() : request.getParameter("unitNumber"));			
			address.setCountry(request.getParameter("country") == null ? address.getCountry() : request.getParameter("country"));
			address.setVisibility(request.getParameter("visibility") == null ? address.getVisibility() : VisibilityType.valueOf(request.getParameter("visibility")));
			address.setPerson(person);
			
			addressService.update(address);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("address", address);

			// return new ModelAndView(new JSONView(), map);
			return new ModelAndView("account/address/jsonAddress", map);
		} catch (Exception e) {
			log.equals(e.getMessage());
			return new ModelAndView("account/address/jsonAddress");
		}
	}

	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			Person person = fetchPerson();
			String recordId = request.getParameter("recordId");
			Address address = addressService.getAddressForPerson(person, Long.parseLong(recordId));
			address.setActive(false);
			addressService.update(address);
			
			Map<String, Object> map = new HashMap<String, Object>();

			
			//map.put("addresses", person.getActiveAddress());

			return new ModelAndView("account/address/jsonAddress", map);
		} catch (Exception e) {
			log.equals(e.getMessage());
			return new ModelAndView("account/address/jsonAddress");
		}
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

}
```
