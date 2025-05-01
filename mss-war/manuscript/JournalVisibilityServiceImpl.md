# JournalVisibilityServiceImpl

```java
package com.myshopnshare.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.JournalVisibilityDAO;
import com.myshopnshare.core.domain.JournalVisibility;

@Service("journalVisibilityService")
@Transactional
public class JournalVisibilityServiceImpl extends
		GenericServiceImpl<JournalVisibility, Long> implements
		JournalVisibilityService {

	private JournalVisibilityDAO journalVisibilityDAO;

	@Autowired
	public JournalVisibilityServiceImpl(JournalVisibilityDAO genericDao) {
		super(genericDao);
		this.journalVisibilityDAO = genericDao;
	}
}
```
