package com.myshopnshare.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.context.SecurityContextHolder;
import com.myshopnshare.model.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.GroupPerson;
import com.myshopnshare.core.domain.Permission;
import com.myshopnshare.core.domain.PermissionVisibilityPerson;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.PersonVisibilityDomain;
import com.myshopnshare.core.domain.VisibilityDomain;
import com.myshopnshare.core.service.PermissionService;
import com.myshopnshare.core.service.PermissionVisibilityService;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.core.service.PersonVisibilityDomainService;

/**
 * Manages the users that are part of the groups
 * Add or delete the users from the group
 * 
 * @author khimung
 *
 */
public class UserGroupController extends MultiActionController {
	private PersonService personService;
	private PermissionVisibilityService permissionVisibilityService;
	private PersonVisibilityDomainService personVisibilityService;
	private PermissionService permissionService;

	private Person fetchPerson() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User user = (User) principal;

		return personService.findPersonByUsername(user.getUsername());
	}

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		return new ModelAndView("common/blank");
	}

	public ModelAndView all(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String groupId = request.getParameter("groupId");
		if (StringUtils.trimToNull(groupId) != null) {
			GroupPerson groupPerson = person.findGroup(Long.parseLong(groupId));
			request.setAttribute("friends", groupPerson.getPermissionVisibility().getFriends());
		}

		return new ModelAndView("permission/jsonPersons");
	}

	/**
	 * Add user to group
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String groupId = request.getParameter("groupId");
		String friendId = request.getParameter("friendId");

		if (StringUtils.trimToNull(groupId) != null
				&& StringUtils.trimToNull(friendId) != null) {
			GroupPerson groupPerson = person.findGroup(Long
					.parseLong(groupId));
			
			//PermissionVisibility pv = permissionVisibilityService.get(Long
			//		.parseLong(groupId));
			
			Person friend = personService.get(Long.parseLong(friendId));
			
			PermissionVisibilityPerson pvp = new PermissionVisibilityPerson();
			pvp.setFriend(friend);
			pvp.setPermissionVisibility(groupPerson.getPermissionVisibility());
			groupPerson.getPermissionVisibility().getFriends().add(pvp);
			
			personService.update(person);
			
			// give users permission to the activity that the group is part of
			for(Permission permission : person.getPermissions()){
				VisibilityDomain v = person.getVisibilityForType(permission.getType());
				if(permission.hasGroup(groupPerson.getPermissionVisibility())){
					PersonVisibilityDomain pvd = personVisibilityService.findPersonWithVisibility(v.getId(), friend.getId());
					if(pvd == null){
						pvd = new PersonVisibilityDomain();
					}					
					pvd.setVisibilityDomain(v);
					pvd.setPerson(friend);
					personVisibilityService.save(pvd);					
				}
			}	
		}
		return new ModelAndView("common/blank");
	}

	/**
	 * Remove user from group
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView remove(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String groupId = request.getParameter("groupId");
		String friendId = request.getParameter("friendId");

		/**
		 * This is going to leave orphan ItemVisibilityDomain, etc. Gonna need
		 * database clean up script in the future.
		 **/
		if (StringUtils.trimToNull(groupId) != null && StringUtils.trimToNull(friendId) != null) {		
			GroupPerson groupPerson = person.findGroup(Long.parseLong(groupId));
			Person friend = person.findFriend(Long.parseLong(friendId)).getFriend();
			// Person friend = personService.get(Long.parseLong(friendId));
			PermissionVisibilityPerson pvf = groupPerson.getPermissionVisibilityFriend(friend);
			groupPerson.getPermissionVisibility().getFriends().remove(pvf);
			pvf.setFriend(null);
			pvf.setPermissionVisibility(null);
					
			// if this person is in 2 groups, then we have a problem.  there is going to be a personvd for this person meant for a different group
			
			// Loop through the permissions for each activity and check if group has permission to this activity
			// If group has permission to the activity, remove the friend from the activity
			for(Permission permission : groupPerson.getPermissions()){
				VisibilityDomain v = person.getVisibilityForType(permission.getType());
				if(permission.hasGroup(groupPerson.getPermissionVisibility())){
					PersonVisibilityDomain pvd = friend.findPersonVisibility(v);	
					pvd.setPerson(null);
					pvd.setVisibilityDomain(null);
					//personVisibilityService.delete(pvd);
				}
			}	
			
			personService.update(person);

		}
		return new ModelAndView("common/blank");
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void setPermissionVisibilityService(
			PermissionVisibilityService permissionVisibilityService) {
		this.permissionVisibilityService = permissionVisibilityService;
	}

	public void setPersonVisibilityService(
			PersonVisibilityDomainService personVisibilityService) {
		this.personVisibilityService = personVisibilityService;
	}

	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

}
