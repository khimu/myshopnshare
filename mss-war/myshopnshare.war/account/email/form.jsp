<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<div style="display: none;">
	<form id="add-email-form" name="emailform">
		<input type="hidden" name="id" value="" />
		<div><label for="email"><span class="asterisk">*</span>Email</label></div>
		<div><input type="text" id="email" name="email" size="25" /></div>
		<div><label for="emailType">Email Type</label></div>
		<div>
			<select name="emailType">
				<option value="" selected="selected">-----------------------------------</option>
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
		<div style="padding-bottom:5px;">
			<label for="primaryEmail">Primary Email</label>
			<input type="checkbox" id="primaryEmail" name="primaryEmail" />
		</div>
		<div>
			<button name="button" id="submit-email-button" type="submit">Add</button>
			<button name="button" type="reset" >Reset</button>
		</div>
	</form>	
</div>
