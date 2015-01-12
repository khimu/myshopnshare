<%@ include file="/WEB-INF/jsp/includes.jsp"%>

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

<div id="toplinks">
	<div id="fisheye" class="fisheye">
		<div class="fisheyeContainter">
			
			<a href="/secure/home.do" class="fisheyeItem">
				<span>Home</span>
				<img src="/images/home.png" width="30" />
			</a>
<%--
			<a href="#TB_inline?height=310&width=400&inlineId=useruploadpage&model=true" class="thickbox fisheyeItem">
				<span>Upload</span>
				<img src="/images/email.png" width="30" />
			</a>
		
			<a href="#TB_inline?height=310&width=500&inlineId=bulkuploadpage&model=true" class="thickbox fisheyeItem">
				<span>Bulk Upload</span>
				<img src="/images/up.png" width="30" />
			</a>
	--%>		
			<a href="/secure/profile.do?KeepThis=true&TB_iframe=true&height=400&width=540" class="thickbox fisheyeItem">
				<span>Profile</span>
				<img src="/images/user.png" width="30" />
			</a>
			<a href="#TB_inline?height=310&width=500&inlineId=adspage&model=true" class="thickbox fisheyeItem">
				<span>Chat</span>
				<img src="/images/chat.png" width="30" />
			</a>
			<a href="/secure/possessions.do?KeepThis=true&TB_iframe=true&height=550&width=710" class="thickbox fisheyeItem">
				<span>Posessions</span>
				<img src="/images/up.png" width="30" />
			</a> 
			<a href="/secure/search.do?KeepThis=true&TB_iframe=true&height=500&width=900" class="thickbox fisheyeItem">
				<span>Search</span>
				<img src="/images/zoom.png" width="30" />
			</a>				
			<a href="/secure/account.do?KeepThis=true&TB_iframe=true&height=500&width=540" class="thickbox fisheyeItem">
				<span>Account</span>
				<img src="/images/info.png" width="30" />
			</a> 
			<a href="#TB_inline?height=500&width=600&inlineId=settingspage&model=true" class="thickbox fisheyeItem">
				<span>Settings</span>
				<img src="/images/configure.png" width="30" />
			</a> 		
			<a href="#TB_inline?height=510&width=600&inlineId=orderspage&model=true" class="thickbox fisheyeItem">
				<span>Orders</span>
				<img src="/images/calculator.png" width="30" />
			</a>
			<a href="#TB_inline?height=510&width=600&inlineId=cartpage&model=true" class="thickbox fisheyeItem">
				<span>Cart</span>
				<img src="/images/shopping_cart.png" width="30" />
			</a>
			<a href="#TB_inline?height=510&width=600&inlineId=cartpage&model=true" class="thickbox fisheyeItem">
				<span>Journal</span>
				<img src="/images/notepad.png" width="30" />
			</a>			
			<a href="#TB_inline?height=100&width=100&inlineId=bankpage&model=true" class="thickbox fisheyeItem">
				<span>Bank</span>
				<img src="/images/finance.png" width="30" />
			</a>
			<a href="/secure/ads.do?KeepThis=true&TB_iframe=true&height=500&width=700&model=true" class="thickbox fisheyeItem">
				<span>Ads Upload</span>
				<img src="/images/email.png" width="20" />
			</a>
			
			<a href="#TB_inline?height=510&width=400&inlineId=adspage&model=true" class="thickbox fisheyeItem">
				<span>Printer</span>
				<img src="/images/print.png" width="30" />
			</a>		
		</div>
	</div>
</div>