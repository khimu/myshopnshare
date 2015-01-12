<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">
	function cyclefriendsADVICE(items, category){
		$('#all-items-by-category').html('');
	
			for(i = 0; i < items.length; i ++){
				$('#friends-advice-paging').show();

				var todisplay = buildScreen(category, items, i);
				
				i = i + 8;

				$('#item-friends-' + category + ' .the-items').append('<p class="left-right">' + todisplay + '</p>');
			}	

		$("a[rel^='prettyPhoto']").prettyPhoto();
		
		$('#friends-selling-paging').hide();
		$('#friends-recommend-paging').hide();
		$('#friends-bought-paging').hide();
		$('#friends-want-paging').hide();
		
		$('#friends-advice-paging').show();
		$('#item-friends-ADVICE DIV').cycle({ 
		    fx:     'fade', 
		    timeout: 5000,
		    next:   '#nextfriendsADVICE', 
		    prev:   '#prevfriendsADVICE',
		    pause: 	true,
		    pager: '#friends-advice-paging'
		 });
	}
	function onAfterfriendsADVICE(){
		$('#item-friends-ADVICE SPAN').html(this.title);
	}
	function onBeforefriendsADVICE(){
		$('#item-friends-ADVICE SPAN').html(this.title);
	}
</script>
		<div id="friends-advice-paging" class="item-pager-element">
		</div>
		<div id="item-friends-ADVICE" class="outer-item-element">
			<div class="the-items inner-item-element">
			</div>
			<ul class="item-prev-next-element">
				<li><a id="prevfriendsADVICE" href="javascript: void(0);"><img src="/images/prev.png" /></a> <a id="nextfriendsADVICE" href="javascript: void(0);"><img src="/images/next.png" /></a></li>
			</ul>
		</div>
