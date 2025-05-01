# VisibilityDomainDAO

```java
package com.myshopnshare.core.dao;

import java.util.List;

import com.myshopnshare.core.domain.Permission;
import com.myshopnshare.core.domain.VisibilityDomain;

public interface VisibilityDomainDAO extends GenericDAO<VisibilityDomain, Long> {

	public List<VisibilityDomain> findAllGroupsForPerson(Permission permission);

}
```
