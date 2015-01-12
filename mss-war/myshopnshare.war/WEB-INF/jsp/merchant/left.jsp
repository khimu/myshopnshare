<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">
	function showHideAboutMe(){
		if($('#about-me').is(':visible')){
			$('#about-me').hide();
			$('#aboutmeopenclose img').attr('src', '/images/close.png');
		}else{
			$('#about-me').show();
			$('#aboutmeopenclose img').attr('src', '/images/open.png');
		}
	}	
</script>

<div id="col" class="noprint">
    <div id="col-in">

    <h3><span>${person.fullName}</span></h3>

    <div id="about-me">
 		Viewed: ${person.viewed.count}
        <div>
        <c:forEach items="${person.activeFaces}" var="face">
        	<img src="../${face.mini.path}" />
        </c:forEach>
        </div>           
    </div>      

    <hr class="noscreen" />

	<%@ include file="../common/contacts.jsp" %>
    <hr class="noscreen" />
    <ul id="links">
    	<li><a href="javascript: void(0);" onclick="showContactInfo();" title="Contact info for ${person.fullName}">Show Contact Info</a></li>
    </ul>
    <hr class="noscreen" />

    <h3><span>Expertise</span></h3>

    <ul id="links">
        <li><p> ${person.profile.expertise}</p></li>
    </ul>

    <hr class="noscreen" />

    <h3><span>Language</span></h3>

    <ul id="links">
        <li><p> ${person.profile.language}</p></li>
    </ul>

	<hr class="noscreen" />
 
    <h3><span>Activities</span></h3>
    <ul id="links">
        <li><p> ${person.profile.activities}</p></li>
    </ul>

	<hr class="noscreen" />

    <h3><span>About</span></h3>
    <ul id="links">
        <li><p> ${person.profile.about}</p></li>
    </ul>
   
    </div> 
        
    <div>
		<script type="text/javascript"><!--
		google_ad_client = "pub-2178827339426285";
		/* 200x200, created 5/18/09 */
		google_ad_slot = "8175680661";
		google_ad_width = 200;
		google_ad_height = 200;
		//-->
		</script>
		<script type="text/javascript"
		src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
		</script>    
    </div>

</div> 




		

