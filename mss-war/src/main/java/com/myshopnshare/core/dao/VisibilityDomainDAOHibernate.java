package com.myshopnshare.core.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Permission;
import com.myshopnshare.core.domain.VisibilityDomain;
@Repository("visibilityDomainDAO")
public class VisibilityDomainDAOHibernate extends
		GenericDAOHibernate<VisibilityDomain, Long> implements
		VisibilityDomainDAO {
	
	public List<VisibilityDomain> findAllGroupsForPerson(Permission permission){
		String hql = "FROM VisibilityDomain v WHERE v in (SELECT p.visibilityDomain FROM PermissionVisibility p WHERE p.permission = :permission)";
		Query q = this.getSession().createQuery(hql);
		q.setParameter("permission", permission);
		q.setCacheable(true);
		return q.list();
	}

	
}
