<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<script type="text/javascript">
	function cyclefriendsSELLING(items, category){
		$('#all-items-by-category').html('');
	
			for(i = 0; i < items.length; i ++){
				$('#friends-selling-paging').show();
				var todisplay = buildScreen(category, items, i);
				
				i = i + 8;

				$('#item-friends-' + category + ' .the-items').append('<p class="left-right">' + todisplay + '</p>');
			}	

		$("a[rel^='prettyPhoto']").prettyPhoto();
		
		$('#friends-recommend-paging').hide();
		$('#friends-bought-paging').hide();
		$('#friends-advice-paging').hide();
		$('#friends-want-paging').hide();

		$('#friends-selling-paging').show();
		$('#item-friends-SELLING DIV').cycle({ 
		    fx:     'fade', 
		    timeout: 5000,
		    next:   '#nextfriendsSELLING', 
		    prev:   '#prevfriendsSELLING',
		    pause: 	true,
		    pager: '#friends-selling-paging'
		 });
	}
	function onAfterfriendsSELLING(){
		$('#item-friends-SELLING SPAN').html(this.title);
	}
	function onBeforefriendsSELLING(){
		$('#item-friends-SELLING SPAN').html(this.title);
	}
</script>
		<div id="friends-selling-paging" class="item-pager-element">
		</div>
		<div id="item-friends-SELLING" class="outer-item-element">
			<div class="the-items inner-item-element">
			</div>
			<ul class="item-prev-next-element">
				<li><a id="prevfriendsSELLING" href="javascript: void(0);"><img src="/images/prev.png" /></a> <a id="nextfriendsSELLING" href="javascript: void(0);"><img src="/images/next.png" /></a></li>
			</ul>
		</div>