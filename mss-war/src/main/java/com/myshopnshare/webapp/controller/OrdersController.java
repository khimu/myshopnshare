package com.myshopnshare.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import com.myshopnshare.model.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.ItemOrderDetail;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.OrderStatus;
import com.myshopnshare.core.service.EmailService;
import com.myshopnshare.core.service.ItemOrderDetailService;
import com.myshopnshare.core.service.OrderService;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.utils.EmailUtil;

public class OrdersController extends MultiActionController {
	@Autowired
	private EmailService emailService;
	@Autowired
	private PersonService personService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ItemOrderDetailService itemOrderDetailService;

	private Person fetchPerson() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User user = (User) principal;

		return personService.findPersonByUsername(user.getUsername());
	}

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		request.setAttribute("orders", person.getOrders());
		request.setAttribute("statuses", OrderStatus.values());
		return new ModelAndView("orders/orders");
	}

	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String itemOrderDetailId = request.getParameter("itemOrderDetailId");

		// Show payment for order
		ItemOrderDetail order = itemOrderDetailService.get(Long
				.parseLong(itemOrderDetailId));

		request.setAttribute("order", order);
		request.setAttribute("statuses", OrderStatus.values());
		return new ModelAndView("orders/order");
	}

	public ModelAndView update(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String trackingNumber = request.getParameter("trackingNumber");
		String itemOrderDetailId = request.getParameter("itemOrderDetailId");
		String status = request.getParameter("status");

		ItemOrderDetail itemOrderDetail = itemOrderDetailService.get(Long
				.parseLong(itemOrderDetailId));
		itemOrderDetail.setTrackingNumber(trackingNumber);
		itemOrderDetail.setStatus(OrderStatus.valueOf(status));
		itemOrderDetailService.update(itemOrderDetail);

		EmailUtil.INSTANCE.sendMail("Your order has been shipped", "Invoice: "
				+ itemOrderDetail.getInvoice() + "\nTracking Number: "
				+ trackingNumber, emailService.getPrimaryEmailForPerson(itemOrderDetail.getPerson())
				.getEmail(), emailService.getPrimaryEmailForPerson(person).getEmail());

		request.setAttribute("itemOrderDetail", itemOrderDetail);

		return new ModelAndView("orders/jsonItemOrderDetail");
	}

	public ModelAndView pending(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		request.setAttribute("itemOrderDetails", itemOrderDetailService
				.findAllPendingOrderFor(person));
		return new ModelAndView("orders/jsonItemOrderDetails");
	}

	public ModelAndView approved(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		request.setAttribute("itemOrderDetails", itemOrderDetailService
				.findAllApprovedOrderFor(person));
		return new ModelAndView("orders/jsonItemOrderDetails");
	}

	public ModelAndView completed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		request.setAttribute("itemOrderDetails", itemOrderDetailService
				.findAllCompletedOrderFor(person));
		return new ModelAndView("orders/jsonItemOrderDetails");
	}

	public ModelAndView shipped(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		request.setAttribute("itemOrderDetails", itemOrderDetailService
				.findAllShippedOrderFor(person));
		return new ModelAndView("orders/jsonItemOrderDetails");
	}

	public ModelAndView returned(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		request.setAttribute("itemOrderDetails", itemOrderDetailService
				.findAllReturnedOrderFor(person));
		return new ModelAndView("orders/jsonItemOrderDetails");
	}

	public ModelAndView requestReturn(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		request.setAttribute("itemOrderDetails", itemOrderDetailService
				.findAllRequestReturnedOrderFor(person));
		return new ModelAndView("orders/jsonItemOrderDetails");
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void setItemOrderDetailService(
			ItemOrderDetailService itemOrderDetailService) {
		this.itemOrderDetailService = itemOrderDetailService;
	}

}
