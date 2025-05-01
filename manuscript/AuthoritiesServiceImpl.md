# AuthoritiesServiceImpl

```java
package com.myshopnshare.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.AuthoritiesDAO;
import com.myshopnshare.core.domain.Authorities;

@Service("authoritiesService")
@Transactional
public class AuthoritiesServiceImpl extends
		GenericServiceImpl<Authorities, String> implements
		AuthoritiesService {

	private AuthoritiesDAO authoritiesDAO;

	@Autowired
	public AuthoritiesServiceImpl(AuthoritiesDAO authoritiesDAO) {
		super(authoritiesDAO);
		this.authoritiesDAO = authoritiesDAO;
	}
}
```
