<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

<script type="text/javascript">	 		

	$(document).ready(function(){
		clearItemStars();
		makeItemActive('${item.itemRating.rating/item.itemRating.count}');
	});

	function takeActionOnItemPage(recordId, itemName){
		if($('#take-action-on-item-vendor-page').is(':visible')){
			$('#take-action-on-item-vendor-page').hide();
		}else{
			$('#take-action-on-item-vendor-page').show();
		}
		search.itemIds.push(recordId);
		search.itemNames.push(itemName);
	}
	
	function pleaseLogIn(){
		Boxy.alert('Please log in to rate items.');
	}

	function pleaseLogIn2(){
		Boxy.alert('Please log in to use this feature.');
	}	
</script>

	<script type="text/javascript" src="/js/jquery.overlay-0.14.js" />
	<link rel="stylesheet" media="screen,projection" type="text/css" href="/css/overlay.css" />
		
		
<script type="text/javascript">	

	function displayMoreDetails(recordId){
		$.ajax( {
			type :"post",
			url :"/public/vendorItemPage.do",
			data: {method: 'view', itemId: recordId},
			success : function(data) {
				$('#user-item-page').html(data);
			}
		});	
	}		

</script>

<c:set var="isLoggedIn" value="no" />
	<sec:authorize ifAllGranted="ROLE_MERCHANT">
	  <c:set var="isLoggedIn" value="yes"/>
	</sec:authorize>
	

<div id="user-item-page">
	<div id="vendor-item-back-page" class="item-inner-wrap" style="height:50px;width:800px; background-image: url(/images/forsale.png); background-repeat: no-repeat;background-position:center;"><a href="#" onclick="backToSearch()"><img src="/images/back.png" /></a></div>	

	<div style="padding:20px;width:450px;height:220px;background-image: url(/images/loginbg.png); background-repeat: no-repeat;">
	<h3>${item.itemName}</h3>
	<div class="clear"></div>
	<div style="font-size:10px;">Posted by <a href="/secure/friendPage.do?friendId=${item.person.id}">${item.vendorName}</a> &nbsp;&nbsp; <fmt:formatDate value="${item.enteredDate}" pattern="MM/dd/yyyy" /></div>
	<div class="clear"></div>
	<div style="font-size:10px;">Resource provided by <a href="${item.resourceLink}">${item.resourceLink}</a></div>
	<div class="clear"></div>
	<strong>Serial Number</strong> ${item.serialNumber}
	
	<div style="font-size:10px;"><strong><a href="http://maps.google.com/maps/geo?KeepThis=true&TB_iframe=true&height=450&width=740&q=${item.location}&output=json&oe=utf8&sensor=true_or_false&key=ABQIAAAAMNNNy34UOUsl9aq0D4kvcBSCJrqMDY1plGogyxSzH0ceUbbsxxRnTZItpUWKC3_XCWAeFxOjOU8Q4Q" class="thickbox" title="${item.itemName} @ ${item.location}">Map</a></strong></div>
	
	<div style="color:red;">Please log in to rate items.</div>
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

	</DIV>
	<div style="position:relative;">
		<div id="user-item-image" class="item-bg" style="float: left;">
			<div class="item-header">
				<div class="clear"></div>
				<div class="clear"></div>				
				<a href="#" onclick="showPhoto('${item.itemName}');">
				<img src="../${item.thumbnail.path}" title="${item.itemName}" alt="${item.description}" />
				</a>				
			</div>
			<c:choose>
				<c:when test="${item.quantity == 0}">
					<div class="errors"><b>Out of stock</b></div>
				</c:when>	
				<c:otherwise>
					<div class="vendor-shopping-element" >
						<div style="float: left;">Add this item to your shopping cart.</div>
						<div id="show-buy-item" style="float: right;padding-right:10px;">							
							<a href="javascript: void(0);" onclick="showBuyItem();"><img src="/images/insert_to_shopping_cart.png" width="50px" height="50px" title="Add To Cart" alt="Add To Cart" /></a>
							<div id="buy-item-element" style="display:none;">
								<form id="add-to-shopping-cart-item-form">
									<input type="hidden" name="type" value="${item.type}" />
									<input type="hidden" name="itemId" value="${item.id}" />
									<div style="width: 2px;"><input type="text" name="quantity" value="1" size="1" /></div>
								</form>
								<div id="buy-item-button"><a style="color: #005500;" href="javascript: void(0);" onclick="buyItem();">Add</a></div>
							</div>
						</div>		
					</div>					
				</c:otherwise>		
			</c:choose>				
		</div>		
	<div id="user-item-description" class="item-information" style="width:450px;height:260px;background-image: url(/images/loginbg.png); background-repeat: no-repeat;">
		<div>
				<div style="overflow:hidden; height:30px;padding:5px 20px;font-size:18px;">${item.subtitle}</div>
				<div style="height: 115pt; padding: 20px; font-size: 12px;">
					<strong>Price</strong> $${item.price}
					<div class="clear"></div>
					<strong>Product Name</strong> ${item.itemName}
					<div class="clear"></div>
					<strong>Location</strong> ${item.location}
					<div class="clear"></div>
					<strong>Manufacturer</strong> ${item.manufacturer}
					<div class="clear"></div>
					<c:if test="${item.products}">
						<strong>New or Used</strong> <c:choose><c:when test="${item.refuberish}">Refurbished</c:when><c:when test="${item.used}"><div>Used</div></c:when><c:otherwise>New</c:otherwise></c:choose>
						<c:if test="${item.rebateAmount > 0}"><div><strong>Rebate Item</strong> <big>$${item.rebateAmount}</big> mail in rebate</div></c:if>
						<c:if test="${item.clearancePercentage > 0}"><div><strong>Clearance Item</strong> <big>${item.clearancePercentage}%</big> off original price</div></c:if>
					</c:if>
					<div class="clear"></div>				
					
					<div id="buy-message" style="color:#CCC;">
					</div>				
				</div>
				<div style="margin-top:15px;padding:5px; height: 20pt; float: right; width: 200px;">	
					<div style="position:relative; text-align:right;">
						<div class="item-logistics">Shipping</div> <div>$${item.shipping} </div>
						<div class="item-logistics">Tax</div> <div>$${item.tax}</div>
					</div>
				</div>
				<div class="clear"></div>
			</div>
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
	
	<div style="vertical-align: top;padding:10px;"><a href="javascript:void(0);" onclick="takeActionOnItemPage('${item.id}', '${item.itemName}');"><img src="/images/takeaction.png" /></a></div>

	<table id="take-action-on-item-vendor-page" style="display:none;">
		<tr><td style="color:red;">Please log in to use this feature.</td>
		</tr>
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
	
	<div id="vendor-item-all-comments" style="clear:both;background-color:#FFF;width:720px;">
		<jsp:include page="../item/publicItemComments.jsp"></jsp:include>
	</div>

</div>