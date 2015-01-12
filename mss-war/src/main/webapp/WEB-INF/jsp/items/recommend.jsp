<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<script type="text/javascript">
	function cycleRECOMMEND(items, category){
		$('#all-items-by-category').html('');
		for(i = 0; i < items.length; i ++){
			$('#self-recommend-paging').show();
			var todisplay = buildScreen(category, items, i);
			
			i = i + 8;

			$('#item-' + category + ' .the-items').append('<p class="left-right">' + todisplay + '</p>');
		}

		$("a[rel^='prettyPhoto']").prettyPhoto();

		$('#self-selling-paging').hide();
		$('#self-advice-paging').hide();
		$('#self-bought-paging').hide();
		$('#self-want-paging').hide();
		
		$('#self-recommend-paging').show();
		
		$('#item-RECOMMEND DIV').cycle({ 
		    fx:     'fade', 
		    timeout: 5000,
		    next:   '#nextRECOMMEND', 
		    prev:   '#prevRECOMMEND',
		    pause: 	true,
		    pager:	'#self-recommend-paging'
		 });
	}
	function onAfterRECOMMEND(){
		$('#item-RECOMMEND SPAN').html(this.title);
	}
	function onBeforeRECOMMEND(){
		$('#item-RECOMMEND SPAN').html(this.title);
	}
</script>
		<div id="self-recommend-paging" class="item-pager-element">
		</div>
<div id="item-RECOMMEND" class="outer-item-element">
			<div  class="the-items inner-item-element">
			</div>
			<ul class="item-prev-next-element">
				<li><a id="prevRECOMMEND" href="javascript: void(0);"><img src="/images/prev.png" /></a> <a id="nextRECOMMEND" href="javascript: void(0);"><img src="/images/next.png" /></a></li>
			</ul>
</div>	