<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">

	function cycleadviceinitialINITIAL(items, category){
		$('#all-items-by-category').html('');

		for(i = 0; i < items.length; i ++){
			$('#self-adviceinitialINITIAL-paging').show();

			var todisplay = buildInitialScreen(category, items, i);
			
			i = i + 8;

			$('#item-' + category + ' .the-items').append('<p class="left-right">' + todisplay + '</p>');
		}
			
		$("a[rel^='prettyPhoto']").prettyPhoto();

		$('#self-selling-paging').hide();
		$('#self-recommend-paging').hide();
		$('#self-bought-paging').hide();
		$('#self-want-paging').hide();
		
		$('#self-adviceinitialINITIAL-paging').show();

		$('#item-adviceinitialINITIAL DIV').cycle({ 
		    fx:     'fade', 
		    timeout: 5000,
		    next:   '#nextadviceinitialINITIAL', 
		    prev:   '#prevadviceinitialINITIAL',
		    pause: 	true,
		    pager:	'#self-adviceinitialINITIAL-paging'
		 });
		
	}
</script>

		<div id="self-adviceinitialINITIAL-paging" class="item-pager-element">
		</div>
		<div id="item-adviceinitialINITIAL" class="outer-item-element">
			<div class="the-items inner-item-element">
			</div>
			<ul class="item-prev-next-element">
				<li><a id="prevadviceinitialINITIAL" href="javascript: void(0);"><img src="/images/prev.png" /></a> <a id="nextadviceinitialINITIAL" href="javascript: void(0);"><img src="/images/next.png" /></a></li>
			</ul>
		</div>
