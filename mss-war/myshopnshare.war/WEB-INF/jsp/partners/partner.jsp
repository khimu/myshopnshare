<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

<%--$("[name]") Gets each element with an attribute of "name" --%>

<script type="text/javascript" src="/js/ui.core.js"></script>
<script type="text/javascript" src="/js/ui.tabs.js"></script>
<script type="text/javascript" src="/js/jquery.tablesorter.min.js"></script>

<link href="/css/tablesorter.css" type="text/css" rel="stylesheet"/>
<link type="text/css" href="/css/flora.tabs.css" rel="stylesheet" />

  <script type="text/javascript">
  	function edit_record(record_id, idType){
		var record = '#edit_'+idType+'_'+record_id;
		var edit_url = $(record).find('div').html();

  		$.getJSON(edit_url+'get', {recordId: record_id}, function(json){
  			$('#add-' + idType + '-form').find('input:first-child').attr('value', json['id']);
  			
  	  		$('#add-' + idType + '-form input').each(function(){
  	  	  		$(this).attr('value', json[$(this).attr('name')]);
  	  		});
  	  		$('#add-' + idType + '-form select').each(function(){	 
  	  	  		$(this).find("option[value='"+json[$(this).attr('name')]+"']").attr('selected', 'selected');
  	  	  	});
  	  		  
  	  		$('#add-' + idType + '-form checkbox').each(function(){
  	  	  		$(this).attr('value', json[$(this).attr('name')]).attr('checked', 'checked');
  	  		});  	  
  	  		$('#add-' + idType + '-form radio').each(function(){
  	  	  		$(this).attr('value', json[$(this).attr('name')]).attr('selected', 'selected');
  	  		});	
  	  		new Boxy('#add-'+idType+'-form', {title: 'Phone', fixed: false});	      
		});
  	}
  	
	function delete_record(record_id, idType){
		var record = '#delete_'+idType+'_'+record_id;
		var delete_url = $(record).find('div').html();
		$.ajax({
			url: delete_url + 'delete',
			type: 'post',
			data: {recordId : record_id}, 
		    error: function(){
		        alert('Error deleting acount info');
		    },
		    success: function(xml){
			    $(record).parent().remove();
		    }
		});							
	}  
	$(document).ready(function() { 
		$('#events-form-popup').click(function(){
			new Boxy('#events-page', {title: 'Profile Upload'});
		});	  });
  </script>
  
  <style type="text/css">
  	#example table {
  		font-size: 10px;
  		margin: 0px;
  	}
  	
  	#example TH {
  		background-color: green;
  		border-bottom: 1px solid green;
  		color: white;
  	}
  	
  	#example TH, #example TD {
  		padding: 2px 5px;
  	}
  	
  	#profile-box li {
  		list-style-type: none;
  	}
  </style>

		<div id="profile-box">
			<ul>
				<li>
					<a href="#" id="events-form-popup">Create Event</a>
					<div style="display: none;"><%@ include file="create.jsp" %></div>
				</li>
			</ul>
		</div>

<%--
        <div id="example" class="flora">
            <ul>
                <li><a href="#fragment-1"><span>Pending</span></a></li>
                <li><a href="#fragment-2"><span>Processed</span></a></li>
                <li><a href="#fragment-3"><span>Tracking</span></a></li>
            </ul>
            <div id="fragment-1">
                <%@ include file="pending/pending.jsp" %>
            </div>
            <div id="fragment-2">
				<%@ include file="processed/processed.jsp" %>
            </div>
            <div id="fragment-3">
				<%@ include file="tracking/tracking.jsp" %>
            </div>            
        </div>	
 --%>