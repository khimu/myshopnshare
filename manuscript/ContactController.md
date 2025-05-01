# ContactController

```java
package com.myshopnshare.webapp.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.myshopnshare.core.domain.Contact;
import com.myshopnshare.core.service.ContactService;
import com.myshopnshare.utils.EmailUtil;

public class ContactController extends SimpleFormController {
	private static transient final Log log = LogFactory
			.getLog(ContactController.class);

	private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
	static {
		simpleDateFormat.applyPattern("MM/dd/yyyy");
	}

	private final static CustomDateEditor dateEditor = new CustomDateEditor(
			new SimpleDateFormat("yyyy-MM-dd"), true);

	@Autowired
	private ContactService contactService;

	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		Contact contact = (Contact) command;
		contactService.save(contact);
		request.setAttribute("msg", "Your message has been sent.  Please allow 2 to 3 days for a response.");

		try {
			EmailUtil.INSTANCE
					.sendMail(
							contact.getSubject(),
							contact.getMessage(),
							contact.getEmailAddress(), "sleekswap@gmail.com");

			return new ModelAndView(getSuccessView());
		} catch (Exception e) {
		}
		return new ModelAndView("contact");
	}

	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}

}
```
