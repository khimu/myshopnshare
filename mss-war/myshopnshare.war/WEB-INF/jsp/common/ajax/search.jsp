<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<link rel="stylesheet" type="text/css" media="screen" href="/js/validation/jquery-validate/css/screen.css" />	
<script src="/js/validation/jquery-validate/jquery.validate.js" type="text/javascript"></script>
<script src="/js/validation/jquery-validate/lib/jquery.form.js" type="text/javascript"></script>

<script type="text/javascript">
	jQuery( function() {
		var v = jQuery("#search").validate( {
			submitHandler : function(form) {
				jQuery(form).ajaxSubmit( {
					target : "#new-item-uploaded",
					dataType: 'json',
				 	success: function(json){
				 		alert(json.itemName);
						$j('#new-item-uploaded').html('');
						$j.each(json, function(i,item){
							$j('#new-item-uploaded').append('<sns:searchitemimagerecord itemName="'+ item.itemName +'" caption="'+ item.caption +'" description="'+ item.description +'" icon="'+ item.icon +'" original="'+ item.original +'" primaryKey="' + item.id + '" />');
						});
						$j('a[@rel*=lightbox]').lightBox({fixedNavigation:true});
					}				
				});
			}
		});
	});
</script>

	<div id="title_info">
		<p><i>Looking for something special:</i></p>
		<p>		
<form id="search" method="POST" action="/secure/ajax/search.do?method=all">

				<input id="search_text" class="search_inactive" type="text"
					value="Search Images" name="searchString" />
				<button id="search_submit" type="submit">SEARCH</button>
				<script type="text/javascript" src="/js/shopnshare/search.js"
					defer="defer"></script>
</form>
		</p>
	</div>
