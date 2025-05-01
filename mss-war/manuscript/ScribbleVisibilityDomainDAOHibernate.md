# ScribbleVisibilityDomainDAOHibernate

```java
package com.myshopnshare.core.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.Scribble;
import com.myshopnshare.core.domain.ScribbleVisibilityDomain;
import com.myshopnshare.core.enums.VisibilityType;

@Repository("scribbleVisibilityDomainDAO")
public class ScribbleVisibilityDomainDAOHibernate extends GenericDAOHibernate<ScribbleVisibilityDomain, Long> implements ScribbleVisibilityDomainDAO{
	/** This isn't used **/
	public List<Scribble> findAllVisibleScribblesFor(Person person, int start){
		StringBuilder hql = new StringBuilder("SELECT v.scribble FROM ScribbleVisibilityDomain v, PersonVisibilityDomain p WHERE");
		hql.append(" (v.visibilityDomain = p.visibilityDomain OR v.visibilityDomain.visibility = :visibility) AND");
		hql.append(" p.person  = :person AND"); 
		hql.append(" v.scribble.person != :person ORDER BY v.enteredDate ASC");
		Query q = getSession().createQuery(hql.toString());
		q.setFirstResult(start);
		q.setMaxResults(20);
		q.setFetchSize(36);
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setCacheable(true);
		return q.list();
	}
	
	// Extra step, make sure that person is friend's with item owner
	public List<Scribble> findAllFriendVisibleScribblesFor(Person person, int start){
		StringBuilder hql = new StringBuilder("SELECT v.scribble FROM Friend f, ScribbleVisibilityDomain v, PersonVisibilityDomain p WHERE");
		hql.append(" f.friend = v.scribble.person AND ");
		hql.append(" f.person = :person AND ");
		hql.append(" (v.visibilityDomain = p.visibilityDomain OR v.visibilityDomain.visibility = :visibility) AND ");
		hql.append(" p.person = :person ");
		hql.append(" ORDER BY v.enteredDate ASC");
		Query q = getSession().createQuery(hql.toString());
		q.setFirstResult(start);
		q.setMaxResults(20);
		q.setFetchSize(36);
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setCacheable(true);
		return q.list();		
	}

}
```
