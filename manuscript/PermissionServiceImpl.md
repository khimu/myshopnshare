# PermissionServiceImpl

```java
package com.myshopnshare.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.PermissionDAO;
import com.myshopnshare.core.domain.Permission;
import com.myshopnshare.core.domain.Person;

@Service("permissionService")
@Transactional
public class PermissionServiceImpl extends
		GenericServiceImpl<Permission, Long> implements
		PermissionService {
	
	private PermissionDAO permissionDAO;

	@Autowired
	public PermissionServiceImpl(PermissionDAO genericDao) {
		super(genericDao);
		this.permissionDAO = genericDao;
	}
	public Permission findPermissionForPerson(Long permissionId, Person person) {
		return ((PermissionDAO) this.dao).findPermissionForPerson(permissionId,
				person);
	}

}
```
