# PasswordHintController

```java
package com.myshopnshare.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import com.myshopnshare.model.User;
import com.myshopnshare.service.MailEngine;
import com.myshopnshare.service.UserManager;
import com.myshopnshare.webapp.util.RequestUtil;

/**
 * Simple class to retrieve and send a password hint to users.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class PasswordHintController implements Controller {
    private final Log log = LogFactory.getLog(PasswordHintController.class);
    private UserManager userManager = null;
    private MessageSource messageSource = null;
    protected MailEngine mailEngine = null;
    protected SimpleMailMessage message = null;
    
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void setMailEngine(MailEngine mailEngine) {
        this.mailEngine = mailEngine;
    }
    
    public void setMessage(SimpleMailMessage message) {
        this.message = message;
    }
    
    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
        log.debug("entering 'handleRequest' method...");

        String username = request.getParameter("username");
        MessageSourceAccessor text = new MessageSourceAccessor(messageSource, request.getLocale());

        // ensure that the username has been sent
        if (username == null) {
            log.warn("Username not specified, notifying user that it's a required field.");
            request.setAttribute("error", text.getMessage("errors.required", text.getMessage("user.username")));
            return new ModelAndView("login");
        }

        log.debug("Processing Password Hint...");

        // look up the user's information
        try {
            User user = userManager.getUserByUsername(username);

            StringBuffer msg = new StringBuffer();
            //msg.append("Your password hint is: ").append(user.getPasswordHint());
            msg.append("\n\nLogin at: ").append(RequestUtil.getAppURL(request));

            message.setTo(user.getEmail());
            String subject = '[' + text.getMessage("webapp.name") + "] " + 
                             text.getMessage("user.passwordHint");
            message.setSubject(subject);
            message.setText(msg.toString());
            mailEngine.send(message);

            saveMessage(request, text.getMessage("login.passwordHint.sent", new Object[] { username, user.getEmail() }));
        } catch (UsernameNotFoundException e) {
            log.warn(e.getMessage());
            saveError(request, text.getMessage("login.passwordHint.error", new Object[] { username }));
        } catch (MailException me) {
            saveError(request, me.getCause().getLocalizedMessage());
        }

        return new ModelAndView(new RedirectView(request.getContextPath()));
    }

    @SuppressWarnings("unchecked")
    public void saveError(HttpServletRequest request, String error) {
        List errors = (List) request.getSession().getAttribute("errors");
        if (errors == null) {
            errors = new ArrayList();
        }
        errors.add(error);
        request.getSession().setAttribute("errors", errors);
    }

    // this method is also in BaseForm Controller
    @SuppressWarnings("unchecked")
    public void saveMessage(HttpServletRequest request, String msg) {
        List messages = (List) request.getSession().getAttribute(BaseFormController.MESSAGES_KEY);
        if (messages == null) {
            messages = new ArrayList();
        }
        messages.add(msg);
        request.getSession().setAttribute(BaseFormController.MESSAGES_KEY, messages);
    }
}
```
