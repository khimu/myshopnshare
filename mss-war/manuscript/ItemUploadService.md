# ItemUploadService

```java
package com.myshopnshare.core.service;

import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.domain.Person;

public interface ItemUploadService {
	public void uploadItem(Item item, Person person, String country,
			String actualcategory, String category, String tagsStr,
			String title, String subtitle, String externalImageLink,
			String resourceLink, String[] friendId);
}
```
