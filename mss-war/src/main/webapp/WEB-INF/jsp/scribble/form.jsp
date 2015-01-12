<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">
	$(document).ready(function(){
		$('#submit-scribble-button').click(function(){
			jQuery('#add-scribble-form').validate( {
				submitHandler : function(form) {
					jQuery(form).ajaxSubmit( {
						url: '/secure/scribble.do', 
						type: 'post',
						data: {method: 'scribble', personId: '${person.id}'},
						dataType: 'json',
						success: function(scribble) { 
							Boxy.get('#scribble-form').hide();
							//$('#scribble-content').append(scribble.category + ' ( ' + scribble.tags + ' )');
							$('#scribble-content').append('<sns:scribble thumbnail="'+ scribble.faceIcon +'" fullName="' + scribble.fullName + '" message="' + scribble.message + '" enteredDate="' + scribble.enteredDate + '"/>');
						}
					});
				}
			});	
		});

		$("#message").Watermark("Scribble a note");
	});
</script>

<div id="scribble-form" style="display: none;">
	<form id="add-scribble-form">
		<input type="hidden" name="id" value="" />
		<div>
			<div><label for="message">Message : </label></div>
			<div><textarea name="message" cols="30" rows="3"></textarea></div>
		</div>
		<button name="button" id="submit-scribble-button" type="submit">Enter</button>
		<button name="button" id="reset-scribble-button" type="reset">Reset</button>
	</form>
</div>