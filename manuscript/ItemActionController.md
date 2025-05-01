# ItemActionController

```java
package com.myshopnshare.webapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.myshopnshare.core.domain.Action;
import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.domain.ItemCategory;
import com.myshopnshare.core.domain.ItemRating;
import com.myshopnshare.core.domain.News;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.VendorItem;
import com.myshopnshare.core.enums.CategoryType;
import com.myshopnshare.core.service.EmailService;
import com.myshopnshare.core.service.ItemCategoryService;
import com.myshopnshare.core.service.ItemService;
import com.myshopnshare.core.service.ItemVisibilityDomainService;
import com.myshopnshare.core.service.NewsService;
import com.myshopnshare.core.service.NewsVisibilityDomainService;
import com.myshopnshare.core.service.VendorItemService;
import com.myshopnshare.utils.EmailUtil;

public class ItemActionController extends BaseController {
	private final static Logger log = Logger.getLogger(ItemActionController.class);

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ItemService itemService;
	@Autowired
	private VendorItemService vendorItemService;
	@Autowired
	private NewsVisibilityDomainService newsVisibilityService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private ItemCategoryService itemCategoryService;
	@Autowired
	private ItemVisibilityDomainService itemVisibilityService;

	/** View the single item **/
	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		fetchPerson();
		return new ModelAndView("home/home");
	}

	public ModelAndView rate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String itemId = request.getParameter("itemId");
		// do friend stuff and permission checking
		if (StringUtils.trimToNull(itemId) != null) {
				Item item = itemService.get(Long.parseLong(itemId));
				Integer rating = Integer.parseInt(request.getParameter("rating"));

				ItemRating r = item.getItemRating();
				r.recalculate(rating);
				r.setItem(item);
				item.setItemRating(r);
				
				itemService.update(item);
				request.setAttribute("rating", item.getItemRating().average());
		}
		
		return new ModelAndView("rating/jsonRating");
	}
	
	public ModelAndView flag(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map map = new HashMap();
		Person person = fetchPerson();
		
		String itemId = request.getParameter("faceId");
		if(StringUtils.trimToNull(itemId) != null){
			Item item = itemService.get(Long.parseLong(itemId));
			item.flagged();
			if(item.violator()){
				item.setActive(false);
				
				EmailUtil.INSTANCE
				.sendMail(
						"Delete Item Profile",
						"Your item, " + item.getItemName() + " has been deleted due to too many complaints from users."
								, "sleekswap@gmail.com", emailService.getPrimaryEmailForPerson(item.getPerson()).getEmail());				
			}
			EmailUtil.INSTANCE
			.sendMail(
					"Inappropriate Image Warning",
					"Your item, " + item.getItemName() + " has been flagged as inappropriate.  If you feel this is a mistake, please contact us at sleekswap@gmail.com."
							, "sleekswap@gmail.com", emailService.getPrimaryEmailForPerson(item.getPerson()).getEmail());				
			
			itemService.update(item);

			request.setAttribute("actionMessage",
					"You have successfully flagged these items.  A message has been sent to its owner." + item.getItemName());
		}
		
		return new ModelAndView("common/blank");
	}

	/**
	 * Used when the user wants to indicate that they have bought the existing
	 * item in their domain
	 */
	/** Take from search result and add to your domain **/
	public ModelAndView bought(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map map = new HashMap();
		String itemId = request.getParameter("itemId");
		Person person = fetchPerson();
		if (StringUtils.trimToNull(itemId) != null) {
			Item item = itemService.get(Long.parseLong(itemId));

			ItemCategory itemCategory = new ItemCategory();
			itemCategory.setCategory(CategoryType.BOUGHT);
			itemCategory.setDescription(person.getFirstName() + " bought "
					+ item.getItemName());
			itemCategory.setPerson(person);
			itemCategory.setItem(item);

			item.getItemCategories().add(itemCategory);
			
			person.getItemVisibility(item, CategoryType.BOUGHT.toString());
			person.getItemCategories().add(itemCategory);
			
			News itemnews = new News();
			itemnews.setAction(Action.BOUGHT);
			itemnews.setPerson(person);
			itemnews.setMessage(Action.BOUGHT.convert(person, item));
			person.getNewsPermission(itemnews);
			person.getNews().add(itemnews);

			personService.update(person);

			request.setAttribute("actionMessage",
					"You have successfully bought " + item.getItemName());
		}
		return new ModelAndView("search/actionMessage", map);
	}

	/**
	 * Used when the user wants to modify an existing uploaded picture to
	 * selling
	 **/
	/** Take from search result and add to your domain **/
	public ModelAndView selling(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String itemId = request.getParameter("itemId");
		Person person = fetchPerson();
		if (StringUtils.trimToNull(itemId) != null) {
			VendorItem item = vendorItemService.get(Long.parseLong(itemId));

			ItemCategory itemCategory = new ItemCategory();
			itemCategory.setCategory(CategoryType.SELLING);
			itemCategory.setDescription(person.getFirstName() + " selling "
					+ item.getItemName());
			itemCategory.setPerson(person);
			itemCategory.setItem(item);

			item.getItemCategories().add(itemCategory);
			person.getItemVisibility(item, CategoryType.SELLING.toString());
			
			person.getItemCategories().add(itemCategory);
			
			News itemnews = new News();
			itemnews.setAction(Action.SELLING);
			itemnews.setWorld(Action.SELLING.isWorld());
			itemnews.setPerson(person);
			itemnews.setMessage(Action.SELLING.convert(person, item));
			
			person.getNewsPermission(itemnews);
			person.getNews().add(itemnews);
			
			personService.update(person);

			request.setAttribute("actionMessage",
					"You have shown interest in selling the item "
							+ item.getItemName());
		}

		return new ModelAndView("search/actionMessage");
	}

	/** Take from search result and add to your domain **/
	public ModelAndView want(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map map = new HashMap();
		String itemId = request.getParameter("itemId");
		Person person = fetchPerson();
		if (StringUtils.trimToNull(itemId) != null) {
			Item item = itemService.get(Long.parseLong(itemId));

			ItemCategory itemCategory = new ItemCategory();
			itemCategory.setCategory(CategoryType.WANT);
			itemCategory.setDescription(person.getFirstName() + " wants "
					+ item.getItemName());
			itemCategory.setPerson(person);
			itemCategory.setItem(item);

			item.getItemCategories().add(itemCategory);
			person.getItemVisibility(item, CategoryType.WANT.toString());
	
			person.getItemCategories().add(itemCategory);

			News itemnews = new News();
			itemnews.setAction(Action.WANT);
			itemnews.setWorld(Action.WANT.isWorld());
			itemnews.setPerson(person);
			itemnews.setMessage(Action.WANT.convert(person, item));
			person.getNewsPermission(itemnews);
			person.getNews().add(itemnews);

			personService.update(person);

			request.setAttribute("actionMessage",
					"You have indicated a desire for " + item.getItemName());
		}

		return new ModelAndView("search/actionMessage");
	}

	/** Take from search result and add to your domain **/
	/** Need auto complete to create list of friends drop down for selecting. **/
	public ModelAndView recommend(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map map = new HashMap();
		String itemId = request.getParameter("itemId");
		Person person = fetchPerson();
		if (StringUtils.trimToNull(itemId) != null) {
			String[] friendIds = request.getParameterValues("friendIds");
			StringBuilder msg = new StringBuilder();
			for (String friendId : friendIds) {
				Item item = itemService.get(Long.parseLong(itemId));

				Person friend = personService.get(Long.parseLong(friendId));
				ItemCategory ic = new ItemCategory();
				ic.setCategory(CategoryType.RECOMMEND);
				ic.setItem(item);
				ic.setPerson(friend);
				
				item.getItemCategories().add(ic);
				
				friend.getItemVisibility(item,
						CategoryType.RECOMMEND.toString());		

				friend.getItemCategories().add(ic);
				
				News ins = new News();
				ins.setAction(Action.RECOMMEND);
				ins.setWorld(Action.RECOMMEND.isWorld());
				ins.setPerson(person);
				ins.setMessage(Action.RECOMMEND
						.convert(person, friend, item));				
				
				friend.getNewsPermission(ins);
				friend.getNews().add(ins);
				
				person.getNewsPermission(ins);
				person.getNews().add(ins);				
				
				personService.update(friend);
				personService.update(person);

				msg.append("\n");
				msg.append("You have recommended " + item.getItemName()
						+ " to " + friend.getFullName());
				
				EmailUtil.INSTANCE.sendMail("Recommendation", person.getFullName() + " has recommended " + item.getItemName() + " to you." , "sleekswap@gmail.com",
						emailService.getPrimaryEmailForPerson(friend).getEmail());					
			}
			request.setAttribute("actionMessage", msg.toString());
		}

		return new ModelAndView("search/actionMessage");
	}

	/** Take from search result and add to your domain **/
	public ModelAndView advice(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// String[] itemIds = request.getParameterValues("itemIds");
		
		/*
		for(String itemId : itemIds){
			Item item = itemService.get(Long.parseLong(itemId));
			System.out.println("itemId " + itemId);
		}
		*/
		String itemId = request.getParameter("itemId");
		Person person = fetchPerson();
		if (StringUtils.trimToNull(itemId) != null) {
			Item item = itemService.get(Long.parseLong(itemId));

			ItemCategory itemCategory = new ItemCategory();
			itemCategory.setCategory(CategoryType.ADVICE);
			itemCategory.setDescription(person.getFirstName()
					+ " needs adivce on item " + item.getItemName());
			itemCategory.setPerson(person);
			itemCategory.setItem(item);

			item.getItemCategories().add(itemCategory);
			person.getItemCategories().add(itemCategory);			

			News itemnews = new News();
			itemnews.setAction(Action.ADVICE);
			itemnews.setWorld(Action.ADVICE.isWorld());
			itemnews.setPerson(person);
			itemnews.setMessage(Action.ADVICE.convert(person, item));
			log.debug("Message : " + itemnews.getMessage());
			person.getNewsPermission(itemnews);
			person.getNews().add(itemnews);

			personService.update(person);

			request.setAttribute("actionMessage",
					"You would like advice for item " + item.getItemName());
		}
		Map map = new HashMap();
		return new ModelAndView("search/actionMessage", map);
	}	

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public void setNewsVisibilityService(
			NewsVisibilityDomainService newsVisibilityService) {
		this.newsVisibilityService = newsVisibilityService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public void setItemCategoryService(ItemCategoryService itemCategoryService) {
		this.itemCategoryService = itemCategoryService;
	}

	public void setItemVisibilityService(
			ItemVisibilityDomainService itemVisibilityService) {
		this.itemVisibilityService = itemVisibilityService;
	}

	public void setVendorItemService(VendorItemService vendorItemService) {
		this.vendorItemService = vendorItemService;
	}

}
```
