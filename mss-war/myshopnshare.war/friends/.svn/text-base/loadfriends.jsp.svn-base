<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

<style type="text/css">
	.enlarge {
		height: 150px;
		width: 150px;
	}
	
	.shrink {
		height: 20px;
		width: 20px;
	}
</style>

	<script type="text/javascript">		
		function getFriends(){
			 $.ajax({  
			       type: 'post',  
			       url: '/secure/manageFriends.do',
			       data: {method: 'friendslist', personId: '${person.id}'},
			       dataType: 'json',
			       success: function(json){ 
			    	   $('.friends').html('');
			    	   $.each(json.friends, function(i,friend){
							$('.friends').append('<sns:friendslist headline="' + friend.friendProfile.headline + '" glimpse="'+friend.friendProfile.glimpse + '" icon="' + friend.friendProfile.thumbnail + '" primaryKey="' + friend.friendProfile.friendId +'" fullName="' + friend.friendProfile.fullName + '" />');
						}); 
			    		    			       
				       	$("a[rel^='prettyPhoto']").prettyPhoto();						           				
			       }
			     });
		}

	    $(function () { 
			getFriends();
	  	});  	  
	</script>
	
	<div id="friends-list">
		<%@ include file="friends.jsp" %>
	</div>

