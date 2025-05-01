# VisibilityDomainPersonDAO

```java
package com.myshopnshare.core.dao;

import java.util.List;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.VisibilityDomainPerson;

public interface VisibilityDomainPersonDAO extends GenericDAO<VisibilityDomainPerson, Long>{
	public List<VisibilityDomainPerson> getVisibilityGroups(Person person);
}
```
