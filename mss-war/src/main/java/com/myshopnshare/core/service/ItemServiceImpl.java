package com.myshopnshare.core.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.ItemDAO;
import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.CategoryType;

@Service("itemService")
@Transactional
public class ItemServiceImpl extends GenericServiceImpl<Item, Long>
		implements ItemService {
		
	private ItemDAO itemDAO;

	@Autowired
    public ItemServiceImpl(ItemDAO genericDao) {
    	super(genericDao);
        this.itemDAO = genericDao;
    }
	
	public Item getItemForPerson(Person person, Long id){
		return itemDAO.getItemForPerson(person, id);
	}
	/** OWN ITEMS FILTER BY TAGS AND CATEGORY **/
	public List<Item> findOwnItems(Person person, String searchString,
			String category, int start) {
		if (StringUtils.trimToNull(searchString) != null) {
			if (StringUtils.trimToNull(category) != null) {
				return itemDAO.findOwnItems(
						person, tokenize(searchString), CategoryType.valueOf(category), start);
			} else {
				return itemDAO.findOwnItems(
						person, tokenize(searchString), start);
			}
		} else if (StringUtils.trimToNull(category) != null) {
			return itemDAO.findOwnItems(person,
					CategoryType.valueOf(category), start);
		}
		return itemDAO.findOwnItems(person, start);
	}

	/**
	 * WORLD ITEMS: FILTERED BY TAGS AND CATEGORY
	 ***/
	public List<Item> findWorldItems(Person person, String searchString,
			String category, int start) {
		if (StringUtils.trimToNull(searchString) != null) {
			if (StringUtils.trimToNull(category) != null) {
				return itemDAO.findWorldItems(
						person, tokenize(searchString), CategoryType.valueOf(category), start);
			} else {
				return itemDAO.findWorldItems(
						person, tokenize(searchString), start);
			}
		} else if (StringUtils.trimToNull(category) != null) {
			return itemDAO.findWorldItems(person,
					CategoryType.valueOf(category), start);
		}
		return itemDAO.findWorldItems(person, start);
	}


	/* ALL FRIENDS: FILTERED BY TAGS AND CATEGORY */
	public List<Item> findFriendsItems(Person person, String searchString,
			String category, int start) {
		if (StringUtils.trimToNull(searchString) != null) {
			if (StringUtils.trimToNull(category) != null) {
				return itemDAO.findFriendsItems(
						person, tokenize(searchString), start, CategoryType.valueOf(category));
			} else {
				return itemDAO.findFriendsItems(
						person, tokenize(searchString), start);
			}
		} else if (StringUtils.trimToNull(category) != null) {
			return itemDAO.findFriendsItems(
					person, start, CategoryType.valueOf(category));
		}
		return itemDAO.findFriendsItems(person,
				start);
	}

	/** ONE FRIEND: Filtered BY TAGS AND CATEGORY **/
	public List<Item> findFriendItems(Person friend, String category,
			Person person, String searchString, int start) {
		if (StringUtils.trimToNull(searchString) != null) {
			if (StringUtils.trimToNull(category) != null) {
				return itemDAO
						.findFriendItems(friend, CategoryType.valueOf(category), person,
								tokenize(searchString), start);
			} else {
				return itemDAO.findFriendItems(
						friend, person, tokenize(searchString), start);
			}
		} else if (StringUtils.trimToNull(category) != null) {
			return itemDAO.findFriendItems(friend,
					CategoryType.valueOf(category), person, start);
		}
		return itemDAO.findFriendItems(friend,
				person, start);

	}

	public Long findFriendItemsCount(Person friend, String category,
			Person person, String searchString) {
		if (StringUtils.trimToNull(searchString) != null) {
			if (StringUtils.trimToNull(category) != null) {
				return itemDAO
						.findFriendItemsCount(friend, CategoryType.valueOf(category), person,
								tokenize(searchString));
			} else {
				return itemDAO.findFriendItemsCount(
						friend, person, tokenize(searchString));
			}
		} else if (StringUtils.trimToNull(category) != null) {
			return itemDAO.findFriendItemsCount(friend,
					CategoryType.valueOf(category), person);
		}
		return itemDAO.findFriendItemsCount(friend,
				person);
	}

	public Long findFriendsItemsCount(Person person, String searchString,
			String category) {
		if (StringUtils.trimToNull(searchString) != null) {
			if (StringUtils.trimToNull(category) != null) {
				return itemDAO.findFriendsItemsCount(
						person, tokenize(searchString), CategoryType.valueOf(category));
			} else {
				return itemDAO.findFriendsItemsCount(
						person, tokenize(searchString));
			}
		} else if (StringUtils.trimToNull(category) != null) {
			return itemDAO.findFriendsItemsCount(
					person, CategoryType.valueOf(category));
		}
		return itemDAO.findFriendsItemsCount(person
				);
	}

	public Long findOwnItemsCount(Person person, String searchString,
			String category) {
		if (StringUtils.trimToNull(searchString) != null) {
			if (StringUtils.trimToNull(category) != null) {
				return itemDAO.findOwnItemsCount(
						person, tokenize(searchString), CategoryType.valueOf(category));
			} else {
				return itemDAO.findOwnItemsCount(
						person, tokenize(searchString));
			}
		} else if (StringUtils.trimToNull(category) != null) {
			return itemDAO.findOwnItemsCount(person,
					CategoryType.valueOf(category));
		}
		return itemDAO.findOwnItemsCount(person);
	}

	public Long findWorldItemsCount(Person person, String searchString,
			String category) {
		if (StringUtils.trimToNull(searchString) != null) {
			if (StringUtils.trimToNull(category) != null) {
				return itemDAO
						.findWorldItemsCount(person,
								tokenize(searchString), CategoryType.valueOf(category));
			} else {
				return itemDAO.findWorldItemsCount(
						person, tokenize(searchString));
			}
		} else if (StringUtils.trimToNull(category) != null) {
			return itemDAO.findWorldItemsCount(person,
					CategoryType.valueOf(category));
		}
		return itemDAO.findWorldItemsCount(person
				);
	}

}
