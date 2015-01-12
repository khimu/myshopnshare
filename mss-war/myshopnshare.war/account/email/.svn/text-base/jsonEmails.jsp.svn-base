<%@ include file="/WEB-INF/jsp/includes.jsp"%>


<json:object>
  <json:array name="emails" var="email" items="${emails}">
    <json:object>
    	<json:property name="id" value="${email.id}"/>
      <json:property name="email" value="${email.email}"/>
      <json:property name="primaryEmail" value="${email.primaryEmail}"/>
      <json:property name="emailType" value="${email.type.value}"/>  
      <json:property name="visibility" value="${email.visibility.value}"/>  
    </json:object>
  </json:array>
</json:object>