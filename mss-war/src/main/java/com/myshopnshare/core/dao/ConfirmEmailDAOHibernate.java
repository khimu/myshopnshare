package com.myshopnshare.core.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.ConfirmEmail;
@Repository("confirmEmailDAO")
public class ConfirmEmailDAOHibernate extends GenericDAOHibernate<ConfirmEmail, Long> implements ConfirmEmailDAO{

	public ConfirmEmail findByKey(String key){
		String hql = "FROM ConfirmEmail WHERE randomKey = :key";
		Query q = getSession().createQuery(hql);
		q.setParameter("key", key);
		q.setCacheable(true);
		return (ConfirmEmail) q.uniqueResult();
	}
}
