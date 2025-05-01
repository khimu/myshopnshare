# JournalService

```java
package com.myshopnshare.core.service;

import java.util.List;

import com.myshopnshare.core.domain.Journal;
import com.myshopnshare.core.domain.Person;

public interface JournalService extends GenericService<Journal, Long>{
	public Journal getJournalForPerson(Person person, Long id);
	
	public List<Journal> findOwnJournal(Person person, String category, String searchString, int start);
	public List<Journal> findWorldJournal(Person person, String category, String searchString, int start);
	public List<Journal> findOneFriendsJournal(Person friend, Person person, String category, String searchString, int start);
	public List<Journal> findAllFriendsJournal(Person person, String category, String searchString,int start);
}
```
