# RegisterController

```java
package com.myshopnshare.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.Gender;
import com.myshopnshare.core.enums.Months;
import com.myshopnshare.core.service.ConfirmEmailService;
import com.myshopnshare.core.service.EmailService;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.core.service.RegisterService;
import com.myshopnshare.core.service.VendorService;
import com.myshopnshare.service.UserManager;
import com.myshopnshare.webapp.form.RegisterForm;

public class RegisterController extends SimpleFormController {
	private static transient final Log log = LogFactory
			.getLog(RegisterController.class);



	@Autowired
	private UserManager userService;
	@Autowired
	private PersonService personService;
	@Autowired
	private ConfirmEmailService confirmEmailService;

	@Autowired
	private VendorService vendorService;
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private RegisterService registerService;

	public RegisterController() {
	}

	/*
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		RegisterForm form = (RegisterForm) command;

		if (!form.getPassword().equals(form.getConfirmPassword()) || !form.validate()) {
			Map controlModel = new HashMap();
			controlModel.put("registerMessage", "Please fill out all fields in the form.");
			return this.showForm(request, response, errors, controlModel);
		}

		Person pExist = emailService.findPersonByEmail(form.getEmail());
		if (pExist != null) {
			if(pExist.isActive()){
				request.setAttribute("accountInActive", "Your registration failed because your account already exist.");
				return new ModelAndView("login/login");
			}
			request.setAttribute("email", form.getEmail());
			request.setAttribute("accountInActive", "Would you like to re-activate your account?");	

			return new ModelAndView("login/emailExist");
		}
		try {
			Person person = new Person();
			person.setFirstName(form.getFirstname());
			person.setLastName(form.getLastname());
			person.setActive(false);

			person.setBirthday(simpleDateFormat.parse(form.getMonth() + "/"
					+ form.getDay() + "/" + form.getYear()));

			// person.setBirthday(simpleDateFormat.parse(form.getBirthday()));

			EmailAddress emailAddress = new EmailAddress();
			emailAddress.setEmail(form.getEmail());
			emailAddress.setPrimaryEmail(true);
			emailAddress.setPerson(person);

			person.getEmails().add(emailAddress);
			person.setGender(Gender.valueOf(form.getGender()));
			person.setUserType(UserType.USER);

			
			Address address = new Address();
			address.setPerson(person);
			address.setCountry(form.getCountry());
			address.setStateOrProvince(form.getStateOrProvince());
			
			person.getAddresses().add(address);			
			
			personService.save(person);

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
			confirm.setAuthority(Authority.ROLE_USER);
			confirmEmailService.save(confirm);
			
			try {
				EmailUtil.INSTANCE
						.sendMail(
								"Confirm Sign Up",
								"Please go to http://www.myshopnshare.com/confirmEmail.do?confirm="+confirm.getRandomKey()+" to confirm your account using confirmation id : "
										+ confirm.getRandomKey(),
								"sleekswap@gmail.com", form
										.getEmail());

				return new ModelAndView(getSuccessView());
			} catch (Exception e) {
				confirmEmailService.delete(confirm);
				personService.delete(person);
				request
						.setAttribute("registerMessage",
								"Unable to send email confirmation.");
				referenceData(request);
				return new ModelAndView("login/registerForm");
			}
		} catch (Exception e) {
			request
					.setAttribute("registerMessage",
							"Unable to register at this moment.  Please try again later.");
			referenceData(request);
			return new ModelAndView("login/registerForm");
		}

	}
	*/

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		RegisterForm form = (RegisterForm) command;

		if (!form.getPassword().equals(form.getConfirmPassword()) || !form.validate()) {
			Map controlModel = new HashMap();
			controlModel.put("registerMessage", "Please fill out all fields in the form.");
			return this.showForm(request, response, errors, controlModel);
		}

		Person pExist = emailService.findPersonByEmail(form.getEmail());
		if (pExist != null) {
			if(pExist.isActive()){
				request.setAttribute("accountInActive", "Your registration failed because your account already exist.");
				return new ModelAndView("login/login");
			}
			request.setAttribute("email", form.getEmail());
			request.setAttribute("accountInActive", "Would you like to re-activate your account?");	

			return new ModelAndView("login/emailExist");
		}
		
		String username = request.getParameter("username");
		String message = registerService.register(form, username);
		
		if(StringUtils.isBlank(message)){
			return new ModelAndView(getSuccessView());
		}
		
		request.setAttribute("registerMessage", message);
		referenceData(request);
		return new ModelAndView("login/registerForm");
	}

	
	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map beans = new HashMap();
		beans.put("months", Months.values());
		beans.put("genders", Gender.values());
		return beans;
	}
	/*
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
    	String symbol = ServletRequestUtils.getStringParameter(request, "genders");
    	String orderType = ServletRequestUtils.getStringParameter(request, "ordertype");
    	CreateOrder order = new CreateOrder();
    	if( symbol != null && !symbol.trim().equals("")){
    		order.setSymbol(symbol);
    	}
    	if( orderType != null && !orderType.trim().equals("") ){
    		order.setOrderType("Buy");
    	}
    	return order;
    }
    */
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		simpleDateFormat.applyPattern("MM/dd/yyyy");

		CustomDateEditor dateEditor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);		
		binder.registerCustomEditor(Date.class, dateEditor);
	}


}
```
