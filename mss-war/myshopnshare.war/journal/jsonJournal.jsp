<%@ include file="/WEB-INF/jsp/includes.jsp"%>

    <json:object>
   	  <json:property name="id" value="${journal.id}"/>
      <json:property name="category" value="${journal.category}"/>
   	  
   	  <c:set var="alltags" />
   	  
   	  <c:forEach items="${journal.tags}" var="tags">
   	  	<c:set var="alltags">
   	  		${alltags} ${tags.tag}
   	  	</c:set>
   	   </c:forEach>
   	   
   	  <json:property name="tags" value="${alltags}"/>
   	  <json:property name="message" value="${journal.entry}"/>
   	  <json:property name="enteredDate" >
   	 	 <fmt:formatDate value="${journal.enteredDate}" pattern="MM/dd/yyyy"/>
   	  </json:property>
   	  
   	  <json:property name="journalPersonId" value="${journal.person.id}" />
   	  <json:property name="fullName" value="${journal.person.fullName}" />
   	  <json:property name="faceIcon" value="${journal.person.defaultFace.icon.path}" />
    </json:object>