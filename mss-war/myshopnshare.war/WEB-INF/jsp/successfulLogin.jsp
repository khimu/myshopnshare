<%@ page
	import="org.springframework.security.ui.webapp.AuthenticationProcessingFilter"%>
<%@ page
	import="org.springframework.security.ui.AbstractProcessingFilter"%>
<%@ page import="org.springframework.security.AuthenticationException"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<h1>Secure Page</h1>
<p>This is a protected page. You can get to me if you've been
remembered, or if you've authenticated this session.</p>

<sec:authorize ifAllGranted="ROLE_ADMIN">
	You are a supervisor! You can therefore see the <a
		href="extreme/index.jsp">extremely secure page</a>.<br />
	<br />
</sec:authorize>

<h3>Properties obtained using <sec:authentication
	property="principal.username" /> tag</h3>
<table border="1">
	<tr>
		<th>Tag</th>
		<th>Value</th>
	</tr>
	<tr>
		<td><sec:authentication property='name' /></td>
		<td><sec:authentication property="name" /></td>
	</tr>
	<tr>
		<td><sec:authentication property='principal.username' /></td>
		<td><sec:authentication property="principal.username" /></td>
	</tr>
	<tr>
		<td><sec:authentication property='principal.enabled' /></td>
		<td><sec:authentication property="principal.enabled" /></td>
	</tr>
	<tr>
		<td><sec:authentication property='principal.accountNonLocked' /></td>
		<td><sec:authentication property="principal.accountNonLocked" /></td>
	</tr>
</table>


<p><a href="../">Home</a>
<p><a href="j_spring_security_logout">Logout</a>