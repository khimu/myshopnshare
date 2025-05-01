# PermissionVisibilityService

```java
package com.myshopnshare.core.service;

import com.myshopnshare.core.domain.Permission;
import com.myshopnshare.core.domain.PermissionVisibility;
import com.myshopnshare.core.domain.VisibilityDomain;

public interface PermissionVisibilityService extends
		GenericService<PermissionVisibility, Long> {
	public PermissionVisibility findFor(Permission permission, VisibilityDomain vd);
}
```
