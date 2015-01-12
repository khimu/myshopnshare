<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="keywords" content="rewards points, rewards, business, self promoting, meeting, joining, friends, connecting, linking, union, unite, united, bonding, social network, journals, scribbles, journal, scribble, social events, social calendars, networking, social shopping network, shop, marketing, advertising, advertise, promoting, selling" />
		<meta name="description" content="A social shopping network that allows business to grow through recommendations from the trusted words of friends and family." />
		
		<link href="/css/shopnshare/login.css" media="screen" rel="stylesheet" type="text/css" />
		<link href="/css/shopnshare/style.css" rel="stylesheet" type="text/css" />

		<script type="text/javascript" src="/js/jquery-1.2.6.js"></script>
		

		<style type="text/css">@import "/css/jquery.datepick.css";</style> 
		<script type="text/javascript" src="/js/jquery.datepick.js"></script>
		
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
			
		<title id="page-title"> MY SHOP N SHARE </title>	

		<script type="text/javascript" src="/js/overlay/jqueryfancyzoom/jquery.shadow.js"></script>
		<script type="text/javascript" src="/js/overlay/jqueryfancyzoom/jquery.ifixpng.js"></script>
		<script type="text/javascript" src="/js/overlay/jqueryfancyzoom/jquery.fancyzoom.min.js"></script>

		<%@ include file="../common/common.jsp" %>
				
		
  <link rel="stylesheet" media="screen,projection" type="text/css" href="/css/crystalx/main.css" />
   <link rel="stylesheet" media="print" type="text/css" href="/css/crystalx/print.css" />
   <link rel="stylesheet" media="aural" type="text/css" href="/css/crystalx/aural.css" />	
	</head>

	<body>	
<div id="main" class="box">

	<div id="main-header">
	<%@ include file="header.jsp" %>

     <%-- Main menu (tabs) --%>
     <div id="tabs" class="noprint">

            <h3 class="noscreen">Navigation</h3>
            <ul class="box">
            	<li id="active"><a href="/public/publicPage.do">Login<span class="tab-l"></span><span class="tab-r"></span></a></li>            
                <li><a href="/register.do">Sign Up<span class="tab-l"></span><span class="tab-r"></span></a></li>
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
        <div id="center-2" style="display: none;height: 100%;">
        </div>        
        <div id="center-3" style="display: none;">
        	<%@ include file="all.jsp" %>
        </div>
        <div id="center">         
        <%@ include file="page.jsp" %>
        </div>
       </div> <%-- /content --%>

    </div> <%-- /page-in --%>
    </div> <%-- /page --%>
    
	<%@ include file="../common/footer.jsp" %>	
	
		<div class="clear"></div>
		<div>					
			<script type="text/javascript"><!--
			google_ad_client = "pub-2178827339426285";
			/* 728x90, created 4/3/09 */
			google_ad_slot = "5531437381";
			google_ad_width = 728;
			google_ad_height = 90;
			//-->
			</script>
			<script type="text/javascript"
			src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
			</script>	
		</div>		
</div>

	</body>
</html>