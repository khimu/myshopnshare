# JournalDAOHibernate

```java
package com.myshopnshare.core.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Journal;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.VisibilityType;
@Repository("journalDAO")
public class JournalDAOHibernate extends GenericDAOHibernate<Journal, Long>
		implements JournalDAO {
	
	public Journal getJournalForPerson(Person person, Long id){
		String hql = "select e FROM Journal e WHERE e.person = :person and e.id = :id";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("id", id);
		q.setCacheable(true);
		return (Journal) q.uniqueResult();		
	}
	
	public List<Journal> findOwnJournal(Person person, int start) {
		StringBuilder hql = new StringBuilder("FROM Journal s WHERE ");
		hql.append(" s.person = :person");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);

		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	public List<Journal> findOwnJournal(Person person, String category,
			int start) {
		StringBuilder hql = new StringBuilder("FROM Journal s WHERE ");

		hql.append(" s.category = :category AND");

		hql.append(" s.person = :person");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("category", category);
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	public List<Journal> findOwnJournal(Person person, String category,
			List<String> tags, int start) {
		StringBuilder hql = new StringBuilder("select distinct s FROM Journal s " +
				"left join s.tags t " +
				"where ");
		int index = appendTag(tags, hql);
		hql.append(" AND s.category = :category");
		hql.append(" AND s.person = :person");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("category", category);
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	public List<Journal> findOwnJournal(Person person, List<String> tags,
			int start) {
		StringBuilder hql = new StringBuilder("select distinct s FROM Journal s " +
				"left join s.tags t " +
				"where ");
		int index = appendTag(tags, hql);
		hql.append(" AND s.person = :person");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	public List<Journal> findWorldJournal(Person person, String category,
			List<String> tags, int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Journal s " +
				"left join s.tags t " +
				"left join s.journalVisibilities n " +
				"WHERE s.category = :category " +
				"and s.person != :person " +
				"and s.person not in (" +
				"SELECT DISTINCT f.friend " +
				"FROM Friend f " +
				"WHERE f.person = :person) and ");
		int index = appendTag(tags, hql);
		hql.append(" and (n.visibilityDomain in (" +
				"SELECT DISTINCT p.visibilityDomain " +
				"FROM PersonVisibilityDomain p " +
				"WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) ");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);

		q.setParameter("category", category);
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	public List<Journal> findWorldJournal(Person person, List<String> tags,
			int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Journal s " +
				"left join s.tags t " +
				"left join s.journalVisibilities n " +
				"where s.person != :person " +
				"and s.person not in (" +
				"SELECT DISTINCT f.friend " +
				"FROM Friend f " +
				"WHERE f.person = :person) and ");
		int index = appendTag(tags, hql);
		hql.append(" and (n.visibilityDomain in (" +
				"SELECT DISTINCT p.visibilityDomain " +
				"FROM PersonVisibilityDomain p " +
				"WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) ");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);

		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		} 
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	public List<Journal> findWorldJournal(Person person, String category,
			int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Journal s " +
				"left join s.journalVisibilities n " +
				"WHERE s.category = :category " +
				"and s.person != :person " +
				"and s.person not in (" +
				"SELECT DISTINCT f.friend " +
				"FROM Friend f " +
				"WHERE f.person = :person) " +
				"and (n.visibilityDomain in (" +
				"SELECT DISTINCT p.visibilityDomain " +
				"FROM PersonVisibilityDomain p " +
				"WHERE p.person = :person ) " +
				"OR n.visibilityDomain.visibility = :visibility) " +
				"AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setParameter("category", category);
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	public List<Journal> findWorldJournal(Person person, int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Journal s " +
				"left join s.journalVisibilities n " +
				"where s.person != :person " +
				"and s.person not in (" +
				"SELECT DISTINCT f.friend " +
				"FROM Friend f " +
				"WHERE f.person = :person) ");
		hql.append(" and (n.visibilityDomain in (" +
				"SELECT DISTINCT p.visibilityDomain " +
				"FROM PersonVisibilityDomain p " +
				"WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) ");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	public List<Journal> findOneFriendsJournal(Person friend, Person person,
			String category, List<String> tags, int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Journal s " +
				"left join s.tags t " +
				"left join s.journalVisibilities n " +
				"WHERE s.category = :category " +
				"and s.person in (SELECT DISTINCT f.friend " +
				"FROM Friend f " +
				"WHERE f.friend = :friend " +
				"AND f.person = :person) and ");
		int index = appendTag(tags, hql);
		hql.append(" and (n.visibilityDomain in (" +
				"SELECT DISTINCT p.visibilityDomain " +
				"FROM PersonVisibilityDomain p " +
				"WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) ");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("friend", friend);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setParameter("category", category);
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	public List<Journal> findOneFriendsJournal(Person friend, Person person,
			List<String> tags, int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Journal s " +
				"left join s.tags t " +
				"left join s.journalVisibilities n " +
				"where s.person in (SELECT DISTINCT f.friend " +
				"FROM Friend f " +
				"WHERE f.friend = :friend " +
				"AND f.person = :person) and ");
		int index = appendTag(tags, hql);
		hql.append(" and (n.visibilityDomain in (" +
				"SELECT DISTINCT p.visibilityDomain " +
				"FROM PersonVisibilityDomain p " +
				"WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) ");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("friend", friend);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	public List<Journal> findOneFriendsJournal(Person friend, Person person,
			String category, int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Journal s " +
				"left join s.journalVisibilities n " +
				"WHERE s.category = :category " +
				"and s.person in (SELECT DISTINCT f.friend " +
				"FROM Friend f " +
				"WHERE f.friend = :friend " +
				"AND f.person = :person) ");
		hql.append("and (n.visibilityDomain in (" +
				"SELECT DISTINCT p.visibilityDomain " +
				"FROM PersonVisibilityDomain p " +
				"WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) ");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("friend", friend);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setParameter("category", category);

		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	public List<Journal> findOneFriendsJournal(Person friend, Person person,
			int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Journal s " +
				"left join s.journalVisibilities n " +
				"where s.person in (SELECT DISTINCT f.friend " +
				"FROM Friend f " +
				"WHERE f.friend = :friend " +
				"AND f.person = :person) ");
		hql.append("and (n.visibilityDomain in (" +
				"SELECT DISTINCT p.visibilityDomain " +
				"FROM PersonVisibilityDomain p " +
				"WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) ");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("friend", friend);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	public List<Journal> findAllFriendsJournal(Person person, String category,
			List<String> tags, int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Journal s " +
				"left join s.tags t " +
				"left join s.journalVisibilities n " +
				"WHERE s.category = :category " +
				"and s.person in (SELECT DISTINCT f.friend FROM Friend f WHERE f.person = :person) and ");
		int index = appendTag(tags, hql);
		hql.append(" and (n.visibilityDomain in (" +
				"SELECT DISTINCT p.visibilityDomain " +
				"FROM PersonVisibilityDomain p " +
				"WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) ");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setParameter("category", category);
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}
	public List<Journal> findAllFriendsJournal(Person person,
			List<String> tags, int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Journal s " +
				"left join s.tags t " +
				"left join s.journalVisibilities n " +
				"where s.person in (SELECT DISTINCT f.friend FROM Friend f WHERE f.person = :person) and ");
		int index = appendTag(tags, hql);
		hql.append(" and (n.visibilityDomain in (" +
				"SELECT DISTINCT p.visibilityDomain " +
				"FROM PersonVisibilityDomain p " +
				"WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) ");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setMaxResults(36);
		q.setFirstResult(start);
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	public List<Journal> findAllFriendsJournal(Person person, String category,
			int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Journal s " +
				"left join s.journalVisibilities n " +
				"WHERE s.category = :category " +
				"and s.person in (SELECT DISTINCT f.friend FROM Friend f WHERE f.person = :person) ");
		hql.append(" and (n.visibilityDomain in (" +
				"SELECT DISTINCT p.visibilityDomain " +
				"FROM PersonVisibilityDomain p " +
				"WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) ");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setParameter("category", category);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}
	public List<Journal> findAllFriendsJournal(Person person, int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Journal s " +
				"left join s.journalVisibilities n " +
				"where s.person in (SELECT DISTINCT f.friend FROM Friend f WHERE f.person = :person) ");
		hql.append(" and (n.visibilityDomain in (" +
				"SELECT DISTINCT p.visibilityDomain " +
				"FROM PersonVisibilityDomain p " +
				"WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) ");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

}
```
