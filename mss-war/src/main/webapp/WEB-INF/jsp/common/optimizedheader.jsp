<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<script type="text/javascript">
	var images = new Array(); 
	var original = new Array();

	function getFriends(){
		 $j.ajax({  
		       type: 'POST',  
		       url: '/secure/manageFriends.do',
		       data: {personId: '${person.id}'},
		       //dataType: 'json', 
		       success: function(data){ 
			       $j('#title_info').html(data);
			       /* 
		       		var index = 0;
					$j.each(json.friends, function(i,friend){
						
						images[index] = '' + friend.friendProfile.thumbnail;
						original[index] = '' + friend.friendProfile.glimpse;
						
						var list = $j('<li id="portfolio_' + index + '"><a href="' + original[index] + '" rel="lightbox"><img src="' + images[index] + '"/></a></li>').attr('class','loading');
			              $j('ul#portfolio').append(list); 
			              var curr = $j("ul#portfolio li#portfolio_"+index); 
			              $j(curr).removeClass('loading');
			              index++;
						
			             						
					});
					*/
					/*
				     $j(images).each(function(index,value){ 
			              var list = $j('<li id="portfolio_' + index + '"><a href="' + original[index] + '" rel="lightbox"><img src="' + value + '"/></a></li>').attr('class','loading');
			              $j('ul#portfolio').append(list); 
			              var curr = $j("ul#portfolio li#portfolio_"+index); 
			              $j(curr).removeClass('loading');
			              
			       		
			              var curr = $j("ul#portfolio li#portfolio_"+index); 
			              var theimg = $j("ul#portfolio li#portfolio_li_"+index + " a");
			              
			              var img = new Image();
			              $j(img).load(function () { 
			                  $j(this).css('display','none');
			                  $j(curr).removeClass('loading');
			                  $j(curr).append(this); 
			                  $j(this).fadeIn(); 
			                  alert($j(curr).html());
			              }).error(function () { 
			                  $j(curr).remove(); 
			              }).attr('src', value);
			              
				      }); 
				      
				      $j('ul#portfolio').addClass('gallery');
				      */
      				$j('a[@rel*=lightbox]').lightBox({fixedNavigation:true});	 							         
		       } 
		     });
	}

    $j(function () { 
		//setTimeout('getFriends()', 300);
		//getFriends();
  	});  	  
</script>

<div id="zaglavlje">
	<%@ include file="toolbar.jsp"%>
	<div id="title"><a href="#"><font style="color: #99FF00;">my</font><font style="color: #66AA44;">SHOP</font><font style="color: #99FF00">n</font><font style="color: #66AA44">SHARE</font></a>
	</div>
	<div id="friends-list">
		<jsp:include page="../friends/friends.jsp"></jsp:include>
	</div>
	<a href="j_spring_security_logout">Logout</a>
</div>

