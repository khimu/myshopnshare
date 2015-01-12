<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<title>MY SHOP N SHARE</title>
	
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		
	<link href="/css/shopnshare/style.css" rel="stylesheet" type="text/css" />
	<link href="/css/shopnshare/login.css" media="screen" rel="stylesheet"
		type="text/css" />

	<link type="text/css" href="/css/ui.datepicker.css" rel="stylesheet" />
	<script type="text/javascript" src="/js/jquery-1.2.6.js"></script>
	<script type="text/javascript" src="/js/ui.core.js"></script>
	<script type="text/javascript" src="/js/ui.datepicker.js"></script>

	<link rel="stylesheet" type="text/css" media="screen" href="/js/validation/jquery-validate/css/screen.css" />	
	<script src="/js/validation/jquery-validate/jquery.validate.min.js" type="text/javascript"></script>

  <link rel="stylesheet" media="screen,projection" type="text/css" href="/css/crystalx/main.css" />
   <link rel="stylesheet" media="print" type="text/css" href="/css/crystalx/print.css" />
   <link rel="stylesheet" media="aural" type="text/css" href="/css/crystalx/aural.css" />	

<style type="text/css">
	#confirm-email-form LABEL {
		font-weight: bold;
		font-size: 1.2em;
		padding-right: 10px;
		vertical-align: center;
		color: #FFF;
	}
	
	.loginStyle {
		border: 1px solid #FFF; 
		padding: 5px;
		vertical-align: center;			
	}	
</style>

<script type="text/javascript">
	jQuery( function() {
		$('#forgotPasswordLink').click( function() {
			$('#forgotPassword').show();
		});
		$('#forgotUsernameLink').click( function() {
			$('#forgotUsername').show();
		});
	});
</script>

</head>
	<body>	
		<div id="main" class="box">
		
					<img src="/images/ss_logo.png" />
		
					<div style="padding: 5px;width:400px;">
					<c:choose>
						<c:when test="${not empty accountInActive}">
							Would you like to reactivate your account?
							<a href="/public/retrieve.do?method=reactivate&email=${email}">Activate</a>
						</c:when>
						<c:otherwise>
							This email already exist.  Did you forget your password?  Please enter it to get a new password sent to you.
					
							<a id="forgotPasswordLink"
								href="/secure/retrieve.do?method=forgotPassword">Forgot
							Password?</a>
					
							<jsp:include page="forgotPassword.jsp" />
					
							<a id="forgotUsername"
								href="/secure/retrieve.do?method=forgotUsername">Forgot
							Email?</a>
					
							<jsp:include page="forgotUsername.jsp" />
						</c:otherwise>
					</c:choose>
					</div>
		</div>
	</body>
</html>

