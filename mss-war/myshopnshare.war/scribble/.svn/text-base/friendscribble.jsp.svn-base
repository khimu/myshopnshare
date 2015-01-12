<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

<script type="text/javascript">		

$(document).ready(function() { 
	$.ajax( {
		type :"post",
		url :"/secure/scribble.do",
		data : {
			method :'view', personId: '${person.id}'
		},
		dataType :'json',
		success : function(json) {
			$.each(json.scribbles, function(i,scribble){
				$('#scribble-content').append('<sns:scribble thumbnail="'+ scribble.faceIcon +'" fullName="' + scribble.fullName + '" message="' + scribble.message + '" enteredDate="' + scribble.enteredDate + '"/>');
	         });
		}
	});	
});

</script>

<style type="text/css">

	#scribble-entry {
		list-style-type: none;
	}
		.hoverme:hover {
			background-color:#FFF !important;
		}

	.scribble-board {
		color: gray;
		position:relative;
		clear: both;
		float: none;
		height: 70px;
	}
      
      #scribble-board span {
      	color: #000;
      	padding-top: 5px;
      }
      
      #scribble-board input, #scribble-board span {
      	float: left;
      }
	
	#scribble-link-buttons {
   		position: absolute;  
   		top: 50px;  
   		left: 450px; 
	}

	#scribble-link-buttons a:hover {
		color: #303030;
	}	
		
	#scribble-link-buttons a {
		text-decoration: none;
		width: 70px;
		height: 30px;
		margin: 5px;
	}
</style>
<style type="text/css">
		
	#scribble {
		font-size: 9px;
	}
	
	#scribble input {
		font-size: 9px;
		color: #808080;
		padding: 2px 2px;
		width: 170px;
	}
	
	#scribble-comments {
		clear both;padding:2px;vertical-align:top;position:relative;color:#FFF;font-weight:bold;
	}
	#scribble-comments a{
		color:#FFF;
		font-weight:bold;
	}
	
</style>

<script type="text/javascript">
	$(document).ready(function(){
		$('#scribble-on-page').click(function(){
			new Boxy('#scribble-form', {title: 'Scribble Note', fixed: false, modal: true}); 
		});		
	});
</script>

<div style="background-color: #000; width:100%; height:100%;">	

<%@ include file="../scribble/form.jsp" %>

	<div id="all-scribbles" class="postings hide-element">
				<a href="#" id="scribble-on-page">
					<span><img src="/images/addscribble.png" /></span>
				</a>	
		
			<ul id="scribble-content" class="item-journals">
			</ul>
	</div>
</div>


