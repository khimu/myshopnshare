<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns"%>

<script type="text/javascript">
	function changeDate(value, date, inst){
		var date = value.split("/");
		$('#month').attr('value', date[0]);
		$('#day').attr('value', date[1]);
		$('#year').attr('value', date[2]);
	}
	
	$(document).ready( function() {
		$('#month').datepick({
			dateFormat: 'mm/dd/yy', 
			onClose: changeDate,
			yearRange: '-100:+10'
		});
	
		$('#day').datepick({
			dateFormat: 'mm/dd/yy', 
			onClose: changeDate,
			yearRange: '-100:+10'
		});
	
		$('#year').datepick({
			dateFormat: 'mm/dd/yy', 
			showOn: 'both', 
			buttonImageOnly: true, 
			buttonImage: '/images/calendar-green.gif',
			onClose: changeDate,
			yearRange: '-100:+10'
		});
	});
	
</script>