<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">
	$(document).ready(function(){
		$('#submit-journal-button').click(function(){
			jQuery('#add-journal-form').validate( {
				submitHandler : function(form) {
					jQuery(form).ajaxSubmit( {
						url: '/secure/journal.do', 
						type: 'post',
						data: {method: 'journal'},
						dataType: 'json',
						success: function(journal) { 
							Boxy.get('#journal-form').hide();
							$('#journal-search-results').append('<sns:journal personId="' + ${person.id} + '" journalPersonId="' + journal.journalPersonId + '" journalId="' + journal.id + '" tags="' + journal.tags + '" category="'+ journal.category +'" thumbnail="'+ journal.faceIcon +'" fullName="' + journal.fullName + '" message="' + journal.message + '" enteredDate="' + journal.enteredDate + '"/>');
						}
					});
				}
			});	
		});

		$("#message").Watermark("Scribble a note");
	});
</script>

<div id="journal-form" style="display: none;">
	<form id="add-journal-form">
		<input type="hidden" name="id" value="" />
		<div>
			<div><label for="subject">Subject: </label></div>
			<div><input type="text" id="category" name="category"/></div>
			<div><label for="subject">Tags: </label></div>
			<div><input type="text" id="tags" name="tags"/></div>
			<div><label for="entry">Entry : </label></div>
			<div><textarea name="entry" cols="30" rows="3"></textarea></div>
		</div>
		<button name="button" id="submit-journal-button" type="submit">Enter</button>
		<button name="button" id="reset-journal-button" type="reset">Reset</button>
	</form>
</div>