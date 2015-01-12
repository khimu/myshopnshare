<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<script type="text/javascript">
	function cycleworldCHEAPEST(items, category){
		$('#all-items-by-category').html('');

			for(i = 0; i < items.length; i ++){
				$('#world-cheapest-paging').show();
				$('#world-cheapest-paging').html('1');
				var todisplay = buildPublicScreen(category, items, i);
				
				i = i + 21;

				$('#item-world-' + category + ' .the-items').append('<p class="left-right">' + todisplay + '</p>');
			}

		$("a[rel^='prettyPhoto']").prettyPhoto();
		
		$('#world-free-paging').hide();
		$('#world-hottest-paging').hide();
		$('#world-latest-paging').hide();
		$('#world-rebate-paging').hide();
		$('#world-clearance-paging').hide();	

		if(items.length > 7){
			$('#world-cheapest-paging').html('');
		}
		
		$('#world-cheapest-paging').show();			
		$('#item-world-CHEAPEST DIV').cycle({ 
		    fx:     'fade', 
		    timeout: 5000,
		    next:   '#nextworldCHEAPEST', 
		    prev:   '#prevworldCHEAPEST',
		    pause: 	true,
		    pager: '#world-cheapest-paging'
		 });
	}
	function onAfterworldCHEAPEST(){
		$('#item-world-CHEAPEST SPAN').html(this.title);
	}
	function onBeforeworldCHEAPEST(){
		$('#item-world-CHEAPEST SPAN').html(this.title);
	}
</script>
		<div id="world-cheapest-paging" class="item-pager-element">
		</div>
<div id="item-world-CHEAPEST" class="outer-item-element">
			<div class="the-items inner-item-element">
			</div>
			<ul class="item-prev-next-element">
				<li><a id="prevworldCHEAPEST" href="javascript: void(0);"><img src="/images/prev.png" /></a> <a id="nextworldCHEAPEST" href="javascript: void(0);"><img src="/images/next.png" /></a></li>
			</ul>
</div>	