<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">
		$(document).ready(function() { 	
			$('#submit-phone-button').click(function(){
				var phone = $('#add-phone-form').find('#area').val() + '' + $('#add-phone-form').find('#number').val();
				if(!isUSPhoneNumber(phone)){
					Boxy.alert('Your phone number is incorrect');
					return false;
				}
				var email_update = jQuery("#add-phone-form").validate( {
					rules : {
						phoneType: "required"
					},
					messages : {
						phoneType :"Please select a phone type."
					},
					submitHandler : function(form) {
						jQuery(form).ajaxSubmit( {
							url: '/secure/phone.do?method=add', 
							type: 'post',
							dataType: 'json',
							success: function(p) { 
								$('#contact-element').hide();
								
								$('#contact-selection').append('<option value="' + p.id + '">(' + p.area + ')' + p.number + ' - ' + p.type + '</option>');
								$('#confirm-phone').html('<sns:reportrecordreadonly records="' + p.area + '|' + p.number + '|' + p.type +'" primaryKey="'+p.id+'"  />');
								
								
							}			
						});
					}
				});			
			});
		});	
</script>

	<form id="add-phone-form">
		<input type="hidden" name="id" /> 
		<div>
			<div><span class="asterisk">*</span><label for="area">Phone Number</label></div>
			<div><input type="text" id="area" name="area" maxlength="3" size="1" /> - <input type="text" id="number" name="number" maxlength="7" size="25" /></div>
			<div><span class="asterisk">*</span><label for="phoneType">Phone Type</label></div>
			<div><select name="phoneType">
					<option value="" selected="selected">--------------------------------</option>
					<c:forEach items="${phoneTypes}" var="type">
					<option value="${type}">${type.label}</option>
					</c:forEach>
				</select>
			</div>
			<div style="padding-top:5px;padding-bottom:5px;">
				<input type="radio" name="visibility" value="PRIVATE" /> PRIVATE
				&nbsp;&nbsp; 
				<input type="radio" name="visibility" value="PUBLIC" /> PUBLIC
			</div>			
		</div>
		<button name="button" id="submit-phone-button" type="submit">Add</button>
		<button name="button" id="reset-phone-button" type="reset">Reset</button>
	</form>
