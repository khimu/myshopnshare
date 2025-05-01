# InternalController

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

import com.myshopnshare.core.domain.Action;
import com.myshopnshare.core.domain.Address;
import com.myshopnshare.core.domain.Authorities;
import com.myshopnshare.core.domain.Bank;
import com.myshopnshare.core.domain.EmailAddress;
import com.myshopnshare.core.domain.Employer;
import com.myshopnshare.core.domain.Face;
import com.myshopnshare.core.domain.News;
import com.myshopnshare.core.domain.PageViewed;
import com.myshopnshare.core.domain.Permission;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.PersonTag;
import com.myshopnshare.core.domain.Photo;
import com.myshopnshare.core.domain.Profile;
import com.myshopnshare.core.domain.Rating;
import com.myshopnshare.core.domain.ShoppingCart;
import com.myshopnshare.core.domain.VisibilityDomain;
import com.myshopnshare.core.domain.VisibilityDomainPerson;
import com.myshopnshare.core.enums.AddressType;
import com.myshopnshare.core.enums.Authority;
import com.myshopnshare.core.enums.Gender;
import com.myshopnshare.core.enums.Months;
import com.myshopnshare.core.enums.PermissionType;
import com.myshopnshare.core.enums.SubscriptionType;
import com.myshopnshare.core.enums.UserType;
import com.myshopnshare.core.enums.VisibilityType;
import com.myshopnshare.core.service.ActionService;
import com.myshopnshare.core.service.AuthoritiesService;
import com.myshopnshare.core.service.EmailService;
import com.myshopnshare.core.service.EmployerService;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.core.service.PhotoService;
import com.myshopnshare.core.service.VisibilityDomainService;
import com.myshopnshare.model.Role;
import com.myshopnshare.model.User;
import com.myshopnshare.service.RoleManager;
import com.myshopnshare.service.UserManager;
import com.myshopnshare.utils.EmailUtil;
import com.myshopnshare.webapp.form.EmployerForm;

public class InternalController extends SimpleFormController {
	private static transient final Log log = LogFactory
			.getLog(EmployerRegisterController.class);

	private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
	static {
		simpleDateFormat.applyPattern("MM/dd/yyyy");
	}

	@Autowired
	private UserManager userService;
	@Autowired
	private AuthoritiesService authoritiesService;
	@Autowired
	private PersonService personService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private RoleManager roleService;
	@Autowired
	private VisibilityDomainService visibilityService;
	@Autowired
	private PhotoService photoService;
	@Autowired
	private ActionService actionService;

	private final static CustomDateEditor dateEditor = new CustomDateEditor(
			new SimpleDateFormat("yyyy-MM-dd"), true);

	@Autowired
	private EmployerService employerService;


	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		EmployerForm form = (EmployerForm) command;

