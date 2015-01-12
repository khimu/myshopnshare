package com.myshopnshare.webapp.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.myshopnshare.core.domain.Address;
import com.myshopnshare.core.domain.CreditCard;
import com.myshopnshare.core.domain.EmailAddress;
import com.myshopnshare.core.domain.ItemOrderDetail;
import com.myshopnshare.core.domain.OrderDetail;
import com.myshopnshare.core.domain.Payment;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.Phone;
import com.myshopnshare.core.domain.VendorItem;
import com.myshopnshare.core.enums.AddressType;
import com.myshopnshare.core.enums.CreditCardType;
import com.myshopnshare.core.enums.EmailAddressType;
import com.myshopnshare.core.enums.OrderStatus;
import com.myshopnshare.core.enums.PhoneType;
import com.myshopnshare.core.enums.SubscriptionType;
import com.myshopnshare.core.service.AddressService;
import com.myshopnshare.core.service.CreditCardService;
import com.myshopnshare.core.service.EmailService;
import com.myshopnshare.core.service.OrderService;
import com.myshopnshare.core.service.PaymentService;
import com.myshopnshare.core.service.PhoneService;
import com.myshopnshare.core.service.VendorItemService;
import com.myshopnshare.utils.EmailUtil;

public class SubscribeController extends BaseController {
	private static transient final Log log = LogFactory
			.getLog(SubscribeController.class);
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private PhoneService phoneService;
	
	@Autowired
	private CreditCardService creditCardService;
	
	@Autowired
	private OrderService orderService;

	@Autowired
	private VendorItemService vendorItemService;

	private final static String BASIC = "8811881";
	private final static String PREMIUM = "8811882";
	private final static String CORP = "8811883";
	private final static String SLEEKSWAP = "sleekswap@gmail.com";

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//Person person = this.setup(request);

		setupHomePage(request);
		
		/*
		request.setAttribute("headline", person.getCurrentHeadline());
		request.setAttribute("person", person);

		request.setAttribute("addresses", person.getActiveAddresses());
		request.setAttribute("emails", person.getActiveEmailAddress());
		request.setAttribute("cards", person.getActiveCreditCards());
		request.setAttribute("phones", person.getActivePhones());
		*/

		request.setAttribute("emailTypes", EmailAddressType.values());
		request.setAttribute("addressTypes", AddressType.values());
		request.setAttribute("phoneTypes", PhoneType.values());
		request.setAttribute("cardTypes", CreditCardType.values());

