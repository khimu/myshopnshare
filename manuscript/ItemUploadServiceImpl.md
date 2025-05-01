# ItemUploadServiceImpl

```java
package com.myshopnshare.core.service;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.EmailDAO;
import com.myshopnshare.core.dao.ItemCategoryDAO;
import com.myshopnshare.core.dao.ItemDAO;
import com.myshopnshare.core.dao.ItemVisibilityDomainDAO;
import com.myshopnshare.core.dao.NewsDAO;
import com.myshopnshare.core.dao.NewsVisibilityDomainDAO;
import com.myshopnshare.core.dao.PersonDAO;
import com.myshopnshare.core.dao.PointItemDAO;
import com.myshopnshare.core.dao.VendorItemDAO;
import com.myshopnshare.core.domain.Action;
import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.domain.ItemCategory;
import com.myshopnshare.core.domain.ItemTag;
import com.myshopnshare.core.domain.News;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.PointItem;
import com.myshopnshare.core.domain.VendorItem;
import com.myshopnshare.core.enums.CategoryType;

@Service("itemUploadService")
@Transactional
public class ItemUploadServiceImpl implements ItemUploadService {
	private static transient final Log log = LogFactory
			.getLog(ItemUploadServiceImpl.class);

	@Autowired
	private EmailDAO emailDao;
	
	@Autowired
	private ItemDAO itemDao;
	@Autowired
	private PersonDAO personDao;
	@Autowired
	private NewsDAO newsDao;
	@Autowired
	private NewsVisibilityDomainDAO newsVisibilityDao;
	@Autowired
	private ItemVisibilityDomainDAO itemVisibilityDao;
	@Autowired
	private ItemCategoryDAO itemCategoryDao;
	@Autowired
	private VendorItemDAO vendorItemDao;
	@Autowired
	private PointItemDAO pointItemDao;

	public void uploadItem(Item item, Person person, String country,
			String actualcategory, String category, String tagsStr,
			String title, String subtitle, String externalImageLink,
			String resourceLink, String[] friendIds) {
		item.setTitle(title);
		item.setSubtitle(subtitle);
		item.setExternalLink(externalImageLink);
		item.setResourceLink(resourceLink);

		String[] tags = StringUtils.split(tagsStr, ",");
		for (String tag : tags) {
			ItemTag t = new ItemTag();
			t.setTag(tag);
			t.setItem(item);
			item.getTags().add(t);
		}

		if (StringUtils.trimToNull(country) != null) {
			ItemTag t = new ItemTag();
			t.setTag(country);
			t.setItem(item);
			item.getTags().add(t);
		}

		if (StringUtils.trimToNull(actualcategory) != null) {
			ItemTag t = new ItemTag();
			t.setTag(actualcategory);
			t.setItem(item);
			item.getTags().add(t);
		}

		ItemTag t = new ItemTag();
		t.setTag(item.getItemName());
		t.setItem(item);

		//itemTagService.save();

		item.getTags().add(t);

		ItemCategory itemCategory = new ItemCategory();
		itemCategory.setCategory(CategoryType.valueOf(category));
		itemCategory.setItem(item);
		itemCategory.setPerson(person);

		//itemCategoryDao.save(itemCategory);

		item.getItemCategories().add(itemCategory);

		person.getItemVisibility(item, category);
		person.getItems().add(item);

		if (CategoryType.valueOf(category) == CategoryType.SELLING
				|| CategoryType.valueOf(category) == CategoryType.SERVICE) {
			vendorItemDao.save((VendorItem) item);
		} else if (CategoryType.valueOf(category) == CategoryType.POINT) {
			pointItemDao.save((PointItem) item);
		} else {
			itemDao.save(item);
		}

		if (CategoryType.valueOf(category) == CategoryType.RECOMMEND) {
			for (String friendId : friendIds) {
				// TODO make sure this actually saves friend information
				// that is new...does cascade cascade down to friend. It
				// should.
				// Person friend =
				// personService.get(Long.parseLong(friendId));
				Person friend = person.findFriend(Long.parseLong(friendId))
						.getFriend();

				ItemCategory ic = new ItemCategory();
				ic.setCategory(CategoryType.RECOMMEND);
				ic.setItem(item);
				ic.setPerson(friend);
				item.getItemCategories().add(ic);
				friend.getItemVisibility(item, category);
				friend.getItemCategories().add(ic);

				News ins = new News();
				ins.setAction(Action.RECOMMEND);
				ins.setPerson(person);
				ins.setMessage(Action.RECOMMEND.convert(person, friend, item));
				log.debug("Message : " + ins.getMessage());

				friend.getNewsPermission(ins);
				friend.getNews().add(ins);

				person.getNewsPermission(ins);
				person.getNews().add(ins);

				personDao.update(person);
				personDao.update(friend);
			}
		} else if (Action.valueOf(category) != Action.RECOMMEND) {
			News itemnews = new News();
			itemnews.setAction(Action.valueOf(category));
			itemnews.setWorld(Action.valueOf(category).isWorld());
			itemnews.setPerson(person);
			// itemnews.setItem(item);
			itemnews.setMessage(Action.valueOf(category).convert(person, item));

			person.getNewsPermission(itemnews);
			person.getNews().add(itemnews);

			personDao.update(person);
		}
	}

}
```
