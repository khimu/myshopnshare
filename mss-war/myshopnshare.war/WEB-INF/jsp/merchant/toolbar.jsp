<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<link href="/css/thickbox.css" media="screen" rel="stylesheet" type="text/css" />		
<script type="text/javascript" src="/js/thickbox-compressed.js"></script>

<%--
<decorator:usePage id="p" />
<%
			HttpServletRequest req = p.getRequest();
			StringBuffer printUrl = new StringBuffer();
			printUrl.append( req.getRequestURI() );
			printUrl.append("?printable=true");
			if (request.getQueryString()!=null) {
				printUrl.append('&');
				printUrl.append(request.getQueryString());
			}
%>
--%>

			
<div id="fisheye" class="fisheye">

	<div class="fisheyeContainter">
		
		<a href="/secure/merchant.do" class="fisheyeItem">
			<span>Home</span>
			<img src="/images/home.png" width="20" />
		</a>

		<a href="/secure/ads.do?KeepThis=true&TB_iframe=true&height=500&width=700&model=true" class="thickbox fisheyeItem">
			<span>Ads Upload</span>
			<img src="/images/email.png" width="20" />
		</a>

		<a href="#TB_inline?height=510&width=400&inlineId=bulkuploadpage&model=true" class="thickbox fisheyeItem">
			<span>Bulk Upload</span>
			<img src="/images/email.png" width="20" />
		</a>
		
		<a href="/secure/account.do?KeepThis=true&TB_iframe=true&height=400&width=540" class="thickbox fisheyeItem">
			<span>Account</span>
			<img src="/images/email.png" width="20" />
		</a> 

		<a href="/secure/search.do?KeepThis=true&TB_iframe=true&height=400&width=900" class="thickbox fisheyeItem">
			<span>Search</span>
			<img src="/images/email.png" width="20" />
		</a>				
		<a href="#TB_inline?height=310&width=400&inlineId=adspage&model=true" class="thickbox fisheyeItem">
			<span>Printer</span>
			<img src="/images/email.png" width="20" />
		</a>		

	</div>
</div>