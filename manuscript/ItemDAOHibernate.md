# ItemDAOHibernate

```java
package com.myshopnshare.core.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.CategoryType;
import com.myshopnshare.core.enums.VisibilityType;
@Repository("itemDAO")
public class ItemDAOHibernate extends GenericDAOHibernate<Item, Long> implements
		ItemDAO {
	
	public Item getItemForPerson(Person person, Long id){
		String hql = "select e FROM Item e WHERE e.person = :person and e.id = :id";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("id", id);
		q.setCacheable(true);
		return (Item) q.uniqueResult();		
	}

	/** OWN ITEMS **/
	public List<Item> findOwnItems(Person person, int start) {
		StringBuilder hql = new StringBuilder();
		hql
				.append("SELECT distinct m FROM Item m WHERE m.person = :person and m.active = true ");
		hql.append(" ORDER BY m, m.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setFirstResult(start);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	/** OWN ITEMS FILTER BY TAGS AND CATEGORY **/
	public List<Item> findOwnItems(Person person, List<String> tags,
			CategoryType category, int start) {
		StringBuilder hql = new StringBuilder(
				"SELECT distinct m FROM Item m left join m.tags t left join m.itemCategories i WHERE ");
		hql.append("i.category = :categoryType AND m.person = :person ");
		hql.append(" AND ");
		int index = appendTag(tags, hql);
		hql.append(" AND m.active = true ");
		hql.append(" ORDER BY m, m.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("categoryType", category);
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setFirstResult(start);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	/** OWN ITEMS FILTER BY CATEGORY **/
	public List<Item> findOwnItems(Person person, CategoryType category, int start) {
		StringBuilder hql = new StringBuilder(
				"SELECT distinct v FROM Item v left join v.itemCategories i");
		hql.append(" WHERE i.category = :categoryType and v.person = :person");
		hql.append(" AND v.active = true ");
		hql.append(" ORDER BY v, v.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("categoryType", category);
		q.setFirstResult(start);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	/** OWN ITEMS FILTER BY TAGS **/
	public List<Item> findOwnItems(Person person, List<String> tags, int start) {
		StringBuilder hql = new StringBuilder(
				"select distinct i FROM Item i " +
				"left join i.tags t " +
				"WHERE ");
		int index = appendTag(tags, hql);
		hql
				.append(" AND i.person = :person and i.active = true order by i, i.enteredDate DESC ");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setFirstResult(start);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	/** WORLD ITEMS **/
	public List<Item> findWorldItems(Person person, int start) {
		String hql = "select distinct m FROM Item m "
				+ "left join m.itemVisibilities v "
				+ "where m.person != :person "
				+ "and m.person not in (select distinct f.friend FROM Friend f where f.person = :person) "
				+ "and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
				+ "or v.visibilityDomain.visibility = :visibility) "
				+ "and m.active = true " 
				+ "order by m, m.enteredDate desc";

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setFirstResult(start);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	/** WORLD ITEMS FILTERED BY CATEGORY **/
	public List<Item> findWorldItems(Person person, CategoryType category, int start) {
		String hql = "select distinct m FROM Item m "
				+ "left join m.itemCategories i "
				+ "left join m.itemVisibilities v "
				+ "where i.category = :categoryType "
				+ "and i.person != :person "
				+ "and i.person not in (select distinct f.friend FROM Friend f where f.person = :person) "
				+ "and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
				+ "or v.visibilityDomain.visibility = :visibility) "
				+ "and m.active = true " 
				+ "order by m, m.enteredDate desc";

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setParameter("categoryType", category);
		q.setFirstResult(start);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	/**
	 * WORLD ITEMS: FILTERED BY TAGS AND CATEGORY
	 ***/
	public List<Item> findWorldItems(Person person, List<String> tags,
			CategoryType category, int start) {
		StringBuilder hql = new StringBuilder(
				"select distinct m from Item m "
						+ "left join m.itemCategories i "
						+ "left join m.tags t "
						+ "left join m.itemVisibilities v "
						+ "where i.category = :categoryType "
						+ "and i.person != :person "
						+ "and i.person not in (select distinct f.friend FROM Friend f where f.person = :person) and ");
		int index = appendTag(tags, hql);
		hql
				.append(" and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
						+ "or v.visibilityDomain.visibility = :visibility) "
						+ "and i.active = true "
						+ "order by m, m.enteredDate desc");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setParameter("categoryType", category);

		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}

		q.setFirstResult(start);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	/**
	 * WORLD ITEMS: FILTERED BY TAGS
	 ***/
	public List<Item> findWorldItems(Person person, List<String> tags, int start) {
		StringBuilder hql = new StringBuilder(
				"select distinct m FROM Item m "
						+ "left join m.tags t "
						+ "left join m.itemVisibilities v "
						+ "where m.person != :person "
						+ "and m.person not in (select distinct f.friend FROM Friend f where f.person = :person) and ");
		int index = appendTag(tags, hql);
		hql
				.append(" and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
						+ "or v.visibilityDomain.visibility = :visibility) "
						+ "and m.active = true "
						+ "order by m, m.enteredDate desc");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);

		// If the user is in personVisibilityDomaiin, it is a custom group and
		// the user already has access to the item just by being in the
		// visibility domain list.
		q.setParameter("visibility", VisibilityType.PUBLIC);

		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}

		q.setFirstResult(start);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	/* ALL FRIENDS: FILTERED BY TAGS */
	public List<Item> findFriendsItems(Person person, List<String> tags,
			int start) {
		StringBuilder hql = new StringBuilder(
				"select distinct m FROM Item m "
						+ "left join m.tags t "
						+ "left join m.itemVisibilities v "
						+ "where m.person in (select distinct f.friend FROM Friend f where f.person = :person) and ");
		int index = appendTag(tags, hql);
		hql
				.append(" and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
						+ "or v.visibilityDomain.visibility = :visibility) "
						+ "and m.active = true "
						+ "order by m, m.enteredDate desc");

		Query q = getSession().createQuery(hql.toString());

		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setFirstResult(start);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	/* ALL FRIENDS: FILTERED BY TAGS AND CATEGORY */
	public List<Item> findFriendsItems(Person person, List<String> tags,
			int start, CategoryType category) {
		StringBuilder hql = new StringBuilder(
				"select distinct m FROM Item m "
						+ "left join m.itemCategories i "
						+ "left join m.tags t "
						+ "left join m.itemVisibilities v "
						+ "where i.category = :categoryType "
						+ "and i.person in (select distinct f.friend FROM Friend f where f.person = :person) and ");
		int index = appendTag(tags, hql);
		hql
				.append(" and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
						+ "or v.visibilityDomain.visibility = :visibility) "
						+ "and m.active = true "
						+ "order by m, m.enteredDate desc");

		Query q = getSession().createQuery(hql.toString());

		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setParameter("categoryType", category);
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setFirstResult(start);
		q.setMaxResults(36);
		q.setCacheable(true);
		return q.list();
	}

	/* ALL FRIENDS: FILTERED BY CATEGORY */
	public List<Item> findFriendsItems(Person person, int start,
			CategoryType category) {
		StringBuilder hql = new StringBuilder(
				"select distinct m FROM Item m "
						+ "left join m.itemCategories i "
						+ "left join m.itemVisibilities v "
						+ "where i.category = :categoryType "
						+ "and i.person in (select distinct f.friend FROM Friend f where f.person = :person) ");
		hql
				.append(" and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
						+ "or v.visibilityDomain.visibility = :visibility) "
						+ "and m.active = true "
						+ "order by m, m.enteredDate desc");

		Query q = getSession().createQuery(hql.toString());

		q.setParameter("person", person);
		q.setParameter("categoryType", category);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setFirstResult(start);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	/* ALL FRIENDS NO FILTER */
	public List<Item> findFriendsItems(Person person, int start) {
		StringBuilder hql = new StringBuilder(
				"select distinct m FROM Item m "
						+ "left join m.itemVisibilities v "
						+ "where m.person in (select distinct f.friend FROM Friend f where f.person = :person) ");
		hql
				.append(" and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
						+ "or v.visibilityDomain.visibility = :visibility) "
						+ "and m.active = true "
						+ "order by m, m.enteredDate desc");

		Query q = getSession().createQuery(hql.toString());

		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setFirstResult(start);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	/** ONE FRIEND: Filtered BY TAGS AND CATEGORY **/
	public List<Item> findFriendItems(Person friend, CategoryType category,
			Person person, List<String> tags, int start) {
		StringBuilder hql = new StringBuilder(
				"select distinct m FROM Item m "
						+ "left join m.itemCategories i "
						+ "left join m.tags t "
						+ "left join m.itemVisibilities v "
						+ "where i.category = :categoryType "
						+ "and i.person in (SELECT distinct f.friend FROM Friend f WHERE f.friend = :friend AND f.person = :person) and ");
		int index = appendTag(tags, hql);
		hql
				.append(" and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
						+ "or v.visibilityDomain.visibility = :visibility) "
						+ "and m.active = true "
						+ "order by m, m.enteredDate desc");

		Query q = getSession().createQuery(hql.toString());

		q.setParameter("categoryType", category);
		q.setParameter("friend", friend);
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setFirstResult(start);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	/** ONE FRIEND FILTERED BY CATEGORY **/
	public List<Item> findFriendItems(Person friend, CategoryType category,
			Person person, int start) {
		StringBuilder hql = new StringBuilder(
				"select distinct m FROM Item m "
						+ "left join m.itemCategories i "
						+ "left join m.itemVisibilities v "
						+ "where i.category = :categoryType "
						+ "and i.person in (SELECT distinct f.friend FROM Friend f WHERE f.friend = :friend AND f.person = :person) "
						+ "and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
						+ "or v.visibilityDomain.visibility = :visibility) "
						+ "and m.active = true "
						+ "order by m, m.enteredDate desc");

		Query q = getSession().createQuery(hql.toString());

		q.setParameter("categoryType", category);
		q.setParameter("friend", friend);
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setFirstResult(start);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	/** ONE FRIEND FILTERED BY TAGS **/
	public List<Item> findFriendItems(Person friend, Person person,
			List<String> tags, int start) {
		StringBuilder hql = new StringBuilder(
				"select distinct m FROM Item m "
						+ "left join m.tags t "
						+ "left join m.itemVisibilities v "
						+ "where m.person in (SELECT distinct f.friend FROM Friend f WHERE f.friend = :friend AND f.person = :person) and ");
		int index = appendTag(tags, hql);
		hql
				.append(" and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
						+ "or v.visibilityDomain.visibility = :visibility) "
						+ "and m.active = true "
						+ "order by m, m.enteredDate desc");

		Query q = getSession().createQuery(hql.toString());

		q.setParameter("friend", friend);
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setFirstResult(start);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	/** ONE FRIEND NO FILTER **/
	public List<Item> findFriendItems(Person friend, Person person, int start) {
		StringBuilder hql = new StringBuilder(
				"select distinct m FROM Item m "
						+ "left join m.itemVisibilities v "
						+ "where m.person in (SELECT distinct f.friend FROM Friend f WHERE f.friend = :friend AND f.person = :person) ");
		hql
				.append("and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
						+ "or v.visibilityDomain.visibility = :visibility) "
						+ "and m.active = true "
						+ "order by m, m.enteredDate desc");

		Query q = getSession().createQuery(hql.toString());

		q.setParameter("friend", friend);
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setFirstResult(start);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}
	
	/** OWN ITEMS **/
	public Long findOwnItemsCount(Person person) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT count(m) FROM Item m left join m.itemCategories ic WHERE ic.person = :person and ic.active = true ");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setFirstResult(0);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	/** OWN ITEMS FILTER BY TAGS AND CATEGORY **/
	public Long findOwnItemsCount(Person person, List<String> tags,
			CategoryType category) {
		StringBuilder hql = new StringBuilder(
				"SELECT count(m) FROM Item m left join m.tags t left join m.itemCategories i WHERE ");
		hql.append("i.category = :categoryType AND i.person = :person ");
		hql.append(" AND ");
		int index = appendTag(tags, hql);
		hql.append(" AND i.active = true ");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("categoryType", category);
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setFirstResult(0);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	/** OWN ITEMS FILTER BY CATEGORY **/
	public Long findOwnItemsCount(Person person, CategoryType category) {
		StringBuilder hql = new StringBuilder(
				"SELECT count (v) FROM Item v left join v.itemCategories i");
		hql.append(" WHERE i.category = :categoryType and i.person = :person");
		hql.append(" AND i.active = true ");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("categoryType", category);
		q.setFirstResult(0);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	/** OWN ITEMS FILTER BY TAGS **/
	public Long findOwnItemsCount(Person person, List<String> tags) {
		StringBuilder hql = new StringBuilder(
				"select count (i) FROM Item i " +
				"left join i.tags t " +
				"left join i.itemCategories c WHERE ");
		int index = appendTag(tags, hql);
		hql
				.append(" AND c.person = :person and c.active = true ");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setFirstResult(0);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	/** WORLD ITEMS **/
	public Long findWorldItemsCount(Person person) {
		String hql = "select count(m) FROM Item m "
				+ "left join m.itemVisibilities v "
				+ "where m.person != :person "
				+ "and m.person not in (select distinct f.friend FROM Friend f where f.person = :person) "
				+ "and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
				+ "or v.visibilityDomain.visibility = :visibility) "
				+ "and m.active = true ";

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);

		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	/** WORLD ITEMS FILTERED BY CATEGORY **/
	public Long findWorldItemsCount(Person person, CategoryType category) {
		String hql = "select count(m) FROM Item m "
				+ "left join m.itemCategories i "
				+ "left join m.itemVisibilities v "
				+ "where i.category = :categoryType "
				+ "and i.person != :person "
				+ "and i.person not in (select distinct f.friend FROM Friend f where f.person = :person) "
				+ "and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
				+ "or v.visibilityDomain.visibility = :visibility) "
				+ "and m.active = true ";

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setParameter("categoryType", category);
		q.setFirstResult(0);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	/**
	 * WORLD ITEMS: FILTERED BY TAGS AND CATEGORY
	 ***/
	public Long findWorldItemsCount(Person person, List<String> tags,
			CategoryType category) {
		StringBuilder hql = new StringBuilder(
				"select count(m) from Item m "
						+ "left join m.itemCategories i "
						+ "left join m.tags t "
						+ "left join m.itemVisibilities v "
						+ "where i.category = :categoryType "
						+ "and i.person != :person "
						+ "and i.person not in (select distinct f.friend FROM Friend f where f.person = :person) and ");
		int index = appendTag(tags, hql);
		hql
				.append(" and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
						+ "or v.visibilityDomain.visibility = :visibility) "
						+ "and i.active = true ");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setParameter("categoryType", category);

		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setFirstResult(0);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	/**
	 * WORLD ITEMS: FILTERED BY TAGS
	 ***/
	public Long findWorldItemsCount(Person person, List<String> tags) {
		StringBuilder hql = new StringBuilder(
				"select count(m) FROM Item m "
						+ "left join m.tags t "
						+ "left join m.itemVisibilities v "
						+ "where m.person != :person "
						+ "and m.person not in (select distinct f.friend FROM Friend f where f.person = :person) and ");
		int index = appendTag(tags, hql);
		hql.append(" and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
						+ "or v.visibilityDomain.visibility = :visibility) "
						+ "and m.active = true ");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);

		// If the user is in personVisibilityDomaiin, it is a custom group and
		// the user already has access to the item just by being in the
		// visibility domain list.
		q.setParameter("visibility", VisibilityType.PUBLIC);

		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setFirstResult(0);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	/* ALL FRIENDS: FILTERED BY TAGS */
	public Long findFriendsItemsCount(Person person, List<String> tags
			) {
		StringBuilder hql = new StringBuilder(
				"select count(m) FROM Item m "
						+ "left join m.tags t "
						+ "left join m.itemVisibilities v "
						+ "where m.person in (select distinct f.friend FROM Friend f where f.person = :person) and ");
		int index = appendTag(tags, hql);
		hql
				.append(" and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
						+ "or v.visibilityDomain.visibility = :visibility) "
						+ "and m.active = true ");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setFirstResult(0);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	/* ALL FRIENDS: FILTERED BY TAGS AND CATEGORY */
	public Long findFriendsItemsCount(Person person, List<String> tags, CategoryType category) {
		StringBuilder hql = new StringBuilder(
				"select count(m) FROM Item m "
						+ "left join m.itemCategories i "
						+ "left join m.tags t "
						+ "left join m.itemVisibilities v "
						+ "where i.category = :categoryType "
						+ "and i.person in (select distinct f.friend FROM Friend f where f.person = :person) and ");
		int index = appendTag(tags, hql);
		hql
				.append(" and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
						+ "or v.visibilityDomain.visibility = :visibility) "
						+ "and m.active = true ");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setParameter("categoryType", category);
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setFirstResult(0);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	/* ALL FRIENDS: FILTERED BY CATEGORY */
	public Long findFriendsItemsCount(Person person,
			CategoryType category) {
		StringBuilder hql = new StringBuilder(
				"select count(m) FROM Item m "
						+ "left join m.itemCategories i "
						+ "left join m.itemVisibilities v "
						+ "where i.category = :categoryType "
						+ "and i.person in (select distinct f.friend FROM Friend f where f.person = :person) ");
		hql
				.append(" and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
						+ "or v.visibilityDomain.visibility = :visibility) "
						+ "and m.active = true ");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("categoryType", category);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setFirstResult(0);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	/* ALL FRIENDS NO FILTER */
	public Long findFriendsItemsCount(Person person) {
		StringBuilder hql = new StringBuilder(
				"select count(m) FROM Item m "
						+ "left join m.itemVisibilities v "
						+ "where m.person in (select distinct f.friend FROM Friend f where f.person = :person) ");
		hql
				.append(" and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
						+ "or v.visibilityDomain.visibility = :visibility) "
						+ "and m.active = true ");

		Query q = getSession().createQuery(hql.toString());

		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setFirstResult(0);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	/** ONE FRIEND: Filtered BY TAGS AND CATEGORY **/
	public Long findFriendItemsCount(Person friend, CategoryType category,
			Person person, List<String> tags) {
		StringBuilder hql = new StringBuilder(
				"select count(m) FROM Item m "
						+ "left join m.itemCategories i "
						+ "left join m.tags t "
						+ "left join m.itemVisibilities v "
						+ "where i.category = :categoryType "
						+ "and i.person in (SELECT distinct f.friend FROM Friend f WHERE f.friend = :friend AND f.person = :person) and ");
		int index = appendTag(tags, hql);
		hql
				.append(" and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
						+ "or v.visibilityDomain.visibility = :visibility) "
						+ "and m.active = true ");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("categoryType", category);
		q.setParameter("friend", friend);
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setFirstResult(0);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	/** ONE FRIEND FILTERED BY CATEGORY **/
	public Long findFriendItemsCount(Person friend, CategoryType category,
			Person person) {
		StringBuilder hql = new StringBuilder(
				"select count(m) FROM Item m "
						+ "left join m.itemCategories i "
						+ "left join m.itemVisibilities v "
						+ "where i.category = :categoryType "
						+ "and i.person in (SELECT distinct f.friend FROM Friend f WHERE f.friend = :friend AND f.person = :person) "
						+ "and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
						+ "or v.visibilityDomain.visibility = :visibility) "
						+ "and m.active = true ");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("categoryType", category);
		q.setParameter("friend", friend);
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setFirstResult(0);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	/** ONE FRIEND FILTERED BY TAGS **/
	public Long findFriendItemsCount(Person friend, Person person,
			List<String> tags) {
		StringBuilder hql = new StringBuilder(
				"select count(m) FROM Item m "
						+ "left join m.tags t "
						+ "left join m.itemVisibilities v "
						+ "where m.person in (SELECT distinct f.friend FROM Friend f WHERE f.friend = :friend AND f.person = :person) and ");
		int index = appendTag(tags, hql);
		hql
				.append(" and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
						+ "or v.visibilityDomain.visibility = :visibility) "
						+ "and m.active = true ");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("friend", friend);
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setFirstResult(0);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	/** ONE FRIEND NO FILTER **/
	public Long findFriendItemsCount(Person friend, Person person) {
		StringBuilder hql = new StringBuilder(
				"select count(m) FROM Item m "
						+ "left join m.itemVisibilities v "
						+ "where m.person in (SELECT distinct f.friend FROM Friend f WHERE f.friend = :friend AND f.person = :person) ");
		hql
				.append("and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
						+ "or v.visibilityDomain.visibility = :visibility) "
						+ "and m.active = true ");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("friend", friend);
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setFirstResult(0);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

}
```
