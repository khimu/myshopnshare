# VisibilityDomainService

```java
package com.myshopnshare.core.service;

import java.util.List;

import com.myshopnshare.core.domain.Permission;
import com.myshopnshare.core.domain.VisibilityDomain;

public interface VisibilityDomainService extends
		GenericService<VisibilityDomain, Long> {
	public List<VisibilityDomain> findAllGroupsForPerson(Permission permission);

}
```
