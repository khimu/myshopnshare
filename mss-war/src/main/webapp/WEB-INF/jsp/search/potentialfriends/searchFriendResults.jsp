<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">
	function addMessage(friendId){
		$('#friend-info' + friendId).hide();
		$('#friend-request-message' + friendId).show();
	}
	function addRequest(friendId){
		$('#loading').show();
		var themessage = $('#the-message-to-friend'+friendId).val();
		$.ajax( {
			type :"POST",
			url :"/secure/potentialFriends.do",
			data: {method: 'requestFriend', friendId: friendId, message: themessage},
			success : function(data) {
				$('#loading').hide();
				$('#result' + friendId).remove();
				$('#request-friend-message').html(data);
		    }
		});			
	}			
</script>

<div style="margin-top:10px;width:600px;">
	<h3>Your search results.</h3>
	<br/>
	<div id="loading"><img src="/images/loading.png"/></div>
	<div id="request-friend-message">
	</div>

	<ul class="item-journals">
		<c:forEach items="${results}" var="result">
			<li id="result${result.id}">
				<div class="posting-header" style="width: 550px;">
					<div style="float: left;">${result.fullName}</div>
					<div style="float:right;"><a href="#" onclick="addMessage(${result.id});">Add Message</a></div>
				</div> 
				<div style="clear: both;position: relative;">
					<div style="float:left;width:70px;">
						<a href="/secure/friendPage.do?friendId=${result.id}"><img src="../${result.defaultFace.icon.path}"></a>
					</div>

					<div style="float: left;padding: 0 5px;" >
						<p id="friend-info${result.id}">Location:  ${result.primaryAddress.country}</p>
						<p id="friend-request-message${result.id}" style="display:none;"><textarea id="the-message-to-friend${result.id}" rows="3" cols="50" name="message" ></textarea></p>
					</div>			
					<div class="clear"></div>
					<div style="position:relative;">
						<div style="float:left;padding:right:10px;">
							<a onclick="addRequest('${result.id}');" href="#">Add to friend</a>	
						</div>
					</div>
					<div class="clear"></div>
				</div>
			</li>
		</c:forEach>
	</ul>

</div>