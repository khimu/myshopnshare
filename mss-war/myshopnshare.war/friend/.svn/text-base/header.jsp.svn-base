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
				makeActive(json.rating);		    }
		});
	});
	
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

   		$.ajax( {
   			type :"post",
   			url :"/secure/cart.do",
   			data: {method: 'get'},
   			dataType: 'json',
   			success : function(json) {
   				$('#shopping-cart-size').html('<a href="#" onclick="showCart();"><img src="/images/insert_to_shopping_cart.png" width="15" height="15" />Shopping Cart: ' + json.cartSize + '</a>');
   		    }
   		});		
	});
    
</script>

  <link rel="stylesheet" media="screen,projection" type="text/css" href="/css/shopnshare/crystalx/main.css" />
   <link rel="stylesheet" media="print" type="text/css" href="/css/crystalx/print.css" />
   <link rel="stylesheet" media="aural" type="text/css" href="/css/crystalx/aural.css" />	

    <%-- Header --%>
    <div id="header" style="height: 120px;margin: 0 7px; background-color:#f79711;">

		<div id="header-right-element">					
			<div id="welcome-header-element">	
				<h1 id="welcome-header">${person.fullName}'s Page</h1>
				<div class="header-element-line">
					<label for="lastloggedin">Last Logged In:</label> 
					<fmt:formatDate value="${person.bank.lastLoggedIn}" pattern="MM/dd/yyyy" />
				</div>		
				<div id="friend-profile"  class="header-element-line">
					<%@ include file="../profile/email.jsp" %>
				</div>					
			</div>
		</div>		

		<%@ include file="../common/searchfriends.jsp"%>
		<span id="friend-design-search-span">Find Friends, Schools, or Businesses</span>


    </div> <%-- /header --%>