<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<style type="text/css">

#ads-box DIV {
	text-align: right;
	padding-right: 5px;
	font-size: 1.0em;
}
		
#ads-box INPUT {
	padding-right: 5px;
	font-size: 1.0em;
	text-align: right;
}

#ads-box LABEL {
	font-weight: bold;
	color: gray;
}
</style>

<script type="text/javascript">
	jQuery(function($){
		$("#tags").Watermark("tag1, tag2, tag3");
		$("#sponsorSite").Watermark("http://www.domainname.com");
	});
	
	function ajaxAdsFileUpload()
	{
		$("#loading")
		.ajaxStart(function(){
			$(this).show();
		})
		.ajaxComplete(function(){
			$(this).hide();
		});

		$.ajaxFileUpload({
				url:'/secure/ads.do?method=upload&' + $('#ads-form').serialize(),
				secureuri:false,
				fileElementId:'adsFileToUpload',
				dataType: 'json',
				success: function (ads, status){
					
				Boxy.get('#ads-box').hide();
				
				if(typeof(ads.error) != 'undefined'){
					if(item.error != ''){
						Boxy.alert(' data.error '+ads.error);
					}else{
						Boxy.alert(' data.msg ' + ads.msg);
					}
				}
				$('#new-ads-uploaded').append('<sns:ads sponsorSite="' + ads.sponsorSite + '" adsId="' + ads.id + '" description="' + unescapeHTML(ads.description) + '" thumbnail="' + ads.thumbnail + '" />');
				$('#ads-form').clearForm();
			},
			error: function (data, status, e){
				$('#ads-form').clearForm();
				Boxy.get('#ads-box').hide();
			}
			});

		return false;
	}

</script>

	<div id="ads-box">		
		<form id="ads-form" name="ads-form" enctype="multipart/form-data">
		<table cellpadding="3">
			<tr>
				<td><label for="adimage">Ad Image</label></td>
				<td><input id="adsFileToUpload" type="file" size="45" name="fileToUpload" class="input"/></td>
			</tr>
			<%--
			<tr>
				<td><label for="commercial">Commercial</label></td>
				<td><input id="commercial" type="file" size="30" maxlength="50"  name="connercial" class="input" /></td>
			</tr>		
			--%>				
			<tr>
				<td><label for="sponsorSite">Sponsor Site</label></td>
				<td><input id="sponsorSite" name="sponsorSite" type="text" size="30" maxlength="50" /></td>
			</tr>
			<tr>
				<td><label for="companyname">Company Name</label></td>
				<td><input id="companyname" name="companyname" type="text" size="30" maxlength="50" /></td>
			</tr>						
			<tr>
				<td><label for="tags">Tags</label></td>
				<td><input id="tags" name="tags" type="text" size="30" maxlength="50" /></td>
			</tr>		
			<tr>
				<td colspan="2"><textarea id="description" name="description" cols="50" rows="10"></textarea></td>
			</tr>
			<tr>
				<td><button name="button" id="submit-upload-button" onclick="return ajaxAdsFileUpload();return false;">Add</button></td>
				<td><button name="button" id="reset-upload-button" type="reset">Reset</button></td>
			</tr>	
		</table>	
    	</form>
    	<img id="loading" src="/images/loading.gif" style="display:none;"/>
    </div>