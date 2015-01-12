<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<style type="text/css">
.warning {
	color: red;
}

</style>
<script type="text/javascript">
	$(document).ready(function(){
		$("a[rel^='prettyPhoto']").prettyPhoto();
	});
	
	jQuery( function() {

		$('#fragment-2').click(function(){
			if($('#forgotPassword').is(':visible')){
				$('#forgotPassword').hide();
			}else{
				$('#forgotPassword').show();
			}
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
		
	});
</script>

<script type="text/javascript">
	jQuery( function() {
		// show a simple loading indicator
		var loader = jQuery(
				'<div id="loader"><img src="/images/loading.gif" alt="loading..." /></div>')
				.css( {
					position :"relative",
					top :"1em",
					left :"25em"
				}).appendTo("body").hide();
		jQuery().ajaxStart( function() {
			loader.show();
		}).ajaxStop( function() {
			loader.hide();
		}).ajaxError( function(a, b, e) {
			throw e;
		});
	});

</script>


<div style="position:relative;">
	<div style="float:left">

		<div style="width:560px;height:250px;background-image: url(/images/loginbg.png);background-repeat: no-repeat;padding:5px;">	
			<h1>Please Login!</h1>		
			
			<c:if test="${not empty accountInActive}">
			<div class="errors">${accountInActive}</div>
			</c:if>
			
			<form name="loginForm" class="cmxform" action="/j_spring_security_check" method="post">
			<table cellpadding="3">
				<c:if test="${not empty param.login_error}">
				<tbody id="login-box"  style="display: block;">
				<tr>
					<td colspan="2">
						<div class="errors">Your login attempt
						was not successful, try again.<br />
						Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />.
						</div>
					</td>
				</tr>
				</tbody>
				</c:if>
				<tr>
					<td><label for="j_username">Email</label></td>
					<td><input id="j_username" name="j_username" title="Please enter your email address" class="required email" minlength="6" /></td>
				</tr>
				<tr>
					<td><label for="j_password">Password</label></td>
					<td><input type="password" name="j_password" id="j_password" class="required" minlength"5" /></td>
				</tr>
				<tr>
					<td colspan="2">
						<p id="checkboxStyle" style="display: none;">
							<input type="checkbox" id="cb"
								name="_spring_security_remember_me"
								checked="checked" /> 
								<label for="_spring_security_remember_me">Stay logged in </label>
						</p>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
							<input class="submit" type="submit" value="Login"/>
					</td>
				</tr>
				<tr>
					<td>
						<a href="#" id="fragment-2">Forgot Password </a>		
					</td>	
				</tr>	
			</table>
			</form>
			
			<div id="forgotPassword" class="forgot-password">
			<form id="forgotPasswordForm" name="forgotPasswordForm" action="/public/retrieve.do?method=forgotPassword" method="POST">		
			<div>
					<div>
						<div><h3>Your password will be sent to the email provided.</h3></div>
						<c:if test="${not empty passwordMessage}">
							<div class="errors">${passwordMessage}</div>
						</c:if>	
					</div>
					<div>
						<div>
						<input type="text" id="emailAddress" name="emailAddress" size="30" maxlength="80"  style="float: none; clear: left;" />
						<button name="submit" id="passwordSubmit"
							type="submit">GO</button>
						</div>
					</div>
			</div>
			</form>
			</div>		
		</div>

		<img src="/images/latestitems.png"/>
		
		<div class="clear"></div>
		
		<%@ include file="all.jsp" %>
		
	</div>	

	<div style="float:left;">
		<img src="/images/leboutiques.png"/>
		<div class="clear"></div>
		<div style="width:250px;position:relative;">
		<c:forEach items="${merchants}" var="merchant">
			<span style="float:left;width:100px;padding:10px;">
				<a href="/public/merchant.do?friendId=${merchant.id}">${merchant.alias}</a>
				<div style="height:75px;"><a href="/${merchant.defaultFace.glimpse.path}" rel="prettyPhoto[merchants]" title="${merchant.alias}" alt="${merchant.alias}"><img src="/${merchant.defaultFace.icon.path}" alt="${merchant.alias}" /></a></div>
			</span>
		</c:forEach>
		</div>
	</div>
	
    <div style="float:left;">
		<script type="text/javascript"><!--
		google_ad_client = "pub-2178827339426285";
		/* 160x600, created 5/18/09 */
		google_ad_slot = "4798291882";
		google_ad_width = 160;
		google_ad_height = 600;
		//-->
		</script>
		<script type="text/javascript"
		src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
		</script>   
    </div>	
</div>


