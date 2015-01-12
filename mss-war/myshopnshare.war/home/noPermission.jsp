<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

		<link href="/css/shopnshare/main.css" rel="stylesheet" type="text/css" />
		<link href="/css/shopnshare/style.css" media="screen" rel="stylesheet"
			type="text/css" />

		<script type="text/javascript" src="/js/shopnshare/FormCheck.js"></script>

		<script type="text/javascript" src="/js/jquery-1.2.6.js"></script>
		<script src="/js/jquery.watermarkinput.js" type="text/javascript"></script>
				 
		<%@ include file="../common/common.jsp" %>
		
				 
		<link rel="stylesheet" type="text/css" href="/css/shopnshare/fisheye.css" />	
		<script type="text/javascript" src="/js/jquery/interface/compressed/iutil.js"></script>
		<script type="text/javascript" src="/js/jquery/interface/compressed/fisheye.js"></script>	
		<script type="text/javascript" src="/js/jquery.cycle.all.min.js"></script>
		<script type="text/javascript" src="/js/jquery.overlay-0.14.js"></script>
		<script type="text/javascript" src="/js/jquery.media.js"></script>
		
		<%-- DO NOT USE THIS.  NOT COMPATIBLE WITH UI.TABS --%>
		<%--
		<script src="/js/multifileupload/jquery.MetaData.js" type="text/javascript" language="javascript"></script>
		<script src="/js/multifileupload/jquery.MultiFile.js" type="text/javascript" language="javascript"></script>
		<script src="/js/multifileupload/jquery.blockUI.js" type="text/javascript" language="javascript"></script>
		--%>
			
     	<script type='text/javascript' src='/js/boxy-0.1.4/jquery.boxy.js'></script>
      	<link rel="stylesheet" href="/js/boxy-0.1.4/boxy.css" type="text/css" />		
		
		<link rel="stylesheet" href="/js/prettygallery/css/prettyGallery.css" type="text/css" media="screen" charset="utf-8" />
		<script src="/js/prettygallery/jquery.prettyGallery.js" type="text/javascript" charset="utf-8"></script>

		<link rel="stylesheet" href="/js/prettygallery/css/prettyPhoto.css" type="text/css" media="screen" charset="utf-8" />
		<script src="/js/prettygallery/jquery.prettyPhoto.js" type="text/javascript" charset="utf-8"></script>

		<link rel="stylesheet" type="text/css" media="screen" href="/js/validation/jquery-validate/css/screen.css" />	
		<script src="/js/validation/jquery-validate/jquery.validate.js" type="text/javascript"></script>
		<script src="/js/validation/jquery-validate/lib/jquery.form.js" type="text/javascript"></script>		

				
		<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
		
		<script type="text/javascript"
			src="/js/jquery/interface/compressed/fisheye.js"></script>			
		<script type="text/javascript"
			src="/js/jquery/interface/compressed/iutil.js"></script>
	
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
				
		<title id="page-title"> MY SHOP N SHARE </title>	
		
		<script type="text/javascript">
		$( function() {
			$("a[rel^='prettyPhoto']").prettyPhoto();		 	
		});
		</script>
	</head>

	<body>

<div id="main" class="box">

	<%@ include file="../common/header.jsp"%>

     <%-- Main menu (tabs) --%>
     <div id="tabs" class="noprint">

            <h3 class="noscreen">Navigation</h3>
		<%@ include file="../common/links.jsp" %>

        <hr class="noscreen" />
     </div> <%-- /tabs --%>

    <div id="page" class="box">
    <div id="page-in" class="box">

		<div style="position:relative;">
	        <div id="strip" class="box noprint" style="float:left;">
	            <%-- Breadcrumbs --%>
	            <p id="breadcrumbs">You are here: <a href="#">Home</a> &gt; <a href="#">Main</a> &gt;  <strong></strong></p>
	            <hr class="noscreen" />
	            
	        </div> <%-- /strip --%>
	
			<div id="latest" style="float:right;width:600px;height: 10px; overflow:hidden;">
				<span>${person.fullName} needs ${headline} </span>
			</div>	
		</div>
		
		<div style="position: relative;clear:both;"><hr style="float: left;" width="100%"/></div>
		
		<%@ include file="../common/left.jsp" %>	
        
        <div id="content">	
			<div id="center">
			<div class="errors">You do not have permission to view this person's page</div>
			<%@ include file="../search/potentialfriends/searchFriendResults.jsp" %>
			</div>
			<%-- Shows my items page --%>
			<div id="center-2"></div>
	        <div id="center-3" style="display: none;">
	        	<%@ include file="../public/all.jsp" %>
	        </div>
		</div>

    </div>
    </div>

	<div id="south"><hr/><jsp:include page="../common/footer.jsp"/></div>		
	<div class="google-ads">
	<h4>Google Ads</h4>
		<script type="text/javascript"><%--
		google_ad_client = "pub-2178827339426285";
		/* 728x90, created 4/3/09 */
		google_ad_slot = "5531437381";
		google_ad_width = 900;
		google_ad_height = 200;
		//--%>
		</script>
		<script type="text/javascript"
		src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
		</script>
	</div>    
</div>		
	<%-- POPUP PAGES --%>
	<%-- There is a problem with one of these files.  Must be broken html.  Use Intellij to check --%>
	<div id="adspage" style="display: none;"><%@ include file="../ads/upload.jsp" %></div>
	<div id="settingspage" style="display: none;"><%@ include file="../settings/settings.jsp" %></div>
	<div id="bankpage" style="display: none;"><%@ include file="../common/bank.jsp" %></div>
	<div id="friendrequestpage" style="display: none;"><%@ include file="../users/friendRequest.jsp" %></div>
	<div id="events-form-popup2" style="display:none;"><%@ include file="../events/form.jsp" %></div>
	<%@ include file="../scribble/scribble.jsp" %>
	<%@ include file="../journal/searchJournalResult.jsp" %>
	</body>
</html>