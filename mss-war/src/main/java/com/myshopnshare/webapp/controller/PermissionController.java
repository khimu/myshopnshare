package com.myshopnshare.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import com.myshopnshare.model.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.GroupPerson;
import com.myshopnshare.core.domain.Permission;
import com.myshopnshare.core.domain.PermissionPermissionVisibility;
import com.myshopnshare.core.domain.PermissionVisibilityPerson;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.PersonVisibilityDomain;
import com.myshopnshare.core.domain.VisibilityDomain;
import com.myshopnshare.core.enums.PermissionType;
import com.myshopnshare.core.service.PermissionService;
import com.myshopnshare.core.service.PermissionVisibilityService;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.core.service.PersonVisibilityDomainService;

/**
 * Adds groups to an activity or removes groups from an activity.
 * @author khimung
 *
 */
public class PermissionController extends MultiActionController {
	
	@Autowired
	private PersonService personService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private PersonVisibilityDomainService personVisibilityDomainService;

	@Autowired
	private PermissionVisibilityService permissionVisibilityService;

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

		request.setAttribute("groups", person.getUserGroups());
		request.setAttribute("permissions", person.getPermissions());
		request.setAttribute("permissionTypes", PermissionType.values());
		return new ModelAndView("permission/permissions");
	}

	public ModelAndView all(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		request.setAttribute("groups", person.getUserGroups());
		return new ModelAndView("permission/jsonGroups");
	}

	public ModelAndView getByType(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String permissionType = request.getParameter("permissionType");
		if (StringUtils.trimToNull(permissionType) == null) {
			return new ModelAndView("common/blank");
		}
		request.setAttribute("permission", person.getPermissionForType(PermissionType.valueOf(permissionType)));
		return new ModelAndView("permission/jsonPermission");
	}	
	
	/*
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String permissionId = request.getParameter("permissionId");
		if (StringUtils.trimToNull(permissionId) == null) {
			return new ModelAndView("common/blank");
		}
		Permission permission = person.findPermission(Long.parseLong(permissionId));
		request.setAttribute("groups", permission.getPermissionGroups());
		return new ModelAndView("permission/jsonGroups");
	}
	*/

	/**
	 * Exceptions should go to login page.. Like when a hacker tries to access
	 * method with no credential
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String permissionId = request.getParameter("permissionId");
		String groupId = request.getParameter("groupId");

		if (StringUtils.trimToNull(permissionId) != null) {
			Permission permission = permissionService.get(Long
					.parseLong(permissionId));

			GroupPerson groupPerson = person.findGroup(Long
					.parseLong(groupId));
			
			if(permission.hasGroup(groupPerson.getPermissionVisibility())){
				permissionService.update(permission);
				return new ModelAndView("permission/permission");				
			}

			VisibilityDomain vd = person.getVisibilityForType(permission
					.getType());
			
			//pv.setVisibilityDomain(vd);
			//pv.setPermission(permission);

			for (PermissionVisibilityPerson friend : groupPerson.getPermissionVisibility().getFriends()) {
				PersonVisibilityDomain pvd = personVisibilityDomainService.findPersonWithVisibility(vd.getId(), friend.getFriend().getId());
				if(pvd == null){
					pvd = new PersonVisibilityDomain();
				}
				pvd.setPerson(friend.getFriend());
				pvd.setVisibilityDomain(vd);
				personVisibilityDomainService.update(pvd);
			}

			PermissionPermissionVisibility ppv = new PermissionPermissionVisibility();
			ppv.setPermission(permission);
			ppv.setPermissionVisibility(groupPerson.getPermissionVisibility());
			
			permission.getPermissionGroups().add(ppv);

			permissionService.update(permission);

			request.setAttribute("permission", permission);
		}
		return new ModelAndView("permission/jsonPermission");
	}

	/**
	 * Called from permissions.jsp
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView addBulk(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String permissionId = request.getParameter("permissionId");
		String[] groupIds = request.getParameterValues("groupId");

		if (StringUtils.trimToNull(permissionId) != null) {
			Permission permission = person.findPermission(Long
					.parseLong(permissionId));
			if (groupIds != null) {
				for (String groupId : groupIds) {
					GroupPerson groupPerson = person.findGroup(Long.parseLong(groupId));

					if(permission.hasGroup(groupPerson.getPermissionVisibility())){
						permissionService.update(permission);
						return new ModelAndView("permission/jsonPermission");			
					}
					
					VisibilityDomain vd = person
							.getVisibilityForType(permission.getType());
					
					// suspect that this is causing the permission issue where
					// the group can only be part of one activity.
					
					// pv.setPermission(permission);
					// pv.setVisibilityDomain(vd);

					for (PermissionVisibilityPerson friend : groupPerson.getPermissionVisibility().getFriends()) {
						PersonVisibilityDomain pvd = friend.getFriend().findPersonVisibility(vd);
						if(pvd == null){
							pvd = new PersonVisibilityDomain();
						}
						pvd.setPerson(friend.getFriend());
						pvd.setVisibilityDomain(vd);
						friend.getFriend().getPersonVisibilities().add(pvd);
						// personVisibilityDomainService.update(pvd);
					}
					
					PermissionPermissionVisibility ppv = new PermissionPermissionVisibility();
					ppv.setPermission(permission);
					ppv.setPermissionVisibility(groupPerson.getPermissionVisibility());

					permission.getPermissionGroups().add(ppv);
					
				}
				// permissionService.update(permission);
				personService.update(person);
			}

			request.setAttribute("permission", permission);
		}
		return new ModelAndView("permission/jsonPermission");
	}

	/**
	 * Removes visibilityDomain from permission In other words, removes groups
	 * 
	 * Used by permissions.jsp
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView remove(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String permissionId = request.getParameter("permissionId");
		// String permissionPermissionVisibilityId = request.getParameter("userGroupId");
		String groupId = request.getParameter("userGroupId");

		if (StringUtils.trimToNull(permissionId) != null) {
			Permission permission = person.findPermission(Long.parseLong(permissionId));
			GroupPerson groupPerson = person.findGroup(Long.parseLong(groupId));
			
			PermissionPermissionVisibility ppv = permission.findGroup(groupPerson.getPermissionVisibility());
			permission.getPermissionGroups().remove(ppv);
			ppv.setPermission(null);
			ppv.setPermissionVisibility(null);
			//permissionService.update(permission);
			
			VisibilityDomain vd = person.getVisibilityForType(permission.getType());

			//pv.setVisibilityDomain(null);
			//pv.setPermission(null);

			for (PermissionVisibilityPerson friend : groupPerson.getPermissionVisibility().getFriends()) {
				PersonVisibilityDomain pvd = friend.getFriend().findPersonVisibility(vd);
				if(pvd != null){
					pvd.setPerson(null);
					pvd.setVisibilityDomain(null);
					//personVisibilityDomainService.delete(pvd);
					friend.getFriend().getPersonVisibilities().remove(pvd);
				}
			}

			// permissionVisibilityService.update(pv);
			// permissionVisibilityService.delete(pv);
			personService.update(person);
		}
		return new ModelAndView("common/blank");
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	public void setPermissionVisibilityService(
			PermissionVisibilityService permissionVisibilityService) {
		this.permissionVisibilityService = permissionVisibilityService;
	}

	public void setPersonVisibilityDomainService(
			PersonVisibilityDomainService personVisibilityDomainService) {
		this.personVisibilityDomainService = personVisibilityDomainService;
	}

}
