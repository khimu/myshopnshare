# CommercialServiceImpl

```java
package com.myshopnshare.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.CommercialDAO;
import com.myshopnshare.core.domain.Commercial;

@Service("commercialService")
@Transactional
public class CommercialServiceImpl extends GenericServiceImpl<Commercial, Long> implements CommercialService{
	private CommercialDAO commercialDAO;

	@Autowired
	public CommercialServiceImpl(CommercialDAO commercialDAO) {
		super(commercialDAO);
		this.commercialDAO = commercialDAO;
	}
}
```
