<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

<script type="text/javascript">
	function addGroups(permissionId, permissionType){
		$('#add-group-permission').show();
		$('#permission-id').attr('value', permissionId);
		$('#add-new-group-header').html('Add New Groups To '+ permissionType);
		$.ajax( {
			type :"post",
			url :"/secure/permission.do",
			data: {method: 'all'},
			dataType: 'json',
			success : function(json) {
		    	   $('#permission-group-list').html('<ul>');
					$.each(json.groups, function(i,group){
						if(group.groupName != 'DEFAULT'){
							$('#permission-group-list').append('<li style="list-style-type:none;"><input type="checkbox" name="groupId" value="'+group.id +'" />&nbsp;&nbsp;'+ group.groupName+'</li>');
						}
					});
					$('#permission-group-list').append('</ul>');				
		    }
		});			
	}

	function cancelAddGroups(permissionId){
		$('#add-group-permission').hide();
	}

	function addBulkGroupPermission(){
		var formId = '#add-group-permission-form';
		var permissionId = $('#permission-id').val();
		
		jQuery(formId).validate( {
			submitHandler : function(form) {
				jQuery(form).ajaxSubmit( {
					url: '/secure/permission.do', 
					type: 'post',
					data: {method: 'addBulk'},
					dataType: 'json',
					success: function(permission) { 
						$('.permission').html('');	
						var permissionStr = '';
						$.each(permission.groups, function(i,group){				
							if(group.groupName != 'DEFAULT'){
								permissionStr = permissionStr + '<li style="list-style-type: none; position:relative;" id="' + permission.id + '_' + group.id + '">';
								permissionStr = permissionStr + '<div id="permission-group-header-' + permission.id + group.id + '" style="position:relative; clear:both;">';
								permissionStr = permissionStr + '<div style="float:left;">';
								permissionStr = permissionStr + '<h3><a href="javascript:void(0);" onclick="showHidePermissionGroup(\'#permission-person_group_' + permission.id + group.id + '\');">';
								permissionStr = permissionStr + '<img src="/images/fw_bold.gif" title="Show or hide friends for group ' + group.groupName + '" /></a>';
								permissionStr = permissionStr + '<span>' + group.groupName + '</span>';
								permissionStr = permissionStr + '</h3></div><div style="float:right;"><a href="#" onclick="removeGroupFrom(\'' + permission.id + '\', \'' + group.id + '\');">REMOVE</a></div></div><div class="clear"></div>';

								permissionStr = permissionStr +  '<ul id="permission-person_group_' + permission.id + group.id + '" style="display:none;">';
							
								$.each(group.friends, function(i,friend){
									permissionStr = permissionStr +  '<li style="list-style-type:none;padding-left:30px;">-&nbsp;&nbsp;' + friend.fullName + '</li>';
								});		
								
								permissionStr = permissionStr +  '</ul></div></li>';		
							}
						});
						$('.permission').append('<sns:permission permissionId="' + permission.id + '" permissionType="' + permission.permissionType + '" >' + permissionStr + '</sns:permission>');
					}
				});
			}
		});
	}

	$(document).ready(function(){
		$('#cancel-permission-button').click(function(){
			$('#add-group-permission').hide();
			document.getElementById('permission-group-list').innerHTML = '';
		});
	});

	function removeGroupFrom(permissionId, userGroupId){
		Boxy.confirm("Please confirm group delete:", function() { //alert('Confirmed!'); 
			$.ajax( {
				type :"POST",
				url :"/secure/permission.do",
				data: {method: 'remove', userGroupId: userGroupId, permissionId: permissionId},
				success : function(data) {
					$('#'+ permissionId + '_' + userGroupId).remove();
			    }
			});	
		}, {title: 'Confirm Delete'});
	    return false;
	}

	function getPermissionFromServer(permissionType){
		// 
		$('.permission').html('');	
		$.ajax( {
			type :"POST",
			url :"/secure/permission.do",
			data: {method: 'getByType', permissionType: permissionType},
			dataType: 'json',
			success : function(permission) {
				var permissionStr = '';
				$.each(permission.groups, function(i,group){	
					if(group.groupName != 'DEFAULT'){
						permissionStr = permissionStr + '<li style="list-style-type: none; position:relative;" id="' + permission.id + '_' + group.id + '">';
						permissionStr = permissionStr + '<div id="permission-group-header-' + permission.id + group.id + '" style="position:relative; clear:both;">';
						permissionStr = permissionStr + '<div style="float:left;">';
						permissionStr = permissionStr + '<h3><a href="javascript:void(0);" onclick="showHidePermissionGroup(\'#permission-person_group_' + permission.id + group.id + '\');">';
						permissionStr = permissionStr + '<img src="/images/fw_bold.gif" title="Show or hide friends for group ' + group.groupName + '" /></a>';
						permissionStr = permissionStr + '<span>' + group.groupName + '</span>';
						permissionStr = permissionStr + '</h3></div><div style="float:right;"><a href="#" onclick="removeGroupFrom(\'' + permission.id + '\', \'' + group.id + '\');"><img src="/images/removebl.png" /></a></div></div><div class="clear"></div>';

						permissionStr = permissionStr +  '<ul id="permission-person_group_' + permission.id + group.id + '" style="display:none;">';
					
						$.each(group.friends, function(i,friend){
							permissionStr = permissionStr +  '<li style="list-style-type:none;padding-left:30px;">-&nbsp;&nbsp;' + friend.fullName + '</li>';
						});		
						
						permissionStr = permissionStr +  '</ul></div></li>';		
					}
				});
				$('.permission').append('<sns:permission permissionId="' + permission.id + '" permissionType="' + permission.permissionType + '" >' + permissionStr + '</sns:permission>');
		    }
		});			
	}

	function showHidePermissionGroup(elementId){
		if($(elementId).is(':visible')){
			$(elementId).hide();
		}else{
			$(elementId).show();
		}
	}
