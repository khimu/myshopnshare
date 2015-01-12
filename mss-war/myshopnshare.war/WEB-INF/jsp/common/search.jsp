<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<script type="text/javascript" src="/js/bsn.autosuggest.js"></script>

<script type="text/javascript">
/*
var options = {
		script: "pathToScript.php?",
		varname: "variableName",
		json: true,
		maxresults: 35
	};
	var as = new bsn.AutoSuggest('idOfTextfield', options);
	//script: function (input) { return "test.php?input="+input+"&testid="+document.getElementById('testid').value; }
*/
/* Json result for above
 * 
 { results: [
         	{ id: "1", value: "Foobar", info: "Cheshire" },
         	{ id: "2", value: "Foobarfly", info: "Shropshire" },
         	{ id: "3", value: "Foobarnacle", info: "Essex" }
         ] }
          
 */
 /** Init autosuggest on Search Input **/
 /*
 jQuery(function() {

 	//==================== Search With all plugins =================================================
 	// Unbind form submit
 	$('.home_searchEngine').bind('submit', function() {return false;} ) ;
 	
 	// Set autosuggest options with all plugins activated
 	var options = {
 		script:"AjaxSearch/_doAjaxSearch.action.php?json=true&limit=8&",
 		varname:"input",
 		json:true,						// Returned response type
 		shownoresults:true,				// If disable, display nothing if no results
 		noresults:"No Results",			// String displayed when no results
 		maxresults:8,					// Max num results displayed
 		cache:false,					// To enable cache
 		minchars:2,						// Start AJAX request with at leat 2 chars
 		timeout:100000,					// AutoHide in XX ms
 		callback: function (obj) { 		// Callback after click or selection
 			// For example use :
 						
 			// Build HTML
 			var html = "ID : " + obj.id + "<br>Main Text : " + obj.value + "<br>Info : " + obj.info;
 			$('#input_search_all_response').html(html).show() ;
 			
 			// => TO submit form (general use)
 			//$('#search_all_value').val(obj.id); 
 			//$('#form_search_country').submit(); 
 		}
 	};
 	// Init autosuggest
 	var as_json = new bsn.AutoSuggest('input_search_all', options);
 	
 	// Display a little watermak	
 	$("#input_search_all").Watermark("ex : France, FRA, Paris...");
 }); 
 */
</script>

	<div id="title_info">
	<p><i>Share your shopping experience</i></p>
	<p><i>Looking for something special:</i></p>
	<form id="search" method="POST" action="/secure/search.do">
		<p>	
			<input id="search_text" class="search_inactive" type="text"
				value="Search Images" name="searchString" />
			<button id="search_submit" type="submit">SEARCH</button>
			<script type="text/javascript" src="/js/shopnshare/search.js"
				defer="defer"></script>
		</p>
	</form>	
	</div>
