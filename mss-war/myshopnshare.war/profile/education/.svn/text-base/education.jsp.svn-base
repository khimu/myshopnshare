<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">

	function deleteEducation(educationId){
		Boxy.confirm("Please confirm delete:", function() {		
		$.ajax( {
			type :"post",
			url: '/secure/education.do',
			data: {method: 'delete', educationId: educationId},
			dataType: 'html',
			success : function(data) {
				$('#education-'+educationId).remove();
				$('#education-header-'+educationId).remove();
		    }
		});	
		}, {title: 'Confirm Delete'});
	    return false;
	}
	
	function editEducation(educationId){
		$('#loading').show();
		$.ajax( {
			type :"post",
			url :"/secure/education.do?method=edit&" + $('#edit-education-upload-form').serialize(),
			dataType: 'html',
			success : function(data) {
				$('#loading').hide();
				$('#edit-education-hidden').html('');
				$('#education-parent-element').html(data);
				$('#user-education-upload-form').clearForm();
		    }
		});	
	}
	
	function getEducation(educationId){
		$.ajax( {
			type :"post",
			url: '/secure/education.do',
			data: {method: 'get', educationId: educationId},
			dataType: 'html',
			success : function(data) {
				$('#edit-education-hidden').html(data);
		    }
		});	
	}		
</script>
	
<div id="education-parent-element" style="padding: 5px;clear:both; width: 600px;">
<c:forEach items="${educations}" var="education">
	<div id="education-header-${education.id}" style="position:relative;">
		<div style="float:left:"><h3><a href="javascript:void(0);" onclick="if($('#education-${education.id}').is(':visible')){$('#education-${education.id}').hide();}else{$('#education-${education.id}').show();}"><img src="/images/fw_bold.gif" title="${education.institutionType} of ${education.institutionName}" /></a>${education.institutionType} of ${education.institutionName}</h3></div>
		<div style="float:left:"><a href="javascript:void(0);" onclick="deleteEducation('${education.id}');"><img src="/images/buttons/delete.png" /></a></div>
		<div style="float:right:"><a href="javascript:void(0);" onclick="getEducation('${education.id}');"><img src="/images/buttons/edit.png"/></a></div>
	</div>
	<div id="edit-education-hidden"></div>
	<div id="education-${education.id}" class="tablelike">

			<div style="float:left;">Degree</div>
			<div style="float:right;">${education.degree}</div>
			
			<div class="clear"></div>
			<hr/>
			<div class="clear"></div>
			
			<div style="float:left;">Graduation Year</div>
			<div style="float:right;">${education.endYear}</div>
			
			<div class="clear"></div>
			<hr/>
			<div class="clear"></div>
			
			<div style="float:left;">Start Year</div>
			<div style="float:right;">${education.startYear}</div>

			<div class="clear"></div>
			<hr/>
			<div class="clear"></div>
			
			<div style="float:left;">Major</div>
			<div style="float:right;">${education.major}</div>
			
			<div class="clear"></div>
			<hr/>
			<div class="clear"></div>
	</div>
</c:forEach>	
</div>