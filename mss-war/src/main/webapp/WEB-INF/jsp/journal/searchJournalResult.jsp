<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

<script type="text/javascript">
/*
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
*/

	$(document).ready(function() {
		$("#journal-search_text").Watermark("Search Advice");
	
		$('#journal-search_submit').click(function(){
			$('#loading').show();
				jQuery("#journal-search-form").validate( {
					submitHandler : function(form) {
						var method = $("input[@name='journal-method-filter']:checked").val();
						
						jQuery(form).ajaxSubmit( {
					       type: "post",  
					       url: "/secure/journal.do",
					       data : {
						       method: method,
						       searchString: $('#journal-search_text').val()
					       },
					       dataType : 'json',
							success: function(json) {
					    	   document.getElementById('journal-search-results').innerHTML = '';
								$.each(json.journals, function(i,journal){
									$('#journal-search-results').append('<sns:journal personId="' + ${person.id} + '" journalPersonId="' + journal.journalPersonId + '" journalId="' + journal.id + '" tags="' + journal.tags + '" category="'+ journal.category +'" thumbnail="'+ journal.faceIcon +'" fullName="' + journal.fullName + '" message="' + journal.message + '" enteredDate="' + journal.enteredDate + '"/>');
						        });			
								$('#loading').hide();			
							} 
						});
					}
				});	
			});
		});
	
</script>

<style type="text/css">


  .clear {
    clear:both;
    height:5px;
    overflow:hidden;
  }
  
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


<div style="background-color: #000; width:100%; height:100%;">	

<%@ include file="../journal/form.jsp" %>
		

	<div id="all-journals" style="display:none;position: relative;clear:both;">
		
		<div id="journal-title_info">
			<p style="padding-left:5px;color:#FFF;font-weight:#FFF;"><i>Search all available advice entries:</i></p>
			<div style="padding-left:5px;color:#FFF;font-weight:#FFF;">
				<form id="journal-search-form">
						<input id="journal-search_text" class="search_inactive" type="text" name="searchString" />
						<input type="image" id="journal-search_submit" value="SUBMIT" src="/images/search.png" />
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

			<div class="postings">
					<a href="#" id="journal-on-page">
						<span><img src="/images/addadvice.png" /></span>
					</a>	
					<ul id="journal-search-results" class="item-journals">
						<c:forEach items="${person.activeJournals}" var="journal">
							<c:set var="tags" value="" />
							<c:forEach items="${journal.tags}" var="tag"><c:set var="tags">${tag.tag},${tags}</c:set></c:forEach>
							<sns:ownjournals journalId="${journal.id}" tags="${tags}" category="${journal.category}" thumbnail="${journal.person.defaultFace.icon.path}" fullName="${journal.person.fullName}" message="${journal.entry}" enteredDate="${journal.enteredDate}" />
						</c:forEach>					
					</ul>
			</div>
		</div>
	</div>
</div>