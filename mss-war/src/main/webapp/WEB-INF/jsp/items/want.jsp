<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">
	function cycleWANT(items, category){
		$('#all-items-by-category').html('');
			for(i = 0; i < items.length; i ++){
				$('#self-want-paging').show();
				var todisplay = buildScreen(category, items, i);
				
				i = i + 8;

				$('#item-' + category + ' .the-items').append('<p class="left-right">' + todisplay + '</p>');
			}

		$("a[rel^='prettyPhoto']").prettyPhoto();
		
		$('#self-selling-paging').hide();
		$('#self-recommend-paging').hide();
		$('#self-bought-paging').hide();
		$('#self-advice-paging').hide();
		
		$('#self-want-paging').show();
		$('#item-WANT DIV').cycle({ 
			fx:     'fade', 
		    timeout: 5000,
		    next:   '#nextWANT', 
		    prev:   '#prevWANT',
		    pause: 	true,
		    pager: '#self-want-paging'
		 });
	}
	function onAfterWANT(){
		$('#item-WANT SPAN').html(this.title);
	}
	function onBeforeWANT(){
		$('#item-WANT SPAN').html(this.title);
	}
</script>
		<div id="self-want-paging" class="item-pager-element">
		</div>
<div id="item-WANT" class="outer-item-element">
			<div class="the-items inner-item-element">
			</div>
			<ul class="item-prev-next-element">
				<li><a id="prevWANT" href="javascript: void(0);"><img src="/images/prev.png" /></a> <a id="nextWANT" href="javascript: void(0);"><img src="/images/next.png" /></a></li>
			</ul>
</div>			