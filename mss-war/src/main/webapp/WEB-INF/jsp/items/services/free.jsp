<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<script type="text/javascript">
	function cycleserviceFREE(items, category){
		$('#all-items-by-category').html('');

			for(i = 0; i < items.length; i ++){
				$('#service-free-paging').show();
				$('#service-free-paging').html('1');
				var todisplay = buildScreen(category, items, i);
				
				i = i + 8;

				$('#item-service-' + category + ' .the-items').append('<p class="left-right">' + todisplay + '</p>');
			}

		$("a[rel^='prettyPhoto']").prettyPhoto();
		
		$('#service-clearance-paging').hide();
		$('#service-hottest-paging').hide();
		$('#service-latest-paging').hide();
		$('#service-rebate-paging').hide();
		$('#service-cheapest-paging').hide();	

		if(items.length > 8){
			$('#service-free-paging').html('');
		}
		
		$('#service-free-paging').show();		
		$('#item-service-FREE DIV').cycle({ 
		    fx:     'fade', 
		    timeout: 5000,
		    next:   '#nextserviceFREE', 
		    prev:   '#prevserviceFREE',
		    pause: 	true,
		    pager: '#service-free-paging'
		 });
	}
	function onAfterserviceFREE(){
		$('#item-service-FREE SPAN').html(this.title);
	}
	function onBeforeserviceFREE(){
		$('#item-service-FREE SPAN').html(this.title);
	}
</script>
		<div id="service-free-paging" class="item-pager-element">
		</div>
<div id="item-service-FREE" class="outer-item-element">
			<div class="the-items inner-item-element">
			</div>
			<ul class="item-prev-next-element">
				<li><a id="prevserviceFREE" href="javascript: void(0);"><img src="/images/prev.png" /></a> <a id="nextserviceFREE" href="javascript: void(0);"><img src="/images/next.png" /></a></li>
			</ul>
</div>			