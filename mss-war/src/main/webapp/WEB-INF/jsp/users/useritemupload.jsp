<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<script type="text/javascript">
	$(function(){		
	   $("#tax").Watermark("0.0825");
	});
</script>

<script type="text/javascript">
	function ajaxUploadFileUpload()
	{
		if($('#upload-item-id').val() == '' && $('#userFileToUpload').val() == '' && $('#externalImageLink').val() == ''){
			Boxy.alert('Please add a photo or a link to an image.');
			return false;
		}
		if($('#category-list :selected').text() == ''){
			Boxy.alert('Please select an action.');
			return false;
		}

		if($('#country').val() == ''){
			Boxy.alert('Please enter the country the item came from or is related to.');
			return false;
		}
		
		if($('#category-list :selected').text().toUpperCase() == 'POINT' || $('#category-list :selected').text().toUpperCase() == 'SERVICE' || $('#category-list :selected').text().toUpperCase() == 'SELLING'){
			if(!isFloat($('#quantity').val()) 
					|| !isFloat($('#rebateAmount').val()) 
					|| !isFloat($('#clearanceAmount').val()) 
					|| !isFloat($('#tax').val())
					|| !isFloat($('#shipping').val()) 
					|| !isFloat($('#price').val())){
				Boxy.alert('Please enter numbers for shipping, price, tax, quantity, rebate, and clearance.');
					return false;
				}		
		}
			
		$("#loading")
		.ajaxStart(function(){
			$(this).show();
		})
		.ajaxComplete(function(){
			$(this).hide();
		});

		$.ajaxFileUpload({
				url: uploadUrl + $('#user-item-upload-form').serialize(),
				secureuri:false,
				fileElementId:'userFileToUpload',
				dataType: 'json',
				success: function (item, status){
					Boxy.get('#user-upload-box').hide();

					if($('#upload-item-id').val() != ''){
							$('#item-description' + $('#upload-item-id').val()).remove();	
					}

					if(item.type == 'VENDOR'){
						$('#new-item-uploaded').append('<sns:useritemeditrecordvendor itemName="'+ item.itemName +'" caption="'+ item.caption +'" description="'+ item.description +'" icon="'+ item.icon +'" glimpse="'+ item.glimpse +'" primaryKey="' + item.id + '" />');
					}else if(item.type == 'POINT'){
						$('#new-item-uploaded').append('<sns:useritemeditrecordpoint itemName="'+ item.itemName +'" caption="'+ item.caption +'" description="'+ item.description +'" icon="'+ item.icon +'" glimpse="'+ item.glimpse +'" primaryKey="' + item.id + '" />');
					}else {
						$('#new-item-uploaded').append('<sns:useritemeditrecord itemName="'+ item.itemName +'" caption="'+ item.caption +'" description="'+ item.description +'" icon="'+ item.icon +'" glimpse="'+ item.glimpse +'" primaryKey="' + item.id + '" />');
					}
					
					$("a[rel^='prettyPhoto']").prettyPhoto();
				},
				error: function (data, status, e){
					Boxy.get('#user-upload-box').hide();
				}
			});

		$('#user-item-upload-form').clearForm();

		return false;
	}

	$(document).ready(function(){
		/** If recommend is selected, load the friends list **/
		$('#category-list').change(function(){
			if($('#category-list :selected').text().toUpperCase() == 'RECOMMEND'){
				$('#selling-items-element').hide();
				$('#parent-dynamic-friends-list').show();
				loadFriends();
			}else if($('#category-list :selected').text().toUpperCase() == 'POINT' || $('#category-list :selected').text().toUpperCase() == 'SERVICE' || $('#category-list :selected').text().toUpperCase() == 'SELLING'){
				$('#selling-items-element').show();
			}else{
				$('.dynamic-friends-list').html('');
				$('#parent-dynamic-friends-list').hide();
				$('#selling-items-element').hide();
			}
		});
	});
</script>

<style type="text/css">
.warning {
	color: red;
	font-size: .9em;
}
</style>

<style type="text/css">

	#user-item-upload-box INPUT {
		padding-right: 5px;
		text-align: left;
	}

	#user-item-upload-box TD {
		vertical-align: top;
		padding: 5px;
	}
	
#ads-box INPUT {

}	
</style>

