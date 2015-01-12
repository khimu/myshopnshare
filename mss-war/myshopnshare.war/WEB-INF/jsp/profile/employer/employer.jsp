<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">
	function showHideEmployment(elementId){
		if($(elementId).is(':visible')){
			$(elementId).hide();
		}else{
			$(elementId).show();
		}
	}

	function deleteEmployment(employmentId){
		Boxy.confirm("Please confirm delete:", function() {			
			$.ajax( {
				type :"post",
				url: '/secure/employment.do',
				data: {method: 'delete', employmentId: employmentId},
				dataType: 'html',
				success : function(data) {
					$('#employment-'+employmentId).remove();
					$('#employment-header-'+employmentId).remove();
			    }
			});			
		}, {title: 'Confirm Delete'});
   	 	return false;
	}

	function editEmployment(employmentId){
		var params = $('#edit-employer-submit-form').serialize();
		
		$.ajax( {
			type :"post",
			url: '/secure/employment.do?method=edit&'+params,
			dataType: 'html',
			success : function(data) {
				$('#loading').hide();
				$('#employer-submit-form').clearForm();
				$('#edit-education-hidden').html('');
				$('#employment-parent-element').html(data);	
		    }
		});			
	}	

	function getEmployment(employmentId){
		$.ajax( {
			type :"post",
			url: '/secure/employment.do',
			data: {method: 'get', employmentId: employmentId},
			dataType: 'html',
			success : function(data) {
				$('#edit-education-hidden').html(data);
		    }
		});			
	}	
</script>

<div id="employment-parent-element" style="padding: 5px;clear:both;">
<c:forEach items="${employments}" var="employment">
	<div id="employment-header-${employment.id}" style="position:relative;">
		<div style="float:left:"><h3><a href="javascript:void(0);" onclick="showHideEmployment('#employment-${employment.id}');"><img src="/images/fw_bold.gif" title="${employment.employerName}" /></a>${employment.employerName}</h3></div>
		<div style="float:left:"><a href="#" onclick="deleteEmployment(${employment.id});"><img src="/images/buttons/delete.png"/></a></div>
		<div style="float:right:"><a href="#" onclick="getEmployment(${employment.id});"><img src="/images/buttons/edit.png"/></a></div>
	</div>
	<fmt:formatDate value="${employment.startDate}" pattern="MM/dd/yyyy" /> - <fmt:formatDate value="${employment.endDate}" pattern="MM/dd/yyyy" />
	<div class="clear"></div>
	<div id="edit-education-hidden"></div>
	<div id="employment-${employment.id}" class="tablelike">

			<div style="float:left;">Title</div>
			<div style="float:right;">${employment.title}</div>
			
			<div class="clear"></div>
			
			<c:if test="${not empty employment.division}">
			<div style="float:left;">Division</div>
			<div style="float:right;">${employment.division}</div>
			<div class="clear"></div>
			</c:if>
			
			<c:if test="${not empty employment.department}">
			<div style="float:left;">Department</div>
			<div style="float:right;">${employment.department}</div>
			<div class="clear"></div>
			</c:if>
	</div>
</c:forEach>	
</div>
