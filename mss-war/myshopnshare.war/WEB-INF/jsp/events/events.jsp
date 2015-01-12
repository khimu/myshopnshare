<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

<%--$("[name]") Gets each element with an attribute of "name" --%>
<%--$("[name]") Gets each element with an attribute of "name" --%>
  <script type="text/javascript">
	function deleteEvent(eventId){
		Boxy.confirm("Please confirm delete:", function() {

			 $.ajax({  
			       type: "post",
			       url: "/secure/events.do",
			       data: {method: 'delete', eventId: eventId},
			       success: function(data){
						$('#center').html(data);
			       }
			 });
		}, {title: 'Confirm Delete'});
	    return false;

	}

	function editEvent(eventId){
			 $.ajax({  
			       type: "post",  
			       url: "/secure/events.do",
			       data: {method: 'editform', eventId: eventId},
			       success: function(data){  
						$('#center').html(data);
			       }
			 });
	}
	

</script> 
	<div id="events-header" class="myshopnshare-header"><h3>Events</h3></div>
	
	<%@ include file="search.jsp" %>
	
	<div id="events-search-results">
	<%@ include file="results.jsp" %>
	</div>