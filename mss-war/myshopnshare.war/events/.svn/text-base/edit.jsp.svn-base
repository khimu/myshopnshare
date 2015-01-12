<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>
		
<style type="text/css">
			.dynamic-friends-list LI {					
				list-style-type: none;
			}	
</style>

<%--$("[name]") Gets each element with an attribute of "name" --%>
  <script type="text/javascript">
 	
	$(document).ready(function() { 
				
		$('#edit-submit-events').click(function(){
			 $.ajax({  
			       type: "post",  
			       url: "/secure/events.do?method=edit&"+$('#edit-events-form').serialize(),
			       success: function(data){ 
						$('#center').html(data);
			       }
			 });			
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
<div id="edit-events-page" style="background-color: #CEDBF9;padding: 10px;">
		<form id="edit-events-form" onsubmit="return false;">	
			<input type="hidden" name="id" value="${event.id}" />
			<div style="position: relative;">
				<div style="float: left;">
					<div style="position: relative;">
						<div style="float: left; width: 200px; margin-right:10px;">
							<div><label for="eventtitle">Title</label></div>
							<div><input type="text" id="eventtitle" name="title" value="${event.title}" /></div>
						</div>
					</div>			
					<div style="position:relative;clear:both;">
						<div style="float:left;margin-right:10px;width:200px;">
							<div><label for="startTime">Start Time</label></div>
							<div><input type="text" id="startTime" name="startTime" value="${event.startTime}"/></div>
						</div>	
						<div style="float:left">
							<div><label for="endTime">End Time</label></div>
							<div><input type="text" id="endTime" name="endTime" value="${event.endTime}"/></div>
						</div>							
					</div>
					<div style="position:relative;clear:both;">
						<c:set var="startDate">
							<fmt:formatDate pattern="MM/dd/yyyy" value="${event.startDate}" />
						</c:set>					
						<div style="float:left;margin-right:10px;width:200px;">
							<div><label for="startDate">Start Date</label></div>
							<div><input type="hidden" id="startDate" name="startDate"  value="${startDate}"/></div>
							<div style="padding-top:5px;" id="startDatePicker"></div>
						</div>
						
						<c:set var="endDate">
							<fmt:formatDate pattern="MM/dd/yyyy" value="${event.endDate}" />
						</c:set>
						
						<div style="float:left">
							<div><label for="endDate">End Date</label></div>
							<div><input type="hidden" id="endDate" name="endDate"  value="${endDate}"/></div>
							<div style="padding-top:5px;" id="endDatePicker"></div>
						</div>
					</div>
					<div style="position:relative;clear:both;">
						<div style="float:left;margin-right:10px;width:200px;">
							<div><input type="radio" id="visibility" name="visibility" value="" /></div>
							<div><label for="default">Default</label></div>
						</div>
						<div style="float:left;margin-right:10px;width:200px;">
							<div><input type="radio" id="visibility" name="visibility" value="private" /></div>
							<div><label for="private">Private</label></div>
						</div>
						
						<div style="float:left">
							<div><label for="public">Public</label></div>
							<div><input type="radio" id="visibility" name="visibility" value="public" /></div>
							<div style="padding-top:5px;" id="public"></div>
						</div>
					</div>						
				</div>
				<div style="float: left; border: 1px solid #fff;padding: 2px;">
					<h3>Guest List</h3>
					<div class="dynamic-friends-list">
						<ul>
							<c:forEach items="${guests}" var="guest">
								<li style="font-weight:bold;">${guest.firstName} &nbsp;&nbsp;<input type="checkbox" name="removeFriendIds" value="${guest.id}" />&nbsp;&nbsp;&nbsp;&nbsp;[REMOVE]</li>
							</c:forEach>
						</ul>				
					</div>
					<h3>Add To List</h3>
					<div class="dynamic-friends-list">
						<ul>
							<c:forEach items="${friends}" var="friend">
								<li style="font-weight:bold;"><input type="checkbox" name="friendIds" value="${friend.id}" />${friend.firstName}</li>
							</c:forEach>
						</ul>			
					</div>
				</div>
			</div>
			<div style="clear:both;"><label for="tags">Tags</label></div>
			<c:set var="tags" value="" />
			<c:forEach items="${event.tags}" var="tag">
				<c:set var="tags">
					${tag.tag}, ${tags}
				</c:set>
			</c:forEach>
			${tags}
			<div style="clear:both;"><input type="text" id="tags" name="tags" size="92" /></div>
			<div style="clear:both;"><label for="description">Description</label></div>
			<div style="clear:both;"><textarea id="description" name="description" rows="5" cols="70">${event.description}</textarea></div>
			<div style="clear:both;"><button name="button" id="edit-submit-events">Submit</button></div>
		</form>
</div>