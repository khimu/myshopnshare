<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<%--$("[name]") Gets each element with an attribute of "name" --%>


  <script type="text/javascript">
  	function edit_record(record_id, idType){
		var record = '#edit_'+idType+'_'+record_id;
		var edit_url = $(record).find('div').html();

  		$.getJSON(edit_url+'get', {recordId: record_id}, function(json){
  			$('#edit-' + idType + '-form').find('input:first-child').attr('value', json['id']);
  			
  	  		$('#edit-' + idType + '-form input').each(function(){
  	  	  		$(this).attr('value', json[$(this).attr('name')]);
  	  		});
  	  		$('#edit-' + idType + '-form select').each(function(){	 
  	  	  		$(this).find("option[value='"+json[$(this).attr('name')]+"']").attr('selected', 'selected');
  	  	  	});
  	  		  
  	  		$('#edit-' + idType + '-form checkbox').each(function(){
  	  	  		$(this).attr('value', json[$(this).attr('name')]).attr('checked', 'checked');
  	  		});  	  
  	  		$('#edit-' + idType + '-form radio').each(function(){
  	  	  		$(this).attr('value', json[$(this).attr('name')]).attr('selected', 'selected');
  	  		});
  	  		new Boxy('#edit-'+idType+'-form', {title: idType, fixed: true, modal: true});	     
		});
  	}
  	
	function delete_record(record_id, idType){
		Boxy.confirm("Please confirm delete:", function() {
			var record = '#delete_'+idType+'_'+record_id;
			var delete_url = $(record).find('div').html();
			$('#loading').show();
			$.ajax({
				url: delete_url + 'delete',
				type: 'post',
				data: {recordId : record_id}, 
			    error: function(){
			        Boxy.alert('Error deleting acount info');
			    },
			    success: function(xml){
			    	$('#loading').hide();
				    $(record).parent().remove();
			    }
			});
		}, {title: 'Confirm Delete'});
	    return false;							
	}  
	$(document).ready(function() { 
	    $("#example > ul").tabs();

	    $("a[rel]").overlay(function(){
	    });
	  });
  </script>
  
  <style type="text/css">
  	#example table {
  		margin: 0px;
  		width: 100%;
  	}
  	
  	#example TH {
  		width: 70px;
  		height: 10px;
  		font-weight: normal;
  		padding: 2px 5px;
  	}
  	
  	#example TD {
  		padding: 2px 5px;
  	}
  </style>
  
<%--
<a rel="#myOverlay">Open overlay</a>  

<div id="myOverlay" style="background-image:url(/images/white.png);"> 
--%>
		<div id="account-name-box">
		<%@ include file="person/person.jsp" %>
		</div>

        <div id="example" class="flora">
            <ul>
                <li><a href="#fragment-1"><span>Address</span></a></li>
                <li><a href="#fragment-2"><span>Email</span></a></li>
                <li><a href="#fragment-3"><span>Phone</span></a></li>
            </ul>
                    
            <div id="fragment-1" class="fragment-padding">
            	<%@ include file="address/address.jsp" %>           
            </div>
            
            <div id="fragment-2" class="fragment-padding">
				<%@ include file="email/email.jsp" %>
            </div>
            
            <div id="fragment-3" class="fragment-padding">
				<%@ include file="phone/phone.jsp" %>
            </div>
        </div>	
<%--</div>--%>       

<img id="loading" src="/images/loading.gif" style="display:none;">
        
        