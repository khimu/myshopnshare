<%@ page
	import="org.springframework.security.ui.webapp.AuthenticationProcessingFilter"%>
<%@ page
	import="org.springframework.security.ui.AbstractProcessingFilter"%>
<%@ page import="org.springframework.security.AuthenticationException"%>


<h1>Access Denied</h1>
<p>Anyone can view this page.</p>
<p>If you're logged in, you can <a href="listAccounts.html">list
accounts</a>.</p>
<p>Your principal object is....: <%= request.getUserPrincipal() %></p>


You do not have permission
