function hideAllSubscribeForms(){
	$('#paypal-element').hide();
	$('#paypal-element').hide();
	$('#contact-element').hide();
	$('#card-element').hide();
	$('#paypal-element').hide();
}

function showHide(elementShow, elementHide){
	hideAllSubscribeForms();
	
	if($('#subscriptionType :selected').val() == ''){
		alert("Please select a subscription");
		return false;
	}

	$(elementShow).show();
	$('#subscribe-'+elementHide+'-element').hide();	

	$('#'+elementHide+'-element').hide();
	$('#' + elementHide + '-subscribe-show-hide-element a').html('');	
}

function isCheckedAddress(){
	if($('#same-as-billing').is(':checked')){
		$('#shipping-subscribe-label').hide();
		$('#shipping-address-selection').hide();
		var selected = $('#billing-address-selection :selected').val();
		if(selected != ''){
			populateConfirmAddress(selected, '#confirm-shipping-address', "Shipping");
		}
	}else{
		$('#shipping-subscribe-label').show();
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
			populateConfirmAddress(selected, '#confirm-shipping-address', "Shipping");
		}
		$('#address-element').hide();
		$('#billing-address-subscribe-form').attr('value', selected);
		$('#address-subscribe-show-hide-element').html('');
	}else{
		$('#address-element').show();
		$('#address-subscribe-show-hide-element').html('<a href="#" onclick="showHideAddresssubscribe()">[-]</a>');
	}
}

function selectShipping(){
	var selected = $('#shipping-address-selection :selected').val();
	if(selected == ''){
		return false;
	}
	if(selected != -1){
		populateConfirmAddress(selected, '#confirm-shipping-address', "Shipping");
		$('#shipping-address-subscribe-form').attr('value', selected);
		$('#address-subscribe-show-hide-element').html('');
		$('#address-element').hide();
	}else{
		$('#address-element').show();
		$('#address-subscribe-show-hide-element').html('<a href="#" onclick="showHideAddresssubscribe()">[-]</a>');
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
		$('#subscribe-contact-id').attr('value', selected);
		$('#contact-subscribe-show-hide-element').html('');	
	}else{
		$('#contact-element').show();
		$('#contact-subscribe-show-hide-element').html('<a href="#" onclick="showHideContactsubscribe()">[-]</a>');
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
		$('#credit-subscribe-show-hide-element').html('');		
	}else{
		$('#card-element').show();
		$('#credit-subscribe-show-hide-element').html('<a href="#" onclick="showHideCreditsubscribe()">[-]</a>');
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
		$('#subscribe-paypal-id').attr('value', selected);
		$('#paypal-subscribe-show-hide-element').html('');
	}else{
		$('#paypal-element').show();
		$('#paypal-subscribe-show-hide-element').html('<a href="#" onclick="showHidePaypalsubscribe()">[-]</a>');
	}
}

$(document).ready(function(){	
	selectBilling();	
	selectShipping();
	selectContact();
	selectPaypal();
	selectCreditCard();
	
	/** THIS WILL CLOSE ANYTHING THAT WAS OPENED BY THE method calls above **/
	hideAllSubscribeForms();
	
	isCheckedAddress();

	$('#contact-selection').change(function(){
		selectContact();
	});	
	
	$('#same-as-billing').click(function(){
		isCheckedAddress();
	});
	$('#billing-address-selection').change(function(){
		selectBilling();
	});
	$('#shipping-address-selection').change(function(){
		selectShipping();
	});	

	$('#card-selection').change(function(){
		selectCreditCard();
	});		
	$('#cancel-subscribe-button').click(function(){
		window.location = "/myshopnshare/secure/home.do";
	});
	$('#paypal-selection').change(function(){
		selectPaypal();
	});
	
	$('#submit-subscribe-button').click(function(){
		$('#loading').show();

		var params = $('#subscribe-form').serialize();
		$.ajax( {
			type :"post",
			url: '/secure/subscribe.do?method=confirm&'+params,
			dataType: 'html',
			success : function(data) {
				$('#center').html(data);
				$('#loading').hide();
				$('#shopping-cart-size').html('<img src="/images/insert_to_shopping_cart.png" width="15" height="15" alt="" title="" />Shopping Cart: 0');
		    }
		});			
		
		$('#loading').hide();
	});
});

function showConfirmation(elementId, pageId){
	hideAllSubscribeForms();
	$('#subscribe-credit-element').hide();
	$('#subscribe-paypal-element').hide();
	$('#subscribe-submit-element').show();
}

function showEndPrev(elementId){
	hideAllSubscribeForms();
	$('#subscribe-submit-element').hide();
	$(elementId).show();		
}

function showHideAddresssubscribe(){
	if($('#address-subscribe-show-hide-element a').html() == '[-]'){
		$('#address-subscribe-show-hide-element a').html('[+]');
		$('#address-element').hide();
	}else{
		$('#address-subscribe-show-hide-element a').html('[-]');
		$('#address-element').show();	
	}
}

function showHideContactsubscribe(){
	if($('#contact-subscribe-show-hide-element a').html() == '[-]'){
		$('#contact-subscribe-show-hide-element a').html('[+]');
		$('#contact-element').hide();
	}else{
		$('#contact-subscribe-show-hide-element a').html('[-]');
		$('#contact-element').show();		
	}
}	
function showHidePaypalsubscribe(){
	if($('#paypal-subscribe-show-hide-element a').html() == '[-]'){
		$('#paypal-subscribe-show-hide-element a').html('[+]');
		$('#paypal-element').hide();
	}else{
		$('#paypal-subscribe-show-hide-element a').html('[-]');
		$('#paypal-element').show();			
	}
}	
function showHideCreditsubscribe(){
	if($('#credit-subscribe-show-hide-element a').html() == '[-]'){
		$('#credit-subscribe-show-hide-element a').html('[+]');
		$('#card-element').hide();
	}else{
		$('#credit-subscribe-show-hide-element a').html('[-]');
		$('#card-element').show();			
	}
}				
