package com.myshopnshare.core.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.CartItem;
import com.myshopnshare.core.domain.Face;
import com.myshopnshare.core.domain.Person;
@Repository("cartItemDAO")
public class CartItemDAOHibernate extends GenericDAOHibernate<CartItem, Long>
		implements CartItemDAO {

	public List<CartItem> getCartItems(Person person){
		String hql = "FROM CartItem WHERE person = :person and active = :active";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("active", true);
		q.setCacheable(true);
		return q.list();	
	}
}
