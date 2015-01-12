<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

<script type="text/javascript">	 		

	function takeActionOnItemPage(){
		if($('#take-action-on-item-page-parent-element').is(':visible')){
			$('#take-action-on-item-page-parent-element').hide();
		}else{
			$('#take-action-on-item-page-parent-element').show();
		}
	}
	
</script>

	<div style="vertical-align: top;padding:10px;"><a href="javascript:void(0);" onclick="takeActionOnItemPage();" ><img src="/images/takeaction.png" /></a></div>
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

	       
		<div id="all-products" style="position: relative;clear:both;">
			<div style="float: left;padding: 5px;margin: 5px;">
				<div id="new-item-uploaded">
				<c:forEach items="${person.activeItems}" var="item">
						<sns:publicmerchantsearchitemimagerecord itemName="${item.itemName}" caption="${item.itemName}" description="${item.description}" icon="/${item.icon.path}" glimpse="/${item.glimpse.path}" primaryKey="${item.id}" />		
				</c:forEach>
				</div>
			</div>
		</div>	       