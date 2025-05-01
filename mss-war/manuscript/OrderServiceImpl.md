# OrderServiceImpl

```java
package com.myshopnshare.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.OrderDetailDAO;
import com.myshopnshare.core.domain.OrderDetail;
import com.myshopnshare.core.domain.Person;

@Service("orderService")
@Transactional
public class OrderServiceImpl extends
		GenericServiceImpl<OrderDetail, Long> implements
		OrderService {

	private OrderDetailDAO orderDetailDAO;

	@Autowired
	public OrderServiceImpl(OrderDetailDAO genericDao) {
		super(genericDao);
		this.orderDetailDAO = genericDao;
	}

	public OrderDetail getOrderForPerson(Person person, Long id){
		return orderDetailDAO.getOrderForPerson(person, id);
	}
	
	public List<OrderDetail> findAllOrderFor(Person person) {
		return orderDetailDAO.findAllOrderFor(person);
	}
}
```
