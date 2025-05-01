# CreditCardDAOHibernate

```java
package com.myshopnshare.core.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.CreditCard;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.Phone;
@Repository("creditCardDAO")
public class CreditCardDAOHibernate extends
		GenericDAOHibernate<CreditCard, Long> implements CreditCardDAO {
	public CreditCard getCreditCardForPerson(Person person, Long id){
		String hql = "select e FROM CreditCard e WHERE e.person = :person and e.id = :id";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("id", id);
		q.setCacheable(true);
		return (CreditCard) q.uniqueResult();		
	}
	
	public List<CreditCard> getCardsForPerson(Person person){
		String hql = "FROM CreditCard e WHERE e.person = :person and e.active = :active";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("active", true);
		q.setCacheable(true);
		return q.list();	
	}
	
}
```
