package com.myshopnshare.core.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Counter;
@Repository("counterDAO")
public class CounterDAOHibernate extends GenericDAOHibernate<Counter, Long>
		implements CounterDAO {
	
	public Counter findGeneralCounter(){
		String hql = "FROM Counter WHERE name = 'GENERAL'";
		Query q = getSession().createQuery(hql);
		return (Counter) q.uniqueResult();
	}

}
