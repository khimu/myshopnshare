<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<json:object escapeXml="false">
  <json:array name="newzz" var="newz" items="${newzz}">
    <json:object>
   	  <json:property name="id" value="${newz.id}"/>
      <json:property name="message" value="${newz.message}"/>
      <json:property name="enteredDate">
      	<fmt:formatDate value="${newz.enteredDate}" pattern="MM/dd/yyyy"/>
      </json:property>
      <json:property name="newsPersonId" value="${newz.person.id}"/>
    </json:object>
  </json:array>
</json:object>