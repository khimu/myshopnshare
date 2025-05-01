# InstitutionRegisterController

```java
package com.myshopnshare.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.providers.dao.SaltSource;
import org.springframework.security.providers.encoding.PasswordEncoder;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.myshopnshare.core.domain.Address;
import com.myshopnshare.core.domain.ConfirmEmail;
import com.myshopnshare.core.domain.EmailAddress;
import com.myshopnshare.core.domain.Institution;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.PersonTag;
import com.myshopnshare.core.enums.AddressType;
import com.myshopnshare.core.enums.Authority;
import com.myshopnshare.core.enums.Gender;
import com.myshopnshare.core.enums.InstitutionType;
import com.myshopnshare.core.enums.Months;
import com.myshopnshare.core.enums.UserType;
import com.myshopnshare.core.enums.VisibilityType;
import com.myshopnshare.core.service.ConfirmEmailService;
import com.myshopnshare.core.service.EmailService;
import com.myshopnshare.core.service.InstitutionService;
import com.myshopnshare.core.service.VendorService;
import com.myshopnshare.service.UserManager;
import com.myshopnshare.utils.EmailUtil;
import com.myshopnshare.webapp.form.InstitutionForm;

public class InstitutionRegisterController extends SimpleFormController {
	private static transient final Log log = LogFactory
			.getLog(InstitutionRegisterController.class);

	private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
	static {
		simpleDateFormat.applyPattern("MM/dd/yyyy");
	}

	private final static CustomDateEditor dateEditor = new CustomDateEditor(
			new SimpleDateFormat("yyyy-MM-dd"), true);

	@Autowired
	private UserManager userService;
	@Autowired
	private VendorService vendorService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private ConfirmEmailService confirmEmailService;

	@Autowired
	private InstitutionService institutionService;

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		InstitutionForm form = (InstitutionForm) command;

		if (!form.getPassword().equals(form.getConfirmPassword()) || !form.validate()) {
			Map controlModel = new HashMap();
			controlModel.put("registerMessage", "Please fill out all fields in the form.");
			return this.showForm(request, response, errors, controlModel);
		}

		/*
		 * User user = form.getUser(); user.setEnabled(1);
		 */
		Person pExist = emailService.findPersonByEmail(form.getEmail().getEmail());
		if (pExist != null) {
			if(pExist.isActive()){
				request.setAttribute("accountInActive", "Your registration failed because your account already exist.");
				return new ModelAndView("login/login");
			}
			request.setAttribute("accountInActive", "Would you like to re-activate your account?");	
			request.setAttribute("email", form.getEmail());
			return new ModelAndView("login/emailExist");
		}
		Institution institution = form.getInstitution();
		institution.setActive(false);
		institution.setInstitutionType(InstitutionType.valueOf(form.getInstitutionType()));
		
		institution.setBirthday(simpleDateFormat.parse(form.getMonth() + "/"
				+ form.getDay() + "/" + form.getYear()));

		// person.setBirthday(simpleDateFormat.parse(form.getBirthday()));

		EmailAddress emailAddress = form.getEmail(); 
		emailAddress.setPrimaryEmail(true);
		emailAddress.setPerson(institution);

		institution.getEmails().add(emailAddress);
		institution.setUserType(UserType.INSTITUTION);

		Address address = form.getAddress();
		address.setPerson(institution);
		address.setType(AddressType.valueOf(form.getAddressType()));
		address.setVisibility(VisibilityType.valueOf(form.getVisibility()));

		institution.getAddresses().add(address);

		String personTags = form.getTags();
		String[] tokenizedTags = StringUtils.split(personTags, ",");
		makePersonTags(institution, institution.getInstitutionName());
		makePersonTags(institution, institution.getSchoolDistrict());
		makePersonTags(institution, institution.getInstitutionType().toString());
		/*
		makePersonTags(institution, address.getCity());
		makePersonTags(institution, address.getCountry());
		makePersonTags(institution, address.getStateOrProvince());
		*/
		for (String str : tokenizedTags) {
			PersonTag tag = new PersonTag();
			tag.setTag(str);
			tag.setPerson(institution);
			institution.getTags().add(tag);
		}	
		
		try {
			institutionService.save(institution);
		} catch (Exception e) {
			request
					.setAttribute("registerMessage",
							"Unable to register at this moment.  Please try again later.");
			return new ModelAndView("login/registerForm");
		}
		/*
		 * Users user = new Users(form.getEmail(), form.getPassword(), true);
		 * 
		 * Object salt = null;
		 * 
		 * if (this.saltSource != null) { salt = this.saltSource.getSalt(user);
		 * } String password =
		 * passwordEncoder.encodePassword(form.getPassword(), salt);
		 */

		@SuppressWarnings("unused")
		String username = request.getParameter("username");

		Random random = new Random(System.currentTimeMillis());
		Integer i = random.nextInt();

		request.setAttribute("email", form.getEmail().getEmail());
		ConfirmEmail confirm = new ConfirmEmail();
		confirm.setEmail(form.getEmail().getEmail());
		confirm.setRandomKey(StringUtils.remove(i.toString(), "-"));
		// confirm.setPassword(password);
		confirm.setPassword(form.getPassword());
		confirm.setEmailAddress(emailAddress);
		confirm.setAuthority(Authority.ROLE_MERCHANT);
		confirmEmailService.save(confirm);

		EmailUtil.INSTANCE
				.sendMail(
						"Confirm Sign Up",
						"Please go to http://www.myshopnshare.com/confirmEmail.do?confirm="+confirm.getRandomKey()+" to confirm your account using confirmation id : "
								+ confirm.getRandomKey(), "sleekswap@gmail.com", form
								.getEmail().getEmail());

		return new ModelAndView(getSuccessView());

	}

	private void makePersonTags(Person person, String tag){
		PersonTag personTag = new PersonTag(person, tag);
		person.getTags().add(personTag);
	}
	
	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map beans = new HashMap();

		beans.put("months", Months.values());

		Gender[] genders = Gender.values();
		beans.put("genders", genders);
		beans.put("institutionTypes", InstitutionType.values());
		beans.put("addressTypes", AddressType.values());
		return beans;
	}

	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Date.class, this.dateEditor);
	}

}
```
