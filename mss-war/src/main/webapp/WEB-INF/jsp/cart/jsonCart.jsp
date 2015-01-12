<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<json:object>
  <json:property name="actionMessage" value="${actionMessage}"/>
  <json:property name="cartSize" value="${cartItemSize}" />
  <json:property name="grandTotal" value="${cartItemsGrandTotal}" />
</json:object>