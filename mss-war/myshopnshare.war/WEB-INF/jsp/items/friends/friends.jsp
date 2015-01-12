<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns"%>

<script type="text/javascript">
	
	function taskFriendsSuccessful(items, category) {
		$('#item-friends-' + category + ' .the-items').html('');

		switch (category) {
		case 'ADVICE':
			cyclefriendsADVICE(items, category);
			break;
		case 'BOUGHT':
			cyclefriendsBOUGHT(items, category);
			break;
		case 'SELLING':
			cyclefriendsSELLING(items, category);
			break;
		case 'WANT':
			cyclefriendsWANT(items, category);
			break;
		case 'RECOMMEND':
			cyclefriendsRECOMMEND(items, category);
			break;

		default:
		}
	}

	function hideFriendsAll(){
		$('#item-friends-ADVICE').hide();
		$('#item-friends-WANT').hide();
		$('#item-friends-BOUGHT').hide();
		$('#item-friends-SELLING').hide();
		$('#item-friends-RECOMMEND').hide();
	}

	// Logic here goes in search - use this in search when wanting to grab an item and so forth.
	var url = '/secure/search.do';

	function getFriendsItems(method, category) {
		var searchString = $('#friends-search_text').val();
		$.ajax( {
			type :"POST",
			url :"/secure/search.do",
			data : {
				method :method,
				category :category,
				personId: '${person.id}',
				searchString: searchString
			},
			dataType :'json',
			success : function(json) {
				$('#friends-search_text').attr('value', '');
				taskFriendsSuccessful(json.items, category);
			}
		});
	}

	$(document).ready(function() {
		/** ALL ITEMS **/
		if ($("input[@name='items-friends-filter']:checked").val()) {
			hideFriendsAll();
			var selected = $("input[@name='items-friends-filter']:checked").attr('id');
			var filter = selected.replace('items-friends-', '');	
			$('#item-friends-'+filter).show();
			getFriendsItems('allfriends', filter);
		}

		$('#friends-search_submit').click(function(){
			if ($("input[@name='items-friends-filter']:checked").val()) {
			 	selected = $("input[@name='items-friends-filter']:checked").attr('id');
				filter = selected.replace('items-friends-', '');
				hideFriendsAll();
				$('#item-friends-'+filter).show();
				getFriendsItems('allfriends', filter);
			}			
		});
		
		$("input[@name='items-friends-filter']").click(function() {
			if ($("input[@name='items-friends-filter']:checked").val()) {
			 	selected = $("input[@name='items-friends-filter']:checked").attr('id');
				filter = selected.replace('items-friends-', '');
				hideFriendsAll();
				$('#item-friends-'+filter).show();
				getFriendsItems('allfriends', filter);
			}
		});
	});

</script>

<div id="items-friends-box" class="myshopnshare-content items-box">
	<div class="myshopnshare-header">
		<h3><img src="/images/Treasure-chest-closed-icon.gif" width="15px" height="15px" /> Friends Shopping Items</h3>
	</div>

	<div id="items-radio-buttons" class="action-items">
		<div><a href="#" onclick="filterActionItems('ADVICE');"><img src="/images/advice.png" /></a></div>
		<div><a href="#" onclick="filterActionItems('BOUGHT');"><img src="/images/BOUGHT.png"/></a></div>
		<div><a href="#" onclick="filterActionItems('WANT');"><img src="/images/WISH.png"/></a></div>
		<div><a href="#" onclick="filterActionItems('RECOMMEND');"><img src="/images/RECOMMEND.png"/></a></div>
		
		<ul style="float:right;">
			<li>
				<div style="vertical-align:bottom;">
				<input id="friends-search_text" type="text" name="friendsSearchString" />
				<a href="javascript: void(0);" id="friends-search_submit" alt="[Submit]" title="search only your friends list of items filtered by selected category"><img src="/images/filter.png" /></a>
				</div>
			</li>
		</ul>
	</div>	

	<%--div id="items-friends-radio-buttons" class="items-radio-buttons">
		<div ><input id="items-friends-ADVICE" type="radio" name="items-friends-filter" value="allfriends" checked="checked" /></div><span><img src="/images/questionmark.png" width="15" height="15" /> ADVICE</span>
		<div ><input id="items-friends-BOUGHT" type="radio" name="items-friends-filter" value="allfriends" /></div><span><img src="/images/dollarsign.png"/> BOUGHT</span>
		<div><input id="items-friends-WANT" type="radio" name="items-friends-filter" value="allfriends" /></div><span><img src="/images/want.gif"/> WISH</span>
		<div ><input id="items-friends-RECOMMEND" type="radio" name="items-friends-filter" value="allfriends" /></div><span><img src="/images/thumbup.png" width="15" height="15" /> RECOMMEND</span>
		
		<ul style="float:right;">
			<li>
				<table>
					<tr>
						<td><input id="friends-search_text" type="text" name="friendsSearchString" /></td>
						<td style="vertical-align:bottom;">
							<a href="javascript: void(0);" id="friends-search_submit" alt="[Submit]" title="search only your friends list of items filtered by selected category"><img src="/images/filter.png" /></a>
						</td>
					</tr>
				</table>
			</li>
		</ul>			
	</div--%>
	
	<%@ include file="advice.jsp" %>
	<%@ include file="bought.jsp" %>
	<%@ include file="want.jsp" %>
	<%@ include file="recommend.jsp" %>

</div>
