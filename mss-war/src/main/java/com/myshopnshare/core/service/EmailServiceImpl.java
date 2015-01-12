package com.myshopnshare.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.EmailDAO;
import com.myshopnshare.core.domain.EmailAddress;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.Phone;
import com.myshopnshare.core.enums.EmailAddressType;

@Service("emailService")
@Transactional
public class EmailServiceImpl extends
		GenericServiceImpl<EmailAddress, Long> implements
		EmailService {

	public EmailDAO emailDAO;

	@Autowired
    public EmailServiceImpl(EmailDAO genericDao) {
    	super(genericDao);
        this.emailDAO = genericDao;
    }
	
	public EmailAddress getEmailAddressForPerson(Person person, Long id){
		return emailDAO.getEmailAddressForPerson(person, id);
	}

	public Person findPersonByEmail(String email) {
		return emailDAO.findPersonByEmail(email);
	}
	public List<EmailAddress> findAllActiveEmailsFor(Person person){
		return emailDAO.findAllActiveEmailsFor(person);
	}
	
	public Person findInactivePersonByEmail(String email){
		return emailDAO.findInactivePersonByEmail(email);
	}
	
	public List<EmailAddress> getPublicEmailsForPerson(Person person){
		return emailDAO.getPublicEmailsForPerson(person);
	}
	
	public EmailAddress getEmailWithType(Person person, EmailAddressType type){
		return emailDAO.getEmailWithType(person, type);
	}
	
	public EmailAddress getPrimaryEmailForPerson(Person person){
		return emailDAO.getPrimaryEmailForPerson(person);
	}
}
