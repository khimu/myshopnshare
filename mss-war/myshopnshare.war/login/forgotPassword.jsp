<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">
	jQuery( function() {
		var v = jQuery("#forPasswordForm").validate( {
			submitHandler : function(form) {
				jQuery(form).ajaxSubmit( {
					target : "#forgotPassword",
					success: function(data){
						$('#fragment-2').hide();
						$('#fragment-1').show();
					}
				});
			}
		});
		$('.loginLinks a').click(function(){
			$('.loginLinks a').each(function(){
				if($($(this).attr('href') + ' div').is(':visible')){
					$($(this).attr('href') + ' div').hide();
				}
			});
			$($(this).attr('href') + ' div').show();
		});
	});
</script>


<div id="forgotPassword" class="loginStyle">
	<div><h3>Forgot Password</h3></div>
	<div class="errors">${passwordMessage}</div>

	<form id="forgotPasswordForm" name="forgotPasswordForm" action="public/retrieve.do?method=forgotPassword" method="POST">
		<div style="white-space: nowrap;">
			<label for="emailAddress" style="float: left; clear: both;">Email Address </label> 
			<input type="text" id="emailAddress" name="emailAddress" size="30" maxlength="80"  style="float: none; clear: both;" />
			<button name="submit" id="passwordSubmit"
				type="submit">GO</button>			
		</div>
		<div class="loginLinks" style="float: none; clear: both;">
			<a href="#fragment-1">Login</a>
		</div>
	</form>
</div>