<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

<script type="text/javascript">
	function getNextPageNews(start){
		var selected = $('#news-options :selected').val();
		var filter = selected.replace('#news-', '');
	 $.ajax({  
	       type: "POST",  
	       url: "/secure/news.do",
	       data: {method: 'page', start: start, category: filter, personId: '${person.id}'},
	       dataType: 'html',
	       success: function(data){  
	    	   $('#news-collections').html(data);
	       } 
	     });
	}
</script>

		<div class="myshopnshare-header">
			<h3>Latest News</h3>
		</div>
		<div id="news-paging">
			<c:forEach begin="0" end="${total/15}" varStatus="status">
				<a href="#" onclick="getNextPageNews(${status.index * 10})">${status.index + 1}</a> &nbsp;&nbsp;
			</c:forEach>
		</div>
		<div class="news-controller">
			<div class="news-select-wrap">
				<select id="news-options"  name="news-options" onchange="reloadFullNews(this[this.selectedIndex].value);">
					<option value="#news-world">World News</option>
					<option value="#news-self">My News</option>
					<option value="#news-friends">Friends News</option>
				</select>
			</div>			
		</div>		
		<div class="clear"></div>
		
		<div id="news-collections">
			<div>Size: ${fn:length(newzs)}</div>
			<div style="float: left;">
				
				<div id="news-box" class="news-box">
				<c:forEach items="${newzs}" var="newz">
					<sns:newsdata personId="${person.id}" newsId="${newz.id}" newsPersonId="${newz.person.id}" enteredDate="${newz.enteredDate}">${newz.message}</sns:newsdata>			
					<hr/>
				</c:forEach>
				</div>
	
			</div>
		</div>

