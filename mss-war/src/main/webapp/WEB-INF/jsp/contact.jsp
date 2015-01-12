<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<title>${application}</title>
	
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		
	<link href="/css/shopnshare/style.css" rel="stylesheet" type="text/css" />
	<link href="/css/shopnshare/login.css" media="screen" rel="stylesheet" type="text/css" />

	<script type="text/javascript" src="/js/jquery-1.2.6.js"></script>

  <link rel="stylesheet" media="screen,projection" type="text/css" href="/css/crystalx/main.css" />
   <link rel="stylesheet" media="print" type="text/css" href="/css/crystalx/print.css" />
   <link rel="stylesheet" media="aural" type="text/css" href="/css/crystalx/aural.css" />	

<script type="text/javascript">
function publicSearch(){
	 $.ajax({  
	       type: "post",  
	       url: '/public/search.do',
	       dataType: 'html',
	       success: function(data){  
		       $('#center').hide();
	    	   $('#center-3').html(data);
	    	   $('#center-3').show();
	       } 
	     });
    return false;
}
</script>

</head>

	<body onLoad="self.focus(); document.contact.emailAddress.focus()" >	
		<div id="main" class="box">
		
		    <%-- Header --%>
		    <%@ include file="public/header.jsp" %>
		
		     <%-- Main menu (tabs) --%>
		     <div id="tabs" class="noprint">
		     
		
		            <h3 class="noscreen">Navigation</h3>
		            <ul class="box">
		            	<li><a href="/secure/home.do">Home<span class="tab-l"></span><span class="tab-r"></span></a></li>
		            	<li><a href="/public/publicPage.do">Login<span class="tab-l"></span><span class="tab-r"></span></a></li>
		            	<li><a href="/register.do">Register<span class="tab-l"></span><span class="tab-r"></span></a></li>
		            	<li id="active"><a href="/contact.do">Contact<span class="tab-l"></span><span class="tab-r"></span></a></li>
		                <li><a href="/mainPage.do?method=terms">Terms Of Use<span class="tab-l"></span><span class="tab-r"></span></a></li>
		                <li><a href="/mainPage.do?method=privacy">Privacy<span class="tab-l"></span><span class="tab-r"></span></a></li>
		            </ul>
		
		        <hr class="noscreen" />
		     </div> <%-- /tabs --%>
		
		        <div id="main">	
					<hr/>
					
				     	<div style="margin:20px;width:300px">
					     	<div>4067 Hardwick St #338</div>
					     	<div>Lakewood, CA, 90712</div>
					     	<div>sleekswap@gmail.com</div>
				     	</div>
		
						<h3>Have a concern?  Express it here!</h3>
					
						<c:if test="${not empty msg}">
						<div class="errors">${msg}</div>
						</c:if>
			
					<div id="center">
						<form name="contact" action="/contact.do" method="post">
								<div><label for="email">Email Address </label></div>
								<div><input type="text" name="emailAddress" id="email" size="70" tabindex="1" /></div>
								<div><label for="fullname">Full Name </label></div>
								<div><input type="text" id="fullname" name="fullName" size="70" tabindex="2" /></div>
								<div><label for="subject">Subject </label></div>
								<div><input type="text" id="subject" name="subject" size="70" tabindex="3" /></div>
								<div><label for="message">Message</label></div>
								<div><textarea name="message" rows="4" cols="50" tabindex="4" ></textarea></div>
								<div>
									<p>
										<input class="submit" type="submit" value="Send"/>
										<input class="submit" type="reset" value="Reset"/>
									</p>	
								</div>
						</form>  
					</div>
				</div>
				<div class="clear"></div>
				<jsp:include page="login/footer.jsp"/>
		</div>		
	</body>
</html>
