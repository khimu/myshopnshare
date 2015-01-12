<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<script type="text/javascript">
	function cycleworldHOTTEST(items, category){
		$('#all-items-by-category').html('');

			for(i = 0; i < items.length; i ++){
				$('#world-hottest-paging').show();
				$('#world-hottest-paging').html('1');
				var todisplay = buildScreen(category, items, i);
				
				i = i + 8;

				$('#item-world-' + category + ' .the-items').append('<p class="left-right">' + todisplay + '</p>');
			}
		
		$("a[rel^='prettyPhoto']").prettyPhoto();
		
		$('#world-clearance-paging').hide();
		$('#world-free-paging').hide();
		$('#world-latest-paging').hide();
		$('#world-rebate-paging').hide();
		$('#world-cheapest-paging').hide();	

		if(items.length > 8){
			$('#world-hottest-paging').html('');
		}
		
		$('#world-hottest-paging').show();			
		$('#item-world-HOTTEST DIV').cycle({ 
		    fx:     'fade', 
		    timeout: 5000,
		    next:   '#nextworldHOTTEST', 
		    prev:   '#prevworldHOTTEST',
		    pause: 	true,
		    pager:	'#world-hottest-paging'
		 });
	}
	function onAfterworldHOTTEST(){
		$('#item-world-HOTTEST SPAN').html(this.title);
	}
	function onBeforeworldHOTTEST(){
		$('#item-world-HOTTEST SPAN').html(this.title);
	}
</script>
		<div id="world-hottest-paging" class="item-pager-element">
		</div>
		<div id="item-world-HOTTEST" class="outer-item-element">
			<div class="the-items inner-item-element">
			</div>
			<ul class="item-prev-next-element">
				<li><a id="prevworldHOTTEST" href="javascript: void(0);"><img src="/images/prev.png" /></a> <a id="nextworldHOTTEST" href="javascript: void(0);"><img src="/images/next.png" /></a></li>
			</ul>
		</div>
