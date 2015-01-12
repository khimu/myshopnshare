<%@ include file="/WEB-INF/jsp/includes.jsp" %>

		<form id="edit-education-upload-form" onsubmit="editEducation(${education.id});return false;">	
		<input type="hidden" name="id" value="${education.id}" />
		<table cellpadding="3">
			<tbody id="education-element">
			<tr>
				<td><label for="institutionid">Member Institution</label></td>
				<td>
					<select id="institutionId" name="institutionId">
						<option value="${institution.id}">Select from our institution directory</option>
						<option value="">-</option>
						<c:choose>
							<c:when test="${not empty education.institution}">
								<c:forEach items="${institutions}" var="institution">
										
											<c:choose>
												<c:when test="${institution.id eq education.institution.id}">
													<option value="${institution.id}" selected="selected">${institution.institutionName}</option>
												</c:when>
												<c:otherwise>
													<option value="${institution.id}">${institution.institutionName}</option>
												</c:otherwise>
											</c:choose>					
										
								</c:forEach>
							</c:when>
							<c:otherwise><option value="${institution.id}">${institution.institutionName}</option></c:otherwise>
						</c:choose>
					</select>
				</td>
			</tr>	
			<tr>
				<td colspan="2">or</td>
			</tr>			
			<tr>
				<td><label for="institutionName">Institution Name</label></td>
				<td><input type="text" id="institutionName" size="30" maxlength="50" name="institutionName"  value="${education.institutionName}"/></td>
			</tr>						
			<tr>
				<td><label for="category">Institution Type: </label></td>
				<td>
					<select id="institutionType" name="institutionType" >
							<option value="" selected="selected">---------------</option>
							<c:forEach items="${institutionTypes}" var="type">
								<c:choose>
									<c:when test="${type eq education.institutionType}">
										<option value="${type.value}" selected="selected">${type.label}</option>
									</c:when>
									<c:otherwise>
										<option value="${type.value}">${type.label}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select>				
				</td>
			</tr>			
			<tr>
				<td><label for="degree">Degree</label></td>
				<td><input type="text" id="degree" size="30" maxlength="50" name="degree"  value="${education.degree}"/></td>
			</tr>	
			<tr>
				<td><label for="startYear">Start Year</label></td>
				<td><input type="text" id="startYear" size="30" maxlength="50" name="startYear"  value="${education.startYear}"/>
				</td>
			</tr>	
			<tr>
				<td><label for="endYear">Graduation Year</label></td>
				<td><input type="text" id="endYear" size="30" maxlength="50" name="endYear"  value="${education.endYear}"/>
				</td>
			</tr>	
			<tr>
				<td><label for="major">Major</label></td>
				<td><input type="text" id="major" size="30" maxlength="50" name="major"  value="${education.major}"/></td>
			</tr>						
			</tbody>						
			<tr>
				<td>
					<button name="button">Submit</button>
				</td>
			</tr>	
		</table>	
    	</form>