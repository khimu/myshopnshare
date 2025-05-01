# PreferenceServiceImpl

```java
package com.myshopnshare.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.PreferenceDAO;
import com.myshopnshare.core.domain.Permission;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.VisibilityDomain;

@Service("preferenceService")
@Transactional
public class PreferenceServiceImpl extends
		GenericServiceImpl<Permission, Long> implements
		PreferenceService {
	private PreferenceDAO preferenceDAO;

	@Autowired
	public PreferenceServiceImpl(PreferenceDAO genericDao) {
		super(genericDao);
		this.preferenceDAO = genericDao;
	}
	
	public List<VisibilityDomain> getVisibilityDomainsFor(Person person){
		return ((PreferenceDAO)this.dao).getVisibilityDomainsFor(person);
	}
}
```
