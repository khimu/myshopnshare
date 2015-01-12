<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">		

</script>

<style type="text/css">
		.hoverme:hover {
			background-color:#FFF !important;
		}

	.journal-board {
		color: gray;
		position:relative;
		clear: both;
		float: none;
		height: 70px;
	}
      
      #journal-board span {
      	color: #000;
      	padding-top: 5px;
      }
      
      #journal-board input, #journal-board span {
      	float: left;
      }
	
	#journal-link-buttons {
   		position: absolute;  
   		top: 50px;  
   		left: 450px; 
	}

	#journal-link-buttons a:hover {
		color: #303030;
	}	
		
	#journal-link-buttons a {
		text-decoration: none;
		width: 70px;
		height: 30px;
		margin: 5px;
	}
</style>
<style type="text/css">
		
	#journal {
		font-size: 9px;
	}
	
	#journal input {
		font-size: 9px;
		color: #808080;
		padding: 2px 2px;
		width: 170px;
	}
	
	#journal-comments {
		clear both;
		padding:2px;
		vertical-align:top;
		position:relative;
		font-weight:bold;
	}
	#journal-comments a{
		font-weight:bold;
	}
	
</style>

<script type="text/javascript">
	$(document).ready(function(){
		$('#journal-on-page').click(function(){
			new Boxy('#journal-form', {title: 'Advice Journal', modal: true, fixed: false}); 
		});		
	});
</script>


<%@ include file="../journal/form.jsp" %>


<p style="padding-top:5px;padding-bottom:5px;clear:both;">

</p>

<div id="all-journals" style="display:none;">
			<a href="#" id="journal-on-page">
				<span>Add Advice</span>
			</a>	
	
		<div id="journal-content" style="padding: 5px;">
		</div>
	
	<%--
	<div id="journal-comments"> 
		<div style="text-align:left;float:left;"><a href="#">Add Comments</a> | <a  href="#">Show All</a></div>
		<div style="text-align:right;float:none;"><a href="journalNext"><img src="/images/next.png" /></a> | <a href="journalPrev"><img src="/images/prev.png" /></a></div>
	</div>
	
	<div id="paginate-journal" style="clear both;"> 
	</div>	
	--%>
</div>


