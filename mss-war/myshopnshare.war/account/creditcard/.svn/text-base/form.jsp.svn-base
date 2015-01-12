<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<div id="account-creditcard-form" style="display: none;">
	<form id="add-creditcard-form">
		<input type="hidden" name="id" /> 		
		<div>
			<div><label for="cardName">Card Name</label></div>
			<div><input type="text" id="cardName" name="cardName" /></div>
			<div><label for="fullName">Full Name</label></div>
			<div><input type="text" id="fullName" name="fullName" /></div>
			<div><span class="asterisk">*</span><label for="number">Card Number</label></div>
			<div><input type="text" id="number" name="number" /></div>
			<div><span class="asterisk">*</span><label for="expirationDate">Expiration Date</label></div>
			<div><input type="text" id="expirationDate" name="expirationDate"/></div>
			<div><span class="asterisk">*</span><label for="securityCode">Security Code</label></div>
			<div><input type="text" id="securityCode" name="securityCode" /></div>
			<div><span class="asterisk">*</span><label for="creditCardType">Card Type</label></div>
			<div><select name="creditCardType">
					<option value="" selected="selected"></option>
					<c:forEach items="${cardTypes}" var="type">
					<option value="${type}">${type.label}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<button name="button" id="submit-creditcard-button" type="submit">Add</button>
		<button name="button" type="reset">Reset</button>
	</form>
</div>
