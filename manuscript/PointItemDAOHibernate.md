# PointItemDAOHibernate

```java
package com.myshopnshare.core.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.PointItem;
@Repository("pointItemDAO")
public class PointItemDAOHibernate extends GenericDAOHibernate<PointItem, Long>
		implements PointItemDAO {


	public List<PointItem> findItems(List<String> tags) {
		StringBuilder hql = new StringBuilder("select distinct p "
				+ "FROM PointItem p " 
				+ "left join p.tags t ");
		int index = appendTag(tags, hql);
		hql.append(" and p.type = 'POINT' " 
				+ "and p.active = true "
				+ "order by p.enteredDate desc");
		Query q = getSession().createQuery(hql.toString());
		q.setCacheable(true);
		q.setMaxResults(36);
		q.setFirstResult(0);
		q.setFetchSize(50);
		return q.list();
	}

	public List<PointItem> findItems(Long vendorId) {
		StringBuilder hql = new StringBuilder("select distinct p "
				+ "FROM PointItem p " +
				" where p.person.id = :vendorId " 
				+ "and p.active = true "
				+ "order by p.enteredDate desc");
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("vendorId", vendorId);
		q.setMaxResults(50);
		q.setFirstResult(0);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	public List<PointItem> findItems(Long vendorId, List<String> tags) {
		StringBuilder hql = new StringBuilder("select distinct p "
				+ "FROM PointItem p " 
				+ "left join p.tags t ");
		int index = appendTag(tags, hql);
		hql.append(" and p.person.id = :vendorId " 
				+ "and p.active = true "
				+ "order by p.enteredDate desc");
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("vendorId", vendorId);
		q.setCacheable(true);
		q.setMaxResults(50);
		q.setFirstResult(0);
		q.setFetchSize(50);
		return q.list();
	}


	public List<PointItem> findItems() {
		StringBuilder hql = new StringBuilder(
				"FROM PointItem p where " +
				"p.type = 'POINT' " 
				+ "and p.active = true "
				+ "order by p.enteredDate desc");
		Query q = getSession().createQuery(hql.toString());
		q.setCacheable(true);
		q.setMaxResults(50);
		q.setFirstResult(0);
		q.setFetchSize(50);
		return q.list();
	}
	
	public PointItem getPointItemForPerson(Person person, Long id){
		String hql = "select e FROM PointItem e WHERE e.person = :person and e.id = :id";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("id", id);
		q.setCacheable(true);
		return (PointItem) q.uniqueResult();		
	}

}
```
