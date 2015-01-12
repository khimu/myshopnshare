package com.myshopnshare.core.service;

import java.util.List;

import com.myshopnshare.core.domain.CreditCard;
import com.myshopnshare.core.domain.Person;

public interface CreditCardService extends
		GenericService<CreditCard, Long> {
	public CreditCard getCreditCardForPerson(Person person, Long id);
	public List<CreditCard> getCardsForPerson(Person person);
}