		request.setAttribute("subscriptionTypes", SubscriptionType.values());
		return new ModelAndView("subscribe/subscribeLayout");
	}

	public ModelAndView show(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//Person person = this.setup(request);
		setupHomePage(request);

		/*
		request.setAttribute("headline", person.getCurrentHeadline());
		request.setAttribute("person", person);

		request.setAttribute("addresses", person.getActiveAddresses());
		request.setAttribute("emails", person.getActiveEmailAddress());
		request.setAttribute("cards", person.getActiveCreditCards());
		request.setAttribute("phones", person.getActivePhones());
		*/

		request.setAttribute("emailTypes", EmailAddressType.values());
		request.setAttribute("addressTypes", AddressType.values());
		request.setAttribute("phoneTypes", PhoneType.values());
		request.setAttribute("cardTypes", CreditCardType.values());
		request.setAttribute("subscriptionTypes", SubscriptionType.values());

		return new ModelAndView("subscribe/subscribePage");
	}

	public ModelAndView refreshContact(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		request.setAttribute("person", person);
		String phoneId = request.getParameter("phoneId");
		request
				.setAttribute("phone", phoneService
						.get(Long.parseLong(phoneId)));

		return new ModelAndView("account/phone/jsonPhone");
	}

	public ModelAndView refreshAddresses(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		request.setAttribute("person", person);
		String addressId = request.getParameter("addressId");
		request.setAttribute("address", addressService.get(Long
				.parseLong(addressId)));
		return new ModelAndView("account/address/jsonAddress");
	}

	public ModelAndView refreshCard(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		request.setAttribute("person", person);
		String cardId = request.getParameter("cardId");
		request.setAttribute("creditcard", creditCardService.get(Long
				.parseLong(cardId)));
		return new ModelAndView("account/creditcard/jsonCreditCard");
	}

	public ModelAndView refreshPaypal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		request.setAttribute("person", person);
		String paypalId = request.getParameter("paypalId");
		request.setAttribute("email", emailService
				.get(Long.parseLong(paypalId)));
		return new ModelAndView("account/email/jsonEmail");
	}

	public ModelAndView unsubscribe(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		person.setSubscribed(false);
		personService.update(person);

		return new ModelAndView("common/blank");
	}

	public ModelAndView subscribe(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// need a confirmation from paypal to confirm

		Person person = fetchPerson();
		person.setSubscribed(true);
		personService.update(person);

		return new ModelAndView("common/blank");
	}

	public ModelAndView confirm(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String type = request.getParameter("type");
		String billingId = request.getParameter("billingId");
		String shippingId = request.getParameter("shippingId");
		String contactId = request.getParameter("contactId");
		String paypalId = request.getParameter("paypalId");
		String cardId = request.getParameter("cardId");
		String subscriptionType = request.getParameter("subscriptionType");

		if (StringUtils.trimToNull(subscriptionType) == null || SubscriptionType.valueOf(subscriptionType) == SubscriptionType.DEFAULT) {
			request
					.setAttribute("errorMessage",
							"Please choose an appropriate subscription type.");
			return new ModelAndView("subscribe/subscribePage");
		}

		person.setSubscriptionType(SubscriptionType.valueOf(subscriptionType));

		if (StringUtils.trimToNull(paypalId) == null
				&& StringUtils.trimToNull(cardId) == null) {
			request
					.setAttribute("errorMessage",
							"Please provide either a credit card number or a paypal account.");
			return new ModelAndView("subscribe/subscribePage");
		}
		Payment payment = new Payment();
		payment.setPerson(person);
		if (StringUtils.trimToNull(billingId) != null) {
			Address address = new Address();
			address.setId(Long.parseLong(billingId));
			payment.setBillingAddress(address);
		}
		if (StringUtils.trimToNull(shippingId) != null) {
			Address address = new Address();
			address.setId(Long.parseLong(shippingId));
			payment.setShippingAddress(address);
		} else {
			Address address = new Address();
			address.setId(Long.parseLong(billingId));
			payment.setShippingAddress(address);
		}
		if (StringUtils.trimToNull(cardId) != null) {
			CreditCard card = new CreditCard();
			card.setId(Long.parseLong(cardId));
			payment.setCreditCards(card);
		}
		if (StringUtils.trimToNull(paypalId) != null) {
			EmailAddress email = new EmailAddress();
			email.setId(Long.parseLong(paypalId));
			payment.setPaypalAccount(email);
		}
		if (StringUtils.trimToNull(contactId) != null) {
			Phone phone = new Phone();
			phone.setId(Long.parseLong(contactId));
			payment.setContact(phone);
		}
		paymentService.save(payment);

		EmailAddress email = emailService.get(Long.parseLong(paypalId));

		Random random = new Random(System.currentTimeMillis());
		Integer invoice = random.nextInt();

		StringBuilder orderMessage = new StringBuilder();
		orderMessage.append(person.getFullName());
		orderMessage.append("\nInvoice:  "
				+ StringUtils.remove(invoice.toString(), "-"));
		orderMessage
				.append("\nItem Name \t Price \t Shipping \t Tax \t Quantity \t Total\n");

		StringBuilder itemOrderMessage = new StringBuilder();

		VendorItem item = vendorItemService.findSleekSwapItem(SubscriptionType.valueOf(subscriptionType).getItemName());

		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setPayment(payment);
		orderDetail.setPerson(personService.findByEmail("sleekswap@gmail.com"));

		log.debug(personService.findByEmail("sleekswap@gmail.com"));
		ItemOrderDetail iod = new ItemOrderDetail();
		iod.setInvoice(invoice.toString());
		iod.setItem(item);
		iod.setQuantity(1);
		iod.setStatus(OrderStatus.APPROVED);
		iod.setOrderDetail(orderDetail);
		iod.setPerson(person);
		orderDetail.getItemOrders().add(iod);

		String str = item.getItemName() + " \t " + item.getPrice() + " \t "
				+ item.getShipping() + " \t " + item.getTax() + " \t 1 \t "
				+ item.getPrice() + item.getShipping() + item.getTax() + " \n";

		itemOrderMessage.append(str);

		EmailUtil.INSTANCE.sendMail("New Orders",
				orderMessage.toString() + str, email.getEmail(),
				"sleekswap@gmail.com");

		orderService.save(orderDetail);

		EmailUtil.INSTANCE.sendMail("Your Order", orderMessage.toString()
				+ itemOrderMessage.toString(), "sleekswap@gmail.com", email
				.getEmail());

		// person.setSubscribed(true);
		personService.update(person);

		request.setAttribute("total", item.getPrice());

		return new ModelAndView("paypal/subscribe");
	}

	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	public void setPhoneService(PhoneService phoneService) {
		this.phoneService = phoneService;
	}

	public void setCreditCardService(CreditCardService creditCardService) {
		this.creditCardService = creditCardService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public void setVendorItemService(VendorItemService vendorItemService) {
		this.vendorItemService = vendorItemService;
	}

}
