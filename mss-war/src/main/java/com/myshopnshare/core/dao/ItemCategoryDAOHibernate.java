package com.myshopnshare.core.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.domain.ItemCategory;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.CategoryType;
import com.myshopnshare.core.enums.VisibilityType;
@Repository("itemCategoryDAO")
public class ItemCategoryDAOHibernate extends
		GenericDAOHibernate<ItemCategory, Long> implements ItemCategoryDAO {
	
	public List<Item> findAllItemsFor(Person person, CategoryType category,
			int start) {
		StringBuilder hql = new StringBuilder(
				"SELECT i.item FROM ItemCategory i WHERE");
		hql.append(" i.item.person = :person AND");
		hql.append(" i.category = :categoryType AND i.item.active = true");
		Query q = getSession().createQuery(hql.toString());
		q.setFirstResult(start);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setParameter("categoryType", category);
		q.setParameter("person", person);
		q.setCacheable(true);
		return q.list();
	}

	
	public ItemCategory getItemCategoryForPerson(Person person, Long id){
		String hql = "select e FROM ItemCategory e WHERE e.person = :person and e.id = :id";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("id", id);
		q.setFirstResult(0);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return (ItemCategory) q.uniqueResult();		
	}

	/** OWN ItemCategoryS **/
	public List<ItemCategory> findOwnItemCategorys(Person person, int start) {
		StringBuilder hql = new StringBuilder();
		hql
				.append("FROM ItemCategory m " +
						"join fetch m.item i " +
						"WHERE m.person = :person " +
						"and m.active = true ");
		hql.append(" ORDER BY m.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setFirstResult(start);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	/** OWN ITEMS FILTER BY TAGS AND CATEGORY **/
	public List<ItemCategory> findOwnItemCategorys(Person person, List<String> tags,
			CategoryType category, int start) {
		StringBuilder hql = new StringBuilder(
				"SELECT distinct m FROM ItemCategory m left join m.tags t left join m.itemCategories i WHERE ");
		hql.append("i.category = :categoryType AND i.person = :person ");
		hql.append(" AND ");
		int index = appendTag(tags, hql);
		hql.append(" AND i.active = true ");
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
	public List<ItemCategory> findOwnItemCategorys(Person person, CategoryType category, int start) {
		StringBuilder hql = new StringBuilder(
				"FROM ItemCategory v ");
		hql.append(" WHERE v.category = :categoryType and v.person = :person");
		hql.append(" AND v.active = true ");
		hql.append(" ORDER BY v.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("categoryType", category);
		q.setFirstResult(start);
		q.setMaxResults(36);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	/** OWN ItemCategoryS FILTER BY TAGS **/
	public List<ItemCategory> findOwnItemCategorys(Person person, List<String> tags, int start) {
		StringBuilder hql = new StringBuilder(
				"select distinct i FROM ItemCategory i " +
				"left join i.item.tags t " +
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


	/* ALL FRIENDS: FILTERED BY TAGS */
	public List<ItemCategory> findFriendsItemCategorys(Person person, List<String> tags,
			int start) {
		StringBuilder hql = new StringBuilder(
				"select distinct m FROM ItemCategory m "
						+ "left join m.item.tags t "
						+ "left join m.item.itemVisibilities v "
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
	public List<ItemCategory> findFriendsItemCategorys(Person person, List<String> tags,
			int start, CategoryType category) {
		StringBuilder hql = new StringBuilder(
				"select distinct m FROM ItemCategory m "
						+ "left join m.item.tags t "
						+ "left join m.item.itemVisibilities v "
						+ "left join m.person.friends f "
						+ "where m.category = :categoryType "
						+ "and i.person = f.person and f.friend = :person and ");
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
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	/* ALL FRIENDS: FILTERED BY CATEGORY */
	public List<ItemCategory> findFriendsItemCategorys(Person person, int start,
			CategoryType category) {
		StringBuilder hql = new StringBuilder(
				"select distinct m FROM ItemCategory m "
				+ "left join m.item.itemVisibilities v "
				+ "left join m.person.friends f "
						+ "where m.category = :categoryType "
						+ "and i.person = f.person and f.friend = :person " +
								"and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
						+ "or v.visibilityDomain.visibility = :visibility) "
						+ "and m.active = true "
						+ "order by m, m.enteredDate desc");

		Query q = getSession().createQuery(hql.toString());
		q.setFirstResult(start);
		q.setMaxResults(36);
		q.setParameter("person", person);
		q.setParameter("categoryType", category);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	/* ALL FRIENDS NO FILTER */
	public List<ItemCategory> findFriendsItemCategorys(Person person, int start) {
		StringBuilder hql = new StringBuilder(
				"select distinct m FROM ItemCategory m "
				+ "left join m.item.itemVisibilities v "
				+ "left join m.person.friends f "
						+ "where m.person = f.person and f.friend = :person " +
						"and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
						+ "or v.visibilityDomain.visibility = :visibility) "
						+ "and m.active = true "
						+ "order by m, m.enteredDate desc");

		Query q = getSession().createQuery(hql.toString());
		q.setFirstResult(start);
		q.setMaxResults(36);
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	/** ONE FRIEND: Filtered BY TAGS AND CATEGORY **/
	public List<ItemCategory> findFriendItemCategorys(Person friend, CategoryType category,
			Person person, List<String> tags, int start) {
		StringBuilder hql = new StringBuilder(
				"select distinct m FROM ItemCategory m "
						+ "left join m.item.tags t "
						+ "left join m.item.itemVisibilities v " +
								"left join m.person.friends f "
						+ "where m.category = :categoryType "
						// category item belongs to the f.person and the f.person is friends with the person and f.person is = to the :friend
						+ "and m.person = f.person and f.friend = :person and f.person = :friend and ");
		int index = appendTag(tags, hql);
		hql
				.append(" and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
						+ "or v.visibilityDomain.visibility = :visibility) "
						+ "and m.active = true "
						+ "order by m, m.enteredDate desc");

		Query q = getSession().createQuery(hql.toString());
		q.setFirstResult(start);
		q.setMaxResults(36);
		q.setParameter("categoryType", category);
		q.setParameter("friend", friend);
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	/** ONE FRIEND FILTERED BY CATEGORY **/
	public List<ItemCategory> findFriendItemCategorys(Person friend, CategoryType category,
			Person person, int start) {
		StringBuilder hql = new StringBuilder(
				"select distinct m FROM ItemCategory m "
				+ "left join m.item.tags t "
				+ "left join m.item.itemVisibilities v " +
						"left join m.person.friends f "
				+ "where m.category = :categoryType "
				// category item belongs to the f.person and the f.person is friends with the person and f.person is = to the :friend
				+ "and m.person = f.person and f.friend = :person and f.person = :friend "
						+ "and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
						+ "or v.visibilityDomain.visibility = :visibility) "
						+ "and m.active = true "
						+ "order by m, m.enteredDate desc");

		Query q = getSession().createQuery(hql.toString());
		q.setFirstResult(start);
		q.setMaxResults(36);
		q.setParameter("categoryType", category);
		q.setParameter("friend", friend);
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	/** ONE FRIEND FILTERED BY TAGS **/
	public List<ItemCategory> findFriendItemCategorys(Person friend, Person person,
			List<String> tags, int start) {
		StringBuilder hql = new StringBuilder(
				"select distinct m FROM ItemCategory m "
				+ "left join m.item.tags t "
				+ "left join m.item.itemVisibilities v " +
						"left join m.person.friends f "
				+ "where m.category = :categoryType "
				// category item belongs to the f.person and the f.person is friends with the person and f.person is = to the :friend
				+ "and m.person = f.person and f.friend = :person and f.person = :friend and ");
		int index = appendTag(tags, hql);
		hql
				.append(" and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
						+ "or v.visibilityDomain.visibility = :visibility) "
						+ "and m.active = true "
						+ "order by m, m.enteredDate desc");

		Query q = getSession().createQuery(hql.toString());
		q.setFirstResult(start);
		q.setMaxResults(36);
		q.setParameter("friend", friend);
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}

	/** ONE FRIEND NO FILTER **/
	public List<ItemCategory> findFriendItemCategorys(Person friend, Person person, int start) {
		StringBuilder hql = new StringBuilder(
				"select distinct m FROM ItemCategory m "
				+ "left join m.item.itemVisibilities v " +
						"left join m.person.friends f "
				// category item belongs to the f.person and the f.person is friends with the person and f.person is = to the :friend
				+ "where m.person = f.person and f.friend = :person and f.person = :friend "
				+"and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
						+ "or v.visibilityDomain.visibility = :visibility) "
						+ "and m.active = true "
						+ "order by m, m.enteredDate desc");

		Query q = getSession().createQuery(hql.toString());
		q.setFirstResult(start);
		q.setMaxResults(36);
		q.setParameter("friend", friend);
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/** OWN ItemCategoryS **/
	public Long findOwnItemCategorysCount(Person person) {
		StringBuilder hql = new StringBuilder();
		hql
				.append("select count(m) FROM ItemCategory m " +
						"left join m.item i " +
						"WHERE m.person = :person " +
						"and m.active = true ");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	/** OWN ITEMS FILTER BY TAGS AND CATEGORY **/
	public Long findOwnItemCategorysCount(Person person, List<String> tags,
			CategoryType category) {
		
		StringBuilder hql = new StringBuilder();
		hql.append(
				"select count(m) FROM ItemCategory m " +
				"left join m.item i " +
				"left join m.item.tags t WHERE ");
		hql.append("m.category = :categoryType AND m.person = :person ");
		
		hql.append(" AND ");
		int index = appendTag(tags, hql);
		
		hql.append(" AND i.active = true ");
		
		//System.out.println("query str: " + hql);

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("categoryType", category);
		
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	/** OWN ITEMS FILTER BY CATEGORY **/
	public Long findOwnItemCategorysCount(Person person, CategoryType category) {
		StringBuilder hql = new StringBuilder(
				"select count(v) FROM ItemCategory v ");
		hql.append(" WHERE v.category = :categoryType and v.person = :person");
		hql.append(" AND v.active = true ");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("categoryType", category);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	/** OWN ItemCategoryS FILTER BY TAGS **/
	public Long findOwnItemCategorysCount(Person person, List<String> tags) {
		StringBuilder hql = new StringBuilder(
				"select count(i) FROM ItemCategory i " +
				"left join i.item.tags t " +
				"WHERE ");
		int index = appendTag(tags, hql);
		hql
				.append(" AND i.person = :person and i.active = true ");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}


	/* ALL FRIENDS: FILTERED BY TAGS */
	public Long findFriendsItemCategorysCount(Person person, List<String> tags
			) {
		StringBuilder hql = new StringBuilder(
				"select count(m) FROM ItemCategory m "
						+ "left join m.item.tags t "
						+ "left join m.item.itemVisibilities v "
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
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	/* ALL FRIENDS: FILTERED BY TAGS AND CATEGORY */
	public Long findFriendsItemCategorysCount(Person person, List<String> tags,
			CategoryType category) {
		StringBuilder hql = new StringBuilder(
				"select count(m) FROM ItemCategory m "
						+ "left join m.item.tags t "
						+ "left join m.item.itemVisibilities v "
						+ "left join m.person.friends f "
						+ "where m.category = :categoryType "
						+ "and f.friend = :person and ");
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
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	/* ALL FRIENDS: FILTERED BY CATEGORY */
	public Long findFriendsItemCategorysCount(Person person,
			CategoryType category) {
		StringBuilder hql = new StringBuilder(
				"select count(m) FROM ItemCategory m "
				+ "left join m.item.itemVisibilities v "
				+ "left join m.person.friends f "
						+ "where m.category = :categoryType "
						+ "and f.friend = :person " +
								"and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
						+ "or v.visibilityDomain.visibility = :visibility) "
						+ "and m.active = true ");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("categoryType", category);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	/* ALL FRIENDS NO FILTER */
	public Long findFriendsItemCategorysCount(Person person) {
		StringBuilder hql = new StringBuilder(
				"select count(m) FROM ItemCategory m "
				+ "left join m.item.itemVisibilities v "
				+ "left join m.person.friends f "
						+ "where m.person = f.person and f.friend = :person " +
						"and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
						+ "or v.visibilityDomain.visibility = :visibility) "
						+ "and m.active = true ");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	/** ONE FRIEND: Filtered BY TAGS AND CATEGORY **/
	public Long findFriendItemCategorysCount(Person friend, CategoryType category,
			Person person, List<String> tags) {
		StringBuilder hql = new StringBuilder(
				"select count(m) FROM ItemCategory m "
						+ "left join m.item.tags t "
						+ "left join m.item.itemVisibilities v " +
								"left join m.person.friends f "
						+ "where m.category = :categoryType "
						// category item belongs to the f.person and the f.person is friends with the person and f.person is = to the :friend
						+ "and m.person = f.person and f.friend = :person and f.person = :friend and ");
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
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	/** ONE FRIEND FILTERED BY CATEGORY **/
	public Long findFriendItemCategorysCount(Person friend, CategoryType category,
			Person person) {
		StringBuilder hql = new StringBuilder(
				"select count(m) FROM ItemCategory m "
				+ "left join m.item.tags t "
				+ "left join m.item.itemVisibilities v " +
						"left join m.person.friends f "
				+ "where m.category = :categoryType "
				// category item belongs to the f.person and the f.person is friends with the person and f.person is = to the :friend
				+ "and m.person = f.person and f.friend = :person and f.person = :friend "
						+ "and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
						+ "or v.visibilityDomain.visibility = :visibility) "
						+ "and m.active = true ");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("categoryType", category);
		q.setParameter("friend", friend);
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	/** ONE FRIEND FILTERED BY TAGS **/
	public Long findFriendItemCategorysCount(Person friend, Person person,
			List<String> tags) {
		StringBuilder hql = new StringBuilder(
				"select count(m) FROM ItemCategory m "
				+ "left join m.item.tags t "
				+ "left join m.item.itemVisibilities v " +
						"left join m.person.friends f "
				+ "where m.category = :categoryType "
				// category item belongs to the f.person and the f.person is friends with the person and f.person is = to the :friend
				+ "and m.person = f.person and f.friend = :person and f.person = :friend and ");
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
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	/** ONE FRIEND NO FILTER **/
	public Long findFriendItemCategorysCount(Person friend, Person person) {
		StringBuilder hql = new StringBuilder(
				"select count(m) FROM ItemCategory m "
				+ "left join m.item.itemVisibilities v " +
						"left join m.person.friends f "
				// category item belongs to the f.person and the f.person is friends with the person and f.person is = to the :friend
				+ "where m.person = f.person and f.friend = :person and f.person = :friend "
				+"and (v.visibilityDomain in (select distinct p.visibilityDomain from PersonVisibilityDomain p where p.person = :person) "
						+ "or v.visibilityDomain.visibility = :visibility) "
						+ "and m.active = true ");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("friend", friend);
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

}
