<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<script type="text/javascript">
	function cycleserviceCHEAPEST(items, category){
		$('#all-items-by-category').html('');

			for(i = 0; i < items.length; i ++){
				$('#service-cheapest-paging').show();
				$('#service-cheapest-paging').html('1');
				var todisplay = buildScreen(category, items, i);
				
				i = i + 8;

				$('#item-service-' + category + ' .the-items').append('<p class="left-right">' + todisplay + '</p>');
			}

		$("a[rel^='prettyPhoto']").prettyPhoto();
		
		$('#service-free-paging').hide();
		$('#service-hottest-paging').hide();
		$('#service-latest-paging').hide();
		$('#service-rebate-paging').hide();
		$('#service-clearance-paging').hide();	

		if(items.length > 8){
			$('#service-cheapest-paging').html('');
		}
		
		$('#service-cheapest-paging').show();			
		$('#item-service-CHEAPEST DIV').cycle({ 
		    fx:     'fade', 
		    timeout: 5000,
		    next:   '#nextserviceCHEAPEST', 
		    prev:   '#prevserviceCHEAPEST',
		    pause: 	true,
		    pager: '#service-cheapest-paging'
		 });
	}
	function onAfterserviceCHEAPEST(){
		$('#item-service-CHEAPEST SPAN').html(this.title);
	}
	function onBeforeserviceCHEAPEST(){
		$('#item-service-CHEAPEST SPAN').html(this.title);
	}
</script>
		<div id="service-cheapest-paging" class="item-pager-element">
		</div>
<div id="item-service-CHEAPEST" class="outer-item-element">
			<div class="the-items inner-item-element">
			</div>
			<ul class="item-prev-next-element">
				<li><a id="prevserviceCHEAPEST" href="javascript: void(0);"><img src="/images/prev.png" /></a> <a id="nextserviceCHEAPEST" href="javascript: void(0);"><img src="/images/next.png" /></a></li>
			</ul>
</div>	