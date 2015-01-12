<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">

$(document).ready(function() { 
	$.ajax( {
		type :"post",
		url :"/secure/journal.do",
		data : {
			method :'view',
			personId : '${person.id}'
		},
		dataType :'json',
		success : function(json) {
			
			$.each(json.journals, function(i,journal){
				$('#journal-search-results').append('<sns:journal personId="' + ${person.id} + '" journalPersonId="' + journal.journalPersonId + '" journalId="' + journal.id + '" tags="' + journal.tags + '" category="'+ journal.category +'" thumbnail="'+ journal.faceIcon +'" fullName="' + journal.fullName + '" message="' + journal.message + '" enteredDate="' + journal.enteredDate + '"/>');
	         });
		}
	});	
});

	$(document).ready(function() {
		$("#journal-search_text").Watermark("Search Journals");
	
		$('#journal-search_submit').click(function(){
				jQuery("#journal-search-form").validate( {
					submitHandler : function(form) {
						var method = $("input[@name='journal-method-filter']:checked").val();

						jQuery(form).ajaxSubmit( {
					       type: "post",  
					       url: "/secure/journal.do",
					       data : {
						       method: method,
						       personId: '${person.id}',
							   searchString: $('#journal-search_text').val()
					       },
					       dataType : 'json',
							success: function(json) {
					    	   document.getElementById('journal-search-results').innerHTML = '';
								$.each(json.journals, function(i,journal){
									$('#journal-search-results').append('<sns:journal personId="' + ${person.id} + '" journalPersonId="' + journal.journalPersonId + '" journalId="' + journal.id + '" tags="' + journal.tags + '" category="'+ journal.category +'" thumbnail="'+ journal.faceIcon +'" fullName="' + journal.fullName + '" message="' + journal.message + '" enteredDate="' + journal.enteredDate + '"/>');
						        });						
							} 
						});
					}
				});	
			});
		});
	
</script>

	<style type="text/css">
	
		#journal-title_info {
			clear: both;
			width: 100%;
		}
		
		#journal-radio-buttons {
			clear: both;
			padding: 2px;
		}		
			#journal-description img:hover {
				border: 3px solid red;
			}
			#search-journals {
				padding: 5px;
			}
	</style>

<script type="text/javascript">
	$(document).ready(function(){
		$('#journal-on-page').click(function(){
			new Boxy('#journal-form', {title: 'Advice Journal', modal: true, fixed: false}); 
		});		
	});
</script>

<div id="all-journals" style="display:none;position: relative;clear:both;">
		
		<div id="journal-title_info">
			<p style="padding-left:5px;"><i>Search all available advice entries:</i></p>
			<div style="padding-left:5px;">
				<form id="journal-search-form">
						<input id="journal-search_text" class="search_inactive" type="text" name="searchString" />
						<button id="journal-search_submit" type="submit" ><img src="/images/search.png"/></button>
				</form>
			</div>
			
			<%-- Search Results Page --%>
			<div id="journal-radio-buttons">
					<input id="items-world" type="radio" name="journal-method-filter" value="world" checked="checked" />
					<span>World</span>
					<input id="items-friends" type="radio" name="journal-method-filter" value="friends"/>
					<span>Friends</span>
					<input id="items-self" type="radio" name="journal-method-filter" value="view"/>
					<span>Personnal</span>
			</div>

			<div style="float: left;width:500px; padding: 5px;margin: 5px;">			
					<div id="journal-search-results">
					</div>
			</div>
		</div>
	</div>