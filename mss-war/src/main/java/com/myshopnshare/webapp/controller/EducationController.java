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
import com.myshopnshare.core.domain.Education;
import com.myshopnshare.core.domain.Friend;
import com.myshopnshare.core.domain.Institution;
import com.myshopnshare.core.domain.News;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.Profile;
import com.myshopnshare.core.enums.InstitutionType;
import com.myshopnshare.core.service.InstitutionService;
import com.myshopnshare.core.service.NewsService;
import com.myshopnshare.core.service.NewsVisibilityDomainService;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.model.User;

public class EducationController extends MultiActionController {

	/*
	 * Change profile picture and any other interest
	 */
	@Autowired
	private PersonService personService;
	
	private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
	static {
		simpleDateFormat.applyPattern("MM/dd/yyyy");
	}
	
	@Autowired
	private InstitutionService institutionService;
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private NewsVisibilityDomainService newsVisibilityService;

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
		request.setAttribute("educations", person.getProfile().getEducations());
		request.setAttribute("institutionTypes", InstitutionType.values());
		request.setAttribute("institutions", institutionService.getAllActive());
		return new ModelAndView("profile/education/education");
	}
	
	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String educationId = request.getParameter("educationId");
		Education education = person.getProfile().findEducation(educationId);
		education.setActive(false);
		personService.update(person);

		return new ModelAndView("common/blank");
	}
	
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String educationId = request.getParameter("educationId");
		request.setAttribute("education", person.getProfile().findEducation(educationId));
		request.setAttribute("institutionTypes", InstitutionType.values());
		request.setAttribute("institutions", institutionService.getAllActive());
		return new ModelAndView("profile/education/edit");
	}	
	
	public ModelAndView edit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		
		String educationId = request.getParameter("id");
		Education education = person.getProfile().findEducation(educationId);

		String degree = request.getParameter("degree");
		String major = request.getParameter("major");
		String endYear = request.getParameter("endYear");
		String startYear = request.getParameter("startYear");
		String institutionName = request.getParameter("institutionName");
		String institutionType = request.getParameter("institutionType");
		String institutionId = request.getParameter("institutionId");

		education.setStartYear(StringUtils.trimToNull(startYear) == null ? education.getStartYear() : startYear);
		education.setEndYear(StringUtils.trimToNull(endYear) == null ? education.getEndYear() : endYear);
		education.setInstitutionType(StringUtils.trimToNull(institutionType) == null ? education.getInstitutionType() : InstitutionType.valueOf(institutionType));
		education.setDegree(StringUtils.trimToNull(degree) == null ? education.getDegree() : degree);
		education.setMajor(StringUtils.trimToNull(major) == null ? education.getMajor() : major);
		education.setInstitutionName(StringUtils.trimToNull(institutionName) == null ? education.getInstitutionName() : institutionName);		

		if(StringUtils.trimToNull(institutionId) != null){
			Institution institution = institutionService.get(Long
					.parseLong(institutionId));
			education.setInstitution(institution);
			
			Friend institutionFriend = new Friend(institution, person);
			institution.getFriends().add(institutionFriend);
			
			Friend friend = new Friend(person, institution);
			person.getFriends().add(friend);
			
			News news = new News();
			news.setAction(Action.INSTITUTION);
			news.setWorld(Action.INSTITUTION.isWorld());
			news.setMessage(Action.INSTITUTION.convert(person, institution));
			news.setPerson(person);
			person.getNewsPermission(news);
			person.getNews().add(news);
			
			News friendnews = new News();
			friendnews.setAction((Action.MEMBER));
			friendnews.setWorld(Action.MEMBER.isWorld());
			friendnews.setMessage(Action.MEMBER.convert(institution, person));
			friendnews.setPerson(institution);
			
			institution.getNewsPermission(friendnews);
			institution.getNews().add(friendnews);

			institutionService.update(institution);	
		}
		
		personService.update(person);
		request.setAttribute("person", person);
		request.setAttribute("institutionTypes", InstitutionType.values());
		request.setAttribute("educations", person.getProfile().getActiveEducations());
		request.setAttribute("institutions", institutionService.getAllActive());
		
		return new ModelAndView("profile/education/education");
	}	
	

	public ModelAndView education(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		Profile profile = person.getProfile();

		String degree = request.getParameter("degree");
		String major = request.getParameter("major");
		String endYear = request.getParameter("endYear");
		String startYear = request.getParameter("startYear");
		String institutionName = request.getParameter("institutionName");
		String institutionType = request.getParameter("institutionType");
		String institutionId = request.getParameter("institutionId");

		Education education = new Education();
		education.setProfile(profile);
		education.setStartYear(startYear);
		education.setEndYear(endYear);
		education.setInstitutionType(InstitutionType.valueOf(institutionType));
		education.setDegree(degree);
		//education.setGraduationDate(simpleDateFormat.parse(graduationDate));
		education.setMajor(major);
		education.setInstitutionName(institutionName);
		if (StringUtils.trimToNull(institutionId) != null) {
			Institution institution = institutionService.get(Long
					.parseLong(institutionId));
			Friend institutionFriend = new Friend(institution, person);
			institution.getFriends().add(institutionFriend);
			
			
			Friend friend = new Friend(person, institution);
			person.getFriends().add(friend);
			
			News news = new News();
			news.setAction(Action.INSTITUTION);
			news.setWorld(Action.INSTITUTION.isWorld());
			news.setMessage(Action.INSTITUTION.convert(person, institution));
			news.setPerson(person);
			person.getNewsPermission(news);
			person.getNews().add(news);
			
			News friendnews = new News();
			friendnews.setAction((Action.MEMBER));
			friendnews.setWorld(Action.MEMBER.isWorld());
			friendnews.setMessage(Action.MEMBER.convert(institution, person));
			friendnews.setPerson(institution);
			
			institution.getNewsPermission(friendnews);
			institution.getNews().add(friendnews);

			institutionService.update(institution);
		}

		profile.getEducations().add(education);

		person.setProfile(profile);
		personService.update(person);

		request.setAttribute("person", person);
		request.setAttribute("educations", profile.getActiveEducations());
		request.setAttribute("institutions", institutionService.getAllActive());
		request.setAttribute("institutionTypes", InstitutionType.values());
		
		return new ModelAndView("profile/education/education");
	}

}
