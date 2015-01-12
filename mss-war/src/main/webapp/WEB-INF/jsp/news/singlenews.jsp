<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

<div id="news-box" class="news-box">
	<c:choose>			
		<c:when test="${news.person.id eq person.id}">
			<sns:ownsinglenewsdata personId="${person.id}" newsId="${news.id}" newsPersonId="${news.person.id}" enteredDate="${news.enteredDate}">${news.message}</sns:ownsinglenewsdata>
		</c:when>
		<c:otherwise>
			<sns:singlenewsdata personId="${person.id}" newsId="${news.id}" newsPersonId="${news.person.id}" enteredDate="${news.enteredDate}">${news.message}</sns:singlenewsdata>
		</c:otherwise>	
	</c:choose>		
</div>

