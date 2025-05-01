# ConfirmEmailService

```java
package com.myshopnshare.core.service;

import com.myshopnshare.core.domain.ConfirmEmail;

public interface ConfirmEmailService extends GenericService<ConfirmEmail, Long>{
	public ConfirmEmail findByKey(String key);
}
```
