package com.myshopnshare.core.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Permission;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.VisibilityDomain;
@Repository("preferenceDAO")
public class PreferenceDAOHibernate extends
		GenericDAOHibernate<Permission, Long> implements PreferenceDAO {

	public List<VisibilityDomain> getVisibilityDomainsFor(Person person) {
		String hql = "SELECT p.visibilityDomains FROM Preference p join fetch p.person WHERE p.person = :person";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setMaxResults(50);
		q.setFirstResult(0);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}
}