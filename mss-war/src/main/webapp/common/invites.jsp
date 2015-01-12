<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">
	jQuery(function($){
		$("#invites").Watermark("email@domainname.com, email@domainname.com, email@domainname.com");
	});

	function sendInvites(){
		Boxy.get('#send-invites').hide();
		
		$.ajax( {
			type :"post",
			url :"/secure/person.do",
			data: {method: 'invites', recipients: $('#invites').text()},
			success : function(data) {
				Boxy.alert('You have successfully invited ' + $('#invites').text());
		    }
		});
	}

	function showInvites(){
		new Boxy('#send-invites', {title: 'Invite friends to join SleekSwap', modal: true});
	}
</script>

<div id="send-invites" style="display:none;width:500px;">
	<div><h3>Invite Friends</h3></div>
	<textarea id="invites" name="recipients" rows="4" cols="50"></textarea>
	<div class="clear"></div>
	<a href="javascript: void(0);" onclick="sendInvites();">Submit</a>
</div>

