<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">		
	$(document).ready(function() { 												
			$('#edit-creditcard-button').click(function(){

				if(!isInteger($('#add-creditcard-form').find('#number').val()) || !isCreditCard($('#add-creditcard-form').find('#number').val())){
					Boxy.alert('Your credit card number is incorrect');
					return false;
				}

				if(!isInteger($('#add-creditcard-form').find('#securityCode').val())){
					Boxy.alert('Your security code must be a number');
					return false;
				}
				
				$('#loading').show();
				var email_update = jQuery("#edit-creditcard-form").validate( {
					rules : {
						fullname :"required",
						expirationDate :"required",
						creditCardType :"required"
					},
					messages : {
						fullname :"<div class='errors'>Please enter your full name.</div>",
						expirationDate :"<div class='errors'>Please enter the card expiration date</div>",
						creditCardType :"<div class='errors'>Please enter the card security code</div>"
					},
					submitHandler : function(form) {
						jQuery(form).ajaxSubmit( {
							url: '/secure/creditCard.do?method=edit', 
							type: 'post',
							dataType: 'json',
							success: function(c) {
								$('#edit-creditcard-form').clearForm();
								$('#loading').hide();
								Boxy.get('#edit-creditcard-form').hide();
								var recordid = $('#edit-creditcard-form').find('input:nth-child(1)').attr('value');
								$('#edit_creditcard_'+ recordid).parent().remove();
								$('#creditcard-replace-me').append('<sns:reportrecords records="'+c.cardName+'|'+c.fullName+'|'+c.number+'|'+c.expirationDate+'|'+c.securityCode+'|'+c.type+'" primaryKey="' + c.id + '" deleteURL="/secure/creditCard.do?method=" idType="creditcard" />');
							}
						});
					}
				});	
			});
		});	
</script>


<div id="creditcard-form-edit" style="display: none;">
	<form id="edit-creditcard-form">
		<input type="hidden" name="id" /> 		
		<div>
			<div><label for="cardName">Card Name</label></div>
			<div><input type="text" id="cardName" name="cardName" /></div>
			<div><label for="fullName">Full Name</label></div>
			<div><input type="text" id="fullName" name="fullName" /></div>
			<div><span class="asterisk">*</span><label for="number">Card Number</label></div>
			<div><input type="text" id="number" name="number" /></div>
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
		<button name="button" id="edit-creditcard-button" type="submit">Edit</button>
		<button name="button" type="reset">Reset</button>
	</form>
</div>
