<%@ include file="/WEB-INF/jsp/includes.jsp"%>

    <json:object>
	 <json:property name="invoice" value="${itemOrderDetail.invoice}"/>
	 <json:property name="orderId" value="${itemOrderDetail.orderDetail.id}"/>
	 <json:property name="status" value="${itemOrderDetail.status.label}"/>
	 <json:property name="trackingNumber" value="${itemOrderDetail.trackingNumber}"/>
	 <json:property name="itemName" value="${itemOrderDetail.item.itemName}"/>
	 <json:property name="price" value="${itemOrderDetail.priceTotal}"/>
	 <json:property name="shipping" value="${itemOrderDetail.shippingTotal}"/>
	 <json:property name="tax" value="${itemOrderDetail.taxTotal}"/>
	 <json:property name="quantity" value="${itemOrderDetail.quantity}"/>
	 <json:property name="total" value="${itemOrderDetail.total}"/>
	 <json:property name="id" value="${itemOrderDetail.id}"/>
    </json:object>