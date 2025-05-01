# VendorItemVisibilityDomainServiceImpl

```java
package com.myshopnshare.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.VendorItemVisibilityDomainDAO;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.VendorItem;
import com.myshopnshare.core.domain.VendorItemVisibilityDomain;
import com.myshopnshare.core.enums.CategoryType;

@Service("vendorItemVisibilityDomainService")
@Transactional
public class VendorItemVisibilityDomainServiceImpl
		extends
		GenericServiceImpl<VendorItemVisibilityDomain, Long>
		implements VendorItemVisibilityDomainService {

	private VendorItemVisibilityDomainDAO vendorItemVisibilityDomainDAO;

	@Autowired
	public VendorItemVisibilityDomainServiceImpl(VendorItemVisibilityDomainDAO genericDao) {
		super(genericDao);
		this.vendorItemVisibilityDomainDAO = genericDao;
	}
	
	/** Find all of your items **/
	public List<VendorItem> findAllVisibleItemsFor(Person person) {
		return ((VendorItemVisibilityDomainDAO) this.dao)
				.findAllVisibleItemsFor(person);
	}

	/** This is all public items. Same as method above **/
	public List<VendorItem> findAllVisibleItemsFor(Person person, int start) {
		return ((VendorItemVisibilityDomainDAO) this.dao)
				.findAllVisibleItemsFor(person, start);
	}

	/** This is all your friends' items **/
	// Extra step, make sure that person is friend's with item owner
	public List<VendorItem> findAllFriendVisibleItemsFor(Person person,
			int start) {
		return ((VendorItemVisibilityDomainDAO) this.dao)
				.findAllFriendVisibleItemsFor(person, start);
	}

	/** This is 1 particular friend's items **/
	public List<VendorItem> findFriendsVisibleItems(Person friend,
			CategoryType category, Person person, int start) {
		return ((VendorItemVisibilityDomainDAO) this.dao)
				.findFriendsVisibleItems(friend, category, person, start);
	}

	/** This is 1 particular friend's items **/
	public List<VendorItem> findFriendsVisibleItems(Person friend,
			Person person, int start) {
		return ((VendorItemVisibilityDomainDAO) this.dao)
				.findFriendsVisibleItems(friend, person, start);
	}

}
```
