package com.myshopnshare.core.dao;

import com.myshopnshare.core.domain.Permission;
import com.myshopnshare.core.domain.Person;

public interface PermissionDAO extends GenericDAO<Permission, Long> {
	public Permission findPermissionForPerson(Long permissionId, Person person);
}
