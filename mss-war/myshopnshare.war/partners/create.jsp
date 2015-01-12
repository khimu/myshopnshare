<%@ include file="/WEB-INF/jsp/includes.jsp" %>

	<script type="text/javascript">
	
	function ajaxFileUpload()
	{
		alert('function called');
		$("#loading")
		.ajaxStart(function(){
			$(this).show();
		})
		.ajaxComplete(function(){
			$(this).hide();
		});

		$.ajaxFileUpload({
				url:'/secure/face.do?method=upload',
				secureuri:false,
				fileElementId:'fileToUpload',
				dataType: 'json',
				success: function (face, status){
					$('#new-profile-face').append('<sns:imagerecord caption="'+ face.caption +'" description="'+ face.description +'" thumbnail="'+ face.thumbnail +'" original="'+ face.original +'" primaryKey="' + face.id + '" defaultPhoto="' + face.defaultFace + '" deleteURL="/secure/face.do?method=" idType="face"  />');
					Boxy.get('#profile-face-page').hide();			
					if(typeof(face.error) != 'undefined'){
						if(face.error != ''){
							alert(' data.error '+face.error);
						}else{
							alert(' data.msg ' + face.msg);
						}
					}
				},
				error: function (data, status, e){
					alert(' e ' + e);
					Boxy.get('#profile-face-page').hide();
				}
			});
		
		return false;
	}
	</script>


<div id="events-page">
	<form name="events-form" action="/secure/events.do" method="POST">
		<table cellpadding="0" cellspacing="0" class="tableForm">	
			<thead>
				<tr>
					<th>Enter Event</th>
				</tr>
			</thead>
			<tbody>	
				<tr>
					<td><label for="event-title">Title</label></td>
					<td><input type="text" name="events.title"></td>			
				</tr>
				<tr>
					<td><label for="events-description">Description</label></td>
					<td><input type="text" name="events.description"></td>			
				</tr>
				<tr>
					<td><label for="event-startDate">Start Date</label></td>
					<td><input type="text" name="events.startDate"></td>			
				</tr>
				<tr>
					<td><label for="event-endDate">End Date</label></td>
					<td><input type="text" name="events.endDate"></td>			
				</tr>

			</tbody>
			<tfoot>
				<tr>
					<td colspan="2"><input type="submit" name="_submit" value="Submit" /></td>
				</tr>
			</tfoot>
		</table>
	</form>

	<img id="loading" src="/images/loading.gif" style="display:none;">
</div>