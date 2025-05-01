# MerchantRegisterController

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
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.PersonTag;
import com.myshopnshare.core.domain.Vendor;
import com.myshopnshare.core.enums.AddressType;
import com.myshopnshare.core.enums.Authority;
import com.myshopnshare.core.enums.Gender;
import com.myshopnshare.core.enums.Months;
import com.myshopnshare.core.enums.UserType;
import com.myshopnshare.core.service.ConfirmEmailService;
import com.myshopnshare.core.service.EmailService;
import com.myshopnshare.core.service.VendorService;
import com.myshopnshare.service.UserManager;
import com.myshopnshare.utils.EmailUtil;
import com.myshopnshare.webapp.form.RegisterForm;

public class MerchantRegisterController extends SimpleFormController {
	private static transient final Log log = LogFactory
			.getLog(MerchantRegisterController.class);

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

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		
		System.out.println("hello merchant register");
		

		RegisterForm form = (RegisterForm) command;

		if (!form.getPassword().equals(form.getConfirmPassword()) || !form.validate()) {
			Map controlModel = new HashMap();
			controlModel.put("registerMessage", "Please fill out all fields in the form.");
			return this.showForm(request, response, errors, controlModel);
		}

		/*
		 * User user = form.getUser(); user.setEnabled(1);
		 */
		Person pExist = emailService.findPersonByEmail(form.getEmail());
		if (pExist != null) {
			if(pExist.isActive()){
				request.setAttribute("accountInActive", "Your registration failed because your account already exist.");
				return new ModelAndView("login/login");
			}
			request.setAttribute("accountInActive", "Would you like to re-activate your account?");	
			request.setAttribute("email", form.getEmail());
			return new ModelAndView("login/emailExist");
		}
		Vendor person = new Vendor();
		person.setActive(false);
		person.setFirstName(form.getFirstname());
		person.setLastName(form.getLastname());
		person.setCompanyName(form.getCompanyName());
		person.setDescription(form.getCompanyDescription());
		
		String personTags = form.getTags();
		String[] tokenizedTags = StringUtils.split(personTags, ",");
		makePersonTags(person, person.getCompanyName());
		for (String str : tokenizedTags) {
			PersonTag tag = new PersonTag();
			tag.setTag(str);
			tag.setPerson(person);
			person.getTags().add(tag);
		}		

		person.setBirthday(simpleDateFormat.parse(form.getMonth() + "/" +
		form.getDay() + "/" + form.getYear()));
		 
		// person.setBirthday(simpleDateFormat.parse(form.getBirthday()));

		EmailAddress emailAddress = new EmailAddress();
		emailAddress.setEmail(form.getEmail());
		emailAddress.setPrimaryEmail(true);
		emailAddress.setPerson(person);

		person.getEmails().add(emailAddress);
		person.setGender(Gender.valueOf(form.getGender()));
		person.setUserType(UserType.MERCHANT);
		
		//person.setSubscribed(true);
		
		Address address = new Address();
		address.setPerson(person);
		address.setCountry(form.getCountry());
		address.setStateOrProvince(form.getStateOrProvince());
		address.setPostalCode(form.getPostalCode());
		address.setStreet1(form.getStreet1());
		address.setStreet2(form.getStreet2());
		address.setCity(form.getCity());
		address.setUnitNumber(form.getUnitNumber());
		
		person.getAddresses().add(address);

		try {
			vendorService.save(person);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("registerMessage",
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

		request.setAttribute("email", form.getEmail());
		ConfirmEmail confirm = new ConfirmEmail();
		confirm.setEmail(form.getEmail());
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
								+ confirm.getRandomKey(),
						"sleekswap@gmail.com", form.getEmail());

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

		beans.put("addressTypes", AddressType.values());
		return beans;		
	}

	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Date.class, this.dateEditor);
	}

}
```
