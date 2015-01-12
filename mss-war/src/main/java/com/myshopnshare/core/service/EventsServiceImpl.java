package com.myshopnshare.core.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.EventsDAO;
import com.myshopnshare.core.domain.Events;
import com.myshopnshare.core.domain.Person;

@Service("eventsService")
@Transactional
public class EventsServiceImpl extends
		GenericServiceImpl<Events, Long> implements EventsService {
	
	
	private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
	static {
		simpleDateFormat.applyPattern("MM/dd/yyyy");
	}

	public EventsDAO eventsDAO;

	@Autowired
    public EventsServiceImpl(EventsDAO genericDao) {
    	super(genericDao);
        this.eventsDAO = genericDao;
    }

	public Events getEventByPerson(Person person, Long id){
		return eventsDAO.getEventByPerson(person, id);
	}

	public List<Events> findAllEvents(Person person) {
		return eventsDAO.findAllEvents(person);
	}

	public List<Events> findEventsForDate(Person person, Date startDate) {
		return eventsDAO.findEventsForDate(person, startDate);
	}


	public List<Events> findOneFriendsEvents(Person friend, Person person,
			String startDate, String searchString, int start)  throws ParseException {
		if (StringUtils.trimToNull(searchString) == null) {
			if (StringUtils.trimToNull(startDate) == null) {
				return eventsDAO.findOneFriendsEvents(friend,
						person, start);
			}
			return eventsDAO.findOneFriendsEvents(friend, person,
					simpleDateFormat.parse(startDate), start);
		}
		if (StringUtils.trimToNull(startDate) == null) {
			return eventsDAO.findOneFriendsEvents(friend, person,
					tokenize(searchString), start);
		}
		return eventsDAO.findOneFriendsEvents(friend, person,
				simpleDateFormat.parse(startDate), tokenize(searchString), start);
	}

	public List<Events> findOwnEvents(Person person, String searchString,
			String startDate, int start)  throws ParseException {
		if (StringUtils.trimToNull(searchString) == null) {
			if (StringUtils.trimToNull(startDate) == null) {
				return eventsDAO.findOwnEvents(person, start);
			}
			return eventsDAO.findOwnEvents(person, simpleDateFormat.parse(startDate),
					start);
		}
		if (startDate == null) {
			return eventsDAO.findOwnEvents(person,
					tokenize(searchString), start);
		}
		return eventsDAO.findOwnEvents(person, simpleDateFormat.parse(startDate),
				tokenize(searchString), start);
	}

	public List<Events> findWorldEvents(Person person, String searchString,
			String startDate, int start)  throws ParseException {
		if (StringUtils.trimToNull(searchString) == null) {
			if (StringUtils.trimToNull(startDate) == null) {
				return eventsDAO.findWorldEvents(person, start);
			}
			return eventsDAO.findWorldEvents(person, simpleDateFormat.parse(startDate),
					start);
		}
		if (StringUtils.trimToNull(startDate) == null) {
			return eventsDAO.findWorldEvents(person,
					tokenize(searchString), start);
		}
		return eventsDAO.findWorldEvents(person, simpleDateFormat.parse(startDate),
				tokenize(searchString), start);
	}

	public List<Events> findAllFriendsEvents(Person person, String startDate,
			String searchString, int start)  throws ParseException {
		if (StringUtils.trimToNull(searchString) == null) {
			if (StringUtils.trimToNull(startDate) == null) {
				return eventsDAO.findAllFriendsEvents(person,
						start);
			}
			return eventsDAO.findAllFriendsEvents(person,
					simpleDateFormat.parse(startDate), start);
		}
		if (StringUtils.trimToNull(startDate) == null) {
			return eventsDAO.findAllFriendsEvents(person,
					tokenize(searchString), start);
		}
		return eventsDAO.findAllFriendsEvents(person, simpleDateFormat.parse(startDate),
				tokenize(searchString), start);
	}


	public List<Events> findPublicEvents(String startDate, String searchString,
			int start)  throws ParseException {
		if (StringUtils.trimToNull(searchString) == null) {
			if (StringUtils.trimToNull(startDate) == null) {
				return eventsDAO.findPublicEvents(start);
			}
			return eventsDAO.findPublicEvents(simpleDateFormat.parse(startDate), start);
		}
		if (StringUtils.trimToNull(startDate) == null) {
			return eventsDAO.findPublicEvents(
					tokenize(searchString), start);
		}
		return eventsDAO.findPublicEvents(simpleDateFormat.parse(startDate),
				tokenize(searchString), start);
	}
	
}
