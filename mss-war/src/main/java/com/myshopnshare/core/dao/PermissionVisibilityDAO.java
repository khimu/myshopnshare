package com.myshopnshare.core.dao;

import com.myshopnshare.core.domain.Permission;
import com.myshopnshare.core.domain.PermissionVisibility;
import com.myshopnshare.core.domain.VisibilityDomain;

public interface PermissionVisibilityDAO extends
		GenericDAO<PermissionVisibility, Long> {
	public PermissionVisibility findFor(Permission permission, VisibilityDomain vd);
}
