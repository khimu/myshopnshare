<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

<style type="text/css">

  ul.item-comments {
    list-style-type:none;
    margin:10px;
    padding:0px;
    width: 700px;
  }
  
  ul.item-comments li {
  	position: relative;
    padding:5px 5px 4px 5px;
    border: 1px solid #FFF;
    margin-bottom:10px;
    height:1%; /* Fix for MSIE peekaboo bug */
  }
  
  ul.item-comments li img {
    float:left;
    margin-right:5px;
  }

  .clear {
    clear:both;
    height:1px;
    overflow:hidden;
  }
  
  #item-comment {}

</style>


<script type="text/javascript">
	$(document).ready(function(){
		$.ajax( {
			type :"post",
			url :"/public/publicItemComments.do",
			data: {method: 'view', itemId: '${item.id}'},
			dataType: 'json',
			success : function(json) {
				$.each(json.comments, function(i,comment){
					$('.item-comments').append('<sns:itemcomment enteredDate="'+comment.enteredDate+'" fullName="' + comment.fullName + '" icon="' + comment.commenterIcon + '" primaryKey="'+ comment.id +'" message="' + comment.message + '" />');		
				});		
			}
		});	
	});		 	

	function readComments(itemId){
		$($('#parent-element-name').val()).hide();
		 $.ajax({  
		       type: "post",  
		       url: "/public/publicItemComments.do",
		       data: {method: 'all', itemId: itemId},
		       success: function(data){ 
		    	   $('#item-comment-page').show();
		    	   $('#all-item-comments-area').html(data);
		       } 
		 });
	}
	
	function showHideComments(parentElement){
		$('#item-comment-page').hide();
		$(parentElement).show();
	}
	

	$(document).ready(function(){
		$('#comment-item-cancel').click(function(){
			//$('#item-comments-area').hide();
			Boxy.get('#item-comments-area').hide();
		});
	});

	function commentItem(recordId, itemName) {
		new Boxy('#item-comments-area', {title: 'Add Comment', fixed: false});
		$('#comment-item-id').attr('value', recordId);
	}

	function addComment() {
		new Boxy('#item-comments-area', {title: 'Add Comment', fixed: false});
		$('#comment-item-id').attr('value', recordId);
	}
</script>

		<ul class="item-comments">
		<c:forEach items="${comments}" var="comment">
			<sns:itemcomment enteredDate="${comment.enteredDate}" fullName="${comment.commenter.fullName}" icon="${comment.commenter.defaultFace.icon.path}" primaryKey="${comment.id}" message="${comment.message}" />	
		</c:forEach>
		</ul>
	
		<div id="item-comments-area" style="display:none;width:450px;">
			<form id="comment-form">
				<div>
				<input id="comment-item-id" type="hidden" name="itemId" />
				<label for="comments">Comment</label>
				</div>

				<div>
				<textarea rows="20" cols="50" id="item-comment" name="comment"  />
				</div>
				
				<div style="clear:both;">
				<button id="comment-item-button" type="submit">Submit</button>
				<button id="comment-item-cancel" type="reset">Cancel</button>
				</div>
			</form>
		</div>		
