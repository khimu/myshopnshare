<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns"%>

<script type="text/javascript">
	function taskServiceSuccessful(items, category) {
		$('#item-service-' + category + ' .the-items').html('');

		switch (category) {
		case 'CLEARANCE':
			cycleserviceCLEARANCE(items, category);
			break;
		case 'HOTTEST':
			cycleserviceHOTTEST(items, category);
			break;
		case 'LATEST':
			cycleserviceLATEST(items, category);
			break;
		case 'FREE':
			cycleserviceFREE(items, category);
			break;
		case 'REBATE':
			cycleserviceREBATE(items, category);
			break;
		case 'CHEAPEST':
			cycleserviceCHEAPEST(items, category);
			break;
		default:
		}
	}

	function hideServiceAll(){
		$('#item-service-CLEARANCE').hide();
		$('#item-service-FREE').hide();
		$('#item-service-LATEST').hide();
		$('#item-service-HOTTEST').hide();
		$('#item-service-REBATE').hide();
		$('#item-service-CHEAPEST').hide();
	}

	function getServiceItems(method, category) {
		var searchString = $('#service-search_text').val();
		$.ajax( {
			type :"POST",
			url :"/public/searchServices.do",
			data : {
				method :method,
				personId: '${person.id}',
				searchString: searchString
			},
			dataType :'json',
			success : function(json) {
				$('#service-search_text').attr('value', '');
				taskServiceSuccessful(json.items, category);
			}
		});
	}

	$(document).ready(function() {
		/** ALL ITEMS **/
		if ($("input[@name='items-service-filter']:checked").val()) {
			hideServiceAll();
			var selected = $("input[@name='items-service-filter']:checked").attr('id');
			
			var filter = selected.replace('items-service-', '');	
			$('#item-service-' + filter).show();
			getServiceItems($("input[@name='items-service-filter']:checked").val(), filter);
		}

		$('#service-search_submit').click(function(){
			if ($("input[@name='items-service-filter']:checked").val()) {
			 	selected = $("input[@name='items-service-filter']:checked").attr('id');
				filter = selected.replace('items-service-', '');
				hideServiceAll();
				$('#item-service-' + filter).show();
				getServiceItems($("input[@name='items-service-filter']:checked").val(), filter);
			}		
		});
		
		$("input[@name='items-service-filter']").click(function() {
			if ($("input[@name='items-service-filter']:checked").val()) {
			 	selected = $("input[@name='items-service-filter']:checked").attr('id');
				filter = selected.replace('items-service-', '');
				hideServiceAll();
				$('#item-service-' + filter).show();
				getServiceItems($("input[@name='items-service-filter']:checked").val(), filter);
			}
		});
	});

</script>

<div id="items-service-box" class="myshopnshare-content items-box">
	<div class="myshopnshare-header">
	<h3>Services</h3>
	</div>
	<div id="items-service-radio-buttons" class="items-radio-buttons">
		<div ><input id="items-service-LATEST" type="radio" name="items-service-filter" value="all" checked="checked" /></div>
		<span>NEW</span>
	
		<div ><input id="items-service-FREE" type="radio" name="items-service-filter" value="free" /></div>
		<span>FREE</span>
		
		<div ><input id="items-service-HOTTEST" type="radio" name="items-service-filter" value="hottest" /></div>
		<span>HOT</span>
		<div ><input id="items-service-REBATE" type="radio" name="items-service-filter" value="rebates" /></div>
		<span>REBATES</span>
		<div ><input id="items-service-CLEARANCE" type="radio" name="items-service-filter" value="clearance" /></div>
		<span>CLEARANCE</span>

		<div ><input id="items-service-CHEAPEST" type="radio" name="items-service-filter" value="cheapest" /></div>
		<span>DEALS</span>
		
		<ul style="float:right;">
			<li>
				<table>
					<tr>
						<td><input id="service-search_text" type="text" name="serviceSearchString" /></td>
						<td style="vertical-align:bottom;">
						<a href="javascript: void(0);" id="service-search_submit" alt="[Submit]" title="Search entire site for items filtered by selected category"><img src="/images/filter.png" /></a>
						</td>
					</tr>
				</table>
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
