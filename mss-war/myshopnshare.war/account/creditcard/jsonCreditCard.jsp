<%@ include file="/WEB-INF/jsp/includes.jsp"%>

    <json:object>
   	  <json:property name="id" value="${creditcard.id}"/>
   	  <json:property name="cardName" value="${creditcard.cardName}"/>
   	  <json:property name="fullName" value="${creditcard.fullName}"/>
   	  <json:property name="number" value="${creditcard.number}"/>
   	  <json:property name="expirationDate" value="${creditcard.expirationDate}"/>
   	  <json:property name="securityCode" value="${creditcard.securityCode}"/>
   	  <json:property name="type" value="${creditcard.type.value}"/>
		<json:object name="cardAddress">
	      <json:property name="id" value="${creditcard.cardAddress.id}"/>
	      <json:property name="street1" value="${creditcard.cardAddress.street1}"/>
	      <json:property name="street2" value="${creditcard.cardAddress.street2}"/>
	      <json:property name="unitNumber" value="${creditcard.cardAddress.unitNumber}"/>
	      <json:property name="city" value="${creditcard.cardAddress.city}"/>
	      <json:property name="stateOrProvince" value="${creditcard.cardAddress.stateOrProvince}"/>
	      <json:property name="country" value="${creditcard.cardAddress.country}"/>
	      <json:property name="postalCode" value="${creditcard.cardAddress.postalCode}"/>
	      <json:property name="primaryAddress" value="${creditcard.cardAddress.primaryAddress}"/>
	    </json:object>   	  
    </json:object>