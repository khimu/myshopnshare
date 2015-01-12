function hideAllPaymentForms(){
	$('#paypal-element').hide();
	$('#paypal-element').hide();
	$('#contact-element').hide();
	$('#card-element').hide();
	$('#paypal-element').hide();
}

function showHide(elementShow, elementHide){
	hideAllPaymentForms();

	$(elementShow).show();
	$('#payment-'+elementHide+'-element').hide();	

	$('#'+elementHide+'-element').hide();
	$('#' + elementHide + '-payment-show-hide-element a').html('');	
}

function showEndPrev(elementId){
	hideAllPaymentForms();
	$('#payment-submit-element').hide();
	$(elementId).show();		
}

$(document).ready(function(){
	isCheckedAddress();
	selectBilling();
	selectShipping();
	selectCreditCard();
	selectPaypal();
	
	hideAllPaymentForms();
	
	$('#billing-address-selection').change(function(){
		selectBilling();
	});
	$('#shipping-address-selection').change(function(){
		selectShipping();
	});	
	$('#same-as-billing').click(function(){
		isCheckedAddress();
	});	
	$('#cancel-payment-button').click(function(){
		$('#center-2').hide();
		$('#center').show();
	});
	$('#contact-selection').change(function(){
		selectContact();
	});		
	$('#paypal-selection').change(function(){
		selectPaypal();
	});
	$('#card-selection').change(function(){
		selectCreditCard();
	});		
	
	/*
<form target="paypal" action="https://www.paypal.com/cgi-bin/webscr" method="post">
<input type="hidden" name="cmd" value="_s-xclick">
<input type="hidden" name="hosted_button_id" value="4661330">
<input type="image" src="https://www.paypal.com/en_US/i/btn/btn_cart_LG.gif" border="0" name="submit" alt="PayPal - The safer, easier way to pay online!">
<img alt="" border="0" src="https://www.paypal.com/en_US/i/scr/pixel.gif" width="1" height="1">
</form>

https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=4661330
	 */
	
	$('#submit-payment-button').click(function(){
		$('#loading').show();
		var params = $('#payment-form').serialize();
		$.ajax( {
			type :"post",
			url: '/secure/payment.do?method=checkout&'+params,
			dataType: 'html',
			success : function(data) {
				$('#center').html(data);
				$('#loading').hide();
				$('#shopping-cart-size').html('<img src="/images/insert_to_shopping_cart.png" width="15" height="15" alt="" title="" />Shopping Cart: 0');
			}
		});	
	});
});

function showHideAddressPayment(){
	if($('#address-payment-show-hide-element a').html() == '[-]'){
		$('#address-payment-show-hide-element a').html('[+]');
		$('#address-element').hide();
	}else{
		$('#address-payment-show-hide-element a').html('[-]');
		$('#address-element').show();	
	}
}

function showHideContactPayment(){
	if($('#contact-payment-show-hide-element a').html() == '[-]'){
		$('#contact-payment-show-hide-element a').html('[+]');
		$('#contact-element').hide();
		$('#contact-payment-label').css('color', '#FFF');
	}else{
		$('#contact-payment-show-hide-element a').html('[-]');
		$('#contact-element').show();
		$('#contact-payment-label').css('color', '#FF3');			
	}
}	
function showHidePaypalPayment(){
	if($('#paypal-payment-show-hide-element a').html() == '[-]'){
		$('#paypal-payment-show-hide-element a').html('[+]');
		$('#paypal-element').hide();
		$('#paypal-payment-label').css('color', '#FFF');
	}else{
		$('#paypal-payment-show-hide-element a').html('[-]');
		$('#paypal-element').show();		
		$('#paypal-payment-label').css('color', '#FF3');	
	}
}	
function showHideCreditPayment(){
	if($('#credit-payment-show-hide-element a').html() == '[-]'){
		$('#credit-payment-show-hide-element a').html('[+]');
		$('#card-element').hide();
		$('#credit-payment-label').css('color', '#FFF');
	}else{
		$('#credit-payment-show-hide-element a').html('[-]');
		$('#card-element').show();			
		$('#credit-payment-label').css('color', '#FF3');
	}
}				


function showConfirmation(elementId, pageId){
	hideAllPaymentForms();
	$('#payment-credit-element').hide();
	$('#payment-paypal-element').hide();
	$('#payment-submit-element').show();
}

function isCheckedAddress(){
	if($('#same-as-billing').is(':checked')){
		$('#shipping-payment-label').hide();
		$('#shipping-address-selection').hide();
		var selected = $('#billing-address-selection :selected').val();
		if(selected == ''){
			return false;
		}
		if(selected != ''){
			populateConfirmAddress(selected, '#confirm-shipping-address', "Shipping");
		}
	}else{
		$('#shipping-payment-label').show();
		$('#shipping-address-selection').show();	
	}
}

function selectBilling(){
	var selected = $('#billing-address-selection :selected').val();
	if(selected == ''){
		return false;
	}
	if(selected != -1){
		populateConfirmAddress(selected, '#confirm-billing-address', "Billing");
		if($('#same-as-billing').is(':checked')){
			$('#shipping-address-selection').attr('value', selected);
			populateConfirmAddress(selected, '#confirm-shipping-address', "Shipping");
		}
		$('#address-element').hide();
		$('#billing-address-paypal-form').attr('value', selected);
		$('#address-paypal-show-hide-element').html('');
	}else{
		$('#address-element').show();
		$('#address-paypal-show-hide-element').html('<a href="#" onclick="showHideAddressPayment()">[-]</a>');
	}
}

function selectShipping(){
	var selected = $('#shipping-address-selection :selected').val();
	if(selected == ''){
		return false;
	}
	if(selected != -1){
		populateConfirmAddress(selected, '#confirm-shipping-address', "Shipping");
		$('#shipping-address-payment-form').attr('value', selected);
		$('#address-payment-show-hide-element').html('');
		$('#address-element').hide();
	}else{
		$('#address-element').show();
		$('#address-payment-show-hide-element').html('<a href="#" onclick="showHideAddressPayment()">[-]</a>');
	}
}

function selectCreditCard(){
	var selected = $('#card-selection :selected').val();
	if(selected == ''){
		return false;
	}
	if(selected != -1){
		populateConfirmCredit();
		$('#card-element').hide();
		$('#credit-card-id').attr('value', selected);
		$('#credit-payment-show-hide-element').html('');
	}else{
		$('#card-element').show();
		$('#credit-payment-show-hide-element').html('<a href="#" onclick="showHideCreditPayment()">[-]</a>');
	}
}

function selectPaypal(){
	var selected = $('#paypal-selection :selected').val();
	if(selected == ''){
		return false;
	}
	if(selected != -1){
		populateConfirmPaypal();
		$('#paypal-element').hide();
		$('#payment-paypal-id').attr('value', selected);
		$('#paypal-payment-show-hide-element').html('');
	}else{
		$('#paypal-element').show();
		$('#paypal-payment-show-hide-element').html('<a href="#" onclick="showHidePaypalPayment()">[-]</a>');
	}
}

function selectContact(){
	var selected = $('#contact-selection :selected').val();
	if(selected == ''){
		return false;
	}
	if(selected != -1){
		populateConfirmContact();
		$('#contact-element').hide();
		$('#payment-contact-id').attr('value', selected);
		$('#contact-payment-show-hide-element').html('');
	}else{
		$('#contact-element').show();
		$('#contact-payment-show-hide-element').html('<a href="#" onclick="showHideContactPayment()">[-]</a>');
	}
}
