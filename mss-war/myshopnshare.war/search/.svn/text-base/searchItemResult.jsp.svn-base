<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

<script type="text/javascript">
	$(document).ready(function(){
			
		$('#search_submit').click(function(){
				jQuery("#myshopnshare-search").validate( {
					submitHandler : function(form) {
						var category = $("#category-filter :selected").val();
						var method = $("#items-filter :selected").val();
						
						jQuery(form).ajaxSubmit( {
					       type: "post",  
					       url: "/secure/search.do",
					       data : {
						       method: method,
						       category :category
					       },
					       dataType : 'json',
							success: function(json) {
					    	   	$('#search-results').show();
					    	   	$('#search-results').html('');
								$('#search-results').append('<h3>' + json.searchMessage + '</h3>');
								$.each(json.items, function(i,item){
									if(item.type == 'VENDOR'){
										$('#search-results').append('<sns:searchitemimagerecordvendor itemName="'+ item.itemName +'" caption="'+ item.caption +'" description="'+ unescapeHTML(item.description) +'" icon="'+ item.icon +'" glimpse="'+ item.glimpse +'" primaryKey="' + item.id + '" />');								
									}else if(item.type == 'POINT'){
										$('#search-results').append('<sns:searchitemimagerecordpoint itemName="'+ item.itemName +'" caption="'+ item.caption +'" description="'+ unescapeHTML(item.description) +'" icon="'+ item.icon +'" glimpse="'+ item.glimpse +'" primaryKey="' + item.id + '" />');
									}else {
										$('#search-results').append('<sns:searchitemimagerecord itemName="'+ item.itemName +'" caption="'+ item.caption +'" description="'+ unescapeHTML(item.description) +'" icon="'+ item.icon +'" glimpse="'+ item.glimpse +'" primaryKey="' + item.id + '" />');
									}
								});	
								
								$("a[rel^='prettyPhoto']").prettyPhoto();		
								$('#myshopnshare-search').hide();		
								$('#go-back-to-search').show();
							} 
						});
					}
				});
				
			});
		});
	
</script>


	<style type="text/css">
	
		#title_info {
			clear: both;
			width: 100%;
		}
		
		#items-radio-buttons {
			clear: both;
			padding: 2px;
		}		
			#item-description img:hover {
				border: 3px solid #FFC;
			}
			.dynamic-friends-list LI {					
				list-style-type: none;
			}
			#search-items {
				padding: 5px;
			}
			#title_info TD {
				text-align: left;
				white-space: nowrap;
			}
			#title_info p {
				padding-left: 3px;
			}
			#myshopnshare-search p {
				padding-bottom: 2px;
			}
	</style>

<div id="search-items">

	<div id="title_info">
			
			<form id="myshopnshare-search" onsubmit="return false;">
			<table>
				<tr>
					<td colspan="2"><p>Looking for something special:</p></td>
				</tr>
				<tr>		
					<td>
					<input id="search_text" class="search_inactive" type="text" name="searchString" />
					<input type="image" src="/images/go.png" id="search_submit" value="OK" />
					</td>
				</tr>
			</table>
			<table>
				<tr>
					<td>Search By: </td>
					<td>
						<select id="items-filter" name="items-filter">
							<option value="world" selected="selected" > PUBLIC ITEMS </option>
							<option value="allfriends" > FRIENDS ITEMS </option>
							<option value="self" > MY ITEMS </option>
						</select>
					</td>
				</tr>
				<tr>
					<td>Filter By: </td>
					<td>
						<select id="category-filter" name="category-filter">
							<option value="" selected="selected">--</option>
							<option value="SERVICE" > SERVICE </option>
							<option value="SELLING" > SELLING </option>
							<option value="ADVICE" > ADVICE </option>
							<option value="BOUGHT" > BOUGHT </option>
							<option value="WANT" > WANT </option>
							<option value="RECOMMEND" > RECOMMEND </option>
						</select>				
					</td>
				</tr>
			</table>
			</form>
			
			<div id="go-back-to-search" style="display:none;"> <a href="#" onclick="backToSearchHeader()"><img src="/images/back.png" /></a> </div>
			
			<%--
			<table id="take-action-parent-element" style="display:none;">
				<tr>
					<td>
						Take Action: 
						<select onchange="takeSearchItemAction(this[this.selectedIndex].value);"><option value=""></option><option value="bought">Bought</option><option value="want">Want</option><option value="advice">Need Advice</option><option value="recommend">Recommend To Friend</option><option value="flag">Flag</option></select>
					</td>				
				</tr>			
			</table>
			--%>
	<div id="take-action-parent-element" style="display:none;vertical-align: top;">
		<table id="take-action-on-item-page-parent-element">
			<tr>
				<td>
					Choose One: 
					<div class="action-items">
						<div><a href="javascript:void(0);" onclick="takeSearchItemAction('advice');"><img src="/images/advice.png" /></a></div>
						<div><a href="javascript:void(0);" onclick="takeSearchItemAction('bought');"><img src="/images/BOUGHT.png"/></a></div>
						<div><a href="javascript:void(0);" onclick="takeSearchItemAction('want');"><img src="/images/WISH.png"/></a></div>
						<div><a href="javascript:void(0);" onclick="takeSearchItemAction('recommend');"><img src="/images/RECOMMEND.png"/></a></div>
					</div>	 
					<%--select onchange="takeSearchItemAction(this[this.selectedIndex].value);"><option value=""></option><option value="bought">Indicate Bought</option><option value="want">Indicate Want</option><option value="advice">Indicate Need Advice</option><option value="recommend">Recommend To Friend</option></select--%>
				</td>				
			</tr>			
		</table>
		<div id="recommend-successful" style="clear:both;">
		</div>	
			
	</div>
	</div>

	<div id="all-products" style="position: relative;clear:both;">
		<div style="float: left;width:auto; padding: 5px;margin: 5px;">
				<div id="recommend-successful-search" style="clear:both;">
				</div>
				<div id="search-results">
				</div>
				
				<div id="search-recommend-friend-element" style="display: none;clear:both;width:300px;height:150px;">
					<form id="recommend-form">
						<input id="item-id" type="hidden" name="itemId" value="" />
						<input id="item-name" type="hidden" name="itemName" value="" />
						<div class="dynamic-friends-list"></div>
					</form>	
					<a href="#" onclick="submitRecommend();">Submit</a>
				</div>			
		</div>
	</div>
</div>

	<%--
	<div id="erase-me-before-showing-item-page">
	  If this is duplicated, make sure it is on a completly different page or you will get errors 
	<input type="hidden" id="parent-element-name" name="noname" value="#search-items" />
	<div id="item-comment-page" style="display:none;clear:both;height:500px;">
		<a href="#" onclick="showHideComments('#search-items')">[+] Close</a>
		<div id="all-item-comments-area">
			<%@ include file="../item/itemComments.jsp" %>
		</div>
	</div>
	
	</div>
	--%>