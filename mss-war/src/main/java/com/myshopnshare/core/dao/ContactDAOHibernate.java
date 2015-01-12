package com.myshopnshare.core.dao;

import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Contact;
@Repository("contactDAO")
public class ContactDAOHibernate extends GenericDAOHibernate<Contact, Long>
		implements ContactDAO {

}
