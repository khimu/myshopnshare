package com.myshopnshare.core.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.domain.ItemVisibilityDomain;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.CategoryType;
import com.myshopnshare.core.enums.VisibilityType;
@Repository("itemVisibilityDomainDAO")
public class ItemVisibilityDomainDAOHibernate extends
		GenericDAOHibernate<ItemVisibilityDomain, Long> implements
		ItemVisibilityDomainDAO {

	/** OWN ITEMS **/
	public List<Item> findOwnItems(Person person) {
		StringBuilder hql = new StringBuilder(
				"SELECT v.item FROM ItemVisibilityDomain v WHERE");
		hql.append(" v.item.person = :person ");
		hql.append(" v.item.active = true ");
		hql.append(" ORDER BY v.item.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setFirstResult(0);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	/** OWN ITEMS FILTER BY TAGS AND CATEGORY **/
	public List<Item> findOwnItems(Person person, List<String> tags, CategoryType category) {
		StringBuilder hql = new StringBuilder(
				"SELECT v.item FROM ItemCategory i, ItemVisibilityDomain v, ItemTag t WHERE");
		hql.append(" v.item.person = :person ");
		hql.append(" i.item = v.item AND ");
		hql.append(" i.category.type = :categoryType ");
		hql.append(" AND v.item = t.item AND ");
		int index = appendTag(tags, hql);
		hql.append(" v.item.active = true ");
		hql.append(" ORDER BY v.item.enteredDate DESC");

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
		return q.list();
	}

	/** OWN ITEMS FILTER BY CATEGORY **/
	public List<Item> findOwnItems(Person person, CategoryType category) {
		StringBuilder hql = new StringBuilder(
				"SELECT v.item FROM ItemCategory i, ItemVisibilityDomain v WHERE");
		hql.append(" v.item.person = :person ");
		hql.append(" i.item = v.item AND ");
		hql.append(" i.category.type = :categoryType ");
		hql.append(" v.item.active = true ");
		hql.append(" ORDER BY v.item.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setParameter("categoryType", category);
		q.setFirstResult(0);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}
	
	/** OWN ITEMS FILTER BY TAGS **/
	public List<Item> findOwnItems(Person person, List<String> tags) {
		StringBuilder hql = new StringBuilder(
				"SELECT v.item FROM ItemVisibilityDomain v, ItemTag t WHERE");
		hql.append(" v.item.person = :person ");
		hql.append(" AND v.item = t.item AND ");
		int index = appendTag(tags, hql);
		hql.append(" v.item.active = true ");
		hql.append(" ORDER BY v.item.enteredDate DESC");

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
		return q.list();
	}
	
	/** WORLD ITEMS **/
	public List<Item> findWorldItems(Person person) {
		StringBuilder hql = new StringBuilder(
				"SELECT v.item FROM ItemVisibilityDomain v WHERE");
		hql.append(" v.item.person != :person ");
		hql.append(" AND (v.visibilityDomain = (SELECT p.visibilityDomain FROM PersonVisibilityDomain p WHERE p.person = :person)");
		hql.append(" OR v.visibilityDomain.visibility = :visibility) ");
		hql.append(" ORDER BY v.item.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setFirstResult(0);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}
	
	/** WORLD ITEMS FILTERED BY CATEGORY **/
	public List<Item> findWorldItems(Person person, CategoryType category) {
		StringBuilder hql = new StringBuilder(
				"SELECT v.item FROM ItemCategory i, ItemVisibilityDomain v WHERE");
		hql.append(" v.item.person != :person ");
		hql.append(" i.item = v.item AND ");
		hql.append(" i.category.type = :categoryType ");
		hql.append(" AND (v.visibilityDomain = (SELECT p.visibilityDomain FROM PersonVisibilityDomain p WHERE p.person = :person)");
		hql.append(" OR v.visibilityDomain.visibility = :visibility) ");
		hql.append(" ORDER BY v.item.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setParameter("categoryType", category);
		q.setFirstResult(0);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	/**
	 * WORLD ITEMS: FILTERED BY TAGS AND CATEGORY
	 ***/
	public List<Item> findWorldItems(Person person, List<String> tags, CategoryType category) {
		StringBuilder hql = new StringBuilder("SELECT v.item FROM ItemCategory i, ItemVisibilityDomain v, ItemTag t WHERE");
		hql.append(" v.item.person != :person ");
		hql.append(" i.item = v.item AND ");
		hql.append(" i.category.type = :categoryType ");
		hql.append(" AND v.item = t.item AND ");
		int index = appendTag(tags, hql);
		hql.append(" AND (v.visibilityDomain = (SELECT p.visibilityDomain FROM PersonVisibilityDomain p WHERE p.person = :person)");
		hql.append(" OR v.visibilityDomain.visibility = :visibility) ");
		hql.append(" ORDER BY v.item.enteredDate DESC");

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
		return q.list();
	}

	
	/**
	 * WORLD ITEMS: FILTERED BY TAGS
	 ***/
	public List<Item> findWorldItems(Person person, List<String> tags) {
		StringBuilder hql = new StringBuilder("SELECT v.item FROM ItemVisibilityDomain v, ItemTag t WHERE");
		hql.append(" v.item.person != :person ");
		hql.append(" AND v.item = t.item AND ");
		int index = appendTag(tags, hql);
		hql.append(" AND (v.visibilityDomain = (SELECT p.visibilityDomain FROM PersonVisibilityDomain p WHERE p.person = :person)");
		hql.append(" OR v.visibilityDomain.visibility = :visibility) ");
		hql.append(" ORDER BY v.item.enteredDate DESC");

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
		return q.list();
	}

	/* ALL FRIENDS: FILTERED BY TAGS */
	public List<Item> findFriendsItems(Person person,
			List<String> tags, int start) {
		StringBuilder hql = new StringBuilder(
				"SELECT v.item FROM ItemVisibilityDomain v, ItemTag t, Friend f WHERE");
		hql.append(" f.friend = v.item.person AND ");
		hql.append(" f.person = :person AND ");
		hql.append(" AND v.item = t.item AND ");
		int index = appendTag(tags, hql);
		hql.append(" AND (v.visibilityDomain = (SELECT p.visibilityDomain FROM PersonVisibilityDomain p WHERE p.person = :person)");
		hql.append(" OR v.visibilityDomain.visibility = :visibility) ");
		hql.append(" ORDER BY v.item.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		q.setFirstResult(start);
		q.setMaxResults(20);
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setFirstResult(0);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	/* ALL FRIENDS: FILTERED BY TAGS AND CATEGORY */
	public List<Item> findFriendsItems(Person person,
			List<String> tags, int start, CategoryType category) {
		StringBuilder hql = new StringBuilder(
				"SELECT v.item FROM ItemVisibilityDomain v, ItemTag t, Friend f WHERE");
		hql.append(" f.friend = v.item.person AND ");
		hql.append(" f.person = :person AND ");
		hql.append(" i.item = v.item AND ");
		hql.append(" i.category.type = :categoryType ");
		hql.append(" AND v.item = t.item AND ");
		int index = appendTag(tags, hql);
		hql.append(" AND (v.visibilityDomain = (SELECT p.visibilityDomain FROM PersonVisibilityDomain p WHERE p.person = :person)");
		hql.append(" OR v.visibilityDomain.visibility = :visibility) ");
		hql.append(" ORDER BY v.item.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		q.setFirstResult(start);
		q.setMaxResults(20);
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
		return q.list();
	}

	/* ALL FRIENDS: FILTERED BY CATEGORY */
	public List<Item> findFriendsItems(Person person, int start, CategoryType category) {
		StringBuilder hql = new StringBuilder(
				"SELECT v.item FROM ItemVisibilityDomain v, ItemTag t, Friend f WHERE");
		hql.append(" f.friend = v.item.person AND ");
		hql.append(" f.person = :person AND ");
		hql.append(" i.item = v.item AND ");
		hql.append(" i.category.type = :categoryType ");
		hql.append(" AND (v.visibilityDomain = (SELECT p.visibilityDomain FROM PersonVisibilityDomain p WHERE p.person = :person)");
		hql.append(" OR v.visibilityDomain.visibility = :visibility) ");
		hql.append(" ORDER BY v.item.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		q.setFirstResult(start);
		q.setMaxResults(20);
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setParameter("categoryType", category);
		q.setFirstResult(0);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	/* ALL FRIENDS NO FILTER */
	public List<Item> findFriendsItems(Person person, int start) {
		StringBuilder hql = new StringBuilder(
				"SELECT v.item FROM ItemVisibilityDomain v, Friend f WHERE");
		hql.append(" f.friend = v.item.person AND ");
		hql.append(" f.person = :person AND ");
		hql.append(" AND (v.visibilityDomain = (SELECT p.visibilityDomain FROM PersonVisibilityDomain p WHERE p.person = :person)");
		hql.append(" OR v.visibilityDomain.visibility = :visibility) ");
		hql.append(" ORDER BY v.item.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		q.setFirstResult(start);
		q.setMaxResults(20);
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setFirstResult(0);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	/** ONE FRIEND: Filtered BY TAGS AND CATEGORY **/
	public List<Item> findFriendItems(Person friend,
			CategoryType category, Person person, List<String> tags, int start) {
		StringBuilder hql = new StringBuilder("SELECT v.item FROM ItemCategory i, ItemVisibilityDomain v, ItemTag t, Friend f WHERE");
		hql.append(" f.friend = v.item.person AND ");
		hql.append(" f.friend = :friend");
		hql.append(" f.person = :person AND ");
		hql.append(" i.item = v.item AND ");
		hql.append(" i.category.type = :categoryType ");
		hql.append(" AND v.item = t.item AND ");
		int index = appendTag(tags, hql);
		hql.append(" AND (v.visibilityDomain = (SELECT p.visibilityDomain FROM PersonVisibilityDomain p WHERE p.person = :person)");
		hql.append(" OR v.visibilityDomain.visibility = :visibility) ");
		hql.append(" ORDER BY v.item.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		// q.setFirstResult(start);
		// q.setMaxResults(20);
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
		return q.list();
	}

	/** ONE FRIEND FILTERED BY CATEGORY **/
	public List<Item> findFriendItems(Person friend,
			CategoryType category, Person person, int start) {
		StringBuilder hql = new StringBuilder("SELECT v.item FROM ItemCategory i, ItemVisibilityDomain v, ItemTag t, Friend f WHERE");
		hql.append(" f.friend = v.item.person AND ");
		hql.append(" f.friend = :friend");
		hql.append(" f.person = :person AND ");
		hql.append(" i.item = v.item AND ");
		hql.append(" i.category.type = :categoryType ");
		hql.append(" AND (v.visibilityDomain = (SELECT p.visibilityDomain FROM PersonVisibilityDomain p WHERE p.person = :person)");
		hql.append(" OR v.visibilityDomain.visibility = :visibility) ");
		hql.append(" ORDER BY v.item.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		// q.setFirstResult(start);
		// q.setMaxResults(20);
		q.setParameter("categoryType", category);
		q.setParameter("friend", friend);
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setFirstResult(0);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	/** ONE FRIEND FILTERED BY TAGS **/
	public List<Item> findFriendItems(Person friend, Person person, List<String> tags, int start) {
		StringBuilder hql = new StringBuilder("SELECT v.item FROM ItemVisibilityDomain v, ItemTag t, Friend f WHERE");
		hql.append(" f.friend = v.item.person AND ");
		hql.append(" f.friend = :friend");
		hql.append(" f.person = :person AND ");
		hql.append(" AND v.item = t.item AND ");
		int index = appendTag(tags, hql);
		hql.append(" AND (v.visibilityDomain = (SELECT p.visibilityDomain FROM PersonVisibilityDomain p WHERE p.person = :person)");
		hql.append(" OR v.visibilityDomain.visibility = :visibility) ");
		hql.append(" ORDER BY v.item.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		// q.setFirstResult(start);
		// q.setMaxResults(20);
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
		return q.list();
	}

	/** ONE FRIEND NO FILTER **/
	public List<Item> findFriendItems(Person friend, Person person, int start) {
		StringBuilder hql = new StringBuilder("SELECT v.item FROM ItemVisibilityDomain v, Friend f WHERE");
		hql.append(" f.friend = v.item.person AND ");
		hql.append(" f.friend = :friend");
		hql.append(" f.person = :person AND ");
		hql.append(" AND (v.visibilityDomain = (SELECT p.visibilityDomain FROM PersonVisibilityDomain p WHERE p.person = :person)");
		hql.append(" OR v.visibilityDomain.visibility = :visibility) ");
		hql.append(" ORDER BY v.item.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		// q.setFirstResult(start);
		// q.setMaxResults(20);
		q.setParameter("friend", friend);
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setFirstResult(0);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

}
