<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">
$(document).ready(function() { 
			$('#edit-submit-phone-button').click(function(){
				var phone = $('#edit-phone-form').find('#area').val() + '' + $('#edit-phone-form').find('#number').val();
				if(!isUSPhoneNumber(phone)){
					Boxy.alert('Your phone number is incorrect');
					return false;
				}
						
				var email_update = jQuery("#edit-phone-form").validate( {
					rules : {
						phoneType: "required"
					},
					messages : {
						phoneType :"<div class='errors'>Please select a phone type.</div>"
					},
					submitHandler : function(form) {
						jQuery(form).ajaxSubmit( {
							url: '/secure/phone.do?method=edit', 
							type: 'post',
							dataType: 'json',
							success: function(p) { 
								$('#edit-creditcard-form').clearForm();
								$('#loading').hide();
								var recordid = $('#edit-phone-form').find('input:nth-child(1)').attr('value');
								$('#edit_phone_'+ recordid).parent().remove();
								Boxy.get('#edit-phone-form').hide();
								$('#phone-replace-me').append('<sns:reportrecords records="' + p.area + '|' + p.number + '|' + p.type +'" primaryKey="'+p.id+'" isPrimary="'+p.primaryPhone+'" deleteURL="/secure/phone.do?method=" idType="phone" />');
							}
						});
					}
				});
			});
			
		});	
</script>


<div id="phone-form-edit" style="display: none;">
	<form id="edit-phone-form">
		<input type="hidden" name="id" />  
		<div>
			<div><label for="number"><span class="asterisk">*</span>Phone Number</label></div>
			<div><input type="text" id="area" name="area" maxlength="3" size="1" /> - <input type="text" id="number" name="number" maxlength="7" size="10" /></div>
			<div><label for="phoneType"><span class="asterisk">*</span>Phone Type</label></div>
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
		<button name="button" id="edit-submit-phone-button" type="submit">Edit</button>
		<button name="button" type="reset">Reset</button>
	</form>
</div>