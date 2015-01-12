<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">
	function cyclefriendsWANT(items, category){
		$('#all-items-by-category').html('');
	
			for(i = 0; i < items.length; i ++){
				$('#friends-want-paging').show();
				var todisplay = buildScreen(category, items, i);
				
				i = i + 8;

				$('#item-friends-' + category + ' .the-items').append('<p class="left-right">' + todisplay + '</p>');
			}		

		$("a[rel^='prettyPhoto']").prettyPhoto();
		
		$('#friends-selling-paging').hide();
		$('#friends-recommend-paging').hide();
		$('#friends-bought-paging').hide();
		$('#friends-advice-paging').hide();		
		
		$('#friends-want-paging').show();
		$('#item-friends-WANT DIV').cycle({ 
		    fx:     'fade', 
		    timeout: 5000,
		    next:   '#nextfriendsWANT', 
		    prev:   '#prevfriendsWANT',
		    pause: 	true,
		    pager:	'#friends-want-paging'
		 });
	}
	function onAfterfriendsWANT(){
		$('#item-friends-WANT SPAN').html(this.title);
	}
	function onBeforefriendsWANT(){
		$('#item-friends-WANT SPAN').html(this.title);
	}
</script>
		<div id="friends-want-paging" class="item-pager-element">
		</div>
		<div id="item-friends-WANT" class="outer-item-element">
			<div class="the-items inner-item-element">
			</div>
			<ul class="item-prev-next-element">
				<li><a id="prevfriendsWANT" href="javascript: void(0);"><img src="/images/prev.png" /></a> <a id="nextfriendsWANT" href="javascript: void(0);"><img src="/images/next.png" /></a></li>
			</ul>
		</div>			