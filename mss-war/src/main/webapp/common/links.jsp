<%@ include file="/WEB-INF/jsp/includes.jsp"%>


<script type="text/javascript">

	function showCategorizedItems(){
			 $.ajax({  
			       type: "post",  
			       url: "/secure/itemCategories.do",
			       data: {method: 'view'},
			       success: function(data){ 			       
			    	   $('#center-2').hide();
			    	   $('#center-3').hide();
						$('#center').show();
						$('#center').html(data);
			       }
			 });			
	}
	
	function showPossessions(){
		 $.ajax({  
		       type: "post",  
		       url: "/secure/possessions.do",
		       data: {method: 'view'},
		       success: function(data){ 			       
		    	   $('#center-2').hide();
		    	   $('#center-3').hide();
					$('#center').show();
					$('#center').html(data);
		       }
		 });			
	}
	function showSearch(){
		 $.ajax({  
		       type: "post",  
		       url: "/secure/search.do",
		       data: {method: 'view'},
		       success: function(data){ 
					$('#center-2').hide();
					$('#center').show();
					$('#center').html(data);
		       }
		 });			
	}	

	function editLinks(){
		if($('#toggleLinks').is(':visible')){
			$('#toggleLinks').hide();
		}else{
			$('#toggleLinks').show();
		}
	}
</script>

<div id="toplinks">
			<a href="/secure/home.do"><span>Home</span></a>	
			&nbsp; &nbsp;
			<span><a href="javascript:void(0);" onclick="showSearch();">Shop</a></span>
			
			&nbsp;  &nbsp;
			<a href="javascript:void(0);" onclick="editLinks()">Edit</a>
			
			&nbsp;  &nbsp;
			
			<a href="j_spring_security_logout"><span>Logout</span></a>
</div>	

<div id="toggleLinks" style="position:absolute; top:155px; right:250px; display:none; padding:10px; border:1px solid #ccff; background-color:#27409e; color:#fff; z-index:101;">
	
	<div style="padding:4px;"><a href="/secure/profile.do?KeepThis=true&TB_iframe=true&height=450&width=760" class="thickbox" title="Profile"><span>Profile</span></a>	
	</div>
	<div style="padding:4px;">
	<a href="/secure/account.do?KeepThis=true&TB_iframe=true&height=450&width=760" class="thickbox" title="Account"><span>Account</span></a>
	</div>
	<div style="padding:4px;">
	<a href="/secure/settings.do?KeepThis=true&TB_iframe=true&height=450&width=760" class="thickbox" title="Settings"><span>Settings</span></a>
	</div>
</div>
