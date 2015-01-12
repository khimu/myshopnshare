<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<div id="payment-paypal-element" style="display: none;">

	<div style="position:relative; padding-bottom:10px;">
		<div style="width:200px;float:left;"><a href="javascript: void(0);" onclick="showHide('#payment-contact-element', 'paypal');"><img src="/images/prev.png" /></a></div><div><a href="javascript: void(0);" onclick="showConfirmation('#payment-submit-element', 'paypal')"><img src="/images/next.png" /></a></div>
	</div>

		<div><h3><a href="javascript: void(0);" onclick="showHide('#payment-credit-element', 'paypal')">Use Credit Card</a></h3></div>

	<div>
		<div id="paypal-payment-label" colspan="2">
				Paypal Account	
		</div>
	</div>	
	<div>
		<div>
			<select id="paypal-selection" name="paypalId">
					<option value="" selected="selected"></option>
					<option value="-1">Add New</option>
				<c:forEach items="${emails}" var="email">
				<option value="${email.id}">${email.email}</option>
				</c:forEach>
			</select>	
			<div id="paypal-payment-show-hide-element"></div>
		</div>
	</div>
	
	
</div>