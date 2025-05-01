# VendorItemVisibilityDomainService

```java
package com.myshopnshare.core.service;

import java.util.List;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.VendorItem;
import com.myshopnshare.core.domain.VendorItemVisibilityDomain;
import com.myshopnshare.core.enums.CategoryType;

public interface VendorItemVisibilityDomainService
		extends
		GenericService<VendorItemVisibilityDomain, Long> {

	/** Find all of your items **/
	public List<VendorItem> findAllVisibleItemsFor(Person person);

	/** This is all public items. Same as method above **/
	public List<VendorItem> findAllVisibleItemsFor(Person person, int start);

	/** This is all your friends' items **/
	// Extra step, make sure that person is friend's with item owner
	public List<VendorItem> findAllFriendVisibleItemsFor(Person person,
			int start);

	/** This is 1 particular friend's items **/
	public List<VendorItem> findFriendsVisibleItems(Person friend,
			CategoryType category, Person person, int start);

	/** This is 1 particular friend's items **/
	public List<VendorItem> findFriendsVisibleItems(Person friend,
			Person person, int start);

}
```
