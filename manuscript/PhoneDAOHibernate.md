# PhoneDAOHibernate

```java
package com.myshopnshare.core.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.EmailAddress;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.Phone;
import com.myshopnshare.core.enums.VisibilityType;
@Repository("phoneDAO")
public class PhoneDAOHibernate extends GenericDAOHibernate<Phone, Long>
		implements PhoneDAO {
	public Phone getPhoneForPerson(Person person, Long id){
		String hql = "select e FROM Phone e WHERE e.person = :person and e.id = :id";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("id", id);
		q.setCacheable(true);
		return (Phone) q.uniqueResult();		
	}
	
	public List<Phone> getPhonesForPerson(Person person){
		String hql = "FROM Phone e WHERE e.person = :person and e.active = :active";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("active", true);
		q.setCacheable(true);
		return q.list();	
	}
	
	public List<Phone> getPublicPhonesForPerson(Person person){
		String hql = "FROM Phone e WHERE e.person = :person and e.active = :active and visibility = :visibility";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("active", true);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setCacheable(true);
		return q.list();	
	}
}
```
