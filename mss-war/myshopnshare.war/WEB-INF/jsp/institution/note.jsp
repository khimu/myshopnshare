<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">
	var url = '/secure/item.do?method=collections';

	$(document).ready(function(){	
		$('#note-submit').click(function(){
		$.ajax({ 
			type:"POST", 
			url: '/secure/home.do?method=note', 
			data: {note: $('#note').val()},
			datatype:"text", 
			complete: function( data ) { 
				$("news-box").html( data );
				}
			});		
		});
	});
	
</script>

<div>
	<form method="post">
	Note: <textarea id="note" name="note" rows="5" cols="60">${person.note}</textarea>
	<input id="note-submit" type="submit" value="Submit"/>
	</form>
	${person.note}
</div>