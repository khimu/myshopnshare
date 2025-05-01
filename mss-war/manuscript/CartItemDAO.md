# CartItemDAO

```java
package com.myshopnshare.core.dao;

import java.util.List;

import com.myshopnshare.core.domain.CartItem;
import com.myshopnshare.core.domain.Person;

public interface CartItemDAO extends GenericDAO<CartItem, Long> {
	public List<CartItem> getCartItems(Person person);
}
```
