<%@ include file="/WEB-INF/jsp/includes.jsp"%>

	<style type="text/css">
		#friend-profile LI {
			list-style-type: none;
		}
		
		#friend-profile {
			float: none;
			clear: both;
			
		}	
	</style>
	
<div id="friend-profile">
	<ul>
		<li>${person.primaryEmailAddress.email}</li>
	</ul>
</div>