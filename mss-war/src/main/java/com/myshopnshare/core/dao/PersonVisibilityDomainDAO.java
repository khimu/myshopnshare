package com.myshopnshare.core.dao;

import java.util.List;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.PersonVisibilityDomain;
import com.myshopnshare.core.domain.VisibilityDomain;

public interface PersonVisibilityDomainDAO extends
		GenericDAO<PersonVisibilityDomain, Long> {
	public List<Person> findAllPersonWithVisibility(VisibilityDomain vd);
	public PersonVisibilityDomain findPersonWithVisibility(Long groupId, Long friendId);
}
