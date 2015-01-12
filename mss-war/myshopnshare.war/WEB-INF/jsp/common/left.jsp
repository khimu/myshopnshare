<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">
	function viewPublicPointItemDetail(itemId){
		$.ajax( {
			type :"post",
			url :"/public/searchPoints.do",
			data: {method: 'get', itemId: itemId},
			dataType: 'html',
			success : function(data) {
				$('#center').hide();
				$('#center-3').hide();
				$('#center-2').show(data);
	
				$('#center-2').html(data);
		    }
		});				
	}
	
	function showPoints(vendorId){
		$('#all-item-category-element').html('');
		$('#center').hide();
		$('#center-3').show();	
		$.ajax( {
			type :"post",
			url :"/public/searchPoints.do",
			data: {method: 'view', vendorId: vendorId},
			dataType: 'json',
			success : function(json) {
				$('#points-items-by-category').html('');
				$.each(json.items, function(i, item) {
					$('#points-items-by-category').append(
							'<sns:rewardsitems itemName="' + item.itemName + '" primaryKey="' + item.id + '" caption="' + item.caption
							+ '" description="' + item.description + '" icon="'
							+ item.icon + '" glimpse="' + item.glimpse
							+ '" />');
					
				}); 
				
				$("a[rel^='prettyPhoto']").prettyPhoto();
		    }
		});				
	}
	
	function showOrders(){
		$.ajax( {
			type :"post",
			url :"/secure/orders.do",
			data: {method: 'view'},
			dataType: 'html',
			success : function(data) {
				$('#center-2').hide();
				$('#center-3').hide();
				$('#center').show();
				$('#center').html(data);
		    }
		});				
	}
	
	function showBuys(){
		$.ajax( {
			type :"post",
			url :"/secure/buys.do",
			data: {method: 'view'},
			success : function(data) {
				$('#center-2').hide();
				$('#center-3').hide();
				$('#center').show();
				$('#center').html(data);
		    }
		});				
	}
	
	function showSells(){
		$.ajax( {
			type :"post",
			url :"/secure/sells.do",
			data: {method: 'view'},
			success : function(data) {
				$('#center-2').hide();
				$('#center-3').hide();
				$('#center').show();
				$('#center').html(data);
		    }
		});				
	}	

	$(function() {
		$('#show-events-form').click(function(){
			 $.ajax({  
			       type: "post",  
			       url: "/secure/events.do",
			       data: {method: 'form'},
			       success: function(data){  
						$('#center').html(data);
						window.location = '#';
			       }
			 });
		});		

	});	
		
		
	function showHideEventList(elementId){
		if($(elementId).is(':visible')){
			$(elementId).hide();
		}else{
			$(elementId).show();
		}
	}

	function showHideAboutMe(){
		if($('#about-me').is(':visible')){
			$('#about-me').hide();
			$('#aboutmeopenclose img').attr('src', '/images/close.png');
		}else{
			$('#about-me').show();
			$('#aboutmeopenclose img').attr('src', '/images/open.png');
		}
	}

	function showHideEvents(){
		if($('#events-accordion').is(':visible')){
			$('#events-accordion').hide();
		}else{
			$('#events-accordion').show();
		}
	}	
	
</script>
<div id="col" class="noprint">
    <div id="col-in">

    <h3><span id="aboutmeopenclose"><a href="javascript:void(0);" onclick="showHideAboutMe();"><img src="/images/close.png" /></a></span>&nbsp;&nbsp;<span>Welcome ${person.fullName}</span></h3>

 	<div id="about-me">    
		<a href="#TB_inline?height=310&width=500&inlineId=friendrequestpage&model=true" class="thickbox">
			${fn:length(friendRequests)} Friend Request
		</a> 					

		<div class="spacer"></div>
        Viewed: ${person.viewed.count}
        <div>
        
        <c:forEach items="${person.activeFaces}" var="face">
        	<img src="../${face.mini.path}" />
        </c:forEach>
        </div>
      <div class="clear"></div>
      <div style="background-color: #FFF;color:#000;text-align:center;">
	      <a href="#" onclick="showPossessions();"><span>${fn:length(person.activeItems)}</span></a> Items
	      <div class="clear"></div>
	      <a href="#" onclick="showPossessions();"><img src="/images/uploadimage.png" /></a>
      </div>
    </div> 
    
    <hr class="noscreen" />
    <div class="clear"></div>

	<div style="text-align:center;">
			<div id="rating-id" class="rating">
				<img alt="poor rating" id="rating0" onclick="submitStars(0);  return false;" src="/images/gr_orange_star_active.gif" title="Poor Review" width="15" height="15" />
				<img alt="poor rating" id="rating1" onclick="submitStars(1);  return false;" src="/images/gr_orange_star_active.gif" title="Below Average" width="15" height="15" />
				<img alt="poor rating" id="rating2" onclick="submitStars(2);  return false;" src="/images/gr_orange_star_active.gif" title="Average" width="15" height="15" />
				<img alt="poor rating" id="rating3" onclick="submitStars(3);  return false;" src="/images/gr_orange_star_active.gif" title="Good" width="15" height="15" />
				<img alt="poor rating" id="rating4" onclick="submitStars(4);  return false;" src="/images/gr_orange_star_active.gif" title="Excellent" width="15" height="15" />			
			</div>
	</div>  

    <hr class="noscreen" />
    
    <div class="clear"></div>

	<%@ include file="invites.jsp" %>
	<%@ include file="../common/contacts.jsp" %>
    <hr class="noscreen" />
    <ul id="links">
    	<li><a href="javascript: void(0);" onclick="showContactInfo();" title="Contact info for ${person.fullName}">Show Contact Info</a></li>
    	<li><a href="javascript: void(0);" onclick="showInvites();">Invite friends to join MY SHOP N SHARE</a></li>
    </ul>
    <hr class="noscreen" />

    <ul id="archive">
       <li><a href="#" onclick="showBuys();">Buys</a></li>
       <c:if test="${person.userType eq 'MERCHANT'}"><li><a href="#" onclick="showSells();">Sells</a></li></c:if>
    </ul>

    <hr class="noscreen" />	
    	
	<%@ include file="../common/bank.jsp" %>
			
    <hr class="noscreen" />

    <h3><span>Friends &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="/secure/manageFriends.do?method=all&personId=${person.id}">${fn:length(person.friends)}</a></span></h3>
    <%@ include file="../friends/friends.jsp" %>	
    </div> 

	    <div>
	<script type="text/javascript"><!--
	google_ad_client = "pub-2178827339426285";
	/* 200x200, created 5/18/09 */
	google_ad_slot = "8175680661";
	google_ad_width = 200;
	google_ad_height = 200;
	//-->
	</script>
	<script type="text/javascript"
	src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
	</script>    
	    </div>
    
</div> 





		

