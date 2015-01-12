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
                 <li id="active"><a href="/mainPage.do?method=terms">Terms Of Use<span class="tab-l"></span><span class="tab-r"></span></a></li>
                <li><a href="/mainPage.do?method=privacy">Privacy<span class="tab-l"></span><span class="tab-r"></span></a></li>

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
		Welcome to our website. If you continue to browse and use this website you are agreeing to comply with and be bound by the following terms and conditions of use, which together with our privacy policy govern MY SHOP N SHARE's relationship with you in relation to this website.
		</p>
		<p style="margin:20px;">
		The term MY SHOP N SHARE or 'us' or 'we' refers to the owner of the website whose registered office is http://www.myshopnshare.com. Our company registration number is currently unavailable. The term 'you' refers to the user or viewer of our website.
		</p>

<div style="margin:20px;">The use of this website is subject to the following terms of use:</div>

<ul style="margin:20px;">
    <li> The content of the pages of this website is for your general information and use only. It is subject to change without notice.</li>
    <li> Neither we nor any third parties provide any warranty or guarantee as to the accuracy, timeliness, performance, completeness or suitability of the information and materials found or offered on this website for any particular purpose. You acknowledge that such information and materials may contain inaccuracies or errors and we expressly exclude liability for any such inaccuracies or errors to the fullest extent permitted by law.</li>
    <li> Your use of any information or materials on this website is entirely at your own risk, for which we shall not be liable. It shall be your own responsibility to ensure that any products, services or information available through this website meet your specific requirements.</li>
   <li>  This website contains material which is owned by or licensed to us. This material includes, but is not limited to, the design, layout, look, appearance and graphics. Reproduction is prohibited other than in accordance with the copyright notice, which forms part of these terms and conditions.</li>
    <li> All trade marks reproduced in this website which are not the property of, or licensed to, the operator are acknowledged on the website.</li>
    <li> Unauthorised use of this website may give rise to a claim for damages and/or be a criminal offence.</li>
    <li> From time to time this website may also include links to other websites. These links are provided for your convenience to provide further information. They do not signify that we endorse the website(s). We have no responsibility for the content of the linked website(s).</li>
    <li> You may not create a link to this website from another website or document without [business name]'s prior written consent.</li>
   <li>  Your use of this website and any dispute arising out of such use of the website is subject to the laws of England, Scotland and Wales.  </li>
  </ul>    

        </div></div> <%-- /content --%>

    </div> <%-- /page-in --%>
    </div> <%-- /page --%>
    
	<%@ include file="common/footer.jsp" %>		
</div>

	</body>
</html>