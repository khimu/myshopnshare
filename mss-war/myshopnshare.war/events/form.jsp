<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>
		
<style type="text/css">
			.dynamic-friends-list LI {					
				list-style-type: none;
			}	
</style>

<%--$("[name]") Gets each element with an attribute of "name" --%>
  <script type="text/javascript">

  var eventUrl = '/secure/events.do?method=add';
 	
	$(document).ready(function() { 
		loadFriends();

		$("input[@name='visibility']").click(function() {
			var selected = $("input[@name='visibility']:checked").val();
			if(selected == 'PUBLIC' || selected == 'PRIVATE'){
				eventUrl = '/secure/events.do?method=addcustom';
			}else{
				eventUrl = '/secure/events.do?method=add';
			}
		});
			
		$('#submit-events').click(function(){
			 $.ajax({  
			       type: "post",  
			       url: eventUrl+"&"+$('#events-form').serialize(),
			       success: function(data){ 
						$('#center').html(data);
						
						// Cant use because of calendar
						// parent.tb_remove();
			       }
			 });			
		});

		$('#startDatePicker').datepick({
			dateFormat: 'mm/dd/yy', 
			showOn: 'both', 
			buttonImageOnly: true, 
			buttonImage: '/images/calendar-green.gif',
			onSelect: function(value, date, inst){
				$('#startDate').attr('value', value);
			},
			yearRange: '-100:+10'
		});
		
		$('#endDatePicker').datepick({
			dateFormat: 'mm/dd/yy', 
			showOn: 'both', 
			buttonImageOnly: true, 
			buttonImage: '/images/calendar-green.gif',
			onSelect: function(value, date, inst){
				$('#endDate').attr('value', value);
			},			
			yearRange: '-100:+10'
		});
	});
  </script>
  
  <style type="text/css">
  	#events-page {
  		list-style-type: none;
  	}
  	#events-form LI {
  		list-style-type: none;
  		padding: 5px;
  		clear: both;
  	}
  	#events-form label {
  		clear: both;
  	}
  	#events-form input {
  		clear: both;
  	}
  </style>

<div class="myshopnshare-header">
	<h3>Events Form</h3>
</div>
<div id="events-page" style="background-color: #CEDBF9;padding: 10px;">
		<form id="events-form" onsubmit="return false;">	
			<div style="position: relative;">
				<div style="float: left;">
					<div style="position: relative;">
						<div style="float: left; width: 200px; margin-right:10px;">
							<div><label for="eventtitle">Title</label></div>
							<div><input type="text" id="eventtitle" name="title" /></div>
						</div>
					</div>			
					<div style="position:relative;clear:both;">
						<div style="float:left;margin-right:10px;width:200px;">
							<div><label for="startTime">Start Time</label></div>
							<div><input type="text" id="startTime" name="startTime" /></div>
						</div>	
						<div style="float:left">
							<div><label for="endTime">End Time</label></div>
							<div><input type="text" id="endTime" name="endTime" /></div>
						</div>							
					</div>
					<div style="position:relative;clear:both;">
						<div style="float:left;margin-right:10px;width:200px;">
							<div><label for="startDate">Start Date</label></div>
							<div><input type="text" id="startDate" name="startDate" /></div>
							<div style="padding-top:5px;" id="startDatePicker"></div>
						</div>
						
						<div style="float:left">
							<div><label for="endDate">End Date</label></div>
							<div><input type="text" id="endDate" name="endDate" /></div>
							<div style="padding-top:5px;" id="endDatePicker"></div>
						</div>
					</div>
					<div style="position:relative;clear:both;padding:10px;">
						<div style="float:left;margin-right:10px;">
							<div><input type="radio" id="visibility" name="visibility" value="PRIVATE" />&nbsp;<label for="private">Private</label></div>
						</div>
						
						<div style="float:left">
							<div><input type="radio" id="visibility" name="visibility" value="PUBLIC" />&nbsp;<label for="public">Public</label></div>
						</div>
					</div>					
				</div>
				<div style="float: left; padding-lef: 2px;padding-left: 10px;">
					<h3>Friends</h3>
					<div class="dynamic-friends-list"></div>
				</div>
			</div>
			<div style="clear:both;"><label for="tags">Tags</label></div>
			<div style="clear:both;"><input type="text" id="tags" name="tags" size="65" /></div>
			<div style="clear:both;"><label for="description">Description</label></div>
			<div style="clear:both;"><textarea id="description" name="description" rows="5" cols="50"></textarea></div>
			<div style="clear:both;"><button name="button" id="submit-events">Submit</button></div>
		</form>
</div>