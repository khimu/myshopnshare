<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<script type="text/javascript">
	function cycleworldREBATE(items, category){
		$('#all-items-by-category').html('');

			for(i = 0; i < items.length; i ++){
				$('#world-rebate-paging').show();
				$('#world-rebate-paging').html('1');
				var todisplay = buildScreen(category, items, i);
				
				i = i + 8;

				$('#item-world-' + category + ' .the-items').append('<p class="left-right">' + todisplay + '</p>');
			}

		$("a[rel^='prettyPhoto']").prettyPhoto();
		
		$('#world-clearance-paging').hide();
		$('#world-free-paging').hide();
		$('#world-hottest-paging').hide();
		$('#world-latest-paging').hide();
		$('#world-cheapest-paging').hide();	

		if(items.length > 8){
			$('#world-rebate-paging').html('');
		}
		
		$('#world-rebate-paging').show();			
		$('#item-world-REBATE DIV').cycle({ 
		    fx:     'fade', 
		    timeout: 5000,
		    next:   '#nextworldREBATE', 
		    prev:   '#prevworldREBATE',
		    pause: 	true,
		    pager: '#world-rebate-paging'
		 });
	}
	function onAfterworldREBATE(){
		$('#item-world-REBATE SPAN').html(this.title);
	}
	function onBeforeworldREBATE(){
		$('#item-world-REBATE SPAN').html(this.title);
	}
</script>
		<div id="world-rebate-paging" class="item-pager-element">
		</div>
		<div id="item-world-REBATE" class="outer-item-element">
			<div  class="the-items inner-item-element">
			</div>
			<ul class="item-prev-next-element">
				<li><a id="prevworldREBATE" href="javascript: void(0);"><img src="/images/prev.png" /></a> <a id="nextworldREBATE" href="javascript: void(0);"><img src="/images/next.png" /></a></li>
			</ul>
		</div>