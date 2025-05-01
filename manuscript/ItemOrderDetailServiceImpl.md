# ItemOrderDetailServiceImpl

```java
package com.myshopnshare.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.ItemOrderDetailDAO;
import com.myshopnshare.core.domain.ItemOrderDetail;
import com.myshopnshare.core.domain.Person;

@Service("itemOrderDetailService")
@Transactional
public class ItemOrderDetailServiceImpl extends
		GenericServiceImpl<ItemOrderDetail, Long> implements
		ItemOrderDetailService {
	
	private ItemOrderDetailDAO itemOrderDetailDAO;

	@Autowired
	public ItemOrderDetailServiceImpl(ItemOrderDetailDAO genericDao) {
		super(genericDao);
		this.itemOrderDetailDAO = genericDao;
	}

	public List<ItemOrderDetail> findAllOrderFor(Person person){
		return itemOrderDetailDAO.findAllOrderFor(person);
	}

	public List<ItemOrderDetail> findAllPendingOrderFor(Person person){
		return itemOrderDetailDAO.findAllPendingOrderFor(person);
	}

	public List<ItemOrderDetail> findAllCompletedOrderFor(Person person){
		return itemOrderDetailDAO.findAllCompletedOrderFor(person);
	}

	public List<ItemOrderDetail> findAllApprovedOrderFor(Person person){
		return itemOrderDetailDAO.findAllApprovedOrderFor(person);
	}

	public List<ItemOrderDetail> findAllShippedOrderFor(Person person){
		return itemOrderDetailDAO.findAllShippedOrderFor(person);
	}

	public List<ItemOrderDetail> findAllReturnedOrderFor(Person person){
		return itemOrderDetailDAO.findAllReturnedOrderFor(person);
	}

	public List<ItemOrderDetail> findAllRequestReturnedOrderFor(Person person){
		return itemOrderDetailDAO.findAllRequestReturnedOrderFor(person);
	}
}
```
