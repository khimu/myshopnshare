package com.myshopnshare.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.ContactDAO;
import com.myshopnshare.core.domain.Contact;

@Service("contactService")
@Transactional
public class ContactServiceImpl extends GenericServiceImpl<Contact, Long> implements ContactService {

	private ContactDAO contactDAO;

	@Autowired
    public ContactServiceImpl(ContactDAO genericDao) {
    	super(genericDao);
        this.contactDAO = genericDao;
    }
}
