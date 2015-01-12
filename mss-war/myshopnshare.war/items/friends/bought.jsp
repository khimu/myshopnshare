<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">
	function cyclefriendsBOUGHT(items, category){
		$('#all-items-by-category').html('');

			for(i = 0; i < items.length; i ++){
				$('#friends-bought-paging').show();
				var todisplay = buildScreen(category, items, i);
				
				i = i + 8;

				$('#item-friends-' + category + ' .the-items').append('<p class="left-right">' + todisplay + '</p>');
			}		

		$("a[rel^='prettyPhoto']").prettyPhoto();
		
		$('#friends-selling-paging').hide();
		$('#friends-recommend-paging').hide();
		$('#friends-advice-paging').hide();
		$('#friends-want-paging').hide();		
				
		$('#friends-bought-paging').show();
		$('#item-friends-BOUGHT DIV').cycle({ 
		    fx:     'fade', 
		    timeout: 5000,
		    next:   '#nextfriendsBOUGHT', 
		    prev:   '#prevfriendsBOUGHT',
		    pause: 	true,
		    pager: '#friends-bought-paging'
		 });
		$("a[rel^='prettyPhoto']").prettyPhoto();
	}
	function onAfterfriendsBOUGHT(){
		$('#item-friends-BOUGHT SPAN').html(this.title);
	}
	function onBeforefriendsBOUGHT(){
		$('#item-friends-BOUGHT SPAN').html(this.title);
	}
</script>
		<div id="friends-bought-paging" class="item-pager-element">
		</div>
		<div id="item-friends-BOUGHT" class="outer-item-element">
			<div class="the-items inner-item-element">
			</div>
			<ul class="item-prev-next-element">
				<li><a id="prevfriendsBOUGHT" href="javascript: void(0);"><img src="/images/prev.png" /></a> <a id="nextfriendsBOUGHT" href="javascript: void(0);"><img src="/images/next.png" /></a></li>
			</ul>
		</div>