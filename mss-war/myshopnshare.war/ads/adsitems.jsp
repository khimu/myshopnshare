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
		<script type="text/javascript" src="/js/ui.core.js"></script>
		<script type="text/javascript" src="/js/ui.datepicker.js"></script>
		<link rel="stylesheet" media="screen" type="text/css" href="/css/ui.datepicker.css" />
		<script type="text/javascript" src="/js/jquery.cycle.all.min.js"></script>

		<script src="/js/jquery.watermarkinput.js" type="text/javascript"></script>

		<link rel="stylesheet" type="text/css" media="screen" href="/js/validation/jquery-validate/css/screen.css" />	
		<script src="/js/validation/jquery-validate/jquery.validate.js" type="text/javascript"></script>
		<script src="/js/validation/jquery-validate/lib/jquery.form.js" type="text/javascript"></script>
		
     	<script type='text/javascript' src='/js/boxy-0.1.4/jquery.boxy.js'></script>
     	
     	<script type="text/javascript" src="/js/ajaxfileupload.js"></script>

		<link rel="stylesheet" href="/js/prettygallery/css/prettyGallery.css" type="text/css" media="screen" charset="utf-8" />
		<script src="/js/prettygallery/jquery.prettyGallery.js" type="text/javascript" charset="utf-8"></script>

		<link rel="stylesheet" href="/js/prettygallery/css/prettyPhoto.css" type="text/css" media="screen" charset="utf-8" />
		<script src="/js/prettygallery/jquery.prettyPhoto.js" type="text/javascript" charset="utf-8"></script>     	
     	
      	<link rel="stylesheet" href="/js/boxy-0.1.4/boxy.css" type="text/css" />

		
		<script type="text/javascript"
			src="/js/jquery/interface/compressed/fisheye.js"></script>			
		<script type="text/javascript"
			src="/js/jquery/interface/compressed/iutil.js"></script>
	
			  <link rel="stylesheet" media="screen,projection" type="text/css" href="/css/shopnshare/crystalx/main.css" />
		   <link rel="stylesheet" media="print" type="text/css" href="/css/crystalx/print.css" />
		   <link rel="stylesheet" media="aural" type="text/css" href="/css/crystalx/aural.css" />	
	

		<title id="page-title"> MY SHOP N SHARE </title>		
		
		<script type="text/javascript">
			var escapeHTML = function(description) {
				return description.replace(/&/g,'&amp;').replace(/</g,'&lt;').replace(/>/g,'&gt;');
			}
			
			var unescapeHTML = function(description) {
			 	return description.replace(/&amp;/g,'&').replace(/&lt;/g,'<').replace(/&gt;/g,'>');
			}
		
			function adsRedirect(recordId){
				$.ajax( {
					type :"POST",
					url :"/secure/ads.do",
					data : {method: 'click', recordId: recordId},
					dataType :'json',
					success : function(ads) {	
						window.location = ads.sponsorSite;		
					}
				});
			}
		
			function getAds(method, category){
				 $.ajax({  
				       type: "post",  
				       url: "/secure/ads.do",
				       data: {method: 'get'},
				       dataType: 'json', 
				       success: function(json){  
							$.each(json.adss, function(i,ads){
								/** append works but for some reason its now displaying the image with html description.  description cannot accept large html  **/
								$('#new-ads-uploaded').append('<a rel="prettyPhoto[gallery]" href="' + ads.thumbnail + '" title="Description:' + ads.description + '" alt="Sponsor Site"><img src="/' + ads.icon + '" title="Description: ' + ads.description + '" /></a>');
					         }); 
					         
							$("a[rel^='prettyPhoto']").prettyPhoto();
				       } 
				     });
			}
		
			$(document).ready(function(){		
				/** ALL ITEMS **/
				getAds('', '');
		
				$('#upload-new-ads').click(function(){
					new Boxy('#ads-box', {title: 'Upload Ads', modal: true});
				});
			});	
		</script>
				
	</head>
	<body>	

		<div id="all-products" style="position: relative;clear:both;">
			<div style="float: left;width:550px; height: 80px;padding: 5px;margin: 5px;">
				<div id="new-ads-uploaded">
				</div>		
			</div>
		</div>
	
		<a href="#" id="upload-new-ads">Upload New Ads</a>
	
		<div id="user-upload-box" style="display: none;">
			<%@ include file="../ads/upload.jsp" %>
		</div>

	</body>
</html>