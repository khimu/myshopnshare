package com.myshopnshare.core.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Events;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.VisibilityType;
@Repository("eventsDAO")
public class EventsDAOHibernate extends GenericDAOHibernate<Events, Long>
		implements EventsDAO {
	
	public Events getEventByPerson(Person person, Long id){
		String hql = "select e FROM Events e WHERE e.person = :person and e.id = :id";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("id", id);
		q.setCacheable(true);
		return (Events) q.uniqueResult();		
	}
	
	public List<Events> findPublicEvents(int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Events s " +
				"left join s.eventVisibilities n " +
				"where n.visibilityDomain.visibility = :visibility ");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setFetchSize(50);
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	public List<Events> findPublicEvents(Date startDate,
			List<String> tags, int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Events s " +
				"left join s.eventVisibilities n " +
				"left join s.tags t where ");
		int index = appendTag(tags, hql);
		hql.append(" and n.visibilityDomain.visibility = :visibility ");
		hql.append(" AND  (day(:startDate) - day(s.startDate)) = 0 ");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("visibility", VisibilityType.PUBLIC);

		q.setParameter("startDate", startDate);
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setFetchSize(50);
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}
	
	public List<Events> findPublicEvents(Date startDate,
			int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Events s " +
				"left join s.eventVisibilities n " +
				"where n.visibilityDomain.visibility = :visibility ");
		hql.append(" AND  (day(:startDate) - day(s.startDate)) = 0 ");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setParameter("startDate", startDate);
		q.setFetchSize(50);
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}


	public List<Events> findPublicEvents(
			List<String> tags, int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Events s " +
				"left join s.eventVisibilities n " +
				"left join s.tags t where ");
		int index = appendTag(tags, hql);
		hql.append(" and n.visibilityDomain.visibility = :visibility ");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("visibility", VisibilityType.PUBLIC);
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}
	
	public List<Events> findAllEvents(Person person) {
		String hql = "FROM Events e where e.person = :person AND e.active = true order by e.enteredDate DESC";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setFirstResult(0);
		q.setCacheable(true);
		return q.list();
	}
	
	// TODO (day(:startDate)) - day(e.startDate)) = 0 syntax error
	public List<Events> findEventsForDate(Person person, Date startDate){
		String hql = "select distinct e FROM Events e " +
				"left join e.guests as g " +
				"where (date(:startDate) - date(e.startDate) = 0) " +
				"and g.person = :person " +
				"AND e.active = true " +
				"order by e.enteredDate DESC";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("startDate", startDate);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setFirstResult(0);
		q.setCacheable(true);
		return q.list();		
	}
	
	public List<Events> findOwnEvents(Person person, int start) {
		StringBuilder hql = new StringBuilder("select distinct s FROM Events s WHERE ");
		hql.append(" s.person = :person");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setFetchSize(50);
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	public List<Events> findOwnEvents(Person person, Date startDate,
			int start) {
		StringBuilder hql = new StringBuilder("select distinct s FROM Events s WHERE ");
		hql.append("(date(:startDate) - date(s.startDate) = 0) AND");
		hql.append(" s.person = :person");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("startDate", startDate);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	public List<Events> findOwnEvents(Person person, Date startDate,
			List<String> tags, int start) {
		StringBuilder hql = new StringBuilder("select distinct s FROM Events s " +
				"left join s.tags t WHERE ");
		int index = appendTag(tags, hql);
		hql.append(" AND  (date(:startDate) - date(s.startDate) = 0)");
		hql.append(" AND s.person = :person");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("startDate", startDate);
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	public List<Events> findOwnEvents(Person person, List<String> tags,
			int start) {
		StringBuilder hql = new StringBuilder("select distinct s FROM Events s " +
				"left join s.tags t WHERE ");
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
		q.setFetchSize(50);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	public List<Events> findWorldEvents(Person person, Date startDate,
			List<String> tags, int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Events s " +
				"left join s.eventVisibilities n " +
				"left join s.tags t WHERE ");
		int index = appendTag(tags, hql);
		hql.append(" and (n.visibilityDomain in (SELECT DISTINCT p.visibilityDomain FROM PersonVisibilityDomain p WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) ");
		hql.append(" AND  (day(:startDate) - day(s.startDate)) = 0 ");
		hql.append(" AND s.person not in (SELECT DISTINCT f.friend FROM Friend f WHERE f.person = :person)");
		
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);

		q.setParameter("startDate", startDate);
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	public List<Events> findWorldEvents(Person person, List<String> tags,
			int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Events s " +
				"left join s.eventVisibilities n " +
				"left join s.tags t WHERE ");
		int index = appendTag(tags, hql);
		hql.append(" and (n.visibilityDomain in (SELECT DISTINCT p.visibilityDomain FROM PersonVisibilityDomain p WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) ");
		hql.append(" AND s.person not in (SELECT DISTINCT f.friend FROM Friend f WHERE f.person = :person)" +
				"and s.person != :person " +
				"AND s.active = true " +
				"ORDER BY s.enteredDate DESC");
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);

		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		} 
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	public List<Events> findWorldEvents(Person person, Date startDate,
			int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Events s " +
				"left join s.eventVisibilities n ");
		hql.append(" where (n.visibilityDomain in (" +
				"SELECT DISTINCT p.visibilityDomain " +
				"FROM PersonVisibilityDomain p " +
				"WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) ");
		hql.append(" AND  (day(:startDate) - day(s.startDate)) = 0 ");
		hql.append(" AND s.person not in (" +
				"SELECT DISTINCT f.friend " +
				"FROM Friend f " +
				"WHERE f.person = :person) " +
				"and s.person != :person ");	
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setParameter("startDate", startDate);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	public List<Events> findWorldEvents(Person person, int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Events s " +
				"left join s.eventVisibilities n ");
		hql.append(" where (n.visibilityDomain in (" +
				"SELECT DISTINCT p.visibilityDomain " +
				"FROM PersonVisibilityDomain p " +
				"WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) ");
		hql.append(" AND s.person not in (" +
				"SELECT DISTINCT f.friend " +
				"FROM Friend f " +
				"WHERE f.person = :person) " +
				"and s.person != :person ");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	public List<Events> findOneFriendsEvents(Person friend, Person person,
			Date startDate, List<String> tags, int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Events s " +
				"left join s.tags t " +
				"left join s.eventVisibilities n " +
				"WHERE ");
		int index = appendTag(tags, hql);
		hql.append(" and (n.visibilityDomain in (" +
				"SELECT DISTINCT p.visibilityDomain " +
				"FROM PersonVisibilityDomain p " +
				"WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) ");
		hql.append(" AND s.person in (" +
				"SELECT DISTINCT f.friend " +
				"FROM Friend f " +
				"WHERE f.friend = :friend " +
				"AND f.person = :person) ");
		hql.append(" AND  (date(:startDate) - date(s.startDate) = 0) ");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("friend", friend);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setParameter("startDate", startDate);
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	public List<Events> findOneFriendsEvents(Person friend, Person person,
			List<String> tags, int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Events s " +
				"left join s.tags t " +
				"left join s.eventVisibilities n " +
				"WHERE ");
		int index = appendTag(tags, hql);
		hql.append(" and (n.visibilityDomain in (" +
				"SELECT DISTINCT p.visibilityDomain " +
				"FROM PersonVisibilityDomain p " +
				"WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) ");
		hql.append(" AND s.person in (" +
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
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	public List<Events> findOneFriendsEvents(Person friend, Person person,
			Date startDate, int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Events s " +
				"left join s.eventVisibilities n " +
				"WHERE ");
		hql.append(" (n.visibilityDomain in (" +
				"SELECT DISTINCT p.visibilityDomain " +
				"FROM PersonVisibilityDomain p " +
				"WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) ");
		hql.append(" AND s.person in (" +
				"SELECT DISTINCT f.friend " +
				"FROM Friend f " +
				"WHERE f.friend = :friend " +
				"AND f.person = :person) ");
		hql.append(" AND  (date(:startDate) - date(s.startDate) = 0) ");
		hql.append(" AND s.active = true");
		hql.append(" ORDER BY s.enteredDate DESC");
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("friend", friend);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setParameter("startDate", startDate);

		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	public List<Events> findOneFriendsEvents(Person friend, Person person,
			int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Events s " +
				"left join s.eventVisibilities n " +
				"WHERE ");
		hql.append(" (n.visibilityDomain in (" +
				"SELECT DISTINCT p.visibilityDomain " +
				"FROM PersonVisibilityDomain p " +
				"WHERE p.person = :person )");
		hql.append(" OR n.visibilityDomain.visibility = :visibility) ");
		hql.append(" AND s.person in (" +
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
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	public List<Events> findAllFriendsEvents(Person person, Date startDate,
			List<String> tags, int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Events s " +
				"left join s.tags t" +
				"left join s.eventVisibilities n " +
				"WHERE ");
		int index = appendTag(tags, hql);
		hql.append(" and (n.visibilityDomain in (" +
				"SELECT DISTINCT p.visibilityDomain " +
				"FROM PersonVisibilityDomain p " +
				"WHERE p.person = :person ) " +
				"OR n.visibilityDomain.visibility = :visibility) " +
				"AND s.person = (" +
				"SELECT DISTINCT f.friend " +
				"FROM Friend f " +
				"WHERE f.person = :person) " +
				"AND (date(:startDate) - date(s.startDate) = 0) " +
				"AND s.active = true " +
				"ORDER BY s.enteredDate DESC");
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setFirstResult(start);
		q.setParameter("startDate", startDate);
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setCacheable(true);
		return q.list();
	}
	public List<Events> findAllFriendsEvents(Person person,
			List<String> tags, int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Events s " +
				"left join s.tags t" +
				"left join s.eventVisibilities n " +
				"WHERE ");
		int index = appendTag(tags, hql);
		hql.append(" and (n.visibilityDomain in (" +
				"SELECT DISTINCT p.visibilityDomain " +
				"FROM PersonVisibilityDomain p " +
				"WHERE p.person = :person ) " +
				"OR n.visibilityDomain.visibility = :visibility) " +
				"AND s.person = (" +
				"SELECT DISTINCT f.friend " +
				"FROM Friend f " +
				"WHERE f.person = :person) " +
				"AND s.active = true " +
				"ORDER BY s.enteredDate DESC");
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setFirstResult(start);
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setCacheable(true);
		return q.list();
	}
	public List<Events> findAllFriendsEvents(Person person, Date startDate,
			int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Events s " +
				"left join s.eventVisibilities n " +
				"WHERE ");
		hql.append(" and (n.visibilityDomain in (" +
				"SELECT DISTINCT p.visibilityDomain " +
				"FROM PersonVisibilityDomain p " +
				"WHERE p.person = :person ) " +
				"OR n.visibilityDomain.visibility = :visibility) " +
				"AND s.person = (" +
				"SELECT DISTINCT f.friend " +
				"FROM Friend f " +
				"WHERE f.person = :person) " +
				"AND (date(:startDate) - date(s.startDate) = 0) " +
				"AND s.active = true " +
				"ORDER BY s.enteredDate DESC");
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setFirstResult(start);
		q.setParameter("startDate", startDate);
		q.setCacheable(true);
		return q.list();
	}
	public List<Events> findAllFriendsEvents(Person person, int start) {
		StringBuilder hql = new StringBuilder("select distinct s " +
				"FROM Events s " +
				"left join s.eventVisibilities n " +
				"WHERE ");
		hql.append(" and (n.visibilityDomain in (" +
				"SELECT DISTINCT p.visibilityDomain " +
				"FROM PersonVisibilityDomain p " +
				"WHERE p.person = :person ) " +
				"OR n.visibilityDomain.visibility = :visibility) " +
				"AND s.person = (" +
				"SELECT DISTINCT f.friend " +
				"FROM Friend f " +
				"WHERE f.person = :person) " +
				"AND s.active = true " +
				"ORDER BY s.enteredDate DESC");
		
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	
}
