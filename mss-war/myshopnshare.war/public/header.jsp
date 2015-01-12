<%@ include file="/WEB-INF/jsp/includes.jsp"%>

    <%-- Header --%>
    <div id="header" style="height: 120px;margin: 0 7px; background-color:#f79711;">
    	<div style="padding: 5px;">
			<img src="/images/myshopnsharelogo2.png" />
		</div>
        <%-- Logotyp --%>
        <hr class="noscreen" />              

        <%-- Search --%>
        <div id="design-search" class="noprint">
         <p>Looking for something special:</p>
            <form onsubmit="publicSearch(); return false;">
               
                    <span id="design-search-input-out"><input type="text" name="searchString" id="design-search-input" /></span>
                    <input type="image" src="/images/design/search_submit.gif" id="design-search-submit" value="OK" />
                </fieldset>
            </form>
        </div> <%-- /search --%>

			<div style="position:absolute;top:100px;right:15px;">
					You are visitor #
					<big id="counter">${counter.count}</big>
			</div>	

    </div> <%-- /header --%>
