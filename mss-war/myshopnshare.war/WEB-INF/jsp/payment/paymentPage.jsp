<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<script src="/js/shopnshare/payment.js" type="text/javascript"></script>
	
<div>
	<p style="text-align: center; padding: 10px;">Please choose a payment method below before proceeding to checkout</p>	

	<div id="payment-element">
		<%@ include file="payment.jsp" %>
	</div>
	
	<div id="address-element" style="display:none;">
			<div style="padding: 5px;border: 1px solid #FFF;"><%@ include file="address.jsp" %></div>
	</div>
	<div id="contact-element" style="display:none;">
			<div style="padding: 5px;border: 1px solid #FFF;"><%@ include file="contact.jsp" %></div>
	</div>
	<div id="card-element" style="display:none;">
			<div style="padding: 5px;border: 1px solid #FFF;"><%@ include file="card.jsp" %></div>
	</div>	
	<div id="paypal-element" style="display:none;">
			<div style="padding: 5px;border: 1px solid #FFF;"><%@ include file="paypal.jsp" %></div>
	</div>	
	
<div>

</div>
<img id="loading" src="/images/loading.gif" style="display:none;">
</div>

