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

	<!-- Start of Page Header -->

	<div id="header_container">
	<div id="page_header">

		<div id="header_company">
		<h1><span>mySHOPnSHARE</span></h1>
		</div>

		<div id="header_menu">
			
            <ul>
            	<li><a href="/public/publicPage.do">Login<span ></span><span></span></a></li>            
                <li><a href="/register.do">Sign Up<span ></span><span></span></a></li>
                <li><a href="/contact.do">Contact<span ></span><span></span></a></li>
                <li><a href="/mainPage.do?method=terms">Terms Of Use<span ></span><span></span></a></li>
                <li><a href="/mainPage.do?method=privacy">Privacy<span ></span><span></span></a></li>
            </ul>			

		</div>

		<div id="header_welcome">
		<h3>Welcome</h3>

			<div id="welcome_text">

				<c:if test="${not empty person}">
					Age: ${person.age}<br />
					<c:if test="${not empty person.primaryAddress}">
						${person.primaryAddress.country}<br />
					</c:if>
					AIM: <c:if test="${not empty person.profile.aim}"><a href="aim:GoIM?screenname=screenname"><img src="http://api.oscar.aol.com/presence/icon?k=mi15Xbwp687KXwRc&t=${person.profile.aim}"/>${person.profile.aim}</a></c:if><br />
					Birthday: <fmt:formatDate value="${person.birthday}" pattern="MM/dd" />	
				</c:if>
			
			</div>
		
		</div>

	</div>
	</div>

	<!-- End of Page Header -->

