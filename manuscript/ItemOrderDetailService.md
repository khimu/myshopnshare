# ItemOrderDetailService

```java
package com.myshopnshare.core.service;

import java.util.List;

import com.myshopnshare.core.domain.ItemOrderDetail;
import com.myshopnshare.core.domain.Person;

public interface ItemOrderDetailService extends
		GenericService<ItemOrderDetail, Long> {
	public List<ItemOrderDetail> findAllOrderFor(Person person);

	public List<ItemOrderDetail> findAllPendingOrderFor(Person person);

	public List<ItemOrderDetail> findAllCompletedOrderFor(Person person);

	public List<ItemOrderDetail> findAllApprovedOrderFor(Person person);

	public List<ItemOrderDetail> findAllShippedOrderFor(Person person);

	public List<ItemOrderDetail> findAllReturnedOrderFor(Person person);

	public List<ItemOrderDetail> findAllRequestReturnedOrderFor(Person person);
}
```
