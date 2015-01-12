<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns"%>

<script type="text/javascript">
	function taskWorldSuccessful(items, category) {
		$('#item-world-' + category + ' .the-items').html('');

		switch (category) {
		case 'CLEARANCE':
			cycleworldCLEARANCE(items, category);
			break;
		case 'HOTTEST':
			cycleworldHOTTEST(items, category);
			break;
		case 'LATEST':
			cycleworldLATEST(items, category);
			break;
		case 'FREE':
			cycleworldFREE(items, category);
			break;
		case 'REBATE':
			cycleworldREBATE(items, category);
			break;
		case 'CHEAPEST':
			cycleworldCHEAPEST(items, category);
			break;
		default:
		}
	}

	function hideWorldAll(){
		$('#item-world-CLEARANCE').hide();
		$('#item-world-FREE').hide();
		$('#item-world-LATEST').hide();
		$('#item-world-HOTTEST').hide();
		$('#item-world-REBATE').hide();
		$('#item-world-CHEAPEST').hide();
	}

	function getWorldItems(method, category) {
		var searchString = $('#world-search_text').val();
		$.ajax( {
			type :"post",
			url :"/public/searchProducts.do",
			data : {
				method :method,
				personId: '${person.id}',
				searchString: searchString
			},
			dataType :'json',
			success : function(json) {
				$('#world-search_text').attr('value', '');
				taskWorldSuccessful(json.items, category);
			}
		});
	}

	$(document).ready(function() {
		/** ALL ITEMS **/
		if ($("input[@name='items-world-filter']:checked").val()) {
			hideWorldAll();
			var selected = $("input[@name='items-world-filter']:checked").attr('id');
			var filter = selected.replace('items-world-', '');	
			$('#item-world-' + filter).show();
			getWorldItems($("input[@name='items-world-filter']:checked").val(), filter);
		}

		$('#world-search_submit').click(function(){
			if ($("input[@name='items-world-filter']:checked").val()) {
			 	selected = $("input[@name='items-world-filter']:checked").attr('id');
				filter = selected.replace('items-world-', '');
				hideWorldAll();
				$('#item-world-' + filter).show();
				getWorldItems($("input[@name='items-world-filter']:checked").val(), filter);
			}		
		});
		
		$("input[@name='items-world-filter']").click(function() {
			if ($("input[@name='items-world-filter']:checked").val()) {
			 	selected = $("input[@name='items-world-filter']:checked").attr('id');
				filter = selected.replace('items-world-', '');
				hideWorldAll();
				$('#item-world-' + filter).show();
				getWorldItems($("input[@name='items-world-filter']:checked").val(), filter);
			}
		});
	});

</script>

<div id="items-world-box" class="myshopnshare-content items-box">
	<div class="myshopnshare-header">
	<h3><img src="/images/insert_to_shopping_cart.png" width="20px" height="20px" /> Go Shopping</h3>
	</div>
	<div id="items-world-radio-buttons" class="items-radio-buttons">
		<div ><input id="items-world-LATEST" type="radio" name="items-world-filter" value="all" checked="checked" /></div>
		<span>NEW</span>
		<div ><input id="items-world-FREE" type="radio" name="items-world-filter" value="free" /></div>
		<span>FREE</span>
		<div ><input id="items-world-HOTTEST" type="radio" name="items-world-filter" value="hottest" /></div>
		<span>HOT</span>
		<div ><input id="items-world-REBATE" type="radio" name="items-world-filter" value="rebates" /></div>
		<span>REBATES</span>
		<div ><input id="items-world-CLEARANCE" type="radio" name="items-world-filter" value="clearance" /></div>
		<span>CLEARANCE</span>
		<div ><input id="items-world-CHEAPEST" type="radio" name="items-world-filter" value="cheapest" /></div>
		<span>DEALS</span>

		<ul style="float:right;">
			<li>
					<input id="world-search_text" class="search_inactive" type="text" name="worldSearchString" />
					<a href="javascript: void(0);" id="world-search_submit" alt="[Submit]" title="Search entire site for items filtered by selected category"><img src="/images/filter.png" /></a>
			</li>
		</ul>			
	</div>
	
	<%@ include file="cheapest.jsp" %>
	<%@ include file="clearance.jsp" %>
	<%@ include file="free.jsp" %>
	<%@ include file="hottest.jsp" %>
	<%@ include file="latest.jsp" %>
	<%@ include file="rebate.jsp" %>

</div>
