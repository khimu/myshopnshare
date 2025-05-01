# EventsService

```java
package com.myshopnshare.core.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.myshopnshare.core.domain.Events;
import com.myshopnshare.core.domain.Person;

public interface EventsService extends GenericService<Events, Long> {
	public Events getEventByPerson(Person person, Long id);
	
	public List<Events> findAllEvents(Person person);

	public List<Events> findEventsForDate(Person person, Date startDate);

	public List<Events> findOwnEvents(Person person, String searchString,
			String startDate, int start)  throws ParseException ;

	public List<Events> findWorldEvents(Person person, String searchString,
			String startDate, int start)  throws ParseException ;

	public List<Events> findOneFriendsEvents(Person friend, Person person,
			String startDate, String searchString, int start) throws ParseException ;

	public List<Events> findAllFriendsEvents(Person person, String startDate,
			String searchString, int start)  throws ParseException ;

	public List<Events> findPublicEvents(String startDate, String searchString,
			int start)  throws ParseException ;	
}
```
