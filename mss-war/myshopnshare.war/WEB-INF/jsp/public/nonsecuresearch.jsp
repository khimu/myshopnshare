<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

<script type="text/javascript">
	$(document).ready(function(){
/*
 * TODO
  public search not working properly
 */
 /*
		$.ajax({  
		       type: "post",  
		       url: "/public/search.do",
		       data: {method: 'nonsecure'},
		       dataType: 'json', 
		       success: function(json){ 
		    	   $('#search-results').show();
		    	   	$('#search-results').html('');
					$('#search-results').append('<h3>' + json.searchMessage + '</h3>');
					$.each(json.items, function(i,item){
						$('#search-results').append('<sns:publicsearchitemimagerecord itemName="'+ item.itemName +'" caption="'+ item.caption +'" description="'+ unescapeHTML(item.description) +'" icon="'+ item.icon +'" glimpse="'+ item.glimpse +'" primaryKey="' + item.id + '" />');								
					});	
					
					$("a[rel^='prettyPhoto']").prettyPhoto();		
					$('#myshopnshare-search').hide();		
					$('#go-back-to-search').show();
		       } 
		     });
*/		
		$('#search_submit').click(function(){
				jQuery("#myshopnshare-search").validate( {
					submitHandler : function(form) {
						var category = $("#category-filter :selected").val();
						jQuery(form).ajaxSubmit( {
					       type: "post",  
					       url: "/public/search.do",
					       data : {
						       method: 'nonsecure',
						       category :category
					       },
					       dataType : 'json',
							success: function(json) {
					    	   $('#search-results').show();
					    	   	$('#search-results').html('');
								$('#search-results').append('<h3>' + json.searchMessage + '</h3>');
								$.each(json.items, function(i,item){
									$('#search-results').append('<sns:publicsearchitemimagerecord itemName="'+ item.itemName +'" caption="'+ item.caption +'" description="'+ unescapeHTML(item.description) +'" icon="'+ item.icon +'" glimpse="'+ item.glimpse +'" primaryKey="' + item.id + '" />');								
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
			<div id="go-back-to-search" style="display:none;"> <a href="#" onclick="backToSearchHeader()">[SEARCH AGAIN]</a> </div>			
			<a href="/login.do">Login</a>	
			
			<form id="myshopnshare-search" onsubmit="return false;">
			<table>
				<tr>
					<td colspan="2"><p>Looking for something special:</p></td>
				</tr>
				<tr>		
					<td>
					<input id="search_text" class="search_inactive" type="text" name="searchString" /></td>
					<td style="font-size: 9px;"><button id="search_submit" type="submit" >GO</button></td>
				</tr>
			</table>
			</form>					
	</div>

	<div id="all-products" style="position: relative;clear:both;">
		<div style="float: left;width:auto; padding: 5px;margin: 5px;">
				<div id="search-results">
				<c:forEach items="${items}" var="item"> 
					<sns:publicsearchitemimagerecord itemName="${item.itemName}" caption="${item.itemName}" description="unescapeHTML(\'${item.description}\')" icon="/${item.icon.path}" glimpse="/${item.glimpse.path}" primaryKey="${item.id}" />
				</c:forEach>
				</div>		
		</div>
	</div>
</div>