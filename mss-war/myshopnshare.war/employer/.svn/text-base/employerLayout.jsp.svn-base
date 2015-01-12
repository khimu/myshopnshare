<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

		<link href="/css/shopnshare/main.css" rel="stylesheet" type="text/css" />
		<link href="/css/shopnshare/style.css" media="screen" rel="stylesheet"
			type="text/css" />

		<script type="text/javascript" src="/js/jquery-1.2.6.js"></script>
		<script src="/js/jquery.watermarkinput.js" type="text/javascript"></script>
				 
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
			
     	<script type='text/javascript' src='js/boxy-0.1.4/jquery.boxy.js'></script>
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
		<link rel="stylesheet" media="screen" type="text/css" href="/css/ui.datepicker.css" />
		<script type="text/javascript" src="/js/ui.tabs.js"></script>
		<script type="text/javascript" src="/js/ui.datepicker.js"></script>
		
		<%@ include file="../common/common.jsp" %>
				
		<title id="page-title"> MY SHOP N SHARE </title>	
		
		<script type="text/javascript">
		$( function() {
			$("a[rel^='prettyPhoto']").prettyPhoto();		 	
		});
		</script>
		<style type="text/css">
			.gallery {
				list-style-type: none;
				list-style-image: none;
				list-style-position: outside;		
			}  

		</style>
		<script type="text/javascript">
			var escapeHTML = function(description) {
				return description.replace(/&/g,'&amp;').replace(/</g,'&lt;').replace(/>/g,'&gt;');
			}
			
			var unescapeHTML = function(description) {
			 	return description.replace(/&amp;/g,'&').replace(/&lt;/g,'<').replace(/&gt;/g,'>');
			}			
		</script>
	</head>

<c:choose>
	<c:when test="${not empty person.customize.backgroundImage}">
		<body style="background-image: url(${person.customize.backgroundImage}) no-repeat;">	
	</c:when>
	<c:when test="${not empty person.customize.backgroundColor}">
		<body style="background-color: #${person.customize.backgroundColor}">	
	</c:when>	
	<c:otherwise>
		<body>	
	</c:otherwise>
</c:choose>
			<div id="ct-wrap" style="text-align: center;">
				<div id="ct">
					<div id="north"><%@ include file="../common/header.jsp"%></div>
					<div id="west"><%@ include file="right.jsp" %></div>
					<div id="center"><%@ include file="page.jsp"%>
					</div>
					<div id="center-2" style="display:none;"></div>
					<div style="vertical-align:bottom;padding:10px;text-align: center;">
						<%@ include file="../friends/loadfriends.jsp" %>
					</div>
					<%@ include file="../common/footer.jsp" %>
				</div>
			</div>
			
			<%-- POPUP PAGES --%>
			<%-- There is a problem with one of these files.  Must be broken html.  Use Intellij to check --%>
			<div id="adspage" style="display: none;"><%@ include file="../ads/upload.jsp" %></div>
			<div id="settingspage" style="display: none;"><%@ include file="../settings/settings.jsp" %></div>
			<div id="bankpage" style="display: none;"><%@ include file="../common/bank.jsp" %></div>
			<div id="friendrequestpage" style="display: none;"><%@ include file="../users/friendRequest.jsp" %></div>


	</body>
</html>