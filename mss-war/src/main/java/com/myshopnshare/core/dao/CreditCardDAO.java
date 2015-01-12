package com.myshopnshare.core.dao;

import java.util.List;

import com.myshopnshare.core.domain.CreditCard;
import com.myshopnshare.core.domain.Person;

public interface CreditCardDAO extends GenericDAO<CreditCard, Long> {
	public CreditCard getCreditCardForPerson(Person person, Long id);
	public List<CreditCard> getCardsForPerson(Person person);
}
