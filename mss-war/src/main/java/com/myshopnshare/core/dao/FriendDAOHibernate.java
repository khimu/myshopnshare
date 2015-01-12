package com.myshopnshare.core.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Friend;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.VisibilityDomain;
import com.myshopnshare.core.enums.VisibilityType;
@Repository("friendDAO")
public class FriendDAOHibernate extends GenericDAOHibernate<Friend, Long>
		implements FriendDAO {
	
	public List<Friend> findFriends(Person person) {
		String hql = "FROM Friend f where f.person = :person AND f.person.active = true and f.friend.active = true and f.active = true";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setCacheable(true);
		return q.list();
	}	
	public Friend getFriendForPerson(Person person, Long id){
		String hql = "FROM Friend e WHERE e.person = :person and e.id = :id and e.active = true and e.friend.active = true and e.person.active = true";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("id", id);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setFirstResult(0);
		q.setCacheable(true);
		return (Friend) q.uniqueResult();		
	}
	
	public List<Person> findAllFriends(Person person) {
		String hql = "SELECT f.friend FROM Friend f where f.person = :person AND f.person.active = true and f.friend.active = true and f.active = true";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setFirstResult(0);
		q.setCacheable(true);
		return q.list();
	}
	
	public List<Long> findAllFriendsID(Person person) {
		String hql = "SELECT f.friend.id FROM Friend f where f.person = :person and f.person.active = true and f.active = true and f.friend.active = true";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setFirstResult(0);
		q.setCacheable(true);
		return q.list();
	}	
	
	public List<Long> getFriends(Person person, int start) {
		String hql = "FROM Friend f where f.person = :person and f.person.active = true and f.active = true and f.friend.active = true";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setFirstResult(start);
		q.setFetchSize(30);
		q.setCacheable(true);
		return q.list();
	}		
	
	public List<Person> findAllFriendWithVisibility(VisibilityDomain vd){
		String hql = "FROM Friend f WHERE f.friend in (SELECT p.person FROM PersonVisibilityDomain p WHERE p.visibilityDomain = :vd) AND f.person.active = true and f.active = true and f.friend.active = true";
		Query q = getSession().createQuery(hql);
		q.setParameter("vd", vd);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setFirstResult(0);
		q.setCacheable(true);
		return q.list();		
	}

	public List<Friend> findOwnFriend(Person person, int start) {
		StringBuilder hql = new StringBuilder("select distinct s FROM Friend s WHERE ");
		hql.append(" s.person = :person");
		hql.append(" AND s.active = true and s.person.active = true and s.friend.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);

		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	public List<Friend> findOwnFriend(Person person, String color,
			int start) {
		StringBuilder hql = new StringBuilder("select distinct s FROM Friend s WHERE ");
		hql.append(" upper(s.color) like :color AND");
		hql.append(" s.person = :person");
		hql.append(" AND s.active = true and s.person.active= true and s.friend.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("color", "%" + color.toUpperCase() + "%");
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	// will never happen
	public List<Friend> findWorldFriend(Person person, String color,
			int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Friend s " +
				"left join s.friendVisibilities n " +
				"where s.person != :person " +
				"and s.person not in (" +
				"SELECT DISTINCT f.friend " +
				"FROM Friend f " +
				"WHERE f.person = :person) " +
				"and (n.visibilityDomain in (" +
				"SELECT DISTINCT p.visibilityDomain " +
				"FROM PersonVisibilityDomain p " +
				"WHERE p.person = :person ) " +
				"OR n.visibilityDomain.visibility = :visibility) " +
				"AND s.active = true and s.person.active= true and s.friend.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setParameter("color", "%" + color.toUpperCase() + "%");
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	// Will never happen
	public List<Friend> findWorldFriend(Person person, int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Friend s " +
				"left join s.friendVisibilities n " +
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
		hql.append(" AND s.active = true and s.person.active= true and s.friend.active = true");
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

	public List<Friend> findOneFriendsFriend(Person friend, Person person,
			String color, int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Friend s " +
				"left join s.friendVisibilities n " +
				"where s.person = :friend " +
				"and s.person != :person " +
				"and s.color = :color " +
				"and (n.visibilityDomain in (" +
				"SELECT DISTINCT p.visibilityDomain " +
				"FROM PersonVisibilityDomain p " +
				"WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) ");
		hql.append(" AND s.active = true and s.person.active= true and s.friend.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("friend", friend);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setParameter("color", "%" + color.toUpperCase() + "%");

		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	public List<Friend> findOneFriendsFriend(Person friend, Person person,
			int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Friend s " +
				"left join s.friendVisibilities n " +
				"where s.person = :friend " +
				"and s.person != :person " +
				"and (n.visibilityDomain in (" +
				"SELECT DISTINCT p.visibilityDomain " +
				"FROM PersonVisibilityDomain p " +
				"WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) ");
		hql.append(" AND s.active = true and s.person.active= true and s.friend.active = true");
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

	// Will never happen
	public List<Friend> findAllFriendsFriend(Person person, String color,
			int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Friend s " +
				"left join s.friendVisibilities n " +
				"WHERE s.color = :color " +
				"and s.person in (SELECT DISTINCT f.friend FROM Friend f WHERE f.person = :person) ");
		hql.append(" and (n.visibilityDomain in (" +
				"SELECT DISTINCT p.visibilityDomain " +
				"FROM PersonVisibilityDomain p " +
				"WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) ");
		hql.append(" AND s.active = true and s.person.active= true and s.friend.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setFetchSize(50);
		q.setParameter("color", "%" + color.toUpperCase() + "%");
		q.setCacheable(true);
		return q.list();
	}
	
	// will never happen and is wrong
	public List<Friend> findAllFriendsFriend(Person person, int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Friend s " +
				"left join s.friendVisibilities n " +
				"where s.person in (SELECT DISTINCT f.friend FROM Friend f WHERE f.person = :person) ");
		hql.append(" and (n.visibilityDomain in (" +
				"SELECT DISTINCT p.visibilityDomain " +
				"FROM PersonVisibilityDomain p " +
				"WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) ");
		hql.append(" AND s.active = true and s.person.active= true and s.friend.active = true");
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
