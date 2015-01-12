<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

		<link href="/css/shopnshare/main.css" rel="stylesheet" type="text/css" />
		<link href="/css/shopnshare/itempage.css" media="screen" rel="stylesheet"
			type="text/css" />

		<script type="text/javascript" src="/js/jquery-1.2.6.js"></script>
		<script src="/js/jquery.watermarkinput.js" type="text/javascript"></script>
				 
		<link rel="stylesheet" type="text/css" href="/css/shopnshare/fisheye.css" />	
		<script type="text/javascript" src="/js/jquery/interface/compressed/iutil.js"></script>
		<script type="text/javascript" src="/js/jquery/interface/compressed/fisheye.js"></script>	
		<script type="text/javascript" src="/js/jquery.cycle.all.min.js"></script>
		<script type="text/javascript" src="/js/jquery.overlay-0.14.js"></script>
		<script type="text/javascript" src="/js/jquery.media.js"></script>
		
     	<script type='text/javascript' src='js/boxy-0.1.4/jquery.boxy.js'></script>
      	<link rel="stylesheet" href="/js/boxy-0.1.4/boxy.css" type="text/css" />		
		
		<link rel="stylesheet" href="/js/prettygallery/css/prettyGallery.css" type="text/css" media="screen" charset="utf-8" />
		<script src="/js/prettygallery/jquery.prettyGallery.js" type="text/javascript" charset="utf-8"></script>

		<link rel="stylesheet" href="/js/prettygallery/css/prettyPhoto.css" type="text/css" media="screen" charset="utf-8" />
		<script src="/js/prettygallery/jquery.prettyPhoto.js" type="text/javascript" charset="utf-8"></script>

		<link rel="stylesheet" type="text/css" media="screen" href="/js/validation/jquery-validate/css/screen.css" />	
		<script src="/js/validation/jquery-validate/jquery.validate.js" type="text/javascript"></script>
		<script src="/js/validation/jquery-validate/lib/jquery.form.js" type="text/javascript"></script>
		
		<script type="text/javascript" src="/js/ui.core.js"></script>
		<script type="text/javascript" src="/js/ui.tabs.js"></script>
		<link type="text/css" href="/css/flora.tabs.css" rel="stylesheet" />
		
		
		<script type="text/javascript" src="/js/ajaxfileupload.js"></script>
		
		<script type="text/javascript"
			src="/js/jquery/interface/compressed/fisheye.js"></script>			
		<script type="text/javascript"
			src="/js/jquery/interface/compressed/iutil.js"></script>
	
		<script src="/js/shopnshare/fisheye.js" type="text/javascript"></script>		
		
		<link href="/css/thickbox.css" media="screen" rel="stylesheet" type="text/css" />		
		<script type="text/javascript" src="/js/thickbox-compressed.js"></script>		
		
		<title id="page-title"> MY SHOP N SHARE </title>	

		<style type="text/css">
			#center {
				float: left;
				width: 730px;
			}
		</style>
	</head>

	<body>	
			<div id="ct-wrap">
				<div id="ct">
					<div id="north"><%@ include file="../common/header.jsp"%></div>
					<div id="center"><%@ include file="cartPage.jsp"%></div>
					<%@ include file="../common/footer.jsp" %>
				</div>
			</div>

			<%-- There is a problem with one of these files.  Must be broken html.  Use Intellij to check --%>
			<div id="adspage" style="display: none;"><%@ include file="../ads/upload.jsp" %></div>
			<div id="settingspage" style="display: none;"><%@ include file="../settings/settings.jsp" %></div>
			<div id="orderspage" style="display: none;"><%@ include file="../orders/orders.jsp" %></div>
			<div id="cartpage" style="display: none;"><%@ include file="../cart/cartPage.jsp" %></div>
			<div id="bulkuploadpage" style="display: none;"><%@ include file="../users/bulkuploadform.jsp" %></div>
			<div id="useruploadpage" style="display: none;"><%@ include file="../users/singleItemUpload.jsp" %></div>
			<div id="bankpage" style="display: none;"><%@ include file="../common/bank.jsp" %></div>
			<div id="friendrequestpage" style="display: none;"><%@ include file="../users/friendRequest.jsp" %></div>

	</body>
</html>