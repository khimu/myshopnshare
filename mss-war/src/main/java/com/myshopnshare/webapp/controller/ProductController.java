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
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.Action;
import com.myshopnshare.core.domain.ItemCategory;
import com.myshopnshare.core.domain.ItemTag;
import com.myshopnshare.core.domain.ItemVisibilityDomain;
import com.myshopnshare.core.domain.News;
import com.myshopnshare.core.domain.NewsVisibilityDomain;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.Photo;
import com.myshopnshare.core.domain.VendorItem;
import com.myshopnshare.core.enums.CategoryType;
import com.myshopnshare.core.enums.ItemType;
import com.myshopnshare.core.enums.UserType;
import com.myshopnshare.core.service.ItemCategoryService;
import com.myshopnshare.core.service.ItemVisibilityDomainService;
import com.myshopnshare.core.service.NewsService;
import com.myshopnshare.core.service.NewsVisibilityDomainService;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.core.service.PhotoCategoryService;
import com.myshopnshare.core.service.PhotoService;
import com.myshopnshare.core.service.VendorItemService;
import com.myshopnshare.core.service.VendorService;
import com.myshopnshare.core.service.VisibilityDomainService;
import com.myshopnshare.utils.Dump;

/**
 * Merchant item management controller
 * 
 * @author khimung
 * 
 */
public class ProductController extends MultiActionController {
	private static transient final Log log = LogFactory
			.getLog(ProductController.class);

