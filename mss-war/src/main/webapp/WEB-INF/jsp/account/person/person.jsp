<%@ include file="/WEB-INF/jsp/includes.jsp"%>
	
		<script type="text/javascript">
		
			// DATE IN JAVASCRIPT
			// new Date().getTime()
		
			$(document).ready(function() {
				
				$('#edit-person').click(function(){
					new Boxy('#edit-name-form', {title: 'Account Info', fixed: false, modal: true});
				});
				
				$('#delete-person').click(function(){
					$('#loading').show();
					Boxy.confirm("Please confirm delete:", function() {
						$.ajax({
							url: '/secure/person.do?method=delete',
						    type: 'post',
						    dataType: 'text',
						    error: function(){
								$('#loading').hide();
						        Boxy.alert('Error deleting acount info');
						    },
						    success: function(xml){		
						    	$('#loading').hide();
							    Boxy.alert('Your account has been deleted.  You can reactivate your account at any time.');
						    }
						});	
					}, {title: 'Confirm Delete'});
				    return false;
				});

				$('#edit-submit-name-button').click(function(){
					$('#loading').show();
					var name_update = jQuery("#edit-name-form").validate( {
						rules : {
							firstName :"required",
							lastName :"required",
							birthday: "required"
						},
						messages : {
							firstName :"<div class='errors'>Please enter your email type.</div>",
							lastName :"<div class='errors'>Please enter a valid email address</div>",
							birthday :"<div class='errors'>Please enter a valid email address</div>"
						},
						submitHandler : function(form) {
							jQuery(form).ajaxSubmit( {
								url: '/secure/person.do',
							    type: 'post',
							    data: {method: 'edit'},
							    target: '#person-header',
							    dataType: 'text',
							    timeout: 1000,
							    error: function(){
							        Boxy.alert('Error saving acount info');
							    },
							    success: function(xml){			
							    	$('#loading').hide();
								    Boxy.alert('Your change has been updated.');
							    	Boxy.get('#edit-name-form').hide();
							    }
							});
						}
					});			
				});
				
			});	
		</script>

<style type="text/css">
	#account-first-name {
		margin: 0px;
		margin-bottom: 10px;
		position: relative;
		float: none;
		clear: both;
		border: 1px solid black;
	}
	#account-first-name div {
		float: left;
		padding: 5px;
	}
	#person-header {
		float: none;
		clear: both;
		padding: 5px;
		font-size: 14px;
		width:760px;
	}
</style>

	<div id="person-header" class="left-right">
		<div style="float:left;">${person.firstName} ${person.lastName}</div>
		<div style="float:right;">
			<a id="delete-person" href="#"><img src="/images/buttons/deleteaccount.png" /></a>
			<a id="edit-person" href="#"><img src="/images/buttons/edit.png" /></a>
		</div>	
	</div>
<div class="clear"></div>

<div id="edit-account-name-form" style="display: none;">
	<form id="edit-name-form">
		<input type="hidden" name="id" /> 
		<div>
			<div><label for="firstName">First Name: </label></div>
			<div><input type="text" id="firstName" name="firstName" value="${person.firstName}"/></div>
			<div><label for="lastName">Last Name: </label></div>
			<div><input type="text" id="lastName" name="lastName" value="${person.lastName}" /></div>
			<div><label for="birthday">Birthday: </label></div>
			<c:set var="personbday">
			<fmt:formatDate value="${person.birthday}" pattern="MM/dd/yyyy" />
			</c:set>
			<div><input type="text" id="birthday" name="birthday" value="${personbday}" /></div>			
		</div>
		<button name="button" id="edit-submit-name-button" type="submit">Edit</button>
		<button name="button" type="reset" >Reset</button>
	</form>
</div>	
