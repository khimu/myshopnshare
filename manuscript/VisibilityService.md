# VisibilityService

```java
package com.myshopnshare.webapp.controller;

import java.util.List;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.VisibilityDomainPerson;

public interface VisibilityService {
	public List<VisibilityDomainPerson> getVisibilityGroups(Person person);
}
```
