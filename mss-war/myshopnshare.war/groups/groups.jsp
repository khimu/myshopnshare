<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<script type="text/javascript">
	function loadFriends() {
		$.ajax( {
			type :"post",
			url :"/secure/manageFriends.do",
			data : {
				method :'get',
				personId :'${person.id}'
			},
			dataType :'json',
			success : function(json) {
				$('.dynamic-friends-list').append('<ul');
				$.each(json.friends, function(i, friend) {
					$('.dynamic-friends-list').append(
							'<li style="font-weight:bold;padding:2px;"><input type="checkbox" name="friendIds" value="'
									+ friend.friendProfile.friendId + '" />&nbsp;&nbsp;'
									+ friend.friendProfile.fullName + '</li>');
				});
				$('.dynamic-friends-list').append('</ul>');
			}
		});
	}

	function addNewGroup() {
		 $.ajax({  
		       type: "post",  
		       url: "/secure/manageFriends.do",
		       data: {method: 'get', personId: '${person.id}'},
		       dataType: 'json', 
		       success: function(json){ 
		    	   $('#add-dynamic-friends-list').html('');
					$('#add-dynamic-friends-list').append('<ul>');
					$.each(json.friends, function(i,friend){
						$('#add-dynamic-friends-list').append('<li style="list-style-type: none;"><input type="checkbox" name="friendIds" value="'+friend.friendProfile.friendId +'" />&nbsp;&nbsp;'+friend.friendProfile.fullName+'</li>');
					});
					$('#add-dynamic-friends-list').append('</ul>');
		       }
		 });	
		 $('#show-add-group-form').show();
	}

	/** REMOVE FRIEND FROM GROUP **/
	function removeFriendFrom(groupId, friendId){
		Boxy.confirm("Please confirm delete", function() {
			$.ajax( {
				type :"post",
				url :"/secure/userGroup.do",
				data: {method: 'remove', groupId: groupId, friendId: friendId},
				success : function(data) {
					$('#'+groupId+'_'+friendId).remove();
			    }
			});	
		}, {title: 'Confirm Delete'});
	    return false;
	}

	/** MAINLY TO ADD friend to group **/
	function editVisibility(groupId){
	 	$.ajax({  
		       type: "post",  
		       url: "/secure/visibility.do?" + $('#edit-visibility-form'+groupId).serialize(),
		       data: {method: 'edit'},
		       success: function(data){ 
			       // returns group.jsp
			       $('#person_group_' + groupId).remove();
		    	   $('#group-header-' + groupId).remove();
			       $('#all-groups_span').append(data);
			       $('#person_group_' + groupId).hide();

		       }
		     });		
	}
	
	function deleteGroup(groupId){
		Boxy.confirm("Please confirm group delete", function() {
			$.ajax( {
				type :"POST",
				url :"/secure/visibility.do",
				data: {method: 'delete', groupId: groupId},
				success : function(data) {
					$('#person_group_' + groupId).remove();
					$('#group-header-' + groupId).remove();
			    }
			});	
		}, {title: 'Confirm Delete'});
	    return false;			
	}
	
	
	$(document).ready(function(){	
		$('#change-default-permission input').click(function(){
			var visibility = $("input[@name='visibility']:checked").val();
			
			Boxy.confirm("Please confirm privacy setting change to " + visibility, function() {
				 	$.ajax({  
				       type: "post",  
				       url: "/secure/visibility.do",
				       data: {method: 'update', visibility: visibility},
				       success: function(data){ 
				    	   $('#change-default-permission').hide();
				    	   $('#default-group h3').html(data);
				       }
				     });
			}, {title: 'Confirm Delete'});
		    return false;
		    
		    $('#change-default-permission').hide();			
		});	
	});

	function showHideGroup(elementId){
		if($(elementId).is(':visible')){
			$(elementId).hide();		
			$('.dynamic-friends-list').html('');	
		}else{
			$(elementId).show();
			loadFriends();
		}
	}
</script>

<style type="text/css">
	#groups-element {
		padding: 5px;
	}
	
</style>

<%-- Button addNewGroup will dynamically add friends to the form at the bottom of the page and then display the page --%>
<h3><a href="#" onclick="addNewGroup();"><img src="/images/buttons/addgroup.png" /></a></h3>

<div id="show-add-group-form" style="display:none;">
	<%@ include file="add.jsp" %>		
</div>


<%-- GroupPerson.java --%>
<div id="all-groups_span" style="border: 1px solid #192666;padding:5px; width:600px;">
	<c:forEach items="${groups}" var="group">
			<div id="group-header-${group.id}" style="position:relative; clear:both;">			
				<div style="float:left;">
					<h3><a href="javascript:void(0);" onclick="showHideGroup('#person_group_${group.id}');">
					<img src="/images/fw_bold.gif" title="Show or hide friends for group ${group.permissionVisibility.groupName}" /></a>
					<span>${group.permissionVisibility.groupName}</span>
					</h3>
				</div>
				<div style="float:right;"><a href="#" onclick="deleteGroup('${group.id}');"><img src="/images/delete.png" /></a></div>
			</div>			
			
			<div class="clear"></div>
			<div id="person_group_${group.id}" style="display:none;">
				<div class="clear"></div>
				<div>
					<div style="float:none; padding-bottom:5px; clear:both;">
						<ul>
						<%-- GroupPerson -> PermissionVisibility -> PermissionVisibilityPerson -> Friend --%>
						<c:forEach items="${group.friends}" var="friend" >
							<li style="list-style-type:none; padding-left:15px;position:relative;" id="${group.id}_${friend.id}"><div style="float:left;">${friend.fullName}</div> <div style="float:right"><a href="#" onclick="removeFriendFrom('${group.id}', '${friend.id}');">Remove</a></div><div class="clear"></div></li>
					 	</c:forEach>
					 	</ul>
					</div>		
					<div class="clear"></div>
													
					<div>
						<hr/>
						<div style="margin:10px; background-color:#FFF; padding:4px;">
							<form id="edit-visibility-form${group.id}" onsubmit="return false;">
								<input id="edit-visibilit-id" type="hidden" name="groupId" value="${group.id}">
								<label for="groupName">Group Name</label> &nbsp;&nbsp;
								<input type="text" id="groupName-${group.id}" name="groupName" value="${group.permissionVisibility.groupName}" size="40"/>
								<div class="clear"></div>
								<div><label for="friends">Add Friends: </label></div>
								
								<div><div class="dynamic-friends-list"></div></div>
								
								<button name="button" type="submit" onclick="editVisibility('${group.id}');"><img src="/images/buttons/save.png" /></button>
							</form>	
						</div>				
					</div>
				</div>				
			</div>
			<div class="clear"></div>
	</c:forEach>
</div>