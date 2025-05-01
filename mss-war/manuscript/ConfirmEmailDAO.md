# ConfirmEmailDAO

```java
package com.myshopnshare.core.dao;

import com.myshopnshare.core.domain.ConfirmEmail;

public interface ConfirmEmailDAO extends GenericDAO<ConfirmEmail, Long> {
	public ConfirmEmail findByKey(String key);
}
```
