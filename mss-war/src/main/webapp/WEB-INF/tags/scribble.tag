<%@ tag isELIgnored="false" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ attribute name="message" required="true" rtexprvalue="true" %><%@ attribute name="thumbnail" required="true" rtexprvalue="true" %><%@ attribute name="fullName" required="true" rtexprvalue="true" %><%@ attribute name="enteredDate" required="true" rtexprvalue="true" %><li id="scribble-entry"><div class="posting-header"><div style="float: left;"> ${fullName} Scribbled : </div><div style="float: right;">&nbsp;&nbsp; ${enteredDate}</div></div> <div style="clear: both;position: relative;"><div style="float:left;width:70px;"><img src="../${thumbnail}" title="${message}" /></div><div style="float: left;padding: 0 5px;"><p>${message}</p></div><div class="clear"></div></div></li>