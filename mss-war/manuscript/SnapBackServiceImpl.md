# SnapBackServiceImpl

```java
package com.myshopnshare.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.SnapBackDAO;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.SnapBack;

@Service("snapBackService")
@Transactional
public class SnapBackServiceImpl extends
		GenericServiceImpl<SnapBack, Long> implements
		SnapBackService {

	public SnapBackDAO snapBackDAO;

	@Autowired
	public SnapBackServiceImpl(SnapBackDAO genericDao) {
		super(genericDao);
		this.snapBackDAO = genericDao;
	}
	
	public List<SnapBack> findAllMessages(Person person) {
		return snapBackDAO.findAllMessages(person);
	}
}
```
