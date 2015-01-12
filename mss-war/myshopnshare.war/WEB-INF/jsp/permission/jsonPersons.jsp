<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<json:object>
  <json:array name="friends" var="friend" items="${friends}">
    <json:object>
   	  <json:property name="id" value="${friend.id}"/>
   	    <json:property name="mini" value="${friend.defaultFace.mini.path}"/>
	    <json:property name="icon" value="${friend.defaultFace.icon.path}"/>	
	    <json:property name="thumbnail" value="${friend.defaultFace.thumbnail.path}"/>
	    <json:property name="glimpse" value="${friend.defaultFace.glimpse.path}"/>
	    <json:property name="fullName" value="${friend.fullName}"/>	      
    </json:object>
  </json:array>
</json:object>