<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<script type="text/javascript">
	function cyclefriendsRECOMMEND(items, category){
		$('#all-items-by-category').html('');
	
			for(i = 0; i < items.length; i ++){
				$('#friends-recommend-paging').show();
				var todisplay = buildScreen(category, items, i);
				
				i = i + 8;

				$('#item-friends-' + category + ' .the-items').append('<p class="left-right">' + todisplay + '</p>');
			}

		$("a[rel^='prettyPhoto']").prettyPhoto();
		
		$('#friends-selling-paging').hide();
		$('#friends-bought-paging').hide();
		$('#friends-advice-paging').hide();
		$('#friends-want-paging').hide();
			
		$('#friends-recommend-paging').show();
		
		$('#item-friends-RECOMMEND DIV').cycle({ 
		    fx:     'fade', 
		    timeout: 5000,
		    next:   '#nextfriendsRECOMMEND', 
		    prev:   '#prevfriendsRECOMMEND',
		    pause: 	true,
		    pager: '#friends-recommend-paging'
		 });
	}
	function onAfterfriendsRECOMMEND(){
		$('#item-friends-RECOMMEND SPAN').html(this.title);
	}
	function onBeforefriendsRECOMMEND(){
		$('#item-friends-RECOMMEND SPAN').html(this.title);
	}
</script>
		<div id="friends-recommend-paging" class="item-pager-element">
		</div>
		<div id="item-friends-RECOMMEND" class="outer-item-element">
			<div class="the-items inner-item-element">
			</div>
			<ul class="item-prev-next-element">
				<li><a id="prevfriendsRECOMMEND" href="javascript: void(0);"><img src="/images/prev.png" /></a> <a id="nextfriendsRECOMMEND" href="javascript: void(0);"><img src="/images/next.png" /></a></li>
			</ul>
</div>	