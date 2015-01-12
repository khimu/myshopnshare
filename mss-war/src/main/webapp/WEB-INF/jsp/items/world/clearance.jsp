<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<script type="text/javascript">
	function cycleworldCLEARANCE(items, category){
		$('#all-items-by-category').html('');

			for(i = 0; i < items.length; i ++){
				$('#world-clearance-paging').show();
				$('#world-clearance-paging').html('1');
				var todisplay = buildScreen(category, items, i);
				
				i = i + 8;

				$('#item-world-' + category + ' .the-items').append('<p class="left-right">' + todisplay + '</p>');
			}

		$("a[rel^='prettyPhoto']").prettyPhoto();
		
		$('#world-free-paging').hide();
		$('#world-hottest-paging').hide();
		$('#world-latest-paging').hide();
		$('#world-rebate-paging').hide();
		$('#world-cheapest-paging').hide();	

		if(items.length > 8){
			$('#world-clearance-paging').html('');
		}
		
		$('#world-clearance-paging').show();			
		$('#item-world-CLEARANCE DIV').cycle({ 
		    fx:     'fade', 
		    timeout: 5000,
		    next:   '#nextworldCLEARANCE', 
		    prev:   '#prevworldCLEARANCE',
		    pause: 	true,
		    pager: '#world-clearance-paging'
		 });
	}
	function onAfterworldCLEARANCE(){
		$('#item-world-CLEARANCE SPAN').html(this.title);
	}
	function onBeforeworldCLEARANCE(){
		$('#item-world-CLEARANCE SPAN').html(this.title);
	}
</script>
		<div id="world-clearance-paging" class="item-pager-element">
		</div>
<div id="item-world-CLEARANCE" class="outer-item-element">
			<div class="the-items inner-item-element">
			</div>
			<ul class="item-prev-next-element">
				<li><a id="prevworldCLEARANCE" href="javascript: void(0);"><img src="/images/prev.png" /></a> <a id="nextworldCLEARANCE" href="javascript: void(0);"><img src="/images/next.png" /></a></li>
			</ul>
</div>	