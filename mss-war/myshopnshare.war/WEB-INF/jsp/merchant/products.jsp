<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<style type="text/css">
	DIV#products-list a {
		float: left;
	}
	DIV#products-list span {
		float: left;
	}
	DIV#products-list DIV {
		float: left;
	}
	.product-actions {
	}
</style>



	<div id="products-list" style="position: relative;clear:both;">
	<c:forEach items="${products}" var="product">
		<div style="position: relative;border:1px solid #FFF;width:920px; height: 80px;padding: 5px;margin: 5px;">
			<a href="${product.glimpse.path}" rel="prettyPhoto[gallery]" title="${person.fullName}"><img
				src="${product.icon.path}" alt="${product.icon.path}" title="${person.firstName}" /></a>
		
			<span style="vertical-align: top;padding-left: 5px;width: 400px;">${product.description}</span>			
		
			<div class="product-actions" style="float: right;padding: 5px;width: 40px;">
				<a href="javascript: deleteProduct('${product.id}');">Delete</a>
				<a href="javascript: viewProduct('${product.id}');">View</a>
			</div>
		</div>
	</c:forEach>
	</div>