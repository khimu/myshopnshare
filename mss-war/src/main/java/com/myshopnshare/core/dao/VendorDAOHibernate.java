package com.myshopnshare.core.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Vendor;
import com.myshopnshare.core.exception.SleekSwapDBException;
@Repository("vendorDAO")
public class VendorDAOHibernate extends GenericDAOHibernate<Vendor, Long>
		implements VendorDAO {
	
	public Vendor findVendorByUsername(String username) throws SleekSwapDBException{
		String hql = "FROM Vendor where userAccount.username = :username and active = true";
		Query q = getSession().createQuery(hql);
		q.setParameter("username", username);
		q.setCacheable(true);
		return (Vendor) q.uniqueResult();
	}

	/*
	 * Working query : select from person p where p.lastname LIKE 'ung' or
	 * p.firstname LIKE 'ung' and p.id = (select s.id from person s where
	 * s.firstname LIKE 'khim' or s.firstname LIKE 'khim') (non-Javadoc)
	 * 
	 * @see com.myshopnshare.core.dao.PersonDAO#findBySearchString(java.lang.String,
	 * java.lang.String)
	 */
	public List<Vendor> findBySearchString(String first, String last) throws SleekSwapDBException{
		String hql = "from Vendor p where UPPER(p.lastName) LIKE :last or UPPER(p.firstName) LIKE :last or p.id = (select s.id from Person s where UPPER(s.firstName) LIKE :first or UPPER(s.lastName) LIKE :first) AND p.active = true";
		Query q = getSession().createQuery(hql);
		q.setParameter("first", first.toUpperCase());
		q.setParameter("last", last.toUpperCase());
		q.setCacheable(true);
		q.setMaxResults(50);
		q.setFirstResult(0);
		q.setFetchSize(50);
		return q.list();
	}
	
	public Vendor findByEmail(String email) throws SleekSwapDBException{
		String hql = "from Vendor as p left join EmailAddress as e where e.person.id = p.id and e.email = :email and p.active = false";
		Query q = getSession().createQuery(hql);
		q.setParameter("email", email);
		q.setCacheable(true);
		return (Vendor) q.uniqueResult();
	}
}
