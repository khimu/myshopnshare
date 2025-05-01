# EmployerDAO

```java
package com.myshopnshare.core.dao;

import java.util.List;

import com.myshopnshare.core.domain.Employer;

public interface EmployerDAO extends GenericDAO<Employer, Long> {
	public Employer findEmployerByUsername(String username);

	public List<Employer> findBySearchString(String searchString);

	public Employer findByEmail(String email);
}
```
