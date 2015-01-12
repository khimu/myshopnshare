<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>
		
<script type="text/javascript">
	jQuery( function() {
		$('#fragment-2').click(function(){
			if($('#forgotPassword').is(":visible")){
				$('#forgotPassword').hide();
			}else{
				$('#forgotPassword').show();
			}
		});

		$('#fragment-3').click(function(){
			$('#forgotPassword').hide();
			$('#forgotUsername').show();
		});

		$('#passwordSubmit').click(function(){
			jQuery("#forgotPasswordForm").validate( {
				submitHandler : function(form) {
					jQuery(form).ajaxSubmit( {
						target : "#forgotPassword"
					});
				}
			});
		});
		
		$('#forgotUsername').click(function(){
			jQuery("#forgotUsernameForm").validate( {
				submitHandler : function(form) {
					jQuery(form).ajaxSubmit( {
						target : "#forgotUsername"
					});
				}
			});
		});
	});
</script>

	<div class="clear"></div><div class="clear"></div>
			<form name="loginForm" class="cmxform" action="/j_spring_security_check" method="post">
			<div>
				<div class="left-right" style="font-size:12px;">
					<div style="width:100px;"><label for="j_username">Email</label>&nbsp;&nbsp;</div>
					<div><input id="j_username" name="j_username" title="Please enter your email address" class="required email" minlength="6" /></div>
				</div>
				<div class="clear"></div>
				<div class="left-right" style="font-size:12px;">
					<div style="width:100px;"><label for="j_password">Password</label>&nbsp;&nbsp;</div>
					<div><input type="password" name="j_password" id="j_password" class="required" minlength"5" /></div>
				</div>
				<div>
						<p id="checkboxStyle" style="display: none;">
							<input type="checkbox" id="cb"
								name="_spring_security_remember_me"
								checked="checked" /> 
								<label for="_spring_security_remember_me">Stay logged in </label>
						</p>
				</div>
				<div class="clear"></div>
				<div>
					<a style="color:#FFF;margin-left:145px;" href="#" id="fragment-2">Forgot Password </a>	
				</div>					
				<div class="clear"></div>				
				<div style="margin-left: 190px;">
					<input class="submit" type="image" value="Login" src="/images/login.png" />
				</div>				
			</div>
			</form>
						


			