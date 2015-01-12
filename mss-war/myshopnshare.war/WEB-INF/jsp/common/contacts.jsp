<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<div id="person-contact-infos" style="display: none; width: 400px;">
	<c:if test="${person.publicAddress eq true}">
		<h3><span>Location</span></h3>
      <div style="background-color: #FFF; padding:5px;">
      <c:forEach items="${person.addresses}" var="address">
	      <c:if test="${address.visibility eq 'PUBLIC'}">
		      <div>${address.street1} <c:if test="${not empty address.unitNumber}"># ${address.unitNumber}</c:if></div>
		      <div>${address.street2}</div>
		      <div>${address.city}, ${address.stateOrProvince}, ${address.postalCode}</div>
		      <h4><strong><a href="http://maps.google.com/maps/geo?KeepThis=true&TB_iframe=true&height=450&width=740&q=${address.street1},+${address.city},+${address.stateOrProvince},+${address.postalCode}&output=json&oe=utf8&sensor=true_or_false&key=ABQIAAAAMNNNy34UOUsl9aq0D4kvcBSCJrqMDY1plGogyxSzH0ceUbbsxxRnTZItpUWKC3_XCWAeFxOjOU8Q4Q" class="thickbox" title="${address.street1} @ ${address.city}, ${address.stateOrProvince} ${address.postalCode}">Map</a></strong></h4>
	      </c:if>
      </c:forEach>
      </div>
	</c:if>

	<c:if test="${person.publicEmail eq true}">
		<h3><span>Email</span></h3>
	
      <div style="background-color: #FFF; padding:5px;">
      <c:forEach items="${person.emails}" var="email">
	      <c:if test="${email.visibility eq 'PUBLIC'}">
		      <div>${email.email}</div>
	      </c:if>
      </c:forEach>
      </div>
	</c:if>

	<c:if test="${person.publicContact eq true}">
		<h3><span>Contact</span></h3>
      <div style="background-color: #FFF; padding: 5px;">
      <c:forEach items="${person.phones}" var="phone">
	      <c:if test="${phone.visibility eq 'PUBLIC'}">
		      <div>${phone.area} ${phone.number} [${phone.type.value}]</div>
	      </c:if>
      </c:forEach>
      </div>
	</c:if>
	
</div>