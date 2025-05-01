# EventsDAO

```java
package com.myshopnshare.core.dao;

import java.util.Date;
import java.util.List;

import com.myshopnshare.core.domain.Events;
import com.myshopnshare.core.domain.Person;

public interface EventsDAO extends GenericDAO<Events, Long> {
	
	public Events getEventByPerson(Person person, Long id);
	
	public List<Events> findAllEvents(Person person);

	public List<Events> findEventsForDate(Person person, Date startDate);

	public List<Events> findOwnEvents(Person person, int start);

	public List<Events> findOwnEvents(Person person, Date startDate, int start);

	public List<Events> findOwnEvents(Person person, Date startDate,
			List<String> tags, int start);

	public List<Events> findOwnEvents(Person person, List<String> tags,
			int start);

	public List<Events> findWorldEvents(Person person, Date startDate,
			List<String> tags, int start);

	public List<Events> findWorldEvents(Person person, List<String> tags,
			int start);

	public List<Events> findWorldEvents(Person person, Date startDate, int start);

	public List<Events> findWorldEvents(Person person, int start);

	public List<Events> findOneFriendsEvents(Person friend, Person person,
			Date startDate, List<String> tags, int start);

	public List<Events> findOneFriendsEvents(Person friend, Person person,
			List<String> tags, int start);

	public List<Events> findOneFriendsEvents(Person friend, Person person,
			Date startDate, int start);

	public List<Events> findOneFriendsEvents(Person friend, Person person,
			int start);

	public List<Events> findAllFriendsEvents(Person person, Date startDate,
			List<String> tags, int start);

	public List<Events> findAllFriendsEvents(Person person, List<String> tags,
			int start);

	public List<Events> findAllFriendsEvents(Person person, Date startDate,
			int start);

	public List<Events> findAllFriendsEvents(Person person, int start);

	public List<Events> findPublicEvents(int start);

	public List<Events> findPublicEvents(Date startDate, List<String> tags,
			int start);

	public List<Events> findPublicEvents(Date startDate, int start);

	public List<Events> findPublicEvents(List<String> tags, int start);

}
```
