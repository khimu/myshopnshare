<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<script type="text/javascript">
	function changeVisibility(){
		$('#change-default-permission').show();
		if($('#change-default').html() == '[+]'){
			$('#change-default').html('[-]');
			$('#change-default-permission').show();
		}else{
			$('#change-default').html('[+]');
			$('#change-default-permission').hide();
		}
	}
</script>

<div class="clear"></div>	
<p>Setting permission to private will disable all users from viewing your activities.</p>
<div id="default-group">
	<h3>${person.defaultVisibility.visibility}</h3>
	<a href="#" onclick="changeVisibility();"><span id="change-default">[+]</span></a> Change
	<div id="change-default-permission" style="display: none;">
			<form>
				<input type="radio" name="visibility" value="PUBLIC" /> PUBLIC
				<input type="radio" name="visibility" value="PRIVATE" /> PRIVATE
			</form>
	</div>
</div>
