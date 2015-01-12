package com.myshopnshare.webapp.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.Face;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.Photo;
import com.myshopnshare.core.service.EmailService;
import com.myshopnshare.core.service.FaceService;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.model.User;
import com.myshopnshare.utils.Dump;
import com.myshopnshare.utils.EmailUtil;

public class FaceController extends MultiActionController {
	private final static Logger log = Logger.getLogger(FaceController.class);

	@Autowired
	private PersonService personService;
	
	@Autowired
	private FaceService faceService;

	@Autowired
	private EmailService emailService;

	private Resource resource;
	@Autowired
	private Dump dump;

	public void setDump(Dump dump) {
		this.dump = dump;
	}

	private Person fetchPerson() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User user = (User) principal;

		return personService.findPersonByUsername(user.getUsername());
	}

	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map map = new HashMap();
		Person person = fetchPerson();

		map.put("faces", person.getActiveFaces());
		return new ModelAndView("profile/face/jsonFaces", map);
	}

	public ModelAndView flag(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map map = new HashMap();
		Person person = fetchPerson();

		String faceId = request.getParameter("faceId");
		if (StringUtils.trimToNull(faceId) != null) {
			Face face = faceService.get(Long.parseLong(faceId));
			face.flagged();
			if (face.violator()) {
				face.setActive(false);

				EmailUtil.INSTANCE
						.sendMail(
								"Delete Face Profile",
								"Your face profile has been deleted due to too many complaints from users.",
								"sleekswap@gmail.com",
								emailService.getPrimaryEmailForPerson(
										face.getPerson()).getEmail());
			}
			faceService.update(face);
		}
		return new ModelAndView("common/blank");
	}
	/*
	 * 		<form method="post" action="/admin/upload" enctype="multipart/form-data">
		    <fieldset>
		        <legend>Upload Fields</legend> 
		        <p>
		            <label>File</label>
		            <input type="file" name="fileData"/>
		        </p>
		
		        <p>
		            <input type="submit" />
		        </p>
		    </fieldset>
		</form>     
	 */
	public ModelAndView upload(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("uploading face file ");
		Map map = new HashMap();
		Person person = fetchPerson();

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		CommonsMultipartFile fileData = (CommonsMultipartFile) multipartRequest.getFile("fileToUpload");

		String path = dump.getFacesPath().getFile().getAbsolutePath()
				+ File.separator + person.identifier() + File.separator;

		// String saveFile = dump.dump(in, formDataLength, lastIndex,
		// boundary, path);
		
		String saveFile = dump.dump(fileData, "", path);

		String relativepath = dump.getFacesPath().getFile().getName()
				+ "/" + person.identifier() + "/";

		Photo mini = new Photo();
		mini.setPath(dump.makeMini(relativepath, path, saveFile));

		Photo icon = new Photo();
		icon.setPath(dump.makeIcon(relativepath, path, saveFile));

		Photo thumb = new Photo();
		thumb.setPath(dump.makeThumbnail(relativepath, path, saveFile));

		Photo glimpse = new Photo();
		glimpse.setPath(dump.makeGlimpse(relativepath, path, saveFile));

		Face face = new Face();
		face.setMini(mini);
		face.setIcon(icon);
		face.setThumbnail(thumb);
		face.setGlimpse(glimpse);

		face.setPerson(person);
		face.setType(person.getUserType());

		face.setDefaultFace(true);
		
		faceService.saveDefaultFace(face, person);
		
		//person.getFaces().add(face);
		// faceService.save(face);
		//personService.update(person);

		map.put("face", face);
		return new ModelAndView("profile/face/jsonFace", map);
	}

	/*
	public ModelAndView upload(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map map = new HashMap();
		Person person = fetchPerson();

		String contentType = request.getContentType();
		if ((contentType != null)
				&& (contentType.indexOf("multipart/form-data") >= 0)) {

			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List items = null;
			try {
				items = upload.parseRequest(request);
				int count = 0;
				Iterator itr = items.iterator();
				while (itr.hasNext()) {
					FileItem fi = (FileItem) itr.next();
					if (fi.isFormField()) {
						log.debug("fieldName: " + fi.getFieldName());
					} else {
						count++;
					}
				}



				String path = dump.getFacesPath().getFile().getAbsolutePath()
						+ File.separator + person.identifier() + File.separator;

				// String saveFile = dump.dump(in, formDataLength, lastIndex,
				// boundary, path);

				String saveFile = dump.dump(items, "", path);

				String relativepath = dump.getFacesPath().getFile().getName()
						+ "/" + person.identifier() + "/";

				Photo mini = new Photo();
				mini.setPath(dump.makeMini(relativepath, path, saveFile));

				Photo icon = new Photo();
				icon.setPath(dump.makeIcon(relativepath, path, saveFile));

				Photo thumb = new Photo();
				thumb.setPath(dump.makeThumbnail(relativepath, path, saveFile));

				Photo glimpse = new Photo();
				glimpse.setPath(dump.makeGlimpse(relativepath, path, saveFile));

				Face face = new Face();
				face.setMini(mini);
				face.setIcon(icon);
				face.setThumbnail(thumb);
				face.setGlimpse(glimpse);

				face.setPerson(person);
				face.setType(person.getUserType());

				face.setDefaultFace(true);

				person.getFaces().add(face);
				// faceService.save(face);
				personService.update(person);

				map.put("face", face);
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
		}
		return new ModelAndView("profile/face/jsonFace", map);
	}
*/
	public ModelAndView edit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			Person person = fetchPerson();
			String recordId = request.getParameter("id");
			String caption = request.getParameter("caption");
			String defaultFace = request.getParameter("defaultFace");

			Face face = faceService.getFaceForPerson(person,
					Long.parseLong(recordId));
			face.setCaption(caption);
			if (StringUtils.trimToNull(defaultFace) == null) {
				face.setDefaultFace(true);
			}

			faceService.saveOrUpdate(face);
			request.setAttribute("face", face);
			return new ModelAndView("profile/face/jsonFace");
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ModelAndView("profile/face/jsonFace");
		}
	}

	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			Person person = fetchPerson();
			String recordId = request.getParameter("recordId");
			Face face = faceService.getFaceForPerson(person,
					Long.parseLong(recordId));
			if (face.isApplication()) {
				// Do not allow deleting application face although this is
				// not really deleting the application Photos if it does happen.
				// We just want to make sure each user has their own application
				// face always
				return new ModelAndView("profile/face/jsonFace");
			}
			face.setActive(false);
			face.setDefaultFace(false);
			faceService.update(face);
			Map<String, Object> map = new HashMap<String, Object>();

			return new ModelAndView("profile/face/jsonFace", map);
		} catch (Exception e) {
			return new ModelAndView("profile/face/jsonFace");
		}
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void setFaceService(FaceService faceService) {
		this.faceService = faceService;
	}

}
