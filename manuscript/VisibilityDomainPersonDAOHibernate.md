# VisibilityDomainPersonDAOHibernate

```java
package com.myshopnshare.core.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.VisibilityDomainPerson;

@Repository("visibilityDomainPersonDao")
public class VisibilityDomainPersonDAOHibernate extends GenericDAOHibernate<VisibilityDomainPerson, Long> implements VisibilityDomainPersonDAO{
	public List<VisibilityDomainPerson> getVisibilityGroups(Person person){
		String hql = "FROM VisibilityDomainPerson WHERE person = :person and active = :active";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("active", true);
		q.setCacheable(true);
		return q.list();	
	}
}
```
