# ItemVisibilityDomainServiceImpl

```java
package com.myshopnshare.core.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.ItemVisibilityDomainDAO;
import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.domain.ItemVisibilityDomain;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.CategoryType;

@Service("itemVisibilityDomainService")
@Transactional
public class ItemVisibilityDomainServiceImpl extends
		GenericServiceImpl<ItemVisibilityDomain, Long>
		implements ItemVisibilityDomainService {

	private ItemVisibilityDomainDAO itemVisibilityDomainDAO;

	@Autowired
    public ItemVisibilityDomainServiceImpl(ItemVisibilityDomainDAO genericDao) {
    	super(genericDao);
        this.itemVisibilityDomainDAO = genericDao;
    }
	
	/*
	private List<String> tokenize(String searchString) {
		List<String> tags = new ArrayList<String>();
		StringTokenizer tokenizerComma = new StringTokenizer(searchString, ",");
		while (tokenizerComma.hasMoreTokens()) {
			String tag = tokenizerComma.nextToken();
			StringTokenizer tokenizerSpace = new StringTokenizer(tag, " ");
			while (tokenizerSpace.hasMoreTokens()) {
				tags.add(tokenizerSpace.nextToken());
			}
		}
		return tags;
	}
	*/

	/** OWN ITEMS **/
	public List<Item> findOwnItems(Person person) {
		return itemVisibilityDomainDAO.findOwnItems(person);
	}

	/** OWN ITEMS FILTER BY TAGS AND CATEGORY **/
	public List<Item> findOwnItems(Person person, String searchString,
			CategoryType category) {
		if (StringUtils.trimToNull(searchString) != null) {
			if (category != null) {
				return itemVisibilityDomainDAO.findOwnItems(
						person, tokenize(searchString), category);
			} else {
				return itemVisibilityDomainDAO.findOwnItems(
						person, tokenize(searchString));
			}
		} else if (category != null) {
			return itemVisibilityDomainDAO.findOwnItems(person,
					category);
		}
		return itemVisibilityDomainDAO.findOwnItems(person);
	}

	/** OWN ITEMS FILTER BY CATEGORY **/
	public List<Item> findOwnItems(Person person, CategoryType category) {
		return itemVisibilityDomainDAO.findOwnItems(person,
				category);
	}

	/** OWN ITEMS FILTER BY TAGS **/
	public List<Item> findOwnItems(Person person, String searchString) {
		return itemVisibilityDomainDAO.findOwnItems(person,
				tokenize(searchString));
	}

	/** WORLD ITEMS **/
	public List<Item> findWorldItems(Person person) {
		return itemVisibilityDomainDAO.findWorldItems(person);
	}

	/** WORLD ITEMS FILTERED BY CATEGORY **/
	public List<Item> findWorldItems(Person person, CategoryType category) {
		return itemVisibilityDomainDAO.findWorldItems(person,
				category);
	}

	/**
	 * WORLD ITEMS: FILTERED BY TAGS AND CATEGORY
	 ***/
	public List<Item> findWorldItems(Person person, String searchString,
			CategoryType category) {
		if (StringUtils.trimToNull(searchString) != null) {
			if (category != null) {
				return itemVisibilityDomainDAO.findWorldItems(
						person, tokenize(searchString), category);
			} else {
				return itemVisibilityDomainDAO.findWorldItems(
						person, tokenize(searchString));
			}
		} else if (category != null) {
			return itemVisibilityDomainDAO.findWorldItems(person,
					category);
		}
		return itemVisibilityDomainDAO.findWorldItems(person);
	}

	/**
	 * WORLD ITEMS: FILTERED BY TAGS
	 ***/
	public List<Item> findWorldItems(Person person, String searchString) {
		return itemVisibilityDomainDAO.findWorldItems(person,
				tokenize(searchString));
	}

	/* ALL FRIENDS: FILTERED BY TAGS */
	public List<Item> findFriendsItems(Person person, String searchString,
			int start) {
		return itemVisibilityDomainDAO.findFriendsItems(person,
				tokenize(searchString), start);
	}

	/* ALL FRIENDS: FILTERED BY TAGS AND CATEGORY */
	public List<Item> findFriendsItems(Person person, String searchString,
			CategoryType category, int start) {
		if (StringUtils.trimToNull(searchString) != null) {
			if (category != null) {
				return itemVisibilityDomainDAO.findFriendsItems(
						person, tokenize(searchString), start, category);
			} else {
				return itemVisibilityDomainDAO.findFriendsItems(
						person, tokenize(searchString), start);
			}
		} else if (category != null) {
			return itemVisibilityDomainDAO.findFriendsItems(
					person, tokenize(searchString), start);
		}
		return itemVisibilityDomainDAO.findFriendsItems(person,
				start);
	}

	/* ALL FRIENDS: FILTERED BY CATEGORY */
	public List<Item> findFriendsItems(Person person, CategoryType category,
			int start) {
		return itemVisibilityDomainDAO.findFriendsItems(person,
				start, category);
	}

	/* ALL FRIENDS NO FILTER */
	public List<Item> findFriendsItems(Person person, int start) {
		return itemVisibilityDomainDAO.findFriendsItems(person,
				start);
	}

	/** ONE FRIEND: Filtered BY TAGS AND CATEGORY **/
	public List<Item> findFriendItems(Person friend, CategoryType category,
			Person person, String searchString, int start) {
		if (StringUtils.trimToNull(searchString) != null) {
			if (category != null) {
				return itemVisibilityDomainDAO
						.findFriendItems(friend, category, person,
								tokenize(searchString), start);
			} else {
				return itemVisibilityDomainDAO.findFriendItems(
						friend, person, tokenize(searchString), start);
			}
		} else if (category != null) {
			return itemVisibilityDomainDAO.findFriendItems(friend,
					category, person, start);
		}
		return itemVisibilityDomainDAO.findFriendItems(friend,
				person, start);

	}

	/** ONE FRIEND FILTERED BY CATEGORY **/
	public List<Item> findFriendItems(Person friend, CategoryType category,
			Person person, int start) {
		return itemVisibilityDomainDAO.findFriendItems(friend,
				category, person, start);
	}

	/** ONE FRIEND FILTERED BY TAGS **/
	public List<Item> findFriendItems(Person friend, Person person,
			String searchString, int start) {
		return itemVisibilityDomainDAO.findFriendItems(friend,
				person, tokenize(searchString), start);
	}

	/** ONE FRIEND NO FILTER **/
	public List<Item> findFriendItems(Person friend, Person person, int start) {
		return itemVisibilityDomainDAO.findFriendItems(friend,
				person, start);
	}

}
```
