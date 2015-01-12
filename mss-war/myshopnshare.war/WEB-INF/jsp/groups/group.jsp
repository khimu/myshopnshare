<%@ include file="/WEB-INF/jsp/includes.jsp" %>


<div id="group-header-${group.id}" style="position:relative; clear:both;">			
	<div style="float:left;">
		<h3><a href="javascript:void(0);" onclick="showHideGroup('#person_group_${group.id}');">
		<img src="/images/fw_bold.gif" title="Show or hide friends for group ${group.permissionVisibility.groupName}" /></a>
		<span>${group.permissionVisibility.groupName}</span>
		</h3>
	</div>
	<div style="float:right;"><a href="#" onclick="deleteGroup('${group.id}');">DELETE</a></div>
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
					
					<button name="button" type="submit" onclick="editVisibility('${group.id}');"><img src="/images/buttons/save.png"/></button>
					</form>	
				</div>				
			</div>
		</div>				
	</div>
<div class="clear"></div>
