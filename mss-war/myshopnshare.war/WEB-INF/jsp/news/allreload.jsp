<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

<div>Size: ${fn:length(newzs)}</div>
<div style="float: left;">
	
	<div id="news-box" class="news-box">
	<c:forEach items="${newzs}" var="newz">
		<sns:newsdata personId="${person.id}" newsId="${newz.id}" newsPersonId="${newz.person.id}" enteredDate="${newz.enteredDate}">${newz.message}</sns:newsdata>			
		<hr/>
	</c:forEach>
	</div>

</div>