	@Autowired
	private VendorItemService vendorItemService;
	@Autowired
	private PersonService personService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private VendorService vendorService;
	@Autowired
	private PhotoService photoService;
	@Autowired
	private ItemCategoryService itemCategoryService;
	@Autowired
	private PhotoCategoryService photoCategoryService;
	@Autowired
	private NewsVisibilityDomainService newsVisibilityService;
	@Autowired
	private ItemVisibilityDomainService itemVisibilityService;
	@Autowired
	private VisibilityDomainService visibilityService;

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
		if (person.getUserType() != UserType.MERCHANT) {
			return new ModelAndView("login/login");
		}
		request.setAttribute("products", vendorItemService
				.findAllVendorItemsFor(person, 0));
		return new ModelAndView("merchant/products");
	}

	// Searching on tag value
	public ModelAndView search(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		List<VendorItem> items = null;
		String searchString = request.getParameter("searchString");
		String startStr = request.getParameter("start");
		int start = 0;
		if (StringUtils.trimToNull(startStr) != null) {
			start = Integer.parseInt(request.getParameter("start"));
		}
		items = vendorItemService.findWorldVendorItems(searchString, "SELLING",
				start);
		if (items.size() == 0) {
			request.setAttribute("searchMessage",
					"No results matched your search");
		}
		request.setAttribute("items", items);
		return new ModelAndView("merchant/products");
	}

	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		if (person.getUserType() != UserType.MERCHANT) {
			return new ModelAndView("login/login");
		}
		String itemId = request.getParameter("itemId");
		VendorItem item = vendorItemService.getVendorItemForPerson(person, Long
				.parseLong(itemId));
		item.setActive(false);
		vendorItemService.update(item);
		request.setAttribute("products", vendorItemService
				.findAllVendorItemsFor(person, 0));

		return new ModelAndView("merchant/products");
	}

	public ModelAndView bulk(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		if (person.getUserType() != UserType.MERCHANT) {
			return new ModelAndView("login/login");
		}
		String itemDirectory = request.getParameter("itemDirectory");
		// This is for uploading the pictures

		String contentType = request.getContentType();
		if ((contentType != null)
				&& (contentType.indexOf("multipart/form-data") >= 0)) {

			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			int count = 0;
			List items = null;
			try {
				items = upload.parseRequest(request);
				Iterator itr = items.iterator();
				while (itr.hasNext()) {
					FileItem item = (FileItem) itr.next();
					if (item.isFormField()) {
					} else {
						count++;
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			}

			String root = dump.getItemsPath().getFile().getAbsolutePath()
					+ File.separator + "merchants" + File.separator
					+ person.identifier() + File.separator;
			String masterFile = dump.dump(items, "sell", root);

			pipe(masterFile, root + "sell" + File.separator, person);
		}

		Map map = new HashMap();
		map.put("products", vendorItemService.findAllVendorItemsFor(person, 0));

		return new ModelAndView("merchant/products", map);
	}

	public ModelAndView upload(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map map = new HashMap();
		Person person = fetchPerson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		CommonsMultipartFile fileData = (CommonsMultipartFile) multipartRequest.getFile("fileToUpload");


		if (StringUtils.trimToNull(request.getParameter("friendId")) != null) {
			Person friend = personService.get(Long.parseLong(request
					.getParameter("friendId")));
		}

		String description = request.getParameter("description");
		String itemName = request.getParameter("itemName");
		String tagsStr = request.getParameter("tags");
		String shipping = request.getParameter("shipping");
		String tax = request.getParameter("tax");
		String serialNumber = request.getParameter("serialNumber");
		String price = request.getParameter("price");
		String category = request.getParameter("category");

		String rebateAmount = request.getParameter("rebateAmount");
		String clearanceAmount = request.getParameter("clearanceAmount");
		String refuberish = request.getParameter("refuberish");
		String used = request.getParameter("used");
		String reedemablePoints = request.getParameter("reedemablePoints");

			/*
			 * DataInputStream in = new
			 * DataInputStream(request.getInputStream()); int formDataLength =
			 * request.getContentLength(); int lastIndex =
			 * contentType.lastIndexOf("="); String boundary =
			 * contentType.substring(lastIndex + 1, contentType .length());
			 */

				String root = dump.getItemsPath().getFile().getAbsolutePath()
						+ File.separator + "merchants" + File.separator
						+ person.identifier() + File.separator + category
						+ File.separator;

				// String saveFile = dump.dump(in, formDataLength, lastIndex,
				// boundary, root);

				String saveFile = dump.dump(fileData, "", root);

				log.debug("File saved as " + root + saveFile);

				String relativeroot = dump.getItemsPath().getFile().getName()
						+ "/merchants/" + person.identifier() + "/" + category
						+ "/";

				Photo icon = new Photo();
				icon.setPath(dump.makeIcon(relativeroot, root, saveFile));

				Photo thumb = new Photo();
				thumb.setPath(dump.makeThumbnail(relativeroot, root, saveFile));

				Photo glimpse = new Photo();
				glimpse.setPath(dump.makeGlimpse(relativeroot, root, saveFile));

				VendorItem item = new VendorItem();
				item.setSerialNumber(serialNumber);
				item.setPrice(Float.parseFloat(price));
				item.setDescription(StringEscapeUtils.escapeHtml(description));
				item.setItemName(itemName);
				item.setIcon(icon);
				item.setThumbnail(thumb);
				item.setGlimpse(glimpse);
				item.setPerson(person);
				item.setType(ItemType.VENDOR);
				item.setShipping(Float.parseFloat(shipping));
				item.setTax(Float.parseFloat(tax));
				item.setClearancePercentage(Integer.parseInt(clearanceAmount));
				item.setRebateAmount(Integer.parseInt(rebateAmount));
				item.setRefuberish(Boolean.valueOf(refuberish));
				item.setReedemablePoints(Integer.parseInt(reedemablePoints));
				item.setUsed(Boolean.valueOf(used));

				String[] tags = StringUtils.split(tagsStr, ",");
				for (String tag : tags) {
					ItemTag t = new ItemTag();
					t.setTag(tag);
					t.setItem(item);
					item.getTags().add(t);
				}

				// association table
				ItemCategory itemCategory = new ItemCategory();
				itemCategory.setCategory(CategoryType.valueOf(category));
				itemCategory.setItem(item);
				itemCategory.setPerson(person);

				item.getItemCategories().add(itemCategory);

				ItemVisibilityDomain ivd = new ItemVisibilityDomain(item,
						person.getDefaultVisibility());

				item.getItemVisibilities().add(ivd);

				person.getItems().add(item);

				// vendorItemService.save(item);

				News itemnews = new News();
				itemnews.setAction(Action.valueOf(category));
				itemnews.setWorld(Action.valueOf(category).isWorld());
				itemnews.setPerson(person);
				itemnews.setMessage(Action.valueOf(category).convert(person,
						item));
				log.debug("Message : " + itemnews.getMessage());

				NewsVisibilityDomain nvd = new NewsVisibilityDomain(itemnews,
						person.getDefaultVisibility());
				itemnews.getNewsVisibility().add(nvd);

				person.getNews().add(itemnews);

				// newsVisibilityService.save(nvd);
				// itemVisibilityService.save(ivd);
				// newsService.save(itemnews);

				personService.update(person);

		map.put("products", vendorItemService.findAllVendorItemsFor(person, 0));

		return new ModelAndView("merchant/products", map);

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
	private void pipe(String masterFile, String root, Person person) {
		List<VendorItem> items = new ArrayList<VendorItem>();
		File file = new File(masterFile);

		try {
			String thisLine = "";
			Byte[] bytes = new Byte[1024];
			FileInputStream fstream = new FileInputStream(file);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			while ((thisLine = br.readLine()) != null) {
				persistItems(thisLine, person, masterFile, root);
			}
		} catch (IOException e) {
			log.error("Unable to read master file " + e.getMessage());
		}
	}

	private void persistItems(String thisLine, Person person,
			String masterFile, String root) throws IOException {
		String[] fields = StringUtils.split(thisLine, "|");
		if (fields.length != 6) {
			log.debug("Error parsing line " + thisLine);
			return;
		}
		String relative = dump.getItemsPath().getFile().getName()
				+ "/merchants/" + person.identifier() + "/sell/";

		Photo icon = new Photo();
		icon.setPath(dump.makeIcon(relative, root, fields[0]));

		Photo thumb = new Photo();
		thumb.setPath(dump.makeThumbnail(relative, root, fields[0]));

		Photo glimpse = new Photo();
		glimpse.setPath(dump.makeGlimpse(relative, root, fields[0]));

		VendorItem item = new VendorItem();
		item.setSerialNumber(fields[1]);
		item.setPrice(Float.parseFloat(fields[2]));
		item.setDescription(StringEscapeUtils.escapeHtml(fields[3]));
		item.setItemName(fields[4]);
		item.setIcon(icon);
		item.setThumbnail(thumb);
		item.setGlimpse(glimpse);
		item.setPerson(person);
		item.setType(ItemType.VENDOR);

		// association table
		ItemCategory itemCategory = new ItemCategory();
		itemCategory.setCategory(CategoryType.SELLING);
		itemCategory.setDescription(fields[5]);
		itemCategory.setItem(item);
		itemCategory.setPerson(person);

		item.getItemCategories().add(itemCategory);

		ItemVisibilityDomain ivd = new ItemVisibilityDomain(item, person
				.getDefaultVisibility());
		item.getItemVisibilities().add(ivd);

		person.getItems().add(item);

		// vendorItemService.save(item);

		News itemnews = new News();
		itemnews.setAction(Action.SELLING);
		itemnews.setPerson(person);
		itemnews.setWorld(Action.SELLING.isWorld());
		itemnews.setMessage(Action.SELLING.convert(person, item));
		log.debug("Message : " + itemnews.getMessage());

		// newsService.save(itemnews);

		NewsVisibilityDomain nvd = new NewsVisibilityDomain(itemnews, person
				.getDefaultVisibility());
		// newsVisibilityService.save(nvd);
		itemnews.getNewsVisibility().add(nvd);

		person.getNews().add(itemnews);

		// newsService.save(itemnews);

		personService.update(person);

		// itemVisibilityService.save(ivd);
		// item.addItemCategory(itemCategory);

		// filename, serialNumber, private, description, itemName,
		// categorydescription
	}

	public void setVendorItemService(VendorItemService vendorItemService) {
		this.vendorItemService = vendorItemService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public void setVendorService(VendorService vendorService) {
		this.vendorService = vendorService;
	}

	public void setPhotoService(PhotoService photoService) {
		this.photoService = photoService;
	}

	public void setItemCategoryService(ItemCategoryService itemCategoryService) {
		this.itemCategoryService = itemCategoryService;
	}

	public void setPhotoCategoryService(
			PhotoCategoryService photoCategoryService) {
		this.photoCategoryService = photoCategoryService;
	}

	public void setNewsVisibilityService(
			NewsVisibilityDomainService newsVisibilityService) {
		this.newsVisibilityService = newsVisibilityService;
	}

	public void setItemVisibilityService(
			ItemVisibilityDomainService itemVisibilityService) {
		this.itemVisibilityService = itemVisibilityService;
	}

	public void setVisibilityService(VisibilityDomainService visibilityService) {
		this.visibilityService = visibilityService;
	}

}
