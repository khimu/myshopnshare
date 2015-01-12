<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="/css/shopnshare/main.css" rel="stylesheet" type="text/css" />
		<link href="/css/shopnshare/none.css" media="screen" rel="stylesheet" type="text/css" />
	
		<script type="text/javascript" src="/js/jquery-1.2.6.js"></script>
		<script src="/js/jquery.watermarkinput.js" type="text/javascript"></script>
	
		<link rel="stylesheet" href="/js/prettygallery/css/prettyPhoto.css" type="text/css" media="screen" charset="utf-8" />
		<script src="/js/prettygallery/jquery.prettyPhoto.js" type="text/javascript" charset="utf-8"></script>

	<link rel="stylesheet" type="text/css" media="screen" href="/js/validation/jquery-validate/css/screen.css" />	
	<script src="/js/validation/jquery-validate/jquery.validate.js" type="text/javascript"></script>
	<script src="/js/validation/jquery-validate/lib/jquery.form.js" type="text/javascript"></script>

		<link rel="stylesheet" type="text/css" href="/css/shopnshare/fisheye.css" />	
			
		<script type="text/javascript"
			src="/js/jquery/interface/compressed/fisheye.js"></script>			
		<script type="text/javascript"
			src="/js/jquery/interface/compressed/iutil.js"></script>

     	<script type='text/javascript' src='/js/boxy-0.1.4/jquery.boxy.js'></script>
      	<link rel="stylesheet" href="/js/boxy-0.1.4/boxy.css" type="text/css" />			
	
		<script src="/js/shopnshare/fisheye.js" type="text/javascript"></script>

		<link href="/css/thickbox.css" media="screen" rel="stylesheet" type="text/css" />		
		<script type="text/javascript" src="/js/thickbox-compressed.js"></script>		
	
		
		<title id="page-title"> MY SHOP N SHARE </title>	
		
		<style type="text/css">
			#center {	
				width: 900px;
				height: 500px;				
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
	<body>	
		<div id="center"><%@ include file="searchItemResult.jsp"%></div>
	</body>
</html>