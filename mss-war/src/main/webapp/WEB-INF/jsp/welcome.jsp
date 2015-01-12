<%@ include file="/WEB-INF/jsp/include2.jsp"%>
<html>
<c:set var="appname" scope="page"
	value="${pageContext.servletContext.servletContextName}" />
<head>
<title>Welcome</title>
</head>
<body>
<h1>Welcome to the ${appname} Application (hosted at )</h1>
</body>
</html>