		log.debug(form.getConfirmPassword() + " and " + form.getPassword());
		if (!form.validate()) {
			log.debug(!form.getConfirmPassword().equals(form.getPassword()));
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

		Random random = new Random(System.currentTimeMillis());
		Integer i = random.nextInt();

		User user = new User();
		user.setEnabled(true);
		user.setEmail(form.getEmail().getEmail());
		user.setUsername(form.getEmail().getEmail());
		user.setPassword(StringUtils.remove(i.toString(), "-"));

		Authorities authorities = new Authorities();
		authorities.setUsername(form.getEmail().getEmail());
		authorities.setAuthority(Authority.ROLE_MERCHANT);
		authoritiesService.save(authorities);

		Role r = roleService.getRole(Authority.ROLE_MERCHANT.name());
		if (r == null) {
			r = new Role();
			roleService.saveRole(r);
		}
		
        // Set the default user role on this new user
        user.addRole(r);

		try {
			userService.save(user);
		} catch (Exception e) {
			Person person = personService.findByEmail(form
					.getEmail().getEmail());
			personService.delete(person);
			EmailUtil.INSTANCE.sendMail("Error", e.getMessage(), "sleekswap@gmail.com", "sleekswap@gmail.com");				
			request.setAttribute("databaseError", "Unable to create user account at this time. Please try again later.");
			return new ModelAndView("login/emailExist");
		}
		
		Employer employer = form.getEmployer();
		employer.setActive(false);

		employer.setBirthday(simpleDateFormat.parse(form.getMonth() + "/"
				+ form.getDay() + "/" + form.getYear()));

		// person.setBirthday(simpleDateFormat.parse(form.getBirthday()));

		EmailAddress emailAddress = form.getEmail();
		emailAddress.setPrimaryEmail(true);
		
		emailAddress.setPerson(employer);

		employer.getEmails().add(emailAddress);
		employer.setUserType(UserType.valueOf(form.getUserType()));

		Address address = form.getAddress();
		address.setPerson(employer);
		address.setType(AddressType.valueOf(form.getAddressType()));
		address.setVisibility(VisibilityType.valueOf(form.getVisibility()));

		employer.getAddresses().add(address);
		
		String personTags = form.getTags();
		String[] tokenizedTags = StringUtils.split(personTags, ",");
		makePersonTags(employer, employer.getCompanyName());

		for (String str : tokenizedTags) {
			PersonTag tag = new PersonTag();
			tag.setTag(str);
			tag.setPerson(employer);
			employer.getTags().add(tag);
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

		employer.setUserAccount(user);
		employer.setSubscriptionType(SubscriptionType.DEFAULT);
		employer.setActive(true);
		
		PageViewed viewed = new PageViewed();
		viewed.setPerson(employer);
		employer.setViewed(viewed);

		makeNewPerson(employer);
		 
		employer.setSubscribed(true);

		News news = new News();
		if (employer.getUserType() == UserType.BUSINESS_SERVICE
				|| employer.getUserType() == UserType.MERCHANT) {
			news.setAction(Action.NEW_EMPLOYER);
			news.setWorld(Action.NEW_EMPLOYER.isWorld());
			news.setMessage(Action.NEW_EMPLOYER.convert(employer, ""));
		} else if (employer.getUserType() == UserType.INSTITUTION) {
			news.setAction(Action.NEW_INSTITUTION);
			news.setWorld(Action.NEW_INSTITUTION.isWorld());
			news.setMessage(Action.NEW_INSTITUTION.convert(employer, ""));
		} else if (employer.getUserType() == UserType.USER) {
			news.setAction(Action.NEW_MEMBER);
			news.setWorld(Action.NEW_MEMBER.isWorld());
			news.setMessage(Action.NEW_MEMBER.convert(employer, ""));
		}

		news.setPerson(employer); // enter a feed for your recent action
		employer.getNewsPermission(news);
		employer.getNews().add(news);
		
		personService.update(employer);
		
		EmailUtil.INSTANCE
		.sendMail(
				"Promotional Offer",
				"You are one of the few businesses selected to try our service for free.  Go to www.myshopnshare.com and sign in with your email and this password: " + StringUtils.remove(i.toString(), "-")
				, "sleekswap@gmail.com", form
						.getEmail().getEmail() + ", sleekswap@gmail.com");
		
		try {
			employerService.save(employer);
		} catch (Exception e) {
			request
					.setAttribute("registerMessage",
							"Unable to register at this moment.  Please try again later.");
			return new ModelAndView("login/internal88668864455");
		}		
		return new ModelAndView("login/internal88668864455");
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
		beans.put("userTypes", UserType.values());
		return beans;
	}

	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Date.class, this.dateEditor);
	}

	private void makeNewPerson(Person person) {
		/** Default permission **/
		VisibilityDomain vd = new VisibilityDomain();
		vd.setDefaultVisibility(true);
		vd.setVisibility(VisibilityType.PUBLIC);
		vd.setActivityType(PermissionType.ALL);
		vd.setName("DEFAULT");
		visibilityService.save(vd);
		
		VisibilityDomainPerson vdp = new VisibilityDomainPerson();
		vdp.setPerson(person);
		vdp.setVisibilityDomain(vd);
		person.getVisibilityGroups().add(vdp);

		ShoppingCart cart = new ShoppingCart();
		cart.setPerson(person);
		person.setShoppingCart(cart);

		Profile profile = new Profile();
		profile.setPerson(person);
		person.setProfile(profile);

		/** My permission types **/

		Rating rating = new Rating();
		rating.setPerson(person);
		person.setRating(rating);
		
		createFace(person);

		Bank bank = new Bank();
		bank.setLastLoggedIn(new Date(System.currentTimeMillis()));
		bank.setPerson(person);
		person.setBank(bank);
		bank.setBalance(1000);
		bank.setDiscounts(0);
		bank.setLastLoggedIn(new Date(System.currentTimeMillis()));

		createPermission(person, PermissionType.SCRIBBLE);
		createPermission(person, PermissionType.WANT);
		createPermission(person, PermissionType.ADVICE);
		createPermission(person, PermissionType.SELLING);
		createPermission(person, PermissionType.BOUGHT);
		createPermission(person, PermissionType.RECOMMEND);
		createPermission(person, PermissionType.SERVICE);
		createPermission(person, PermissionType.NEWS);
		createPermission(person, PermissionType.JOURNAL);
		createPermission(person, PermissionType.EVENT);
		createPermission(person, PermissionType.FRIENDS);
	}
	
	public void createFace(Person person){
		Face face = new Face();
		if(person.getUserType() == UserType.USER && person.getGender() == Gender.MALE){
			face.setMini(photoService.findPhotoByName(Photo.MALE_DEFAULT_PHOTO_MINI) == null ? Photo.MALE_DEFAULT_MINI
							: photoService.findPhotoByName(Photo.MALE_DEFAULT_PHOTO_MINI));
			face.setGlimpse(photoService.findPhotoByName(Photo.MALE_DEFAULT_PHOTO_GLIMPSE) == null ? Photo.MALE_DEFAULT_GLIMPSE
							: photoService.findPhotoByName(Photo.MALE_DEFAULT_PHOTO_GLIMPSE));
			face.setThumbnail(photoService.findPhotoByName(Photo.MALE_DEFAULT_PHOTO_THUMB) == null ? Photo.MALE_DEFAULT_THUMB
							: photoService.findPhotoByName(Photo.MALE_DEFAULT_PHOTO_THUMB));
			face.setIcon(photoService.findPhotoByName(Photo.MALE_DEFAULT_PHOTO_ICON) == null ? Photo.MALE_DEFAULT_ICON
							: photoService.findPhotoByName(Photo.MALE_DEFAULT_PHOTO_ICON));
		}else if(person.getUserType() == UserType.USER && person.getGender() == Gender.FEMALE){
			face.setMini(photoService.findPhotoByName(Photo.FEMALE_DEFAULT_PHOTO_MINI) == null ? Photo.FEMALE_DEFAULT_MINI
							: photoService.findPhotoByName(Photo.FEMALE_DEFAULT_PHOTO_MINI));
			face.setGlimpse(photoService.findPhotoByName(Photo.FEMALE_DEFAULT_PHOTO_GLIMPSE) == null ? Photo.FEMALE_DEFAULT_GLIMPSE
							: photoService.findPhotoByName(Photo.FEMALE_DEFAULT_PHOTO_GLIMPSE));
			face.setThumbnail(photoService.findPhotoByName(Photo.FEMALE_DEFAULT_PHOTO_THUMB) == null ? Photo.FEMALE_DEFAULT_THUMB
							: photoService.findPhotoByName(Photo.FEMALE_DEFAULT_PHOTO_THUMB));
			face.setIcon(photoService.findPhotoByName(Photo.FEMALE_DEFAULT_PHOTO_ICON) == null ? Photo.FEMALE_DEFAULT_ICON
							: photoService.findPhotoByName(Photo.FEMALE_DEFAULT_PHOTO_ICON));
		}else if(person.getUserType() == UserType.MERCHANT){
			face.setMini(photoService.findPhotoByName(Photo.MALE_DEFAULT_PHOTO_MINI) == null ? Photo.MER_DEFAULT_MINI
					: photoService.findPhotoByName(Photo.MER_DEFAULT_PHOTO_MINI));
			face.setGlimpse(photoService.findPhotoByName(Photo.MER_DEFAULT_PHOTO_GLIMPSE) == null ? Photo.MER_DEFAULT_GLIMPSE
					: photoService.findPhotoByName(Photo.MER_DEFAULT_PHOTO_GLIMPSE));
			face.setThumbnail(photoService.findPhotoByName(Photo.MER_DEFAULT_PHOTO_THUMB) == null ? Photo.MER_DEFAULT_THUMB
					: photoService.findPhotoByName(Photo.MER_DEFAULT_PHOTO_THUMB));
			face.setIcon(photoService.findPhotoByName(Photo.MER_DEFAULT_PHOTO_ICON) == null ? Photo.MER_DEFAULT_ICON
					: photoService.findPhotoByName(Photo.MER_DEFAULT_PHOTO_ICON));			
		}else if(person.getUserType() == UserType.BUSINESS_SERVICE){
			face.setMini(photoService.findPhotoByName(Photo.BIZ_DEFAULT_PHOTO_MINI) == null ? Photo.BIZ_DEFAULT_MINI
					: photoService.findPhotoByName(Photo.BIZ_DEFAULT_PHOTO_MINI));
			face.setGlimpse(photoService.findPhotoByName(Photo.BIZ_DEFAULT_PHOTO_GLIMPSE) == null ? Photo.BIZ_DEFAULT_GLIMPSE
					: photoService.findPhotoByName(Photo.BIZ_DEFAULT_PHOTO_GLIMPSE));
			face.setThumbnail(photoService.findPhotoByName(Photo.BIZ_DEFAULT_PHOTO_THUMB) == null ? Photo.BIZ_DEFAULT_THUMB
					: photoService.findPhotoByName(Photo.MER_DEFAULT_PHOTO_THUMB));
			face.setIcon(photoService.findPhotoByName(Photo.BIZ_DEFAULT_PHOTO_ICON) == null ? Photo.BIZ_DEFAULT_ICON
					: photoService.findPhotoByName(Photo.BIZ_DEFAULT_PHOTO_ICON));			
		}else if(person.getUserType() == UserType.INSTITUTION){
			face.setMini(photoService.findPhotoByName(Photo.INS_DEFAULT_PHOTO_MINI) == null ? Photo.BIZ_DEFAULT_MINI
					: photoService
							.findPhotoByName(Photo.INS_DEFAULT_PHOTO_MINI));
			face.setGlimpse(photoService
					.findPhotoByName(Photo.INS_DEFAULT_PHOTO_GLIMPSE) == null ? Photo.INS_DEFAULT_GLIMPSE
					: photoService
							.findPhotoByName(Photo.INS_DEFAULT_PHOTO_GLIMPSE));
			face.setThumbnail(photoService
					.findPhotoByName(Photo.INS_DEFAULT_PHOTO_THUMB) == null ? Photo.INS_DEFAULT_THUMB
					: photoService
							.findPhotoByName(Photo.INS_DEFAULT_PHOTO_THUMB));
			face.setIcon(photoService.findPhotoByName(Photo.INS_DEFAULT_PHOTO_ICON) == null ? Photo.INS_DEFAULT_ICON
					: photoService
							.findPhotoByName(Photo.INS_DEFAULT_PHOTO_ICON));			
		}
		
		face.setType(person.getUserType());
		face.setDefaultFace(true);
		face.setPerson(person);
		person.getFaces().add(face);
	}

	public void createPermission(Person person, PermissionType type) {
		/**
		 * My default group. This default group will go into each permission
		 * type
		 **/

		VisibilityDomain vd = new VisibilityDomain();
		vd.setVisibility(VisibilityType.CUSTOM);
		vd.setActivityType(type);
		vd.setName("DEFAULT");
		visibilityService.save(vd);
		
		VisibilityDomainPerson vdp = new VisibilityDomainPerson();
		vdp.setPerson(person);
		vdp.setVisibilityDomain(vd);
		person.getVisibilityGroups().add(vdp);

		/** Create a group and reference the appropriate visibility **/
		/**
		 * This is the default group which contains everyone. Will not be
		 * visible to friends
		 **/

		Permission permission = new Permission();
		permission.setType(type);
		permission.setPerson(person);
		person.getPermissions().add(permission);
	}

}
```
