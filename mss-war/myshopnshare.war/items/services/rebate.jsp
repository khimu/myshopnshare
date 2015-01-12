<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<script type="text/javascript">
	function cycleserviceREBATE(items, category){
		$('#all-items-by-category').html('');

			for(i = 0; i < items.length; i ++){
				$('#service-rebate-paging').show();
				$('#service-rebate-paging').html('1');
				var todisplay = buildScreen(category, items, i);
				
				i = i + 8;

				$('#item-service-' + category + ' .the-items').append('<p class="left-right">' + todisplay + '</p>');
			}

		$("a[rel^='prettyPhoto']").prettyPhoto();
		
		$('#service-clearance-paging').hide();
		$('#service-free-paging').hide();
		$('#service-hottest-paging').hide();
		$('#service-latest-paging').hide();
		$('#service-cheapest-paging').hide();	

		if(items.length > 8){
			$('#service-rebate-paging').html('');
		}
		
		$('#service-rebate-paging').show();			
		$('#item-service-REBATE DIV').cycle({ 
		    fx:     'fade', 
		    timeout: 5000,
		    next:   '#nextserviceREBATE', 
		    prev:   '#prevserviceREBATE',
		    pause: 	true,
		    pager: '#service-rebate-paging'
		 });
	}
	function onAfterserviceREBATE(){
		$('#item-service-REBATE SPAN').html(this.title);
	}
	function onBeforeserviceREBATE(){
		$('#item-service-REBATE SPAN').html(this.title);
	}
</script>
		<div id="service-rebate-paging"  class="item-pager-element">
		</div>
		<div id="item-service-REBATE" class="outer-item-element">
			<div class="the-items inner-item-element">
			</div>
			<ul class="item-prev-next-element">
				<li><a id="prevserviceREBATE" href="javascript: void(0);"><img src="/images/prev.png" /></a> <a id="nextserviceREBATE" href="javascript: void(0);"><img src="/images/next.png" /></a></li>
			</ul>
		</div>