# PhotoCategoryServiceImpl

```java
package com.myshopnshare.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.PhotoCategoryDAO;
import com.myshopnshare.core.domain.PhotoCategory;

@Service("photoCategoryService")
@Transactional
public class PhotoCategoryServiceImpl extends
		GenericServiceImpl<PhotoCategory, Long> implements
		PhotoCategoryService {
	
	private PhotoCategoryDAO photoCategoryDAO;

	@Autowired
	public PhotoCategoryServiceImpl(PhotoCategoryDAO genericDao) {
		super(genericDao);
		this.photoCategoryDAO = genericDao;
	}
}
```
