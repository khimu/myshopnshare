# PersonService

```java
package com.myshopnshare.core.service;

import java.util.List;

import com.myshopnshare.core.domain.Headline;
import com.myshopnshare.core.domain.Person;

public interface PersonService extends GenericService<Person, Long>{
	public Person findPersonByUsername(String username);
	public List<Person> findPersonsBySearchString(String searchString);
	public Person findByEmail(String email);
	public List<Person> findBySearchString(String searchString);
	public List<Person> findMerchants();
	
	public List<Headline> getHeadlines(String username);
	
	public boolean isMaxSelling(Person person) ;
	
	public void updateHeadline(Person person, String message);
}
```
