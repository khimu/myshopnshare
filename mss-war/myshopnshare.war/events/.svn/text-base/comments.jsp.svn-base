<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

<script type="text/javascript">	
	function deleteComment(eventId, recordId) {
		$.ajax( {
			type :"POST",
			url :"/secure/eventsComments.do",
			data: {method: 'delete', eventId: eventId, commentId: recordId},
			success : function(data) {
				$('#events-comments-' + recordId + recordId).remove();
			}
		});	
	}
	
	$(document).ready(function(){
		$('#comment-event-button').click(function(){
			jQuery('#event-comment-form').validate( {
				submitHandler : function(form) {
					jQuery(form).ajaxSubmit( {
						url: '/secure/eventsComments.do', 
						type: 'post',
						data: {method: 'add'},
						dataType: 'json',
						success: function(comment) { 
							$('.item-comments').append('<sns:eventscomments enteredDate="'+comment.enteredDate+'" fullName="' + comment.fullName + '" icon="' + comment.commenterIcon + '" primaryKey="'+ comment.id +'" message="' + comment.message + '" />');
							form.clearForm();
							Boxy.get('#event-comments-area').hide();
						}
					});
				}
			});			
		});
		
		$('#comment-event-cancel').click(function(){
			Boxy.get('#event-comments-area').hide();
		});
	});

	function commentEvent(recordId, itemName) {
		new Boxy('#event-comments-area', {title: 'Add Comment', modal: true, fixed: false});
	}
</script>

		<img src="/images/comment_edit.png" width="16px" height="16px;" title="Add A Comment" /> Comments <a href="javascript: void(0);" onclick="commentEvent();"><img src="/images/buttons/addred.png" /></a>
		<div style="height:1%;"></div>

		<ul class="item-comments">
		<c:forEach items="${event.comments}" var="comment">
			<sns:itemcomment enteredDate="${comment.enteredDate}" fullName="${comment.person.fullName}" icon="${comment.person.defaultFace.icon.path}" primaryKey="${comment.id}" message="${comment.message}" />	
		</c:forEach>
		</ul>
	
		<div id="event-comments-area" style="display:none;width:450px;">
			<form id="event-comment-form" onsubmit="return false;">
				<div>
				<input type="hidden" name="eventId" value="${event.id}" />
				<label for="comments">Comment</label>
				</div>

				<div>
				<textarea rows="5" cols="50" name="comment"  />
				</div>
				
				<div style="clear:both;">
				<button id="comment-event-button" type="submit">Submit</button>
				<button id="comment-event-cancel" type="reset">Cancel</button>
				</div>
			</form>
		</div>		
