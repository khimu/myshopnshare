# HeadlineServiceImpl

```java
package com.myshopnshare.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.HeadlineDAO;
import com.myshopnshare.core.domain.Headline;
import com.myshopnshare.core.domain.Person;

@Service("headlineService")
@Transactional
public class HeadlineServiceImpl extends
		GenericServiceImpl<Headline, Long> implements
		HeadlineService {
	public HeadlineDAO headlineDAO;

	@Autowired
    public HeadlineServiceImpl(HeadlineDAO genericDao) {
    	super(genericDao);
        this.headlineDAO = genericDao;
    }
	
	public Headline findLatestHeadlineFor(Person person){
		return ((HeadlineDAO)this.dao).findLatestHeadlineFor(person);
	}
}
```
