<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<script type="text/javascript">
	
	$(document).ready(function(){
		$('#submit-education-upload-button').click(function(){
			var errorMsg = '';

			if($('#institutionName').val() == '' && $('#institutionId').val() == ''){
				errorMsg = errorMsg + 'Please select an institution from our institution members list or enter an institution name.  <div class="clear"></div>';
			}
			
			if(!isInteger($('#startYear').val())){
				errorMsg = errorMsg + 'Please enter the year you started attending the institution in the format YYYY.  <div class="clear"></div>';
			}
			if(!isInteger($('#endYear').val())){
				errorMsg = errorMsg + 'Please enter the year you graduated in the format YYYY.  <div class="clear"></div>';
			}
			
			if($('#institutionType').val() == ''){
				errorMsg = errorMsg + 'Please select the institution type.  <div class="clear"></div>';
			}
			
			if($('#degree').val() == ''){
				errorMsg = errorMsg + 'Please enter the degree you obtained from the institution.  <div class="clear"></div>';
			}
			
			if($('#major').val() == ''){
				errorMsg = errorMsg + 'Please enter your major.  <div class="clear"></div>';
			}

			if(errorMsg != ''){
				Boxy.alert('<div class="errors">'+errorMsg+'</div>');
				return false;
			}
			
			$('#loading').show();
			$.ajax( {
				type :"post",
				url :"/secure/education.do?method=education&" + $('#user-education-upload-form').serialize(),
				dataType: 'html',
				success : function(data) {
					$('#loading').hide();
					Boxy.get('#education-form').hide();
					$('#user-education-upload-form').clearForm();
					$('#education-parent-element').html(data);
			    }
			});	
		});
	});
</script>

<div id="user-education-upload-box"> 							
		<form id="user-education-upload-form" onsubmit="return false;">	
		<table cellpadding="3">
			<tbody id="education-element">
			<tr>
				<td><label for="institutionid">Member Institution</label></td>
				<td>
					<select id="institutionId" name="institutionId">
						<option value="${institution.id}">Select from our institution directory</option>
						<option value="">-</option>
					<c:forEach items="${institutions}" var="institution">
						<option value="${institution.id}">${institution.institutionName}</option>
					</c:forEach>
					</select>
				</td>
			</tr>	
			<tr>
				<td colspan="2">or</td>
			</tr>			
			<tr>
				<td><label for="institutionName">Institution Name</label></td>
				<td><input type="text" id="institutionName" size="30" maxlength="50" name="institutionName" /></td>
			</tr>						
			<tr>
				<td><label for="category">Institution Type: </label></td>
				<td>
					<select id="institutionType" name="institutionType" >
							<option value="" selected="selected">---------------</option>
							<c:forEach items="${institutionTypes}" var="type">
								<option value="${type.value}">${type.label}</option>
							</c:forEach>
					</select>				
				</td>
			</tr>			
			<tr>
				<td><label for="degree">Degree</label></td>
				<td><input type="text" id="degree" size="30" maxlength="50" name="degree" /></td>
			</tr>	
			<tr>
				<td><label for="startYear">Start Year</label></td>
				<td><input type="text" id="startYear" size="30" maxlength="50" name="startYear" />
				</td>
			</tr>	
			<tr>
				<td><label for="endYear">Graduation Year</label></td>
				<td><input type="text" id="endYear" size="30" maxlength="50" name="endYear" />
				</td>
			</tr>	
			<tr>
				<td><label for="major">Major</label></td>
				<td><input type="text" id="major" size="30" maxlength="50" name="major" /></td>
			</tr>						
			</tbody>						
			<tr>
				<td>
					<button name="button" id="submit-education-upload-button">Submit</button>
					<button name="button" id="reset-education-upload-button" type="reset">Reset</button>
				</td>
			</tr>	
		</table>	
    	</form>
    	<img id="loading" src="/images/loading.gif" style="display:none;"/>
    </div>