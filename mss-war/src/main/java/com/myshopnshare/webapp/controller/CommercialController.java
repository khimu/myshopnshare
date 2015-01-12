package com.myshopnshare.webapp.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.Action;
import com.myshopnshare.core.domain.Commercial;
import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.domain.News;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.service.CommercialService;
import com.myshopnshare.core.service.EmailService;
import com.myshopnshare.core.service.ItemService;
import com.myshopnshare.core.service.NewsService;
import com.myshopnshare.core.service.NewsVisibilityDomainService;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.model.User;
import com.myshopnshare.utils.Dump;
import com.myshopnshare.utils.ImageFileFilter;

public class CommercialController extends MultiActionController {
	private static final Log log = LogFactory
			.getLog(CommercialController.class);

	@Autowired
	private PersonService personService;
	
	@Autowired
	private CommercialService commercialService;
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private NewsVisibilityDomainService newsVisibilityService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private Dump dump;

	private Person fetchPerson() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User user = (User) principal;

		return personService.findPersonByUsername(user.getUsername());
	}

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("commercials/commercials");
	}

	public ModelAndView all(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String itemId = request.getParameter("itemId");
		request.setAttribute("commercials", emailService.findAllActiveEmailsFor(person));
		return new ModelAndView("account/email/jsonEmails");
	}

	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {

			// return new ModelAndView(new JSONView(), map);
			return new ModelAndView("account/email/jsonEmail");
		} catch (Exception e) {
			log.equals(e.getMessage());
			return new ModelAndView("secure/error");
		}
	}

	public ModelAndView edit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {

			return new ModelAndView("account/email/jsonEmail");
		} catch (Exception e) {
			log.equals(e.getMessage());
			return new ModelAndView("secure/error");
		}
	}

	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			return new ModelAndView("account/email/jsonEmail");
		} catch (Exception e) {
			log.equals(e.getMessage());
			return new ModelAndView("secure/error");
		}
	}

	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			Person person = fetchPerson();
			return new ModelAndView("account/email/jsonEmail");
		} catch (Exception e) {
			log.equals(e.getMessage());
			return new ModelAndView("secure/error");
		}
	}

	public ModelAndView bulk(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String itemId = request.getParameter("itemId");
		Item product = itemService.get(Long
				.parseLong(itemId));
		// This is for uploading the pictures

		String root = dump.getVideosPath().getFile().getAbsolutePath()
				+ File.separator + person.identifier() + File.separator
				+ "users" + File.separator + "commercials" + File.separator;

		String relative = dump.getVideosPath().getFile().getName() + "/"
				+ person.identifier() + "/users/commercials/";

		String contentType = request.getContentType();
		if ((contentType != null)
				&& (contentType.indexOf("multipart/form-data") >= 0)) {

			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List items = null;

			List<String> paths = new ArrayList<String>();
			try {
				items = upload.parseRequest(request);
				int count = 0;
				Iterator itr = items.iterator();
				while (itr.hasNext()) {
					FileItem item = (FileItem) itr.next();
					if (item.isFormField()) {
						if (item.getFieldName().equals("itemId")) {
							itemId = item.getString();
						}
					} else {
						String itemName = item.getName();
						File itemFile = new File(itemName);
						itemName = itemFile.getName();

						dump.make(root, itemName);
						try {
							item.write(new File(root + itemName));

							
							Commercial commercial = new Commercial();
							commercial.setFileFormat(ImageFileFilter
									.getExtension(itemName));
							commercial.setFilePath(root + itemName);
							commercial.setItem(product);

							product.getCommercials().add(commercial);
							itemService.update(product);

						} catch (Exception e) {
							log.error("Unable to save IMAGE.");
						}
						log.debug("File saved as " + root + itemName);
					}
				}

				News news = new News();
				news.setAction(Action.COMMERCIAL);
				news.setWorld(Action.COMMERCIAL.isWorld());
				news.setPerson(person);
				news.setMessage(Action.COMMERCIAL.convert(person, count));
				log.debug("Message : " + news.getMessage());
				
				person.getNewsPermission(news);
				person.getNews().add(news);
				
				newsService.save(news);
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			request.setAttribute("commercials", product.getCommercials());
		}
		
		return new ModelAndView("commercials/jsonCommercials");
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void setCommercialService(CommercialService commercialService) {
		this.commercialService = commercialService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public void setNewsVisibilityService(
			NewsVisibilityDomainService newsVisibilityService) {
		this.newsVisibilityService = newsVisibilityService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public void setDump(Dump dump) {
		this.dump = dump;
	}

}
