<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<c:forEach items="${mylist}" var="item">
${item}</BR>
</c:forEach>

${user.username}

<form:form commandName="user" action="edit.html" method="post">
	<c:out value="${users.username}" />
</form:form>