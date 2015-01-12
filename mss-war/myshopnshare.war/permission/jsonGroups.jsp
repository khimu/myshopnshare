<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<json:object>
  <json:array name="groups" var="group" items="${groups}">
    <json:object>
   	  <json:property name="id" value="${group.id}"/>
      <json:property name="groupName" value="${group.permissionVisibility.groupName}"/>     
    </json:object>
  </json:array>
</json:object>