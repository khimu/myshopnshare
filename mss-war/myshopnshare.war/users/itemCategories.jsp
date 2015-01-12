<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

<%-- This isn't necessary.  possessions.jsp is enough.  If a user wants to see all items under one category, they
can use the view all under category.  don't need a page with all categories together.  --%>

	<script type="text/javascript">
	function getNextItemCategoriesPageItems(start){
		var selected = $("input[@name='items-filter']:checked").attr('id');
		var filter = selected.replace('items-', '');
		var category = $("input[@name='items-filter']:checked").val();	
			
		 $.ajax({  
		       type: "post",  
		       url: "/secure/itemCategories.do",
		       data: {method: filter, category: category, start: start},
		       dataType: 'json', 
		       success: function(json){ 
			   		document.getElementById('new-item-uploaded').innerHTML = '';
					$.each(json.items, function(i,item){
						if(item.type == 'VENDOR'){
							$('#new-item-uploaded').append('<sns:useritemcategoryrecordvendor itemId="' + item.itemId + '" color="' + item.color + '" itemName="'+ item.itemName +'" caption="'+ item.caption +'" description="'+ item.description + '" icon="'+ item.icon +'" glimpse="'+ item.glimpse +'" primaryKey="' + item.id + '" />');
						}else if(item.type == 'POINT'){
							$('#new-item-uploaded').append('<sns:useritemcategoryrecordpoint itemId="' + item.itemId + '" color="' + item.color + '" itemName="'+ item.itemName +'" caption="'+ item.caption +'" description="'+ item.description + '" icon="'+ item.icon +'" glimpse="'+ item.glimpse +'" primaryKey="' + item.id + '" />');
						}else{
							$('#new-item-uploaded').append('<sns:useritemcategoryrecord itemId="' + item.itemId + '" color="' + item.color + '" itemName="'+ item.itemName +'" caption="'+ item.caption +'" description="'+ item.description + '" icon="'+ item.icon +'" glimpse="'+ item.glimpse +'" primaryKey="' + item.id + '" />');
						}
				    }); 	      
					$("a[rel^='prettyPhoto']").prettyPhoto();	
		       } 
		});
	}
	
	function getItemCategoryItems(method, category){
		 $.ajax({  
		       type: "post",  
		       url: "/secure/itemCategories.do",
		       data: {method: method, category: category},
		       dataType: 'json', 
		       success: function(json){ 
			   		document.getElementById('new-item-uploaded').innerHTML = '';
					$.each(json.items, function(i,item){
						if(item.type == 'VENDOR'){
							$('#new-item-uploaded').append('<sns:useritemcategoryrecordvendor itemId="' + item.itemId + '" color="' + item.color + '" itemName="'+ item.itemName +'" caption="'+ item.caption +'" description="'+ item.description + '" icon="'+ item.icon +'" glimpse="'+ item.glimpse +'" primaryKey="' + item.id + '" />');
						}else if(item.type == 'POINT'){
							$('#new-item-uploaded').append('<sns:useritemcategoryrecordpoint itemId="' + item.itemId + '" color="' + item.color + '" itemName="'+ item.itemName +'" caption="'+ item.caption +'" description="'+ item.description + '" icon="'+ item.icon +'" glimpse="'+ item.glimpse +'" primaryKey="' + item.id + '" />');
						}else {
							$('#new-item-uploaded').append('<sns:useritemcategoryrecord itemId="' + item.itemId + '" color="' + item.color + '" itemName="'+ item.itemName +'" caption="'+ item.caption +'" description="'+ item.description + '" icon="'+ item.icon +'" glimpse="'+ item.glimpse +'" primaryKey="' + item.id + '" />');
						}
				    }); 	      
					$("a[rel^='prettyPhoto']").prettyPhoto();	
		       } 
		     });
	}

	$(document).ready(function(){		
		getItemCategoryItems('all', '');

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
				getItemCategoryItems($("input[@name='items-filter']:checked").val(), filter);
			}
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
		</style>
	

<div id="parent-possessions-element">	
		<a href="#" onclick="showPossessions();"><img src="/images/back.png" title="Back to your uploaded items" /></a>
		<br />
		<span>Filter By:  </span> 
		<div id="items-radio-buttons" style="border: 1px solid #FFF; padding: 5px;" > 
				<input id="items-" type="radio" name="items-filter" value="all" checked="checked"  />
				<span>ALL</span>    
				
				<input id="items-ADVICE" type="radio" name="items-filter" value="search" />
				<span> <font class="color-dot" style="background-color: yellow;color:yellow;">-</font> REQUEST ADVICE</span>
				
				
				<input id="items-BOUGHT" type="radio" name="items-filter" value="search"/>
				<span> <font class="color-dot"  style="background-color: blue;color:blue;">-</font> BOUGHT</span>
				
				
				<input id="items-SELLING" type="radio" name="items-filter" value="search"/>
				<span> <font class="color-dot"  style="background-color: orange;color:orange;">-</font> SELLING</span>

				<input id="items-SERVICE" type="radio" name="items-filter" value="search"/>
				<span> <font  class="color-dot"  style="background-color: purple;color:purple;">-</font> SERVICE</span>

				<input id="items-WANT" type="radio" name="items-filter" value="search"/>
				<span> <font class="color-dot"  style="background-color: green;color:green;">-</font> MY WISH LIST</span>

				<input id="items-RECOMMEND" type="radio" name="items-filter" value="search"/>
				<span> <font class="color-dot"  style="background-color: red;color:red;">-</font> RECOMMENDED BY FRIENDS</span>		
					
		</div>
		
		<div>
			<c:forEach begin="0" end="${total/15}" varStatus="status">
				<a href="#" onclick="getNextItemCategoriesPageItems(${status.index * 10})">${status.index + 1}</a> &nbsp;&nbsp;
			</c:forEach>
		</div>		
		
		<div id="all-products" style="position: relative;clear:both;">
			<div style="float: left;padding: 5px;margin: 5px;">
				<div id="new-item-uploaded">							
				</div>		
			</div>
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
	<%-- If this is duplicated, make sure it is on a completly different page or you will get errors --%>
		
	<%@ include file="../commercials/commercials.jsp" %>
	