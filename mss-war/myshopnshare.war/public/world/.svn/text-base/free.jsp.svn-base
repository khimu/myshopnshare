<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<script type="text/javascript">
	function cycleworldFREE(items, category){
		$('#all-items-by-category').html('');

			for(i = 0; i < items.length; i ++){
				$('#world-free-paging').show();
				$('#world-free-paging').html('1');
				var todisplay = buildPublicScreen(category, items, i);
				
				i = i + 21;

				$('#item-world-' + category + ' .the-items').append('<p class="left-right">' + todisplay + '</p>');
			}


		$("a[rel^='prettyPhoto']").prettyPhoto();
		
		$('#world-clearance-paging').hide();
		$('#world-hottest-paging').hide();
		$('#world-latest-paging').hide();
		$('#world-rebate-paging').hide();
		$('#world-cheapest-paging').hide();	

		if(items.length > 7){
			$('#world-free-paging').html('');
		}
		
		$('#world-free-paging').show();		
		$('#item-world-FREE DIV').cycle({ 
		    fx:     'fade', 
		    timeout: 5000,
		    next:   '#nextworldFREE', 
		    prev:   '#prevworldFREE',
		    pause: 	true,
		    pager: '#world-free-paging'
		 });
	}
	function onAfterworldFREE(){
		$('#item-world-FREE SPAN').html(this.title);
	}
	function onBeforeworldFREE(){
		$('#item-world-FREE SPAN').html(this.title);
	}
</script>
		<div id="world-free-paging" class="item-pager-element">
		</div>
<div id="item-world-FREE" class="outer-item-element">
			<div class="the-items inner-item-element">
			</div>
			<ul class="item-prev-next-element">
				<li><a id="prevworldFREE" href="javascript: void(0);"><img src="/images/prev.png" /></a> <a id="nextworldFREE" href="javascript: void(0);"><img src="/images/next.png" /></a></li>
			</ul>
</div>			