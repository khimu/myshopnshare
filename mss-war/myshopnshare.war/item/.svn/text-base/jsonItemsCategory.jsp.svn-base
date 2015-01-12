<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<json:object>
  <json:property name="searchMessage" value="${searchMessage}"/>
  <json:array name="items" var="item" items="${items}">
    <json:object>     
     <json:property name="itemId" value="${item.item.id}"/>
	 <json:property name="icon">
	 <c:set	var="slash" value="/" />
	 <c:choose>
	 <c:when test="${not empty item.item.externalLink}">
	 	${item.item.externalLink}
	 </c:when>
	 <c:otherwise>
	 	${slash}${item.item.icon.path}
	 </c:otherwise>
	 </c:choose>
	 </json:property>
	 
	 <json:property name="glimpse" value="/${item.item.glimpse.path}"/>
	 <json:property name="description" value="${item.item.description}"/>
	 <json:property name="itemName" value="${fn:toUpperCase(item.item.itemName)}"/>
	 <json:property name="caption" value="${fn:toUpperCase(item.item.itemName)}"/>
	 <json:property name="type" value="${item.item.type}"/>
	 <json:property name="resourceLink" value="${item.item.resourceLink}"/>
	 <json:property name="externalImageLink" value="${item.item.externalLink}"/>

	  <json:array name="tags" var="tag" items="${item.item.tags}">
	    <json:object>
		 <json:property name="id" value="${tag.id}"/>
		 <json:property name="tag" value="${tag.tag}"/>
	    </json:object>
	  </json:array>
	 <json:property name="id" value="${item.id}"/>
	 <json:property name="color" value="${item.category.color}"/>
    </json:object>
  </json:array>
</json:object>