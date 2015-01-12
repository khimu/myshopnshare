<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">
	$("#search_friends_text").Watermark("Search Friends");
	
	$(document).ready(function(){
			$('#search_friends').submit(function(){
				var searchString = $('#search_friends_text').val();
				 $.ajax({  
				       type: "post",  
				       url: "/secure/potentialFriends.do",
				       data: {method: 'search', searchString: searchString},
				       success: function(data){  
					       $('#center').html(data);	   
					       $("#search_friends_text").Watermark("Search Friends");
				       } 
				     });
			     return false;
			});
	});
</script>

<style type="text/css">
	#search_friends input {
		font-size: 9px;
		color: #000;
		padding: 4px;
		width: 150px;
	}
	
	#search_friends {
		font-size: 9px;
		padding: 2px;
	}	
</style>

<%-- Making an ajax call to retrieve list of people matching search criteria --%>

<form id="search_friends">
	<input id="search_friends_text" type="text" name="searchString" style="float: left;" />
	<button id="search_friends_submit" type="submit" style="padding:0px; vertical-align:top;"></button>
</form>