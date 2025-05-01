# EmployerServiceImpl

```java
package com.myshopnshare.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.EmployerDAO;
import com.myshopnshare.core.domain.Employer;

@Service("employerService")
@Transactional
public class EmployerServiceImpl extends
		GenericServiceImpl<Employer, Long> implements
		EmployerService {

	public EmployerDAO employerDAO;

	@Autowired
    public EmployerServiceImpl(EmployerDAO genericDao) {
    	super(genericDao);
        this.employerDAO = genericDao;
    }
}
```
