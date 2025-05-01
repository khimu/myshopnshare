# NewsService

```java
package com.myshopnshare.core.service;

import java.util.List;

import com.myshopnshare.core.domain.News;
import com.myshopnshare.core.domain.Person;

public interface NewsService extends GenericService<News, Long> {
	public News getNewsForPerson(Person person, Long id);
	public List<News> findOwnNews(Person person, int start);
	
	public List<News> findOneFriendsNews(Person friend, Person person, int start);
	public List<News> findAllFriendsNews(Person person, int start); 
	
	public Long findOwnNewsCount(Person person);

	public List<News> findWorldNews(Person person, int start);
	
	public Long findWorldNewsCount(Person person);

	public List<News> findWorldNews(int start);
	
	public Long findWorldNewsCount();
	
	public Long findOneFriendsNewsCount(Person friend, Person person);

	public Long findAllFriendsNewsCount(Person person);
}
```
