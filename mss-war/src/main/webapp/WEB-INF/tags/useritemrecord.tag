<%@ tag isELIgnored="false" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ attribute name="primaryKey" required="true" rtexprvalue="true" %><%@ attribute name="icon" required="true" rtexprvalue="true" %><%@ attribute name="glimpse" required="true" rtexprvalue="true" %><%@ attribute name="description" required="true" rtexprvalue="true" %><%@ attribute name="caption" required="true" rtexprvalue="true" %><p><a href="${glimpse}" rel="prettyPhoto[gallery]" title="${description}" title="${description}"><img src="${icon}" width="70px" height="70px" title="${description}" alt="${description}" /></a><br /><a href="#" onclick="viewItemDetail(\'${primaryKey}\')" alt="View Item Detail">MORE ...</a></p>