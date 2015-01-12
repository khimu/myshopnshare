<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<script type="text/javascript">
	function ajaxFileUpload()
	{
		$("#loading")
		.ajaxStart(function(){
			$(this).show();
		})
		.ajaxComplete(function(){
			$(this).hide();
		});

		$.ajaxFileUpload({
				url:'/secure/user.do?method=upload&' + $('#user-item-upload-form').serialize(),
				secureuri:false,
				fileElementId:'fileToUpload',
				dataType: 'json',
				success: function (item, status){					
					if(typeof(item.error) != 'undefined'){
						if(item.error != ''){
							alert(' data.error '+item.error);
						}else{
							alert(' data.msg ' + item.msg);
						}
					}
					$('#user-item-upload-form').clearForm();
					$('a[@rel*=lightbox]').lightBox({fixedNavigation:true});
				},
				error: function (data, status, e){
					$('#user-item-upload-form').clearForm();
				}
			});

		return false;
	}

</script>

<style type="text/css">
.warning {
	color: red;
	font-size: .9em;
}
</style>

<style type="text/css">
	#user-item-upload-box P {
		text-align: right;
		padding-right: 5px;
		font-size: 1.0em;
	}

	#user-item-upload-box INPUT {
		padding-right: 5px;
		font-size: 1.0em;
	}

	#user-item-upload-box LABEL {
		font-weight: bold;
		color: gray;
	}
</style>

<div id="user-item-upload-box" > 
		<form id="user-item-upload-form" method="post">	
			<h3>Item Upload</h3>
				<p>
					<label for="category">Category: </label>
					<select name="category">
							<option value="" selected="selected"></option>
							<option value="BOUGHT">Bought</option>
							<option value="SELLING">Selling</option>
							<option value="ADVICE">Advice</option>
							<option value="RECOMMEND">Recommend</option>
							<option value="WANT">Want</option>
					</select>
				</p>
				<p>
					<label for="itemName">Item Name: </label>
					<input type="text" id="itemName" size="45" name="itemName" />
				</p>
				<p>
					<label for="categoryDescription">Category Description: </label>
					<textarea id="categoryDescription" name="categoryDescription" cols="50" rows="2"></textarea>
				</p>		
				<p>
					<label for="description">Description</label>
					<textarea id="description" name="description" cols="50" rows="3"></textarea>
				</p>
				<p>
					<label for="tags">Tags</label>
					<input id="tags" name="tags" type="text" size="45" maxlength="50" />
				</p>
				<p>
					<input id="fileToUpload" type="file" size="45" name="fileToUpload" class="input">
				</p>
				<p>
					<button name="button" id="submit-upload-button" onclick="return ajaxFileUpload();">Add</button>
					<button name="button" id="reset-upload-button" type="reset">Reset</button>
				</p>
		</form>

		<img id="loading" src="/images/loading.gif" style="display:none;"/>
</div>