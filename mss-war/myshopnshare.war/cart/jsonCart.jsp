<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<json:object>
  <json:property name="actionMessage" value="${actionMessage}"/>
  <json:property name="cartSize" value="${shoppingCart.cartSize}" />
  <json:property name="grandTotal" value="${shoppingCart.grandTotal}" />
</json:object>