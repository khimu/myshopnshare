package com.myshopnshare.core.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.News;
import com.myshopnshare.core.domain.NewsVisibilityDomain;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.VisibilityType;
@Repository("newsVisibilityDomainDAO")
public class NewsVisibilityDomainDAOHibernate extends GenericDAOHibernate<NewsVisibilityDomain, Long> implements NewsVisibilityDomainDAO {

	/* Call this for reading world news */
	public List<News> findAllVisibleNewsFor(Person person, int start){
		StringBuilder hql = new StringBuilder("SELECT v.news FROM NewsVisibilityDomain v, PersonVisibilityDomain p WHERE");
		hql.append(" (v.visibilityDomain = p.visibilityDomain OR v.visibilityDomain.visibility = :visibility) AND");
		hql.append(" p.person  = :person AND"); 
		hql.append(" v.news.person != :person ORDER BY v.enteredDate ASC");
		Query q = getSession().createQuery(hql.toString());
		q.setMaxResults(50);
		q.setFirstResult(start);
		q.setFetchSize(50);
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setCacheable(true);
		return q.list();
	}
	
	public List<News> findAllFriendVisibleNewsFor(Person person, int start){
		StringBuilder hql = new StringBuilder("SELECT v.news FROM Friend f, NewsVisibilityDomain v, PersonVisibilityDomain p WHERE");
		hql.append(" f.friend = v.news.person AND ");
		hql.append(" f.person = :person AND ");
		hql.append(" (v.visibilityDomain = p.visibilityDomain OR v.visibilityDomain.visibility = :visibility) AND ");
		hql.append(" p.person = :person ");
		hql.append(" ORDER BY v.enteredDate ASC");
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setMaxResults(50);
		q.setFirstResult(start);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();		
	}

	public List<News> findFriendsVisibleNews(Person friend, Person person, int start){
		StringBuilder hql = new StringBuilder("SELECT v.news FROM Friend f, NewsVisibilityDomain v, PersonVisibilityDomain p WHERE");
		hql.append(" f.friend = v.news.person AND ");
		hql.append(" f.friend = :friend");
		hql.append(" f.person = :person AND ");
		hql.append(" (v.visibilityDomain = p.visibilityDomain OR v.visibilityDomain.visibility = :visibility) AND ");
		hql.append(" p.person = :person ");
		hql.append(" ORDER BY v.enteredDate ASC");
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setMaxResults(50);
		q.setFirstResult(start);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();		
	}
	
}
