# VendorService

```java
package com.myshopnshare.core.service;

import java.util.List;

import com.myshopnshare.core.domain.Vendor;

public interface VendorService extends GenericService<Vendor, Long> {
	public Vendor findVendorByUsername(String username);

	public List<Vendor> findBySearchString(String first, String last);
	public Vendor findByEmail(String email);
}
```
