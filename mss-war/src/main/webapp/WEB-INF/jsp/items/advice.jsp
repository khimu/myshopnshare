<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">

	// singleitem tag do not change to <div height:75.  Does not work for ie

	function cycleADVICE(items, category){
		$('#all-items-by-category').html('');

		for(i = 0; i < items.length; i ++){
			$('#self-advice-paging').show();
			$('#self-advice-paging').html('1');	

			var todisplay = buildScreen(category, items, i);
			
			i = i + 8;

			$('#item-' + category + ' .the-items').append('<p class="left-right">' + todisplay + '</p>');
		}
			
		$("a[rel^='prettyPhoto']").prettyPhoto();

		$('#self-selling-paging').hide();
		$('#self-recommend-paging').hide();
		$('#self-bought-paging').hide();
		$('#self-want-paging').hide();
		
		$('#self-advice-paging').show();

		$('#item-ADVICE DIV').cycle({ 
		    fx:     'fade', 
		    timeout: 5000,
		    next:   '#nextADVICE', 
		    prev:   '#prevADVICE',
		    pause: 	true,
		    pager:	'#self-advice-paging'
		 });
		
	}
	/* ONLY WORKS WITH 2 + items.  DOES NOT work with only 1 item. */
	function onAfterADVICE(){
		$('#item-ADVICE SPAN').html(this.title);
	}
	function onBeforeADVICE(){
		$('#item-ADVICE SPAN').html(this.title);
	}
</script>

		<div id="self-advice-paging" class="item-pager-element">
		</div>
		<div id="item-ADVICE" class="outer-item-element">
			<div class="the-items inner-item-element">
			</div>
			<ul class="item-prev-next-element">
				<li><a id="prevADVICE" href="javascript: void(0);"><img src="/images/prev.png" /></a> <a id="nextADVICE" href="javascript: void(0);"><img src="/images/next.png" /></a></li>
			</ul>
		</div>
