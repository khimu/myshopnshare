<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%--
<applet
    code="PhotoUploadApplet"
    codebase="applet/com/myshopnshare/core/applet/"
    archive="axis.jar,commons-discovery.jar,commons-logging.jar,jaxrpc.jar,JTwain.jar,saaj.jar,servlet-api.jar,wsdl4j.jar"
    width="100%" height="100%"
    mayscript="true">

    <param name=caseId value="<%=request.getParameter("caseId")%>" />
    <param name="endPoint" value="<%=request.getParameter("endPoint")%>" />

</applet>
--%>


<html>
<body>
<jsp:plugin type="applet"
	code="javaatwork.myuploader.UploadApplet.class"
	archive="myuploader-free-signed-1.12.1.jar, labels.jar"
	codebase="http://localhost:8080/myshopnshare/applet"
	width="400" 
	height="300">
  <jsp:params>
   <jsp:param name="uploadURL" value="uploader.jsp"/>
   <jsp:param name="successURL" value="success.html"/>
   <jsp:param name="codebase_lookup" value="false"/>
  </jsp:params>

 <jsp:fallback>
    <br/>
     This browser does not support Java applets.
  </jsp:fallback>
</jsp:plugin>

</body>
</html>

     