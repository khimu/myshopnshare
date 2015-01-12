<%@ include file="/WEB-INF/jsp/includes.jsp"%>
    <json:object>
   	  <json:property name="id" value="${phone.id}"/>
   	  <json:property name="area" value="${phone.area}"/>
   	  <json:property name="number" value="${phone.number}"/>
   	  <json:property name="type" value="${phone.type.value}"/>
   	  <json:property name="primaryPhone" value="${phone.primaryPhone}"/>
   	  <json:property name="visibility" value="${phone.visibility.value}"/>
    </json:object>
