<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<style type="text/css">
	#item-description hover {
		border: 1px solid red;
	}
</style>

	<c:forEach items="${items}" var="product">
		<div id="item-description" style="float: left;width: 70px; height: 70px;border: 3px solid white; background-color: white;">
			<a href="${product.glimpse.path}" rel="lightbox" title="${product.description}" alt="${product.itemName}"><img
				src="${product.icon.path}" title="${product.itemName}" /></a>
			<div style="vertical-align: top;">
				<a href="/secure/user.do?method=delete&itemId=${product.id}">DELETE</a> |
				<a href="/secure/user.do?method=edit&itemId=${product.id}">Edit</a>
			</div>
		</div>
	</c:forEach>
