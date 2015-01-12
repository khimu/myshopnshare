<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

<style type="text/css">
	#cartTable TH, #cartTable TD {
		padding: 5px;
	}
	#center {
		height: 880px;
	}
</style>

<script type="text/javascript">
function showPayment(){
	$('#loading').show();
	$.ajax( {
		type :"post",
		url :"/secure/payment.do",
		data: {method: 'view'},
		success : function(data) {
			$('#center').show();
			$('#center-2').hide();
			$('#center-3').hide();
			$('#center').html(data);
			$('#loading').hide();
	    }
	});				
}

function deleteItemFromCart(cartItemId){
	$('#loading').show();
	$.ajax( {
		type :"post",
		url :"/secure/cart.do",
		data: {method: 'delete', cartItemId: cartItemId},
		dataType: 'json',
		success : function(json) {
			$('#shopping-cart-size').html('<a href="#" onclick="showCart();"><img src="/images/insert_to_shopping_cart.png" width="15" height="15" />Shopping Cart: ' + json.cartSize + '</a>');
			$('#loading').hide();
			$('#cart-'+cartItemId).remove();
			$('#cart-grand-total').html(json.grandTotal);
	    }
	});			
}

</script>

<div id="shopping-cart-element">
	<div class="myshopnshare-header">
	<h3>Shopping Cart </h3>
	</div>
	<div clss="spacer"></div>
	
	<h3>Purchasing Items</h3>
	<sns:report tableName="cartTable" columns="Item Name|Price|Shipping|Tax|Quantity|Total">
	<c:forEach items="${shoppingCart.cartItems}" var="cartItem">
	<c:set var="cartTotal">
		<fmt:formatNumber value="${cartItem.total}" pattern="#,###,###.00" />
	</c:set>
	<sns:cartreport type="${cartItem.item.type}" records="${cartItem.item.itemName}|${cartItem.item.price}|${cartItem.item.shipping}|${cartItem.item.tax}|${cartItem.quantity}|${cartTotal}" idType="cart" primaryKey="${cartItem.id}" />
	</c:forEach>
	<c:set var="grandTotal">
		<fmt:formatNumber value="${shoppingCart.grandTotal}" pattern="#,###,###.00" />
	</c:set>		
	<sns:carttotal records="${grandTotal}" idType="cart" primaryKey="${cartItem.id}" />
	</sns:report>
	
	<div clss="spacer"></div>
	<div clss="spacer"></div>

	<h3>Rewards Point Items</h3>
	<sns:report tableName="cartTable" columns="Item Name|Price|Shipping|Tax|Quantity|Total">
	<c:forEach items="${shoppingCart.rewardsItems}" var="rewardsItem">
		<c:set var="rewardTotal">
			<fmt:formatNumber value="${rewardsItem.total}" pattern="#,###,###.00" />
		</c:set>
		<sns:cartreport type="${rewardsItem.item.type}" records="${rewardsItem.item.itemName}|${rewardsItem.item.price}|${rewardsItem.item.shipping}|${rewardsItem.item.tax}|${rewardsItem.quantity}|${rewardTotal}" idType="cart" primaryKey="${rewardsItem.id}" />
	</c:forEach>
	<c:set var="grandTotal">
		<fmt:formatNumber value="${shoppingCart.rewardGrandTotal}" pattern="#,###,###.00" />
	</c:set>	
	<sns:carttotal records="${grandTotal}" idType="cart" primaryKey="${rewardsItem.id}" />
	</sns:report>
	
	<div style="padding: 10px;">	
	<c:if test="${not empty shoppingCart.cartItems or not empty shoppingCart.rewardsItems}">
		<a href="#" onclick="showPayment();"><img src="/images/checkout.png"/></a>	
	</c:if>
	</div>
</div>
<img id="loading" src="/images/loading.gif" style="display:none;">
