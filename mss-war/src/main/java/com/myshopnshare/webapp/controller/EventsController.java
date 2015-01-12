package com.myshopnshare.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.AcceptPerson;
import com.myshopnshare.core.domain.Action;
import com.myshopnshare.core.domain.ConsideringPerson;
import com.myshopnshare.core.domain.DeclinePerson;
import com.myshopnshare.core.domain.EventTag;
import com.myshopnshare.core.domain.EventVisibility;
import com.myshopnshare.core.domain.Events;
import com.myshopnshare.core.domain.GuestPerson;
import com.myshopnshare.core.domain.News;
import com.myshopnshare.core.domain.NewsVisibilityDomain;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.PersonVisibilityDomain;
import com.myshopnshare.core.domain.VisibilityDomain;
import com.myshopnshare.core.enums.EventStatusType;
import com.myshopnshare.core.enums.PermissionType;
import com.myshopnshare.core.enums.VisibilityType;
import com.myshopnshare.core.service.EmailService;
import com.myshopnshare.core.service.EventsService;
import com.myshopnshare.core.service.FriendService;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.core.service.VisibilityDomainService;
import com.myshopnshare.model.User;
import com.myshopnshare.utils.EmailUtil;

public class EventsController extends MultiActionController {
	private static transient final Log log = LogFactory
			.getLog(EventsController.class);

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private EventsService eventsService;
	
	@Autowired
	private FriendService friendService;
	
	@Autowired
	private VisibilityDomainService visibilityService;

	private Person fetchPerson() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User user = (User) principal;

