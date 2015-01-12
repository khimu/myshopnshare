<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<json:object>
  <json:property name="searchMessage" value="${searchMessage}"/>
	 <json:property name="icon">
	 <c:set	var="slash" value="/" />
	 <c:choose>
	 <c:when test="${not empty item.externalLink}">
	 	${item.externalLink}
	 </c:when>
	 <c:otherwise>
	 	${slash}${item.icon.path}
	 </c:otherwise>
	 </c:choose>
	 </json:property>
	 <json:property name="glimpse" value="/${item.glimpse.path}">
	 </json:property>
	 <json:property name="description" value="${item.description}"/>
	 <json:property name="itemName" value="${fn:toUpperCase(item.itemName)}"/>
	 <json:property name="caption" value="${fn:toUpperCase(item.itemName)}"/>

	 <json:property name="title" value="${fn:toUpperCase(item.title)}"/>
	 <json:property name="subtitle" value="${fn:toUpperCase(item.subtitle)}"/>
	 <json:property name="subscribed" value="${item.subscribed}"/>
	 <json:property name="type" value="${item.type}"/>
	 <json:property name="resourceLink" value="${item.resourceLink}"/>
	 <json:property name="externalImageLink" value="${item.externalLink}"/>

	<c:if test="${item.type eq 'POINT'}">	 
		<json:property name="serialNumber" value="${item.serialNumber}"/>
		<json:property name="quantity" value="${item.quantity}"/>
		<json:property name="price" value="${item.price}"/>
		<json:property name="shipping" value="${item.shipping}"/>
		<json:property name="tax" value="${item.tax}"/>
	</c:if>
 
	<c:if test="${item.type eq 'VENDOR'}">	 
		<json:property name="serialNumber" value="${item.serialNumber}"/>
		<json:property name="quantity" value="${item.quantity}"/>
		<json:property name="price" value="${item.price}"/>
		<json:property name="shipping" value="${item.shipping}"/>
		<json:property name="tax" value="${item.tax}"/>
		<json:property name="clearanceAmount" value="${item.clearancePercentage}"/>
		<json:property name="rebateAmount" value="${item.rebateAmount}"/>
		<json:property name="refurbish" value="${item.refuberish}"/>
		<json:property name="used" value="${item.used}"/>
	</c:if>

	<c:set var="tags" value="" />
	<c:forEach items="${item.tags}" var="thetag">
		<c:set var="tags">
			<c:if test="${not empty thetag}">${thetag.tag}, </c:if>	${tags}
		</c:set>
	</c:forEach>
	
	<json:property name="tags" value="${tags}" />
	
	 <json:property name="id" value="${item.id}"/>
</json:object>