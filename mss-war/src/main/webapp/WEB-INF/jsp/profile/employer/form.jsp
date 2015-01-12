<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<script type="text/javascript">

$(document).ready(function(){	
		$("#startDate").Watermark("MM/DD/YYYY");
		$("#endDate").Watermark("MM/DD/YYYY");
	
	$('#submit-employer-button').click(function(){
		$('#loading').show();
		var startDate = $('#startDate').val().split('/');
		var endDate = $('#endDate').val().split('/');
		
		/*
		if($('#endDate').val() != 'present' || !isDate(startDate[2], startDate[0], startDate[1]) || !isDate(endDate[2], endDate[0], endDate[1])){
			Boxy.alert("Please enter the start date and end date in the format MM/DD/YYYY.");
			return false;
		}
		*/

		var errorMsg = '';

		if($('#employerName').val() == '' && $('#employerId').val() == ''){
			errorMsg = errorMsg + 'Please select an employer from our employer members list or enter an employer name.  <div class="clear"></div>';
		}
		
		if($('#jobtitle').val() == ''){
			errorMsg = errorMsg + 'Please enter your job title.  <div class="clear"></div>';
		}

		if(errorMsg != ''){
			Boxy.alert('<div class="errors">'+errorMsg+'</div>');
			return false;
		}

		
		var params = $('#employer-submit-form').serialize();
		
		$.ajax( {
			type :"post",
			url: '/secure/employment.do?method=employer&'+params,
			dataType: 'html',
			success : function(data) {
				$('#loading').hide();
				Boxy.get('#employer-submit-form').hide();
				$('#employer-submit-form').clearForm();
				$('#employment-parent-element').html(data);
		    }
		});	
	});
});

</script>
	<form id="employer-submit-form" onsubmit="return false;">				
		<table cellpadding="3">
			<tr>
				<td><label for="employerid">Member Employer</label></td>
				<td>
					<select id="employerId" name="employerId">
						<option value="">Select from our business directory</option>
					<c:forEach items="${employers}" var="employer">
						<option value="${employer.id}">${employer.companyName}</option>
					</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2">or</td>
			</tr>		
			<tr>
				<td><label for="employerName">Employer Name</label></td>
				<td><input type="text" id="employerName" size="30" maxlength="50" name="employerName" /></td>
			</tr>	
			<tr>
				<td><label for="jobtitle">Title</label></td>
				<td><input type="text" id="jobtitle" size="30" maxlength="50" name="title" /></td>
			</tr>
			<tr>
				<td><label for="startDate">Start Date</label></td>
				<td><input type="text" id="startDate" size="30" maxlength="50" name="startDate" /></td>
			</tr>
			<tr>
				<td><label for="endDate">End Date</label></td>
				<td><input type="text" id="endDate" size="30" maxlength="50" name="endDate" /></td>
			</tr>
			<tr>
				<td><label for="department">Department</label></td>
				<td><input type="text" id="department" size="30" maxlength="50" name="department" /></td>
			</tr>
			<tr>
				<td><label for="division">Division</label></td>
				<td><input type="text" id="division" size="30" maxlength="50" name="division" /></td>
			</tr>				
			<tr>
				<td colspan="2">
					<input id="submit-employer-button" class="submit" type="submit" value="Submit" />
					<button name="button" id="cancel-employer-button" type="reset" >Cancel</button>	
				</td>
			</tr>					
		</table>	 
	</form>  
	<img id="loading" src="/images/loading.gif" style="display:none;"/>