# PaymentController

```java
package com.myshopnshare.webapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import com.myshopnshare.model.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.Action;
import com.myshopnshare.core.domain.Address;
import com.myshopnshare.core.domain.CartItem;
import com.myshopnshare.core.domain.CreditCard;
import com.myshopnshare.core.domain.EmailAddress;
import com.myshopnshare.core.domain.ItemCategory;
import com.myshopnshare.core.domain.ItemOrderDetail;
import com.myshopnshare.core.domain.LineItemDetail;
import com.myshopnshare.core.domain.News;
import com.myshopnshare.core.domain.OrderDetail;
import com.myshopnshare.core.domain.Payment;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.Phone;
import com.myshopnshare.core.domain.PointItemOrderDetail;
import com.myshopnshare.core.domain.RewardCart;
import com.myshopnshare.core.enums.AddressType;
import com.myshopnshare.core.enums.CartStatus;
import com.myshopnshare.core.enums.CategoryType;
import com.myshopnshare.core.enums.CreditCardType;
import com.myshopnshare.core.enums.EmailAddressType;
import com.myshopnshare.core.enums.OrderStatus;
import com.myshopnshare.core.enums.PhoneType;
import com.myshopnshare.core.service.AddressService;
import com.myshopnshare.core.service.CartItemService;
import com.myshopnshare.core.service.CreditCardService;
import com.myshopnshare.core.service.EmailService;
import com.myshopnshare.core.service.ItemVisibilityDomainService;
import com.myshopnshare.core.service.NewsService;
import com.myshopnshare.core.service.NewsVisibilityDomainService;
import com.myshopnshare.core.service.OrderService;
import com.myshopnshare.core.service.PaymentService;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.core.service.PhoneService;
import com.myshopnshare.core.service.VendorItemService;
import com.myshopnshare.utils.EmailUtil;

/*
 * Collect the payment when about to charge user.
 */
public class PaymentController extends MultiActionController {
	private static transient final Log log = LogFactory
			.getLog(PaymentController.class);

	@Autowired
	private PersonService personService;
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
	private CartItemService cartItemService;
	@Autowired
	private OrderService orderService;

	private VendorItemService vendorItemService;
	private NewsService newsService;
	private NewsVisibilityDomainService newsVisibilityService;
	private ItemVisibilityDomainService itemVisibilityService;

	private Person fetchPerson() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User user = (User) principal;

		return personService.findPersonByUsername(user.getUsername());
	}

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		request.setAttribute("person", person);

		request.setAttribute("addresses", person.getActiveAddresses());
		request.setAttribute("emails", person.getActiveEmailAddress());
		request.setAttribute("cards", person.getActiveCreditCards());
		request.setAttribute("phones", person.getActivePhones());

		request.setAttribute("emailTypes", EmailAddressType.values());
		request.setAttribute("addressTypes", AddressType.values());
		request.setAttribute("phoneTypes", PhoneType.values());
		request.setAttribute("cardTypes", CreditCardType.values());
		return new ModelAndView("payment/paymentPage");
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

	public ModelAndView complete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();

		// if paypal, must wait for confirmation of money transfer before
		// funding merchant

		// Fund merchants

		// Done via java api and soap calls

		return new ModelAndView("home/homeLayout");
	}

	public ModelAndView checkout(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String billingId = request.getParameter("billingId");
		String shippingId = request.getParameter("shippingId");
		String contactId = request.getParameter("contactId");
		String paypalId = request.getParameter("paypalId");
		String cardId = request.getParameter("cardId");
		if (StringUtils.trimToNull(paypalId) == null
				&& StringUtils.trimToNull(cardId) == null) {
			request
					.setAttribute("errorMessage",
							"Please provide either a credit card number or a paypal account.");
			return new ModelAndView("payment/paymentPage");
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
		orderMessage.append("\n\nInvoice:  "
				+ StringUtils.remove(invoice.toString(), "-"));
		orderMessage
				.append("\n\n" +
						"Item Name \t Price \t Shipping \t Tax \t Quantity \t Total\n");

		StringBuilder itemOrderMessage = new StringBuilder();
		StringBuilder rewardItemOrderMessage = new StringBuilder();

		// Charge users here - perform this process outside of application for
		// now.
		
		float total = person.getShoppingCart().getGrandTotal()
				+ person.getShoppingCart().getRewardGrandTotal();
		
		
		cartItemOrders(person, itemOrderMessage, payment, invoice,
				orderMessage, email);

		rewardItemOrders(person, rewardItemOrderMessage, payment, invoice,
				orderMessage, email);

		orderMessage.append(itemOrderMessage.toString());
		orderMessage.append("\n\nRewards Items");
		orderMessage.append("\n\n" +
				"Item Name \t Price \t Shipping \t Tax \t Quantity \t Total\n");
		orderMessage.append(rewardItemOrderMessage.toString());

		// cartItemService.batchDelete(cartItemIds);
		EmailUtil.INSTANCE.sendMail("Your Order", orderMessage.toString()
				+ itemOrderMessage.toString(), "sleekswap@gmail.com", email
				.getEmail());

		// create batch delete here otherwise orphan children
		personService.update(person);

		request.setAttribute("total", total);
		request.setAttribute("invoice", invoice.toString());

		if (StringUtils.trimToNull(cardId) != null) {
			return new ModelAndView("authorize/buy");
		} else {
			return new ModelAndView("paypal/buy");
		}
	}

	public void cartItemOrders(Person person, StringBuilder itemOrderMessage,
			Payment payment, Integer invoice, StringBuilder orderMessage,
			EmailAddress email) throws MessagingException {
		Map<Long, OrderDetail> orders = new HashMap<Long, OrderDetail>();

		for (CartItem c : person.getShoppingCart().getCartItems()) {
			OrderDetail orderDetail = orders.get(c.getItem().getPerson()
					.getId());
			if (orderDetail == null) {
				orderDetail = new OrderDetail();
				orderDetail.setPayment(payment);
				// This is the merchant person
				orderDetail.setPerson(c.getItem().getPerson());
				orderDetail.setBuyer(person);
			}

			// Log processing of the item in the order
			ItemOrderDetail iod = new ItemOrderDetail();
			iod.setPerson(person);
			iod.setInvoice(invoice.toString());
			iod.setItem(c.getItem());
			iod.setQuantity(c.getQuantity());
			iod.setStatus(OrderStatus.PENDING_APPROVAL);
			iod.setOrderDetail(orderDetail);
			orderDetail.getItemOrders().add(iod);

			orders.put(c.getItem().getPerson().getId(), orderDetail);

			person.getBank().updateBalance(c.getItem().getPrice().intValue());
			CartItem ic = person.getShoppingCart().findCartItem(c.getId());
			ic.setShoppingCart(null);
			ic.setStatus(CartStatus.BOUGHT);
			
			// update the merchant's balance info to be billed to merchants separately
			orderDetail.getPerson().getCurrentBalance().getLineItems().add(new LineItemDetail(c.getItem(), (double)c.getTotal()));
			
			// TODO: move c.total from person to merchant
			// this is where authorize.net will go

			String str = c.getItem().getItemName() + " \t "
					+ c.getItem().getPrice() + " \t "
					+ c.getItem().getShipping() + " \t " + c.getItem().getTax()
					+ " \t " + c.getQuantity() + " \t " + c.getTotal() + " \n";

			itemOrderMessage.append(str);

			EmailUtil.INSTANCE.sendMail("New Orders", orderMessage.toString()
					+ str, emailService.getPrimaryEmailForPerson(c.getItem().getPerson())
					.getEmail(), email.getEmail());

			ItemCategory itemCategory = new ItemCategory();
			itemCategory.setCategory(CategoryType.BOUGHT);
			itemCategory.setDescription(person.getFirstName() + " bought "
					+ c.getItem());
			itemCategory.setPerson(person);
			itemCategory.setItem(c.getItem());

			c.getItem().getItemCategories().add(itemCategory);
			if (!c.getItem().getIsService()) {
				c.getItem().decrementQuantity();
			}

			person.getItemCategories().add(itemCategory);

			person.getItemVisibility(c.getItem(), CategoryType.BOUGHT
					.toString());

			News itemnews = new News();
			itemnews.setAction(Action.BOUGHT);
			itemnews.setWorld(Action.MEMBER.isWorld());
			itemnews.setPerson(person);
			itemnews.setMessage(Action.BOUGHT.convert(person, c.getItem()));
			person.getNewsPermission(itemnews);
			person.getNews().add(itemnews);
		}

		person.getShoppingCart().setCartItems(new ArrayList<CartItem>());

		for (OrderDetail orderDetail : orders.values()) {
			person.getBuyOrders().add(orderDetail);
			//orderService.saveOrUpdate(orderDetail);
			// pay merchants here this way we know there is a record of the item
			// in the order if
			// transaction goes wrong and does not get executed. If the save
			// fails no charge happens.
		}

	}

	public void rewardItemOrders(Person person, StringBuilder itemOrderMessage,
			Payment payment, Integer invoice, StringBuilder orderMessage,
			EmailAddress email) throws MessagingException {
		Map<Long, OrderDetail> orders = new HashMap<Long, OrderDetail>();

		/*
		 * List<CartItem> copiedList = Collections.unmodifiableList(person
		 * .getShoppingCart().getCartItems());
		 */
		for (RewardCart c : person.getShoppingCart().getRewardsItems()) {
			OrderDetail orderDetail = orders.get(c.getItem().getPerson()
					.getId());
			if (orderDetail == null) {
				orderDetail = new OrderDetail();
				orderDetail.setPayment(payment);
				orderDetail.setPerson(c.getItem().getPerson());
				orderDetail.setBuyer(person);
			}

			PointItemOrderDetail iod = new PointItemOrderDetail();
			iod.setPerson(person);
			iod.setInvoice(invoice.toString());
			iod.setItem(c.getItem());
			iod.setQuantity(c.getQuantity());
			iod.setStatus(OrderStatus.PENDING_APPROVAL);
			iod.setOrderDetail(orderDetail);
			orderDetail.getPointItemOrders().add(iod);

			orders.put(c.getItem().getPerson().getId(), orderDetail);

			person.getBank().updateBalance(
					0 - c.getItem().getPrice().intValue());
			RewardCart ic = person.getShoppingCart().findRewardItem(c.getId());
			ic.setShoppingCart(null);
			ic.setStatus(CartStatus.BOUGHT);
			// person.getShoppingCart().getRewardsItems().remove(c);

			String str = c.getItem().getItemName() + " \t "
					+ c.getItem().getPrice() + " \t "
					+ c.getItem().getShipping() + " \t " + c.getItem().getTax()
					+ " \t " + c.getQuantity() + " \t " + c.getTotal() + " \n";

			itemOrderMessage.append(str);

			EmailUtil.INSTANCE.sendMail("New Rewards Orders", orderMessage.toString()
					+ str, emailService.getPrimaryEmailForPerson(c.getItem().getPerson())
					.getEmail(), email.getEmail());

			ItemCategory itemCategory = new ItemCategory();
			itemCategory.setCategory(CategoryType.BOUGHT);
			itemCategory.setPerson(person);
			itemCategory.setItem(c.getItem());

			c.getItem().getItemCategories().add(itemCategory);
			if (!c.getItem().getIsService()) {
				c.getItem().decrementQuantity();
			}

			person.getItemCategories().add(itemCategory);

			person.getItemVisibility(c.getItem(), CategoryType.BOUGHT
					.toString());

			// TODO CHECK IF quantity is decremented
			// pointItemService.update(c.getItem());

			News itemnews = new News();
			itemnews.setAction(Action.BOUGHT);
			itemnews.setWorld(Action.BOUGHT.isWorld());
			itemnews.setPerson(person);
			itemnews.setMessage(Action.BOUGHT.convert(person, c.getItem()));
			person.getNewsPermission(itemnews);
			person.getNews().add(itemnews);
		}

		person.getShoppingCart().setRewardsItems(new ArrayList<RewardCart>());

		for (OrderDetail orderDetail : orders.values()) {
			person.getBuyOrders().add(orderDetail);
			//orderService.saveOrUpdate(orderDetail);
		}

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

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void setCartItemService(CartItemService cartItemService) {
		this.cartItemService = cartItemService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public void setVendorItemService(VendorItemService vendorItemService) {
		this.vendorItemService = vendorItemService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public void setNewsVisibilityService(
			NewsVisibilityDomainService newsVisibilityService) {
		this.newsVisibilityService = newsVisibilityService;
	}

	public void setItemVisibilityService(
			ItemVisibilityDomainService itemVisibilityService) {
		this.itemVisibilityService = itemVisibilityService;
	}

} // SampleApp
```
