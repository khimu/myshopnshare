<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<json:object>
  <json:array name="friends" var="friend" items="${friends}">
    <json:object>
      <json:property name="id" value="${friend.id}"/>
      <json:property name="color" value="${friend.color}"/>
	  <json:object name="friendProfile">
	    <json:property name="friendId" value="${friend.friend.id}"/>
	    <json:property name="icon" value="${friend.friend.defaultFace.icon.path}"/>	
	    <json:property name="mini" value="${friend.friend.defaultFace.mini.path}"/>	
	    <json:property name="thumbnail" value="${friend.friend.defaultFace.thumbnail.path}"/>
	    <json:property name="glimpse" value="${friend.friend.defaultFace.glimpse.path}"/>
	    <json:property name="fullName" value="${friend.friend.fullName}"/>	
	    <json:property name="headline" value="${friend.friend.currentHeadline}"/>      
	  </json:object> 
	</json:object>
  </json:array>
</json:object>