<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

<script type="text/javascript">

</script>

<script type="text/javascript">

	function getFull(){
		 $.ajax({  
		       type: "POST",  
		       url: "/secure/news.do",
		       data: {method: 'full', personId: '${person.id}'},
		       dataType: 'html',
		       success: function(data){  
		    	   $('#center').html(data);
		       } 
		     });		
	}

	function getNews(method){
		 $.ajax({  
		       type: "POST",  
		       url: "/secure/news.do",
		       data: {method: method, personId: '${person.id}'},
		       dataType: 'json', 
		       success: function(json){  
		    	   document.getElementById('news-box').innerHTML = '';
					$.each(json.newzz, function(i,newz){
						if(newz.newsPersonId == ${person.id}){
							$('#news-box').append('<sns:ownnewsdata personId="'+ ${person.id} + '" newsId="'+ newz.id + '" newsPersonId="' + newz.newsPersonId + '" enteredDate="' + newz.enteredDate + '">' + unescapeHTML(newz.message) + '</sns:ownnewsdata>');
						}else{
							$('#news-box').append('<sns:newsdata personId="'+ ${person.id} + '" newsId="'+ newz.id + '" newsPersonId="' + newz.newsPersonId + '" enteredDate="' + newz.enteredDate + '">' + unescapeHTML(newz.message) + '</sns:newsdata>');
						}
						//alert($('#news-box').html());
					});

					//$("a[rel^='prettyPhoto']").prettyPhoto();
					

					document.getElementById('news-paging').innerHTML = '';
					
					$('#news-box').cycle({ 
					    fx:     'scrollDown', 
					    timeout: 5000,
					    pause:   true,
					    next:   '#newsNext', 
					    prev:   '#newsPrev',
					    pager: '#news-paging'
					 });	         

					//$("a[rel^='prettyPhoto']").prettyPhoto();
		       } 
		     });
	}
	
	$(document).ready(function(){
		getNews('world');
		
		$('#news-options').change(function(){
			/** LOADING SELECTED NEWS **/
			var selected = $('#news-options :selected').val();
			var filter = selected.replace('#news-', '');
			getNews(filter);
			//$("a[rel^='prettyPhoto']").prettyPhoto();
		});
		
	});
</script>




<div id="main-news-element" class="myshopnshare-content-bl">
	<div class="myshopnshare-header">
		<h3>Latest News  <a class="myshopnshare-for-news" href="#" onclick="getFull();"><img src="/images/viewall.png" /></a></h3>
	</div>
		<div id="news-paging" class="news-pager">
		</div>
	<div style="float: left;">
		<div id="news-box" class="news-box">
		</div>
	</div>
	<div class="news-controller">
		<div class="news-prev-next">
			<a href="#" id="newsPrev"><img src="/images/prev.png"/></a>
			<a href="#" id="newsNext"><img src="/images/next.png"/></a>
		</div>	
		<div class="news-select-wrap">
			<select id="news-options" name="news-options">
				<option value="#news-world" selected>World News</option>
				<option value="#news-self">My News</option>
				<option value="#news-friends">Friends News</option>
			</select>
		</div>
	</div>

</div>
