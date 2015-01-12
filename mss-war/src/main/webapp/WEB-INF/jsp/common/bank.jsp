<%@ include file="/WEB-INF/jsp/includes.jsp"%>


    <h3><span id="rewardspointsopenclose"><a href="javascript:void(0);" onclick="if($('#category').is(':visible')){$('#category').hide();$('#rewardspointsopenclose img').attr('src', '/images/close.png');}else{$('#category').show();$('#rewardspointsopenclose img').attr('src', '/images/open.png');}"><img src="/images/close.png" /></a></span>&nbsp;&nbsp;<span>Redeemable Points</span></h3>

    <ul id="category" style="display: none;">
    	<li>Points For Vendors</li>
    	
    	<c:forEach items="${rewardsPerVendor}" var="rewardPerVendor">
 	        <li><a href="#" onclick="showPoints('');" title="Points are reedemable for rewards items offered by specific merchants of those reward points.">${rewardPerVendor}</a></li>    	
    	</c:forEach>
    	<li>Total Points</li>
   	 	<c:if test="${not empty person.bank}">
	        <li><a href="#" onclick="showPoints('');" title="Points are reedemable for rewards items offered by specific merchants of those reward points.">$ ${person.bank.balance}</a></li> 
	        <li><a href="#" title="Discounts are rewards given by merchants for purchases made on their items."><label for="discounts">Discounts: </label>$ ${person.bank.discounts}</a></li>
        </c:if>
    </ul>

    <hr class="noscreen" />	