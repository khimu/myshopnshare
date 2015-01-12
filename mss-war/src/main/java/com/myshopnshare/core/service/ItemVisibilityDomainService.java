package com.myshopnshare.core.service;

import java.util.List;

import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.domain.ItemVisibilityDomain;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.CategoryType;

public interface ItemVisibilityDomainService extends
		GenericService<ItemVisibilityDomain, Long> {
	
	/** OWN ITEMS **/
	public List<Item> findOwnItems(Person person);

	/** OWN ITEMS FILTER BY TAGS AND CATEGORY **/
	public List<Item> findOwnItems(Person person, String searchString, CategoryType category);

	/** OWN ITEMS FILTER BY CATEGORY **/
	public List<Item> findOwnItems(Person person, CategoryType category);
	
	/** OWN ITEMS FILTER BY TAGS **/
	public List<Item> findOwnItems(Person person, String searchString);

	/** WORLD ITEMS **/
	public List<Item> findWorldItems(Person person);
	
	/** WORLD ITEMS FILTERED BY CATEGORY **/
	public List<Item> findWorldItems(Person person, CategoryType category);

	/**
	 * WORLD ITEMS: FILTERED BY TAGS AND CATEGORY
	 ***/
	public List<Item> findWorldItems(Person person, String searchString, CategoryType category);

	
	/**
	 * WORLD ITEMS: FILTERED BY TAGS
	 ***/
	public List<Item> findWorldItems(Person person, String searchString);

	/* ALL FRIENDS: FILTERED BY TAGS */
	public List<Item> findFriendsItems(Person person,
			String searchString, int start);

	/* ALL FRIENDS: FILTERED BY TAGS AND CATEGORY */
	public List<Item> findFriendsItems(Person person,
			String searchString, CategoryType category, int start);

	/* ALL FRIENDS: FILTERED BY CATEGORY */
	public List<Item> findFriendsItems(Person person, CategoryType category, int start);

	/* ALL FRIENDS NO FILTER */
	public List<Item> findFriendsItems(Person person, int start);

	/** ONE FRIEND: Filtered BY TAGS AND CATEGORY **/
	public List<Item> findFriendItems(Person friend,
			CategoryType category, Person person, String searchString, int start);

	/** ONE FRIEND FILTERED BY CATEGORY **/
	public List<Item> findFriendItems(Person friend,
			CategoryType category, Person person, int start);

	/** ONE FRIEND FILTERED BY TAGS **/
	public List<Item> findFriendItems(Person friend, Person person, String searchString, int start);

	/** ONE FRIEND NO FILTER **/
	public List<Item> findFriendItems(Person friend, Person person, int start);
}
