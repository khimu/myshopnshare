<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

<script type="text/javascript">
	
	$(document).ready(function(){
		$('#your-decision input').click(function(){
			var yourchoice = $("input[@name='yourchoice']:checked").val();

			Boxy.confirm("Please confirm you want to " + yourchoice, function() {
					 $.ajax({  
					       type: "post",  
					       url: "/secure/events.do",
					       data: {method: yourchoice, eventId: ${event.id}, personId: '${person.id}'},
					       success: function(data){  
								$('#center').html(data);
					       }
					 });	
			}, {title: 'Confirm Delete'});
		    return false;
		});
	});
</script>

<style type="text/css">
	#this-event-page {
		width: 790px;
		text-align: center;
		vertical-align: middle;
		padding: 5px;
		height: 1%;
		padding:5px;
	}
	.event-guest-list {
		margin: 10px;
		height: 1%;
		padding:5px;
	}

	.event-guest-list img {
		float:left;
		padding: 5px;
	}	
			
	#your-decision {
		height: 1%;
		margin: 10px;
		padding:5px;
	}
	#host-element {
		margin: 10px;
		height: 1%;
		padding:5px;
	}	
	#event-description {
		background-color: #66E160;
		margin: 10px;
		height: 1%;
		padding:5px;
	}
	#start-end-time {
		background-color: #66E160;
		margin: 10px;
		height: 1%;
		padding:5px;
	}
	#center {
		width: auto;
	}
	#this-event-page h2 {
		border-bottom: 1px dotted #fff;
		height: 1%;
		padding:5px;
	}
</style>

<div id="this-event-page" class="myshopnshare-content">
	<input type="hidden" id="event-page-event-id" name="eventId" value="${event.id}" />
	
	<h2>${event.title}</h2>
	
	<div class="spacer"></div>

	<div id="host-element">
		<h3>Host</h3>
		<h4>${event.person.fullName}</h4>
	</div>
	
	<div id="start-end-time">
		<h3>Start Time</h3>
		<div><fmt:formatDate pattern="MM/dd/yyyy" value="${event.startDate}" /> ${event.startTime}</div>
		<h3>End Time</h3>
		<div><fmt:formatDate pattern="MM/dd/yyyy" value="${event.endDate}" /> ${event.endTime}</div>
	</div>
	
	<div id="event-description">
		<h3>Description</h3>
		<div>${event.description}</div>
	</div>
	
	<div id="your-decision">
	You are:  &nbsp; &nbsp;&nbsp; &nbsp;
	&nbsp; &nbsp;<input type="radio" name="yourchoice" value="attending" />&nbsp; &nbsp; Attending
	&nbsp; &nbsp;<input type="radio" name="yourchoice" value="decline" />&nbsp; &nbsp; Decline
	&nbsp; &nbsp;<input type="radio" name="yourchoice" value="considering" />&nbsp; &nbsp; Considering
	</div>
	
	<div class="event-guest-list">
	<div>Guest List</div>
		<div style="background-color:#fff;">
			<c:forEach items="${event.guests}" var="guest">
				<a href="/secure/friendPage.do?friendId=${guest.person.id}"><img src="../${guest.person.defaultFace.icon.path}" title="${guest.person.fullName}"></a>
			</c:forEach>
		</div>
	</div>
	
	<div class="clear"></div>
	
	<div class="event-guest-list">
	<div>Attending</div>
			<div style="background-color:#fff;">
				<c:forEach items="${event.accept}" var="accept">
					<a href="/secure/friendPage.do?friendId=${accept.person.id}"><img src="../${accept.person.defaultFace.icon.path}" title="${accept.person.fullName}"></a>
				</c:forEach>
		</div>
	</div>
	
	<div class="clear"></div>

	<div class="event-guest-list">
	<div>Decline</div>
		<div style="background-color:#fff;">
			<c:forEach items="${event.decline}" var="decline">
				<a href="/secure/friendPage.do?friendId=${decline.person.id}"><img src="../${decline.person.defaultFace.icon.path}" title="${decline.person.fullName}"></a>
			</c:forEach>
		</div>
	</div>
	
	<div class="clear"></div>
	
	<div class="event-guest-list">
	<div>Considering</div>
		<div style="background-color:#fff;">
			<c:forEach items="${event.considering}" var="considering">
				<a href="/secure/friendPage.do?friendId=${considering.person.id}"><img src="../${considering.person.defaultFace.icon.path}" title="${considering.person.fullName}"></a>
			</c:forEach>	
		</div>
	</div>
	
	<div class="spacer"></div>

</div>
	
	<%@ include file="comments.jsp" %>	