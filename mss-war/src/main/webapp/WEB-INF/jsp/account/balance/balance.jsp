<%@ include file="/WEB-INF/jsp/includes.jsp"%>

<div><h3>Balance: $${person.unpaidBalance}</h3><big><a href="#">Pay</a></big></div>

<c:forEach items="${person.balances}" var="balance">
<div><fmt:formatDate value="${balance.startDate}" pattern="MM/dd/yyyy" /> - <fmt:formatDate value="${balance.endDate}" pattern="MM/dd/yyyy" /> </div>
	<sns:report tableName="balanceTable" columns="Serial Number|Item Name|Amount">
		<tbody id="balance-replace-me"> 
			<c:forEach items="${balance.limeItems}" var="lineItem">
				<sns:reportrecordreadonly records="${lineItem.item.serialNumber}|${lineItem.item.itemName}|$${lineItem.amount}" primaryKey="${lineItem.id}" />
			</c:forEach>
		</tbody>
	</sns:report>
<div><h3>Total: $${balance.total}</h3></div>
</c:forEach>