</script>

<style type="text/css">
	#parent-permission-element {
		font-size: 9pt;
		padding: 5px;
	}

	#parent-permission-element h3 {
		font-size: 1em;
	}	

	#parent-permission-element a {
		font-size: 1em;
	}
		
	#permission-dynamic-friends-list LI{
		list-style-type: none;
	}

	.newpermissionsgroups{}
	
	.permission {
		width:600px;
		padding: 5px; 
	}
	.helpnote {
		padding-bottom: 10px;
	}
</style>


<div id="parent-permission-element">

	<div class="helpnote">
	If your account is set to private, use this screen to assign group permissions to your activities.
	<br/>
	Groups can be created under the group tab.
	</div>

	Activities:
	<select onchange="getPermissionFromServer(this[this.selectedIndex].value);">
		<option value="">--</option>
		
		<c:forEach items="${permissionTypes}" var="permissionType">
			<c:if test="${permissionType ne 'ALL'}">
				<option value="${permissionType}">${permissionType}</option>
			</c:if>
		</c:forEach>
	</select>

<hr/>
	
<div style="padding:5px;vertical-align; top;">
	<div id="add-group-permission" style="display: none;padding-left:10px;">
	
		<%-- Dynamic call add the header when addGroup is called --%>
		<h3 id="add-new-group-header"></h3>
		
		<form id="add-group-permission-form">
			<input id="permission-id" type="hidden" name="permissionId" value="" />
			
			<div id="permission-group-list">
			</div>
			
			<button name="button" type="submit" onclick="addBulkGroupPermission();"><img src="/images/buttons/save.png" /></button>
				<button name="button" id="cancel-permission-button" type="reset"><img src="/images/buttons/cancel.png" /></button>						
				<button name="button" type="reset" ><img src="/images/buttons/reset.png" /></button>	
			</form>
		</div>
	</div>
</div>

<div class="permission">
</div>
				