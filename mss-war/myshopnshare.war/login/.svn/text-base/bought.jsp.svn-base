<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">
	function cycleBOUGHT(items, category){
		$('#all-items-by-category').html('');

		for(i = 0; i < items.length; i ++){
			$('#self-bought-paging').show();

			var todisplay = buildScreen(category, items, i);
			
			i = i + 8;

			$('#item-' + category + ' .the-items').append('<p class="left-right">' + todisplay + '</p>');
		}


		$("a[rel^='prettyPhoto']").prettyPhoto();
		
		$('#self-selling-paging').hide();
		$('#self-recommend-paging').hide();
		$('#self-advice-paging').hide();
		$('#self-want-paging').hide();
		
		$('#self-bought-paging').show();
		
		$('#item-BOUGHT DIV').cycle({ 
		    fx:     'fade', 
		    timeout: 5000,
		    next:   '#nextBOUGHT', 
		    prev:   '#prevBOUGHT',
		    pause: 	true,
		    pager:	'#self-bought-paging'
		 });
	}
	function onAfterBOUGHT(){
		$('#item-BOUGHT SPAN').html(this.title);
	}
	function onBeforeBOUGHT(){
		$('#item-BOUGHT SPAN').html(this.title);
	}
</script>

		<div id="self-bought-paging" class="item-pager-element">
		</div>
		<div id="item-BOUGHT" class="outer-item-element">
			<div class="the-items inner-item-element">
			</div>
			<ul class="item-prev-next-element">
				<li><a id="prevBOUGHT" href="javascript: void(0);"><img src="/images/prev.png" /></a> <a id="nextBOUGHT" href="javascript: void(0);"><img src="/images/next.png" /></a></li>
			</ul>
		</div>