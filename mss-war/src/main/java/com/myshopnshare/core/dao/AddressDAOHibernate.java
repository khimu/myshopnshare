package com.myshopnshare.core.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Address;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.AddressType;
import com.myshopnshare.core.enums.VisibilityType;

@Repository("addressDAO")
public class AddressDAOHibernate extends GenericDAOHibernate<Address, Long> implements AddressDAO{

	public Address getAddressForPerson(Person person, Long id){
		String hql = "select e FROM Address e WHERE e.person = :person and e.id = :id";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("id", id);
		q.setCacheable(true);
		return (Address) q.uniqueResult();		
	}
	
	
	public List<Address> getAddressesForPerson(Person person){
		String hql = "FROM Address e WHERE e.person = :person and e.active = :active";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("active", true);
		q.setCacheable(true);
		return q.list();	
	}
	
	public List<Address> getPublicAddressesForPerson(Person person){
		String hql = "FROM Address e WHERE e.person = :person and e.active = :active and e.visibility = :visibility";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("active", true);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setCacheable(true);
		return q.list();	
	}
	
	public Address getAddressWithTypeForPerson(Person person, AddressType type){
		String hql = "FROM Address e WHERE e.person = :person and e.active = :active and e.type = :type";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("active", true);
		q.setParameter("type", type);
		q.setCacheable(true);
		return (Address)q.uniqueResult();
	}
	
	public Address getPrimaryAddressForPerson(Person person){
		String hql = "FROM Address e WHERE e.person = :person and e.active = :active and e.primaryAddress = :primary";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("active", true);
		q.setParameter("primary", true);
		q.setCacheable(true);
		return (Address)q.uniqueResult();
	}
}
