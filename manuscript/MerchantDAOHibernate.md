# MerchantDAOHibernate

```java
package com.myshopnshare.core.dao;

import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Merchant;
@Repository("merchantDAO")
public class MerchantDAOHibernate  extends GenericDAOHibernate<Merchant, Long> implements MerchantDAO{

}
```
