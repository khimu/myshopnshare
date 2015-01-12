<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

<script type="text/javascript">

function populateConfirmAddress(addressId, elementId, type){
	$('#loading').show();
	$.ajax( {
		type :"post",
		url: '/secure/subscribe.do',
		data: {method: 'refreshAddresses', addressId: addressId},
		dataType: 'json',
		success : function(address) {
			$('#loading').hide();
			$(elementId).html('<sns:reportrecordreadonly records="'+address.street1 + '|' + address.street2 + '|' + address.unitNumber +'|' + address.city + '|' + address.stateOrProvince + '|' + address.postalCode + '|' + address.country+'" primaryKey="' + address.id + '" />');
		}
	});
}
function populateConfirmContact(){
	$('#loading').show();
		$.ajax( {
			type :"post",
			url: '/secure/subscribe.do',
			data: {method: 'refreshContact', phoneId: $('#contact-selection :selected').val()},
			dataType: 'json',
			success : function(p) {
				$('#loading').hide();
				$('#confirm-phone').html('<sns:reportrecordreadonly records="' + p.area + '|' + p.number + '|' + p.type +'" primaryKey="'+p.id+'"  />');
		    }
		});	
}

function populateConfirmCredit(){
		$('#loading').show();
		$.ajax( {
			type :"post",
			url: '/secure/subscribe.do',
			data: {method: 'refreshCard', cardId: $('#card-selection :selected').val()},
			dataType: 'json',
			success : function(c) {
				$('#loading').hide();
				$('#confirm-credit-card').html('<sns:reportrecordreadonly records="'+c.cardName+'|'+c.fullName+'|'+c.number+'|'+c.expirationDate+'|'+c.securityCode+'|'+c.type+'" primaryKey="' + c.id + '" />');
		    }
		});	 
}
function populateConfirmPaypal(){
	$('#loading').show();
	$.ajax( {
		type :"post",
		url: '/secure/subscribe.do',
		data: {method: 'refreshPaypal', paypalId: $('#paypal-selection :selected').val()},
		dataType: 'json',
		success : function(email) {
			$('#loading').hide();
			$('#confirm-paypal-address').html('<sns:reportrecordreadonly records="'+email.email+'" primaryKey="'+email.id + '" />');
	    }
	});	
}

</script>

<script src="/js/shopnshare/subscribe.js" type="text/javascript"></script>

<style type="text/css">
	#subscribe-submit-element h3 {
		padding: 5px;
		background-color: #FFF;
	}
</style>

<form id="subscribe-form" onsubmit="return false;">
<div>
	<div style="margin: 20px 0px;">
		<label for="subscriptionType">Subscription Type &nbsp;&nbsp;&nbsp;</label>
		<select name="subscriptionType">
				<option value=""></option>
			<c:forEach items="${subscriptionTypes}" var="sType">
				<c:if test="${sType.value != 'DEFAULT'}">
				<option value="${sType.value}">${sType.label}</option>
				</c:if>
			</c:forEach>
		</select>
	</div>
	
	<%@ include file="addresses.jsp" %>

	<%@ include file="phones.jsp" %>

	<%@ include file="cards.jsp" %>

	<%@ include file="emails.jsp" %>
	
	<div id="subscribe-submit-element" style="display:none;">
		<div>
			<div><a href="javascript: void(0);" onclick="showEndPrev('#subscribe-credit-element')"><img src="/images/prev.png" /></a></div><div></div>
		</div>

		<div>
			<h3>Billing Address</h3>
			<sns:report tableName="addressBillingTable" columns="Street1|Street2|Unit/Apt #|City|State/Province|Zip/Postal Code|Country">
				<tbody id="confirm-billing-address"  style="color: #000;"> 
				</tbody> 
			</sns:report>
		</div>
		<div class="spacer"></div>
		<div>
			<h3>Shipping Address</h3>
			<sns:report tableName="addressShippingTable" columns="Street1|Street2|Unit/Apt #|City|State/Province|Zip/Postal Code|Country">
				<tbody id="confirm-shipping-address"  style="color: #000;"> 
				</tbody> 
			</sns:report>		
		</div>
		<div class="spacer"></div>	
		<div>
			<h3>Credit Card</h3>
			<sns:report tableName="creditCardTable" columns="Card Name|Full Name|Card Number|Expiration Date|Security Code|Card Type|-|-|-|">
				<tbody id="confirm-credit-card"  style="color: #000;"> 
				</tbody>
			</sns:report>					
		</div>	
		<div class="spacer"></div>
		<div>
			<h3>Contact Number</h3>
			<table>
				<tbody id="confirm-phone"  style="color: #000;"> 
				</tbody> 
			</table>		
		</div>			
		<div class="spacer"></div>
		<div>
			<h3>Paypal Account</h3>
			<table>
				<tbody id="confirm-paypal-address"  style="color: #000;"> 
				</tbody> 
			</table>			
		</div>
		<div>
			<div>
				<p>Please confirm and submit subscribe</p>				
				<button name="button" id="submit-subscribe-button" type="submit">Submit</button>
				<button name="button" id="cancel-subscribe-button" type="reset" >Cancel</button>		
			</div>
		</div>
	</div>
	<div><div colspan="2"></div>
	<%-- address.jsp doesn't like being in the same file as addresses.jsp but this is not the case
	for creditCard, phones and so forth.  Odd. --%>

</div>
</form>
