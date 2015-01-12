<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<div id="payment-address-element">

	<div style="position:relative; padding-bottom:10px;">
		<div style="width:200px;float:left;"></div><div><a href="javascript: void(0);" onclick="showHide('#payment-contact-element', 'address')"><img src="/images/next.png" /></a></div>
	</div>

	<div>
		<div id="billing-payment-label" colspan="2">
				Billing Address	
		</div>
	</div>
	<div>
		<div>
				<select id="billing-address-selection" name="billingId">
					<option value="" selected="selected"></option>
					<option value="-1">Add New</option>
					<c:forEach items="${addresses}" var="address">
					<option value="${address.id}">${address.street1}</option>
					</c:forEach>
				</select>	
		</div>
	</div>
	<div>
		<div colspan="2"> <input id="same-as-billing" type="checkbox" name="sameAsBilling" /> Use billing address for shipping address </div>
	</div>
	<div>
		<div id="shipping-payment-label" colspan="2">
				Shipping Address	
		</div>
	</div>	
	<div>
		<div>
			<select id="shipping-address-selection" name="shippingId">
					<option value="" selected="selected"></option>
					<option value="-1">Add New</option>
				<c:forEach items="${addresses}" var="address">
				<option value="${address.id}">${address.street1}</option>
				</c:forEach>
			</select>	
		</div>
	</div>
	<div><div><div id="address-payment-show-hide-element"></div> </div><div></div></div>

</div>

