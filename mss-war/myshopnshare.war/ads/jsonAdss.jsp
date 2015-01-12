<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<json:object escapeXml="false">
  <json:array name="adss" var="ads" items="${adss}">
    <json:object>
     <json:property name="glimpse" value="/${ads.glimpse.path}"/>
	 <json:property name="thumbnail" value="/${ads.thumbnail.path}"/>
	 <json:property name="icon" value="/${ads.icon.path}"/>
	 <json:property name="description" value="${ads.description}"/>
	 <json:property name="sponsorSite" value="${ads.sponsorSite}"/>
	 <json:property name="companyName" value="${ads.companyName}"/>
	 <json:property name="price" value="${ads.price}"/>
	 <json:property name="fileName" value="${ads.fileName}"/>
	 <json:property name="ageRange" value="${ads.ageRange}"/>
	 <json:property name="clicks" value="${ads.clicks}"/>
	 <json:property name="id" value="${ads.id}"/>
    </json:object>
  </json:array>
</json:object>