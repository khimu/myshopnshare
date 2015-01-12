package com.myshopnshare.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import com.myshopnshare.model.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.Friend;
import com.myshopnshare.core.domain.GroupPerson;
import com.myshopnshare.core.domain.Permission;
import com.myshopnshare.core.domain.PermissionVisibility;
import com.myshopnshare.core.domain.PermissionVisibilityPerson;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.PersonVisibilityDomain;
import com.myshopnshare.core.domain.VisibilityDomain;
import com.myshopnshare.core.enums.VisibilityType;
import com.myshopnshare.core.service.FriendService;
import com.myshopnshare.core.service.PermissionVisibilityService;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.core.service.PersonVisibilityDomainService;
import com.myshopnshare.core.service.VisibilityDomainService;

/**
 * Manages the groups. Edit, delete, or add a group to person
 * 
 * @author khimung
 * 
 */
public class VisibilityController extends MultiActionController {
	@Autowired
	private PersonService personService;
	@Autowired
	private VisibilityDomainService visibilityService;
	@Autowired
	private PermissionVisibilityService permissionVisibilityService;
	@Autowired
	private FriendService friendService;
	@Autowired
	private PersonVisibilityDomainService personVisibilityService;

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
		request.setAttribute("groups", person.getUserGroups());
		return new ModelAndView("groups/groups");
	}

	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String groupId = request.getParameter("groupId");
		if (StringUtils.trimToNull(groupId) != null) {
			GroupPerson groupPerson = person.findGroup(Long
					.parseLong(groupId));
			request.setAttribute("group", groupPerson);
			// friends is list of Person
			request.setAttribute("friends", groupPerson.getPermissionVisibility().getPermissionVisibilityFriends());
		}
		return new ModelAndView("groups/group");
	}

	public ModelAndView getEdit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String groupId = request.getParameter("groupId");
		if (StringUtils.trimToNull(groupId) != null) {
			GroupPerson groupPerson = person.findGroup(Long.parseLong(groupId));
			request.setAttribute("group", groupPerson);
			// friends is list of person
			request.setAttribute("friends", friendService
					.findAllFriends(person));
		}
		return new ModelAndView("groups/edit");
	}

	/**
	 * Adding a new group and list of friends to the group
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String groupName = request.getParameter("groupName");
		String[] friendIds = request.getParameterValues("friendIds");

		PermissionVisibility userGroup = new PermissionVisibility();
		userGroup.setGroupName(groupName);
		
		GroupPerson groupPerson = new GroupPerson();
		groupPerson.setPermissionVisibility(userGroup);
		groupPerson.setPerson(person);
		person.getUserGroups().add(groupPerson);
		
		userGroup.setGroupPerson(groupPerson);

		if (friendIds != null) {
			// Should probably search all items of type permission and set
			// person to be able to view them.
			for (String friendId : friendIds) {
				Friend friend = person.findFriend(Long.parseLong(friendId));
				
				PermissionVisibilityPerson pvp = new PermissionVisibilityPerson();
				pvp.setFriend(friend.getFriend());
				pvp.setPermissionVisibility(userGroup);
				
				userGroup.getFriends().add(pvp);
			}
		}
		personService.update(person);
		request.setAttribute("group", groupPerson);
		return new ModelAndView("groups/group");
	}

	public ModelAndView edit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String groupId = request.getParameter("groupId");
		String[] friendIds = request.getParameterValues("friendIds");

		String groupName = request.getParameter("groupName");
		if (StringUtils.trimToNull(groupId) != null) {

			GroupPerson groupPerson = person.findGroup(Long.parseLong(groupId));
			groupPerson.getPermissionVisibility().setGroupName(StringUtils.trimToNull(groupName) == null ? groupPerson.getPermissionVisibility().getGroupName() : groupName);

			if (friendIds != null) {
				// Should probably search all items of type permission and set
				// person to be able to view them.
				for (String friendId : friendIds) {
					Person friend = person.findFriend(Long.parseLong(friendId)).getFriend();
					if(groupPerson.getFriends().contains(friend)){
						continue;
					}
					PermissionVisibilityPerson pvp = new PermissionVisibilityPerson();
					pvp.setFriend(friend);
					pvp.setPermissionVisibility(groupPerson.getPermissionVisibility());
					groupPerson.getPermissionVisibility().getFriends().add(pvp);

					for (Permission permission : person.getPermissions()) {
						VisibilityDomain v = person
								.getVisibilityForType(permission.getType());
						if (permission.hasGroup(groupPerson.getPermissionVisibility())) {
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
			}

			personService.update(person);
			request.setAttribute("group", groupPerson);
		}

		return new ModelAndView("groups/group");
	}

	/** Removes the group from person */
	/** Don't know what this remove is for? */
	public ModelAndView remove(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String permissionVisibilityId = request.getParameter("groupId");

		if (StringUtils.trimToNull(permissionVisibilityId) != null) {
			GroupPerson userGroup = person.findPermissionVisibilityInGroup(Long
					.parseLong(permissionVisibilityId));
			person.getUserGroups().remove(userGroup);
			userGroup.setPerson(null);

			for (PermissionVisibilityPerson friend : userGroup.getPermissionVisibility().getFriends()) {
				/*
				 * Don't think i need to do this since cascade will delete trickled from deleting GroupPerson -> PermissionVisibility -> ppv and pvd
				 */

				for (Permission permission : person.getPermissions()) {
					/*
					PermissionPermissionVisibility ppv = permission.findGroup(userGroup.getPermissionVisibility());
					if(ppv != null){
						permission.getPermissionGroups().remove(ppv);
						ppv.setPermission(null);
						ppv.setPermissionVisibility(null);
					}
					*/
					
					VisibilityDomain v = person.getVisibilityForType(permission
							.getType());
					
					//if (permission.hasGroup(userGroup.getPermissionVisibility())) {
						PersonVisibilityDomain pvd = friend.getFriend().findPersonVisibility(v);
						pvd.setPerson(null);
						pvd.setVisibilityDomain(null);
						// personVisibilityService.delete(pvd);
					//}
				}
			}
			personService.update(person);
		}
		return new ModelAndView("common/blank");
	}

	/**
	 * Same as remove
	 * Not sure which jsp is using
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String groupId = request.getParameter("groupId");

		if (StringUtils.trimToNull(groupId) != null) {
			GroupPerson groupPerson = person.findGroup(Long.parseLong(groupId));
			person.getUserGroups().remove(groupPerson);
			groupPerson.setPerson(null);

			for (Person friend : groupPerson.getFriends()) {
				for (Permission permission : person.getPermissions()) {
					VisibilityDomain v = person.getVisibilityForType(permission
							.getType());
					if (permission.hasGroup(groupPerson.getPermissionVisibility())) {
						PersonVisibilityDomain pvd = friend.findPersonVisibility(v);
						personVisibilityService.delete(pvd);
					}
				}
			}
			// permissionVisibilityService.delete(groupPerson.getPermissionVisibility());
			personService.update(person);
		}
		return new ModelAndView("common/blank");
	}

	/** Every thing is assign a defualt visibility **/
	public ModelAndView update(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String visibility = request.getParameter("visibility");

		/**
		 * This is going to leave orphan ItemVisibilityDomain, etc. Gonna need
		 * database clean up script in the future.
		 **/
		if (StringUtils.trimToNull(visibility) != null) {
			person.getDefaultVisibility().setVisibility(
					VisibilityType.valueOf(visibility));
			visibilityService.update(person.getDefaultVisibility());
		}

		request.setAttribute("visibilitySetting", visibility);
		return new ModelAndView("groups/visibility");
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void setVisibilityService(VisibilityDomainService visibilityService) {
		this.visibilityService = visibilityService;
	}

	public void setPermissionVisibilityService(
			PermissionVisibilityService permissionVisibilityService) {
		this.permissionVisibilityService = permissionVisibilityService;
	}

	public void setFriendService(FriendService friendService) {
		this.friendService = friendService;
	}

	public void setPersonVisibilityService(
			PersonVisibilityDomainService personVisibilityService) {
		this.personVisibilityService = personVisibilityService;
	}

}
