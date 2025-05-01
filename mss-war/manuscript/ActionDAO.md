# ActionDAO

```java
package com.myshopnshare.core.dao;

import java.util.List;

import com.myshopnshare.core.domain.Action;

public interface ActionDAO {
	public Action get(Long id);

	public List<Action> getAll();

	public List<Action> getAllSortBy(String... parameters);

	public void save(Action instance);

	public void update(Action instance);

	public void saveOrUpdate(Action instance);

	public void delete(Action instance);
}
```
