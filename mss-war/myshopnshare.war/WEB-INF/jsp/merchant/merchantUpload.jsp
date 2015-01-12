<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$("#tags").Watermark("Tags1, Tags2, Tags3");
	$("#shipping").Watermark("0");
	$("#tax").Watermark("0");
	$("#price").Watermark("0");
	$("#clearanceAmount").Watermark("0");
	$("#rebateAmount").Watermark("0");
});

function isNumber(inputStr) {
    for (var i = 0; i < inputStr.length; i++) {
        var oneChar = inputStr.substring(i, i + 1)
        if (oneChar < "0" || oneChar > "9") {
            alert("Please make sure entries are numbers only.")
            return false;
        }
    }
    return true;
}

function ajaxFileUpload()
{

	if(!isNumber($('#rebateAmount').val()) || !isNumber($('#clearanceAmount').val()) || !isNumber($('#shipping').val()) || !isNumber($('#tax').val()) || !isNumber($('#price').val())){
		return false;
	}
	
	$("#loading")
	.ajaxStart(function(){
		$(this).show();
	})
	.ajaxComplete(function(){
		$(this).hide();
	});

	$.ajaxFileUpload({
			url:'/secure/product.do?method=upload&' + $('#merchant-item-upload-form').serialize(),
			secureuri:false,
			fileElementId:'fileToUpload',
			dataType: 'text',
			success: function (data, status){
				Boxy.get('#merchant-upload-page').hide();
				$('#product-items').html(data);
				
				if(typeof(item.error) != 'undefined'){
					if(item.error != ''){
						alert(' data.error '+item.error);
					}else{
						alert(' data.msg ' + item.msg);
					}
				}
				$('#merchant-item-upload-form').clearForm();
				$("a[rel^='prettyPhoto']").prettyPhoto(); 	
			},
			error: function (data, status, e){
				$('#merchant-item-upload-form').clearForm();
				Boxy.get('#merchant-upload-page').hide();
			}
		});

	return false;
}
</script>

<style type="text/css">
	.warning {
		color: red;
		font-size: 1.2em;
	}

	#merchant-item-upload-form DIV {
		text-align: right;
		padding-right: 5px;
		font-size: 11pt;
	}

	#merchant-item-upload-form INPUT {
		padding-right: 5px;
		font-size: 11pt;
	}

	#merchant-item-upload-form LABEL {
		font-weight: bold;
		color: gray;
	}
</style>

<div id="merchant-item-upload-box" > 	
	<div id="merchant-error-upload"></div>	
	<form id="merchant-item-upload-form" method="post" enctype="multipart/form-data">
		<table cellpadding="3">
			<tr>
				<td><label for="itemFile">Item Image</label></td>
				<td><input id="fileToUpload" type="file" size="45" name="fileToUpload" class="input"/></td>
			</tr>					
			<tr>
				<td><label for="serialNumber">Serial Number</label></td>
				<td><input id="serialNumber" name="serialNumber" type="text" size="30" maxlength="50" /></td>
			</tr>
			<tr>
				<td><label for="itemName">Item Name</label></td>
				<td><input type="text" id="itemName" size="45" name="itemName" /></td>
			</tr>
			<tr>			
				<td><label for="price">Price</label></td>
				<td><input id="price" name="price" type="text" size="30" maxlength="50" /></td>
			</tr>	
			<tr>
				<td><label for="shipping">Shipping</label></td>
				<td><input id="shipping" name="shipping" type="text" size="30" maxlength="50" /></td>
			</tr>			
			<tr>			
				<td><label for="tax">Tax</label></td>
				<td><input id="tax" name="tax" type="text" size="30" maxlength="50" /></td>
			</tr>					
			<tr>			
				<td><label for="clearanceAmount">Clearance Amount</label></td>
				<td><input id="clearanceAmount" name="clearanceAmount" type="text" size="30" maxlength="50" /></td>
			</tr>
			<tr>			
				<td><label for="rebateAmount">Rebate Amount</label></td>
				<td><input id="rebateAmount" name="rebateAmount" type="text" size="30" maxlength="50" /></td>
			</tr>
			<tr>
				<td><label for="reedemablePoints">Reedemable Points</label></td>
				<td><input id="reedemablePoints" name="reedemablePoints" type="text" size="30" maxlength="50" /></td>
			</tr>			
			<tr>			
				<td></td>
				<td><input id="refurbish" name="refurbish" type="checkbox" /> <label for="refurbish">Refurbished</label></td>
			</tr>
			<tr>			
				<td></td>
				<td><input id="used" name="used" type="checkbox" /> <label for="used">Used</label></td>
			</tr>							
			<tr>
				<td><label for="tags">Tags</label></td>
				<td><input id="tags" name="tags" type="text" size="30" maxlength="50" /></td>
			</tr>		
			<tr>
				<td><label for="description">Description</label></td>
				<td><textarea id="description" name="description" cols="30" rows="5"></textarea></td>
			</tr>
			<tr>
				<td>
					<button name="button" id="submit-upload-button" onclick="return ajaxFileUpload();">Add</button>
					<button name="button" id="reset-upload-button" type="reset">Reset</button>
				</td>
			</tr>	
		</table>	
    </form>
    <img id="loading" src="/images/loading.gif" style="display:none;"/>
</div>