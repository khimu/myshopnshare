package com.myshopnshare.core.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.ItemOrderDetail;
import com.myshopnshare.core.domain.OrderDetail;
import com.myshopnshare.core.domain.Person;
@Repository("orderDetailDAO")
public class OrderDetailDAOHibernate extends
		GenericDAOHibernate<OrderDetail, Long> implements OrderDetailDAO {

	public OrderDetail getOrderForPerson(Person person, Long id){
		String hql = "select e FROM OrderDetail e WHERE e.person = :person and e.id = :id";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("id", id);
		q.setCacheable(true);
		return (OrderDetail) q.uniqueResult();		
	}
	

	public List<OrderDetail> findAllOrderFor(Person person) {
		String hql = "select distinct o " +
				"FROM OrderDetail o " +
				"WHERE o.person = :person " +
				"and o.active = true " +
				"ORDER BY o.enteredDate ASC";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setMaxResults(50);
		q.setFirstResult(0);
		q.setFetchSize(50);
		return q.list();
	}

	public List<ItemOrderDetail> findAllApprovedOrderFor(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ItemOrderDetail> findAllCompletedOrderFor(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ItemOrderDetail> findAllPendingOrderFor(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ItemOrderDetail> findAllRequestReturnedOrderFor(Person person) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<ItemOrderDetail> findAllReturnedOrderFor(Person person) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<ItemOrderDetail> findAllShippedOrderFor(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

}
