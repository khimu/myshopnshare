# MockScenario

```java
package com.myshopnshare.test;

import java.util.Random;

import com.myshopnshare.core.domain.Address;
import com.myshopnshare.core.domain.EmailAddress;
import com.myshopnshare.core.domain.Friend;
import com.myshopnshare.core.domain.Institution;
import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.domain.ItemCategory;
import com.myshopnshare.core.domain.ItemTag;
import com.myshopnshare.core.domain.ItemVisibilityDomain;
import com.myshopnshare.core.domain.Journal;
import com.myshopnshare.core.domain.News;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.Phone;
import com.myshopnshare.core.domain.Photo;
import com.myshopnshare.core.domain.VendorItem;
import com.myshopnshare.core.domain.VisibilityDomain;
import com.myshopnshare.core.enums.CategoryType;
import com.myshopnshare.core.enums.EmailDomain;
import com.myshopnshare.core.enums.VisibilityType;

public class MockScenario {

	public News addNews(Person person) {
		News news = new News();
		news.setPerson(person);
		news.setMessage("hello");
		return news;
	}

	public Journal addJournal(Person person) {
		Journal journal = new Journal();
		journal.setPerson(person);
		journal.setCategory("test");
		journal.setEntry("just a test");
		return journal;
	}
	
	public Institution Institution() {
		Random random = new Random(System.currentTimeMillis());
		Integer i = random.nextInt();

		Institution institution = new Institution();
		institution.setFirstName("UCLA");
		institution.setLastName("BRUINS");

		EmailAddress email = new EmailAddress();
		email.setEmail(String.valueOf(i));
		email.setPrimaryEmail(true);
		email.setPerson(institution);

		Address address = new Address();
		address.setCity("los angeles");
		address.setPerson(institution);
		institution.getAddresses().add(address);
		institution.getEmails().add(email);

		Phone phone = new Phone();
		phone.setNumber("111");
		phone.setPerson(institution);
		institution.getPhones().add(phone);

		return institution;
	}

	public Person person() {
		Random random = new Random(System.currentTimeMillis());
		Integer i = random.nextInt();

		Person person = new Person();
		person.setFirstName("Khim");
		person.setLastName("Ung");

		EmailAddress email = new EmailAddress();
		email.setEmail(String.valueOf(i));
		email.setPrimaryEmail(true);
		email.setPerson(person);

		Address address = new Address();
		address.setCity("los angeles");
		address.setPerson(person);
		person.getAddresses().add(address);
		person.getEmails().add(email);

		Phone phone = new Phone();
		phone.setNumber("111");
		phone.setPerson(person);
		person.getPhones().add(phone);

		return person;
	}

	public Friend addFriends(Person person, Person friend) {
		Friend f = new Friend();
		f.setPerson(person);
		f.setFriend(friend);
		f.setColor("red");

		person.getFriends().add(f);

		return f;
	}

	public ItemCategory addItemCategory(Person person, Item item) {
		ItemCategory ic = new ItemCategory();
		ic.setPerson(person);
		ic.setCategory(CategoryType.RECOMMEND);
		ic.setItem(item);
		item.getItemCategories().add(ic);
		return ic;
	}

	public ItemCategory addItemCategory(Person person, Item item,
			CategoryType category) {
		ItemCategory ic = addItemCategory(person, item);
		ic.setCategory(category);
		return ic;
	}

	public ItemCategory addVendorItemCategory(Person person, Item item) {
		ItemCategory ic = new ItemCategory();
		ic.setPerson(person);
		ic.setCategory(CategoryType.SELLING);
		ic.setItem(item);
		item.getItemCategories().add(ic);
		return ic;
	}

	public Item addItem(Person person) {
		Item item = new Item();
		item.setGlimpse(new Photo());
		item.setItemName("test");
		item.setPerson(person);
		return item;
	}

	public VendorItem addVendorItem(Person person) {
		VendorItem item = new VendorItem();
		item.setGlimpse(new Photo());
		item.setItemName("test");
		item.setPrice(new Float(3.9900));
		item.setClearancePercentage(12);
		item.setRebateAmount(12);
		item.setPerson(person);
		return item;
	}

	public VisibilityDomain makeVisibility() {
		VisibilityDomain visibilityDomain = new VisibilityDomain();
		visibilityDomain.setVisibility(VisibilityType.PUBLIC);
		return visibilityDomain;
	}

	public void addPublicVisibility(VisibilityDomain visibilityDomain, Item item) {
		ItemVisibilityDomain ivd = new ItemVisibilityDomain(item,
				visibilityDomain);
		item.getItemVisibilities().add(ivd);
	}

	public void addTag(Item item) {
		ItemTag tag = new ItemTag();
		tag.setTag("Khim");
		tag.setItem(item);
		item.getTags().add(tag);
	}
}
```
