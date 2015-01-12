<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="sns" %>

<script type="text/javascript">

	var escapeHTML = function(description) {
		return description.replace(/&/g,'&amp;').replace(/</g,'&lt;').replace(/>/g,'&gt;');
	}
	
	var unescapeHTML = function(description) {
	 	return description.replace(/&amp;/g,'&').replace(/&lt;/g,'<').replace(/&gt;/g,'>');
	}
	
	function getAds(method) {
		$.ajax( {
			type :"POST",
			url :"/secure/ads.do",
			data : {method: 'get'},
			dataType :'json',
			success : function(json) {
				$.each(json.adss, function(i, ads) {
					$('#ads-box'+' .the-ads').append('<sns:ads sponsorSite="' + ads.sponsorSite + '" adsId="' + ads.id + '" description="' + unescapeHTML(ads.description) + '" thumbnail="' + ads.thumbnail + '" />');
				});
				
				$('.the-ads').cycle({ 
				    fx:     'fade', 
				    timeout: 5000, 
				    pause:   1,
				    before:  onBeforeADS,
				    after:   onAfterADS
				 }); 			
			}
		});
	}
	
	function adsRedirect(recordId){
		$.ajax( {
			type :"POST",
			url :"/secure/ads.do",
			data : {method: 'click', recordId: recordId},
			dataType :'text',
			success : function(data) {	
				$('#ads-box').html(data);	
			}
		});
	}

	$(document).ready(function(){
		getAds('');	
	});
	
	function onBeforeADS() { 
	     $('#ads-box SPAN').html(this.title);
	} 
	
	function onAfterADS() { 
		$('#ads-box SPAN').html(this.title);
	}

</script>

<style type="text/css">
	.the-ads {
	}
	#advertisement-frame {		
		height: 200px;
		margin-bottom: 10px;
	}
		
	#ads-box {
		height: 200px;
	}
</style>

<h3><span>ADVERTISEMENT</span></h3>

<div class="column-content">	
	<div id="advertisement-frame">
		<div id="ads-box" style="position: relative;text-align: center;">
			<div class="the-ads" style="float: left;width: 150px;height:150px;text-align;center;">
			</div>
			<span style="float: none;clear: both;text-align: left;vertical-align: bottom;"></span>
		</div>
	</div>
</div>	
	