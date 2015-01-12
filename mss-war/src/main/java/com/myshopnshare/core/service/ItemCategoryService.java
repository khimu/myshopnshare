package com.myshopnshare.core.service;

import java.util.List;

import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.domain.ItemCategory;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.CategoryType;

public interface ItemCategoryService extends
		GenericService<ItemCategory, Long> {
	
	public List<Item> findAllItemsFor(Person person, CategoryType category,
			int start);
	
	public ItemCategory getItemCategoryForPerson(Person person, Long id);

	/** OWN ITEMS FILTER BY TAGS AND CATEGORY **/
	public List<ItemCategory> findOwnItemCategorys(Person person,
			String searchString, String category, int start);

	/* ALL FRIENDS: FILTERED BY TAGS AND CATEGORY */
	public List<ItemCategory> findFriendsItemCategorys(Person person,
			String searchString, String category, int start);

	/** ONE FRIEND: Filtered BY TAGS AND CATEGORY **/
	public List<ItemCategory> findFriendItemCategorys(Person friend,
		 Person person, String category, String searchString, int start);

	
	
	/** OWN ITEMS FILTER BY TAGS AND CATEGORY **/
	public Long findOwnItemCategorysCount(Person person,
			String searchString, String category);

	/* ALL FRIENDS: FILTERED BY TAGS AND CATEGORY */
	public Long findFriendsItemCategorysCount(Person person,
			String searchString, String category);

	/** ONE FRIEND: Filtered BY TAGS AND CATEGORY **/
	public Long findFriendItemCategorysCount(Person friend,
		 Person person, String category, String searchString);

}
