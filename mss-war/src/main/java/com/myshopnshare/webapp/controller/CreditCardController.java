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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.CreditCard;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.CreditCardType;
import com.myshopnshare.core.service.CreditCardService;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.model.User;

public class CreditCardController extends MultiActionController {
	private static transient final Log log = LogFactory
			.getLog(CreditCardController.class);

	protected final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
	static {
		simpleDateFormat.applyPattern("MM/dd/yyyy");
	}
	
	@Autowired
	private CreditCardService cardService;
	
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
		return new ModelAndView("account/creditcard/creditcard");
	}

	public ModelAndView all(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		request.setAttribute("creditcards", person.getActiveCreditCards());
		return new ModelAndView("account/creditcard/jsonCreditCards");
	}

	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			Person person = fetchPerson();
			CreditCard card = new CreditCard();
			card.setNumber(request.getParameter("number"));
			//card.setCardAddress(new Address());
			card.setCardName(request.getParameter("cardName"));
			card.setFullName(request.getParameter("fullName"));
			card.setExpirationDate(request.getParameter("expirationDate"));
			card.setSecurityCode(StringUtils.trimToNull(request.getParameter("securityCode")) == null ? null : Integer.parseInt(request.getParameter("securityCode")));
			card.setType(StringUtils.trimToNull(request.getParameter("creditCardType")) == null ? null : CreditCardType.valueOf(request.getParameter("creditCardType")));
			card.setPerson(person);

			cardService.save(card);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("creditcard", card);

			// return new ModelAndView(new JSONView(), map);
			return new ModelAndView("account/creditcard/jsonCreditCard", map);
		} catch (Exception e) {
			log.equals(e.getMessage());
			return new ModelAndView("account/creditcard/jsonCreditCard");
		}
	}
	
	public ModelAndView edit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			Person person = fetchPerson();
			CreditCard card = cardService.getCreditCardForPerson(person, Long.parseLong(request.getParameter("id")));
			card.setNumber(StringUtils.trimToNull(request.getParameter("number")) == null ? card.getNumber() : request.getParameter("number"));
			//card.setCardAddress(new Address());
			card.setCardName(StringUtils.trimToNull(request.getParameter("cardName")) == null ? card.getCardName() : request.getParameter("cardName"));
			card.setFullName(StringUtils.trimToNull(request.getParameter("fullName")) == null ? card.getFullName() : request.getParameter("fullName"));
			card.setExpirationDate(StringUtils.trimToNull(request.getParameter("expirationDate")) == null ? card.getExpirationDate() : request.getParameter("expirationDate"));
			card.setSecurityCode(StringUtils.trimToNull(request.getParameter("securityCode")) == null ? card.getSecurityCode() : Integer.parseInt(request.getParameter("securityCode")) );
			card.setType(StringUtils.trimToNull(request.getParameter("creditCardType")) == null ? card.getType() : CreditCardType.valueOf(request.getParameter("creditCardType")));
			card.setPerson(person);

			cardService.update(card);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("creditcard", card);

			// return new ModelAndView(new JSONView(), map);
			return new ModelAndView("account/creditcard/jsonCreditCard", map);
		} catch (Exception e) {
			log.equals(e.getMessage());
			return new ModelAndView("account/creditcard/jsonCreditCard");
		}
	}
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			Person person = fetchPerson();
			String recordId = request.getParameter("recordId");
			CreditCard card = cardService.getCreditCardForPerson(person, Long.parseLong(recordId));

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("creditcard", card);

			return new ModelAndView("account/creditcard/jsonCreditCard", map);
		} catch (Exception e) {
			log.equals(e.getMessage());
			return new ModelAndView("account/creditcard/jsonCreditCard");
		}
	}

	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			Person person = fetchPerson();
			String recordId = request.getParameter("recordId");
			CreditCard card = cardService.getCreditCardForPerson(person, Long.parseLong(recordId));
			card.setActive(false);
			cardService.update(card);

			return new ModelAndView("account/creditcard/jsonCreditCard");
		} catch (Exception e) {
			log.equals(e.getMessage());
			return new ModelAndView("account/creditcard/jsonCreditCard");
		}
	}

	public void setCardService(CreditCardService cardService) {
		this.cardService = cardService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

}
