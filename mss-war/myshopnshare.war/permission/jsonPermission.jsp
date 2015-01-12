<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<json:object>
  <json:property name="id" value="${permission.id}"/>
  <json:property name="permissionType" value="${permission.type.label}"/>
  <json:array name="groups" var="group" items="${permission.permissionGroups}">
    <json:object>
   	  <json:property name="id" value="${group.permissionVisibility.groupPerson.id}"/>
      <json:property name="groupName" value="${group.permissionVisibility.groupName}"/>     
	  <json:array name="friends" var="friend" items="${group.permissionVisibility.groupPerson.friends}">
	    <json:object>
	   	  <json:property name="id" value="${friend.id}"/>
	      <json:property name="fullName" value="${friend.fullName}"/>     
	    </json:object>
	  </json:array>
    </json:object>
  </json:array> 
</json:object>