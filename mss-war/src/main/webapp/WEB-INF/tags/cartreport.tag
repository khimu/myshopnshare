<%@ tag isELIgnored="false" %><%@ attribute name="type" required="true" rtexprvalue="true" %><%@ attribute name="idType" required="true" rtexprvalue="true" %><%@ attribute name="isPrimary" required="false" rtexprvalue="true" %><%@ attribute name="primaryKey" required="true" rtexprvalue="true" %><%@ attribute name="records" required="true" rtexprvalue="true" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><tr id="cart-${primaryKey}"><c:forTokens items="<%=records%>" delims="|" var="record"><td>${record}</td></c:forTokens><td><a href="javascript: void(0);" onclick="deleteItemFromCart(${primaryKey});">Remove</a></td></tr>