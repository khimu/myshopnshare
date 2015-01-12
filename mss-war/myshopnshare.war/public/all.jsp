<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<style type="text/css">
	.the-items {
		position:relative;
		
	}
	.the-items span {
		float: left;
		margin:2px;
	}
</style>

	<div id="all-products" style="position: relative;clear:both;">
		<div style="float: left;width:auto; padding: 5px;margin: 5px;">
				<div id="search-results">
				<fmt:message key="hello.world"/>
				<c:forEach items="${items}" var="item"> 
					<sns:publicsearchitemimagerecord itemName="${item.itemName}" caption="${item.itemName}" description="${item.description}" icon="/${item.icon.path}" glimpse="/${item.glimpse.path}" primaryKey="${item.id}" />
				</c:forEach>
				</div>		
		</div>
	</div>

