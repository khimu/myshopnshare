<%@ include file="/WEB-INF/jsp/includes.jsp"%>


<div id="subscribe-contact-element" style="display: none;">

	<div style="position:relative; padding-bottom:10px;">
		<div style="width:200px;float:left;"><a href="javascript: void(0);" onclick="showHide('#subscribe-address-element', 'contact')"><img src="/images/prev.png" /></a></div><div><a href="javascript: void(0);" onclick="showHide('#subscribe-credit-element', 'contact')"><img src="/images/next.png" /></a></div>
	</div>
	<div>
		<div id="contact-subscribe-label" colspan="2">
				Phone	
		</div>
	</div>	
	<div>
		<div>
			<select id="contact-selection" name="contactId">
					<option value="" selected="selected"></option>
					<option value="-1">Add New</option>
				<c:forEach items="${phones}" var="phone">
				<option value="${phone.id}">(${phone.area}) ${phone.number} - ${phone.type}</option>
				</c:forEach>
			</select>	
			<div id="contact-subscribe-show-hide-element"></div>
		</div>
	</div>
</div>



