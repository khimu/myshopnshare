# OrderDetailDAO

```java
package com.myshopnshare.core.dao;

import java.util.List;

import com.myshopnshare.core.domain.ItemOrderDetail;
import com.myshopnshare.core.domain.OrderDetail;
import com.myshopnshare.core.domain.Person;

public interface OrderDetailDAO extends GenericDAO<OrderDetail, Long> {
	public OrderDetail getOrderForPerson(Person person, Long id);
	
	public List<OrderDetail> findAllOrderFor(Person person);

	public List<ItemOrderDetail> findAllPendingOrderFor(Person person);

	public List<ItemOrderDetail> findAllCompletedOrderFor(Person person);

	public List<ItemOrderDetail> findAllApprovedOrderFor(Person person);

	public List<ItemOrderDetail> findAllShippedOrderFor(Person person);

	public List<ItemOrderDetail> findAllReturnedOrderFor(Person person);

	public List<ItemOrderDetail> findAllRequestReturnedOrderFor(Person person);

}
```
