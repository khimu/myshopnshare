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

</head>
	<body>	
<div id="main" class="box">

			<img src="/images/ss_logo.png" />

				<h3>Confirm Email</h3>
			<c:choose>
				<c:when test="${not empty error}">
					<font color="red"> Your confirmation number is invalid.
					<br />
					<br />
					Please try again. </font>
				</c:when>
				<c:otherwise>
					<p>A message has been sent to your email at ${email}. Please
					follow the link to confirm your email.</p>
				</c:otherwise>
			</c:choose>
		
			<p id="confirmEmailForm">
			<form:form action="confirmEmail.do" commandName="confirmForm"
				method="POST">
					<p id="confirm-email-form">
						<label for="confirm">Confirm: </label>
						<input type="text" id="confirm" name="confirm" size="30" maxlength="80" />
						<button name="button" id="addButton" type="submit">Confirm</button>
					</p>
			</form:form>
			</p>
		</div>
	</body>
</html>