package com.myshopnshare.core.service;

import java.util.List;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.PersonVisibilityDomain;
import com.myshopnshare.core.domain.VisibilityDomain;

public interface PersonVisibilityDomainService extends
		GenericService<PersonVisibilityDomain, Long> {
	public List<Person> findAllPersonWithVisibility(VisibilityDomain vd);
	public PersonVisibilityDomain findPersonWithVisibility(Long groupId, Long friendId);
}
