<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">
		function getPhone(){
			$.ajax( {
				type :"POST",
				url :"/secure/phone.do",
				data : {method: 'all'},
				dataType :'json',
				success : function(json) {
					$.each(json.phones, function(i,p){
						$('#phone-replace-me').append('<sns:reportrecords records="' + p.area + '|' + p.number + '|' + p.type +'" primaryKey="'+p.id+'" isPrimary="'+p.primaryPhone+'" deleteURL="/secure/phone.do?method=" idType="phone" />');
			         }); 
			    }
			});		
		}	
		$(document).ready(function() { 	
			getPhone();
									
			$('#add-phone').click(function(){
				new Boxy('#add-phone-form', {title: 'Phone', fixed: true, modal: true});
			});
		});	
</script>

<style type="text/css">
	#account-phone {
		margin: 0px;
		position: relative;
	}
	#account-phone div {
		float: left;
		padding: 5px;
	}
	#account-phone UL {
		float: none;
		clear: both;
	}
	#account-phone LI {
		list-style-type: none;
	}
	#phone-header {
		float: none;
		clear: both;
	}
	#phone-heading {
		font-weight: bold; color: gray;
	}
</style>

	<div id="phone-header" class="left-right">
		<div style="float:left;">Phone</div>
		<div style="float:right;">
			<a id="add-phone" href="#"><img src="/images/buttons/add.png"/></a>
		</div>
	</div>
	
<sns:report tableName="phoneTable" columns="Area Code|Phone Number|Type|-|-|-|">
	<tbody id="phone-replace-me"> 
	</tbody> 
</sns:report>

<%@ include file="form.jsp"%>

<%@ include file="edit.jsp"%>