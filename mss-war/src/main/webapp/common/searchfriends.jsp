<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">
	$("#search_friends_text").Watermark("Search Friends");
	
	$(document).ready(function(){
			$('#search_friends_submit').click(function(){
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

<%-- Making an ajax call to retrieve list of people matching search criteria --%>

        <%-- Search --%>
        <div id="friend-design-search" class="noprint">
            <form onsubmit="return false;">
                <fieldset><legend>Search</legend>
                    <label><span class="noscreen">Find:</span>
                    <span id="friend-design-search-input-out"><input type="text" name="searchString" id="search_friends_text"  /></span></label>
                    <input type="image" src="/images/user_search-sm.png" id="search_friends_submit" value="OK" />
                </fieldset>
            </form>
        </div> <%-- /search --%>  