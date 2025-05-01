# PaymentDAOHibernate

```java
package com.myshopnshare.core.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Payment;
import com.myshopnshare.core.domain.Person;
@Repository("paymentDAO")
public class PaymentDAOHibernate extends GenericDAOHibernate<Payment, Long>
		implements PaymentDAO {
	public Payment getPaymentForPerson(Person person, Long id){
		String hql = "select e FROM Payment e WHERE e.person = :person and e.id = :id";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("id", id);
		q.setCacheable(true);
		return (Payment) q.uniqueResult();		
	}
}
```
