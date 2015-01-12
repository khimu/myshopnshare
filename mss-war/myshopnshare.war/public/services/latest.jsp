<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">
	function cycleserviceLATEST(items, category){
		$('#all-items-by-category').html('');

			for(i = 0; i < items.length; i ++){
				$('#service-latest-paging').show();
				$('#service-latest-paging').html('1');
				var todisplay = buildPublicScreen(category, items, i);
				
				i = i + 21;

				$('#item-service-' + category + ' .the-items').append('<p class="left-right">' + todisplay + '</p>');
			}

		$("a[rel^='prettyPhoto']").prettyPhoto();
		
		$('#service-clearance-paging').hide();
		$('#service-free-paging').hide();
		$('#service-hottest-paging').hide();
		$('#service-rebate-paging').hide();
		$('#service-cheapest-paging').hide();	

		if(items.length > 7){
			$('#service-latest-paging').html('');
		}
		
		$('#service-latest-paging').show();	
		
		$('#item-service-LATEST DIV').cycle({ 
		    fx:     'fade', 
		    timeout: 5000,
		    next:   '#nextserviceLATEST', 
		    prev:   '#prevserviceLATEST',
		    pause: 	true,
		    pager: '#service-latest-paging'
		 });
	}
	function onAfterserviceLATEST(){
		$('#item-service-LATEST SPAN').html('<p style="white-space: wrap;">' + this.title + '</p>');
	}
	function onBeforeserviceLATEST(){
		$('#item-service-LATEST SPAN').html('<p style="white-space: wrap;>' + this.title + '</p>');
	}
</script>

		<div id="service-latest-paging" class="item-pager-element">
		</div>
		<div id="item-service-LATEST" class="outer-item-element">
			<div class="the-items inner-item-element">
			</div>
			<ul class="item-prev-next-element">
				<li><a id="prevserviceLATEST" href="javascript: void(0);"><img src="/images/prev.png" /></a> <a id="nextserviceLATEST" href="javascript: void(0);"><img src="/images/next.png" /></a></li>
			</ul>
		</div>