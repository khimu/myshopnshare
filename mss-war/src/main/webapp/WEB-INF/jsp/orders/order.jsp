<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>
<style type="text/css">
	#payment-submit-element {
		color: #000;
	}
	
	#payment-submit-element h3 {
		color: #000;
	}
</style>

<script type="text/javascript">
	function showOrderDetail(itemOrderDetailId){
		new Boxy('#show-order-detail-update-form', {title: 'Update Item Order Detail', modal: true, fixed: false});
		$('#item-order-detail-id').attr('value', itemOrderDetailId);
	}
</script>

<div id="show-order-detail-update-form" style="display: none;">
	<%@ include file="form.jsp" %>
</div>

<div style="position:relative;background-color: #8FACF1;width: 300px;height:100px;border: 2px solid #CEDBF9;">
	<div style="position: relative;">
		<div style="float: left"><h1>Invoice:  ${order.invoice} </h1></div>
		<div style="float: right;"><a href="#" onclick="showOrderDetail(${order.id});">[UPDATE]</a></div>
	</div>
	<div style="clear: both;">
		<h3>${order.orderDetail.payment.person.fullName}</h3>
	</div>
</div>

<div>
	<sns:report tableName="orderTable" columns="Invoice|Item Name|Tracking Number|Status|Price|Shipping|Tax|Quantity|Total">
		<tbody class="odd" id="completed-order-detail-items">
			<sns:orderitemdetail invoice="${order.invoice}" itemName="${order.item.itemName}" trackingNumber="${order.trackingNumber}" records="${order.status}|${order.priceTotal}|${order.shippingTotal}|${order.taxTotal}|${order.quantity}|${order.total}" primaryKey="${order.id}" />
		</tbody>
	</sns:report>
	
	<div class="tabular">
		<div class="inner-column">
			<div>
				<h3>Billing Address</h3>
				<sns:report tableName="addressBillingTable" columns="Street1|Street2|Unit/Apt #|City|State/Province|Zip/Postal Code|Country">
					<tbody id="confirm-billing-address"  style="color: #000;"> 
						<sns:reportrecordreadonly records="${order.orderDetail.payment.billingAddress.street1}|${order.orderDetail.payment.billingAddress.street2}|${order.orderDetail.payment.billingAddress.unitNumber}|${order.orderDetail.payment.billingAddress.city}|${order.orderDetail.payment.billingAddress.stateOrProvince}|${order.orderDetail.payment.billingAddress.postalCode}|${order.orderDetail.payment.billingAddress.country}" primaryKey="${order.orderDetail.payment.billingAddress.id}" />
					</tbody> 
				</sns:report>
			</div>
			
			<div class="spacer"></div>
			
			<div>
				<h3>Shipping Address</h3>
				<sns:report tableName="addressShippingTable" columns="Street1|Street2|Unit/Apt #|City|State/Province|Zip/Postal Code|Country">
					<tbody id="confirm-shipping-address"  style="color: #000;"> 
						<sns:reportrecordreadonly records="${order.orderDetail.payment.shippingAddress.street1}|${order.orderDetail.payment.shippingAddress.street2}|${order.orderDetail.payment.shippingAddress.unitNumber}|${order.orderDetail.payment.shippingAddress.city}|${order.orderDetail.payment.shippingAddress.stateOrProvince}|${order.orderDetail.payment.shippingAddress.postalCode}|${order.orderDetail.payment.shippingAddress.country}" primaryKey="${order.orderDetail.payment.shippingAddress.id}" />
					</tbody> 
				</sns:report>		
			</div>	
			
			<div class="spacer"></div>
			
			<div>
				<h3>Credit Card</h3>
				<table>
					<tbody id="confirm-credit-card"  style="color: #000;"> 
						<sns:reportrecordreadonly records="${order.orderDetail.payment.creditCards.orderDetail.payment.cardName}|${order.orderDetail.payment.creditCards.fullName}|${order.orderDetail.payment.creditCards.number}|${order.orderDetail.payment.creditCards.expirationDate}|${order.orderDetail.payment.creditCards.securityCode}|${order.orderDetail.payment.creditCards.type}" primaryKey="${order.orderDetail.payment.creditCards.id}" />
					</tbody> 
				</table>		
			</div>	
			
			<div class="spacer"></div>
			
			<div>
				<h3>Contact Number</h3>
				<table>
					<tbody id="confirm-phone"  style="color: #000;"> 
						<sns:reportrecordreadonly records="${order.orderDetail.payment.contact.number}|${order.orderDetail.payment.contact.type}" primaryKey="${order.orderDetail.payment.contact.id}"  />
					</tbody> 
				</table>		
			</div>		
			
			<div class="spacer"></div>	
			
			<div>
				<h3>Paypal Account</h3>
				<table>
					<tbody id="confirm-paypal-address"  style="color: #000;"> 
						<sns:reportrecordreadonly records="${order.orderDetail.payment.paypalAccount.email}" primaryKey="${order.orderDetail.payment.paypalAccount.id}" />
					</tbody> 
				</table>			
			</div>
			
		</div>		
		<div colspan="2"></div>
	</div>
</div>
