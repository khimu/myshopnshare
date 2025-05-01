# SnapBackDAOHibernate

```java
package com.myshopnshare.core.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.SnapBack;
@Repository("snapBackDAO")
public class SnapBackDAOHibernate extends GenericDAOHibernate<SnapBack, Long>
		implements SnapBackDAO {

	public List<SnapBack> findAllMessages(Person person) {
		String hql = "FROM SnapBack s join fetch s.person WHERE s.person = :person ORDER BY s.createDate DESC";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		return q.list();
	}
}
```
