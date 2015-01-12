<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">
	
</script>

<div id="col" class="noprint">
    <div id="col-in">

    <h3><span id="aboutmeopenclose"><a href="javascript:void(0);" onclick="showHideAboutMe();"><img src="/images/close.png" /></a></span>&nbsp;&nbsp;<span>Welcome ${person.fullName}</span></h3>

    <div id="about-me">
 		Viewed: ${person.viewed.count}
        <div>
        <c:forEach items="${person.activeFaces}" var="face">
        	<img src="../${face.mini.path}" />
        </c:forEach>
        </div>           
    </div> 

	<div style="text-align:center;">
			<div id="rating-id" class="rating">
				<img alt="poor rating" id="rating0" onclick="submitStars(0);  return false;" src="/images/gr_orange_star_active.gif" title="Poor Review" width="15" height="15" />
				<img alt="poor rating" id="rating1" onclick="submitStars(1);  return false;" src="/images/gr_orange_star_active.gif" title="Below Average" width="15" height="15" />
				<img alt="poor rating" id="rating2" onclick="submitStars(2);  return false;" src="/images/gr_orange_star_active.gif" title="Average" width="15" height="15" />
				<img alt="poor rating" id="rating3" onclick="submitStars(3);  return false;" src="/images/gr_orange_star_active.gif" title="Good" width="15" height="15" />
				<img alt="poor rating" id="rating4" onclick="submitStars(4);  return false;" src="/images/gr_orange_star_active.gif" title="Excellent" width="15" height="15" />			
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

	<h3>Employment</h3>
	<c:forEach items="${person.profile.employments}" var="employment">	
		<ul id="links">
			<li>
				<div style="font-weight: bold;">${employment.employerName} </div>
				<div style="font-weight: none; font-size:10px;"><fmt:formatDate value="${employment.startDate}" pattern="MM/dd/yyyy" /> - <fmt:formatDate value="${employment.endDate}" pattern="MM/dd/yyyy" /></div>		
				<div>${employment.title}</div>
				<div>${employment.division}/${employment.department}</div>
			</li>
		</ul>
	</c:forEach>

	<h3>Education</h3>
	<c:forEach items="${person.profile.educations}" var="education">
		<ul>
			<li>
				<div style="font-weight: bold;">${education.institutionType} ${education.institutionName}</div>
				<div>${education.degree}</div>
				<div>Graduation Year : ${education.endYear}</div>
				<div></div>
				<div>Start Year : ${education.startYear}</div>
				<div>${education.major}</div>
			</li>
		</ul>
	</c:forEach>
     
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

    <h3><span>Friends &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="/secure/manageFriends.do?method=all&personId=${person.id}">${fn:length(person.friends)}</a></span></h3>
    <%@ include file="../friends/friends.jsp" %>	
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




		

