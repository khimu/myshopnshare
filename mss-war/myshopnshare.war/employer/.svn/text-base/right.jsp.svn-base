<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">

	$(function()
			{
				$('#events-calendar').datepicker({dateFormat: 'mm/dd/yy', changeMonth: true, changeYear: true, highlightWeek: true,
					onSelect: function(){
					 $.ajax({  
					       type: "POST",  
					       url: "/secure/events.do",
					       data: {method: 'view', startDate: $('#events-calendar').val(), personId: '${person.id}'},
					       success: function(data){  
								$('#center').html(data);
								$('#events-header').html("Events on " + $('#events-calendar').val());
					       }
					     });
					}
				});
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

<style type="text/css">
	#right-column-header1{
		background-image: url(/images/clanak.gif);
		background-repeat: repeat;	
		position: relative;
		clear:both;
		margin-top:5px;
	}
	#right-column-header1 h3{
		padding:5px;
		color: #055;
		font-size:12pt
	}
</style>

<div style="color:#FFF;">	
	<div id="right-column-header1">
		<h3>Events Calendar</h3>
	</div>
	<%@ include file="../events/form.jsp" %>
	<table>
		<%--<tr><td><jsp:include page="../ads/ads.jsp"></jsp:include></td>
		</tr>--%>
		<tr><td><a href="#" id="events-form-popup"> [New Event] </a> <a href="#" id="view-all-events"> [View All] </a></td></tr>
		<tr><td><div id="events-calendar"> </div></td></tr>
		<tbody style="padding:5px;border: 1px solid #FFF;background-color:#222222;">
		<tr><td>Age: <p> ${person.age}</p></td></tr>
		<tr><td>Birthday: <p> <fmt:formatDate value="${person.birthday}" pattern="MM/dd/yyyy" /></p></td></tr>
		<tr><td>AIM: ${person.profile.aim}</td></tr>
		<tr><td>Expertise: <p> ${person.profile.expertise}</p></td></tr>
		<tr><td>Language: <p> ${person.profile.language}</p></td></tr>
		</tbody>
	</table>
<jsp:include page="../ads/ads.jsp"></jsp:include>
</div>




		

