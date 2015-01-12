package com.myshopnshare.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import com.myshopnshare.model.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.Profile;
import com.myshopnshare.core.enums.InstitutionType;
import com.myshopnshare.core.service.EmployerService;
import com.myshopnshare.core.service.InstitutionService;
import com.myshopnshare.core.service.PersonService;

public class ProfileController extends MultiActionController {

	/*
	 * Change profile picture and any other interest
	 */
	@Autowired
	private PersonService personService;
	@Autowired
	private InstitutionService institutionService;
	@Autowired
	private EmployerService employerService;

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
		request.setAttribute("profile", person.getProfile());
		request.setAttribute("employments", person.getProfile().getActiveEmployments());
		request.setAttribute("educations", person.getProfile().getActiveEducations());

		request.setAttribute("institutions", institutionService.getAllActive());
		request.setAttribute("employers", employerService.getAllActive());
		request.setAttribute("institutionTypes", InstitutionType.values());

		return new ModelAndView("profile/profileLayout");
	}

	public ModelAndView profile(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		Profile profile = person.getProfile();

		String about = request.getParameter("about");
		String activities = request.getParameter("activities");
		String expertise = request.getParameter("expertise");
		String language = request.getParameter("language");
		String aim = request.getParameter("aim");

		profile.setAbout(StringUtils.trimToNull(about) == null ? profile.getAbout() : about);
		profile.setActivities(StringUtils.trimToNull(activities) == null ? profile.getActivities() : activities);
		profile.setExpertise(StringUtils.trimToNull(expertise) == null ? profile.getExpertise() : expertise);
		profile.setLanguage(StringUtils.trimToNull(language) == null ? profile.getLanguage() : language);
		profile.setAim(StringUtils.trimToNull(aim) == null ? profile.getAim() : aim);
		person.setProfile(profile);

		personService.update(person);

		request.setAttribute("profile", profile);

		return new ModelAndView("profile/form");
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void setInstitutionService(InstitutionService institutionService) {
		this.institutionService = institutionService;
	}

	public void setEmployerService(EmployerService employerService) {
		this.employerService = employerService;
	}

}
