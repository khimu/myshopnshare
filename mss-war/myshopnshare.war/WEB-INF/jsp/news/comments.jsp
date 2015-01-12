<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<div id="news-comments" style="display: none;">
	<ul style="color:#fff;padding: 5px;">
		<c:forEach items="${news.comments}" var="comment">
		<li>
			${comment.message}
			<BR/>
			<fmt:formatDate value="${comment.enteredDate}" pattern="MM/dd/yyyy"/>
		</li>
		</c:forEach>
		<li>
		${news.item.itemName}
		</li>
	</ul>
</div>