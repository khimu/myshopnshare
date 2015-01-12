package com.myshopnshare.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.PersonVisibilityDomainDAO;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.PersonVisibilityDomain;
import com.myshopnshare.core.domain.VisibilityDomain;

@Service("personVisibilityDomainService")
@Transactional
public class PersonVisibilityDomainServiceImpl
		extends
		GenericServiceImpl<PersonVisibilityDomain, Long>
		implements PersonVisibilityDomainService {
	
	private PersonVisibilityDomainDAO personVisibilityDomainDAO;

	@Autowired
	public PersonVisibilityDomainServiceImpl(PersonVisibilityDomainDAO genericDao) {
		super(genericDao);
		this.personVisibilityDomainDAO = genericDao;
	}
	
	public List<Person> findAllPersonWithVisibility(VisibilityDomain vd){
		return ((PersonVisibilityDomainDAO)this.dao).findAllPersonWithVisibility(vd);
	}
	
	public PersonVisibilityDomain findPersonWithVisibility(Long groupId, Long friendId){
		return ((PersonVisibilityDomainDAO)this.dao).findPersonWithVisibility(groupId, friendId);
	}

}
