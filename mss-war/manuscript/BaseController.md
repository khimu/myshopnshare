# BaseController

```java
package com.myshopnshare.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import com.myshopnshare.core.domain.Bank;
import com.myshopnshare.core.domain.Headline;
import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.AddressType;
import com.myshopnshare.core.enums.CreditCardType;
import com.myshopnshare.core.enums.EmailAddressType;
import com.myshopnshare.core.enums.PhoneType;
import com.myshopnshare.core.service.AddressService;
import com.myshopnshare.core.service.BankService;
import com.myshopnshare.core.service.CartItemService;
import com.myshopnshare.core.service.CreditCardService;
import com.myshopnshare.core.service.EmailService;
import com.myshopnshare.core.service.FaceService;
import com.myshopnshare.core.service.FriendService;
import com.myshopnshare.core.service.HeadlineService;
import com.myshopnshare.core.service.ItemCategoryService;
import com.myshopnshare.core.service.ItemService;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.core.service.PhoneService;
import com.myshopnshare.core.service.RequestFriendService;
import com.myshopnshare.model.User;

public abstract class BaseController extends AbstractMultiActionController {
	
	@Autowired
	protected ItemCategoryService itemCategoryService;
	
	@Autowired
	protected PersonService personService;
	
	@Autowired
	protected BankService bankService;
	
	@Autowired
	protected RequestFriendService requestFriendService;
	
	@Autowired
	protected AddressService addressService;

	@Autowired
	protected EmailService emailService;

	@Autowired
	protected PhoneService phoneService;

	@Autowired
	protected CreditCardService creditCardService;

	@Autowired
	protected HeadlineService headlineService;
	
	@Autowired
	protected FaceService faceService;

	@Autowired
	protected ItemService itemService;
	
	@Autowired
	protected FriendService friendService;
	
	@Autowired
	protected CartItemService cartItemService;

	protected Person fetchPerson() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User user = (User) principal;

		return personService.findPersonByUsername(user.getUsername());
	}

	protected void application(HttpServletRequest request) {
		request.setAttribute("application", "SleekSwap");
	}

	protected Person setup(HttpServletRequest request) throws Exception {
		//Object principal = SecurityContextHolder.getContext()
		//		.getAuthentication().getPrincipal();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
		
		//System.out.println("principal " + principal);
		//User user = (User) principal;

		Person person = personService.findPersonByUsername(user.getUsername());
		Bank bank = bankService.get(person.getBank().getId());

		if (bank != null) {
			bankService.updateBank(bank);
		}
		request.setAttribute("bank", bank);
		request.setAttribute("friendRequests", requestFriendService
				.findAllRequestFor(person));

		request.setAttribute("application", "mySHOPnSHARE");

		request.setAttribute("person", person);

		return person;
	}
	
	protected void setupHomePage(HttpServletRequest request) throws Exception{
		Person person = this.setup(request);

		/*
		request.setAttribute("addresses", addressService.getAddressesForPerson(person));
		request.setAttribute("emails", emailService.findAllActiveEmailsFor(person));
		
		request.setAttribute("phones", phoneService.getPhonesForPerson(person));
		*/
		request.setAttribute("cards", creditCardService.getCardsForPerson(person));
		
		request.setAttribute("primaryAddress", addressService.getPrimaryAddressForPerson(person));
		request.setAttribute("primaryEmail", emailService.getPrimaryEmailForPerson(person));
		
		request.setAttribute("addresses", addressService.getPublicAddressesForPerson(person));
		request.setAttribute("emails", emailService.getPublicEmailsForPerson(person));
		request.setAttribute("phones", phoneService.getPublicPhonesForPerson(person));		
		
		request.setAttribute("emailTypes", EmailAddressType.values());
		request.setAttribute("addressTypes", AddressType.values());
		request.setAttribute("phoneTypes", PhoneType.values());
		request.setAttribute("cardTypes", CreditCardType.values());	
		
		request.setAttribute("faces", faceService.getFaces(person));
		
		request.setAttribute("defaultFace", faceService.getDefaultFace(person));
		
		request.setAttribute("activeItemsCount", itemService.findOwnItemsCount(person, null, null));
		
		request.setAttribute("friends", friendService.findAllFriends(person));
		
		request.setAttribute("rewardsPerVendor", cartItemService.getRewardsPerVendor(person));
		
		List<Item> itemCategories =  itemCategoryService.findAllItemsFor(person, null, 0);
		int itemCategorySize = 0;
		if(itemCategories != null){
			itemCategorySize = itemCategories.size();
		}
		request.setAttribute("itemCategories", itemCategoryService.findAllItemsFor(person, null, 0));
		request.setAttribute("itemCategorySize", itemCategorySize);
		
		request.setAttribute("isMaxSelling", personService.isMaxSelling(person));
		
		request.setAttribute("friendRequests", requestFriendService
				.findAllRequestFor(person));
		
		String headline = "";
		Headline hl = headlineService.findLatestHeadlineFor(person);
		if(hl != null){
			headline = hl.getHeadline();
		}
		request.setAttribute("headline",
				headline);		
	}
	
    @InitBinder
    public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(false);
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
		
		binder.registerCustomEditor(String.class,
				new StringTrimmerEditor(false));
		
		binder.registerCustomEditor(Integer.class, null,
				new CustomNumberEditor(Integer.class, null, true));
		
		binder.registerCustomEditor(Long.class, null, new CustomNumberEditor(
				Long.class, null, true));
		
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
		
		binder.registerCustomEditor(Boolean.class, null, new CustomBooleanEditor(
				 "true", "false", true));
    }


}
```