		return personService.findPersonByUsername(user.getUsername());
	}

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String personId = request.getParameter("personId");

		String searchString = request.getParameter("searchString");
		String startDate = request.getParameter("startDate");

		// do friend stuff and permission checking
		if (StringUtils.trimToNull(personId) != null
				&& !person.getId().equals(Long.parseLong(personId))) {
			if (person.isFriends(personId)) {
				Person friend = personService.get(Long.parseLong(personId));
				request.setAttribute("events", eventsService
						.findOneFriendsEvents(friend, person, startDate,
								searchString, 0));
				request.setAttribute("eventStatusType", EventStatusType
						.values());
				request.setAttribute("person", friend);
				return new ModelAndView("events/events");
			}
		}

		request.setAttribute("person", person);
		request.setAttribute("events", eventsService.findOwnEvents(person,
				searchString, startDate, 0));
		request.setAttribute("eventStatusType", EventStatusType.values());
		return new ModelAndView("events/events");
	}

	public ModelAndView all(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();

		String searchString = request.getParameter("searchString");
		String startDate = request.getParameter("startDate");

		String personId = request.getParameter("personId");
		// do friend stuff and permission checking
		if (StringUtils.trimToNull(personId) != null
				&& !person.getId().equals(Long.parseLong(personId))) {
			if (person.isFriends(personId)) {
				Person friend = personService.get(Long.parseLong(personId));
				request.setAttribute("events", eventsService
						.findOneFriendsEvents(friend, person, startDate,
								searchString, 0));
				request.setAttribute("eventStatusType", EventStatusType
						.values());
				request.setAttribute("person", friend);
				return new ModelAndView("events/events");
			}
		}

		request.setAttribute("person", person);
		request.setAttribute("events", eventsService.findOwnEvents(person,
				searchString, startDate, 0));
		request.setAttribute("eventStatusType", EventStatusType.values());
		return new ModelAndView("events/events");
	}

	public ModelAndView form(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute("eventStatusType", EventStatusType.values());
		return new ModelAndView("events/form");
	}

	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String eventId = request.getParameter("eventId");

		Events event = eventsService.get(Long.parseLong(eventId));
		request.setAttribute("event", event);

		request.setAttribute("yourchoice", "decline");
		if (event.getAccept().contains(person)) {
			request.setAttribute("yourchoice", "attending");
		} else if (event.getConsidering().contains(person)) {
			request.setAttribute("yourchoice", "considering");
		}

		request.setAttribute("person", person);
		request.setAttribute("eventStatusType", EventStatusType.values());
		return new ModelAndView("events/eventPage");
	}

	@SuppressWarnings("unchecked")
	public ModelAndView editform(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String eventId = request.getParameter("eventId");

		Events event = eventsService.get(Long.parseLong(eventId));

		request.setAttribute("guests", event.getGuestList());

		List<Person> friends = friendService.findAllFriends(person);
		friends.removeAll((Collection) event.getGuestList());

		request.setAttribute("friends", friends);

		request.setAttribute("person", person);
		request.setAttribute("event", event);
		request.setAttribute("eventStatusType", EventStatusType.values());
		return new ModelAndView("events/edit");
	}

	@SuppressWarnings("unchecked")
	public ModelAndView edit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		
		Person person = fetchPerson();
		// get the friendIds for guests list
		// add start and end times
		String eventId = request.getParameter("id");

		if (StringUtils.trimToNull(eventId) == null) {
			request.setAttribute("person", person);
			request.setAttribute("events", eventsService.findOwnEvents(person,
					null, null, 0));
			return new ModelAndView("events/events");
		}

		String description = request.getParameter("description");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String title = request.getParameter("title");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String tagStr = request.getParameter("tags");
		String[] friendIds = request.getParameterValues("friendIds");

		String[] removeFriendIds = request
				.getParameterValues("removeFriendIds");

		Events event = eventsService.get(Long.parseLong(eventId));

		if (friendIds != null) {
			for (String friendId : friendIds) {
				Person friend = personService.get(Long.parseLong(friendId));
				GuestPerson gp = new GuestPerson();
				gp.setPerson(friend);
				gp.setEvent(event);
				person.getGuests().add(gp);
				event.getGuests().add(gp);
			}
		}

		event.setDescription(description);
		event.setStartDate(dateFormat.parse(startDate));
		event.setEndDate(dateFormat.parse(endDate));
		event.setTitle(title);
		event.setStartTime(startTime);
		event.setEndTime(endTime);
		event.setPerson(person);

		GuestPerson gp = new GuestPerson();
		gp.setPerson(person);
		gp.setEvent(event);
		person.getGuests().add(gp);
		event.getGuests().add(gp);

		String[] tags = StringUtils.split(tagStr, ",");
		for (String str : tags) {
			EventTag tag = new EventTag();
			tag.setTag(str);
			tag.setEvent(event);
			event.getTags().add(tag);
		}

		person.getEventPermission(event);
		person.getEvents().add(event);

		News news = new News();
		news.setAction(Action.EVENT);
		news.setWorld(Action.EVENT.isWorld());
		news.setPerson(person); // enter a feed for your recent action
		news.setMessage(Action.EVENT.convert(person, title));
		person.getNewsPermission(news);
		person.getNews().add(news);
		
		personService.update(person);

		request.setAttribute("event", event);
		request.setAttribute("person", person);
		request.setAttribute("eventStatusType", EventStatusType.values());
		return new ModelAndView("events/eventPage");
	}

	public ModelAndView friends(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String searchString = request.getParameter("searchString");
		String startDate = request.getParameter("startDate");

		Person person = fetchPerson();
		request.setAttribute("events", eventsService.findAllFriendsEvents(
				person, startDate, searchString, 0));
		return new ModelAndView("events/events");
	}

	public ModelAndView world(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String searchString = request.getParameter("searchString");
		String startDate = request.getParameter("startDate");

		Person person = fetchPerson();
		request.setAttribute("events", eventsService.findWorldEvents(person,
				searchString, startDate, 0));
		return new ModelAndView("events/events");
	}

	public ModelAndView attending(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String eventId = request.getParameter("eventId");

		if (StringUtils.isNotBlank(eventId)) {
			Events event = eventsService.get(Long.parseLong(eventId));
			AcceptPerson ap = new AcceptPerson();
			ap.setPerson(person);
			ap.setEvent(event);
			person.getAccepts().add(ap);
			event.getAccept().add(ap);
			event.getGuests().remove(event.findGuestPerson(person));
			eventsService.update(event);

			request.setAttribute("event", event);
		}

		return new ModelAndView("events/eventPage");
	}

	public ModelAndView decline(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String eventId = request.getParameter("eventId");

		if (StringUtils.isNotBlank(eventId)) {
			Events event = eventsService.get(Long.parseLong(eventId));
			DeclinePerson ap = new DeclinePerson();
			ap.setPerson(person);
			ap.setEvent(event);
			person.getDeclines().add(ap);
			event.getDecline().add(ap);
			event.getGuests().remove(event.findGuestPerson(person));
			eventsService.update(event);

			request.setAttribute("event", event);
		}

		return new ModelAndView("events/eventPage");
	}

	public ModelAndView considering(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String eventId = request.getParameter("eventId");
		if (StringUtils.isNotBlank(eventId)) {
			Events event = eventsService.get(Long.parseLong(eventId));
			ConsideringPerson ap = new ConsideringPerson();
			ap.setPerson(person);
			ap.setEvent(event);
			person.getConsiderings().add(ap);
			event.getConsidering().add(ap);
			event.getGuests().remove(event.findGuestPerson(person));
			eventsService.update(event);

			request.setAttribute("event", event);
		}

		return new ModelAndView("events/eventPage");
	}

	// need an edit

	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();

		String eventId = request.getParameter("eventId");

		Events event = eventsService.getEventByPerson(person, Long
				.parseLong(eventId));
		if (event.getPerson().getId().equals(person.getId())) {
			event.setActive(false);
			eventsService.update(event);
		}

		request.setAttribute("person", person);
		request.setAttribute("events", eventsService.findOwnEvents(person,
				null, null, 0));

		return new ModelAndView("events/events");
	}

	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		
		Person person = fetchPerson();
		// get the friendIds for guests list
		// add start and end times
		String description = request.getParameter("description");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String title = request.getParameter("title");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String tagStr = request.getParameter("tags");
		String[] friendIds = request.getParameterValues("friendIds");

		Events event = new Events();

		if (friendIds != null) {
			StringBuilder recipients = new StringBuilder();
			for (String friendId : friendIds) {
				Person friend = person.findFriend(Long.parseLong(friendId))
						.getFriend();
				// Person friend = personService.get(Long.parseLong(friendId));
				GuestPerson gp = new GuestPerson();
				gp.setPerson(friend);
				gp.setEvent(event);
				friend.getGuests().add(gp);
				event.getGuests().add(gp);

				recipients.append(emailService.getPrimaryEmailForPerson(friend).getEmail() + ",");
			}
			EmailUtil.INSTANCE.sendMail("You're Invited",
					"You are invited to " + title + " hosted by "
							+ person.getFullName(), "sleekswap@gmail.com",
					recipients.toString());	
		}

		event.setDescription(description);
		event.setStartDate(dateFormat.parse(startDate));
		event.setEndDate(dateFormat.parse(endDate));
		event.setTitle(title);
		event.setStartTime(startTime);
		event.setEndTime(endTime);
		event.setPerson(person);

		GuestPerson gp = new GuestPerson();
		gp.setPerson(person);
		gp.setEvent(event);
		person.getGuests().add(gp);
		event.getGuests().add(gp);

		String[] tags = StringUtils.split(tagStr, ",");
		for (String str : tags) {
			EventTag tag = new EventTag();
			tag.setTag(str);
			tag.setEvent(event);
			event.getTags().add(tag);
		}

		//person.getEventPermission(event);
		//person.getEvents().add(event);
		
		event.getEventVisibilities().add(new EventVisibility(event, person.getVisibilityForType(PermissionType.EVENT)));
		event.getEventVisibilities().add(new EventVisibility(event, person.getDefaultVisibility()));

		News news = new News();
		news.setAction(Action.EVENT);
		news.setWorld(Action.EVENT.isWorld());
		news.setPerson(person); // enter a feed for your recent action
		news.setMessage(Action.EVENT.convert(person, title));
		person.getNewsPermission(news);
		person.getNews().add(news);

		personService.update(person);

		request.setAttribute("event", event);
		return new ModelAndView("events/eventPage");
	}

	public ModelAndView addcustom(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		
		Person person = fetchPerson();
		// get the friendIds for guests list
		// add start and end times
		String description = request.getParameter("description");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String title = request.getParameter("title");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String tagStr = request.getParameter("tags");
		String[] friendIds = request.getParameterValues("friendIds");
		String visibility = request.getParameter("visibility");

		Events event = new Events();

		VisibilityDomain vd = new VisibilityDomain();
		vd.setVisibility(VisibilityType.valueOf(visibility));
		vd.setActivityType(PermissionType.EVENT);
		vd.setDefaultVisibility(false);
		vd.setName(title);
		visibilityService.save(vd);

		EventVisibility ev = new EventVisibility();
		ev.setVisibilityDomain(vd);
		ev.setEvent(event);

		event.getEventVisibilities().add(ev);

		if (friendIds != null) {
			StringBuilder recipients = new StringBuilder();
			for (String friendId : friendIds) {
				Person friend = person.findFriend(Long.parseLong(friendId))
						.getFriend();
				// Person friend = personService.get(Long.parseLong(friendId));
				GuestPerson gp = new GuestPerson();
				gp.setPerson(friend);
				gp.setEvent(event);
				friend.getGuests().add(gp);
				event.getGuests().add(gp);

				PersonVisibilityDomain pvd = new PersonVisibilityDomain();
				pvd.setPerson(friend);
				pvd.setVisibilityDomain(vd);
				friend.getAllowedPermissions().add(pvd);
				recipients.append(emailService.getPrimaryEmailForPerson(friend).getEmail() + ",");
			}
			EmailUtil.INSTANCE.sendMail("You're Invited",
					"You are invited to " + title + " hosted by "
							+ person.getFullName(), "sleekswap@gmail.com",
					recipients.toString());			
		}

		event.setDescription(description);
		event.setStartDate(dateFormat.parse(startDate));
		event.setEndDate(dateFormat.parse(endDate));
		event.setTitle(title);
		event.setStartTime(startTime);
		event.setEndTime(endTime);
		event.setPerson(person);

		GuestPerson gp = new GuestPerson();
		gp.setPerson(person);
		gp.setEvent(event);
		person.getGuests().add(gp);
		event.getGuests().add(gp);

		String[] tags = StringUtils.split(tagStr, ",");
		for (String str : tags) {
			EventTag tag = new EventTag();
			tag.setTag(str);
			tag.setEvent(event);
			event.getTags().add(tag);
		}

		person.getEventPermission(event);
		person.getEvents().add(event);
		
		News news = new News();
		news.setAction(Action.EVENT);
		news.setWorld(Action.EVENT.isWorld());
		news.setPerson(person); // enter a feed for your recent action
		news.setMessage(Action.EVENT.convert(person, title));
		NewsVisibilityDomain nvd = new NewsVisibilityDomain(news, vd);
		news.getNewsVisibility().add(nvd);
		person.getNews().add(news);

		personService.update(person);

		request.setAttribute("event", event);
		return new ModelAndView("events/eventPage");
	}
		
	public void setEventsService(EventsService eventsService) {
		this.eventsService = eventsService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void setFriendService(FriendService friendService) {
		this.friendService = friendService;
	}

	public void setVisibilityService(VisibilityDomainService visibilityService) {
		this.visibilityService = visibilityService;
	}

}
