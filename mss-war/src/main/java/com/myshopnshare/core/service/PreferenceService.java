package com.myshopnshare.core.service;

import java.util.List;

import com.myshopnshare.core.domain.Permission;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.VisibilityDomain;

public interface PreferenceService extends
		GenericService<Permission, Long> {
	public List<VisibilityDomain> getVisibilityDomainsFor(Person person);
}
