<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns"%>

<style type="text/css">
	.action-items {
		position:relative;
	}
	
	.action-items div {
		float: left;
	}
</style>

<script type="text/javascript">

	function taskSuccessful(items, category) {
		$('#item-' + category + ' .the-items').html('');
		
		switch (category) {
		case 'ADVICE':
			cycleADVICE(items, category);
			break;
		case 'BOUGHT':
			cycleBOUGHT(items, category);
			break;
		case 'SELLING':
			cycleSELLING(items, category);
			break;
		case 'WANT':
			cycleWANT(items, category);
			break;
		case 'RECOMMEND':
			cycleRECOMMEND(items, category);
			break;

		default:
		}
	}

	function hideAll(){
		$('#item-ADVICE').hide();
		$('#item-WANT').hide();
		$('#item-BOUGHT').hide();
		$('#item-SELLING').hide();
		$('#item-RECOMMEND').hide();
	}

	function getItems(method, category) {
		var searchString = $('#self-search_text').val();
		$.ajax( {
			type :"post",
			url :"/secure/possessions.do",
			data : {
				method :method,
				category :category,
				personId: '${person.id}',
				searchString: searchString
			},
			dataType :'json',
			success : function(json) {
				$('#self-search_text').attr('value', '');
				taskSuccessful(json.items, category);
			}
		});
	}

	$(document).ready(function() {		
		/** ALL ITEMS **/
		if ($("input[@name='items-filter']:checked").val()) {
			hideAll();
			var selected = $("input[@name='items-filter']:checked").attr('id');
			
			var filter = selected.replace('items-', '');	
			$('#item-'+filter).show();

			$.ajax( {
				type :"post",
				url :"/secure/possessions.do",
				data : {
					method :$("input[@name='items-filter']:checked").val(),
					category :filter,
					personId: '${person.id}'
				},
				dataType :'json',
				success : function(json) {
					$('#self-search_text').attr('value', '');
					$('#item-' + filter + ' .the-items').html('');
					cycleadviceinitialINITIAL(json.items, filter);
				}
			});			
		}

		$('#self-search_submit').click(function(){
			if ($("input[@name='items-filter']:checked").val()) {
			 	selected = $("input[@name='items-filter']:checked").attr('id');
				filter = selected.replace('items-', '');
				hideAll();
				$('#item-'+filter).show();
				getItems($("input[@name='items-filter']:checked").val(), filter);
			}			
		});
		
		$("input[@name='items-filter']").click(function() {
			if ($("input[@name='items-filter']:checked").val()) {
			 	selected = $("input[@name='items-filter']:checked").attr('id');
				filter = selected.replace('items-', '');
				hideAll();
				$('#item-'+filter).show();
				getItems($("input[@name='items-filter']:checked").val(), filter);
			}
		});
	});




	
</script>

<div id="items-box" class="myshopnshare-content items-box">
	<div class="myshopnshare-header">
		<h3><img src="/images/Treasure-chest-closed-icon.gif" width="15px" height="15px" /> My Shopping Items</h3>
	</div>
	<div id="items-radio-buttons" class="action-items">
		<div><a href="#" onclick="filterActionItems('ADVICE');"><img src="/images/advice.png" /></a></div>
		<div><a href="#" onclick="filterActionItems('BOUGHT');"><img src="/images/BOUGHT.png"/></a></div>
		<div><a href="#" onclick="filterActionItems('WANT');"><img src="/images/WISH.png"/></a></div>
		<div><a href="#" onclick="filterActionItems('RECOMMEND');"><img src="/images/RECOMMEND.png"/></a></div>
		<%--
		<div><input id="items-ADVICE" type="radio" name="items-filter" value="search" checked="checked" /></div><span><img src="/images/questionmark.png" width="15" height="15" /> ADVICE</span>
		<div ><input id="items-BOUGHT" type="radio" name="items-filter" value="search" /></div><span><img src="/images/dollarsign.png"/> BOUGHT</span>
		 
		<div ><input id="items-WANT" type="radio" name="items-filter" value="search" /></div><span><img src="/images/want.gif"/> WISH</span>
		<div ><input id="items-RECOMMEND" type="radio" name="items-filter" value="search" /></div><span><img src="/images/thumbup.png" width="15" height="15" /> RECOMMEND</span>
		--%>
		
		<ul style="float:right;">
			<li>
				<div style="vertical-align:bottom;">
				<input id="self-search_text" type="text" name="selfSearchString" />
				<a href="javascript: void(0);" id="self-search_submit" alt="[Submit]" title="Search personal items filtered by selected category" ><img src="/images/filter.png"/></a>
				</div>
			</li>
		</ul>
	</div>
	
	<%@ include file="adviceInitial.jsp" %>
	<%@ include file="advice.jsp" %>
	<%@ include file="bought.jsp" %>
	<%@ include file="want.jsp" %>
	<%@ include file="recommend.jsp" %>

</div>
