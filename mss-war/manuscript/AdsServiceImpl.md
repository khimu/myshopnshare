# AdsServiceImpl

```java
package com.myshopnshare.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.AdsDAO;
import com.myshopnshare.core.domain.Ads;

@Service("adsService")
@Transactional
public class AdsServiceImpl extends GenericServiceImpl<Ads, Long> implements
		AdsService {

	private AdsDAO adsDao;

	@Autowired
	public AdsServiceImpl(AdsDAO adsDao) {
		super(adsDao);
		this.adsDao = adsDao;
	}
}
```
