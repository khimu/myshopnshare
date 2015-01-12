<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

  <script type="text/javascript"> 	
	$(document).ready(function() { 
		$('#submit-order-detail-update').click(function(){
			 $.ajax({  
			       type: "post",  
			       url: "/secure/orders.do?method=update&"+$('#order-detail-form').serialize(),
				   dataType: 'json',
			       success: function(json){ 
						Boxy.alert("Your order detail has been updated.");
						Boxy.get('#show-order-detail-update-form').hide();
			       }
			 });			
		});
	});
  </script>

<div id="order-detail-page" style="margin-left: 30px;">
		<form id="order-detail-form">
			<input id="item-order-detail-id" type="hidden" name="itemOrderDetailId" value="CHANGE ME" />
			<div><label for="status">Status: </label></div>
			<div><select id="status" name="status">
					<option value="" selected="selected"></option>
					<c:forEach items="${statuses}" var="stat">
					<option value="${stat}">${stat.label}</option>
					</c:forEach>
				</select>
			</div>
			<div style="clear:both;"><label for="trackingNumber">Tracking Number</label></div>
			<div style="clear:both;"><input type="text" id="trackingNumber" name="trackingNumber"></div>
			<div style="clear:both;"><button name="button" id="submit-order-detail-update">Submit</button></div>
		</form>
</div>