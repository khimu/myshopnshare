<%@ include file="/WEB-INF/jsp/includes.jsp" %>

	<script type="text/javascript">
	
	function ajaxFileUpload()
	{
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
					$('#new-profile-face').append('<sns:imagerecord caption="'+ face.caption +'" description="'+ face.description +'" thumbnail="'+ face.glimpse +'" icon="'+ face.icon +'" primaryKey="' + face.id + '" defaultPhoto="' + face.defaultFace + '" deleteURL="/secure/face.do?method=" idType="face"  />');
					
					Boxy.get('#profile-face-page').hide();			
					if(typeof(face.error) != 'undefined'){
						if(face.error != ''){
							alert(' data.error '+face.error);
						}else{
							alert(' data.msg ' + face.msg);
						}
					}
					$("a[rel^='prettyPhoto']").prettyPhoto();
				},
				error: function (data, status, e){
					alert(' e ' + e);
					Boxy.get('#profile-face-page').hide();
				}
			});
		
		return false;
	}
	</script>


<div id="profile-face-page">
	<form name="profile-face-form" action="" method="POST" enctype="multipart/form-data">
		<table cellpadding="0" cellspacing="0" class="tableForm">	
			<thead>
				<tr>
					<th>Please select your image</th>
				</tr>
			</thead>
			<tbody>	
				<tr>
					<td><input id="fileToUpload" type="file" size="45" name="fileToUpload" class="input"></td>			
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td><button class="button" id="buttonUpload" onclick="return ajaxFileUpload();">Upload</button></td>
				</tr>
			</tfoot>
		</table>
	</form>

	<img id="loading" src="/images/loading.gif" style="display:none;">
</div>