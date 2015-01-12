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
		var edit_url = $j(record).find('div').html();

  		$j.getJSON(edit_url+'get', {recordId: record_id}, function(json){
  			$j('#add-' + idType + '-form').find('input:first-child').attr('value', json['id']);
  			
  	  		$j('#add-' + idType + '-form input').each(function(){
  	  	  		$j(this).attr('value', json[$j(this).attr('name')]);
  	  		});
  	  		$j('#add-' + idType + '-form select').each(function(){	 
  	  	  		$j(this).find("option[value='"+json[$j(this).attr('name')]+"']").attr('selected', 'selected');
  	  	  	});
  	  		  
  	  		$j('#add-' + idType + '-form checkbox').each(function(){
  	  	  		$j(this).attr('value', json[$j(this).attr('name')]).attr('checked', 'checked');
  	  		});  	  
  	  		$j('#add-' + idType + '-form radio').each(function(){
  	  	  		$j(this).attr('value', json[$j(this).attr('name')]).attr('selected', 'selected');
  	  		});	
  	  		new Boxy('#add-'+idType+'-form', {title: 'Phone', fixed: false});	      
		});
  	}
  	
	function delete_record(record_id, idType){
		var record = '#delete_'+idType+'_'+record_id;
		var delete_url = $j(record).find('div').html();
		$j.ajax({
			url: delete_url + 'delete',
			type: 'post',
			data: {recordId : record_id}, 
		    error: function(){
		        alert('Error deleting acount info');
		    },
		    success: function(xml){
			    alert($j(record).parent().html());
			    $j(record).parent().remove();
		    }
		});							
	}  
	$j(document).ready(function() { 
		$j('#profile-face-popup').click(function(){
			new Boxy('#profile-face-page', {title: 'Profile Upload'});
		});
		$j('#profile-about-popup').click(function(){
			new Boxy('#profile-about-page', {title: 'Profile Upload'});
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
  	
  	#photo-box li {
  		list-style-type: none;
  	}
  </style>

		<div id="photo-box">
			<ul>
				<li>
					<a href="#" id="profile-face-popup">Upload Profile Image</a>
					<div style="display: block;"><%@ include file="upload.jsp" %></div>
				</li>
				<li>
					<a href="#" id="profile-face-popup">Upload Profile Image</a>
					<div style="display: block;">
<form name="testform">
	<input name="cmd" type="text">
	<input type="button" value="Call JUpload function" onclick="doit()">
</form>

<applet
		title="JUpload"
		name="JUpload"
		code="com.smartwerkz.jupload.classic.JUpload"
		codebase="http://localhost:8080/myshopnshare"
		archive="applet/jupload.jar,
				skinlf/skinlf-6.2.jar"
		width="640"
		height="480"
		mayscript="mayscript"
		alt="JUpload by www.jupload.biz">

	<param name="Config" value="applet/cfg/jupload.default.config">

	Your browser does not support Java Applets or you disabled Java Applets in your browser-options.
	To use this applet, please install the newest version of Sun's Java Runtime Environment (JRE).
	You can get it from <a href="http://www.java.com/">java.com</a>

</applet>					
					
					</div>
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