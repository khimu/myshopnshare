<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<style type="text/css">
	#headline {
		padding: 2px;
	}
	
	#headline input {
	}

</style>

<script type="text/javascript">
	$("#headline_text").Watermark("${person.fullName} is ");

	$(document).ready(function(){
		$('#headline_submit').click(function(){
			var headline = $('#headline_text').val();
			 $.ajax({  
			       type: "post",  
			       url: "/secure/headline.do",
			       data: {method: 'add', headline: headline},
			       dataType: 'json', 
			       success: function(json){  
				       $('#latest').html('${person.fullName} is ' + json.headline);	   
				       $("#headline_text").attr('value', '');
			       } 
			     });
		     return false;
		});
	});
</script>


        <%-- 
        <div id="headline-design-search" class="noprint">
            <form onsubmit="return false;">
                <fieldset><legend>Search</legend>
                    <label><span class="noscreen">Find:</span>
                    <span id="headline-search-input-out"><input type="text" name="searchString" id="headline_text"  /></span></label>
                    <input type="image" src="/images/buttons/gored.png" id="headline_submit" value="OK" />
                </fieldset>
            </form>
        </div> 
        --%>  

        <div id="headline-design-search" class="noprint">
            <form onsubmit="return false;">
                    <span><input type="text" name="searchString" id="headline_text" size="50" /></span>
                    <input type="image" src="/images/go.png" id="headline_submit" value="OK" />
            </form>
        </div> 