<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<script type="text/javascript">
	$(document).ready(function(){
		$('#upload-merchant-item').click(function(){
			new Boxy('#merchant-upload-page', {title: 'Upload Products'});
		});		
		$.ajax({ 
			type:"POST", 
			url: '/secure/product.do', 
			success: function( data ) { 
				$("#product-items").html( data );
				$("a[rel^='prettyPhoto']").prettyPhoto(); 	
			}
		});	
	});
	
	function deleteProduct(itemId){
		//var itemId = $(this).attr('alt').val();
		$.ajax({ 
			type:"POST", 
			data: {itemId: itemId, method: 'delete'},
			url: '/secure/product.do', 
			success: function( data ) { 
				$('#product-items').html(data);
				$("a[rel^='prettyPhoto']").prettyPhoto(); 	
			}
		});			
	}

	function viewProduct(itemId){
		$.ajax({ 
			type:"POST", 
			data: {itemId: itemId},
			url: '/public/vendorItemPage.do', 
			success: function( data ) { 
				$('#product-items').html(data);
			}
		});			
	}
</script>

<h3>[<a href="#" id="upload-merchant-item">Upload New Items</a>]</h3>

<div id="product-items">
	<jsp:include page="products.jsp"></jsp:include>
</div>
<div id="merchant-upload-page" style="display: none;">
	<jsp:include page="merchantUpload.jsp"></jsp:include>
</div>
