<%@ include file="/WEB-INF/jsp/includes.jsp"%>


<div id="payment-credit-element" style="display:none;">

	<div style="position:relative; padding-bottom:10px;">
		<div style="width:200px;float:left;"><a href="javascript: void(0);" onclick="showHide('#payment-contact-element', 'credit')"><img src="/images/prev.png" /></a></div><div><a href="javascript: void(0);" onclick="showConfirmation('#payment-submit-element', 'credit')"><img src="/images/next.png" /></a></div>
	</div>

	<div><h3><a href="javascript: void(0);" onclick="showHide('#payment-paypal-element', 'credit')">Use Paypal</a></h3></div>

	<div>
		<div id="credit-payment-label" colspan="2">
				Credit Card
		</div>
	</div>	
	<div>
		<div>
			<select id="card-selection" name="cardId">
					<option value="" selected="selected"></option>
					<option value="-1">Add New</option>
				<c:forEach items="${cards}" var="card">
				<option value="${card.id}">${card.lastFour} - ${card.type}</option>
				</c:forEach>
			</select>	
			<div id="credit-payment-show-hide-element"></div>
		</div>
	</div>

</div>