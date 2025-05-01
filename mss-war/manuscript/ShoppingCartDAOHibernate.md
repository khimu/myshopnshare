# ShoppingCartDAOHibernate

```java
package com.myshopnshare.core.dao;

import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.ShoppingCart;
@Repository("shoppingCartDAO")
public class ShoppingCartDAOHibernate extends
		GenericDAOHibernate<ShoppingCart, Long> implements ShoppingCartDAO {

}
```
