<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

<script type="text/javascript">
	function getFriends(){
		 $.ajax({  
		       type: 'post',  
		       url: '/secure/manageFriends.do',
		       data: {method: 'friendslist', personId: '${person.id}'},
		       dataType: 'json',
		       success: function(json){ 
		    	   $.each(json.friends, function(i,friend){
		    		   $('#friends-box'+' .the-friends').append('<sns:friendslist headline="' + friend.friendProfile.headline + '" glimpse="'+friend.friendProfile.glimpse + '" icon="' + friend.friendProfile.icon + '" primaryKey="' + friend.friendProfile.friendId +'" fullName="' + friend.friendProfile.fullName + '" />');
					}); 
		    		    		
					$('.the-friends').cycle({ 
					    fx:     'fade', 
					    timeout: 3000, 
					    pause:   1
					 }); 		

					$("a[rel^='prettyPhoto']").prettyPhoto();				       							           				
		       }
		     });
	}

	$(document).ready(function(){
		getFriends();	
	});

</script>

<style type="text/css">
	.the-friends {
	}
	#friends-frame {		
		height: 200px;
		margin-bottom: 10px;
	}
		
	#friends-box {
		height: 200px;
	}
</style>

<div class="column-content">	
	<div id="friends-frame">
		<div id="friends-box" style="position: relative;text-align: center;">
			<div class="the-friends" style="float: left;width: 150px;height:150px;text-align;center;">
			</div>
			<span style="float: none;clear: both;text-align: left;vertical-align: bottom;"></span>
		</div>
	</div>
</div>	
	