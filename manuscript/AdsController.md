# AdsController

```java
package com.myshopnshare.webapp.controller;

import java.io.File;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.Ads;
import com.myshopnshare.core.domain.AdsTag;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.Photo;
import com.myshopnshare.core.enums.UserType;
import com.myshopnshare.core.service.AdsService;
import com.myshopnshare.core.service.AdsTagService;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.model.User;
import com.myshopnshare.utils.Dump;

public class AdsController extends MultiActionController {
	private static transient final Log log = LogFactory
			.getLog(AdsController.class);

	@Autowired
	private AdsService adsService;
	@Autowired
	private AdsTagService adsTagService;
	@Autowired
	private PersonService personService;
	@Autowired
	private Dump dump;

	/** Use this later to determine what ads the user's prefer **/
	public Person fetchPerson() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		User user = (User) principal;
		Person person = personService.findPersonByUsername(user.getUsername());
		return person;
	}

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute("adss", adsService.getAll());
		return new ModelAndView("ads/adsitems");
	}

	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute("adss", adsService.getAll());
		return new ModelAndView("ads/jsonAdss");
	}

	public ModelAndView click(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String recordId = request.getParameter("recordId");
		if (StringUtils.trimToNull(recordId) != null) {
			Ads ads = adsService.get(Long.parseLong(recordId));
			ads.incrementClick();
			adsService.update(ads);
			request.setAttribute("ads", ads);
			return new ModelAndView("redirect:" + ads.getSponsorSite());
		}
		// return new ModelAndView("ads/adsRedirect");
		return new ModelAndView("home/homeLayout");
	}

	public ModelAndView upload(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map map = new HashMap();
		Person person = fetchPerson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		CommonsMultipartFile fileData = (CommonsMultipartFile) multipartRequest.getFile("fileToUpload");

		String description = request.getParameter("description");
		String filename = request.getParameter("filename");
		String price = request.getParameter("price");
		String tagStr = request.getParameter("tags");
		String companyname = request.getParameter("companyname");
		String sponsorSite = request.getParameter("sponsorSite");
		String ageRange = request.getParameter("ageRange");


				String root = dump.getAdsPath().getFile().getAbsolutePath()
						+ File.separator + person.identifier() + File.separator
						+ companyname + File.separator;

				String relativeroot = dump.getAdsPath().getFile().getName()
						+ "/" + person.identifier() + "/" + companyname + "/";

				// String saveFile = Dump.dump(in, formDataLength, lastIndex,
				// boundary, root);
				
				String saveFile = dump.dump(fileData, "", root);

				Photo icon = new Photo();
				icon.setPath(Dump.makeIcon(relativeroot, root, saveFile));

				Photo thumb = new Photo();
				thumb.setPath(Dump.makeThumbnail(relativeroot, root, saveFile));

				Photo glimpse = new Photo();
				glimpse.setPath(Dump.makeGlimpse(relativeroot, root, saveFile));

				try {
					Ads ads = new Ads();
					ads.setCompanyName(companyname);
					ads.setDescription(StringEscapeUtils
							.escapeHtml(description));
					ads.setSponsorSite(sponsorSite);
					ads.setIcon(icon);
					ads.setThumbnail(thumb);
					// ads.setOriginal(original);
					ads.setGlimpse(glimpse);
					ads.setPrice(price);
					ads.setFileName(filename);
					ads.setSponsor(person);

					String[] tags = StringUtils.split(tagStr, ",");
					for (String tag : tags) {
						AdsTag t = new AdsTag();
						t.setTag(tag);
						t.setAds(ads);
						t.setDescription("advertisement");
						ads.getTags().add(t);
					}

					adsService.save(ads);
				} catch (Exception e) {
					log.error(e);
				}


		request.setAttribute("adss", adsService.getAll());

		return new ModelAndView("ads/jsonAds");
	}

	public ModelAndView old(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		if (person.getUserType() != UserType.MERCHANT) {
			return new ModelAndView("login/login");
		}

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		CommonsMultipartFile fileData = (CommonsMultipartFile) multipartRequest.getFile("fileToUpload");
		
		Map<String, String> params = new HashMap<String, String>();

			String root = dump.getAdsPath().getFile().getAbsolutePath()
					+ File.separator + person.identifier() + File.separator;
			Dump.dump(fileData, params.get("companyname"), root);

			root = root + params.get("companyname") + File.separator;

			String relative = dump.getAdsPath().getFile().getName() + "/"
					+ person.identifier() + "/" + params.get("companyname")
					+ "/";

			Photo icon = new Photo();
			icon.setPath(Dump.makeIcon(relative, root, params.get("filename")));

			Photo thumb = new Photo();
			thumb.setPath(Dump.makeThumbnail(relative, root, params
					.get("filename")));

			Photo glimpse = new Photo();
			glimpse.setPath(Dump.makeGlimpse(relative, root, params
					.get("filename")));

			Photo original = new Photo();
			original.setPath(root + params.get("filename"));

			try {
				Ads ads = new Ads();
				ads.setCompanyName(params.get("companyname"));
				ads.setDescription(StringEscapeUtils.escapeHtml(params
						.get("description")));
				ads.setSponsorSite(params.get("sponsorSite"));
				ads.setIcon(icon);
				ads.setThumbnail(thumb);
				ads.setGlimpse(glimpse);
				ads.setPrice(params.get("price"));
				ads.setFileName(params.get("filename"));
				ads.setSponsor(person);
				adsService.save(ads);

				String[] tags = StringUtils.split(params.get("tags"), ",");
				for (String tag : tags) {
					AdsTag t = new AdsTag();
					t.setTag(tag);
					t.setAds(ads);
					t.setDescription("advertisement");
					ads.getTags().add(t);
				}
			} catch (Exception e) {
				log.error(e);
			}

		request.setAttribute("adss", adsService.getAll());

		return new ModelAndView("ads/jsonAds");
	}

}
```
