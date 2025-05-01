# PointItemService

```java
package com.myshopnshare.core.service;

import java.util.List;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.PointItem;

public interface PointItemService extends
		GenericService<PointItem, Long> {
	public List<PointItem> findItems(String vendorId, String searchString);
	public PointItem getPointItemForPerson(Person person, Long id);
}
```
