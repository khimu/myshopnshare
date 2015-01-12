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

		<script type="text/javascript" src="/js/shopnshare/FormCheck.js"></script>	
	
	<script type="text/javascript" src="/js/jquery-1.2.6.js"></script>

     	<script type='text/javascript' src='/js/boxy-0.1.4/jquery.boxy.js'></script>
      	<link rel="stylesheet" href="/js/boxy-0.1.4/boxy.css" type="text/css" />			

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

		$('#registerSubmit').click(function(){
			if(!isZIPCode($('#employerForm').find('#postalCode').val())){
				Boxy.alert('Zip/Postal Code is required');
				return false;
			}
		});
		
		var v = $("#employerForm").validate( {
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
            	<li id="active"><a href="#">Register<span class="tab-l"></span><span class="tab-r"></span></a></li> <%-- Active --%>
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
			<div id="forgotPassword" class="forgot-password">
			<form id="forgotPasswordForm" name="forgotPasswordForm" action="/public/retrieve.do?method=forgotPassword" method="POST">		
			<div>
					<div>
							<div><h3>Your password will be sent to the email provided.</h3></div>
							<div class="errors">${passwordMessage}</div>	
					</div>
					<div>
						<div><label for="emailAddress" style="float: left; clear: both;">Login Email Address</label>
						</div>
						<div>
						<input type="text" id="emailAddress" name="emailAddress" size="30" maxlength="80"  style="float: none; clear: left;" />
						<button name="submit" id="passwordSubmit"
							type="submit">GO</button>
						</div>
					</div>
			</div>
			</form>
			</div>					
		

        <%-- Content --%>
        <div id="content">	
       	 	<p id="breadcrumbs"><a href="/register.do">User</a> &nbsp;|&nbsp; <strong><a href="/employerRegister.do"><big>Business</big></a></strong> &nbsp;|&nbsp; <a href="/institutionRegister.do">Institution</a></p>
            <hr class="noscreen" />
            <h3>Merchant Registration</h3>
			<hr/>
	        <div id="center-2" style="display: none;height: 100%;">
	      	  	<%@ include file="../public/all.jsp" %>
	        </div>        
	        <div id="center-3" style="display: none;">
	        	<%@ include file="../public/all.jsp" %>
	        </div>			
			<div id="center">
			<form:form id="employerForm" commandName="register" action="/employerRegister.do" method="post">
			<div class="register">
				<c:if test="${not empty registerMessage}">
						<div class="errors">${registerMessage}</div>
						<div class="clear"></div>
				</c:if>
				<div class="registerDIV">
					<div style="white-space: nowrap; width: 100px"><span class="asterisk">*</span><label for="email">Email Address</label></div>
					<div><form:input id="email" path="email.email" /></div>

					<div style="white-space: nowrap; width: 100px"><span class="asterisk">*</span><label for="companyName">Business Name</label></div>
					<div><form:input id="companyName" path="employer.companyName" /></div>

					<div style="white-space: nowrap; width: 100px"><span class="asterisk">*</span><label for="firstname">Administrator First Name</label></div>
					<div><form:input id="firstname" path="employer.firstName" /></div>

					<div style="white-space: nowrap; width: 100px"><span class="asterisk">*</span><label for="lastname">Administrator Last Name</label></div>
					<div><form:input id="lastname" path="employer.lastName" /></div>

					<div><span class="asterisk">*</span><label for="password">Password</label></div>
					<div><form:password id="mpassword" path="password" /></div>

					<div style="white-space:nowrap;"><span class="asterisk">*</span><label for="confirm_password">Confirm Password</label></div>
					<div><form:password id="confirm_password" path="confirmPassword" /></div>	
					<div><label for="tags">Specialization</label></div>
					<div>
						<form:textarea id="tags" path="tags" rows="2" cols="40" />	
					</div>										
				</div>
				
				<div class="registerDIV">
					<div><span class="asterisk">*</span><label for="street1">Street1</label></div>
					<div><form:input  id="street1" path="address.street1"/></div>
					<div><label for="street2">Street2</label></div>
					<div><form:input  id="street2" path="address.street2" /></div>
					<div><label for="unitNumber">Unit/Apt</label></div>
					<div><form:input  id="unitNumber" path="address.unitNumber" /></div>
					<div><span class="asterisk">*</span><label for="city">City</label></div>
					<div><form:input  id="city" path="address.city"/></div>
					<div><span class="asterisk">*</span><label for="state">State/Provance</label></div>
					<div><select id="stateOrProvince" name="address.stateOrProvince" size="1"><%@ include file="../account/address/state.jsp" %></select></div>
					<div><span class="asterisk">*</span><label for="zip">Zip/Postal Code</label></div>
					<div><form:input  id="postalCode" path="address.postalCode"/></div>
					<div><span class="asterisk">*</span><label for="addressType">Address Type</label></div>
					<div>
						<form:select path="addressType">
							<option value="" selected="selected">------------</option>
							<c:forEach items="${addressTypes}" var="type">
								<form:option value="${type.value}">${type.label}</form:option>
							</c:forEach>
						</form:select>	
					</div>
					<div><span class="asterisk">*</span>Country</div>
					<div><select name="address.country" id="countryComboBox"><%@ include file="../account/address/country.jsp" %></select></div>
				</div>
			</div>
			<div class="clear"></div>
			<div class="registerBottom">
				<div style="padding-top:5px;padding-bottom:5px;">
					Address Permission
					<form:radiobutton cssClass="registerSm" path="visibility" value="PRIVATE" /> PRIVATE
					&nbsp;&nbsp;
					<form:radiobutton cssClass="registerSm" path="visibility" value="PUBLIC" /> PUBLIC
				</div>			
	
				<div style="vertical-align: top;"><span class="asterisk">*</span><label for="mbirthday">Inception Date</label></div>
				<div>
					<div class="left-right">
						<div> 
							<div>MM:</div> 
							<div class="clear"></div>
							<div><form:input id="month" path="month" size="1" maxlength="2" /></div>
						</div>
						<div> <div>DD:</div> <div class="clear"></div><div><form:input id="day" path="day" size="1" maxlength="2" /></div></div>
						<div> <div>YYYY:</div> <div class="clear"></div><div><form:input id="year" path="year" size="2" maxlength="4" /></div></div>
					</div>
					<div class="clear"></div>
				</div>	
				<div style="vertical-align: top;"><label for="description">Description</label></div>
				<div>
					<form:textarea id="description" path="employer.description" rows="4" cols="40"/>	
				</div>		
				<div><input type="checkbox" id="agree" name="agree" /><span class="asterisk">*</span><label for="agree">Please agree to our policy</label></div>
				<div style="white-space: nowrap; width: 100px"></div>
	
				<input type="submit" id="registerSubmit" name="Submit" class="submit" value="Submit" />	
			</div>
			</form:form>
			</div><%-- End of Center Div --%>
		</div>     		  
    </div> <%-- /page-in --%>
    </div> <%-- /page --%>
		<jsp:include page="footer.jsp"/>	
	</div>
	</body>
</html>

