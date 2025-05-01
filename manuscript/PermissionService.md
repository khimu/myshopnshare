# PermissionService

```java
package com.myshopnshare.core.service;

import com.myshopnshare.core.domain.Permission;
import com.myshopnshare.core.domain.Person;

public interface PermissionService extends
		GenericService<Permission, Long> {
	public Permission findPermissionForPerson(Long permissionId, Person person);
}
```
