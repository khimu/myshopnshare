<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="/css/shopnshare/login.css" media="screen" rel="stylesheet"
			type="text/css" />
		<link href="/css/shopnshare/style.css" rel="stylesheet" type="text/css" />

		<script type="text/javascript" src="/js/jquery-1.2.6.js"></script>
		<link href="/css/thickbox.css" media="screen" rel="stylesheet" type="text/css" />		
		<script type="text/javascript" src="/js/thickbox-compressed.js"></script>
		<script src="/js/jquery.watermarkinput.js" type="text/javascript"></script>
		
		<link rel="stylesheet" href="/js/prettygallery/css/prettyGallery.css" type="text/css" media="screen" charset="utf-8" />
		<script src="/js/prettygallery/jquery.prettyGallery.js" type="text/javascript" charset="utf-8"></script>

		<link rel="stylesheet" href="/js/prettygallery/css/prettyPhoto.css" type="text/css" media="screen" charset="utf-8" />
		<script src="/js/prettygallery/jquery.prettyPhoto.js" type="text/javascript" charset="utf-8"></script>
								 
		<link rel="stylesheet" type="text/css" href="/css/shopnshare/fisheye.css" />	
		<script type="text/javascript" src="/js/jquery/interface/compressed/iutil.js"></script>
		<script type="text/javascript" src="/js/jquery/interface/compressed/fisheye.js"></script>	
		<script type="text/javascript" src="/js/jquery.cycle.all.min.js"></script>

     	<script type='text/javascript' src='/js/boxy-0.1.4/jquery.boxy.js'></script>
      	<link rel="stylesheet" href="/js/boxy-0.1.4/boxy.css" type="text/css" />		
	
		<script src="/js/shopnshare/page-level.js" type="text/javascript"></script>

		<link rel="stylesheet" type="text/css" media="screen" href="/js/validation/jquery-validate/css/screen.css" />	
		<script src="/js/validation/jquery-validate/jquery.validate.js" type="text/javascript"></script>
		<script src="/js/validation/jquery-validate/lib/jquery.form.js" type="text/javascript"></script>
		
		<script type="text/javascript" src="/js/ajaxfileupload.js"></script>

  <link rel="stylesheet" media="screen,projection" type="text/css" href="/css/crystalx/main.css" />
   <link rel="stylesheet" media="print" type="text/css" href="/css/crystalx/print.css" />
   <link rel="stylesheet" media="aural" type="text/css" href="/css/crystalx/aural.css" />	
		
		<title id="page-title"> MY SHOP N SHARE </title>	
		
	</head>

	<body>	
<div id="main" class="box">

	<%@ include file="public/header.jsp" %>
     <%-- Main menu (tabs) --%>
     <div id="tabs" class="noprint">

            <h3 class="noscreen">Navigation</h3>
            <ul class="box">
                <li><a href="/public/publicPage.do">Login<span class="tab-l"></span><span class="tab-r"></span></a></li>
                <li><a href="/register.do">Register<span class="tab-l"></span><span class="tab-r"></span></a></li> <%-- Active --%>
                <li><a href="/contact.do">Contact<span class="tab-l"></span><span class="tab-r"></span></a></li>
                <li><a href="/mainPage.do?method=terms">Terms Of Use<span class="tab-l"></span><span class="tab-r"></span></a></li>
                <li id="active"><a href="/mainPage.do?method=privacy">Privacy<span class="tab-l"></span><span class="tab-r"></span></a></li>

            </ul>

        <hr class="noscreen" />
     </div> <%-- /tabs --%>

    <%-- Page (2 columns) --%>
    <div id="page" class="box">
    <div id="page-in" class="box">

        <div id="strip" class="box noprint">
            <%-- Breadcrumbs --%>
            <p id="breadcrumbs">You are here: <a href="#">Home</a> &gt; <a href="#">Public</a> &gt;  <strong>Terms Of Use</strong></p>
            <hr class="noscreen" />
            
        </div> <%-- /strip --%>
		

        <%-- Content --%>
        <div id="content">
        <div id="center-2" style="display: none;height: 100%;">
        </div>        
        <div id="center-3" style="display: none;">
        	<%@ include file="public/all.jsp" %>
        </div>
       <div id="center"> 
        <p style="margin:20px;">
