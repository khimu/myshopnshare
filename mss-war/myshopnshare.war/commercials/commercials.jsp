<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%-- JAVASCRIPT conflicts with ui.tabs Cannot use --%>
<script type="text/javascript">	

	$(function(){ // wait for document to load 
		/*
		 $('#T7').MultiFile({ 
		  list: '#T7-list'
		 }); 
		*/
		 $('#T7').change(function(){
				var itemId = $('#commercial-item-id').val();
				$("#loading")
				.ajaxStart(function(){
					$(this).show();
				})
				.ajaxComplete(function(){
					$(this).hide();
				});

				$.ajaxFileUpload({
						url:'/secure/commercials.do',
						data: {method: 'bulk', itemId: itemId},
						secureuri:false,
						fileElementId:'T7',
						success: function (commercial, status){
							Boxy.get('#item-commercials-form').hide();			
						},
						error: function (data, status, e){
							Boxy.get('#item-commercials-form').hide();
						}
					});
				
				return false;		 
		});
	});
	</script>

<div id="item-commercials-form" style="display:none;">
    <form action="" class="P10">
    	<input type="hidden" name="itemId" id="commercial-item-id" />
     	<input type="file" id="T7"/>
    </form>
   	<div id="T7-list" style="border:#999 solid 3px; padding:10px;">
   		Completed:
    	<br/><br/>
   	</div>
   	<img id="loading" src="/images/loading.gif" style="display:none;"/>
</div>