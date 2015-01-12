<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">

function getCreditCard(){
	$.ajax( {
		type :"post",
		url :"/secure/creditCard.do",
		data : {method: 'all'},
		dataType :'json',
		success : function(json) {
			$.each(json.creditcards, function(i,c){
				$('#creditcard-replace-me').append('<sns:reportrecords records="'+c.cardName+'|'+c.fullName+'|'+c.number+'|'+c.expirationDate+'|'+c.securityCode+'|'+c.type+'" primaryKey="' + c.id + '" deleteURL="/secure/creditCard.do?method=" idType="creditcard" />');
			});
	    }
	});		
}
/*
function setDateFromDialog(date) {
	$('#expirationDate').val(date);
}
*/
$(document).ready(function() { 		
	getCreditCard();
	/*
	$('#expirationDate').click(function(){
		$.datepicker._dialogDatepicker($('#expirationDate').val(),
				setDateFromDialog, {prompt: 'Choose a date', speed: ''});
	});
	*/

	$('#add-creditcard').click(function(){
		new Boxy('#add-creditcard-form', {title: 'Credit Card', fixed: false, modal: true});
	});
	
	$('#submit-creditcard-button').click(function(){
		if(!isInteger($('#add-creditcard-form').find('#number').val()) || !isCreditCard($('#add-creditcard-form').find('#number').val())){
			Boxy.alert('Your credit card number is incorrect');
			return false;
		}

		if(!isInteger($('#add-creditcard-form').find('#securityCode').val())){
			Boxy.alert('Your security code must be a number');
			return false;
		}
					
		$('#loading').show();
		var email_update = jQuery("#add-creditcard-form").validate( {
			rules : {
				fullname :"required",
				expirationDate :"required",
				creditCardType :"required"
			},
			messages : {
				fullname :"<div class='errors'>Please enter your full name</div>",
				expirationDate :"<div class='errors'>Please enter the card expiration date</div>",
				creditCardType :"<div class='errors'>Please enter the card security code</div>"
			},
			submitHandler : function(form) {
				jQuery(form).ajaxSubmit( {
					url: '/secure/creditCard.do?method=add', 
					type: 'post',
					dataType: 'json',
					success: function(c) { 
						$('#loading').hide();
						$('#add-creditcard-form').clearForm();
						Boxy.get('#add-creditcard-form').hide();
						$('#creditcard-replace-me').append('<sns:reportrecords records="'+c.cardName+'|'+c.fullName+'|'+c.number+'|'+c.expirationDate+'|'+c.securityCode+'|'+c.type+'" primaryKey="' + c.id + '" deleteURL="/secure/creditCard.do?method=" idType="creditcard" />');
						//$('#creditcard-replace-me').append('<tr><td colspan="7">'+c.cardAddress.street1+'</td><td id="edit_cardaddress_'+c.cardAddress.id+'"><a href="#" onclick="edit_record('+c.cardAddress.id+', \'cardaddress\');return false;">Edit</a></td></tr>');																		
					}			
				});
			}
		});			
	});
});	
</script>

<style type="text/css">
	#account-creditcard {
		margin: 0px;
		position: relative;
	}
	#account-creditcard ul {
		list-style-type: none;
	}
	#account-creditcard div {
		float: left;
		padding: 5px;
	}
	#account-creditcard LI {
		list-style-type: none;
	}
	#account-creditcard UL {
		float: none;
		clear: both;
	}
	#creditcard-header {
		float: none;
		clear: both;
	}
</style>

	<div id="creditcard-header">
		Credit Card 
		<span>
			[<a id="add-creditcard" href="#">Add</a>]
		</span>
	</div>

<sns:report tableName="creditCardTable" columns="Card Name|Full Name|Card Number|Expiration Date|Security Code|Card Type|-|-|-|">
	<tbody id="creditcard-replace-me">
	</tbody> 
</sns:report>

<%@ include file="form.jsp"%>
<%@ include file="edit.jsp"%>