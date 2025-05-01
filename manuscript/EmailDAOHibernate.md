# EmailDAOHibernate

```java
package com.myshopnshare.core.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.EmailAddress;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.EmailAddressType;
import com.myshopnshare.core.enums.VisibilityType;

@Repository("emailDAO")
public class EmailDAOHibernate extends GenericDAOHibernate<EmailAddress, Long>
		implements EmailDAO {
	
	public EmailAddress getEmailAddressForPerson(Person person, Long id){
		String hql = "select e FROM EmailAddress e WHERE e.person = :person and e.id = :id";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("id", id);
		q.setCacheable(true);
		return (EmailAddress) q.uniqueResult();		
	}
	
	public Person findInactivePersonByEmail(String email) {
		String hql = "SELECT e.person FROM EmailAddress e WHERE e.email = :email AND e.active = false";
		Query q = getSession().createQuery(hql);
		q.setParameter("email", email.toLowerCase());
		q.setCacheable(true);
		return (Person) q.uniqueResult();
	}
	
	public Person findPersonByEmail(String email) {
		String hql = "SELECT e.person FROM EmailAddress e WHERE e.email = :email";
		Query q = getSession().createQuery(hql);
		q.setParameter("email", email.toLowerCase());
		q.setCacheable(true);
		return (Person) q.uniqueResult();
	}

	public List<EmailAddress> findAllActiveEmailsFor(Person person){
		String hql = "FROM EmailAddress WHERE person = :person AND active = true";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setFirstResult(0);
		q.setFetchSize(50);
		q.setMaxResults(50);
		q.setCacheable(true);
		return q.list();
	}
	
	public List<EmailAddress> getPublicEmailsForPerson(Person person){
		String hql = "FROM EmailAddress e WHERE e.person = :person and e.active = :active and e.visibility = :visibility";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("active", true);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setCacheable(true);
		return q.list();	
	}
	
	public EmailAddress getPrimaryEmailForPerson(Person person){
		String hql = "FROM EmailAddress e WHERE e.person = :person and e.active = :active and e.primaryEmail = :primaryEmail";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("active", true);
		q.setParameter("primaryEmail", true);
		q.setCacheable(true);
		return (EmailAddress)q.uniqueResult();	
	}
	
	public EmailAddress getEmailWithType(Person person, EmailAddressType type){
		String hql = "FROM EmailAddress e WHERE e.person = :person and e.active = :active and e.type = :type";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("active", true);
		q.setParameter("type", type);
		q.setCacheable(true);
		return (EmailAddress)q.uniqueResult();	
	}
}
```
