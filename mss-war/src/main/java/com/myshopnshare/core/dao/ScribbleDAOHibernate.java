package com.myshopnshare.core.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.Scribble;
import com.myshopnshare.core.enums.VisibilityType;
@Repository("scribbleDAO")
public class ScribbleDAOHibernate extends GenericDAOHibernate<Scribble, Long>
		implements ScribbleDAO {
	public Scribble getScribbleForPerson(Person person, Long id){
		String hql = "select e FROM Scribble e WHERE e.person = :person and e.id = :id";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("id", id);
		q.setCacheable(true);
		return (Scribble) q.uniqueResult();		
	}
	
	public List<Scribble> findOwnScribble(Person person, int start) {
		StringBuilder hql = new StringBuilder("FROM Scribble s WHERE ");
		hql.append(" s.person = :person");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);

		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setFetchSize(36);
		q.setCacheable(true);
		return q.list();
	}

	public List<Scribble> findOwnScribble(Person person, String category,
			int start) {
		StringBuilder hql = new StringBuilder("FROM Scribble s WHERE ");

		hql.append(" s.category = :category AND");

		hql.append(" s.person = :person");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("category", category);
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setFetchSize(36);
		q.setCacheable(true);
		return q.list();
	}

	public List<Scribble> findOwnScribble(Person person, String category,
			List<String> tags, int start) {
		StringBuilder hql = new StringBuilder("FROM Scribble s WHERE ");
		hql.append(" s in (SELECT distinct t.scribble FROM ScribbleTag t ");
		int index = appendTag(tags, hql);
		hql.append(" )");
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
		q.setFetchSize(36);
		q.setCacheable(true);
		return q.list();
	}

	public List<Scribble> findOwnScribble(Person person, List<String> tags,
			int start) {
		StringBuilder hql = new StringBuilder("FROM Scribble s WHERE ");
		hql.append(" s in (SELECT distinct t.scribble FROM ScribbleTag t ");
		int index = appendTag(tags, hql);
		hql.append(" )");
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
		q.setFetchSize(36);
		q.setCacheable(true);
		return q.list();
	}

	public List<Scribble> findWorldScribble(Person person, String category,
			List<String> tags, int start) {
		StringBuilder hql = new StringBuilder("FROM Scribble s WHERE ");
		hql.append(" s in (SELECT distinct n.scribble FROM ScribbleVisibilityDomain n WHERE ");
		hql.append(" (n.visibilityDomain in (SELECT DISTINCT p.visibilityDomain FROM PersonVisibilityDomain p WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) ");

		hql.append(" AND n.scribble in (SELECT t.scribble FROM ScribbleTag t ");
		int index = appendTag(tags, hql);
		hql.append(" ))");

		hql.append(" AND s.category = :category ");

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
		q.setFetchSize(36);
		q.setCacheable(true);
		return q.list();
	}

	public List<Scribble> findWorldScribble(Person person, List<String> tags,
			int start) {
		StringBuilder hql = new StringBuilder("FROM Scribble s WHERE ");
		hql.append(" s in (SELECT distinct n.scribble FROM ScribbleVisibilityDomain n WHERE ");
		hql.append(" (n.visibilityDomain in (SELECT DISTINCT p.visibilityDomain FROM PersonVisibilityDomain p WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) ");

		hql.append(" AND n.scribble in (SELECT t.scribble FROM ScribbleTag t ");
		int index = appendTag(tags, hql);
		hql.append(" ))");

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
		q.setFetchSize(36);
		q.setCacheable(true);
		return q.list();
	}

	public List<Scribble> findWorldScribble(Person person, String category,
			int start) {
		StringBuilder hql = new StringBuilder("FROM Scribble s WHERE ");
		hql.append(" s in (SELECT distinct n.scribble FROM ScribbleVisibilityDomain n WHERE ");
		hql.append(" (n.visibilityDomain in (SELECT DISTINCT p.visibilityDomain FROM PersonVisibilityDomain p WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility)) ");
		hql.append(" AND s.category = :category ");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setParameter("category", category);
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setFetchSize(36);
		q.setCacheable(true);
		return q.list();
	}

	public List<Scribble> findWorldScribble(Person person, int start) {
		StringBuilder hql = new StringBuilder("FROM Scribble s WHERE ");
		hql.append(" s in (SELECT distinct n.scribble FROM ScribbleVisibilityDomain n WHERE ");
		hql.append(" (n.visibilityDomain in (SELECT DISTINCT p.visibilityDomain FROM PersonVisibilityDomain p WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility)) ");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setFetchSize(36);
		q.setCacheable(true);
		return q.list();
	}

	public List<Scribble> findOneFriendsScribble(Person friend, Person person,
			String category, List<String> tags, int start) {
		StringBuilder hql = new StringBuilder("FROM Scribble s WHERE ");
		hql.append(" s in (SELECT distinct n.scribble FROM ScribbleVisibilityDomain n WHERE");
		hql.append(" (n.visibilityDomain in (SELECT DISTINCT p.visibilityDomain FROM PersonVisibilityDomain p WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) ");
		hql.append(" AND n.scribble in (SELECT t.scribble FROM ScribbleTag t ");
		int index = appendTag(tags, hql);
		hql.append(" AND t.scribble.person = (SELECT DISTINCT f.friend FROM Friend f WHERE f.friend = :friend AND f.person = :person))) ");
		hql.append(" AND s.category = :category ");
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
		q.setFetchSize(36);
		q.setCacheable(true);
		return q.list();
	}

	public List<Scribble> findOneFriendsScribble(Person friend, Person person,
			List<String> tags, int start) {
		StringBuilder hql = new StringBuilder("FROM Scribble s WHERE ");
		hql.append(" s in (SELECT distinct n.scribble FROM ScribbleVisibilityDomain n WHERE");
		hql.append(" (n.visibilityDomain in (SELECT DISTINCT p.visibilityDomain FROM PersonVisibilityDomain p WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) ");
		hql.append(" AND n.scribble in (SELECT t.scribble FROM ScribbleTag t ");
		int index = appendTag(tags, hql);
		hql.append(" AND t.scribble.person = (SELECT DISTINCT f.friend FROM Friend f WHERE f.friend = :friend AND f.person = :person))) ");
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
		q.setFetchSize(36);
		q.setCacheable(true);
		return q.list();
	}

	public List<Scribble> findOneFriendsScribble(Person friend, Person person,
			String category, int start) {
		StringBuilder hql = new StringBuilder("FROM Scribble s WHERE ");
		hql.append(" s in (SELECT distinct n.scribble FROM ScribbleVisibilityDomain n WHERE");
		hql.append(" (n.visibilityDomain in (SELECT DISTINCT p.visibilityDomain FROM PersonVisibilityDomain p WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) ");
		hql.append(" AND n.scribble.person = (SELECT DISTINCT f.friend FROM Friend f WHERE f.friend = :friend AND f.person = :person)) ");
		hql.append(" AND s.category = :category ");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("friend", friend);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setParameter("category", category);

		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setFetchSize(36);
		q.setCacheable(true);
		return q.list();
	}

	public List<Scribble> findOneFriendsScribble(Person friend, Person person,
			int start) {
		StringBuilder hql = new StringBuilder("FROM Scribble s WHERE ");
		hql.append(" s in (SELECT distinct n.scribble FROM ScribbleVisibilityDomain n WHERE");
		hql.append(" (n.visibilityDomain in (SELECT DISTINCT p.visibilityDomain FROM PersonVisibilityDomain p WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) ");
		hql.append(" AND n.scribble.person = (SELECT DISTINCT f.friend FROM Friend f WHERE f.friend = :friend AND f.person = :person)) ");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("friend", friend);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setFetchSize(36);
		q.setCacheable(true);
		return q.list();
	}

	public List<Scribble> findAllFriendsScribble(Person person, String category,
			List<String> tags, int start) {
		StringBuilder hql = new StringBuilder("FROM Scribble s WHERE ");
		hql.append(" s in (SELECT distinct n.scribble FROM ScribbleVisibilityDomain n WHERE");
		hql.append(" (n.visibilityDomain in (SELECT DISTINCT p.visibilityDomain FROM PersonVisibilityDomain p WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) ");
		hql.append(" AND n.scribble in (SELECT t.scribble FROM ScribbleTag t ");
		int index = appendTag(tags, hql);
		hql.append(" AND t.scribble.person = (SELECT DISTINCT f.friend FROM Friend f WHERE f.person = :person))) ");

		hql.append(" AND s.category = :category ");
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
		q.setFetchSize(36);
		q.setCacheable(true);
		return q.list();
	}
	public List<Scribble> findAllFriendsScribble(Person person,
			List<String> tags, int start) {
		StringBuilder hql = new StringBuilder("FROM Scribble s WHERE ");
		hql.append(" s in (SELECT distinct n.scribble FROM ScribbleVisibilityDomain n WHERE");
		hql.append(" (n.visibilityDomain in (SELECT DISTINCT p.visibilityDomain FROM PersonVisibilityDomain p WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) ");
		hql.append(" AND n.scribble in (SELECT t.scribble FROM ScribbleTag t ");
		int index = appendTag(tags, hql);
		hql.append(" AND t.scribble.person = (SELECT DISTINCT f.friend FROM Friend f WHERE f.person = :person))) ");
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
		q.setFetchSize(36);
		q.setCacheable(true);
		return q.list();
	}
	public List<Scribble> findAllFriendsScribble(Person person, String category,
			int start) {
		StringBuilder hql = new StringBuilder("FROM Scribble s WHERE ");
		hql.append(" s in (SELECT distinct n.scribble FROM ScribbleVisibilityDomain n WHERE");
		hql.append(" (n.visibilityDomain in (SELECT DISTINCT p.visibilityDomain FROM PersonVisibilityDomain p WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) ");
		hql.append(" AND n.scribble.person = (SELECT DISTINCT f.friend FROM Friend f WHERE f.person = :person)) ");
		hql.append(" AND s.category = :category ");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setParameter("category", category);
		q.setFetchSize(36);
		q.setCacheable(true);
		return q.list();
	}
	public List<Scribble> findAllFriendsScribble(Person person, int start) {
		StringBuilder hql = new StringBuilder("FROM Scribble s WHERE ");
		hql.append(" s in (SELECT distinct n.scribble FROM ScribbleVisibilityDomain n WHERE");
		hql.append(" (n.visibilityDomain in (SELECT DISTINCT p.visibilityDomain FROM PersonVisibilityDomain p WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) ");
		hql.append(" AND n.scribble.person = (SELECT DISTINCT f.friend FROM Friend f WHERE f.person = :person)) ");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setFetchSize(36);
		q.setCacheable(true);
		return q.list();
	}

	/*
	private int appendTag(List<String> tags, StringBuilder hql) {
		int index = 0;

		for (String tag : tags) {
			hql.append("(t.tag LIKE :tag" + index++ + ")");
			hql.append(" OR ");
		}
		hql = hql.delete(hql.toString().lastIndexOf("O"), hql.toString()
				.length());

		return index;
	}
	*/
}