<div id="user-item-upload-box" > 							
		<form id="user-item-upload-form">	
		<input id="upload-item-id" type="hidden" name="id" />
		<div style="position: relative;">
			<div style="float: left;margin-right: 5px;padding: 2px;">
				<div id="selling-items-element" style="display:none">
					<div><label for="serialNumber">Serial Number</label></div>
					<div><input id="serialNumber" name="serialNumber" type="text" size="30" maxlength="50" /></div>	
					<div><label for="quantity">Quantity</label></div>
					<div><input id="quantity" name="quantity" type="text" size="30" maxlength="50" /></div>			
					<div><label for="price">Price</label></div>
					<div><input id="price" name="price" type="text" size="30" maxlength="50" /></div>
					<div><label for="shipping">Shipping</label></div>
					<div><input id="shipping" name="shipping" type="text" size="30" maxlength="50" /></div>			
					<div><label for="tax">Tax</label></div>
					<div><input id="tax" name="tax" type="text" size="30" maxlength="50" /></div>
				
					<div><label for="clearanceAmount">Clearance Amount</label></div>
					<div><input id="clearanceAmount" name="clearanceAmount" type="text" size="30" maxlength="50" /></div>			
					<div><label for="rebateAmount">Rebate Amount</label></div>
					<div><input id="rebateAmount" name="rebateAmount" type="text" size="30" maxlength="50" /></div>			
					<div><input id="refurbish" name="refurbish" type="checkbox" /> <label for="refurbish">Referbished</label></div>
					<div><input id="used" name="used" type="checkbox" /> <label for="used">Used</label></div>												
				</div>
				<div id="parent-dynamic-friends-list" style="display:none;">
					<div><label for="friends">Friends: </label></div>
					<div class="dynamic-friends-list"></div>
				</div>
			</div>
			<div style="float: left;margin-left: 5px;padding: 2px;">	
				<div>
					<div><label for="itemName">Item Name: </label></div>
					<div><input type="text" id="itemName" size="30" maxlength="50"  name="itemName" /></div>
					<div><label for="itemtitle">Title: </label></div>
					<div><input type="text" id="itemtitle" size="30" maxlength="100"  name="title" /></div>
					<div><label for="subtitle">Subtitle: </label></div>
					<div><input type="text" id="subtitle" size="30" name="subtitle" maxlength="100"  /></div>											
					<div><label for="adimage">Photo</label></div>
					<div><input id="userFileToUpload" type="file" size="30" name="userFileToUpload"/></div>
					<div>or</div>
					<div><label for="externalImageLink">External Image Link</label></div>
					<div><input id="externalImageLink" type="text" size="30" name="externalImageLink"/></div>					
					<div><label for="sourceLink">Resource Link</label></div>
					<div><input id="resourceLink" type="text" size="30" name="resourceLink"/></div>					
					<div><label for="category">Action: </label></div>
					<div>
							<select id="category-list" name="category" >
									<option value="" selected="selected">----</option>
									
									<c:if test="${isMaxSelling eq false or person.subscribed}">
										<option value="SELLING">Selling</option>
									</c:if>
									<%--c:if test="${person.maxService eq false or person.subscribed}">
										<option value="SERVICE">Service</option>
									</c:if--%>									
									<option value="BOUGHT">Bought</option>
									<option value="ADVICE">Advice</option>
									<option value="RECOMMEND">Recommend</option>
									<option value="WANT">Want</option>
									
									<c:if test="${person.subscribed}">
										<option value="POINT">Point</option>
									</c:if>					
			
							</select>							
					</div>
					<%-- 
					<div><label for="actualcategory">Category: </label></div>
					<div>
							<select id="actualcategory" name="actualcategory" >
									<option value="" selected="selected">----</option>
								<c:forEach items="${categories}" var="category">
									<option value="${category.value}">${category.label}</option>		
								</c:forEach>
							</select>							
					</div>
					--%>
					<div><label for="tags">Tags</label></div>
					<div><input id="tags" name="tags" type="text" size="30" /></div>
					<div id="existing-item-tags" style="width: 400px;"></div>
					<div><label for="country">Made in Country</label></div>
					<div><input id="country" name="country" type="text" size="30" /></div>					
					<div><label for="description">Description</label></div>
					<div><textarea id="description" name="description" cols="50" rows="5" maxlength="500"></textarea></div>
				</div>
			</div>
		</div>
		<div>
			<button name="button" id="submit-upload-button" onclick="return ajaxUploadFileUpload();">Add</button>
			<button name="button" id="reset-upload-button" type="reset">Reset</button>
		</div>	
    	</form>
    	<img id="loading" src="/images/loading.gif" style="display:none;"/>
    </div>