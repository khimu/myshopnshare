package com.myshopnshare.core.dao;

import java.util.List;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.Phone;

public interface PhoneDAO extends GenericDAO<Phone, Long> {
	public Phone getPhoneForPerson(Person person, Long id);
	public List<Phone> getPhonesForPerson(Person person);
	public List<Phone> getPublicPhonesForPerson(Person person);
}
