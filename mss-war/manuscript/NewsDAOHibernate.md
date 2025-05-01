# NewsDAOHibernate

```java
package com.myshopnshare.core.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.News;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.VisibilityType;
@Repository("newsDAO")
public class NewsDAOHibernate extends GenericDAOHibernate<News, Long> implements
		NewsDAO {

	public News getNewsForPerson(Person person, Long id){
		String hql = "select e FROM News e WHERE e.person = :person and e.id = :id";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("id", id);
		q.setCacheable(true);
		return (News) q.uniqueResult();		
	}
	
	public Long findOwnNewsCount(Person person){
		String hql = "select count(s) FROM News s WHERE s.person = :person and s.active = true";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setCacheable(true);
		return (Long) q.uniqueResult();
	}

	public Long findOneFriendsNewsCount(Person friend, Person person) {
		StringBuilder hql = new StringBuilder("select count(s) " +
				"FROM News s " +
				"left join s.newsVisibility n ");
		hql.append("where (n.visibilityDomain in (" +
				"SELECT DISTINCT p.visibilityDomain " +
				"FROM PersonVisibilityDomain p " +
				"WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) " +
				"AND s.person = (" +
				"SELECT DISTINCT f.friend " +
				"FROM Friend f " +
				"WHERE f.friend = :friend " +
				"AND f.person = :person) ");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("friend", friend);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setCacheable(true);
		q.setMaxResults(6);
		return (Long) q.uniqueResult();
	}
	
	public Long findAllFriendsNewsCount(Person person) {
		StringBuilder hql = new StringBuilder("select count(s) " +
				"FROM News s " +
			"left join s.newsVisibility n ");
		hql.append("where n.visibilityDomain in (" +
				"SELECT DISTINCT p.visibilityDomain " +
				"FROM PersonVisibilityDomain p " +
				"WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility " +
				"AND s.person = (" +
				"SELECT DISTINCT f.friend " +
				"FROM Friend f " +
				"WHERE f.person = :person) ");
		hql.append("AND s.active = true ");
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setMaxResults(6);
		q.setCacheable(true);
		return (Long) q.uniqueResult();
	}
	
	
	public List<News> findOwnNews(Person person, int start) {
		String hql = "FROM News s WHERE s.person = :person and s.active = true ORDER BY s.enteredDate DESC";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setMaxResults(6);
		q.setFirstResult(start);
		q.setFetchSize(6);
		q.setCacheable(true);
		return q.list();
	}

	public List<News> findWorldNews(Person person, int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM News s " +
				"left join s.newsVisibility n ");
		hql.append("where n.visibilityDomain.visibility = :visibility ");
		hql.append("AND s.person not in (SELECT DISTINCT f.friend FROM Friend f WHERE f.person = :person) " +
				"and s.person != :person " +
				"and s.world = TRUE ");
		hql.append("AND s.active = TRUE ");
		hql.append("ORDER BY s.enteredDate DESC");
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setMaxResults(6);
		q.setFirstResult(start);
		q.setFetchSize(6);
		q.setCacheable(true);
		return q.list();
	}
	
	public Long findWorldNewsCount(Person person) {
		StringBuilder hql = new StringBuilder("select count(s) " +
				"FROM News s " +
				"left join s.newsVisibility n ");
		hql.append("where n.visibilityDomain.visibility = :visibility ");
		hql.append("AND s.person not in (SELECT DISTINCT f.friend FROM Friend f WHERE f.person = :person) " +
				"and s.person != :person " +
				"and s.world = TRUE ");
		hql.append("AND s.active = TRUE");
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setCacheable(true);
		return (Long) q.uniqueResult();
	}	

	public List<News> findWorldNews(int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM News s " +
				"left join s.newsVisibility n ");
		hql.append("where n.visibilityDomain.visibility = :visibility " +
				"and s.world = TRUE ");
		hql.append("AND s.active = TRUE ");
		hql.append("ORDER BY s.enteredDate DESC");
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setMaxResults(6);
		q.setFirstResult(start);
		q.setCacheable(true);
		q.setFetchSize(6);
		return q.list();
	}
	
	public Long findWorldNewsCount() {
		StringBuilder hql = new StringBuilder("select count(s) " +
				"FROM News s " +
				"left join s.newsVisibility n ");
		hql.append("where n.visibilityDomain.visibility = :visibility " +
				"and s.world = TRUE ");
		hql.append("AND s.active = TRUE");
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setCacheable(true);
		return (Long) q.uniqueResult();
	}	

	
	public List<News> findOneFriendsNews(Person friend, Person person, int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM News s " +
				"left join s.newsVisibility n ");
		hql.append("where (n.visibilityDomain in (" +
				"SELECT DISTINCT p.visibilityDomain " +
				"FROM PersonVisibilityDomain p " +
				"WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) " +
				"AND s.person = (" +
				"SELECT DISTINCT f.friend " +
				"FROM Friend f " +
				"WHERE f.friend = :friend " +
				"AND f.person = :person) ");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("friend", friend);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setMaxResults(6);
		q.setFirstResult(start);
		q.setFetchSize(6);
		q.setCacheable(true);
		return q.list();
	}
	
	public List<News> findAllFriendsNews(Person person, int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM News s " +
				"left join s.person.friends f " +
				"left join f.friend.allowedPermissions p " +
			"left join s.newsVisibility n ");
		hql.append("where n.visibilityDomain = p.visibilityDomain ");
		hql.append(" OR n.visibilityDomain.visibility = :visibility " +
				"AND f.friend = :person) ");
		hql.append("AND s.active = true ");
		hql.append("ORDER BY s.enteredDate DESC");
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setMaxResults(6);
		q.setFirstResult(start);
		q.setFetchSize(6);
		q.setCacheable(true);
		return q.list();
	}
	
}
```
