# CartItemServiceImpl

```java
package com.myshopnshare.core.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.CartItemDAO;
import com.myshopnshare.core.domain.CartItem;
import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.domain.Person;

@Service("cartItemService")
@Transactional
public class CartItemServiceImpl extends
		GenericServiceImpl<CartItem, Long> implements
		CartItemService {

	private CartItemDAO cartitemDao;
	
	@Autowired
	public CartItemServiceImpl(CartItemDAO cartitemDao) {
		super(cartitemDao);
		this.cartitemDao = cartitemDao;
	}

	public List<String> getRewardsPerVendor(Person person) {
		Map<Person, Float> rewards = new HashMap<Person, Float>();
		List<String> forWeb = new ArrayList<String>();
		for (CartItem c : cartitemDao.getCartItems(person)) {
			Float totalRewardsPoints = rewards.get(c.getItem().getPerson());

			if (totalRewardsPoints == null) {
				totalRewardsPoints = c.getPriceTotal();
			} else {
				totalRewardsPoints += c.getPriceTotal();
			}
			rewards.put(c.getItem().getPerson(), totalRewardsPoints);
		}
		for (Map.Entry<Person, Float> entry : rewards.entrySet()) {
			forWeb.add("<a href='#' onclick='showPoints("
					+ entry.getKey().getId() + ");'>"
					+ entry.getKey().getFullName() + " : $ " + entry.getValue()
					+ "</a>\n");
		}

		return forWeb;
	}	
	
	public float getPriceTotal(Person person) {
		float total = 0;
		for (CartItem q : cartitemDao.getCartItems(person)) {
			total = total + q.getPriceTotal();
		}
		return total;
	}

	public float getShippingTotal(Person person) {
		float total = 0;
		for (CartItem q : cartitemDao.getCartItems(person)) {
			total = total + q.getShippingTotal();
		}
		return total;
	}

	public float getGrandTotal(Person person) {
		float total = 0;
		for (CartItem q : cartitemDao.getCartItems(person)) {
			total = total + q.getTotal();
		}
		return total;
	}

	public CartItem findCartItem(Person person, Long cartItemId) {
		for (CartItem c : this.cartitemDao.getCartItems(person)) {
			if (c.getId().equals(cartItemId)) {
				return c;
			}
		}
		return null;
	}

	public int getQuantityTotalFor(Person person, Item item) {
		int total = 0;
		for (CartItem q : cartitemDao.getCartItems(person)) {
			if (q.getItem().getId().equals(item.getId())) {
				return q.getQuantity();
			}
		}
		return total;
	}

	public int getRemainingQuantityFor(Person person, int desiredQuantity, int total, Item item) {
		int remainingQuantity = total - this.getQuantityTotalFor(person, item);
		if(desiredQuantity >  remainingQuantity){
			return remainingQuantity;
		}
		return desiredQuantity;
	}
	
	public List<CartItem> getCartItems(Person person){
		return  cartitemDao.getCartItems(person);
	}
	
	
	public Integer getShoppingCartSize(Person person){
		return cartitemDao.getCartItems(person).size();
	}
}
```
