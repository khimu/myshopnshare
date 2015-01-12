<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<json:object>
	  <json:array name="faces" var="face" items="${faces}">
	    <json:object>
	   	  <json:property name="id" value="${face.id}"/>
	   	    <json:property name="defaultFace" value="${face.defaultFace}"/>
	      <json:property name="icon" value="${face.icon.path}"/>
	      <json:property name="thumbnail" value="${face.thumbnail.path}"/>
	      <json:property name="glimpse" value="${face.glimpse.path}"/>
	      <json:property name="description" value="${face.person.firstName} ${face.person.lastName}"/>
	      <json:property name="caption" value="${face.caption}"/>
	    </json:object>
	  </json:array>
</json:object>