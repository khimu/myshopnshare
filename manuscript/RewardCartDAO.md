# RewardCartDAO

```java
package com.myshopnshare.core.dao;

import java.util.List;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.RewardCart;

public interface RewardCartDAO extends GenericDAO<RewardCart, Long>{
	public List<RewardCart> getRewardCartItems(Person person);
}
```
