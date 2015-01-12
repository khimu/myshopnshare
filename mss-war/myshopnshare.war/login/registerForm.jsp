<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<title>MY SHOP N SHARE</title>
	
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="keywords" content="rewards points, rewards, business, self promoting, meeting, joining, friends, connecting, linking, union, unite, united, bonding, social network, journals, scribbles, journal, advice, recommendation, recommend, wish list, scribble, social events, social calendars, networking, social shopping network, shop, marketing, advertising, advertise, promoting, selling" />
        <meta name="keywords" content="A social shopping network that allows members to sell services and items and grow their business through recommendations from previous clients/customers.  Allows users to get advice, recommend items, show wish list, and bought items." />
	<link href="/css/shopnshare/style.css" rel="stylesheet"
		type="text/css" />
	<link href="/css/shopnshare/login.css" media="screen"
		rel="stylesheet" type="text/css" />
	
	<script type="text/javascript" src="/js/jquery-1.2.6.js"></script>
	
		<style type="text/css">@import "/css/jquery.datepick.css";</style> 
		<script type="text/javascript" src="/js/jquery.datepick.js"></script>
	    
		<%@ include file="../common/common.jsp" %>
		<%@ include file="../common/loginjs.jsp" %>
		
		
  <link rel="stylesheet" media="screen,projection" type="text/css" href="/css/crystalx/main.css" />
   <link rel="stylesheet" media="print" type="text/css" href="/css/crystalx/print.css" />
   <link rel="stylesheet" media="aural" type="text/css" href="/css/crystalx/aural.css" />	
	
	<link rel="stylesheet" type="text/css" media="screen"
		href="/js/validation/jquery-validate/css/screen.css" />
	<script
		src="/js/validation/jquery-validate/jquery.validate.js"
		type="text/javascript"></script>
	<script
		src="/js/validation/jquery-validate/lib/jquery.form.js"
		type="text/javascript"></script>
	
	<script src="/js/jquery.watermarkinput.js" type="text/javascript"></script>

	<style type="text/css">
		.warning {
			color: red;
		}
	</style>

<script type="text/javascript">
	$(document).ready( function() {
				
		// check if confirm password is still valid after password changed
		$("#password").blur( function() {
			$("#confirm_password").valid();
		});

		var v = $("#registerForm").validate( {
			rules : {
				firstname :"required",
				lastname :"required",
				month :"required",
				day :"required",
				year :"required",
				password : {
					required :true,
					minlength :4
				},
				confirmPassword : {
					required :true,
					minlength :4,
					equalTo :"#mpassword"
				},
				email : {
					required :true,
					email :true
				},
				agree :"required"
			},
			messages : {
				email: "<div class='errors'>Email is required</div>",
				firstname :"<div class='errors'>First name required</div>",
				lastname :"<div class='errors'>Last name required</div>",
				password : {
					required :"<div class='errors'>Please provide a password</div>",
					minlength :"<div class='errors'>Your password must be at least 4 characters long</div>"
				},
				confirmPassword : {
					required :"<div class='errors'>Please provide a password</div>",
					minlength :"<div class='errors'>Your password must be at least 5 characters long</div>",
					equalTo :"<div class='errors'>Please enter the same password as above</div>"
				},
				month :"<div class='errors'>Month</div>",
				day :"<div class='errors'>Day</div>",
				year :"<div class='errors'>Year</div>",
				agree :"<div class='errors'>Please accept our policy</div>"
			},
			onSubmit: function(){
				var date = $('#register-datepicker').val().split("/");
				$('#month').attr('value', date[0]);
				$('#day').attr('value', date[1]);
				$('#year').attr('value', date[2]);
			}
		});
	
	});
</script>

</head>

<body>

