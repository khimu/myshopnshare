<%@ include file="/WEB-INF/jsp/includes.jsp"%>

    <json:object>
	 <json:property name="commenterIcon" value="${comment.commenter.defaultFace.icon.path}"/>
	 <json:property name="fullName" value="${comment.commenter.fullName}"/>
	 <json:property name="itemIcon" value="${comment.item.icon.path}"/>
	 <json:property name="message" value="${comment.message}"/>
	 <json:property name="id" value="${comment.id}"/>
	 <json:property name="enteredDate">
	 	<fmt:formatDate pattern="MM/dd/yyyy hh:mm:ss" value="${comment.enteredDate}" />
	 </json:property>
    </json:object>