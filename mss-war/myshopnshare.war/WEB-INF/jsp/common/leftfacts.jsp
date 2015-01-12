<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">

	$(function()
			{
				$('#view-all-events').click(function(){
					 $.ajax({  
					       type: "post",  
					       url: "/secure/events.do",
					       data: {method: 'all', personId: '${person.id}'},
					       success: function(data){  
								$('#center').html(data);
					       }
					 });
				});
			});		
</script>
		
		<div class="profile-strip"></div>
		<div class="profile-info">Age: <p> ${person.age}</p></div>
		<div class="profile-strip"></div>
		<div class="profile-info">Birthday: <p> <fmt:formatDate value="${person.birthday}" pattern="MM/dd/yyyy" /></p></div>
		<div class="profile-strip"></div>
		<div class="profile-info">AIM: <a href="aim:GoIM?screenname=screenname"><img src="http://api.oscar.aol.com/presence/icon?k=mi15Xbwp687KXwRc&t=${person.profile.aim}"/>${person.profile.aim}</a></div>
		<div class="profile-strip"></div>
		<div class="profile-info">Expertise: <p> ${person.profile.expertise}</p></div>
		<div class="profile-strip"></div>
		<div class="profile-info">Language: <p> ${person.profile.language}</p></div>
