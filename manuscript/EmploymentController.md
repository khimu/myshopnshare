# EmploymentController

```java
package com.myshopnshare.webapp.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.Action;
import com.myshopnshare.core.domain.Employer;
import com.myshopnshare.core.domain.Employment;
import com.myshopnshare.core.domain.Friend;
import com.myshopnshare.core.domain.News;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.Profile;
import com.myshopnshare.core.service.EmployerService;
import com.myshopnshare.core.service.NewsService;
import com.myshopnshare.core.service.NewsVisibilityDomainService;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.model.User;

public class EmploymentController extends MultiActionController {
	private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
	static {
		simpleDateFormat.applyPattern("MM/dd/yyyy");
	}
	
	@Autowired
	private EmployerService employerService;
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private NewsVisibilityDomainService newsVisibilityService;

	@Autowired
	private PersonService personService;

	private Person fetchPerson() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User user = (User) principal;

		return personService.findPersonByUsername(user.getUsername());
	}

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		request.setAttribute("person", person);

		request
				.setAttribute("employment", person.getProfile()
						.getActiveEmployments());

		request.setAttribute("employers", employerService.getAllActive());

		return new ModelAndView("profile/employer/employer");
	}

	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String employmentId = request.getParameter("employmentId");
		Employment employment = person.getProfile().findEmployment(employmentId);
		employment.setActive(false);
		personService.update(person);

		return new ModelAndView("common/blank");
	}
	
	public ModelAndView edit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();

		String employmentId = request.getParameter("id");
		Employment employment = person.getProfile().findEmployment(employmentId);

		String title = request.getParameter("title");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String department = request.getParameter("department");
		String division = request.getParameter("division");
		String employerName = request.getParameter("employerName");
		String employerId = request.getParameter("employerId");

		employment.setTitle(StringUtils.trimToNull(title) == null ? employment.getTitle() : title);
		employment.setStartDate(simpleDateFormat.parse(startDate));
		employment.setEndDate(StringUtils.trimToNull(endDate) == null ? employment.getEndDate() : simpleDateFormat.parse(endDate));
		employment.setDepartment(StringUtils.trimToNull(department) == null ? employment.getDepartment() : department);
		employment.setDivision(StringUtils.trimToNull(division) == null ? employment.getDivision() : division);
		employment.setEmployerName(StringUtils.trimToNull(employerName) == null ? employment.getEmployerName() : employerName);

		if (StringUtils.trimToNull(employerId) != null) {
			Employer employer = employerService.get(Long.parseLong(employerId));
			Friend institutionFriend = new Friend(employer, person);
			employer.getFriends().add(institutionFriend);
			
			Friend friend = new Friend(person, employer);
			person.getFriends().add(friend);

			News news = new News();
			news.setAction((Action.EMPLOYED));
			news.setWorld(Action.EMPLOYED.isWorld());
			news.setMessage(Action.EMPLOYED.convert(person, employer));
			news.setPerson(person);
			person.getNewsPermission(news);
			person.getNews().add(news);

			News friendnews = new News();
			friendnews.setAction((Action.MEMBER));
			friendnews.setWorld(Action.MEMBER.isWorld());
			friendnews.setMessage(Action.MEMBER.convert(employer, person));
			friendnews.setPerson(employer);
			
			employer.getNewsPermission(friendnews);	
			employer.getNews().add(friendnews);
			
			employerService.update(employer);
		}
	
		request.setAttribute("employers", employerService.getAllActive());
		request.setAttribute("employments", person.getProfile().getActiveEmployments());

		return new ModelAndView("profile/employer/employer");
	}
	
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String employmentId = request.getParameter("employmentId");
		request.setAttribute("employment", person.getProfile().findEmployment(employmentId));
		request.setAttribute("employers", employerService.getAllActive());

		return new ModelAndView("profile/employer/edit");
	}	
	
	public ModelAndView employer(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		Profile profile = person.getProfile();

		String title = request.getParameter("title");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String department = request.getParameter("department");
		String division = request.getParameter("division");
		String employerName = request.getParameter("employerName");
		String employerId = request.getParameter("employerId");

		Employment employment = new Employment();
		employment.setProfile(profile);

		if (StringUtils.trimToNull(employerId) != null) {
			Employer employer = employerService.get(Long.parseLong(employerId));
			Friend institutionFriend = new Friend(employer, person);
			employer.getFriends().add(institutionFriend);
			
			Friend friend = new Friend(person, employer);
			person.getFriends().add(friend);

			News news = new News();
			news.setAction((Action.EMPLOYED));
			news.setWorld(Action.EMPLOYED.isWorld());
			news.setMessage(Action.EMPLOYED.convert(person, employer));
			news.setPerson(person);
			person.getNewsPermission(news);
			person.getNews().add(news);

			News friendnews = new News();
			friendnews.setAction((Action.MEMBER));
			friendnews.setWorld(Action.MEMBER.isWorld());
			friendnews.setMessage(Action.MEMBER.convert(employer, person));
			friendnews.setPerson(employer);
			employer.getNewsPermission(friendnews);	
			employer.getNews().add(friendnews);
		
			employerService.update(employer);
		}

		employment.setTitle(title);
		employment.setStartDate(simpleDateFormat.parse(startDate));
		employment.setEndDate(StringUtils.trimToNull(endDate) == null ? employment.getEndDate() : simpleDateFormat.parse(endDate));
		employment.setDepartment(department);
		employment.setDivision(division);
		employment.setEmployerName(employerName);

		profile.getEmployments().add(employment);
		person.setProfile(profile);

		personService.update(person);

		request.setAttribute("employers", employerService.getAllActive());
		request.setAttribute("employments", profile.getActiveEmployments());

		return new ModelAndView("profile/employer/employer");
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void setEmployerService(EmployerService employerService) {
		this.employerService = employerService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public void setNewsVisibilityService(
			NewsVisibilityDomainService newsVisibilityService) {
		this.newsVisibilityService = newsVisibilityService;
	}

}
```
