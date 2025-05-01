# PersonDAOHibernate

```java
package com.myshopnshare.core.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.UserType;

@Repository("personDAO")
public class PersonDAOHibernate extends GenericDAOHibernate<Person, Long>
		implements PersonDAO {

	public Person findPersonByUsername(String username) {
		String hql = "FROM Person where userAccount.username = :username";
		Query q = getSession().createQuery(hql);
		q.setParameter("username", username);
		q.setCacheable(true);
		return (Person) q.uniqueResult();
	}

	/*
	 * Working query : select from person p where p.lastname LIKE 'ung' or
	 * p.firstname LIKE 'ung' and p.id = (select s.id from person s where
	 * s.firstname LIKE 'khim' or s.firstname LIKE 'khim') (non-Javadoc)
	 * 
	 * @see com.myshopnshare.core.dao.PersonDAO#findBySearchString(java.lang.String,
	 * java.lang.String)
	 */
	public List<Person> findMerchants() {
		String hql = "select distinct p from Person p " +
				"where p.userType LIKE :merchant " +
				"and p.subscribed = true " +
				"and p.active = true " +
				"order by p.enteredDate desc";
		Query q = getSession().createQuery(hql);
		q.setParameter("merchant", UserType.MERCHANT);
		q.setCacheable(true);
		q.setFetchSize(10);
		q.setMaxResults(10);
		q.setFirstResult(0);
		return q.list();
	}
	
	/*
	 * Working query : select from person p where p.lastname LIKE 'ung' or
	 * p.firstname LIKE 'ung' and p.id = (select s.id from person s where
	 * s.firstname LIKE 'khim' or s.firstname LIKE 'khim') (non-Javadoc)
	 * 
	 * @see com.myshopnshare.core.dao.PersonDAO#findBySearchString(java.lang.String,
	 * java.lang.String)
	 */
	public List<Person> findBySearchString(String first, String last) {
		String email = first;
		String hql = "select distinct p from Person p " +
				"left join p.emails e " +
				"left join p.tags t " +
				"left join p.addresses a " +
				"where e.email like :email " +
				"or UPPER(p.firstName) LIKE :first " +
				"or UPPER(p.lastName) LIKE :last " +
				"or UPPER(p.lastName) LIKE :first " +
				"or UPPER(p.firstName) LIKE :last " +
				"or upper(a.city) like :searchString " +
				"or upper(a.stateOrProvince) like :searchString " +
				"or upper(a.country) like :searchString " +
				"or upper(t.tag) like :searchString " +
				"and p.active = true " +
				"order by p.enteredDate desc";
		Query q = getSession().createQuery(hql);
		q.setParameter("first", first.toUpperCase().trim());
		q.setParameter("last", last.toUpperCase().trim());
		q.setParameter("searchString", StringUtils.trimToNull(first.toUpperCase() + " " + last.toUpperCase()));
		q.setParameter("email", first.trim());
		q.setMaxResults(50);
		q.setFirstResult(0);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}
	
	// @Deprecated bad query
	public List<Person> findBySearchString(String searchString) {
		String hql = "select p from Person p " +
		"left join p.emails e " +
		"left join p.tags t " +
				"left join p.addresses a " +
				"where e.email like :email " +
				"or upper(p.fullName) like :searchString " +
				"or upper(a.city) like :searchString " +
				"or upper(a.stateOrProvince) like :searchString " +
				"or upper(a.country) like :searchString " +
				"or upper(t.tag) like :searchString " +
				"and p.active = true " +
				"order by p.enteredDate desc";
		Query q = getSession().createQuery(hql);
		q.setParameter("searchString", "%" + searchString.toUpperCase().trim() + "%");
		q.setParameter("email", searchString.trim());
		q.setMaxResults(50);
		q.setFirstResult(0);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}	
	
	public Person findByEmail(String email) {
		String hql = "select distinct p from Person p left join p.emails e WHERE e.person.id = p.id and e.email LIKE :email and p.active = false";
		Query q = getSession().createQuery(hql);
		q.setParameter("email", email);
		q.setCacheable(true);
		return (Person) q.uniqueResult();
	}

}
```