This privacy policy sets out how MY SHOP N SHARE uses and protects any information that you give MY SHOP N SHARE when you use this website.
</p>
<p style="margin:20px;">
MY SHOP N SHARE is committed to ensuring that your privacy is protected. Should we ask you to provide certain information by which you can be identified when using this website, then you can be assured that it will only be used in accordance with this privacy statement.
</p>
MY SHOP N SHARE may change this policy from time to time by updating this page. You should check this page from time to time to ensure that you are happy with any changes. This policy is effective from April 18, 2009.

<ul style="margin:20px;">
<li><h1>What we collect</h1></li>
<li>
We may collect the following information:
</li>
    <li>name and job title</li>
    <li> contact information including email address</li>
    <li> demographic information such as postcode, preferences and interests</li>
    <li>other information relevant to customer surveys and/or offers</li>

<li>
What we do with the information we gather
</li>

<li>We require this information to understand your needs and provide you with a better service, and in particular for the following reasons:
</li>
    <li> Internal record keeping.</li>
    <li>
      We may use the information to improve our products and services.</li>
    <li>We may periodically send promotional emails about new products, special offers or other information which we think you may find interesting using the email address which you have provided.
    </li>
    <li>
      From time to time, we may also use your information to contact you for market research purposes. We may contact you by email, phone, fax or mail. We may use the information to customise the website according to your interests.
	</li>
	</ul>
<h3>
Security</h3>
<p style="margin:20px;">
We are committed to ensuring that your information is secure. In order to prevent unauthorised access or disclosure we have put in place suitable physical, electronic and managerial procedures to safeguard and secure the information we collect online.
How we use cookies
</p>
<p style="margin:20px;">
A cookie is a small file which asks permission to be placed on your computer's hard drive. Once you agree, the file is added and the cookie helps analyse web traffic or lets you know when you visit a particular site. Cookies allow web applications to respond to you as an individual. The web application can tailor its operations to your needs, likes and dislikes by gathering and remembering information about your preferences.
</p>
<p style="margin:20px;">
We use traffic log cookies to identify which pages are being used. This helps us analyse data about webpage traffic and improve our website in order to tailor it to customer needs. We only use this information for statistical analysis purposes and then the data is removed from the system.
</p>
<p style="margin:20px;">
Overall, cookies help us provide you with a better website, by enabling us to monitor which pages you find useful and which you do not. A cookie in no way gives us access to your computer or any information about you, other than the data you choose to share with us.
</p>
<p style="margin:20px;">
You can choose to accept or decline cookies. Most web browsers automatically accept cookies, but you can usually modify your browser setting to decline cookies if you prefer. This may prevent you from taking full advantage of the website.
Links to other websites
</p>
<p style="margin:20px;">
Our website may contain links to other websites of interest. However, once you have used these links to leave our site, you should note that we do not have any control over that other website. Therefore, we cannot be responsible for the protection and privacy of any information which you provide whilst visiting such sites and such sites are not governed by this privacy statement. You should exercise caution and look at the privacy statement applicable to the website in question.
Controlling your personal information
</p>
<p style="margin:20px;">
You may choose to restrict the collection or use of your personal information in the following ways:
</p>
<ul style="margin:20px;">
      <li>whenever you are asked to fill in a form on the website, look for the box that you can click to indicate that you do not want the information to be used by anybody for direct marketing purposes
    </li>
    <li>
      if you have previously agreed to us using your personal information for direct marketing purposes, you may change your mind at any time by writing to or emailing us at myshopnshare@gmail.com
</li>
<li>
We will not sell, distribute or lease your personal information to third parties unless we have your permission or are required by law to do so. We may use your personal information to send you promotional information about third parties which we think you may find interesting if you tell us that you wish this to happen.
</li>
<li>
You may request details of personal information which we hold about you under the Data Protection Act 1998. A small fee will be payable. If you would like a copy of the information held on you please write to myshopnshare@gmail.com.
</li>
<li>
If you believe that any information we are holding on you is incorrect or incomplete, please write to or email us as soon as possible, at the above address. We will promptly correct any information found to be incorrect.   
</li>
</ul>
        </div></div> <%-- /content --%>

    </div> <%-- /page-in --%>
    </div> <%-- /page --%>
    
	<%@ include file="common/footer.jsp" %>		
</div>

	</body>
</html>