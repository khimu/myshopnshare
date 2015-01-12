<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

<div class="myshopnshare-header"><h3>Sell Orders</h3></div>

<div class="tabular">
	<div class="inner-column">
		<c:forEach items="${orders}" var="order">
		<sns:report tableName="orderPendingTable" columns="Invoice|Item Name|Tracking Number|Status|Price|Shipping|Tax|Quantity|Total">	
			<tbody>
				<c:forEach items="${order.itemOrders}" var="iod">
						<sns:orderitemdetail invoice="${iod.invoice}" trackingNumber="${iod.trackingNumber}" itemName="${iod.item.itemName}" records="${iod.status.label}|${iod.priceTotal}|${iod.shippingTotal}|${iod.taxTotal}|${iod.quantity}|${iod.total}" primaryKey="${iod.id}" />
				</c:forEach>
			</tbody>
		</sns:report>
		</c:forEach>
	</div>	
</div>
<img id="loading" src="/images/loading.gif" style="display:none;">
