<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="/css/shopnshare/main.css" rel="stylesheet" type="text/css" />
		<link href="/css/shopnshare/style.css" media="screen" rel="stylesheet"
			type="text/css" />
	
		<script type="text/javascript" src="/js/jquery-1.2.6.js"></script>
		<script type="text/javascript" src="/js/ui.core.js"></script>
		<script type="text/javascript" src="/js/ui.datepicker.js"></script>
		
		<link rel="stylesheet" type="text/css" media="screen" href="/js/validation/jquery-validate/css/screen.css" />	
		<script src="/js/validation/jquery-validate/jquery.validate.js" type="text/javascript"></script>
		<script src="/js/validation/jquery-validate/lib/jquery.form.js" type="text/javascript"></script>
     	<script type='text/javascript' src='js/boxy-0.1.4/jquery.boxy.js'></script>
      	<link rel="stylesheet" href="/js/boxy-0.1.4/boxy.css" type="text/css" />

		<link rel="stylesheet" type="text/css"
							href="/css/shopnshare/fisheye.css" />	
			
		<script type="text/javascript"
			src="/js/jquery/interface/compressed/fisheye.js"></script>			
		<script type="text/javascript"
			src="/js/jquery/interface/compressed/iutil.js"></script>
	
		<script src="/js/shopnshare/fisheye.js" type="text/javascript"></script>

		<title id="page-title"> mySHOPnSHARE </title>	
		
		<style type="text/css">
			#center {
				width: 940px;
				height: 400px;
				background-color: #FFFFF0;
			}
		</style>
	</head>

	<body>	
			<div id="ct-wrap">
				<div id="ct">
					<div id="north"><%@ include file="../common/ajax/header.jsp"%></div>
					<div id="center"><%@ include file="partner.jsp"%></div>
					<div id="south-panel">
						<div id="toolbar"><%@ include file="../common/partnertoolbar.jsp"%></div>
						<div id="south">
							<div id="footer">
								<span id="copyright"><b>mySHOPnSHARE</b> Copyright &copy; 2009  &nbsp;&nbsp;[ All Rights Reserved ] </span>		
								<a href="#">site map</a>
								<a href="#">contact us</a>
								<a href="#">Terms of Use</a>
								<a href="#">Privacy Policy</a>
							</div>	
						</div>
					</div>
				</div>
			</div>
	</body>
</html>