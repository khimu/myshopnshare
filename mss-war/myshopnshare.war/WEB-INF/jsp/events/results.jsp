<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

	<ol class="item-journals">
		<c:forEach items="${events}" var="event">		
		<li id="event-entry-${event.id}" style="list-style-type: none;">
			<div class="posting-header">
				<div style="float: left;">${event.title}</div>
				<div style="float: right;"><c:if test="${person.id eq event.person.id}"><a href="#" onclick="deleteEvent(${event.id});"><img src="/images/delete.png" /></a> &nbsp;&nbsp;&nbsp;&nbsp; <%-- <a href="#" onclick="editEvent(${event.id});">[EDIT]</a>--%></c:if> &nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="showEvent(${event.id});"><img src="/images/viewdetail.png" /></a> &nbsp; &nbsp; &nbsp; &nbsp; <fmt:formatDate pattern="MM/dd/yyyy hh:mm:ss" value="${event.enteredDate}" /></div>
			</div> 
			<div style="clear: both;position: relative;">
				<div style="float:left;width:70px;">
					<a href="/secure/friendPage.do?friendId=${event.person.id}"><img src="../${event.person.defaultFace.icon.path}" title="${event.description}" /></a>
				</div>
				<div style="float: left;padding: 0 5px;"><p>${event.description}</p></div><div class="clear"></div>
			</div>
		</li>
		</c:forEach>	
	</ol>