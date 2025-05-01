# PersonVisibilityDomainDAOHibernate

```java
package com.myshopnshare.core.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.PersonVisibilityDomain;
import com.myshopnshare.core.domain.VisibilityDomain;
@Repository("personVisibilityDomainDAO")
public class PersonVisibilityDomainDAOHibernate extends
		GenericDAOHibernate<PersonVisibilityDomain, Long> implements PersonVisibilityDomainDAO{
	
	public List<Person> findAllPersonWithVisibility(VisibilityDomain vd){
		String hql = "SELECT p.person FROM PersonVisibilityDomain p WHERE p.visibilityDomain = :vd ORDER BY p.enteredDate DESC";
		Query q = getSession().createQuery(hql);
		q.setParameter("vd", vd);
		q.setMaxResults(50);
		q.setFirstResult(0);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();		
	}
	
	public PersonVisibilityDomain findPersonWithVisibility(Long groupId, Long friendId){
		String hql = "FROM PersonVisibilityDomain p WHERE p.visibilityDomain.id = :vdId AND p.person.id = :friendId ORDER BY p.enteredDate DESC";
		Query q = getSession().createQuery(hql);
		q.setParameter("vdId", groupId);
		q.setParameter("friendId", friendId);
		q.setMaxResults(50);
		q.setFirstResult(0);
		q.setFetchSize(50);
		q.setCacheable(true);
		return (PersonVisibilityDomain) q.uniqueResult();		
	}

	
	// search specific friend's items that are visible to person 
}
```
