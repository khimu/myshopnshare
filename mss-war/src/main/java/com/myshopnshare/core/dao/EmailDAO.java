package com.myshopnshare.core.dao;

import java.util.List;

import com.myshopnshare.core.domain.EmailAddress;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.EmailAddressType;

public interface EmailDAO extends GenericDAO<EmailAddress, Long> {
	public Person findPersonByEmail(String email);
	public List<EmailAddress> findAllActiveEmailsFor(Person person);
	public Person findInactivePersonByEmail(String email);
	public EmailAddress getEmailAddressForPerson(Person person, Long id);
	public List<EmailAddress> getPublicEmailsForPerson(Person person);
	public EmailAddress getPrimaryEmailForPerson(Person person);
	public EmailAddress getEmailWithType(Person person, EmailAddressType type);
}
