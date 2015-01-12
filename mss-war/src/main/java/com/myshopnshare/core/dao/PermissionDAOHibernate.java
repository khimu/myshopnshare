package com.myshopnshare.core.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Permission;
import com.myshopnshare.core.domain.Person;
@Repository("permissionDAO")
public class PermissionDAOHibernate extends
		GenericDAOHibernate<Permission, Long> implements PermissionDAO {
	public Permission findPermissionForPerson(Long permissionId, Person person){
		String hql = "FROM Permission p WHERE p.person = :person";
		Query q = this.getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setCacheable(true);
		return (Permission) q.uniqueResult();
	
	}
	
}