<div id="main" class="box">

	<div id="main-header">
        <%@ include file="../public/header.jsp" %>

     <%-- Main menu (tabs) --%>
     <div id="tabs" class="noprint">

            <h3 class="noscreen">Navigation</h3>
            <ul class="box">
            	<li><a href="/public/publicPage.do">Login<span class="tab-l"></span><span class="tab-r"></span></a></li>            
                <li id="active"><a href="#">Sign Up<span class="tab-l"></span><span class="tab-r"></span></a></li>
                <li><a href="/contact.do">Contact<span class="tab-l"></span><span class="tab-r"></span></a></li>
                <li><a href="/mainPage.do?method=terms">Terms Of Use<span class="tab-l"></span><span class="tab-r"></span></a></li>
                <li><a href="/mainPage.do?method=privacy">Privacy<span class="tab-l"></span><span class="tab-r"></span></a></li>
            </ul>

        <hr class="noscreen" />
     </div> <%-- /tabs --%>
     </div>

    <%-- Page (2 columns) --%>
    <div id="page" class="box">
    <div id="page-in" class="box">
        <%-- Content --%>
        <div id="content">	
            <p id="breadcrumbs"><strong><a href="/register.do"><big>User</big></a></strong> &nbsp;|&nbsp; <a href="/merchantRegister.do">Merchant</a></p>
	        <div id="center-2" style="display: none;height: 100%;">
	      	  	<%@ include file="../public/all.jsp" %>
	        </div>        
	        <div id="center-3" style="display: none;">
	        	<%@ include file="../public/all.jsp" %>
	        </div>			
			<div id="center">
		<form id="registerForm" class="cmxform" action="register.do" method="post">
		
		<div class="register">
			<c:if test="${not empty registerMessage}">
				<div class="errors">${registerMessage}</div>
				<div class="clear"></div>
			</c:if>
			<div class="registerDIV">
				<div style="white-space: nowrap; width: 100px"><span class="asterisk">*</span><label for="email">Email Address</label></div>
				<div><input type="text" id="email" name="email" /></div>

				<div><span class="asterisk">*</span><label for="password">Password</label></div>
				<div><input id="mpassword" name="password" type="password" /></div>

				<div style="white-space:nowrap;"><span class="asterisk">*</span><label for="confirm_password">Confirm Password</label></div>
				<div><input id="confirm_password" name="confirmPassword" type="password" /></div>

				<div><span class="asterisk">*</span><label for="firstname">First Name</label></div>
				<div><input id="firstname" name="firstname" maxlength="15" value="" type="text" /></div>

				<div><span class="asterisk">*</span><label for="lastname">Last Name</label></div>
				<div><input id="lastname" name="lastname" maxlength="15" value="" type="text" /></div>

				<div style="white-space:nowrap;"><span class="asterisk">*</span><label for="stateOrProvince">State Or Province</label></div>		
				<div><select name="stateOrProvince" ><%@ include file="../account/address/state.jsp" %></select></div>

				<div><span class="asterisk">*</span>Country</div>
				<div><select name="country" id="countryComboBox"><%@ include file="../account/address/country.jsp" %></select></div>
			</div>
			<div class="float:left;">
			
				<div><span class="asterisk">*</span><label for="gender">Gender</label></div>
				<div style="padding-top:5px;padding-bottom:5px;">
					<input name="gender" id="male" type="radio" value="MALE" checked /> Male
					<input name="gender" id="female" type="radio" value="FEMALE" checked /> Female
				</div>			
				<div style="vertical-align: top;"><span class="asterisk">*</span><label for="mbirthday">Inception Date</label></div>
				<div>
					<div class="left-right">
						<div> 
							<div>MM:</div> 
							<div class="clear"></div>
							<div><input type="text" id="month" name="month" size="1" maxlength="2" /></div>
						</div>
						<div> <div>DD:</div> <div class="clear"></div><div><input type="text" id="day" name="day" size="1" maxlength="2" /></div></div>
						<div> <div>YYYY:</div> <div class="clear"></div><div><input type="text" id="year" name="year" size="2" maxlength="4" /></div></div>
					</div>
				</div>
			</div>
		</div>
		<div class="clear"></div>
			
		<div class="registerBottom">
			<div style="white-space: nowrap; width: 100px"></div>
			<div><input type="checkbox" id="agree" name="agree" /><label for="agree"><span class="asterisk">*</span>Please agree to our policy</label></div>
			<input id="registerSubmit" class="submit" type="submit" value="Submit" />
		</div>
		</form>
		</div>
		</div>       
    </div> <%-- /page-in --%>
    </div> <%-- /page --%>
		<jsp:include page="footer.jsp"/>
</div>
</body>
</html>

