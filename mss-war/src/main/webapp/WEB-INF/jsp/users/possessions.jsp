<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>


<script type="text/javascript">
	var uploadUrl = '/secure/user.do?method=upload&';
	
	function getItemsComplete(items){
		document.getElementById('new-item-uploaded').innerHTML = '';
		$.each(items, function(i,item){
			if(item.type == 'VENDOR'){
			 $('#new-item-uploaded').append('<sns:useritemeditrecordvendor itemName="'+ item.itemName +'" caption="'+ item.caption +'" description="'+ item.description + '" icon="'+ item.icon +'" glimpse="'+ item.glimpse +'" primaryKey="' + item.id + '" />');
			}else if(item.type == 'POINT'){
				 $('#new-item-uploaded').append('<sns:useritemeditrecordpoint itemName="'+ item.itemName +'" caption="'+ item.caption +'" description="'+ item.description + '" icon="'+ item.icon +'" glimpse="'+ item.glimpse +'" primaryKey="' + item.id + '" />');
			}else{
				 $('#new-item-uploaded').append('<sns:useritemeditrecord itemName="'+ item.itemName +'" caption="'+ item.caption +'" description="'+ item.description + '" icon="'+ item.icon +'" glimpse="'+ item.glimpse +'" primaryKey="' + item.id + '" />');
			}
	       }); 	      
		$("a[rel^='prettyPhoto']").prettyPhoto();	
				
	}

	function getNextPossessionsPageItems(start){
		var selected = $("input[@name='items-filter']:checked").attr('id');
		var filter = selected.replace('items-', '');

		var category = $("input[@name='items-filter']:checked").val();
		 $.ajax({  
		       type: "POST",  
		       url: "/secure/possessions.do",
		       data: {method: filter, category: category, start: start},
		       dataType: 'json', 
		       success: function(json){ 
		    	   getItemsComplete(json.items);
		       } 
		  });
	}

	function getPossessionsItems(method, category){
		 $.ajax({  
		       type: "POST",  
		       url: "/secure/possessions.do",
		       data: {method: method, category: category},
		       dataType: 'json', 
		       success: function(json){ 
		    	   getItemsComplete(json.items);
		       } 
		     });
	}
	
	$(document).ready(function(){		
		getPossessionsItems('all', '');
		
		$('#tag-item-button').click(function(){	
			var recordId = $('#tag-item-id').val();
			var tags = $('#item-tags').val();
			 $.ajax({  
			       type: "post",  
			       url: "/secure/user.do",
			       data: {method: 'tag', itemId: recordId, tags: tags},
			       dataType: 'json', 
			       success: function(json){ 		
			    	  // $('#item-tag-form').hide(); 
			    	   Boxy.get('#item-tag-form').hide();
			    	} 
			     });
		});
		
		$('#tag-item-cancel').click(function(){
			//$('#item-tag-form').hide();
			Boxy.get('#item-tag-form').hide();
		});
		
		$("input[@name='items-filter']").click(function(){
			if ($("input[@name='items-filter']:checked").val()){
				var selected = $("input[@name='items-filter']:checked").attr('id');
				var filter = selected.replace('items-', '');
				getPossessionsItems($("input[@name='items-filter']:checked").val(), filter);
			}
		});		
		
		$('#upload-new-item').click(function(){
			uploadUrl = "/secure/user.do?method=upload&";
			$('#upload-item-id').attr('value', '');
			$('#existing-item-tags').html('');
			$('#user-item-upload-form').clearForm();
			new Boxy('#user-upload-box', {title: 'Upload Item', modal: true});
		});
	});	

</script>

		<style type="text/css">
			#parent-possessions-element {padding: 5px;}

			#item-description DIV{
				text-align: center;
				vertical-align:middle;
			}
			#item-description UL {
				text-align: center;
				vertical-align: middle;
			}
			
			#items-radio-buttons INPUT {
				padding: 2px;
			}
			
			#items-radio-buttons SPAN {
				padding-right: 5px;
			}	
						
			.highlight { background-color: yellow }
				
			#possessions-links {
				position: relative;
				vertical-align: bottom;
				clear:both;
			}
			
			#possessions-links a {
				float: left;
				height:10px;
				padding:10px 0;
			}
			
		</style>
	
<div id="parent-possessions-element">	
			
		<div id="possessions-links">
			<a style="padding-right: 20px;" href="#" id="upload-new-item"><img src="/images/uploadimage.png" /></a>
			<a href="#" onclick="showCategorizedItems();"><img src="/images/viewcategorizeditems.png" /></a>
		</div>
		<div class="clear"></div>
		<c:if test="${not empty searchMessage}"><div class="errors">${searchMessage}</div></c:if>
		
		<div>
			<c:forEach begin="0" end="${total/15}" varStatus="status">
				<a href="#" onclick="getNextPossessionsPageItems(${status.index * 10})">${status.index + 1}</a> &nbsp;&nbsp;
			</c:forEach>
		</div>		

		<div id="all-products" style="position: relative;clear:both;">
			<div style="float: left;padding: 5px;margin: 5px;">
				<div id="new-item-uploaded">							
				</div>		
			</div>
		</div>

		<div id="user-upload-box" style="display: none;">
			<%@ include file="useritemupload.jsp" %>
		</div>
		
		<div id="item-tag-form" style="display:none;width:200px;">
			<form id="tag-form" onsubmit="return false;">
				<div>
				<input id="tag-item-id" type="hidden" name="itemId" />
				<label for="tags">Tags</label>
				</div>
				
				<div>
				<input type="text" id="item-tags" name="tags" />
				</div>
				
				<div>
				<button id="tag-item-button" type="submit">Submit</button>
				<button id="tag-item-cancel" type="reset">Cancel</button>
				</div>
			</form>
		</div>

	</div>
		
	<%@ include file="../commercials/commercials.jsp" %>
	