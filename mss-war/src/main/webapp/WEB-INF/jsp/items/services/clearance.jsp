<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<script type="text/javascript">
	function cycleserviceCLEARANCE(items, category){
		$('#all-items-by-category').html('');

			for(i = 0; i < items.length; i ++){
				$('#service-clearance-paging').show();
				$('#service-clearance-paging').html('1');
				var todisplay = buildScreen(category, items, i);
				
				i = i + 8;

				$('#item-service-' + category + ' .the-items').append('<p class="left-right">' + todisplay + '</p>');
			}

		$("a[rel^='prettyPhoto']").prettyPhoto();
		
		$('#service-free-paging').hide();
		$('#service-hottest-paging').hide();
		$('#service-latest-paging').hide();
		$('#service-rebate-paging').hide();
		$('#service-cheapest-paging').hide();	

		if(items.length > 8){
			$('#service-clearance-paging').html('');
		}
		
		$('#service-clearance-paging').show();			
		$('#item-service-CLEARANCE DIV').cycle({ 
		    fx:     'fade', 
		    timeout: 5000,
		    next:   '#nextserviceCLEARANCE', 
		    prev:   '#prevserviceCLEARANCE',
		    pause: 	true,
		    pager: '#service-clearance-paging'
		 });
	}
	function onAfterserviceCLEARANCE(){
		$('#item-service-CLEARANCE SPAN').html(this.title);
	}
	function onBeforeserviceCLEARANCE(){
		$('#item-service-CLEARANCE SPAN').html(this.title);
	}
</script>
		<div id="service-clearance-paging" class="item-pager-element">
		</div>
<div id="item-service-CLEARANCE" class="outer-item-element">
			<div class="the-items inner-item-element">
			</div>
			<ul class="item-prev-next-element">
				<li><a id="prevserviceCLEARANCE" href="javascript: void(0);"><img src="/images/prev.png" /></a> <a id="nextserviceCLEARANCE" href="javascript: void(0);"><img src="/images/next.png" /></a></li>
			</ul>
</div>	