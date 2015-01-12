<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>
		
<script type="text/javascript">	 		

	$(document).ready(function(){
		clearItemStars();
		makeItemActive('${item.itemRating.rating/item.itemRating.count}');
	});

	function takeActionOnItemPage(recordId, itemName){
		if($('#take-action-on-item-page-parent-element').is(':visible')){
			$('#take-action-on-item-page-parent-element').hide();
		}else{
			$('#take-action-on-item-page-parent-element').show();
		}
		search.itemIds.push(recordId);
		search.itemNames.push(itemName);
	}
	
</script>

<div id="user-item-page" class="authenticated">
	<div class="item-inner-wrap"><a href="#" onclick="backToSearch()"><img src="/images/back.png" /></a> &nbsp;&nbsp;&nbsp;&nbsp; <c:if test="${item.rewards}"> <a href="#" onclick="viewRewardItemDetail(${item.id})"><img src="/images/buttons/buyred.png" /></a></c:if><c:if test="${item.products}"> <a href="#" onclick="viewVendorItemDetail(${item.id})"><img src="/images/buttons/buyred.png" /></a></c:if></div>
	
	<div id="search-recommend-friend-element" style="display: none;clear:both;width:300px;height:150px;">
		<form id="recommend-form">
			<input id="item-id" type="hidden" name="itemId" value="" />
			<input id="item-name" type="hidden" name="itemName" value="" />
			<div class="dynamic-friends-list"></div>
		</form>	
		<a href="#" onclick="submitRecommend();">Submit</a>
	</div>	

	<div style="font-size:10px;"><strong><a href="http://maps.google.com/maps/geo?KeepThis=true&TB_iframe=true&height=450&width=740&q=${item.location}&output=json&oe=utf8&sensor=true_or_false&key=ABQIAAAAMNNNy34UOUsl9aq0D4kvcBSCJrqMDY1plGogyxSzH0ceUbbsxxRnTZItpUWKC3_XCWAeFxOjOU8Q4Q" class="thickbox" title="${item.itemName} @ ${item.location}" target="_blank">Map</a></strong></div>
	
	<div class="clear"></div>
	<h3>${item.itemName}</h3>
	<div class="clear"></div>
	
	<div style="text-align:center;">
			<div id="item-rating-id" class="rating">
				<img alt="poor rating" id="rating-item-0" onclick="rateItem(0, ${item.id});  return false;" src="/images/gr_orange_star_active.gif" title="Poor Review" width="15" height="15" />
				<img alt="poor rating" id="rating-item-1" onclick="rateItem(1, ${item.id});  return false;" src="/images/gr_orange_star_active.gif" title="Below Average" width="15" height="15" />
				<img alt="poor rating" id="rating-item-2" onclick="rateItem(2, ${item.id});  return false;" src="/images/gr_orange_star_active.gif" title="Average" width="15" height="15" />
				<img alt="poor rating" id="rating-item-3" onclick="rateItem(3, ${item.id});  return false;" src="/images/gr_orange_star_active.gif" title="Good" width="15" height="15" />
				<img alt="poor rating" id="rating-item-4" onclick="rateItem(4, ${item.id});  return false;" src="/images/gr_orange_star_active.gif" title="Excellent" width="15" height="15" />			
			</div>	
	</div> 
	
	<div class="clear"></div>	
	<div style="font-size:10px;">Posted by <a href="/secure/friendPage.do?friendId=${item.person.id}">${item.vendorName}</a> <fmt:formatDate value="${item.enteredDate}" pattern="MM/dd/yyyy" /></div>
	<div class="clear"></div>
	
	<div id="user-item-image" class="item-bg" style="float: left;">
		<div class="item-header">
			<div class="clear"></div>
			<div class="clear"></div>		
			<a href="#" onclick="showPhoto('${item.itemName}');">
			<img src="../${item.thumbnail.path}" title="${item.itemName}" alt="${item.description}" /></a>  
		</div>
	</div>

	<div id="user-item-description" class="item-information">
		<div>
			<h3>${item.title}</h3>
			<h4>${item.subtitle}</h4>
		</div>
	</div>
	<div class="clear"></div>
		
	<div class="clear"></div>	
	<p class="item-description">${item.description}</p>
	<div class="clear"></div>
	
	<div style="display:none;" id="item-overlay">  
		<img src="../${item.glimpse.path}" title="${item.itemName}" alt="${item.description}" />
	</div>	
	
	<div class="clear"></div>
	<div>
		<strong>Tags</strong> 
		<c:forEach items="${item.tags}" var="tag">
				${tag.tag}
		</c:forEach>
	</div>
	
	<div style="vertical-align: top;padding:10px;"><a href="javascript:void(0);" onclick="takeActionOnItemPage('${item.id}', '${item.itemName}');" ><img src="/images/takeaction.png" /></a></div>
	<table id="take-action-on-item-page-parent-element" style="display:none;">
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
	<div class="clear"></div>
	<div class="clear"></div>
	<div id="vendor-item-all-comments" style="clear:both;background-color:#FFF;width:720px;">
		<jsp:include page="../item/itemComments.jsp"></jsp:include>
	</div>
	
</div>
