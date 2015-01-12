<%@ include file="/WEB-INF/jsp/includes.jsp"%>

    <json:object>
   	  <json:property name="id" value="${scribble.id}"/>
      <json:property name="category" value="${scribble.category}"/>
   	  
   	  <c:set var="alltags" />
   	  
   	  <c:forEach items="${scribble.tags}" var="tags">
   	  	<c:set var="alltags">
   	  		${alltags} ${tags.tag}
   	  	</c:set>
   	   </c:forEach>
   	   
   	  <json:property name="tags" value="${alltags}"/>
   	  <json:property name="message" value="${scribble.message}"/>
   	  <json:property name="enteredDate" >
   	 	 <fmt:formatDate value="${scribble.enteredDate}" pattern="MM/dd/yyyy"/>
   	  </json:property>
   	  <json:property name="fullName" value="${scribble.scribbler.fullName}"/>
   	  <json:property name="faceIcon" value="${scribble.scribbler.defaultFace.icon.path}"/>
    </json:object>