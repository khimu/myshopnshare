<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<script type="text/javascript">
	function cancelAddVisibility(){
		$('#show-add-group-form').hide();
		document.getElementById('add-dynamic-friends-list').innerHTML = '';
	}
	
	function createGroup(){
		var formId = '#add-visibility-form';
		jQuery(formId).validate( {
			rules : {
				groupName: "required"
			},
			messages : {
				groupName :"Please enter a group name."
			},
			submitHandler : function(form) {
				jQuery(form).ajaxSubmit( {
					url: '/secure/visibility.do', 
					type: 'post',
					data: {method: 'add'},
					success: function(data) { 
						$('#show-add-group-form').hide();
						$(form).clearForm();
						$('#all-groups_span').append(data);
					}
				});
			}
		});
	}
</script>

<form id="add-visibility-form">
<table>
	<tbody id="group-name">
		<tr>
			<td>Group Name</td>
			<td><input type="text" name="groupName" /></td>
		</tr>
	</tbody>	
	<tr>
		<td></td>
		<td>
		<div id="add-dynamic-friends-list"></div>
		</td>
	</tr>				
	<tr>
	<td></td>
	<td>
		<button name="button" type="submit" onclick="createGroup();"><img src="/images/buttons/save.png" /></button>
		<button name="button" onclick="cancelAddVisibility(); return false;"><img src="/images/buttons/cancel.png" /></button>
		<button name="button" type="reset" ><img src="/images/buttons/reset.png" /></button>
	</td>
	</tr>						
</table>
</form>