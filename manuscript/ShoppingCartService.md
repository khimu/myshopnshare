# ShoppingCartService

```java
package com.myshopnshare.core.service;

import java.util.List;

import javax.persistence.Transient;

import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.RewardCart;
import com.myshopnshare.core.domain.ShoppingCart;

public interface ShoppingCartService extends
		GenericService<ShoppingCart, Long> {
	public List<RewardCart> getRewardCartItems(Person person);
	
	public float getRewardPriceTotal(Person person);

	public int getRewardQuantityTotalFor(Person person, Item item);

	public int getRewardRemainingQuantityFor(Person person, int desiredQuantity, int total, Item item);

	public float getRewardPriceTotalFor(Person person, Item item);

	public float getRewardShippingTotal(Person person);

	public float getRewardGrandTotal(Person person);

	public RewardCart findRewardItem(Person person, Long rewardItemId);
}
```
