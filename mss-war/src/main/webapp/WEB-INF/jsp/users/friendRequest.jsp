<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">
	function confirmFriend(requestFriendId){
		$.ajax( {
			type :"POST",
			url :"/secure/manageRequest.do",
			data: {method: 'confirmFriend', requestFriendId: requestFriendId},
			success : function(data) {
				//$('#display-friend-request').html(data);
				$('#potential-friend-'+requestFriendId).remove();
		    }
		});				
	}
	
	function rejectFriend(requestFriendId){
		$.ajax( {
			type :"POST",
			url :"/secure/manageRequest.do",
			data: {method: 'rejectFriend', requestFriendId: requestFriendId},
			success : function(data) {
				//$('#display-friend-request').html(data);
				$('#potential-friend-'+requestFriendId).remove();
		    }
		});				
	}	


</script>


<div id="display-friend-request" class="postings">
	<ul class="item-journals">
		<c:forEach items="${friendRequests}" var="potentialFriend">
			<li id="potential-friend-${potentialFriend.id}">
				<div class="posting-header">
					<div style="float: left;">${potentialFriend.requester.fullName}</div>
				</div> 
				<div style="clear: both;position: relative;">
					<div style="float:left;width:70px;">
						<a href="/secure/friendPage.do?friendId=${potentialFriend.requester.id}">HELLO<%--<img src="../${potentialFriend.requester.defaultFace.icon.path}">--%></a>
					</div>
		
					<div class="clear"></div>
					<div style="position:relative;">
						<div style="float:left;padding:right:10px;">
							<a href="#" onclick="confirmFriend('${potentialFriend.id}');">Confirm</a>
						</div>
						<div style="float:right;">
							<a href="#" onclick="rejectFriend('${potentialFriend.id}');">Reject</a>
						</div>
					</div>
					<div class="clear"></div>
				</div>
			</li>
		</c:forEach>
	</ul>
</div>