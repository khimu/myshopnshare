<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">

	$(document).ready(function(){
		$.ajax( {
			type :"get",
			url :"/secure/rating.do",
			data: {method: 'view', personId: '${person.id}'},
			dataType: 'json',
			success : function(json) {
				clearStars();
				makeActive(json.rating);
		    }
		});
	});

	function showCart(){
		$.ajax( {
			type :"post",
			url :"/secure/cart.do",
			data: {method: 'view'},
			dataType: 'html',
			success : function(data) {
				$('#center').show();
				$('#center-2').hide();
				$('#center-3').hide();
				$('#center').html(data);
		    }
		});				
	}

	function submitStars(theRating){
		$.ajax( {
			type :"post",
			url :"/secure/rating.do",
			data: {method: 'rate', rating: theRating, personId: '${person.id}'},
			dataType: 'json',
			success : function(json) {
				clearStars();
				makeActive(json.rating);
		    }
		});	
	}

	function clearStars(){
		var startRating = 0;	
		while(startRating < 5){
			$('#rating'+startRating).attr('src', '/images/gr_orange_star_unactive.gif');
			startRating ++;
		}
	}
	
	function makeActive(theRating){
		var startRating = 0;
		while(startRating < theRating){
			$('#rating'+startRating).attr('src', '/images/gr_orange_star_active.gif');
			startRating ++;
		}
	}
	
	function checkStars(theRating){
		clearStars();
		makeActive(theRating);
	}
	
	function friendRequests(){
		$.ajax( {
			type :"post",
			url :"/secure/manageRequest.do",
			success : function(data) {
				$('#center').html(data);
		    }
		});				
	}

	$(document).ready(function(){	 	
		// $('#rating-id').rating('/secure/rating.do?method=rate', {maxvalue:5, curvalue: 3});
   		$.ajax( {
   			type :"post",
   			url :"/secure/cart.do",
   			data: {method: 'get'},
   			dataType: 'json',
   			success : function(json) {
   				$('#shopping-cart-size').html('<a href="#" onclick="showCart();"><img src="/images/insert_to_shopping_cart.png" width="30" height="30" alt="" title="" />Shopping Cart: ' + json.cartSize + '</a>');
   		    }
   		});		
	});

	function showFullFace(title){
		new Boxy('#face-overlay', {title: title, modal: true});
	}
    
</script>


    <%-- Header --%>
    <div id="header" style="height: 120px;margin: 0 7px;">
    	<div style="padding: 5px;">
			<img src="/images/myshopnsharelogo2.png" />
		</div>
		<div style="position:absolute;top:5px;right:340px;">
			Age: ${person.age}<br />
			<c:if test="${not empty primaryAddress}">
				${primaryAddress.country}<br />
			</c:if>
			AIM: <c:if test="${not empty person.profile.aim}"><a href="aim:GoIM?screenname=screenname"><img src="http://api.oscar.aol.com/presence/icon?k=mi15Xbwp687KXwRc&t=${person.profile.aim}"/>${person.profile.aim}</a></c:if><br />
			Birthday: <fmt:formatDate value="${person.birthday}" pattern="MM/dd" />	
		</div>
    
		<div style="position:absolute;top:5px;right:350px;">		
			<div style="height:70px;"><a href="#" onclick="showFullFace('${person.alias}')"><img src="../${defaultFace.icon.path}" alt="${person.alias}" /></a></div>		
			
			<div class="clear"></div>
			
			<label for="lastloggedin">Last Logged In:</label> 
			<fmt:formatDate value="${person.bank.lastLoggedIn}" pattern="MM/dd/yyyy" />		
			<div id="friend-profile"  class="header-element-line">
				<%@ include file="../profile/email.jsp" %>
			</div>	
		</div>	
		<div style="display:none;" id="face-overlay">  
			<img src="../${defaultFace.glimpse.path}" title="${person.alias}" alt="${person.alias}" />
		</div>		
		<div style="position:absolute;top:10px;right:100px;">
				<div class="header-element-line">
					<div id="shopping-cart-size">
						<a href="#" onclick="showCart();">
							<img src="/images/insert_to_shopping_cart.png" width="30" height="30" />Shopping Cart: 0
						</a>
					</div>
				</div>
		</div>
		<%@ include file="../common/searchfriends.jsp"%>
		<span id="friend-design-search-span">Find Friends</span>
		
    </div> <%-- /header --%>