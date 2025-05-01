# JournalController

```java
package com.myshopnshare.webapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import com.myshopnshare.model.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.Action;
import com.myshopnshare.core.domain.Journal;
import com.myshopnshare.core.domain.JournalTag;
import com.myshopnshare.core.domain.News;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.service.EmailService;
import com.myshopnshare.core.service.JournalService;
import com.myshopnshare.core.service.JournalVisibilityService;
import com.myshopnshare.core.service.NewsService;
import com.myshopnshare.core.service.NewsVisibilityDomainService;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.utils.EmailUtil;

/**
 * In order to delete own journal, must have a manage journal page dedicate to just deleting.
 * The page will only be reached from your own home page.  The page will be configured to be able to delete the journal.
 * 
 * @author khimung
 *
 */
public class JournalController extends MultiActionController {

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private PersonService personService;
	@Autowired
	private JournalService journalService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private NewsVisibilityDomainService newsVisibilityService;
	@Autowired
	private JournalVisibilityService journalVisibilityService;

	private Person fetchPerson() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User user = (User) principal;

		return personService.findPersonByUsername(user.getUsername());
	}

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String personId = request.getParameter("personId");
		String searchString = request.getParameter("searchString");
		String category = request.getParameter("category");

		Person person = fetchPerson();
		if (StringUtils.trimToNull(personId) == null
				|| person.getId().equals(Long.parseLong(personId))) {
			request.setAttribute("journals", journalService.findOwnJournal(person, category, searchString, 0));
			return new ModelAndView("journal/jsonJournals");
		}
		Person friend = personService.get(Long.parseLong(personId));
		if (!person.isFriends(personId)
				&& !person.getId().equals(friend.getId())) {
			// return blank
			return new ModelAndView("journal/jsonJournals");
		}
		request.setAttribute("journals", journalService.findOneFriendsJournal(
				friend, person, category, searchString, 0));
		return new ModelAndView("journal/jsonJournals");
	}

	public ModelAndView friends(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String searchString = request.getParameter("searchString");
		String category = request.getParameter("category");

		Person person = fetchPerson();
		request.setAttribute("journals", journalService.findAllFriendsJournal(person, category, searchString, 0));
		return new ModelAndView("journal/jsonJournals");
	}

	public ModelAndView flag(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map map = new HashMap();
		Person person = fetchPerson();
		
		String journalId = request.getParameter("faceId");
		if(StringUtils.trimToNull(journalId) != null){
			Journal journal = journalService.get(Long.parseLong(journalId));
			journal.flagged();
			if(journal.violator()){
				journal.setActive(false);
				
				EmailUtil.INSTANCE
				.sendMail(
						"Delete Journal",
						"Your journal entry, " + journal.getTitle() + " has been deleted due to too many complaints from users."
								, "sleekswap@gmail.com", emailService.getPrimaryEmailForPerson(journal.getPerson()).getEmail());				
			}
			journalService.update(journal);
		}
		return new ModelAndView("common/blank");
	}

	
	public ModelAndView world(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String searchString = request.getParameter("searchString");
		String category = request.getParameter("category");

		Person person = fetchPerson();
		request.setAttribute("journals", journalService.findWorldJournal(person, category, searchString, 0));

		return new ModelAndView("journal/jsonJournals");
	}

	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String start = request.getParameter("start");
		String personId = request.getParameter("personId");

		Person person = fetchPerson();
		if (StringUtils.trimToNull(personId) == null
				|| person.getId().equals(Long.parseLong(personId))) {
			return new ModelAndView("journal/jsonJournal");
		}
		Person friend = personService.get(Long.parseLong(personId));
		if (!person.isFriends(personId)
				&& !person.getId().equals(friend.getId())) {
			return new ModelAndView("journal/jsonJournal");
		}

		String journalId = request.getParameter("journalId");
		if (StringUtils.trimToNull(journalId) != null) {
			/** Place holder **/
		} else {

		}
		request.setAttribute("journal", null);
		return new ModelAndView("journal/jsonJournal");
	}

	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();

		String journalId = request.getParameter("journalId");
		Journal journal = journalService.getJournalForPerson(person, Long.parseLong(journalId));
		journal.setActive(false);
		journalService.update(journal);

		request.setAttribute("journal", null);
		return new ModelAndView("journal/jsonJournal");
	}

	public ModelAndView journal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String message = request.getParameter("entry");
		String tagStr = request.getParameter("tags");
		String category = request.getParameter("category");

		Person person = fetchPerson();

		Journal journal = new Journal();
		journal.setPerson(person);
		journal.setEntry(message);
		journal.setCategory(category);

		String[] tags = StringUtils.split(tagStr, ",");
		for (String tag : tags) {
			JournalTag t = new JournalTag();
			t.setTag(tag);
			t.setJournal(journal);
			journal.getTags().add(t);
		}

		person.getJournalPermission(journal);
		person.getJournals().add(journal);

		News news = new News();
		news.setAction(Action.JOURNAL);
		news.setWorld(Action.JOURNAL.isWorld());
		news.setPerson(person); // enter a feed for your recent action
		news.setMessage(Action.JOURNAL.convert(person, " titled " + journal.getTitle()));
		person.getNews().add(news);
		person.getNewsPermission(news);
		
		personService.update(person);

		request.setAttribute("journal", journal);

		return new ModelAndView("journal/jsonJournal");
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void setJournalService(JournalService journalService) {
		this.journalService = journalService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public void setNewsVisibilityService(
			NewsVisibilityDomainService newsVisibilityService) {
		this.newsVisibilityService = newsVisibilityService;
	}

	public void setJournalVisibilityService(
			JournalVisibilityService journalVisibilityService) {
		this.journalVisibilityService = journalVisibilityService;
	}

}
```
