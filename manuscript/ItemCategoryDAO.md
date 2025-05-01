# ItemCategoryDAO

```java
package com.myshopnshare.core.dao;

import java.util.List;

import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.domain.ItemCategory;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.CategoryType;

public interface ItemCategoryDAO extends GenericDAO<ItemCategory, Long> {
	public List<Item> findAllItemsFor(Person person, CategoryType category,
			int start);

	public ItemCategory getItemCategoryForPerson(Person person, Long id);

	/** OWN ItemCategoryS **/
	public List<ItemCategory> findOwnItemCategorys(Person person, int start);

	/** OWN ITEMS FILTER BY TAGS AND CATEGORY **/
	public List<ItemCategory> findOwnItemCategorys(Person person,
			List<String> tags, CategoryType category, int start);

	/** OWN ITEMS FILTER BY CATEGORY **/
	public List<ItemCategory> findOwnItemCategorys(Person person,
			CategoryType category, int start);

	/** OWN ItemCategoryS FILTER BY TAGS **/
	public List<ItemCategory> findOwnItemCategorys(Person person,
			List<String> tags, int start);

	/* ALL FRIENDS: FILTERED BY TAGS */
	public List<ItemCategory> findFriendsItemCategorys(Person person,
			List<String> tags, int start);

	/* ALL FRIENDS: FILTERED BY TAGS AND CATEGORY */
	public List<ItemCategory> findFriendsItemCategorys(Person person,
			List<String> tags, int start, CategoryType category);

	/* ALL FRIENDS: FILTERED BY CATEGORY */
	public List<ItemCategory> findFriendsItemCategorys(Person person,
			int start, CategoryType category);

	/* ALL FRIENDS NO FILTER */
	public List<ItemCategory> findFriendsItemCategorys(Person person, int start);

	/** ONE FRIEND: Filtered BY TAGS AND CATEGORY **/
	public List<ItemCategory> findFriendItemCategorys(Person friend,
			CategoryType category, Person person, List<String> tags, int start);

	/** ONE FRIEND FILTERED BY CATEGORY **/
	public List<ItemCategory> findFriendItemCategorys(Person friend,
			CategoryType category, Person person, int start);

	/** ONE FRIEND FILTERED BY TAGS **/
	public List<ItemCategory> findFriendItemCategorys(Person friend,
			Person person, List<String> tags, int start);

	/** ONE FRIEND NO FILTER **/
	public List<ItemCategory> findFriendItemCategorys(Person friend,
			Person person, int start);
	
	

	
	/** OWN ItemCategoryS **/
	public Long findOwnItemCategorysCount(Person person);

	/** OWN ITEMS FILTER BY TAGS AND CATEGORY **/
	public Long findOwnItemCategorysCount(Person person,
			List<String> tags, CategoryType category);

	/** OWN ITEMS FILTER BY CATEGORY **/
	public Long findOwnItemCategorysCount(Person person,
			CategoryType category);

	/** OWN ItemCategoryS FILTER BY TAGS **/
	public Long findOwnItemCategorysCount(Person person,
			List<String> tags);

	/* ALL FRIENDS: FILTERED BY TAGS */
	public Long findFriendsItemCategorysCount(Person person,
			List<String> tags);

	/* ALL FRIENDS: FILTERED BY TAGS AND CATEGORY */
	public Long findFriendsItemCategorysCount(Person person,
			List<String> tags, CategoryType category);

	/* ALL FRIENDS: FILTERED BY CATEGORY */
	public Long findFriendsItemCategorysCount(Person person,
			CategoryType category);

	/* ALL FRIENDS NO FILTER */
	public Long findFriendsItemCategorysCount(Person person);

	/** ONE FRIEND: Filtered BY TAGS AND CATEGORY **/
	public Long findFriendItemCategorysCount(Person friend,
			CategoryType category, Person person, List<String> tags);

	/** ONE FRIEND FILTERED BY CATEGORY **/
	public Long findFriendItemCategorysCount(Person friend,
			CategoryType category, Person person);

	/** ONE FRIEND FILTERED BY TAGS **/
	public Long findFriendItemCategorysCount(Person friend,
			Person person, List<String> tags);

	/** ONE FRIEND NO FILTER **/
	public Long findFriendItemCategorysCount(Person friend,
			Person person);

}
```
