<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">

	function getNews(method){
		 $.ajax({  
		       type: "POST",  
		       url: "/secure/news.do",
		       data: {method: method, friendId: '${person.id}'},
		       dataType: 'json', 
		       success: function(json){  
		    	   document.getElementById('news-box').innerHTML = '';
		    	   
					$.each(json.newzz, function(i,newz){
						$('#news-box').append('<sns:newsdata personId="'+ ${person.id} + '" newsId="'+ newz.id + '" newsPersonId="' + newz.newsPersonId + '" enteredDate="' + newz.enteredDate + '">' + unescapeHTML(newz.message) + '</sns:newsdata>');
					});

					//$("a[rel^='prettyPhoto']").prettyPhoto();

					document.getElementById('news-paging').innerHTML = '';
					
					$('#news-box').cycle({ 
					    fx:     'scrollUp', 
					    timeout: 5000,
					    pause:   true,
					    next:   '#newsNext', 
					    prev:   '#newsPrev',
					    pager: '#news-paging'
					 });	         

					
		       } 
		     });
	}
	
	$(document).ready(function(){
		getNews('afriends');
	});
</script>



<div id="main-news-element" class="myshopnshare-content-bl">
	<div class="myshopnshare-header">
		<h3>Latest News</h3>
	</div>


		<div id="news-paging" class="news-pager">
		</div>
	<div style="float: left;">
		<div id="news-box" class="news-box">
		</div>
	</div>
	<div class="news-controller">
		<div class="news-prev-next">
			<a href="#" id="newsPrev"><img src="/images/prev.png"/></a> |
			<a href="#" id="newsNext"><img src="/images/next.png"/></a>
		</div>			
	</div>

</div>
