# ShoppingCartServiceImpl

```java
package com.myshopnshare.core.service;

import java.util.List;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.RewardCartDAO;
import com.myshopnshare.core.dao.ShoppingCartDAO;
import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.RewardCart;
import com.myshopnshare.core.domain.ShoppingCart;

@Service("shoppingCartService")
@Transactional
public class ShoppingCartServiceImpl extends
		GenericServiceImpl<ShoppingCart, Long> implements
		ShoppingCartService {
	
	@Autowired
	private RewardCartDAO rewardCartDao;

	public ShoppingCartDAO shoppingCartDAO;

	@Autowired
	public ShoppingCartServiceImpl(ShoppingCartDAO genericDao) {
		super(genericDao);
		this.shoppingCartDAO = genericDao;
	}
	
	public List<RewardCart> getRewardCartItems(Person person){
		return rewardCartDao.getRewardCartItems(person);
	}
	
	public float getRewardPriceTotal(Person person) {
		float total = 0;
		for (RewardCart q : rewardCartDao.getRewardCartItems(person)) {
			total = total + q.getPriceTotal();
		}
		return total;
	}

	public int getRewardQuantityTotalFor(Person person, Item item) {
		int total = 0;
		for (RewardCart q : rewardCartDao.getRewardCartItems(person)) {
			if (q.getItem().getId().equals(item.getId())) {
				return q.getQuantity();
			}
		}
		return total;
	}

	public int getRewardRemainingQuantityFor(Person person, int desiredQuantity, int total, Item item) {
		int remainingQuantity = total - this.getRewardQuantityTotalFor(person, item);
		if(desiredQuantity >  remainingQuantity){
			return remainingQuantity;
		}
		return desiredQuantity;
	}

	public float getRewardPriceTotalFor(Person person, Item item) {
		float total = 0;
		for (RewardCart q : rewardCartDao.getRewardCartItems(person)) {
			if (q.getItem().getId().equals(item.getId())) {
				total = total + q.getPriceTotal();
			}
		}
		return total;
	}

	public float getRewardShippingTotal(Person person) {
		float total = 0;
		for (RewardCart q : rewardCartDao.getRewardCartItems(person)) {
			total = total + q.getShippingTotal();
		}
		return total;
	}

	public float getRewardGrandTotal(Person person) {
		float total = 0;
		for (RewardCart q : rewardCartDao.getRewardCartItems(person)) {
			total = total + q.getTotal();
		}
		return total;
	}

	public RewardCart findRewardItem(Person person, Long rewardItemId) {
		for (RewardCart c : this.rewardCartDao.getRewardCartItems(person)) {
			if (c.getId().equals(rewardItemId)) {
				return c;
			}
		}
		return null;
	}

}
```
