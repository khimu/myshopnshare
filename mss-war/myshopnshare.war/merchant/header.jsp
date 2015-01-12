<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<script type="text/javascript">
 
 	function showFullFace(title){
 		new Boxy('#face-overlay', {title: title, modal: true});
	}
</script>


    <%-- Header --%>
    <div id="header" style="height: 120px;margin: 0 7px;">
    	<div style="padding: 5px;">
			<img src="/images/myshopnsharelogo2.png" />
		</div>

		<div style="position:absolute;top:5px;right:340px;">
			Age: ${person.age}<br />
			<c:if test="${not empty person.primaryAddress}">
				${person.primaryAddress.country}<br />
			</c:if>
			AIM: <c:if test="${not empty person.profile.aim}"><a href="aim:GoIM?screenname=screenname"><img src="http://api.oscar.aol.com/presence/icon?k=mi15Xbwp687KXwRc&t=${person.profile.aim}"/>${person.profile.aim}</a></c:if><br />
			Birthday: <fmt:formatDate value="${person.birthday}" pattern="MM/dd" />	
		</div>
    
		<div style="position:absolute;top:5px;right:350px;">		
			<div style="height:70px;"><a href="#" onclick="showFullFace('${person.alias}')"><img src="../${person.defaultFace.icon.path}" alt="${person.alias}" /></a></div>		
			
			<div class="clear"></div>
			
			<label for="lastloggedin">Last Logged In:</label> 
			<fmt:formatDate value="${person.bank.lastLoggedIn}" pattern="MM/dd/yyyy" />		
			<div id="friend-profile"  class="header-element-line">
				<%@ include file="../profile/email.jsp" %>
			</div>	
		</div>

		<div style="display:none;" id="face-overlay">  
			<img src="../${person.defaultFace.glimpse.path}" title="${person.alias}" alt="${person.alias}" />
		</div>

    </div> <%-- /header --%>