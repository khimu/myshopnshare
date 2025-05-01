# RegisterServiceImpl

```java
package com.myshopnshare.core.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.providers.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.AuthoritiesDAO;
import com.myshopnshare.core.dao.ConfirmEmailDAO;
import com.myshopnshare.core.dao.EmailDAO;
import com.myshopnshare.core.dao.PersonDAO;
import com.myshopnshare.core.dao.PhotoDAO;
import com.myshopnshare.core.dao.VisibilityDomainDAO;
import com.myshopnshare.core.domain.Action;
import com.myshopnshare.core.domain.Address;
import com.myshopnshare.core.domain.Authorities;
import com.myshopnshare.core.domain.Bank;
import com.myshopnshare.core.domain.ConfirmEmail;
import com.myshopnshare.core.domain.EmailAddress;
import com.myshopnshare.core.domain.Face;
import com.myshopnshare.core.domain.News;
import com.myshopnshare.core.domain.PageViewed;
import com.myshopnshare.core.domain.Permission;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.Photo;
import com.myshopnshare.core.domain.Profile;
import com.myshopnshare.core.domain.Rating;
import com.myshopnshare.core.domain.ShoppingCart;
import com.myshopnshare.core.domain.VisibilityDomain;
import com.myshopnshare.core.domain.VisibilityDomainPerson;
import com.myshopnshare.core.enums.Authority;
import com.myshopnshare.core.enums.Gender;
import com.myshopnshare.core.enums.PermissionType;
import com.myshopnshare.core.enums.SubscriptionType;
import com.myshopnshare.core.enums.UserType;
import com.myshopnshare.core.enums.VisibilityType;
import com.myshopnshare.dao.RoleDao;
import com.myshopnshare.dao.UserDao;
import com.myshopnshare.model.Role;
import com.myshopnshare.model.User;
import com.myshopnshare.utils.EmailUtil;
import com.myshopnshare.webapp.form.RegisterForm;

@Service("registerService")
@Transactional
public class RegisterServiceImpl implements RegisterService {

	private static final Log log = LogFactory.getLog(RegisterServiceImpl.class);

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private AuthoritiesDAO authoritiesDao;
	// private AuthoritiesService authoritiesService;

	@Autowired
	private EmailDAO emailDao;
	// private EmailService emailService;

	@Autowired
	private RoleDao roleDao;
	// private RoleManager roleService;

	@Autowired
	private VisibilityDomainDAO visibilityDomainDao;
	// private VisibilityDomainService visibilityService;

	@Autowired
	private PhotoDAO photoDao;
	// private PhotoService photoService;

	@Autowired
	private ConfirmEmailDAO confirmEmailDao;
	// private ConfirmEmailService confirmEmailService;

	@Autowired
	private PersonDAO personDao;
	//private PersonService personService;

	public String register(RegisterForm form, String username) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		simpleDateFormat.applyPattern("MM/dd/yyyy");

		/*
		 * User user = form.getUser(); user.setEnabled(1);
		 * 
		 * Need to create the 1 query for both active and inactive. being lazy
		 */
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

			personDao.save(person);

			Random random = new Random(System.currentTimeMillis());
			Integer i = random.nextInt();

			ConfirmEmail confirm = new ConfirmEmail();
			confirm.setEmail(form.getEmail());
			confirm.setRandomKey(StringUtils.remove(i.toString(), "-"));
			// confirm.setPassword(password);
			confirm.setPassword(form.getPassword());
			confirm.setEmailAddress(emailAddress);
			confirm.setAuthority(Authority.ROLE_USER);
			confirmEmailDao.save(confirm);
			// confirmEmailService.save(confirm);

			try {
				EmailUtil.INSTANCE
						.sendMail(
								"Confirm Sign Up",
								"Please go to http://www.myshopnshare.com/confirmEmail.do?confirm="
										+ confirm.getRandomKey()
										+ " to confirm your account using confirmation id : "
										+ confirm.getRandomKey(),
								"sleekswap@gmail.com", form.getEmail());
				return null;
			} catch (Exception e) {
				return "Unable to send email confirmation.";
			}
		} catch (Exception e) {
			return "Unable to register at this moment.  Please try again later.";
		}
	}

	public User completeRegister(ConfirmEmail confirmEmail) {
		User user = new User();
		user.setEmail(confirmEmail.getEmail());
		user.setVersion(0);
		user.setEnabled(true);
		user.setUsername(confirmEmail.getEmail());
		user.setPassword(passwordEncoder.encodePassword(confirmEmail.getPassword(), null));

		Authorities authorities = new Authorities();
		authorities.setUsername(confirmEmail.getEmail());
		authorities.setAuthority(Authority.ROLE_MERCHANT);
		authoritiesDao.save(authorities);
		// authoritiesService.save(authorities);

		Role r = roleDao.getRoleByName(confirmEmail.getAuthority().name());
		// Role r = roleService.getRole(confirmEmail.getAuthority().name());

		user.setRoles(new HashSet<Role>());
		user.getRoles().add(r);

		//userDao.save(user);

		Person person = emailDao.get(confirmEmail.getEmailAddress().getId())
				.getPerson();
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

		personDao.update(person);
		
		return user;
	}

	private void makeNewPerson(Person person) {
		/** Default permission **/
		VisibilityDomain vd = new VisibilityDomain();
		vd.setDefaultVisibility(true);
		vd.setVisibility(VisibilityType.PUBLIC);
		vd.setActivityType(PermissionType.ALL);
		vd.setName("DEFAULT");
		visibilityDomainDao.save(vd);

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

	public void createFace(Person person) {
		Face face = new Face();
		if (person.getUserType() == UserType.USER
				&& person.getGender() == Gender.MALE) {
			face.setMini(photoDao
					.findPhotoByName(Photo.MALE_DEFAULT_PHOTO_MINI) == null ? Photo.MALE_DEFAULT_MINI
					: photoDao.findPhotoByName(Photo.MALE_DEFAULT_PHOTO_MINI));
			face.setGlimpse(photoDao
					.findPhotoByName(Photo.MALE_DEFAULT_PHOTO_GLIMPSE) == null ? Photo.MALE_DEFAULT_GLIMPSE
					: photoDao
							.findPhotoByName(Photo.MALE_DEFAULT_PHOTO_GLIMPSE));
			face.setThumbnail(photoDao
					.findPhotoByName(Photo.MALE_DEFAULT_PHOTO_THUMB) == null ? Photo.MALE_DEFAULT_THUMB
					: photoDao.findPhotoByName(Photo.MALE_DEFAULT_PHOTO_THUMB));
			face.setIcon(photoDao
					.findPhotoByName(Photo.MALE_DEFAULT_PHOTO_ICON) == null ? Photo.MALE_DEFAULT_ICON
					: photoDao.findPhotoByName(Photo.MALE_DEFAULT_PHOTO_ICON));
		} else if (person.getUserType() == UserType.USER
				&& person.getGender() == Gender.FEMALE) {
			face.setMini(photoDao
					.findPhotoByName(Photo.FEMALE_DEFAULT_PHOTO_MINI) == null ? Photo.FEMALE_DEFAULT_MINI
					: photoDao.findPhotoByName(Photo.FEMALE_DEFAULT_PHOTO_MINI));
			face.setGlimpse(photoDao
					.findPhotoByName(Photo.FEMALE_DEFAULT_PHOTO_GLIMPSE) == null ? Photo.FEMALE_DEFAULT_GLIMPSE
					: photoDao
							.findPhotoByName(Photo.FEMALE_DEFAULT_PHOTO_GLIMPSE));
			face.setThumbnail(photoDao
					.findPhotoByName(Photo.FEMALE_DEFAULT_PHOTO_THUMB) == null ? Photo.FEMALE_DEFAULT_THUMB
					: photoDao
							.findPhotoByName(Photo.FEMALE_DEFAULT_PHOTO_THUMB));
			face.setIcon(photoDao
					.findPhotoByName(Photo.FEMALE_DEFAULT_PHOTO_ICON) == null ? Photo.FEMALE_DEFAULT_ICON
					: photoDao.findPhotoByName(Photo.FEMALE_DEFAULT_PHOTO_ICON));
		} else if (person.getUserType() == UserType.MERCHANT) {
			face.setMini(photoDao
					.findPhotoByName(Photo.MALE_DEFAULT_PHOTO_MINI) == null ? Photo.MER_DEFAULT_MINI
					: photoDao.findPhotoByName(Photo.MER_DEFAULT_PHOTO_MINI));
			face.setGlimpse(photoDao
					.findPhotoByName(Photo.MER_DEFAULT_PHOTO_GLIMPSE) == null ? Photo.MER_DEFAULT_GLIMPSE
					: photoDao.findPhotoByName(Photo.MER_DEFAULT_PHOTO_GLIMPSE));
			face.setThumbnail(photoDao
					.findPhotoByName(Photo.MER_DEFAULT_PHOTO_THUMB) == null ? Photo.MER_DEFAULT_THUMB
					: photoDao.findPhotoByName(Photo.MER_DEFAULT_PHOTO_THUMB));
			face.setIcon(photoDao.findPhotoByName(Photo.MER_DEFAULT_PHOTO_ICON) == null ? Photo.MER_DEFAULT_ICON
					: photoDao.findPhotoByName(Photo.MER_DEFAULT_PHOTO_ICON));
		} else if (person.getUserType() == UserType.BUSINESS_SERVICE) {
			face.setMini(photoDao.findPhotoByName(Photo.BIZ_DEFAULT_PHOTO_MINI) == null ? Photo.BIZ_DEFAULT_MINI
					: photoDao.findPhotoByName(Photo.BIZ_DEFAULT_PHOTO_MINI));
			face.setGlimpse(photoDao
					.findPhotoByName(Photo.BIZ_DEFAULT_PHOTO_GLIMPSE) == null ? Photo.BIZ_DEFAULT_GLIMPSE
					: photoDao.findPhotoByName(Photo.BIZ_DEFAULT_PHOTO_GLIMPSE));
			face.setThumbnail(photoDao
					.findPhotoByName(Photo.BIZ_DEFAULT_PHOTO_THUMB) == null ? Photo.BIZ_DEFAULT_THUMB
					: photoDao.findPhotoByName(Photo.MER_DEFAULT_PHOTO_THUMB));
			face.setIcon(photoDao.findPhotoByName(Photo.BIZ_DEFAULT_PHOTO_ICON) == null ? Photo.BIZ_DEFAULT_ICON
					: photoDao.findPhotoByName(Photo.BIZ_DEFAULT_PHOTO_ICON));
		} else if (person.getUserType() == UserType.INSTITUTION) {
			face.setMini(photoDao.findPhotoByName(Photo.INS_DEFAULT_PHOTO_MINI) == null ? Photo.BIZ_DEFAULT_MINI
					: photoDao.findPhotoByName(Photo.INS_DEFAULT_PHOTO_MINI));
			face.setGlimpse(photoDao
					.findPhotoByName(Photo.INS_DEFAULT_PHOTO_GLIMPSE) == null ? Photo.INS_DEFAULT_GLIMPSE
					: photoDao.findPhotoByName(Photo.INS_DEFAULT_PHOTO_GLIMPSE));
			face.setThumbnail(photoDao
					.findPhotoByName(Photo.INS_DEFAULT_PHOTO_THUMB) == null ? Photo.INS_DEFAULT_THUMB
					: photoDao.findPhotoByName(Photo.INS_DEFAULT_PHOTO_THUMB));
			face.setIcon(photoDao.findPhotoByName(Photo.INS_DEFAULT_PHOTO_ICON) == null ? Photo.INS_DEFAULT_ICON
					: photoDao.findPhotoByName(Photo.INS_DEFAULT_PHOTO_ICON));
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
		visibilityDomainDao.save(vd);

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
