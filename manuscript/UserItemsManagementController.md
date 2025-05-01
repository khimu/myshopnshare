# UserItemsManagementController

```java
package com.myshopnshare.webapp.controller;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import com.myshopnshare.model.User;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.myshopnshare.core.domain.Action;
import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.domain.ItemCategory;
import com.myshopnshare.core.domain.ItemTag;
import com.myshopnshare.core.domain.News;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.Photo;
import com.myshopnshare.core.domain.PointItem;
import com.myshopnshare.core.domain.VendorItem;
import com.myshopnshare.core.enums.CategoryType;
import com.myshopnshare.core.enums.ItemType;
import com.myshopnshare.core.enums.UserType;
import com.myshopnshare.core.service.EmailService;
import com.myshopnshare.core.service.ItemCategoryService;
import com.myshopnshare.core.service.ItemService;
import com.myshopnshare.core.service.ItemUploadService;
import com.myshopnshare.core.service.ItemVisibilityDomainService;
import com.myshopnshare.core.service.NewsService;
import com.myshopnshare.core.service.NewsVisibilityDomainService;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.core.service.PointItemService;
import com.myshopnshare.core.service.VendorItemService;
import com.myshopnshare.utils.Dump;

public class UserItemsManagementController extends BaseController {
	private static transient final Log log = LogFactory
			.getLog(UserItemsManagementController.class);
	@Autowired
	private EmailService emailService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private PersonService personService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private NewsVisibilityDomainService newsVisibilityService;
	@Autowired
	private ItemVisibilityDomainService itemVisibilityService;
	@Autowired
	private ItemCategoryService itemCategoryService;
	@Autowired
	private VendorItemService vendorItemService;
	@Autowired
	private PointItemService pointItemService;
	
	@Autowired
	private ItemUploadService itemUploadService;

	@Autowired
	private Dump dump;

	public Person fetchPerson() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		User user = (User) principal;
		Person person = personService.findPersonByUsername(user.getUsername());
		return person;
	}

	public void setDump(Dump dump) {
		this.dump = dump;
	}

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();

		request.setAttribute("items", itemService.findOwnItems(person, null,
				null, 0));
		return new ModelAndView("item/jsonItems");
	}

	public ModelAndView tag(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String itemId = request.getParameter("itemId");
		String newtags = request.getParameter("tags");
		if (StringUtils.trimToNull(itemId) != null) {
			Item item = itemService.get(Long.parseLong(itemId));
			String[] tags = StringUtils.split(newtags, ",");
			for (String str : tags) {
				ItemTag tag = new ItemTag();
				tag.setTag(str);
				tag.setItem(item);
				item.getTags().add(tag);
			}
			itemService.update(item);
			request.setAttribute("items", item);
		}
		return new ModelAndView("item/jsonItem");
	}

	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map map = new HashMap();
		Person person = fetchPerson();
		String itemId = request.getParameter("itemId");
		if (StringUtils.trimToNull(itemId) != null) {
			Item item = itemService.getItemForPerson(person, Long
					.parseLong(itemId));
			item.setActive(false);
			itemService.update(item);
		}

		request.setAttribute("items", itemService.findOwnItems(person, null,
				null, 0));
		return new ModelAndView("item/jsonItems");
	}

	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();

		String itemId = request.getParameter("itemId");
		if (StringUtils.trimToNull(itemId) != null) {
			request.setAttribute("item", itemService.getItemForPerson(person,
					Long.parseLong(itemId)));
		}
		return new ModelAndView("item/jsonItem");
	}

	// @Deprecated
	public ModelAndView getEdit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();

		String itemId = request.getParameter("itemId");
		if (StringUtils.trimToNull(itemId) != null) {
			request.setAttribute("item", itemService.getItemForPerson(person,
					Long.parseLong(itemId)));
		}
		return new ModelAndView("users/useritemuploadedit");
	}

	public ModelAndView upload(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map map = new HashMap();
		Person person = fetchPerson();
		String country = request.getParameter("country");
		String actualcategory = request.getParameter("actualcategory");
		String category = request.getParameter("category");
		String tagsStr = request.getParameter("tags");
		String title = request.getParameter("title");
		String subtitle = request.getParameter("subtitle");
		String externalImageLink = request.getParameter("externalImageLink");
		String resourceLink = request.getParameter("resourceLink");
		String[] friendIds = request.getParameterValues("friendId");

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		CommonsMultipartFile fileData = (CommonsMultipartFile) multipartRequest.getFile("userFileToUpload");

		/*
		 * if(!person.isSubscribed() && category != null &&
		 * (CategoryType.valueOf(category) == CategoryType.SELLING &&
		 * person.isMaxSelling() || CategoryType.valueOf(category) ==
		 * CategoryType.SERVICE)){ request.setAttribute("searchMessage",
		 * "You have reached your max selling allowed."); return new
		 * ModelAndView("items/jsonItem"); }
		 */

		// do not Need to reset this on a monthly basis
		if (category != null
				&& CategoryType.valueOf(category) == CategoryType.SELLING
				&& !person.isSubscribed()) {
			// && person.isMaxSelling()) {
			request.setAttribute("searchMessage",
					"You have reached your max selling allowed.");
			return new ModelAndView("items/jsonItem");
		}

		/*
		 * if (category != null && CategoryType.valueOf(category) ==
		 * CategoryType.SERVICE && person.isMaxService()) {
		 * request.setAttribute("searchMessage",
		 * "You have reached your max service allowed."); return new
		 * ModelAndView("items/jsonItem"); }
		 */

		// //////////////////

		Item item = null;
		Photo icon = new Photo();
		Photo thumb = new Photo();
		Photo glimpse = new Photo();

		if (StringUtils.trimToNull(externalImageLink) == null) {

				String root = dump.getItemsPath().getFile().getAbsolutePath()
				+ File.separator + "users" + File.separator
				+ person.identifier() + File.separator + category
				+ File.separator;

				String saveFile = dump.dump(fileData, "", root);

				log.debug("File saved as " + root + saveFile);

				String relativeroot = dump.getItemsPath().getFile()
						.getName()
						+ "/users/"
						+ person.identifier()
						+ "/"
						+ category
						+ "/";

				icon.setPath(dump.makeIcon(relativeroot, root, saveFile));
				thumb.setPath(dump.makeThumbnail(relativeroot, root,
						saveFile));
				glimpse.setPath(dump.makeGlimpse(relativeroot, root,
						saveFile));
		} else {
			icon = Photo.GENERIC_DEFAULT_ICON;
			thumb = Photo.GENERIC_DEFAULT_THUMB;
			glimpse = Photo.GENERIC_DEFAULT_GLIMPSE;
		}

		if (CategoryType.valueOf(category) == CategoryType.SELLING
				|| CategoryType.valueOf(category) == CategoryType.SERVICE) {
			item = makeVendorItem(request, person, icon, thumb, glimpse);
		} else if (person.getUserType() == UserType.SLEEKSWAP
				&& CategoryType.valueOf(category) == CategoryType.POINT) {
			item = makePointItem(request, person, icon, thumb, glimpse);
		} else {
			item = makeItem(request, person, icon, thumb, glimpse);
		}

		itemUploadService.uploadItem(item, person, country, actualcategory, category, tagsStr, title, subtitle, externalImageLink, resourceLink, friendIds);
/*
		item.setTitle(title);
		item.setSubtitle(subtitle);
		item.setExternalLink(externalImageLink);
		item.setResourceLink(resourceLink);

		String[] tags = StringUtils.split(tagsStr, ",");
		for (String tag : tags) {
			ItemTag t = new ItemTag();
			t.setTag(tag);
			t.setItem(item);
			item.getTags().add(t);
		}

		if (StringUtils.trimToNull(country) != null) {
			ItemTag t = new ItemTag();
			t.setTag(country);
			t.setItem(item);
			item.getTags().add(t);
		}

		if (StringUtils.trimToNull(actualcategory) != null) {
			ItemTag t = new ItemTag();
			t.setTag(actualcategory);
			t.setItem(item);
			item.getTags().add(t);
		}

		ItemTag t = new ItemTag();
		t.setTag(item.getItemName());
		t.setItem(item);
		
		itemTagService.save();
		
		//item.getTags().add(t);

		ItemCategory itemCategory = new ItemCategory();
		itemCategory.setCategory(CategoryType.valueOf(category));
		itemCategory.setItem(item);
		itemCategory.setPerson(person);
		
		itemCategoryService.save(itemCategory);
		
		//item.getItemCategories().add(itemCategory);

		person.getItemVisibility(item, category);
		//person.getItems().add(item);

		if (CategoryType.valueOf(category) == CategoryType.SELLING
				|| CategoryType.valueOf(category) == CategoryType.SERVICE) {
			vendorItemService.save((VendorItem) item);
		} else if (CategoryType.valueOf(category) == CategoryType.POINT) {
			pointItemService.save((PointItem) item);
		} else {
			itemService.save(item);
		}

		if (CategoryType.valueOf(category) == CategoryType.RECOMMEND) {
			for (String friendId : friendIds) {
				// TODO make sure this actually saves friend information
				// that is new...does cascade cascade down to friend. It
				// should.
				// Person friend =
				// personService.get(Long.parseLong(friendId));
				Person friend = person.findFriend(Long.parseLong(friendId))
						.getFriend();

				ItemCategory ic = new ItemCategory();
				ic.setCategory(CategoryType.RECOMMEND);
				ic.setItem(item);
				ic.setPerson(friend);
				item.getItemCategories().add(ic);
				friend.getItemVisibility(item, category);
				friend.getItemCategories().add(ic);

				News ins = new News();
				ins.setAction(Action.RECOMMEND);
				ins.setPerson(person);
				ins.setMessage(Action.RECOMMEND.convert(person, friend,
						item));
				log.debug("Message : " + ins.getMessage());

				friend.getNewsPermission(ins);
				friend.getNews().add(ins);

				person.getNewsPermission(ins);
				person.getNews().add(ins);

				personService.update(person);
				personService.update(friend);
			}
		} else if (Action.valueOf(category) != Action.RECOMMEND) {
			News itemnews = new News();
			itemnews.setAction(Action.valueOf(category));
			itemnews.setWorld(Action.valueOf(category).isWorld());
			itemnews.setPerson(person);
			// itemnews.setItem(item);
			itemnews.setMessage(Action.valueOf(category).convert(person,
					item));

			person.getNewsPermission(itemnews);
			person.getNews().add(itemnews);

			personService.update(person);
		}
		*/

		request.setAttribute("item", item);
		
		return new ModelAndView("item/jsonItem", map);
	}

	/** ----------------------------------------- **/
	public ModelAndView edit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map map = new HashMap();
		Person person = fetchPerson();
		String country = request.getParameter("country");
		String actualcategory = request.getParameter("actualcategory");
		String itemId = request.getParameter("id");
		String category = request.getParameter("category");
		String tagsStr = request.getParameter("tags");
		String title = request.getParameter("title");
		String subtitle = request.getParameter("subtitle");
		String[] friendIds = request.getParameterValues("friendId");

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		CommonsMultipartFile fileData = (CommonsMultipartFile) multipartRequest.getFile("fileToUpload");
		
		if (StringUtils.trimToNull(itemId) == null) {
			log.error("id is missing for item edit");
			return new ModelAndView("item/jsonItem", map);
		}

		Item item = null;
		if (category.equals("SELLING") || category.equals("SERVICE")) {
			item = vendorItemService.getVendorItemForPerson(person, Long
					.parseLong(itemId));
			item = editVendorItem(request, person, (VendorItem) item);
		} else if (category.equals("POINT")) {
			item = pointItemService.getPointItemForPerson(person, Long
					.parseLong(itemId));
			item = editPointItem(request, person, (PointItem) item);
		} else {
			item = itemService.getItemForPerson(person, Long.parseLong(itemId));
			item = editItem(request, person, item);
		}

		item.setTitle(StringUtils.trimToNull(title) == null ? item.getTitle()
				: title);
		item.setSubtitle(StringUtils.trimToNull(title) == null ? item
				.getSubtitle() : subtitle);

		if (StringUtils.trimToNull(category) != null) {
			ItemCategory itemCategory = item.categoryFor(category);
			if (itemCategory != null) {
				itemCategory.setCategory(CategoryType.valueOf(category));
				itemCategory.setItem(item);
				itemCategory.setPerson(person);
				item.getItemCategories().add(itemCategory);
			}
		}

		if (StringUtils.trimToNull(country) != null) {
			ItemTag t = new ItemTag();
			t.setTag(country);
			t.setItem(item);
			item.getTags().add(t);
		}

		if (StringUtils.trimToNull(actualcategory) != null) {
			ItemTag t = new ItemTag();
			t.setTag(actualcategory);
			t.setItem(item);
			item.getTags().add(t);
		}

		if ((category.equals("SELLING") || category.equals("SERVICE"))
				&& (person.isSubscribed() || emailService.getPrimaryEmailForPerson(person)
						.getEmail().equals("sleekswap@gmail.com"))) {
			vendorItemService.update((VendorItem) item);
		} else {
			itemService.update(item);
		}

		if (CategoryType.valueOf(category) == CategoryType.RECOMMEND) {
			for (String friendId : friendIds) {
				// TODO make sure this actually saves friend information that is
				// new...does cascade cascade down to friend. It should.
				// Person friend = personService.get(Long.parseLong(friendId));
				Person friend = person.findFriend(Long.parseLong(friendId))
						.getFriend();

				ItemCategory ic = new ItemCategory();
				ic.setCategory(CategoryType.RECOMMEND);
				ic.setItem(item);
				ic.setPerson(friend);
				item.getItemCategories().add(ic);

				friend.getItemVisibility(item, category);
				friend.getItemCategories().add(ic);

				News ins = new News();
				ins.setAction(Action.RECOMMEND);
				ins.setWorld(Action.RECOMMEND.isWorld());
				ins.setPerson(person);
				ins.setMessage(Action.RECOMMEND.convert(person, friend, item));
				log.debug("Message : " + ins.getMessage());

				friend.getNewsPermission(ins);
				friend.getNews().add(ins);

				person.getNewsPermission(ins);
				person.getNews().add(ins);

				personService.update(person);
				personService.update(friend);
			}
		} else if (Action.valueOf(category) != Action.RECOMMEND) {
			News itemnews = new News();
			itemnews.setAction(Action.valueOf(category));
			itemnews.setPerson(person);
			itemnews.setWorld(Action.valueOf(category).isWorld());
			itemnews.setMessage(Action.valueOf(category).convert(person, item));

			person.getNewsPermission(itemnews);
			person.getNews().add(itemnews);

			personService.update(person);
		}

			/*
			 * DataInputStream in = new
			 * DataInputStream(request.getInputStream()); int formDataLength =
			 * request.getContentLength(); int lastIndex =
			 * contentType.lastIndexOf("="); String boundary =
			 * contentType.substring(lastIndex + 1, contentType .length());
			 */

				String root = dump.getItemsPath().getFile().getAbsolutePath()
						+ File.separator + "users" + File.separator
						+ person.identifier() + File.separator + category
						+ File.separator;

				String saveFile = dump.dump(fileData, "", root);

				// String saveFile = dump.dump(in, formDataLength, lastIndex,
				// boundary, root);

				if (saveFile != null) {
					String relativeroot = dump.getItemsPath().getFile()
							.getName()
							+ "/users/"
							+ person.identifier()
							+ "/"
							+ category
							+ "/";

					Photo icon = item.getIcon();
					icon.setPath(dump.makeIcon(relativeroot, root, saveFile));

					Photo thumb = item.getThumbnail();
					thumb.setPath(dump.makeThumbnail(relativeroot, root,
							saveFile));

					Photo glimpse = item.getGlimpse();
					glimpse.setPath(dump.makeGlimpse(relativeroot, root,
							saveFile));
				}

		request.setAttribute("item", item);
		personService.update(person);
		return new ModelAndView("item/jsonItem", map);
	}

	private Item editItem(HttpServletRequest request, Person person, Item item) {
		String description = request.getParameter("description");
		String itemName = request.getParameter("itemName");

		item.setDescription(StringEscapeUtils.escapeHtml(description));
		item.setItemName(StringUtils.trimToNull(itemName) == null ? item
				.getItemName() : itemName);

		return item;
	}

	private VendorItem editVendorItem(HttpServletRequest request,
			Person person, VendorItem item) {
		String description = request.getParameter("description");
		String itemName = request.getParameter("itemName");
		String quantity = request.getParameter("quantity");

		String shipping = request.getParameter("shipping");
		String tax = request.getParameter("tax");
		String serialNumber = request.getParameter("serialNumber");
		String price = request.getParameter("price");

		String rebateAmount = request.getParameter("rebateAmount");
		String clearanceAmount = request.getParameter("clearanceAmount");
		String refuberish = request.getParameter("refuberish");
		String used = request.getParameter("used");

		item
				.setSerialNumber(StringUtils.trimToNull(serialNumber) == null ? item
						.getSerialNumber()
						: serialNumber);
		item.setPrice(StringUtils.trimToNull(price) == null ? item.getPrice()
				: Float.parseFloat(price));
		item.setDescription(StringUtils.trimToNull(description) == null ? item
				.getDescription() : StringEscapeUtils.escapeHtml(description));
		item.setItemName(StringUtils.trimToNull(itemName) == null ? item
				.getItemName() : itemName);
		item.setQuantity(StringUtils.trimToNull(quantity) == null ? item
				.getQuantity() : Integer.parseInt(quantity));

		item.setShipping(StringUtils.trimToNull(shipping) == null ? item
				.getShipping() : Float.parseFloat(shipping));
		item.setTax(StringUtils.trimToNull(tax) == null ? item.getTax() : Float
				.parseFloat(tax));
		item
				.setClearancePercentage(StringUtils.trimToNull(clearanceAmount) == null ? item
						.getClearancePercentage()
						: Integer.parseInt(clearanceAmount));
		item
				.setRebateAmount(StringUtils.trimToNull(rebateAmount) == null ? item
						.getRebateAmount()
						: Integer.parseInt(rebateAmount));
		item.setRefuberish(StringUtils.trimToNull(refuberish) == null ? item
				.isRefuberish() : Boolean.valueOf(refuberish));
		item.setUsed(StringUtils.trimToNull(used) == null ? item.isUsed()
				: Boolean.valueOf(used));
		return item;
	}

	private PointItem editPointItem(HttpServletRequest request, Person person,
			PointItem item) {
		String description = request.getParameter("description");
		String itemName = request.getParameter("itemName");
		String quantity = request.getParameter("quantity");

		String shipping = request.getParameter("shipping");
		String tax = request.getParameter("tax");
		String serialNumber = request.getParameter("serialNumber");
		String price = request.getParameter("price");

		item
				.setSerialNumber(StringUtils.trimToNull(serialNumber) == null ? item
						.getSerialNumber()
						: serialNumber);
		item.setPrice(StringUtils.trimToNull(price) == null ? item.getPrice()
				: Float.parseFloat(price));
		item.setDescription(StringUtils.trimToNull(description) == null ? item
				.getDescription() : StringEscapeUtils.escapeHtml(description));
		item.setItemName(StringUtils.trimToNull(itemName) == null ? item
				.getItemName() : itemName);
		item.setQuantity(StringUtils.trimToNull(quantity) == null ? item
				.getQuantity() : Integer.parseInt(quantity));

		item.setShipping(StringUtils.trimToNull(shipping) == null ? item
				.getShipping() : Float.parseFloat(shipping));
		item.setTax(StringUtils.trimToNull(tax) == null ? item.getTax() : Float
				.parseFloat(tax));

		return item;
	}

	private Item makeItem(HttpServletRequest request, Person person,
			Photo icon, Photo thumb, Photo glimpse) {
		String description = request.getParameter("description");
		String itemName = request.getParameter("itemName");

		Item item = new Item(icon, thumb, glimpse);
		item.setDescription(StringEscapeUtils.escapeHtml(description));
		item.setItemName(itemName);
		item.setPerson(person);
		item.setType(ItemType.ITEM);

		return item;
	}

	private VendorItem makeVendorItem(HttpServletRequest request,
			Person person, Photo icon, Photo thumb, Photo glimpse) {
		String description = request.getParameter("description");
		String itemName = request.getParameter("itemName");

		String shipping = request.getParameter("shipping");
		String tax = request.getParameter("tax");
		String serialNumber = request.getParameter("serialNumber");
		String price = request.getParameter("price");
		String quantity = request.getParameter("quantity");

		String rebateAmount = request.getParameter("rebateAmount");
		String clearanceAmount = request.getParameter("clearanceAmount");
		String refuberish = request.getParameter("refuberish");
		String used = request.getParameter("used");

		VendorItem item = new VendorItem(icon, thumb, glimpse);
		item.setSerialNumber(serialNumber);
		item.setPrice(Float.parseFloat(price));
		item.setQuantity(Integer.parseInt(quantity));
		item.setDescription(StringEscapeUtils.escapeHtml(description));
		item.setItemName(itemName);
		item.setPerson(person);
		item.setType(ItemType.VENDOR);

		ItemTag t = new ItemTag();
		t.setTag(item.getSerialNumber());
		t.setItem(item);
		item.getTags().add(t);

		item.setShipping(Float.parseFloat(shipping));
		item.setTax(Float.parseFloat(tax));
		item.setClearancePercentage(Integer.parseInt(clearanceAmount));
		item.setRebateAmount(Integer.parseInt(rebateAmount));
		item.setRefuberish(Boolean.valueOf(refuberish));
		item.setUsed(Boolean.valueOf(used));
		return item;
	}

	private PointItem makePointItem(HttpServletRequest request, Person person,
			Photo icon, Photo thumb, Photo glimpse) {
		String description = request.getParameter("description");
		String itemName = request.getParameter("itemName");

		String shipping = request.getParameter("shipping");
		String tax = request.getParameter("tax");
		String serialNumber = request.getParameter("serialNumber");
		String price = request.getParameter("price");
		String quantity = request.getParameter("quantity");

		PointItem item = new PointItem(icon, thumb, glimpse);
		item.setSerialNumber(serialNumber);
		item.setPrice(Float.parseFloat(price));
		item.setQuantity(Integer.parseInt(quantity));
		item.setDescription(StringEscapeUtils.escapeHtml(description));
		item.setItemName(itemName);
		item.setPerson(person);
		item.setType(ItemType.POINT);

		item.setShipping(Float.parseFloat(shipping));
		item.setTax(Float.parseFloat(tax));

		return item;
	}

	public ModelAndView bulk(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String itemDirectory = request.getParameter("itemDirectory");
		String category = request.getParameter("category");
		// This is for uploading the pictures
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		CommonsMultipartFile fileData = (CommonsMultipartFile) multipartRequest.getFile("fileToUpload");

				String root = dump.getItemsPath().getFile().getAbsolutePath()
						+ person.identifier() + File.separator + "merchant"
						+ File.separator;
				String masterFile = dump.dump(fileData, category, root);

				pipe(masterFile, root, category, person);

		request.setAttribute("items", itemService.findOwnItems(person, null,
				null, 0));
		return new ModelAndView("item/jsonItems");
	}

	/**
	 * Expects that images are already uploaded via applet
	 * 
	 * This should go in a thread and ran in the background and response should
	 * be returned to user immediatly
	 * 
	 * @param masterFile
	 * @param itemPath
	 */
	private void pipe(String masterFile, String root, String category,
			Person person) {
		List<Item> items = new ArrayList<Item>();
		File file = new File(masterFile);

		try {
			String thisLine = "";
			Byte[] bytes = new Byte[1024];
			FileInputStream fstream = new FileInputStream(file);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			while ((thisLine = br.readLine()) != null) {
				persistItems(thisLine, person, masterFile, category, root);
			}
		} catch (IOException e) {
			log.error("Unable to read master file " + e.getMessage());
		}
	}

	private void persistItems(String thisLine, Person person,
			String masterFile, String category, String root) throws IOException {
		String[] fields = StringUtils.split(thisLine, "|");
		if (fields.length != 6) {
			log.debug("Error parsing line " + thisLine);
			return;
		}

		root = root + category + "/";

		String relative = dump.getItemsPath().getFile().getName() + "/users/"
				+ person.identifier() + "/" + category + "/";

		Photo icon = new Photo();
		icon.setPath(dump.makeIcon(relative, root, fields[0]));

		Photo thumb = new Photo();
		thumb.setPath(dump.makeThumbnail(relative, root, fields[0]));

		Photo glimpse = new Photo();
		glimpse.setPath(dump.makeGlimpse(relative, root, fields[0]));

		Item item = new Item();

		/*
		 * item.setTitle(title); item.setSubtitle(subtitle);
		 * item.setExternalLink(externalImageLink);
		 * item.setResourceLink(resourceLink);
		 * 
		 * String[] tags = StringUtils.split(tagsStr, ","); for (String tag :
		 * tags) { ItemTag t = new ItemTag(); t.setTag(tag); t.setItem(item);
		 * item.getTags().add(t); }
		 * 
		 * if(StringUtils.trimToNull(country) != null){ ItemTag t = new
		 * ItemTag(); t.setTag(country); t.setItem(item); item.getTags().add(t);
		 * }
		 */
		ItemTag t = new ItemTag();
		t.setTag(item.getItemName());
		t.setItem(item);
		item.getTags().add(t);

		item.setDescription(StringEscapeUtils.escapeHtml(fields[3]));
		item.setItemName(fields[4]);
		item.setIcon(icon);
		item.setThumbnail(thumb);
		item.setGlimpse(glimpse);
		item.setPerson(person);
		item.setType(ItemType.ITEM);

		// association table
		ItemCategory itemCategory = new ItemCategory();
		itemCategory.setCategory(CategoryType.valueOf(category.toUpperCase()));
		itemCategory.setDescription(fields[5]);
		itemCategory.setPerson(person);
		itemCategory.setItem(item);

		item.getItemCategories().add(itemCategory);
		person.getItemVisibility(item, category);
		person.getItems().add(item);

		News itemnews = new News();
		itemnews.setAction(Action.valueOf(category));
		itemnews.setPerson(person);
		itemnews.setWorld(Action.valueOf(category).isWorld());
		itemnews.setMessage(Action.valueOf(category).convert(person, item));
		log.debug("Message : " + itemnews.getMessage());
		person.getNewsPermission(itemnews);
		person.getNews().add(itemnews);

		personService.update(person);

		// filename, serialNumber, private, description, itemName,
		// categorydescription
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
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

	public void setItemCategoryService(ItemCategoryService itemCategoryService) {
		this.itemCategoryService = itemCategoryService;
	}

	public void setVendorItemService(VendorItemService vendorItemService) {
		this.vendorItemService = vendorItemService;
	}

	public void setPointItemService(PointItemService pointItemService) {
		this.pointItemService = pointItemService;
	}

}
```
