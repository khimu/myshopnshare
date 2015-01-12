<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>


<script type="text/javascript">
function populateConfirmContact(){
	$.ajax( {
		type :"post",
		url: '/secure/payment.do',
		data: {method: 'refreshContact', phoneId: $('#contact-selection :selected').val()},
		dataType: 'json',
		success : function(p) {
			$('#loading').hide();
			$('#confirm-phone').html('<sns:reportrecordreadonly records="' + p.area + '|' + p.number + '|' + p.type +'" primaryKey="'+p.id+'"  />');
	    }
	});	
}

function populateConfirmPaypal(){
	$.ajax( {
		type :"post",
		url: '/secure/payment.do',
		data: {method: 'refreshPaypal', paypalId: $('#paypal-selection :selected').val()},
		dataType: 'json',
		success : function(email) {
			$('#loading').hide();
			$('#confirm-paypal-address').html('<sns:reportrecordreadonly records="'+email.email+'" primaryKey="'+email.id + '" />');
	    }
	});	
}

function populateConfirmCredit(){
	$('#loading').show();
	$.ajax( {
		type :"post",
		url: '/secure/payment.do',
		data: {method: 'refreshCard', cardId: $('#card-selection :selected').val()},
		dataType: 'json',
		success : function(c) {
			$('#loading').hide();
			$('#confirm-credit-card').html('<sns:reportrecordreadonly records="'+c.cardName+'|'+c.fullName+'|'+c.number+'|'+c.expirationDate+'|'+c.securityCode+'|'+c.type+'" primaryKey="' + c.id + '" />');
	    }
	});	 
}
function populateConfirmAddress(addressId, elementId, type){
	$('#loading').show();
	$.ajax( {
		type :"post",
		url: '/secure/payment.do',
		data: {method: 'refreshAddresses', addressId: addressId},
		dataType: 'json',
		success : function(address) {
			$('#loading').hide();
			$(elementId).html('<sns:reportrecordreadonly records="'+address.street1 + '|' + address.street2 + '|' + address.unitNumber +'|' + address.city + '|' + address.stateOrProvince + '|' + address.postalCode + '|' + address.country+'" primaryKey="' + address.id + '" />');
		}
	});		
}

</script>

<c:if test="${not empty errorMessage}">
<div class="errors">
${errorMessage}
</div>
</c:if>

<form id="payment-form" onsubmit="return false;">
<div>
	<%@ include file="addresses.jsp" %>

	<%@ include file="phones.jsp" %>

	<%@ include file="emails.jsp" %>

	<%@ include file="cards.jsp" %>
	
	<div id="payment-submit-element" style="display:none;" class="tabular">
		<div>
			<div><a href="javascript: void(0);" onclick="showEndPrev('#payment-credit-element')"><img src="/images/prev.png" /></a></div><div></div>
		</div>
		
		<div class="inner-column">
			<div>
				<h3>Billing Address</h3>
				<sns:report tableName="addressBillingTable" columns="Street1|Street2|Unit/Apt #|City|State/Province|Zip/Postal Code|Country">
					<tbody id="confirm-billing-address" > 
					</tbody> 
				</sns:report>
			</div>
			<div class="spacer"></div>
			<div>
				<h3>Shipping Address</h3>
				<sns:report tableName="addressShippingTable" columns="Street1|Street2|Unit/Apt #|City|State/Province|Zip/Postal Code|Country">
					<tbody id="confirm-shipping-address" > 
					</tbody> 
				</sns:report>		
			</div>	
			<div class="spacer"></div>
			<div>
				<h3>Credit Card</h3>
				<sns:report tableName="creditCardTable" columns="Card Name|Full Name|Card Number|Expiration Date|Security Code|Card Type|-|-|-|">
					<tbody id="confirm-credit-card" > 
					</tbody>
				</sns:report>		
			</div>	
			<div class="spacer"></div>
			<div>
				<h3>Contact Number</h3>
				<table>
					<tbody id="confirm-phone" > 
					</tbody> 
				</table>		
			</div>			
			<div class="spacer"></div>
			<div>
				<h3>Paypal Account</h3>
				<table>
					<tbody id="confirm-paypal-address" > 
					</tbody> 
				</table>			
			</div>
		</div>
		<div>
			<div>
				<p>Please confirm and submit payment</p>
				<input id="submit-payment-button" class="submit" type="submit" value="Submit" />
				<%--<button name="button" id="submit-payment-button" type="submit">Submit</button>--%>
				<button name="button" id="cancel-payment-button" type="reset" >Cancel</button>		
			</div>
		</div>
	</div>
	<div><div colspan="2"></div>
</div>
</form>
