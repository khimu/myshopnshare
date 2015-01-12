package com.myshopnshare.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Institution;
@Repository("institutionDAO")
public class InstitutionDAOHibernate extends
		GenericDAOHibernate<Institution, Long> implements InstitutionDAO {

	public Institution findInstitutionByUsername(String username) {
		String hql = "FROM Institution where userAccount.username = :username";
		Query q = getSession().createQuery(hql);
		q.setParameter("username", username);
		q.setCacheable(true);
		return (Institution) q.uniqueResult();
	}

	public List<Institution> findBySearchString(String searchString) {
		String hql = "select p from Institution p " + "left join p.emails e "
				+ "where e.email like :email "
				+ "or UPPER(p.firstName) LIKE :searchString "
				+ "or UPPER(p.lastName) LIKE :searchString "
				+ "or UPPER(p.schoolDistrict) LIKE :searchString "
				+ "or UPPER(p.institutionName) LIKE :searchString "
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

	public List<Institution> findBySearchString(Map<String, String> searchKeys) {
		String hql = "select p from Institution p "
				+ "left join p.emails emails "
				+ "left join p.addresses address "
				+ "left join p.phones phone " + "where p.active = true ";

		for (Map.Entry<String, String> entry : searchKeys.entrySet()) {
			hql = hql + "and UPPER(" + entry.getKey() + ") LIKE :"
					+ StringUtils.remove(entry.getKey(), ".") + " ";
		}

		hql = hql + "order by p.enteredDate desc";

		Query q = getSession().createQuery(hql);
		for (Map.Entry<String, String> entry : searchKeys.entrySet()) {
			q.setParameter(StringUtils.remove(entry.getKey(), "."), "%"
					+ entry.getValue().toUpperCase() + "%");
		}
		q.setFirstResult(0);
		q.setFetchSize(50);
		q.setMaxResults(50);
		q.setCacheable(true);
		return q.list();
	}

	public Institution findByEmail(String email) {
		String hql = "from Institution p " + "left join p.emails e "
				+ "where e.email LIKE :email " + "and p.active = false";
		Query q = getSession().createQuery(hql);
		q.setParameter("email", email);
		q.setFirstResult(0);
		q.setFetchSize(50);
		q.setMaxResults(50);
		q.setCacheable(true);
		return (Institution) q.uniqueResult();
	}

}
