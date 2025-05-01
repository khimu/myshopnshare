# CounterService

```java
package com.myshopnshare.core.service;

import com.myshopnshare.core.domain.Counter;

public interface CounterService extends
		GenericService<Counter, Long> {
	public Counter findGeneralCounter();
}
```
