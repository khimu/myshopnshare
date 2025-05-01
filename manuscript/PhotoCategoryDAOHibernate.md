# PhotoCategoryDAOHibernate

```java
package com.myshopnshare.core.dao;

import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.PhotoCategory;
@Repository("photoCategoryDAO")
public class PhotoCategoryDAOHibernate extends
		GenericDAOHibernate<PhotoCategory, Long> implements PhotoCategoryDAO {

}
```
