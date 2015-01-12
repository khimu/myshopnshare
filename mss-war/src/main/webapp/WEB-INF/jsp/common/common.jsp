<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns"%>

<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-48724005-1', 'myshopnshare.com');
  ga('send', 'pageview');

</script>

<script type="text/javascript">

	function filterActionItems(category){
		hideAll();
		$('#item-'+category).show();
		getItems('search', category);		
	}
	
	function rateItem(theRating, itemId){
		$.ajax( {
			type :"post",
			url :"/secure/itemAction.do",
			data: {method: 'rate', rating: theRating, itemId: itemId},
			dataType: 'json',
			success : function(json) {
				clearItemStars();
				makeItemActive(json.rating);
		    }
		});	
	}
	
	function clearItemStars(){
		var startRating = 0;	
		while(startRating < 5){
			$('#rating-item-'+startRating).attr('src', '/images/gr_orange_star_unactive.gif');
			startRating ++;
		}
	}
	
	function makeItemActive(theRating){
		var startRating = 0;
		while(startRating < theRating){
			$('#rating-item-'+startRating).attr('src', '/images/gr_orange_star_active.gif');
			startRating ++;
		}
	}

	/** Event JS **/
	function changeEventDate(value, date, inst){
		 $.ajax({  
		       type: "post",  
		       url: "/secure/events.do",
		       data: {method: 'view', startDate: value, personId: '${person.id}'},
		       success: function(data){
					$('#center').html(data);
					$('#events-header h3').html("Events on " + value);
					window.location = '#';
		       }
		     });
	}
	
	$(document).ready( function() {
		$('#event-date-picker').datepick({
			dateFormat: 'mm/dd/yy', 
			showOn: 'both', 
			buttonImageOnly: true, 
			buttonImage: '/images/calendar-green.gif',
			onClose: changeEventDate,
			yearRange: '-100:+10',
			appendText: '(Select Event Date)'
		});
	});
	
	$(function() {
		$('#view-all-events').click(function(){
			 $.ajax({  
			       type: "post",  
			       url: "/secure/events.do",
			       data: {method: 'all', personId: '${person.id}'},
			       success: function(data){  
						$('#center').html(data);
						window.location = '#';
			       }
			 });
		});			
	});	
	
	function showContactInfo(personName){
		var title = personName + ' Contact Info';
		new Boxy('#person-contact-infos', {title: title, modal: true});
	}
	
	function deleteScribble(scribbleId){
		$('#loading').show();
		Boxy.confirm("Please confirm delete:", function() {
			$.ajax( {
				type :"post",
				url :"/secure/scribble.do",
				data : {
					method :'view', personId: '${person.id}'
				},
				dataType :'json',
				success : function(json) {
					$('#loading').hide();
					$('#scribble-entry-' + scribbleId).remove();
				}
			});
		}, {title: 'Confirm Delete'});
	    return false;
	}

	function deleteJournal(journalId){
		$('#loading').show();
		Boxy.confirm("Please confirm delete", function() {
			$.ajax( {
				type :"post",
				url :"/secure/journal.do",
				data : {
					method :'delete',
					journalId : journalId
				},
				dataType :'json',
				success : function(json) {
						$('#journal-entry-'+journalId).remove();
						$('#loading').hide();
				}
			});	
		}, {title: 'Confirm Delete'});
	    return false; 
	}

	function buildPublicScreen(category, items, i){
		var todisplay = '';
	
		todisplay = todisplay + '<sns:publicsingleitem type="'+items[i].type+'" primaryKey="' + items[i].id 
				+ '" caption="' + items[i].caption + '" description="' + items[i].description  + '" icon="'
				+ items[i].icon + '" glimpse="' + items[i].glimpse + '" />';
		
		showAllItems(items[i], category);

		todisplay = todisplay + buildSinglePublicScreen(i+1, items, category);
		todisplay = todisplay + buildSinglePublicScreen(i+2, items, category);
		todisplay = todisplay + buildSinglePublicScreen(i+3, items, category);
		todisplay = todisplay + buildSinglePublicScreen(i+4, items, category);
		todisplay = todisplay + buildSinglePublicScreen(i+5, items, category);
		todisplay = todisplay + buildSinglePublicScreen(i+6, items, category);
		todisplay = todisplay + buildSinglePublicScreen(i+7, items, category);
		todisplay = todisplay + buildSinglePublicScreen(i+8, items, category);
		todisplay = todisplay + buildSinglePublicScreen(i+9, items, category);
		todisplay = todisplay + buildSinglePublicScreen(i+10, items, category);
		todisplay = todisplay + buildSinglePublicScreen(i+11, items, category);
		todisplay = todisplay + buildSinglePublicScreen(i+12, items, category);
		todisplay = todisplay + buildSinglePublicScreen(i+13, items, category);
		todisplay = todisplay + buildSinglePublicScreen(i+14, items, category);
		todisplay = todisplay + buildSinglePublicScreen(i+15, items, category);
		todisplay = todisplay + buildSinglePublicScreen(i+16, items, category);
		todisplay = todisplay + buildSinglePublicScreen(i+17, items, category);
		todisplay = todisplay + buildSinglePublicScreen(i+18, items, category);
		todisplay = todisplay + buildSinglePublicScreen(i+19, items, category);
		todisplay = todisplay + buildSinglePublicScreen(i+20, items, category);
		todisplay = todisplay + buildSinglePublicScreen(i+21, items, category);

		return todisplay;		
	}

	function buildSinglePublicScreen(i, items, category){
		if(items[i] != null){
			showAllItems(items[i], category);			
			return '<sns:publicsingleitem  type="'+items[i].type+'"  primaryKey="' + items[i].id + '" caption="' + items[i].caption
			+ '" description="' + items[i].description + '" icon="'
			+ items[i].icon + '" glimpse="' + items[i].glimpse + '" />';
		}
		return '';
	}

	function buildSingleScreen(category, items, i){
		if(items[i] != null){
			showAllItems(items[i], category);

			if(items[i].type == 'VENDOR'){
				return '<sns:singleitemvendor  type="'+items[i].type+'" primaryKey="' + items[i].id 
					+ '" caption="' + items[i].caption + '" description="' + items[i].description  + '" icon="'
					+ items[i].icon + '" glimpse="' + items[i].glimpse + '" />';
			}else if(items[i].type == 'POINT'){
				return '<sns:singleitempoint   type="'+items[i].type+'" primaryKey="' + items[i].id 
					+ '" caption="' + items[i].caption + '" description="' + items[i].description  + '" icon="'
					+ items[i].icon + '" glimpse="' + items[i].glimpse + '" />';			
			}else{
				return '<sns:singleitem   type="'+items[i].type+'" primaryKey="' + items[i].id 
					+ '" caption="' + items[i].caption + '" description="' + items[i].description  + '" icon="'
					+ items[i].icon + '" glimpse="' + items[i].glimpse + '" />';			
			}
		}
		return '';
	}

	function buildScreen(category, items, i){
		var todisplay = '';
		todisplay = todisplay + buildSingleScreen(category, items, i);
		todisplay = todisplay + buildSingleScreen(category, items, i+1);
		todisplay = todisplay + buildSingleScreen(category, items, i+2);
		todisplay = todisplay + buildSingleScreen(category, items, i+3);
		todisplay = todisplay + buildSingleScreen(category, items, i+4);
		todisplay = todisplay + buildSingleScreen(category, items, i+5);
		todisplay = todisplay + buildSingleScreen(category, items, i+6);
		todisplay = todisplay + buildSingleScreen(category, items, i+7);
		todisplay = todisplay + buildSingleScreen(category, items, i+8);
	
		return todisplay;		
	}


	function buildInitialSingleScreen(category, items, i){
		if(items[i] != null){
			showAllItems(items[i], category);
	
			if(items[i].type == 'VENDOR'){
				return '<sns:singleitemvendorinitial type="'+items[i].type+'" primaryKey="' + items[i].id 
					+ '" caption="' + items[i].caption + '" description="' + items[i].description  + '" icon="'
					+ items[i].icon + '" glimpse="' + items[i].glimpse + '" />';
			}else if(items[i].type == 'POINT'){
				return '<sns:singleitempointinitial type="'+items[i].type+'" primaryKey="' + items[i].id 
					+ '" caption="' + items[i].caption + '" description="' + items[i].description  + '" icon="'
					+ items[i].icon + '" glimpse="' + items[i].glimpse + '" />';			
			}else{
				return '<sns:singleiteminitial  type="'+items[i].type+'" primaryKey="' + items[i].id 
					+ '" caption="' + items[i].caption + '" description="' + items[i].description  + '" icon="'
					+ items[i].icon + '" glimpse="' + items[i].glimpse + '" />';			
			}
		}
		return '';
	}

	function buildInitialScreen(category, items, i){
		var todisplay = '';
		todisplay = todisplay + buildInitialSingleScreen(category, items, i);
		todisplay = todisplay + buildInitialSingleScreen(category, items, i+1);
		todisplay = todisplay + buildInitialSingleScreen(category, items, i+2);
		todisplay = todisplay + buildInitialSingleScreen(category, items, i+3);
		todisplay = todisplay + buildInitialSingleScreen(category, items, i+4);
		todisplay = todisplay + buildInitialSingleScreen(category, items, i+5);
		todisplay = todisplay + buildInitialSingleScreen(category, items, i+6);
		todisplay = todisplay + buildInitialSingleScreen(category, items, i+7);
		todisplay = todisplay + buildInitialSingleScreen(category, items, i+8);
	
		return todisplay;		
	}	

	function showEvent(eventId){
		 $.ajax({  
		       type: "post",  
		       url: "/secure/events.do",
		       data: {method: 'get', eventId: eventId, personId: '${person.id}'},
		       success: function(data){  
			       $('#center-2').hide();
			       $('#center-3').hide();
			       $('#center').show();
					$('#center').html(data);
		       }
		 });
 	}

	function showAllEvents(){	
		 $.ajax({  
		       type: "post",  
		       url: "/secure/events.do",
		       data: {method: 'all', personId: '${person.id}'},
		       success: function(data){  
					$('#center').html(data);
		       }
		 });
	}
	
	function unsubscribe(){
		Boxy.confirm("Please confirm un-subscribe:", function() {
			 $.ajax({  
			       type: "POST",  
			       url: "/secure/subscribe.do",
			       data: {method: 'unsubscribe'},
			       success: function(data){ 
			       	Boxy.alert('You have successfully unsubscribed to our service.  You will not be charged for any fees from today on.');
			       }
			});	
		}, {title: 'Confirm Delete'});
	    return false;
	}

	function subscribe(){
			 $.ajax({  
			       type: "post",  
			       url: "/secure/subscribe.do",
			       data: {method: 'show'},
			       success: function(data){ 
				       $('#center-2').hide();
				       $('#center-3').hide();
				       $('#center').show();
			       		$('#center').html(data);
			       }
			});	
	}

	function showPhoto(itemName){
		new Boxy('#item-overlay', {title: itemName, modal: true});
	}

	function deleteNews(newsId) {
		Boxy.confirm("Please confirm delete:", function() {
			$.ajax( {
				type :"post",
				url :"/secure/news.do",
				data : {
					method :'delete',
					newsId :newsId
				},
				dataType :'json',
				success : function(json) {
					$('#news-element-' + newsId).remove();
				}
			});
		}, {title: 'Confirm Delete'});
	    return false;
	}

	function fullStory(newsId) {
		$.ajax( {
			type :"post",
			url :"/secure/news.do",
			data : {
				method :'get',
				newsId :newsId
			},
			dataType :'html',
			success : function(data) {
				$('#center').hide();
				$('#center-2').hide();
				
				$('#center-3').show();
				$('#center-3').html(data);
			}
		});
	}

	function buyItem() {
		$.ajax( {
			type :"post",
			url :"/secure/cart.do?method=add&" + $('#add-to-shopping-cart-item-form').serialize(),
			dataType :'json',
			success : function(json) {
				$('#shopping-cart-size').html('<a href="#" onclick="showCart();"><img src="/images/insert_to_shopping_cart.png" width="30" height="30" />Shopping Cart: ' + json.cartSize + '</a>');
				$('#buy-message').html(json.actionMessage);
				$('#buy-item-element').hide();
			}
		});
	}

	function showBuyItem() {
		$('#buy-item-element').show();
	}

	function viewAllNews() {
		$.ajax( {
			type :"post",
			url :"/secure/news.do",
			data : {
				method :'showAll'
			},
			dataType :'html',
			success : function(data) {
				$('#center').html(data);
			}
		});
	}

	function viewOrderDetailPage(orderId) {
		$.ajax( {
			type :"post",
			url :"/secure/orders.do",
			data : {
				method :'get',
				itemOrderDetailId :orderId
			},
			dataType :'html',
			success : function(data) {
				$('#center').html(data);
			}
		});
	}

	var escapeHTML = function(description) {
		return description.replace(/&/g, '&amp;').replace(/</g, '&lt;')
				.replace(/>/g, '&gt;');
	}

	var unescapeHTML = function(description) {
		return description.replace(/&amp;/g, '&').replace(/&lt;/g, '<')
				.replace(/&gt;/g, '>');
	}

	function buyPublicItem() {
		Boxy.alert("Please log in or sign up for full access.");
	}

	function viewPublicVendorItemDetail(recordId) {
		// $('#item_'+itemName).parent().css('border-color', '#FCC');
		$
				.ajax( {
					type :"post",
					url :"/public/vendorItemPage.do",
					data : {
						method :'detail',
						itemId :recordId
					},
					success : function(data) {

						$('#center').hide();
						$('#center-3').hide();
						$('#center-2').show(data);

						$('#center-2').html(data);
						//$('#vendor-item-all-comments').html('');

						$('#user-item-page').removeClass('authenticated');
						$('#user-item-page').removeClass('public');
						$('#buy-item-button')
								.html(
										'<a style="color: #005500;" href="javascript: void(0);" onclick="buyPublicItem();">Add</a>');

						$('#vendor-item-back-page')
								.html(
										'<a href="#" onclick="hidePublicSearch()"><img id="backbt" src="/images/back.png" /></a>');

					}
				});
	}

	function publicSearch() {
		$.ajax( {
			type :"post",
			url :'/public/search.do',
			data : {
				method :'view',
				searchString :$('#design-search-input').val()
			},
			dataType :'html',
			success : function(data) {
				$('#center').hide();
				$('#center-3').hide();

				$('#center-2').show();
				$('#center-2').html(data);

				$('#search-results').show();

				/*
					$('#search-results').html('');
					
				$('#search-results').append('<h3>' + json.searchMessage + '</h3>');
				
				$.each(json.items, function(i,item){
					$('#search-results').append('<sns:publicsearchitemimagerecord itemName="'+ item.itemName +'" caption="'+ item.caption +'" description="'+ unescapeHTML(item.description) +'" icon="'+ item.icon +'" glimpse="'+ item.glimpse +'" primaryKey="' + item.id + '" />');								
				});	
				 */

				$("a[rel^='prettyPhoto']").prettyPhoto( {
					showTitle :true
				});
				$('#myshopnshare-search').hide();
				$('#go-back-to-search').show();

			}
		});
		return false;
	}

	function showAllUnderCategory() {
		$('#points-items-element').html('');
		$('#center').hide();
		$('#center-2').hide();
		$('#center-3').show();
	}

	function hidePublicSearch() {
		$('#center-2').hide();
		$('#center').show();
	}

	function hideAllUnderCategory() {
		$('#center-3').hide();
		$('#center').show();
	}

	/*
	function registerCalendar() {
		$('#register-datepicker').DatePicker(
				{
					flat :true,
					format :'m/d/Y',
					starts :1,
					position :'r',
					date :$('#register-datepicker').val(),
					current :$('#register-datepicker').val(),
					calendars :1,
					onBeforeShow : function() {
						$('#register-datepicker').DatePickerSetDate(
								$('#startDate').val(), true);
					},
					onChange : function(formated, dates) {
						var date = formated.split("/");
						$('#month').attr('value', date[0]);
						$('#day').attr('value', date[1]);
						$('#year').attr('value', date[2]);
					}
				});
	}
	*/

	function reloadFullNews(selected){
		var filter = selected.replace('#news-', '');
			 $.ajax({  
			       type: "POST",  
			       url: "/secure/news.do",
			       data: {method: 'page', start: '0', category: filter, personId: '${person.id}'},
			       dataType: 'html',
			       success: function(data){  
			    	   $('#news-collections').html(data);
			       } 
			     });
	}
	
	function showAllItems(item, category) {
		$('#the-category-selected').html('SHOWING ALL OF ' + category);
		//$('#all-items-by-category').html('');
		$('#all-items-by-category').append(
				'<sns:publicuseritemrecord primaryKey="' + item.id
						+ '" caption="' + item.caption + '" description="'
						+ item.description + '" icon="' + item.icon
						+ '" glimpse="' + item.glimpse + '" />');
	}

	function showPossessions() {
		$.ajax( {
			type :"post",
			url :"/secure/possessions.do",
			data : {
				method :'view'
			},
			success : function(data) {
				$('#center').html(data);
			}
		});
	}
	function showSearch() {
		$.ajax( {
			type :"post",
			url :"/secure/search.do",
			data : {
				method :'view'
			},
			success : function(data) {
				$('#center').html(data);
			}
		});
	}

	function backToSearch() {
		$('#center-2').hide();
		$('#center').show();
		$('#center-3').hide();
	}
	function backToSearchHeader() {
		$('#search-results').hide();
		$('#take-action-parent-element').hide();
		$('#go-back-to-search').hide();
		$('#myshopnshare-search').show();

	}

	function viewItemDetail(recordId) {
		$.ajax( {
			type :"post",
			url :"/secure/itemPage.do",
			data : {
				method :'detail',
				itemId :recordId
			},
			success : function(data) {
				$('#center').hide();
				$('#center-3').hide();
				$('#center-2').show();
				$('#center-2').html(data);
			}
		});
	}

	function viewVendorItemDetail(recordId) {
		// $('#item_'+itemName).parent().css('border-color', '#FCC');
		$.ajax( {
			type :"post",
			url :"/public/vendorItemPage.do",
			data : {
				method :'detail',
				itemId :recordId
			},
			success : function(data) {
				//$('#west').html('');
			$('#center').hide();
			$('#center-3').hide();
			$('#center-2').show();
			$('#center-2').html(data);
			//$('#item_'+itemName).parent().css('border-color', '#FFF');
		}
		});
	}

	function viewRewardItemDetail(recordId) {
		// $('#item_'+itemName).parent().css('border-color', '#FCC');
		$.ajax( {
			type :"post",
			url :"/public/pointItemPage.do",
			data : {
				method :'view',
				itemId :recordId
			},
			success : function(data) {
				//$('#west').html('');
			$('#center').hide();
			$('#center-3').hide();
			$('#center-2').show();
			$('#center-2').html(data);
			//$('#item_'+itemName).parent().css('border-color', '#FFF');
		}
		});
	}
	var search = function() {
	}

	search.itemIds = new Array();
	search.itemNames = new Array();
	search.index = 0;

	function addItemToSearchArray(recordId, itemName) {
		$('#take-action-parent-element').show();
		search.itemIds.push(recordId);
		search.itemNames.push(itemName);
	}

	function takeSearchItemAction(selectedValue) {
		if (selectedValue == 'want') {
			for ( var i = 0; i < search.itemIds.length; i++) {
				var sid = search.itemIds[i];
				var sname = search.itemNames[i];
				wantSearchItem(sid, sname);
			}
		} else if (selectedValue == 'bought') {
			for ( var i = 0; i < search.itemIds.length; i++) {
				var sid = search.itemIds[i];
				var sname = search.itemNames[i];
				boughtSearchItem(sid, sname);
			}
		} else if (selectedValue == 'advice') {
			/*
			$('#item_' + itemName).parent().css('border-color', '#FCC');
			var itemIds = '';
			for ( var i = 0; i < search.itemIds.length; i++) {
				itemIds = search.itemIds[i] + ',';
			}			
			$.ajax( {
				type :"post",
				url :"/secure/itemAction.do",
				data : {
					method :'advice',
					itemIds :itemIds
				},
				success : function(data) {
					$('#recommend-successful').html(data);
					$('#recommend-successful-search').html(data);
					//$('#item_'+itemName).parent().css('border-color', '#FFF');
				}
			});
			*/
			for ( var i = 0; i < search.itemIds.length; i++) {
				var sid = search.itemIds[i];
				var sname = search.itemNames[i];
				adviceSearchItem(sid, sname);
			}
		} else if (selectedValue == 'recommend') {
			for ( var i = 0; i < search.itemIds.length; i++) {
				var sid = search.itemIds[i];
				var sname = search.itemNames[i];
				recommendSearchItem(sid, sname);
			}
		} else if (selectedValue == 'view') {
			for ( var i = 0; i < search.itemIds.length; i++) {
				var sid = search.itemIds[i];
				var sname = search.itemNames[i];
				viewItemDetail(sid);
			}
			/*
			}else if(selectedValue == 'comments'){
				for(var i = 0; i < search.itemIds.length; i ++){
					var sid = search.itemIds[i];
					var sname = search.itemNames[i];
					readOnlyComments(sid);
				}
			 */
		} else if (selectedValue == 'flag') {
			Boxy.confirm("Please confirm flag:", function() {
				for ( var i = 0; i < search.itemIds.length; i++) {
					var sid = search.itemIds[i];
					var sname = search.itemNames[i];
					flagItem(sid, sname);
				}
			}, {title: 'Confirm Delete'});
		    return false;
		}
	}

	function takeItemActionOnItemCategory(selectedValue, itemCategoryId, itemId) {
		if (selectedValue == 'tag') {
			tagPossessionsItem(itemId);
		} else if (selectedValue == 'delete') {
			deleteItemCategoryItem(itemCategoryId);
		}
		/*
		}else if(selectedValue == 'comments'){
			readOnlyComments(itemId);	
		}
		 */
	}

	function takeSearchItemActionOnPossession(selectedValue, itemId) {
		if (selectedValue == 'tag') {
			tagPossessionsItem(itemId);
		} else if (selectedValue == 'edit') {
			editPossessionsItem(itemId);
		} else if (selectedValue == 'delete') {
			deletePossessionsItem(itemId);
		}
		/*
		}else if(selectedValue == 'comments'){
			readOnlyComments(itemId);
		}	
		 */
	}

	function addCommercialsPossessionsItem(itemId, itemName) {
		new Boxy('#item-commercials-form', {
			title :'Commercials',
			fixed :false, modal: true
		});
		$('#commercial-item-id').attr('value', itemId);
	}

	function tagPossessionsItem(itemId) {
		new Boxy('#item-tag-form', {
			title :'Tag Item',
			fixed :false, modal: true
		});
		$('#tag-item-id').attr('value', itemId);
	}

	function deleteItemCategoryItem(itemId) {
		Boxy.confirm("Please confirm remove:", function() {
			$.ajax( {
				type :"POST",
				url :"/secure/itemCategories.do",
				data : {
					method :'delete',
					itemId :itemId
				},
				dataType :'json',
				success : function(json) {
					$('#item-description' + itemId).remove();
					// getItemsComplete(json.items);		       
			}
			});
		}, {title: 'Confirm Delete'});
	    return false;
	}

	function deletePossessionsItem(itemId) {
		Boxy.confirm("Please confirm delete:", function() {
			$.ajax( {
				type :"POST",
				url :"/secure/user.do",
				data : {
					method :'delete',
					itemId :itemId
				},
				dataType :'json',
				success : function(json) {
					$('#item-description' + itemId).remove();
				}
			});
		}, {title: 'Confirm Delete'});
	    return false;
	}

	function editPossessionsItem(itemId) {

		$.ajax( {
			type :"post",
			url :"/secure/user.do",
			data : {
				method :'get',
				itemId :itemId
			},
			dataType :'json',
			success : function(json) {
				uploadUrl = "/secure/user.do?method=edit&";

				$('#upload-item-id').attr('value', '');
				$('#existing-item-tags').html('');
				$('#user-item-upload-form').clearForm();
				new Boxy('#user-upload-box', {
					title :'Edit Item',
					fixed :false, modal: true
				});
				
				if (json.type == 'POINT' || json.type == 'SERVICE'
						|| json.type == 'SELLING') {
					$('#selling-items-element').show();
				}

				$('#user-item-upload-form input').each( function() {
					$(this).attr('value', json[$(this).attr('name')]);
				});

				$('#user-item-upload-form checkbox').each(
						function() {
							$(this).attr('value', json[$(this).attr('name')]).attr('checked', 'checked');
						});

				$('#user-item-upload-form radio').each(function() {
							$(this).attr('value', json[$(this).attr('name')]).attr('selected', 'selected');
						});

				$('#user-item-upload-form textarea').each(function() {
							$(this).attr('value', json[$(this).attr('name')]);
						});

				$('#existing-item-tags').html(json.tags);
			}
		});
	}

	function flagItem(itemId, itemName) {
		$('#item_' + itemName).parent().css('border-color', '#FCC');
		$.ajax( {
			type :"post",
			url :"/secure/itemAction.do",
			data : {
				method :'flag',
				itemId :itemId
			},
			success : function(data) {
				$('#recommend-successful').html(data);
				$('#recommend-successful-search').html(data);
			}
		});
	}

	function loadFriends() {
		$.ajax( {
			type :"post",
			url :"/secure/manageFriends.do",
			data : {
				method :'get',
				personId :'${person.id}'
			},
			dataType :'json',
			success : function(json) {
				$('.dynamic-friends-list').append('<ul');
				$.each(json.friends, function(i, friend) {
					$('.dynamic-friends-list').append(
							'<li style="font-weight:bold;padding:2px;"><input type="checkbox" name="friendIds" value="'
									+ friend.friendProfile.friendId + '" />&nbsp;&nbsp;'
									+ friend.friendProfile.fullName + '</li>');
				});
				$('.dynamic-friends-list').append('</ul>');
			}
		});
	}

	function wantSearchItem(recordId, itemName) {
		$('#item_' + itemName).parent().css('border-color', '#FCC');
		$.ajax( {
			type :"POST",
			url :"/secure/itemAction.do",
			data : {
				method :'want',
				itemId :recordId
			},
			success : function(data) {
				$('#recommend-successful').html(data);
				$('#recommend-successful-search').html(data);
				//$('#item_'+itemName).parent().css('border-color', '#FFF');
			//$('#item_'+itemName).parent().remove();
		}
		});
	}

	function recommendSearchItem(recordId, itemName) {
		//$('#item_'+itemName).parent().css('border-color', '#FCC');
		new Boxy('#search-recommend-friend-element', {
			title :'Recommend Item',
			fixed :false, modal: true
		});
		loadFriends();
		$('#item-id').attr('value', recordId);
		$('#item-name').attr('value', itemName);
	}

	function adviceSearchItem(recordId, itemName) {
		$('#item_' + itemName).parent().css('border-color', '#FCC');
		$.ajax( {
			type :"POST",
			url :"/secure/itemAction.do",
			data : {
				method :'advice',
				itemId :recordId
			},
			success : function(data) {
				$('#recommend-successful').html(data);
				$('#recommend-successful-search').html(data);
				//$('#item_'+itemName).parent().css('border-color', '#FFF');
		}
		});
	}

	function boughtSearchItem(recordId, itemName) {
		$('#item_' + itemName).parent().css('border-color', '#FCC');
		$.ajax( {
			type :"POST",
			url :"/secure/itemAction.do",
			data : {
				method :'bought',
				itemId :recordId
			},
			success : function(data) {
				$('#recommend-successful').html(data);
				$('#recommend-successful-search').html(data);
				//$('#item_'+itemName).parent().css('border-color', '#FFF');
		}
		});
	}

	function submitRecommend() {
		$('#recommend-form').ajaxSubmit( {
			type :"post",
			url :"/secure/itemAction.do?method=recommend",
			dataType :'html',
			success : function(data) {
				Boxy.get('#search-recommend-friend-element').hide();
				$('#recommend-successful').html(data);
				$('#recommend-successful-search').html(data);
				$('#recommend-form').clearForm();
				$('#item-name').parent().css('border-color', 'white');
				$('#search-recommend-friend-element').hide();
				$('.dynamic-friends-list').hide();
			}
		});
	}
</script>