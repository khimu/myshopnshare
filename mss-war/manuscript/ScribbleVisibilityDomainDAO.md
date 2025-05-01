# ScribbleVisibilityDomainDAO

```java
package com.myshopnshare.core.dao;

import java.util.List;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.Scribble;
import com.myshopnshare.core.domain.ScribbleVisibilityDomain;

public interface ScribbleVisibilityDomainDAO extends
		GenericDAO<ScribbleVisibilityDomain, Long> {
	public List<Scribble> findAllVisibleScribblesFor(Person person, int start);

	// Extra step, make sure that person is friend's with item owner
	public List<Scribble> findAllFriendVisibleScribblesFor(Person person,
			int start);
}
```
