package com.myshopnshare.core.dao;

import java.util.List;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.VendorItem;
import com.myshopnshare.core.enums.CategoryType;

public interface VendorItemDAO extends GenericDAO<VendorItem, Long> {
	public VendorItem findSleekSwapItem(String itemName);
	public VendorItem getVendorItemForPerson(Person person, Long id);
	public VendorItem findBasicPackage();
	public VendorItem findPremiumPackage();
	public VendorItem findCorporatePackage();

	public List<VendorItem> findHotestItems(int start);
	public List<VendorItem> findHotestItems(List<String> tags, int start);
	public List<VendorItem> findHotestItems(CategoryType category, int start);
	public List<VendorItem> findHotestItems(List<String> tags, CategoryType category, int start);

	public List<VendorItem> findCheapest(int start);
	public List<VendorItem> findCheapest(List<String> tags, int start);
	public List<VendorItem> findCheapest(CategoryType category, int start);
	public List<VendorItem> findCheapest(List<String> tags, CategoryType category, int start);

	/** THE BIGGER THE CLEARANCE THE BETTER **/
	public List<VendorItem> findClearance(int start);
	public List<VendorItem> findClearance(List<String> tags, int start);
	public List<VendorItem> findClearance(CategoryType category, int start);
	public List<VendorItem> findClearance(List<String> tags, CategoryType category, int start);

	public List<VendorItem> findRebates(int start);
	public List<VendorItem> findRebates(List<String> tags, int start);
	public List<VendorItem> findRebates(CategoryType category, int start);
	public List<VendorItem> findRebates(List<String> tags, CategoryType category, int start);

	public List<VendorItem> findFree(int start);
	public List<VendorItem> findFree(List<String> tags, int start);
	public List<VendorItem> findFree(CategoryType category, int start);
	public List<VendorItem> findFree(List<String> tags, CategoryType category, int start);
	
	public List<VendorItem> findAllVendorItemsFor(Person person, int start);

	/** WORLD ITEMS **/
	public List<VendorItem> findWorldVendorItems(int start);
	public List<VendorItem> findWorldVendorItems(List<String> tags, int start);
	public List<VendorItem> findWorldVendorItems(CategoryType category, int start);
	public List<VendorItem> findWorldVendorItems(List<String> tags, CategoryType category, int start);
	
	
	public Long findHotestItemsCount();
	public Long findHotestItemsCount(List<String> tags);
	public Long findHotestItemsCount(CategoryType category);
	public Long findHotestItemsCount(List<String> tags, CategoryType category);

	public Long findCheapestCount();
	public Long findCheapestCount(List<String> tags);
	public Long findCheapestCount(CategoryType category);
	public Long findCheapestCount(List<String> tags, CategoryType category);

	/** THE BIGGER THE CLEARANCE THE BETTER **/
	public Long findClearanceCount();
	public Long findClearanceCount(List<String> tags);
	public Long findClearanceCount(CategoryType category);
	public Long findClearanceCount(List<String> tags, CategoryType category);

	public Long findRebatesCount();
	public Long findRebatesCount(List<String> tags);
	public Long findRebatesCount(CategoryType category);
	public Long findRebatesCount(List<String> tags, CategoryType category);

	public Long findFreeCount();
	public Long findFreeCount(List<String> tags);
	public Long findFreeCount(CategoryType category);
	public Long findFreeCount(List<String> tags, CategoryType category);
	
	public Long findAllVendorItemsForCount(Person person);

	/** WORLD ITEMS **/
	public Long findWorldVendorItemsCount();
	public Long findWorldVendorItemsCount(List<String> tags);
	public Long findWorldVendorItemsCount(CategoryType category);
	public Long findWorldVendorItemsCount(List<String> tags, CategoryType category);
}
