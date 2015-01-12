<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">
	function cycleworldLATEST(items, category){
		$('#all-items-by-category').html('');
			for(i = 0; i < items.length; i ++){
				$('#world-latest-paging').show();
				$('#world-latest-paging').html('1');

				$('#item-world-' + category + ' .the-items').append('<p class="left-right">' + buildPublicScreen(category, items, i) + '</p>');
				i = i + 21;
			}

		$("a[rel^='prettyPhoto']").prettyPhoto();
		
		$('#world-clearance-paging').hide();
		$('#world-free-paging').hide();
		$('#world-hottest-paging').hide();
		$('#world-rebate-paging').hide();
		$('#world-cheapest-paging').hide();	

		if(items.length > 7){
			$('#world-latest-paging').html('');
		}
		
		$('#world-latest-paging').show();	
		
		$('#item-world-LATEST DIV').cycle({ 
		    fx:     'fade', 
		    timeout: 5000,
		    next:   '#nextworldLATEST', 
		    prev:   '#prevworldLATEST',
		    pause: 	true,
		    pager: '#world-latest-paging'
		 });
	}
	function onAfterworldLATEST(){
		$('#item-world-LATEST SPAN').html(this.title);
	}
	function onBeforeworldLATEST(){
		$('#item-world-LATEST SPAN').html(this.title);
	}
</script>
		<div id="world-latest-paging" class="item-pager-element">
		</div>
		<div id="item-world-LATEST" class="outer-item-element">
			<div  class="the-items inner-item-element">
			</div>
			<ul class="item-prev-next-element">
				<li><a id="prevworldLATEST" href="javascript: void(0);"><img src="/images/prev.png" /></a> <a id="nextworldLATEST" href="javascript: void(0);"><img src="/images/next.png" /></a></li>
			</ul>
		</div>