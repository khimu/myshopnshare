<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<script type="text/javascript">
	function cycleserviceHOTTEST(items, category){
		$('#all-items-by-category').html('');

			for(i = 0; i < items.length; i ++){
				$('#service-hottest-paging').show();
				$('#service-hottest-paging').html('1');
				var todisplay = buildScreen(category, items, i);
				
				i = i + 8;

				$('#item-service-' + category + ' .the-items').append('<p class="left-right">' + todisplay + '</p>');
			}
		
		$("a[rel^='prettyPhoto']").prettyPhoto();
		
		$('#service-clearance-paging').hide();
		$('#service-free-paging').hide();
		$('#service-latest-paging').hide();
		$('#service-rebate-paging').hide();
		$('#service-cheapest-paging').hide();	

		if(items.length > 8){
			$('#service-hottest-paging').html('');
		}
		
		$('#service-hottest-paging').show();			
		$('#item-service-HOTTEST DIV').cycle({ 
		    fx:     'fade', 
		    timeout: 5000,
		    next:   '#nextserviceHOTTEST', 
		    prev:   '#prevserviceHOTTEST',
		    pause: 	true,
		    pager:	'#service-hottest-paging'
		 });
	}
	function onAfterserviceHOTTEST(){
		$('#item-service-HOTTEST SPAN').html(this.title);
	}
	function onBeforeserviceHOTTEST(){
		$('#item-service-HOTTEST SPAN').html(this.title);
	}
</script>
		<div id="service-hottest-paging" class="item-pager-element">
		</div>
		<div id="item-service-HOTTEST" class="outer-item-element">
			<div class="the-items inner-item-element">
			</div>
			<ul class="item-prev-next-element">
				<li><a id="prevserviceHOTTEST" href="javascript: void(0);"><img src="/images/prev.png" /></a> <a id="nextserviceHOTTEST" href="javascript: void(0);"><img src="/images/next.png" /></a></li>
			</ul>
		</div>
