# ShoppingCartController

```java
package com.myshopnshare.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.CartItem;
import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.PointItem;
import com.myshopnshare.core.domain.RewardCart;
import com.myshopnshare.core.domain.VendorItem;
import com.myshopnshare.core.enums.CartStatus;
import com.myshopnshare.core.enums.ItemType;
import com.myshopnshare.core.service.CartItemService;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.core.service.PointItemService;
import com.myshopnshare.core.service.ShoppingCartService;
import com.myshopnshare.core.service.VendorItemService;
import com.myshopnshare.model.User;

public class ShoppingCartController extends MultiActionController {
	private static transient final Log log = LogFactory
			.getLog(EmailController.class);

	@Autowired
	private PersonService personService;
	@Autowired
	private VendorItemService vendorItemService;
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private PointItemService pointItemService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;

	private Person fetchPerson() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User user = (User) principal;

		return personService.findPersonByUsername(user.getUsername());
	}

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		request.setAttribute("shoppingCart", person.getShoppingCart());
		request.setAttribute("cartItemSize", cartItemService.getShoppingCartSize(person));
		request.setAttribute("cartItems", cartItemService.getCartItems(person));
		request.setAttribute("rewardsItems", shoppingCartService.getRewardCartItems(person));
		request.setAttribute("cartItemsGrandTotal", cartItemService.getGrandTotal(person));
		request.setAttribute("rewardsItemsGrandTotal", shoppingCartService.getRewardGrandTotal(person));
		return new ModelAndView("cart/cartPage");
	}

	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();

		request.setAttribute("shoppingCart", person.getShoppingCart());
		request.setAttribute("cartItemSize", cartItemService.getShoppingCartSize(person));
		request.setAttribute("cartItems", cartItemService.getCartItems(person));
		//request.setAttribute("rewardsItems", shoppingCartService.getRewardCartItems(person));
		request.setAttribute("cartItemsGrandTotal", cartItemService.getGrandTotal(person));
		//request.setAttribute("rewardsItemsGrandTotal", shoppingCartService.getRewardGrandTotal(person));
		return new ModelAndView("cart/jsonCart");
	}

	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			Person person = fetchPerson();
			String type = request.getParameter("type");
			String itemId = request.getParameter("itemId");
			String quantity = request.getParameter("quantity");

			if (StringUtils.trimToNull(itemId) != null) {
				Item item = null;
				if (StringUtils.trimToNull(type) != null
						&& ItemType.valueOf(type) == ItemType.POINT) {
					item = pointItemService.get(Long.parseLong(itemId));

					if (((PointItem) item).getQuantity() == 0) {
						// This is also checked in jsp
						request.setAttribute("actionMessage", "Out of stock");
						return new ModelAndView("vendorItem/jsonCart");
					}
					if (StringUtils.trimToNull(quantity) != null) {
						int determinedQuantity = person.getShoppingCart().getRewardRemainingQuantityFor(Integer.parseInt(quantity), ((PointItem) item).getQuantity(), item);

						for (int i = 0; i < determinedQuantity; i++) {
							if ((person.getShoppingCart().getRewardPriceTotalFor(item) + ((PointItem) item).getPrice()) < person.getRewardForVendor(item.getPerson())) {
								person.getShoppingCart().addItemToCart((PointItem) item);
							}
						}
					} else {
						request.setAttribute("actionMessage", "Out of stock");
					}
				} else {
					item = vendorItemService.get(Long.parseLong(itemId));
					if (((VendorItem) item).getQuantity() == 0) {
						// This is also checked in jsp
						request.setAttribute("actionMessage", "Out of stock");
						return new ModelAndView("vendorItem/jsonCart");
					}
					if (StringUtils.trimToNull(quantity) != null) {
						int determinedQuantity = person.getShoppingCart().getRemainingQuantityFor(Integer.parseInt(quantity), ((VendorItem) item).getQuantity(), item);
						
						for (int i = 0; i < determinedQuantity; i++) {
							person.getShoppingCart().addItemToCart((VendorItem) item);
						}
					} else {
						request.setAttribute("actionMessage", "Out of stock");
					}
				}

				request.setAttribute("actionMessage", "You have added "
						+ item.getItemName() + " to your shopping cart.");

				personService.update(person);
				request.setAttribute("shoppingCart", person.getShoppingCart());
				return new ModelAndView("vendorItem/jsonCart");
			}
			request.setAttribute("actionMessage", "There is no item to add.");
			return new ModelAndView("vendorItem/jsonCart");
		} catch (Exception e) {
			log.equals(e.getMessage());
			request
					.setAttribute("actionMessage",
							"The system is down at the moment.  Please try again in a few 5 minutes.");
			return new ModelAndView("vendorItem/jsonCart");
		}
	}

	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String cartItemId = request.getParameter("cartItemId");
		try {
			if (StringUtils.trimToNull(cartItemId) != null) {
				CartItem cartItem = person.getShoppingCart().findCartItem(
						Long.parseLong(cartItemId));
				if (cartItem != null) {
					person.getShoppingCart().getCartItems().remove(cartItem);
					cartItem.setShoppingCart(null);
					cartItem.setStatus(CartStatus.REMOVED);
					// cartItemService.delete(cartItem);
				} else {
					RewardCart rewardItem = person.getShoppingCart()
							.findRewardItem(Long.parseLong(cartItemId));
					if (rewardItem != null) {
						person.getShoppingCart().getRewardsItems().remove(
								rewardItem);
						rewardItem.setShoppingCart(null);
						rewardItem.setStatus(CartStatus.REMOVED);
					}
					// genericService.delete(rewardItem);
				}
				personService.update(person);
			}
			request.setAttribute("shoppingCart", person.getShoppingCart());
			return new ModelAndView("vendorItem/jsonCart");
		} catch (Exception e) {
			log.equals(e.getMessage());
			return new ModelAndView("vendorItem/jsonCart");
		}
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void setVendorItemService(VendorItemService vendorItemService) {
		this.vendorItemService = vendorItemService;
	}

	public void setCartItemService(CartItemService cartItemService) {
		this.cartItemService = cartItemService;
	}

	public void setPointItemService(PointItemService pointItemService) {
		this.pointItemService = pointItemService;
	}

}
```
