<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<title> MY SHOP N SHARE </title>	
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="keywords" content="rewards points, rewards, business, self promoting, meeting, joining, friends, connecting, linking, union, unite, united, bonding, social network, journals, scribbles, journal, advice, recommendation, recommend, wish list, scribble, social events, social calendars, networking, social shopping network, shop, marketing, advertising, advertise, promoting, selling" />
        <meta name="keywords" content="A social shopping network that allows members to sell services and items and grow their business through recommendations from previous clients/customers.  Allows users to get advice, recommend items, show wish list, and bought items." />

		<link href="/css/shopnshare/main.css" rel="stylesheet" type="text/css" />
		<link href="/css/shopnshare/style.css" media="screen" rel="stylesheet"
			type="text/css" />

		<script type="text/javascript" src="/js/shopnshare/FormCheck.js"></script>

		<script type="text/javascript" src="/js/jquery-1.2.6.js"></script>
		<script src="/js/jquery.watermarkinput.js" type="text/javascript"></script>		
				 
		<link rel="stylesheet" type="text/css" href="/css/shopnshare/fisheye.css" />	
		<script type="text/javascript" src="/js/jquery/interface/compressed/iutil.js"></script>
		<script type="text/javascript" src="/js/jquery/interface/compressed/fisheye.js"></script>	
		<script type="text/javascript" src="/js/jquery.cycle.all.min.js"></script>
		
		<script type="text/javascript" src="/js/jquery.media.js"></script>
		
		<%-- DO NOT USE THIS.  NOT COMPATIBLE WITH UI.TABS --%>
		<%--
		<script src="/js/multifileupload/jquery.MetaData.js" type="text/javascript" language="javascript"></script>
		<script src="/js/multifileupload/jquery.MultiFile.js" type="text/javascript" language="javascript"></script>
		<script src="/js/multifileupload/jquery.blockUI.js" type="text/javascript" language="javascript"></script>
		--%>
			
     	<script type='text/javascript' src='/js/boxy-0.1.4/jquery.boxy.js'></script>
      	<link rel="stylesheet" href="/js/boxy-0.1.4/boxy.css" type="text/css" />		
		
		<%--
		<link rel="stylesheet" href="/js/prettygallery/css/prettyGallery.css" type="text/css" media="screen" charset="utf-8" />
		<script src="/js/prettygallery/jquery.prettyGallery.js" type="text/javascript" charset="utf-8"></script>
		--%>
		
		<link rel="stylesheet" href="/js/prettygallery/css/prettyPhoto.css" type="text/css" media="screen" charset="utf-8" />
		<script src="/js/prettygallery/jquery.prettyPhoto.js" type="text/javascript" charset="utf-8"></script>

		<link rel="stylesheet" type="text/css" media="screen" href="/js/validation/jquery-validate/css/screen.css" />	
		<script src="/js/validation/jquery-validate/jquery.validate.js" type="text/javascript"></script>
		<script src="/js/validation/jquery-validate/lib/jquery.form.js" type="text/javascript"></script>		

		<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
		
		<script type="text/javascript" src="/js/jquery/interface/compressed/fisheye.js"></script>			
		<script type="text/javascript" src="/js/jquery/interface/compressed/iutil.js"></script>
	
		<script src="/js/shopnshare/fisheye.js" type="text/javascript"></script>		
		
		<link href="/css/thickbox.css" media="screen" rel="stylesheet" type="text/css" />		
		<script type="text/javascript" src="/js/thickbox-compressed.js"></script>		

		<script type="text/javascript" src="/js/ui.core.js"></script>
		<link type="text/css" href="/css/flora.tabs.css" rel="stylesheet" />
		<script type="text/javascript" src="/js/ui.tabs.js"></script>
		
		<style type="text/css">@import "/css/jquery.datepick.css";</style> 
		<script type="text/javascript" src="/js/jquery.datepick.js"></script>

		  <link rel="stylesheet" media="screen,projection" type="text/css" href="/css/shopnshare/crystalx/main.css" />
		   <link rel="stylesheet" media="print" type="text/css" href="/css/crystalx/print.css" />
		   <link rel="stylesheet" media="aural" type="text/css" href="/css/crystalx/aural.css" />			
		
	<script type="text/javascript" src="/js/jquery.overlay-0.14.js" />
		
	<script type="text/javascript">
		$( function() {
			$("a[rel^='prettyPhoto']").prettyPhoto();		 	
		});
	</script>

		<%@ include file="../common/common.jsp" %>

	</head>

	<body>
	
	<%-- POPUP PAGES --%>
	<%-- There is a problem with one of these files.  Must be broken html.  Use Intellij to check --%>
	<div id="friendrequestpage" style="display:none;"><%@ include file="../users/friendRequest.jsp" %></div>	

<div id="main" class="box">

	<div id="main-header">
	<%@ include file="../common/header.jsp"%>

     <%-- Main menu (tabs) --%>
     <div id="tabs" class="noprint">

            <h3 class="noscreen">Navigation</h3>
		<%@ include file="../common/links.jsp" %>

        <hr class="noscreen" />
     </div> <%-- /tabs --%>
     </div>

    <div id="page" class="box">
    <div id="page-in" class="box">

		<div style="position:relative;">
				<div id="latest" style="float:left;width:600px;height: 50px; overflow:hidden;">
					<span>${person.fullName} says ${headline} </span>
				</div>	        	            
	            <div style="float:right;">
					<%@ include file="../common/headline.jsp"%>
					<span id="headline-design-search-span">Update Headline</span>	            
				</div>
		</div>
		
		<div class="clear"></div>
		
		<%@ include file="../common/left.jsp" %>	
        
        <div id="content">	
			<div id="center"><%@ include file="homePage.jsp"%></div>
			<%-- Shows my items page --%>
			<div id="center-2" style="display:none;"></div>
	        <div id="center-3" style="display: none;">
	        	<%@ include file="all.jsp" %>
	        </div>
		</div>

    </div>
    </div>
    
	<div id="south"><hr/><jsp:include page="../common/footer.jsp"/></div>		
	<div class="google-ads">
	<h4>Google Ads</h4>
		<script type="text/javascript">
		<!--
		google_ad_client = "pub-2178827339426285";
		/* 728x90, created 4/3/09 */
		google_ad_slot = "5531437381";
		google_ad_width = 900;
		google_ad_height = 200;
		//-->
		</script>
		<script type="text/javascript"
		src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
		</script>
	</div>    
</div>		

	</body>
</html>