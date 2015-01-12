package com.myshopnshare.core.dao;

import java.util.List;

import com.myshopnshare.core.domain.Permission;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.VisibilityDomain;

public interface PreferenceDAO extends GenericDAO<Permission, Long> {
	public List<VisibilityDomain> getVisibilityDomainsFor(Person person);
}
