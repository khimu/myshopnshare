# VendorItemVisibilityDomainDAOHibernate

```java
package com.myshopnshare.core.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.VendorItem;
import com.myshopnshare.core.domain.VendorItemVisibilityDomain;
import com.myshopnshare.core.enums.CategoryType;
import com.myshopnshare.core.enums.VisibilityType;
@Repository("vendorVisibilityDomainDAO")
public class VendorItemVisibilityDomainDAOHibernate extends GenericDAOHibernate<VendorItemVisibilityDomain, Long> implements VendorItemVisibilityDomainDAO{
	
	/** Find all of your items **/
	public List<VendorItem> findAllVisibleItemsFor(Person person){
		// [ v.visibilityDomain.id = p.visibilityDomain.id ] - [ find the visibility of item for person ]
		// [ v.visibilityDomain.visibility = :visibility ]   - [ Public item ]
		// [ p.person  = :person ] - [ person has visibility to item ]
		// [ v.item.person != :person ] - [ the item is not from the person ]
		String hql = "SELECT v.item FROM VendorItemVisibilityDomain v, PersonVisibilityDomain p WHERE (v.visibilityDomain.id = p.visibilityDomain.id OR v.visibilityDomain.visibility = :visibility) AND p.person  = :person AND v.item.person != :person ORDER BY v.item.enteredDate DESC";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		// If the user is in personVisibilityDomaiin, it is a custom group and the user already has access to the item just by being in the visibility domain list.
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setCacheable(true);
		q.setMaxResults(50);
		q.setFirstResult(0);
		q.setFetchSize(50);
		return q.list();
	}

	/** This is all public items. Same as method above **/
	public List<VendorItem> findAllVisibleItemsFor(Person person, int start){
		// [ v.visibilityDomain.id = p.visibilityDomain.id ] - [ find the visibility of item for person ]
		// [ v.visibilityDomain.visibility = :visibility ]   - [ Public item ]
		// [ p.person  = :person ] - [ person has visibility to item ]
		// [ v.item.person != :person ] - [ the item is not from the person ]
		String hql = "SELECT v.item FROM VendorItemVisibilityDomain v, PersonVisibilityDomain p WHERE (v.visibilityDomain.id = p.visibilityDomain.id OR v.visibilityDomain.visibility = :visibility) AND p.person  = :person AND v.item.person != :person ORDER BY v.item.enteredDate ASC";
		Query q = getSession().createQuery(hql);
		q.setFirstResult(start);
		q.setMaxResults(20);
		q.setParameter("person", person);
		// If the user is in personVisibilityDomaiin, it is a custom group and the user already has access to the item just by being in the visibility domain list.
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setCacheable(true);
		q.setMaxResults(50);
		q.setFirstResult(0);
		q.setFetchSize(50);
		return q.list();
	}

	/** This is all your friends' items **/
	// Extra step, make sure that person is friend's with item owner
	public List<VendorItem> findAllFriendVisibleItemsFor(Person person, int start){
		// [ f.person = :person ] - [ person logged in is friends with item owner ]
		// [ f.friend = v.item.person ] - [ the person who owns the item ]
		// [ v.visibilityDomain = p.visibilityDomain ] - [ check if person has access to item ]
		// [ p.person = :person ] - [ person has to be in the PersonVisibilityDomain to have access]
		StringBuilder hql = new StringBuilder("SELECT v.item FROM Friend f, VendorItemVisibilityDomain v, PersonVisibilityDomain p WHERE");
		hql.append(" f.friend = v.item.person AND ");
		hql.append(" f.person = :person AND ");
		hql.append(" (v.visibilityDomain = p.visibilityDomain OR v.visibilityDomain.visibility = :visibility) AND ");
		hql.append(" p.person = :person ");
		hql.append(" ORDER BY v.item.enteredDate ASC");
		Query q = getSession().createQuery(hql.toString());
		q.setFirstResult(start);
		q.setMaxResults(20);
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setCacheable(true);
		q.setMaxResults(50);
		q.setFirstResult(0);
		q.setFetchSize(50);
		return q.list();		
	}

	/** This is 1 particular friend's items **/
	public List<VendorItem> findFriendsVisibleItems(Person friend, CategoryType category, Person person, int start){
		StringBuilder hql = new StringBuilder("SELECT v.item FROM ItemCategory i, Friend f, VendorItemsVisibilityDomain v, PersonVisibilityDomain p WHERE");
		hql.append(" f.friend = v.item.person AND ");
		hql.append(" i.item = v.item AND ");
		hql.append(" i.category.type = :categoryType ");
		hql.append(" f.friend = :friend");
		hql.append(" f.person = :person AND ");
		hql.append(" (v.visibilityDomain = p.visibilityDomain OR v.visibilityDomain.visibility = :visibility) AND ");
		hql.append(" p.person = :person ");
		hql.append(" ORDER BY v.item.enteredDate ASC");
		Query q = getSession().createQuery(hql.toString());
		//q.setFirstResult(start);
		//q.setMaxResults(20);
		q.setParameter("categoryType", category);
		q.setParameter("friend", friend);
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setCacheable(true);
		q.setMaxResults(50);
		q.setFirstResult(0);
		q.setFetchSize(50);
		return q.list();		
	}

	/** This is 1 particular friend's items **/
	public List<VendorItem> findFriendsVisibleItems(Person friend, Person person, int start){
		StringBuilder hql = new StringBuilder("SELECT v.item FROM Friend f, VendorItemsVisibilityDomain v, PersonVisibilityDomain p WHERE");
		hql.append(" f.friend = v.item.person AND ");
		hql.append(" i.item = v.item AND ");
		hql.append(" f.friend = :friend");
		hql.append(" f.person = :person AND ");
		hql.append(" (v.visibilityDomain = p.visibilityDomain OR v.visibilityDomain.visibility = :visibility) AND ");
		hql.append(" p.person = :person ");
		hql.append(" ORDER BY v.item.enteredDate ASC");
		Query q = getSession().createQuery(hql.toString());
		//q.setFirstResult(start);
		//q.setMaxResults(20);
		q.setParameter("friend", friend);
		q.setParameter("person", person);
		q.setParameter("visibility", VisibilityType.PUBLIC);
		q.setCacheable(true);
		q.setMaxResults(50);
		q.setFirstResult(0);
		q.setFetchSize(50);
		return q.list();		
	}

}
```
