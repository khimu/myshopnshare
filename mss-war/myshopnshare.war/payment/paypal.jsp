<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<script type="text/javascript">
$(document).ready(function() { 
	$('#submit-email-button').click(function(){
		var email_update = jQuery("#add-email-form").validate( {
			rules : {
				email : {
					required :true
				}
			},
			messages : {
				email :"<div class='errors'>Please enter your email address</div>"
			},
			submitHandler : function(form) {
				jQuery(form).ajaxSubmit( { 
					url: '/secure/email.do?method=add',
					type: 'post',
					dataType: 'json',
					success: function(email) { 
						$('#paypal-element').hide();
						$('#paypal-selection').append('<option value="' + email.id + '">' + email.email + '</option>');
						$('#confirm-paypal-address').html('<sns:reportrecordreadonly records="'+email.email+'" primaryKey="'+email.id + '" />');
					}
				});
			}
		});
	});
});	
</script>

	<form id="add-email-form" name="emailform">
		<input type="hidden" name="emailType" value="PAYPAL" />
		<input type="hidden" name="id" value="" />
		<div><span class="asterisk">*</span><label for="email">Email</label></div>
		<div><input type="text" id="email" name="email"/></div>
		<div class="clear"></div>
		<div><input type="checkbox" id="primaryEmail" name="primaryEmail" /> <label for="primaryEmail">Primary Email</label></div>
		<div class="clear"></div>
		<div>
			<button name="button" id="submit-email-button" type="submit">Add</button>
			<button name="button" id="reset-email-button" type="reset" >Reset</button>
		</div>
	</form>	