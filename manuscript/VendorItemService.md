# VendorItemService

```java
package com.myshopnshare.core.service;

import java.util.List;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.VendorItem;

public interface VendorItemService extends
		GenericService<VendorItem, Long> {
	public VendorItem getVendorItemForPerson(Person person, Long id);

	public VendorItem findSleekSwapItem(String itemName);
	public VendorItem findBasicPackage();
	public VendorItem findPremiumPackage();
	public VendorItem findCorporatePackage();

	public List<VendorItem> findHotestItems(String searchString, String category, int start);

	public List<VendorItem> findCheapest(String searchString, String category, int start);

	/** THE BIGGER THE CLEARANCE THE BETTER **/
	public List<VendorItem> findClearance(String searchString, String category, int start);

	public List<VendorItem> findRebates(String searchString, String category, int start);

	public List<VendorItem> findFree(String searchString, String category, int start);
	
	public List<VendorItem> findAllVendorItemsFor(Person person, int start);
	
	/** WORLD ITEMS **/
	public List<VendorItem> findWorldVendorItems(String searchString, String category, int start);
	
	public Long findHotestItemsCount(String searchString, String category);

	public Long findCheapestCount(String searchString, String category);

	/** THE BIGGER THE CLEARANCE THE BETTER **/
	public Long findClearanceCount(String searchString, String category);

	public Long findRebatesCount(String searchString, String category);

	public Long findFreeCount(String searchString, String category);
	
	public Long findAllVendorItemsForCount(Person person);
	
	/** WORLD ITEMS **/
	public Long findWorldVendorItemsCount(String searchString, String category);
	
}
```
