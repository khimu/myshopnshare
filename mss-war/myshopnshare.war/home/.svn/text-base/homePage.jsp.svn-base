<%@ include file="/WEB-INF/jsp/includes.jsp"%>


  <script type="text/javascript">
	$(document).ready(function() { 
	    $("#example > ul").tabs();
	  });
  </script>
  
  <style type="text/css">
  	#example table {
  		margin: 0px;
  		width: 100%;
  	}
  	
  	#example TH {
  		width: 70px;
  		height: 10px;
  		font-weight: normal;
  		padding: 2px 5px;
  	}
  	
  	#example TD {
  		padding: 2px 5px;
  	}
  </style>

        <div id="example" class="flora">
            <ul>
                <li><a href="#fragment-1"><span>News</span></a></li>
                <li><a href="#fragment-2"><span>${person.fullName}</span></a></li>
                <li><a href="#fragment-3"><span>Shopping</span></a></li>
                <li><a href="#fragment-4"><span>Friends</span></a></li>
            </ul>
            <div id="fragment-1">
            	<%--@ include file="../news/news.jsp" --%>   
            	<%@ include file="../news/all.jsp" %>        
            </div>
            
            <div id="fragment-2">
				<p style="padding-top:5px;padding-bottom:5px;clear:both;">Mouse over image to pause item.  Click on photo to enlarge photo.</p>

				<%@ include file="../items/all.jsp" %>
            </div>
            <div id="fragment-3">

				<p style="padding-top:5px;padding-bottom:5px;clear:both;">Mouse over image to pause item.  Click on photo to enlarge photo.</p>          

				<%@ include file="../items/world/world.jsp" %>
            </div>
            <div id="fragment-4">

				<p style="padding-top:5px;padding-bottom:5px;clear:both;">Mouse over image to pause item.  Click on photo to enlarge photo.</p>

				<%@ include file="../items/friends/friends.jsp" %>	            
            </div>
        </div>	

