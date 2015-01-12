<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">		
	function getAddress(){
		$.ajax( {
			type :"post",
			url :"/secure/address.do",
			data : {method: 'all'},
			dataType :'json',
			success : function(json) {
				$.each(json.addresses, function(i,address){
					$('#address-replace-me').append('<sns:reportrecords records="'+address.street1 + '|' + address.street2 + '|' + address.unitNumber +'|' + address.city + '|' + address.stateOrProvince + '|' + address.postalCode + '|' + address.country+'" primaryKey="' + address.id + '" isPrimary="' + address.primaryAddress + '" deleteURL="/secure/address.do?method=" idType="address"  />');
		         }); 
		    }
		});		
	}	
	$(document).ready(function() { 
		getAddress();
			$('#add-address').click(function(){
				new Boxy('#add-address-form', {title: 'Address', fixed: false, modal: true});
			});																		
		});	
</script>

<style type="text/css">
	#account-address {
		margin: 0px;
		position: relative;
		padding:5px;
	}
	#account-address div {
		float: left;
		padding: 2px 5px;
		width: 70px;
	}
	#account-address UL {
		float: none;
		clear: both;
	}	
	#account-address LI {
		position: relative;
		list-style-type: none;	
		float: none;
		clear: both;
	}
	#address-header {
		clear: both;
		float: none;
	}
</style>

	<div id="address-header" class="left-right">
		<div style="float:left;">Address</div>
		<div style="float:right;"><a id="add-address" href="#"><img src="/images/buttons/add.png" /></a></div>
	</div>

<sns:report tableName="addressTable" columns="Street1|Street2|Unit/Apt #|City|State/Province|Zip/Postal Code|Country|-|-|-|">
	<tbody id="address-replace-me"> 
	</tbody> 
</sns:report>

<%@ include file="add.jsp"%>
<%@ include file="edit.jsp"%>
