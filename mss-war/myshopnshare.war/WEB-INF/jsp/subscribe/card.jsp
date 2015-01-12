<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<script type="text/javascript">
$(document).ready(function() { 		
	$('#submit-creditcard-button').click(function(){
		if(!isInteger($('#add-creditcard-form').find('#number').val()) || !isCreditCard($('#add-creditcard-form').find('#number').val())){
			Boxy.alert('Your credit card number is incorrect');
			return false;
		}

		if(!isInteger($('#add-creditcard-form').find('#securityCode').val())){
			Boxy.alert('Your security code must be a number');
			return false;
		}
		
		var email_update = jQuery("#add-creditcard-form").validate( {
			rules : {
				fullname :"required",
				expirationDate :"required",
				creditCardType :"required"
			},
			messages : {
				fullname :"<div class='errors'>Please enter your email type</div>",
				expirationDate :"<div class='errors'>Please enter the card expiration date</div>",
				creditCardType :"<div class='errors'>Please enter the card security code</div>"
			},
			submitHandler : function(form) {
				jQuery(form).ajaxSubmit( {
					url: '/secure/creditCard.do?method=add', 
					type: 'post',
					dataType: 'json',
					success: function(c) {
						$('#card-element').hide();
						
						$('#card-selection').append('<option value="' + c.id + '" selected="selected">'+c.lastFour+'</option>');
						$('#confirm-credit-card').html('<sns:reportrecordreadonly records="'+c.cardName+'|'+c.fullName+'|'+c.number+'|'+c.expirationDate+'|'+c.securityCode+'|'+c.type+'" primaryKey="' + c.id + '" />'); 
					}			
				});
			}
		});			
	});
});	
</script>

	<form id="add-creditcard-form">
		<input type="hidden" name="id" /> 		
		<div>
			<div><label for="cardName">Card Name</label></div>
			<div><input type="text" id="cardName" name="cardName" /></div>
			<div><label for="fullName">Full Name</label></div>
			<div><input type="text" id="fullName" name="fullName" /></div>
			<div><span class="asterisk">*</span><label for="number">Card Number</label></div>
			<div><input type="text" id="number" name="number"  /></div>
			<div><span class="asterisk">*</span><label for="expirationDate">Expiration Date</label></div>
			<div><input type="text" id="expirationDate" name="expirationDate"/></div>
			<div><span class="asterisk">*</span><label for="securityCode">Security Code</label></div>
			<div><input type="text" id="securityCode" name="securityCode" /></div>
			<div><span class="asterisk">*</span><label for="creditCardType">Card Type</label></div>
			<div><select name="creditCardType">
					<option value="" selected="selected"></option>
					<c:forEach items="${cardTypes}" var="type">
					<option value="${type}">${type.label}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<button name="button" id="submit-creditcard-button" type="submit">Add</button>
		<button name="button" id="reset-creditcard-button" type="reset">Reset</button>
	</form>