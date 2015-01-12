package com.myshopnshare.core.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.ItemCategoryDAO;
import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.domain.ItemCategory;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.CategoryType;

@Service("itemCategoryService")
@Transactional
public class ItemCategoryServiceImpl extends
		GenericServiceImpl<ItemCategory, Long> implements
		ItemCategoryService {

	private ItemCategoryDAO itemCategoryDAO;

	@Autowired
    public ItemCategoryServiceImpl(ItemCategoryDAO genericDao) {
    	super(genericDao);
        this.itemCategoryDAO = genericDao;
    }
	

	public List<Item> findAllItemsFor(Person person, CategoryType category,
			int start) {
		return itemCategoryDAO.findAllItemsFor(person, category,
				start);
	}


	public List<ItemCategory> findFriendItemCategorys(Person friend,
			Person person, String category, String searchString, int start) {
		if (StringUtils.trimToNull(searchString) != null) {
			if (StringUtils.trimToNull(category) != null) {
				return itemCategoryDAO.findFriendItemCategorys(
						friend, CategoryType.valueOf(category), person,
						tokenize(searchString), 0);
			} else {
				return itemCategoryDAO.findFriendItemCategorys(
						friend, person, tokenize(searchString), 0);
			}
		} else if (StringUtils.trimToNull(category) != null) {
			return itemCategoryDAO.findFriendItemCategorys(friend,
					CategoryType.valueOf(category), person, 0);
		}
		return itemCategoryDAO.findFriendItemCategorys(friend,
				person, 0);
	}

	public List<ItemCategory> findFriendsItemCategorys(Person person,
			String searchString, String category, int start) {
		if (StringUtils.trimToNull(searchString) != null) {
			if (StringUtils.trimToNull(category) != null) {
				return itemCategoryDAO.findFriendsItemCategorys(
						person, tokenize(searchString), 0, CategoryType
								.valueOf(category));
			} else {
				return itemCategoryDAO.findFriendsItemCategorys(
						person, tokenize(searchString), 0);
			}
		} else if (StringUtils.trimToNull(category) != null) {
			return itemCategoryDAO.findFriendsItemCategorys(
					person, 0, CategoryType.valueOf(category));
		}
		return itemCategoryDAO.findFriendsItemCategorys(person, 0);
	}

	public List<ItemCategory> findOwnItemCategorys(Person person,
			String searchString, String category, int start) {
		if (StringUtils.trimToNull(searchString) != null) {
			if (StringUtils.trimToNull(category) != null) {
				return itemCategoryDAO.findOwnItemCategorys(
						person, tokenize(searchString), CategoryType
								.valueOf(searchString), start);
			} else {
				return itemCategoryDAO.findOwnItemCategorys(
						person, tokenize(searchString), start);
			}
		} else if (StringUtils.trimToNull(category) != null) {
			return itemCategoryDAO.findOwnItemCategorys(person,
					CategoryType.valueOf(category), start);
		}
		return itemCategoryDAO.findOwnItemCategorys(person, start);
	}

	public ItemCategory getItemCategoryForPerson(Person person, Long id) {
		return itemCategoryDAO
				.getItemCategoryForPerson(person, id);
	}

	public Long findFriendItemCategorysCount(Person friend,
			Person person, String category, String searchString) {
		if (StringUtils.trimToNull(searchString) != null) {
			if (StringUtils.trimToNull(category) != null) {
				return itemCategoryDAO.findFriendItemCategorysCount(
						friend, CategoryType.valueOf(category), person,
						tokenize(searchString));
			} else {
				return itemCategoryDAO.findFriendItemCategorysCount(
						friend, person, tokenize(searchString));
			}
		} else if (StringUtils.trimToNull(category) != null) {
			return itemCategoryDAO.findFriendItemCategorysCount(friend,
					CategoryType.valueOf(category), person);
		}
		return itemCategoryDAO.findFriendItemCategorysCount(friend,
				person);
	}

	public Long findFriendsItemCategorysCount(Person person,
			String searchString, String category) {
		if (StringUtils.trimToNull(searchString) != null) {
			if (StringUtils.trimToNull(category) != null) {
				return itemCategoryDAO.findFriendsItemCategorysCount(
						person, tokenize(searchString), CategoryType
								.valueOf(category));
			} else {
				return itemCategoryDAO.findFriendsItemCategorysCount(
						person, tokenize(searchString));
			}
		} else if (StringUtils.trimToNull(category) != null) {
			return itemCategoryDAO.findFriendsItemCategorysCount(
					person, CategoryType.valueOf(category));
		}
		return itemCategoryDAO.findFriendsItemCategorysCount(person);
	}

	public Long findOwnItemCategorysCount(Person person,
			String searchString, String category) {
		if (StringUtils.trimToNull(searchString) != null) {
			if (StringUtils.trimToNull(category) != null) {
				return itemCategoryDAO.findOwnItemCategorysCount(
						person, tokenize(searchString), CategoryType
								.valueOf(searchString));
			} else {
				return itemCategoryDAO.findOwnItemCategorysCount(
						person, tokenize(searchString));
			}
		} else if (StringUtils.trimToNull(category) != null) {
			return itemCategoryDAO.findOwnItemCategorysCount(person,
					CategoryType.valueOf(category));
		}
		return itemCategoryDAO.findOwnItemCategorysCount(person);
	}

}
