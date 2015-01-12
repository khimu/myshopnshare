<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

<script type="text/javascript">

	$(document).ready(function(){
		$('#event-search_submit').click(function(){
			var method = $("#event-search-by :selected").val();
			
			// get date
			var searchString = $('#event-search_text').val();
			var startDate = $('#search-event-start-date').val();
			$.ajax( {
				type :"POST",
				url :"/public/searchEvents.do",
				data : {
					method :method,
					personId: '${person.id}',
					searchString: searchString,
					startDate: startDate
				},
				dataType :'html',
				success : function(data) {
					$('#events-search-results').html(data);
				}
			});
		});

		$('#search-event-start-date').datepick({
			dateFormat: 'mm/dd/yy', 
			showOn: 'both', 
			buttonImageOnly: true, 
			buttonImage: '/images/calendar-green.gif',
			yearRange: '-10:+10'
		});	
	});

</script>
			<div>
				<div class="left-right"><input id="event-search_text" type="text" name="searchString" /></div> 
				&nbsp; &nbsp; 
				<a href="javascript: void(0);" id="event-search_submit" alt="[Submit]" title="Search entire site for events filtered by selected category"><img src="/images/search.png" /></a>
			</div>
			<div class="clear"></div>
			<div>
				<div class="left-right">Filter By: </div>
				&nbsp; &nbsp; 

					<select id="event-search-by" name="eventsfilter">
						<option value="afriends" selected="selected" >--</option>
						<option value="afriends" selected="selected" >OWN</option>
						<option value="all" > ALL </option>
						<option value="all" > PUBLIC </option>
						<option value="friends" > FRIENDS </option>
					</select>				
		
			</div>
			<div class="clear"></div>
			<label for="thestartate">Start Date</label> &nbsp; &nbsp; <input type="text" id="search-event-start-date" name="startDate" />
