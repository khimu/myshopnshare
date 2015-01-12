package com.myshopnshare.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.myshopnshare.core.domain.Action;
import com.myshopnshare.core.domain.Comment;
import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.domain.News;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.service.CommentsService;
import com.myshopnshare.core.service.ItemService;
import com.myshopnshare.core.service.NewsService;
import com.myshopnshare.core.service.NewsVisibilityDomainService;
import com.myshopnshare.core.service.PersonService;
import com.myshopnshare.model.User;
import com.myshopnshare.utils.EmailUtil;

public class ItemCommentsController extends MultiActionController {
	private final static Logger log = Logger.getLogger(ItemCommentsController.class);

	@Autowired
	private PersonService personService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private NewsVisibilityDomainService newsVisibilityService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private CommentsService commentsService;

	private Person fetchPerson() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		User user = (User) principal;

		return personService.findPersonByUsername(user.getUsername());
	}

	/** View the single item **/
	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String itemId = request.getParameter("itemId");
		if (StringUtils.trimToNull(itemId) != null) {
			Item item = itemService.get(Long.parseLong(itemId));
			request.setAttribute("comments", item.getComments());
		}
		return new ModelAndView("item/jsonItemComments");
	}
	
	public ModelAndView all(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String itemId = request.getParameter("itemId");
		if (StringUtils.trimToNull(itemId) != null) {
			Item item = itemService.get(Long.parseLong(itemId));
			request.setAttribute("comments", item.getComments());
		}
	
		return new ModelAndView("item/itemComments");
	}
	
	public ModelAndView readOnly(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String itemId = request.getParameter("itemId");
		if (StringUtils.trimToNull(itemId) != null) {
			Item item = itemService.get(Long.parseLong(itemId));
			request.setAttribute("comments", item.getComments());
		}
		return new ModelAndView("item/itemCommentsReadOnly");
	}

	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String commentId = request.getParameter("commentId");
		Person person = fetchPerson();
		if (StringUtils.trimToNull(commentId) != null) {
			commentsService.delete(commentsService.getCommentForPerson(person, Long.parseLong(commentId)));
		}
		return new ModelAndView("common/blank");
	}

	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String itemId = request.getParameter("itemId");
		Person person = fetchPerson();
		if (StringUtils.trimToNull(itemId) != null) {
			Item item = itemService.get(Long.parseLong(itemId));
			String comment = request.getParameter("comment");

			Comment comments = new Comment();
			comments.setMessage(comment);
			comments.setCommenter(person);
			comments.setItem(item);
			//commentsService.save(comments);
			item.getComments().add(comments);
			itemService.update(item);

			News itemnews = new News();
			itemnews.setAction(Action.COMMENT);
			itemnews.setWorld(Action.COMMENT.isWorld());
			itemnews.setPerson(person);
			itemnews.setMessage(Action.COMMENT.convert(person, item));
			log.debug("Message : " + itemnews.getMessage());
			person.getNewsPermission(itemnews);			
			newsService.save(itemnews);
			
			EmailUtil.INSTANCE.sendMail("Event comment",
					person.getAlias() + " commented on your item " + item.getItemName() , "sleekswap@gmail.com",
					item.getPerson().getAlias());	
			
			request.setAttribute("actionMessage",
					"You have successfully bought " + item.getItemName());
			request.setAttribute("comment", comment);
		}
		return new ModelAndView("item/jsonItemComment");
	}

	public ModelAndView comment(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String itemId = request.getParameter("itemId");
		Person person = fetchPerson();
		if (StringUtils.trimToNull(itemId) != null) {
			Item item = itemService.get(Long.parseLong(itemId));
			String comment = request.getParameter("comment");

			Comment comments = new Comment();
			comments.setMessage(comment);
			comments.setCommenter(person);
			comments.setItem(item);
			item.getComments().add(comments);
			itemService.update(item);

			News itemnews = new News();
			itemnews.setAction(Action.COMMENT);
			itemnews.setWorld(Action.COMMENT.isWorld());
			itemnews.setPerson(person);
			itemnews.setMessage(Action.COMMENT.convert(person, item));
			log.debug("Message : " + itemnews.getMessage());
			person.getNewsPermission(itemnews);
			newsService.save(itemnews);

			request.setAttribute("actionMessage",
					"You have successfully bought " + item.getItemName());
			request.setAttribute("comment", comments);
		}
		return new ModelAndView("item/jsonItemComment");
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

	public void setCommentsService(CommentsService commentsService) {
		this.commentsService = commentsService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

}
