package com.myshopnshare.core.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Face;
import com.myshopnshare.core.domain.Person;
@Repository("faceDAO")
public class FaceDAOHibernate extends GenericDAOHibernate<Face, Long> implements
		FaceDAO {
	public Face getFaceForPerson(Person person, Long id){
		String hql = "select e FROM Face e WHERE e.person = :person and e.id = :id";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("id", id);
		q.setCacheable(true);
		return (Face) q.uniqueResult();		
	}
	
	public List<Face> getFaces(Person person){
		String hql = "FROM Face WHERE person = :person and active = :active";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("active", true);
		q.setCacheable(true);
		return q.list();	
	}
	
	public Face getDefaultFace(Person person){
		String hql = "FROM Face WHERE person = :person and defaultFace = :default and active = :active";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("active", true);
		q.setParameter("default", true);
		q.setCacheable(true);
		return (Face)q.uniqueResult();	
	}
	
	public void getDeleteDefaultFace(Person person){
		String hql = "update Face set defaultFace = :defaultFace WHERE person = :person";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("defaultFace", false);
		int rowsUpdated = q.executeUpdate();
		System.out.println("rows Updated " + rowsUpdated);
	}
}
