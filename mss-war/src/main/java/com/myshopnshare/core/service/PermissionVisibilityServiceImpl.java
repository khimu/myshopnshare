package com.myshopnshare.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.PermissionVisibilityDAO;
import com.myshopnshare.core.domain.Permission;
import com.myshopnshare.core.domain.PermissionVisibility;
import com.myshopnshare.core.domain.VisibilityDomain;

@Service("permissionVisibilityService")
@Transactional
public class PermissionVisibilityServiceImpl extends
		GenericServiceImpl<PermissionVisibility, Long>
		implements PermissionVisibilityService {
	
	private PermissionVisibilityDAO permissionVisibilityDAO;

	@Autowired
	public PermissionVisibilityServiceImpl(PermissionVisibilityDAO genericDao) {
		super(genericDao);
		this.permissionVisibilityDAO = genericDao;
	}
	
	public PermissionVisibility findFor(Permission permission, VisibilityDomain vd){
		return ((PermissionVisibilityDAO)this.dao).findFor(permission, vd);
	}
}
