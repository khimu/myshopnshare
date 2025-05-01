# ItemVisibilityDomainDAO

```java
package com.myshopnshare.core.dao;

import java.util.List;

import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.domain.ItemVisibilityDomain;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.CategoryType;

public interface ItemVisibilityDomainDAO extends
		GenericDAO<ItemVisibilityDomain, Long> {
	
	/** OWN ITEMS **/
	public List<Item> findOwnItems(Person person);

	/** OWN ITEMS FILTER BY TAGS AND CATEGORY **/
	public List<Item> findOwnItems(Person person, List<String> tags, CategoryType category);

	/** OWN ITEMS FILTER BY CATEGORY **/
	public List<Item> findOwnItems(Person person, CategoryType category);
	
	/** OWN ITEMS FILTER BY TAGS **/
	public List<Item> findOwnItems(Person person, List<String> tags);
	/** WORLD ITEMS **/
	public List<Item> findWorldItems(Person person);
	
	/** WORLD ITEMS FILTERED BY CATEGORY **/
	public List<Item> findWorldItems(Person person, CategoryType category);

	/**
	 * WORLD ITEMS: FILTERED BY TAGS AND CATEGORY
	 ***/
	public List<Item> findWorldItems(Person person, List<String> tags, CategoryType category);

	/**
	 * WORLD ITEMS: FILTERED BY TAGS
	 ***/
	// SELECT * from item i, itemvisibilitydomain v, itemtag t,
	// personvisibilitydomain p, person o, visibility_domain vd WHERE
	// i.person_id != 44 AND v.item_id = i.id AND t.item_id = i.id AND o.id =
	// i.person_id AND ((o.id = 1 AND p.person_id = o.id AND vd.id =
	// v.visibilitydomain_id AND p.visibilitydomain_id = vd.id) OR vd.visibility
	// = 'PUBLIC') AND (t.tag LIKE 'deck')
	public List<Item> findWorldItems(Person person, List<String> tags);

	/* ALL FRIENDS: FILTERED BY TAGS */
	public List<Item> findFriendsItems(Person person,
			List<String> tags, int start);

	/* ALL FRIENDS: FILTERED BY TAGS AND CATEGORY */
	public List<Item> findFriendsItems(Person person,
			List<String> tags, int start, CategoryType category);

	/* ALL FRIENDS: FILTERED BY CATEGORY */
	public List<Item> findFriendsItems(Person person, int start, CategoryType category);

	/* ALL FRIENDS NO FILTER */
	public List<Item> findFriendsItems(Person person, int start);

	/** ONE FRIEND: Filtered BY TAGS AND CATEGORY **/
	public List<Item> findFriendItems(Person friend,
			CategoryType category, Person person, List<String> tags, int start);

	/** ONE FRIEND FILTERED BY CATEGORY **/
	public List<Item> findFriendItems(Person friend,
			CategoryType category, Person person, int start);

	/** ONE FRIEND FILTERED BY TAGS **/
	public List<Item> findFriendItems(Person friend, Person person, List<String> tags, int start);

	/** ONE FRIEND NO FILTER **/
	public List<Item> findFriendItems(Person friend, Person person, int start);

}
```
