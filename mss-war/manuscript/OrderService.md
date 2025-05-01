# OrderService

```java
package com.myshopnshare.core.service;

import java.util.List;

import com.myshopnshare.core.domain.OrderDetail;
import com.myshopnshare.core.domain.Person;

public interface OrderService extends
		GenericService<OrderDetail, Long> {
	public List<OrderDetail> findAllOrderFor(Person person);
	public OrderDetail getOrderForPerson(Person person, Long id);
}
```
