<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">	
	$(document).ready(function() { 						
			$('#submit-address-button').click(function(){
				if(!isZIPCode($('#add-address-form').find('#postalCode').val())){
					Boxy.alert('Zip/Postal Code is required');
					return false;
				}
				$('#loading').show();
				jQuery("#add-address-form").validate( {
					rules : {
						street1 :"required",
						city :"required",
						stateOrProvince :"required",
						country :"required",
						addressType: "required"
					},
					messages : {
						street1 :"<div class='errors'>Please enter your street address.</div>",
						city :"<div class='errors'>Please enter your city</div>",
						stateOrProvince :"<div class='errors'>Please enter the state you live in</div>",
						country :"<div class='errors'>Please enter your country</div>",
						addressType :"<div class='errors'>Please enter your address type</div>"
					},
					submitHandler : function(form) {
						jQuery(form).ajaxSubmit( {
							url: '/secure/address.do?method=add', 
							type: 'post',
							dataType: 'json',
							success: function(address) {
								$('#address-element').hide();
								$('#loading').hide();
								$('#billing-address-selection').append('<option value="' + address.id + '">'+address.street1+'</option>');
								$('#shipping-address-selection').append('<option value="' + address.id + '">'+address.street1+'</option>');
								
								if($('#billing-address-selection').is(':selected')){
									var selected = $('#billing-address-selection :selected').val();
									if(selected == -1){
										$('#confirm-billing-address').html('<sns:reportrecordreadonly records="'+address.street1 + '|' + address.street2 + '|' + address.unitNumber +'|' + address.city + '|' + address.stateOrProvince + '|' + address.postalCode + '|' + address.country+'" primaryKey="' + address.id +'" />');
										
							
										if($('#same-as-billing').is(':checked')){
											$('#confirm-shipping-address').html('<sns:reportrecordreadonly records="'+address.street1 + '|' + address.street2 + '|' + address.unitNumber +'|' + address.city + '|' + address.stateOrProvince + '|' + address.postalCode + '|' + address.country+'" primaryKey="' + address.id +'" />');
											
										}
									}
								}
								if($('#shipping-address-selection').is(':selected')){
									var selected = $('#shipping-address-selection :selected').val();
									if(selected == -1){
										// $("select[name='basket'] :selected").attr("value");
										$('#confirm-shipping-address').html('<sns:reportrecordreadonly records="'+address.street1 + '|' + address.street2 + '|' + address.unitNumber +'|' + address.city + '|' + address.stateOrProvince + '|' + address.postalCode + '|' + address.country+'" primaryKey="' + address.id + '" />');
									}
								}								
							}
						});
					}
				});	
			});
	});	
</script>

	<form id="add-address-form">
		<input type="hidden" name="id" value="" />
		<input type="hidden" name="personId" value="${personId}" /> 
		<div>
			<div><span class="asterisk">*</span><label for="street1">Street1</label></div>
			<div><input type="text" id="street1" name="street1"/></div>
			<div><label for="street2">Street2</label></div>
			<div><input type="text" id="street2" name="street2" /></div>
			<div><label for="unitNumber">Unit/Apt</label></div>
			<div><input type="text" id="unitNumber" name="unitNumber" /></div>
			<div><span class="asterisk">*</span><label for="city">City</label></div>
			<div><input type="text" id="city" name="city"/></div>
			<div><span class="asterisk">*</span><label for="state">State/Provance</label></div>
			<div><select id="stateOrProvince" name="stateOrProvince" size="1"><%@ include file="../account/address/state.jsp" %></select></div>
			<div><span class="asterisk">*</span><label for="zip">Zip/Postal Code: </label></div>
			<div><input type="text" id="postalCode" name="postalCode"/></div>
			<div><span class="asterisk">*</span><label for="addressType">Address Type</label></div>
			<div><select id="addressType" name="addressType">
					<option value="" selected="selected"></option>
					<c:forEach items="${addressTypes}" var="type">
					<option value="${type}">${type.label}</option>
					</c:forEach>
				</select>
			</div>
			<div style="padding-top:5px;padding-bottom:5px;">
				<input type="radio" name="visibility" value="PRIVATE" /> PRIVATE
				&nbsp;&nbsp; 
				<input type="radio" name="visibility" value="PUBLIC" /> PUBLIC
			</div>			
			<div><span class="asterisk">*</span><label for="country">Country</label></div>
			<div><select name="country" id="countryComboBox"><%@ include file="../account/address/country.jsp" %></select></div>
		</div>
		
		<button name="button" id="submit-address-button" type="submit">Add</button>
		<button name="button" id="reset-address-button" type="reset">Reset</button>
	</form>