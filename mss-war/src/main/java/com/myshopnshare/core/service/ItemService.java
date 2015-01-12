package com.myshopnshare.core.service;

import java.util.List;

import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.domain.Person;

public interface ItemService extends GenericService<Item, Long> {
	public Item getItemForPerson(Person person, Long id);
	
	/** OWN ITEMS FILTER BY TAGS AND CATEGORY **/
	public List<Item> findOwnItems(Person person, String searchString,
			String category, int start);

	/**
	 * WORLD ITEMS: FILTERED BY TAGS AND CATEGORY
	 ***/
	public List<Item> findWorldItems(Person person, String searchString,
			String category, int start);

	/* ALL FRIENDS: FILTERED BY TAGS AND CATEGORY */
	public List<Item> findFriendsItems(Person person, String searchString,
			String category, int start);

	/** ONE FRIEND: Filtered BY TAGS AND CATEGORY **/
	public List<Item> findFriendItems(Person friend, String category,
			Person person, String searchString, int start);

	
	/** OWN ITEMS FILTER BY TAGS AND CATEGORY **/
	public Long findOwnItemsCount(Person person, String searchString,
			String category);

	/**
	 * WORLD ITEMS: FILTERED BY TAGS AND CATEGORY
	 ***/
	public Long findWorldItemsCount(Person person, String searchString,
			String category);

	/* ALL FRIENDS: FILTERED BY TAGS AND CATEGORY */
	public Long findFriendsItemsCount(Person person, String searchString,
			String category);

	/** ONE FRIEND: Filtered BY TAGS AND CATEGORY **/
	public Long findFriendItemsCount(Person friend, String category,
			Person person, String searchString);

}
