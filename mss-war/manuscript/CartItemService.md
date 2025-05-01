# CartItemService

```java
package com.myshopnshare.core.service;

import java.util.List;

import javax.persistence.Transient;

import com.myshopnshare.core.domain.CartItem;
import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.domain.Person;

public interface CartItemService extends
		GenericService<CartItem, Long> {
	
	public List<String> getRewardsPerVendor(Person person);
	
	public Integer getShoppingCartSize(Person person);
	
	public List<CartItem> getCartItems(Person person);
	
	public float getPriceTotal(Person person);

	public float getShippingTotal(Person person);

	public float getGrandTotal(Person person);

	public CartItem findCartItem(Person person, Long cartItemId);

	public int getQuantityTotalFor(Person person, Item item);

	public int getRemainingQuantityFor(Person person, int desiredQuantity, int total, Item item);
}
```
