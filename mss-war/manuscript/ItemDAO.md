# ItemDAO

```java
package com.myshopnshare.core.dao;

import java.util.List;

import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.CategoryType;


public interface ItemDAO extends GenericDAO<Item, Long>{
	public Item getItemForPerson(Person person, Long id);
	
	/** OWN ITEMS **/
	public List<Item> findOwnItems(Person person, int start);

	/** OWN ITEMS FILTER BY TAGS AND CATEGORY **/
	public List<Item> findOwnItems(Person person, List<String> tags, CategoryType category, int start);

	/** OWN ITEMS FILTER BY CATEGORY **/
	public List<Item> findOwnItems(Person person, CategoryType category, int start);
	
	/** OWN ITEMS FILTER BY TAGS **/
	public List<Item> findOwnItems(Person person, List<String> tags, int start);
	/** WORLD ITEMS **/
	public List<Item> findWorldItems(Person person, int start);
	
	/** WORLD ITEMS FILTERED BY CATEGORY **/
	public List<Item> findWorldItems(Person person, CategoryType category, int start);

	/**
	 * WORLD ITEMS: FILTERED BY TAGS AND CATEGORY
	 ***/
	public List<Item> findWorldItems(Person person, List<String> tags, CategoryType category, int start);

	public List<Item> findWorldItems(Person person, List<String> tags, int start);

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
	
	/** OWN ITEMS **/
	public Long findOwnItemsCount(Person person);

	/** OWN ITEMS FILTER BY TAGS AND CATEGORY **/
	public Long findOwnItemsCount(Person person, List<String> tags, CategoryType category);

	/** OWN ITEMS FILTER BY CATEGORY **/
	public Long findOwnItemsCount(Person person, CategoryType category);
	
	/** OWN ITEMS FILTER BY TAGS **/
	public Long findOwnItemsCount(Person person, List<String> tags);
	/** WORLD ITEMS **/
	public Long findWorldItemsCount(Person person);
	
	/** WORLD ITEMS FILTERED BY CATEGORY **/
	public Long findWorldItemsCount(Person person, CategoryType category);

	/**
	 * WORLD ITEMS: FILTERED BY TAGS AND CATEGORY
	 ***/
	public Long findWorldItemsCount(Person person, List<String> tags, CategoryType category);

	public Long findWorldItemsCount(Person person, List<String> tags);

	/* ALL FRIENDS: FILTERED BY TAGS */
	public Long findFriendsItemsCount(Person person,
			List<String> tags);

	/* ALL FRIENDS: FILTERED BY TAGS AND CATEGORY */
	public Long findFriendsItemsCount(Person person,
			List<String> tags, CategoryType category);

	/* ALL FRIENDS: FILTERED BY CATEGORY */
	public Long findFriendsItemsCount(Person person, CategoryType category);

	/* ALL FRIENDS NO FILTER */
	public Long findFriendsItemsCount(Person person);

	/** ONE FRIEND: Filtered BY TAGS AND CATEGORY **/
	public Long findFriendItemsCount(Person friend,
			CategoryType category, Person person, List<String> tags);

	/** ONE FRIEND FILTERED BY CATEGORY **/
	public Long findFriendItemsCount(Person friend,
			CategoryType category, Person person);

	/** ONE FRIEND FILTERED BY TAGS **/
	public Long findFriendItemsCount(Person friend, Person person, List<String> tags);

	/** ONE FRIEND NO FILTER **/
	public Long findFriendItemsCount(Person friend, Person person);

}
```
