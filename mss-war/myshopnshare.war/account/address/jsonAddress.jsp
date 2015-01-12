<%@ include file="/WEB-INF/jsp/includes.jsp"%>

    <json:object>
   	  <json:property name="id" value="${address.id}"/>
      <json:property name="street1" value="${address.street1}"/>
      <json:property name="street2" value="${address.street2}"/>
      <json:property name="unitNumber" value="${address.unitNumber}"/>
      <json:property name="city" value="${address.city}"/>
      <json:property name="stateOrProvince" value="${address.stateOrProvince}"/>
      <json:property name="country" value="${address.country}"/>
      <json:property name="postalCode" value="${address.postalCode}"/>
      <json:property name="primaryAddress" value="${address.primaryAddress}"/>
      <json:property name="addressType" value="${address.type.value}"/>
      <json:property name="visibility" value="${address.visibility.value}"/>
    </json:object>