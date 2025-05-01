# FriendPageController

```java
package com.myshopnshare.webapp.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.Headline;
import com.myshopnshare.core.domain.PageViewed;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.AddressType;
import com.myshopnshare.core.enums.CreditCardType;
import com.myshopnshare.core.enums.EmailAddressType;
import com.myshopnshare.core.enums.PhoneType;
import com.myshopnshare.core.enums.UserType;
import com.myshopnshare.core.service.AddressService;
import com.myshopnshare.core.service.BankService;
import com.myshopnshare.core.service.CartItemService;
import com.myshopnshare.core.service.CreditCardService;
import com.myshopnshare.core.service.EmailService;
import com.myshopnshare.core.service.FaceService;
import com.myshopnshare.core.service.FriendService;
import com.myshopnshare.core.service.HeadlineService;
import com.myshopnshare.core.service.ItemService;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.core.service.PhoneService;
import com.myshopnshare.core.service.RequestFriendService;
import com.myshopnshare.model.User;

/**
 * View a single friend's page
 * 
 * @author khimung
 * 
 */
public class FriendPageController extends MultiActionController {
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
	protected FaceService faceService;

	@Autowired
	protected ItemService itemService;

	@Autowired
	protected CartItemService cartItemService;

	@Autowired
	private HeadlineService headlineService;
	
	@Autowired
	private FriendService friendService;

	private Person fetchPerson() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User user = (User) principal;

		return personService.findPersonByUsername(user.getUsername());
	}

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		Map map = new HashMap();
		String friendId = request.getParameter("friendId");
		
		if(StringUtils.trimToNull(friendId) == null){			
			return new ModelAndView("secure/home");
		}
		
		Person friend = personService.get(Long.parseLong(friendId));

		if (person.getUserType() == UserType.USER && !friendService.isFriends(person, friendId) && !friend.getId().equals(person.getId())) {
			request.setAttribute("person", person);
			request.setAttribute("results", Arrays.asList(friend));
			return new ModelAndView("home/noPermission");
		}

		PageViewed viewed = friend.getViewed();
		if(viewed == null){
			viewed = new PageViewed();
			viewed.setPerson(friend);
			friend.setViewed(viewed);
		}
		viewed.increment();
		
		personService.update(person);
		
		Headline headline = headlineService.findLatestHeadlineFor(friend);
		map.put("person", friend);
		map.put("headline", headlineService.findLatestHeadlineFor(friend));
		map.put("bank", friend.getBank());
		
		request.setAttribute("cards", creditCardService.getCardsForPerson(friend));
		
		request.setAttribute("primaryAddress", addressService.getPrimaryAddressForPerson(friend));
		request.setAttribute("primaryEmail", emailService.getPrimaryEmailForPerson(friend));
		
		request.setAttribute("addresses", addressService.getPublicAddressesForPerson(friend));
		request.setAttribute("emails", emailService.getPublicEmailsForPerson(friend));
		request.setAttribute("phones", phoneService.getPublicPhonesForPerson(friend));		
		
		request.setAttribute("emailTypes", EmailAddressType.values());
		request.setAttribute("addressTypes", AddressType.values());
		request.setAttribute("phoneTypes", PhoneType.values());
		request.setAttribute("cardTypes", CreditCardType.values());	
		
		request.setAttribute("faces", faceService.getFaces(friend));
		
		request.setAttribute("defaultFace", faceService.getDefaultFace(friend));
		
		request.setAttribute("activeItemsCount", itemService.findOwnItemsCount(friend, null, null));
		
		request.setAttribute("friends", friendService.findAllFriends(friend));

		return new ModelAndView("friend/friendPageLayout", map);
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void setHeadlineService(HeadlineService headlineService) {
		this.headlineService = headlineService;
	}

}
```
