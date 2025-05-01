# ItemOrderDetailDAOHibernate

```java
package com.myshopnshare.core.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.ItemOrderDetail;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.OrderStatus;
@Repository("itemOrderDetailDAO")
public class ItemOrderDetailDAOHibernate extends
		GenericDAOHibernate<ItemOrderDetail, Long> implements
		ItemOrderDetailDAO {
	
	public List<ItemOrderDetail> findAllOrderFor(Person person) {
		String hql = "FROM ItemOrderDetail o " +
				"WHERE o.person = :person " +
				"and o.active = true " +
				"order by o.enteredDate desc";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setFirstResult(0);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	public List<ItemOrderDetail> findAllCompletedOrderFor(Person person) {
		String hql = "FROM ItemOrderDetail o " +
				"WHERE o.person = :person " +
				"and o.status = :status " +
				"and o.active = true " +
				"order by o.enteredDate desc";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("status", OrderStatus.CLOSED);
		q.setFirstResult(0);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}


	public List<ItemOrderDetail> findAllPendingOrderFor(Person person) {
		String hql = "FROM ItemOrderDetail o " +
				"WHERE o.person = :person " +
				"and o.status = :status " +
				"and o.active = true " +
				"order by o.enteredDate desc";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("status", OrderStatus.PENDING_APPROVAL);
		q.setFirstResult(0);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	public List<ItemOrderDetail> findAllApprovedOrderFor(Person person) {
		String hql = "FROM ItemOrderDetail o " +
				"where o.person = :person " +
				"and o.status = :status " +
				"and o.active = true " +
				"order by o.enteredDate desc";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("status", OrderStatus.APPROVED);
		q.setFirstResult(0);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	public List<ItemOrderDetail> findAllShippedOrderFor(Person person) {
		String hql = "FROM ItemOrderDetail o " +
				"where o.person = :person " +
				"and o.status = :status " +
				"and o.active = true " +
				"order by o.enteredDate desc";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("status", OrderStatus.SHIPPED);
		q.setFirstResult(0);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}
	

	public List<ItemOrderDetail> findAllReturnedOrderFor(Person person) {
		String hql = "FROM ItemOrderDetail o " +
				"where o.person = :person " +
				"and o.status = :status " +
				"and o.active = true " +
				"order by o.enteredDate desc";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("status", OrderStatus.RETURNED);
		q.setFirstResult(0);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	public List<ItemOrderDetail> findAllRequestReturnedOrderFor(Person person) {
		String hql = "FROM ItemOrderDetail o " +
				"where o.person = :person " +
				"and o.status = :status " +
				"and o.active = true " +
				"order by o.enteredDate desc";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("status", OrderStatus.REQUEST_RETURN);
		q.setFirstResult(0);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

}
```
