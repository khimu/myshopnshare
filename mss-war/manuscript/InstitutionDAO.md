# InstitutionDAO

```java
package com.myshopnshare.core.dao;

import java.util.List;
import java.util.Map;

import com.myshopnshare.core.domain.Institution;

public interface InstitutionDAO extends GenericDAO<Institution, Long>{
	public List<Institution> findBySearchString(Map<String, String> searchKeys);
	
	public Institution findInstitutionByUsername(String username);

	public List<Institution> findBySearchString(String searchString);

	public Institution findByEmail(String email);
}
```
