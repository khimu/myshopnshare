<%@ include file="/WEB-INF/jsp/includes.jsp" %>

	<form id="edit-employer-submit-form" onsubmit="editEmployment(${employment.id});return false;">	
		<input type="hidden" name="id" />			
		<table cellpadding="3">
			<tr>
				<td><label for="employerid">Member Employer</label></td>
				<td>
					<select id="employerId" name="employerId">
						<option value="">Select from our business directory</option>
						<c:choose>
							<c:when test="${not empty employment.employer}">
								<c:forEach items="${employers}" var="employer">
										
											<c:choose>
												<c:when test="${employer.id eq employment.employer.id}">
													<option value="${employer.id}" selected="selected">${employer.companyName}</option>
												</c:when>
												<c:otherwise>
													<option value="${employer.id}">${employer.companyName}</option>
												</c:otherwise>
											</c:choose>					
										
								</c:forEach>
							</c:when>
							<c:otherwise><option value="${employer.id}">${employer.companyName}</option></c:otherwise>
						</c:choose>						
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2">or</td>
			</tr>		
			<tr>
				<td><label for="employerName">Employer Name</label></td>
				<td><input type="text" id="employerName" size="30" maxlength="50" name="employerName" value="${employer.employerName}" /></td>
			</tr>	
			<tr>
				<td><label for="jobtitle">Title</label></td>
				<td><input type="text" id="jobtitle" size="30" maxlength="50" name="title" value="${employer.title}" /></td>
			</tr>
			<tr>
				<td><label for="startDate">Start Date</label></td>
				<c:set var="startDate"><fmt:formatDate pattern="mm/dd/yyyy" value="${employer.startDate}" /></c:set>
				<td><input type="text" id="startDate" size="30" maxlength="50" name="startDate" value="${startDate}" /></td>
			</tr>
			<tr>
				<td><label for="endDate">End Date</label></td>
				<c:set var="endDate"><fmt:formatDate pattern="mm/dd/yyyy" value="${employer.endDate}" /></c:set>
				<td><input type="text" id="endDate" size="30" maxlength="50" name="endDate" value="${endDate}" /></td>
			</tr>
			<tr>
				<td><label for="department">Department</label></td>
				<td><input type="text" id="department" size="30" maxlength="50" name="department" value="${employer.department}" /></td>
			</tr>
			<tr>
				<td><label for="division">Division</label></td>
				<td><input type="text" id="division" size="30" maxlength="50" name="division" value="${employer.division}" /></td>
			</tr>				
			<tr>
				<td colspan="2">
					<input class="submit" type="submit" value="Submit" />
				</td>
			</tr>					
		</table>	 
	</form>  