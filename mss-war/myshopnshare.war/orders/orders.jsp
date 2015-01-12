<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

<script type="text/javascript">
function getOrderDetail(status, elementId){
	$.ajax( {
		type :"post",
		url :"/secure/orders.do",
		data: {method: status},
		dataType: 'json',
		success : function(json) {
			$.each(json.itemOrderDetails, function(i,iod){
				$(elementId).append('<sns:orderitemdetail invoice="' + iod.invoice + '" itemName="' + iod.itemName + '" records="' + iod.status + '|' + iod.trackingNumber + '|' + iod.price + '|' + iod.shipping + '|' + iod.tax + '|' + iod.quantity + '|' + iod.total + '" primaryKey="' + p.id + '" />');
			}); 
	    }
	});	
}
$(document).ready(function(){
	getOrderDetail('pending', '#pending-order-detail-items');
	getOrderDetail('approved', '#approved-order-detail-items');
});

</script>

<div class="myshopnshare-header"><h3>Manage Orders</h3></div>

<div id="order-detail-element" class="tabular">
	<div class="inner-column">
		<div>
		<h3>Pending Orders</h3>
		<sns:report tableName="orderPendingTable" columns="Invoice|Item Name|Status|Tracking Number|Price|Shipping|Tax|Quantity|Total|-">
			<tbody id="pending-order-detail-items">
			</tbody>
		</sns:report>
		</div>
		<div class="spacer"></div>
		<div>
		<h3>Approved Orders</h3>
		<sns:report tableName="orderApprovedTable" columns="Invoice|Item Name|Status|Tracking Number|Price|Shipping|Tax|Quantity|Total|-">
			<tbody  id="approved-order-detail-items">
			</tbody>
		</sns:report>
		</div>	
		<div class="spacer"></div>
		<div>
		<h3>Shipped Orders</h3>
		<sns:report tableName="orderShippedTable" columns="Invoice|Item Name|Status|Tracking Number|Price|Shipping|Tax|Quantity|Total|-">
			<tbody  id="shipped-order-detail-items">
			</tbody>
		</sns:report>	
		</div>
		<div class="spacer"></div>
		<div>
		<h3>Completed Orders</h3>
		<sns:report tableName="orderCompletedTable" columns="Invoice|Item Name|Status|Tracking Number|Price|Shipping|Tax|Quantity|Total|-">
			<tbody id="completed-order-detail-items">
			</tbody>
		</sns:report>
		</div>	
		<div class="spacer"></div>
		<div>
		<h3>Returned Orders</h3>
		<sns:report tableName="orderReturnedTable" columns="Invoice|Item Name|Status|Tracking Number|Price|Shipping|Tax|Quantity|Total|-">
			<tbody id="returned-order-detail-items">
			</tbody>
		</sns:report>
		</div>	
		<div class="spacer"></div>
		<div>
		<h3>Request Return Orders</h3>
		<sns:report tableName="orderRequestReturnTable" columns="Invoice|Item Name|Status|Tracking Number|Price|Shipping|Tax|Quantity|Total|-">
			<tbody id="request-return-order-detail-items">
			</tbody>
		</sns:report>
		</div>	
	</div>	
</div>
<img id="loading" src="/images/loading.gif" style="display:none;">
