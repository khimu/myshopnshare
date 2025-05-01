# InstitutionServiceImpl

```java
package com.myshopnshare.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.InstitutionDAO;
import com.myshopnshare.core.domain.Institution;

@Service("institutionService")
@Transactional
public class InstitutionServiceImpl extends
		GenericServiceImpl<Institution, Long> implements
		InstitutionService {

	public InstitutionDAO institutionDAO;

	@Autowired
    public InstitutionServiceImpl(InstitutionDAO genericDao) {
    	super(genericDao);
        this.institutionDAO = genericDao;
    }
}
```
