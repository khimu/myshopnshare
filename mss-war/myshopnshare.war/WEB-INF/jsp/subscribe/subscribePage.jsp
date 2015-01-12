<%@ include file="/WEB-INF/jsp/includes.jsp" %>
	
<div>	
	<div style="margin: 10px;font-size: 16px;">
			Sign up today and take advantage of our free for 30 days promotion to use our extended services.
	</div>
	
	<div id="subscribe-element" style="margin: 0 20px;">
		<%@ include file="payment.jsp" %>
	</div>	
	
		<div id="card-element" style="display: none;">
				<div style="padding: 5px;border: 1px solid #FFF;"><%@ include file="card.jsp" %></div>
		</div>	
		<div id="paypal-element" style="display:none;">
				<div style="padding: 5px;border: 1px solid #FFF;"><%@ include file="paypal.jsp" %></div>
		</div>
		<div id="contact-element" style="display:none;">
				<div style="padding: 5px;border: 1px solid #FFF;"><%@ include file="contact.jsp" %></div>
		</div>
		<div id="address-element" style="display:none;">
				<div style="padding: 5px;border: 1px solid #FFF;"><%@ include file="address.jsp" %></div>
		</div>	
	
	<img id="loading" src="/images/loading.gif" style="display:none;">

</div>

