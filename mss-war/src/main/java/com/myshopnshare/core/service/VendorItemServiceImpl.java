package com.myshopnshare.core.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.VendorItemDAO;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.VendorItem;
import com.myshopnshare.core.enums.CategoryType;

@Service("vendorItemService")
@Transactional
public class VendorItemServiceImpl extends
		GenericServiceImpl<VendorItem, Long> implements
		VendorItemService {
	private VendorItemDAO vendorItemDAO;

	@Autowired
	public VendorItemServiceImpl(VendorItemDAO genericDao) {
		super(genericDao);
		this.vendorItemDAO = genericDao;
	}
	public VendorItem getVendorItemForPerson(Person person, Long id){
		return ((VendorItemDAO) this.dao).getVendorItemForPerson(person, id);
	}
	
	public VendorItem findBasicPackage() {
		return ((VendorItemDAO) this.dao).findBasicPackage();
	}

	public VendorItem findPremiumPackage() {
		return ((VendorItemDAO) this.dao).findPremiumPackage();
	}

	public VendorItem findCorporatePackage() {
		return ((VendorItemDAO) this.dao).findCorporatePackage();
	}

	/** WORLD ITEMS **/
	public List<VendorItem> findWorldVendorItems(String searchString,
			String category, int start) {
		if (StringUtils.trimToNull(searchString) != null) {
			if (StringUtils.trimToNull(category) != null) {
				return ((VendorItemDAO) this.dao).findWorldVendorItems(
						tokenize(searchString), CategoryType.valueOf(category), start);
			}
			return ((VendorItemDAO) this.dao)
					.findWorldVendorItems(tokenize(searchString), start);
		} else if (StringUtils.trimToNull(category) != null) {
			return ((VendorItemDAO) this.dao).findWorldVendorItems(CategoryType
					.valueOf(category), start);
		}
		return ((VendorItemDAO) this.dao).findWorldVendorItems(start);
	}


	public List<VendorItem> findAllVendorItemsFor(Person person, int start) {
		return ((VendorItemDAO) this.dao).findAllVendorItemsFor(person, start);
	}

	
	public List<VendorItem> findCheapest(String searchString, String category, int start) {
		if (StringUtils.trimToNull(searchString) != null) {
			if (StringUtils.trimToNull(category) != null) {
				return ((VendorItemDAO) this.dao).findCheapest(
						tokenize(searchString), CategoryType.valueOf(category), start);
			}
			return ((VendorItemDAO) this.dao)
					.findCheapest(tokenize(searchString), start);
		} else if (StringUtils.trimToNull(category) != null) {
			return ((VendorItemDAO) this.dao).findCheapest(CategoryType
					.valueOf(category), start);
		}
		return ((VendorItemDAO) this.dao).findCheapest(start);
	}

	
	public List<VendorItem> findClearance(String searchString, String category, int start) {
		if (StringUtils.trimToNull(searchString) != null) {
			if (StringUtils.trimToNull(category) != null) {
				return ((VendorItemDAO) this.dao).findClearance(
						tokenize(searchString), CategoryType.valueOf(category), start);
			}
			return ((VendorItemDAO) this.dao)
					.findClearance(tokenize(searchString), start);
		} else if (StringUtils.trimToNull(category) != null) {
			return ((VendorItemDAO) this.dao).findClearance(CategoryType
					.valueOf(category), start);
		}
		return ((VendorItemDAO) this.dao).findClearance(start);
	}

	
	public List<VendorItem> findFree(String searchString, String category, int start) {
		if (StringUtils.trimToNull(searchString) != null) {
			if (StringUtils.trimToNull(category) != null) {
				return ((VendorItemDAO) this.dao).findFree(
						tokenize(searchString), CategoryType.valueOf(category), start);
			}
			return ((VendorItemDAO) this.dao).findFree(tokenize(searchString), start);
		} else if (StringUtils.trimToNull(category) != null) {
			return ((VendorItemDAO) this.dao).findFree(CategoryType
					.valueOf(category), start);
		}
		return ((VendorItemDAO) this.dao).findFree(start);
	}

	
	public List<VendorItem> findHotestItems(String searchString, String category, int start) {
		if (StringUtils.trimToNull(searchString) != null) {
			if (StringUtils.trimToNull(category) != null) {
				return ((VendorItemDAO) this.dao).findHotestItems(
						tokenize(searchString), CategoryType.valueOf(category), start);
			}
			return ((VendorItemDAO) this.dao)
					.findHotestItems(tokenize(searchString), start);
		} else if (StringUtils.trimToNull(category) != null) {
			return ((VendorItemDAO) this.dao).findHotestItems(CategoryType
					.valueOf(category), start);
		}
		return ((VendorItemDAO) this.dao).findHotestItems(start);
	}

	
	public List<VendorItem> findRebates(String searchString, String category, int start) {
		if (StringUtils.trimToNull(searchString) != null) {
			if (StringUtils.trimToNull(category) != null) {
				return ((VendorItemDAO) this.dao).findRebates(
						tokenize(searchString), CategoryType.valueOf(category), start);
			}
			return ((VendorItemDAO) this.dao)
					.findRebates(tokenize(searchString), start);
		} else if (StringUtils.trimToNull(category) != null) {
			return ((VendorItemDAO) this.dao).findRebates(CategoryType
					.valueOf(category), start);
		}
		return ((VendorItemDAO) this.dao).findRebates(start);
	}

	
	public VendorItem findSleekSwapItem(String itemName) {
		return ((VendorItemDAO) this.dao).findSleekSwapItem(itemName);
	}

	
	public Long findAllVendorItemsForCount(Person person) {
		return ((VendorItemDAO) this.dao).findAllVendorItemsForCount(person);
	}

	
	public Long findCheapestCount(String searchString, String category) {
		if (StringUtils.trimToNull(searchString) != null) {
			if (StringUtils.trimToNull(category) != null) {
				return ((VendorItemDAO) this.dao).findCheapestCount(
						tokenize(searchString), CategoryType.valueOf(category));
			}
			return ((VendorItemDAO) this.dao)
					.findCheapestCount(tokenize(searchString));
		} else if (StringUtils.trimToNull(category) != null) {
			return ((VendorItemDAO) this.dao).findCheapestCount(CategoryType
					.valueOf(category));
		}
		return ((VendorItemDAO) this.dao).findCheapestCount();
	}

	
	public Long findClearanceCount(String searchString, String category) {
		if (StringUtils.trimToNull(searchString) != null) {
			if (StringUtils.trimToNull(category) != null) {
				return ((VendorItemDAO) this.dao).findClearanceCount(
						tokenize(searchString), CategoryType.valueOf(category));
			}
			return ((VendorItemDAO) this.dao)
					.findClearanceCount(tokenize(searchString));
		} else if (StringUtils.trimToNull(category) != null) {
			return ((VendorItemDAO) this.dao).findClearanceCount(CategoryType
					.valueOf(category));
		}
		return ((VendorItemDAO) this.dao).findClearanceCount();
	}

	
	public Long findFreeCount(String searchString, String category) {
		if (StringUtils.trimToNull(searchString) != null) {
			if (StringUtils.trimToNull(category) != null) {
				return ((VendorItemDAO) this.dao).findFreeCount(
						tokenize(searchString), CategoryType.valueOf(category));
			}
			return ((VendorItemDAO) this.dao).findFreeCount(tokenize(searchString));
		} else if (StringUtils.trimToNull(category) != null) {
			return ((VendorItemDAO) this.dao).findFreeCount(CategoryType
					.valueOf(category));
		}
		return ((VendorItemDAO) this.dao).findFreeCount();
	}

	
	public Long findHotestItemsCount(String searchString, String category) {
		if (StringUtils.trimToNull(searchString) != null) {
			if (StringUtils.trimToNull(category) != null) {
				return ((VendorItemDAO) this.dao).findHotestItemsCount(
						tokenize(searchString), CategoryType.valueOf(category));
			}
			return ((VendorItemDAO) this.dao)
					.findHotestItemsCount(tokenize(searchString));
		} else if (StringUtils.trimToNull(category) != null) {
			return ((VendorItemDAO) this.dao).findHotestItemsCount(CategoryType
					.valueOf(category));
		}
		return ((VendorItemDAO) this.dao).findHotestItemsCount();
	}

	
	public Long findRebatesCount(String searchString, String category) {
		if (StringUtils.trimToNull(searchString) != null) {
			if (StringUtils.trimToNull(category) != null) {
				return ((VendorItemDAO) this.dao).findRebatesCount(
						tokenize(searchString), CategoryType.valueOf(category));
			}
			return ((VendorItemDAO) this.dao)
					.findRebatesCount(tokenize(searchString));
		} else if (StringUtils.trimToNull(category) != null) {
			return ((VendorItemDAO) this.dao).findRebatesCount(CategoryType
					.valueOf(category));
		}
		return ((VendorItemDAO) this.dao).findRebatesCount();
	}

	
	public Long findWorldVendorItemsCount(String searchString, String category) {
		if (StringUtils.trimToNull(searchString) != null) {
			if (StringUtils.trimToNull(category) != null) {
				return ((VendorItemDAO) this.dao).findWorldVendorItemsCount(
						tokenize(searchString), CategoryType.valueOf(category));
			}
			return ((VendorItemDAO) this.dao)
					.findWorldVendorItemsCount(tokenize(searchString));
		} else if (StringUtils.trimToNull(category) != null) {
			return ((VendorItemDAO) this.dao).findWorldVendorItemsCount(CategoryType
					.valueOf(category));
		}
		return ((VendorItemDAO) this.dao).findWorldVendorItemsCount();
	}

}
