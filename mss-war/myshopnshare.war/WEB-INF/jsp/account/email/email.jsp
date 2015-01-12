<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">

	function getEmails(){
		$.ajax( {
			type :"POST",
			url :"/secure/email.do",
			data : {method: 'all'},
			dataType :'json',
			success : function(json) {
				$.each(json.emails, function(i,email){
					$('#replaceMe').append('<sns:reportrecords records="'+email.email+'" isPrimary="'+email.primaryEmail+'" primaryKey="'+email.id+'" deleteURL="/secure/email.do?method=" idType="email" />');
		         });
		    }
		});		
	}
	
	$(document).ready(function() { 
		getEmails();
		
		$('#add-email').click(function(){
			new Boxy('#add-email-form', {title: 'Email', fixed: false, modal: true});
		});
		
		$('#submit-email-button').click(function(){
			$('#loading').show();
			var email_update = jQuery("#add-email-form").validate( {
				rules : {
					email : {
						required :true
					}
				},
				messages : {
					email :"<div class='errors'>Please enter your email address.</div>"
				},
				submitHandler : function(form) {
					jQuery(form).ajaxSubmit( { 
						url: '/secure/email.do?method=add',
						type: 'post',
						dataType: 'json',
						success: function(email) { 
							$('#add-email-form').clearForm();
							$('#loading').hide();							
							Boxy.get('#add-email-form').hide();
							$('#replaceMe').append('<sns:reportrecords records="'+email.email+'" isPrimary="'+email.primaryEmail+'" primaryKey="'+email.id+'" deleteURL="/secure/email.do?method=" idType="email" />');
						}
					});
				}
			});
			$('#loading').hide();
		});
		$('#edit-submit-email-button').click(function(){
			$('#loading').show();
			var email_update = jQuery("#edit-email-form").validate( {
				rules : {
					email : {
						required :true
					}
				},
				messages : {
					email :"<div class='errors'>Please enter your email address.</div>"
				},
				submitHandler : function(form) {
					jQuery(form).ajaxSubmit( { 
						url: '/secure/email.do?method=edit',
						type: 'post',
						dataType: 'json',
						success: function(email) { 
							$('#edit-email-form').clearForm();
							$('#loading').hide();
							Boxy.get('#edit-email-form').hide();
							var recordid = $('#edit-email-form').find('input:nth-child(1)').attr('value');
							$('#edit_email_'+ recordid).parent().remove();							
							$('#replaceMe').append('<sns:reportrecords records="'+email.email+'" isPrimary="'+email.primaryEmail+'" primaryKey="'+email.id+'" deleteURL="/secure/email.do?method=" idType="email" />');
						}
					});
				}
			});
			$('#loading').hide();
		});
		
	});	
</script>

<div id="email-header" class="left-right">
	<div style="float:left;">Email Accounts</div>
	<div style="float:right;">
		<a id="add-email" href="#"><img src="/images/buttons/add.png" /></a>
	</div>
</div>	

<sns:report tableName="emailTable" columns="Email Address|-|-|-|">
	<tbody id="replaceMe"> 
	</tbody> 
</sns:report>

<%@ include file="form.jsp"%>

<%@ include file="edit.jsp"%>

