# PointItemServiceImpl

```java
package com.myshopnshare.core.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.PointItemDAO;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.PointItem;

@Service("pointItemService")
@Transactional
public class PointItemServiceImpl extends
		GenericServiceImpl<PointItem, Long> implements
		PointItemService {
	
	private PointItemDAO pointItemDAO;

	@Autowired
	public PointItemServiceImpl(PointItemDAO genericDao) {
		super(genericDao);
		this.pointItemDAO = genericDao;
	}
	
	public List<PointItem> findItems(String vendorId, String searchString){
		if(StringUtils.trimToNull(vendorId) == null){ 
			if (StringUtils.trimToNull(searchString) != null) {
		return ((PointItemDAO) this.dao).findItems(Long.parseLong(vendorId), tokenize(searchString));
			}
			return ((PointItemDAO) this.dao).findItems(Long.parseLong(vendorId));
		}else if (StringUtils.trimToNull(searchString) != null) {
			return ((PointItemDAO) this.dao)
			.findItems(tokenize(searchString));
		}
		return ((PointItemDAO) this.dao).findItems();
	}


	public PointItem getPointItemForPerson(Person person, Long id) {
		return ((PointItemDAO) this.dao).getPointItemForPerson(person, id);
	}
}
```
