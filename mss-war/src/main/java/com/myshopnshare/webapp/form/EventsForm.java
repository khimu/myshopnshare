package com.myshopnshare.webapp.form;

import com.myshopnshare.core.domain.Events;
import com.myshopnshare.core.domain.Person;

public class EventsForm {

	private Person person;

	/** This is the value the user selected: Accept, decline, or considering **/
	private String statusType;

	private Events events;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public Events getEvents() {
		return events;
	}

	public void setEvents(Events events) {
		this.events = events;
	}

}
