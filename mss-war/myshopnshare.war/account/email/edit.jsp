<%@ include file="/WEB-INF/jsp/includes.jsp"%>

	<form id="edit-email-form" name="emailform" style="display: none;">
		<input type="hidden" name="id" value="" />
		<div><label for="email"><span class="asterisk">*</span>Email </label></div>
		<div><input type="text" id="email" name="email" size="25" /></div>
		<div><label for="emailType">Email Type</label></div>
		<div><select name="emailType">
				<option value="" selected="selected">--------------------------------</option>
				<c:forEach items="${emailTypes}" var="emailType">
					<option value="${emailType}">${emailType.label}</option>
				</c:forEach>
			</select>
		</div>
		<div style="padding-top:5px;padding-bottom:5px;">
			<input type="radio" name="visibility" value="PRIVATE" /> PRIVATE
			&nbsp;&nbsp; 
			<input type="radio" name="visibility" value="PUBLIC" /> PUBLIC
		</div>			
		<div style="padding-bottom:5px;"><input type="checkbox" id="primaryEmail" name="primaryEmail" /> <label for="primaryEmail">Primary Email</label></div>
		<button name="button" id="edit-submit-email-button" type="submit">Edit</button>
		<button name="button" type="reset" >Reset</button>
	</form>	
