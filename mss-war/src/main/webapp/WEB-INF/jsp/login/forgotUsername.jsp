<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">
	jQuery( function() {
		var v = jQuery("#forUsernameForm").validate( {
			submitHandler : function(form) {
				jQuery(form).ajaxSubmit( {
					target : "#forgotUsername",
					success: function(data){
						$('#fragment-3').hide();
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

<div id="forgotUsername" class="loginStyle">
	<div style="float: none; clear:both;"><h3>Forgot Username</h3></div>
	<form id="forUsernameForm" name="forgotUsernameForm" method="POST" action="public/retrieve.do?method=forgotUsername">
	<div style="white-space: nowrap;">
		<label for="emailAddress" style="clear: both;">Email Address</label> 
		<input type="text" id="emailAddress" name="emailAddress"  size="30" maxlength="80" style="float: none;" />
		<button name="submit" id="usernameSubmit"
			type="submit">GO</button>
	</div>
	</form>
	<div class="loginLinks" style="float: none; clear: both;">
		<a href="#fragment-1">Login</a>
	</div>
</div>
