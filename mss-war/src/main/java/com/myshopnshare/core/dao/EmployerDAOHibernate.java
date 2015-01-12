package com.myshopnshare.core.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Employer;
@Repository("employerDAO")
public class EmployerDAOHibernate extends GenericDAOHibernate<Employer, Long>
		implements EmployerDAO {

	public Employer findEmployerByUsername(String username) {
		String hql = "FROM Employer where userAccount.username = :username";
		Query q = getSession().createQuery(hql);
		q.setParameter("username", username);
		q.setCacheable(true);
		return (Employer) q.uniqueResult();
	}

	public List<Employer> findBySearchString(String searchString) {
		String hql = "select p from Employer p " + "left join p.emails e "
				+ "where e.email like :email "
				+ "or UPPER(p.firstName) LIKE :searchString "
				+ "or UPPER(p.lastName) LIKE :searchString "
				+ "or UPPER(p.companyName) LIKE :searchString "
				+ "and p.active = true " + "order by p.enteredDate desc";
		Query q = getSession().createQuery(hql);
		q.setParameter("searchString", "%" + searchString.toUpperCase() + "%");
		q.setParameter("email", searchString);
		q.setFirstResult(0);
		q.setFetchSize(50);
		q.setMaxResults(50);
		q.setCacheable(true);
		return q.list();
	}

	public Employer findByEmail(String email) {
		String hql = "from Employer p " +
				"left join p.emails e " +
				"where e.email LIKE :email " +
				"and p.active = false";
		Query q = getSession().createQuery(hql);
		q.setParameter("email", email);
		q.setCacheable(true);
		return (Employer) q.uniqueResult();
	}

}
