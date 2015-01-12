<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<script type="text/javascript">
	function deleteFriend(fullName, friendId){
			Boxy.confirm("Please confirm delete " + fullName, function() {
			 $.ajax({  
			       type: "post",  
			       url: "/secure/manageFriends.do",
			       data: {method: 'delete', friendId: friendId},
			       dataType: 'json', 
			       success: function(json){ 
				       $('#existing-friends-list'+friendId).remove();
			       }
			 });
			}, {title: 'Confirm Delete'});
		    return false;
	}
</script>


<div id="show-all-existing-friends">
	<div id="all-existing-friends-list">
		<ul>
			<c:forEach items="${friends}" var="friend">
				<li id="existing-friends-list${friend.id}" style="list-style-type: none;"><div style="position:relative;"><div style="float:left;"><img src="/${friend.friend.defaultFace.icon.path}" /></div><div style="float:left;width:150px;">&nbsp;&nbsp;${friend.friend.fullName}</div> <div style="padding:5px;float:left;clear:right;">&nbsp;&nbsp; <a href="javascript: void(0);" onclick="deleteFriend('${friend.friend.fullName}', '${friend.id}');"><img src="/images/deletebl.png" /></a></div><div class="clear" /></div> </li>	
			</c:forEach>
		</ul>	
	</div>
</div>