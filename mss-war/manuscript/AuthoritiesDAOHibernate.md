# AuthoritiesDAOHibernate

```java
package com.myshopnshare.core.dao;

import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Authorities;
@Repository("authoritiesDAO")
public class AuthoritiesDAOHibernate extends GenericDAOHibernate<Authorities, String> implements AuthoritiesDAO{

}
```
