<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<script type="text/javascript">
	$(document).ready(function(){
		$('#submit-profile-upload-button').click(function(){
			$.ajax( {
				type :"post",
				url :"/secure/profile.do?method=profile&" + $('#user-profile-upload-form').serialize(),
				dataType: 'html',
				success : function(data) {
					$('#user-profile-upload-box').html(data);
			    }
			});	
		});
	});
</script>

<div id="user-profile-upload-box" > 							
		<form id="user-profile-upload-form">	
		<table cellpadding="3">	
			<tr>
				<td><label for="language">Language</label></td>
				<td><input id="language" name="language" type="text" size="30" maxlength="50"  maxlength="50" value="${profile.language}" /></td>
			</tr>		
			<tr>
				<td><label for="aim">AIM</label></td>
				<td><input type="text" id="aim" name="aim" size="30" value="${profile.aim}" /></td>
			</tr>			
			<tr>
				<td><label for="expertise">Expertise</label></td>
				<td><textarea id="expertise" name="expertise" cols="50" rows="5">${profile.expertise}</textarea></td>
			</tr>
			<tr>
				<td><label for="activities">Activities</label></td>
				<td><textarea id="activities" name="activities" cols="50" rows="5">${profile.activities}</textarea></td>
			</tr>		
			<tr>
				<td><label for="about">About</label></td>
				<td><textarea id="about" name="about" cols="50" rows="5">${profile.about}</textarea></td>
			</tr>				
			<tr>
				<td>
					<button name="button" id="submit-profile-upload-button">Submit</button>
					<button name="button" id="reset-profile-upload-button" type="reset">Reset</button>
				</td>
			</tr>	
		</table>	
    	</form>
    	<img id="loading" src="/images/loading.gif" style="display:none;"/>
    </div>