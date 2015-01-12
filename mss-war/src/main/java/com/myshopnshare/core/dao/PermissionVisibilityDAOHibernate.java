package com.myshopnshare.core.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Permission;
import com.myshopnshare.core.domain.PermissionVisibility;
import com.myshopnshare.core.domain.VisibilityDomain;
@Repository("permissionVisibilityDAO")
public class PermissionVisibilityDAOHibernate extends
		GenericDAOHibernate<PermissionVisibility, Long> implements
		PermissionVisibilityDAO {
	public PermissionVisibility findFor(Permission permission, VisibilityDomain vd){
		String hql = "FROM PermissionVisibility p WHERE p.permission = :permission AND p.visibilityDomain = :vd";
		Query q = getSession().createQuery(hql);
		q.setParameter("permission", permission);
		q.setParameter("vd", vd);
		return (PermissionVisibility) q.uniqueResult();
	}
}
