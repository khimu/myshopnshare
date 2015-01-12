package com.myshopnshare.webapp.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.myshopnshare.core.domain.News;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.service.FriendService;
import com.myshopnshare.core.service.NewsService;
import com.myshopnshare.core.service.NewsVisibilityDomainService;
import com.myshopnshare.service.UserManager;

public class NewsController extends BaseController {
	private static Logger logger = Logger.getLogger(NewsController.class);

	@Autowired
	private UserManager userService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private FriendService friendService;
	@Autowired
	private NewsVisibilityDomainService newsVisibilityDomainService;

	/*
	 * Increment and decrement of prev and next are done by javascript
	 */
	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// String param = getServletContext().getInitParameter("WEB_ROOT") ;
		Person person = setup(request);
		String startStr = request.getParameter("start");
		int start = 0;
		if (StringUtils.trimToNull(startStr) != null) {
			start = Integer.parseInt(request.getParameter("start"));
		}

		String category = request.getParameter("category");

		if (StringUtils.trimToNull(category) == null) {
			request.setAttribute("total", newsService
					.findWorldNewsCount(person));
		} else if (category.equals("world")) {
			request.setAttribute("total", newsService
					.findWorldNewsCount(person));
		} else if (category.equals("friends")) {
			request.setAttribute("total", newsService
					.findAllFriendsNewsCount(person));
		} else if (category.equals("self")) {
			request.setAttribute("total", newsService.findOwnNewsCount(person));
		} else {
			request.setAttribute("total", newsService
					.findWorldNewsCount(person));
		}
		return new ModelAndView("news/news");
	}

	public ModelAndView showAll(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// String param = getServletContext().getInitParameter("WEB_ROOT") ;
		Person person = fetchPerson();

		String startStr = request.getParameter("start");
		int start = 0;
		if (StringUtils.trimToNull(startStr) != null) {
			start = Integer.parseInt(request.getParameter("start"));
		}

		String category = request.getParameter("category");
		if (StringUtils.trimToNull(category) == null) {
			request.setAttribute("total", newsService
					.findWorldNewsCount(person));
			request.setAttribute("newzs", newsService.findWorldNews(person, 0));
		} else if (category.equals("world")) {

		} else if (category.equals("friends")) {
			request.setAttribute("total", newsService
					.findAllFriendsNewsCount(person));
			request.setAttribute("newzs", newsService.findAllFriendsNews(
					person, 0));
		} else if (category.equals("self")) {
			request.setAttribute("total", newsService.findOwnNewsCount(person));
			request.setAttribute("newzs", newsService.findOwnNews(person, 0));
		}
		return new ModelAndView("news/all");
	}

	public ModelAndView full(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = setup(request);
		String startStr = request.getParameter("start");
		int start = 0;
		if (StringUtils.trimToNull(startStr) != null) {
			start = Integer.parseInt(request.getParameter("start"));
		}

		String category = request.getParameter("category");
		if (StringUtils.trimToNull(category) == null) {
			request.setAttribute("total", newsService
					.findWorldNewsCount(person));
			request.setAttribute("newzs", newsService.findWorldNews(person,
					start));
		} else if (category.equals("world")) {
			request.setAttribute("total", newsService
					.findWorldNewsCount(person));
			request.setAttribute("newzs", newsService.findWorldNews(person,
					start));
		} else if (category.equals("friends")) {
			request.setAttribute("total", newsService
					.findAllFriendsNewsCount(person));
			request.setAttribute("newzs", newsService.findAllFriendsNews(
					person, start));
		} else if (category.equals("self")) {
			request.setAttribute("total", newsService.findOwnNewsCount(person));
			request.setAttribute("newzs", newsService
					.findOwnNews(person, start));
		}
		return new ModelAndView("news/all");
	}

	public ModelAndView page(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = setup(request);
		String startStr = request.getParameter("start");
		int start = 0;
		if (StringUtils.trimToNull(startStr) != null) {
			start = Integer.parseInt(request.getParameter("start"));
		}

		String category = request.getParameter("category");
		if (StringUtils.trimToNull(category) == null) {
			request.setAttribute("total", newsService
					.findWorldNewsCount(person));
			request.setAttribute("newzs", newsService.findWorldNews(person,
					start));
		} else if (category.equals("world")) {
			request.setAttribute("total", newsService
					.findWorldNewsCount(person));
			request.setAttribute("newzs", newsService.findWorldNews(person,
					start));
		} else if (category.equals("friends")) {
			request.setAttribute("total", newsService
					.findAllFriendsNewsCount(person));
			request.setAttribute("newzs", newsService.findAllFriendsNews(
					person, start));
		} else if (category.equals("self")) {
			request.setAttribute("total", newsService.findOwnNewsCount(person));
			request.setAttribute("newzs", newsService
					.findOwnNews(person, start));
		}
		return new ModelAndView("news/allreload");
	}

	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String newsId = request.getParameter("newsId");
		if (StringUtils.trimToNull(newsId) != null) {
			News news = newsService.getNewsForPerson(person, Long
					.parseLong(newsId));
			// news.setActive(false);
			newsService.delete(news);
		}
		return new ModelAndView("news/jsonNews");
	}

	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		String newsId = request.getParameter("newsId");
		if (StringUtils.trimToNull(newsId) != null) {
			News news = newsService.getNewsForPerson(person, Long
					.parseLong(newsId));
			request.setAttribute("person", person);
			request.setAttribute("news", news);
		}
		return new ModelAndView("news/singlenews");
	}

	public ModelAndView all(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String personId = request.getParameter("personId");

		String startStr = request.getParameter("start");
		int start = 0;
		if (StringUtils.trimToNull(startStr) != null) {
			start = Integer.parseInt(request.getParameter("start"));
		}

		List<News> newzz = null;

		Person person = fetchPerson();
		if (StringUtils.trimToNull(personId) == null
				|| person.getId().equals(Long.parseLong(personId))) {
			/** Get news for person logged in **/
			request.setAttribute("total", newsService
					.findWorldNewsCount(person));
			newzz = newsService.findWorldNews(person, start);
			request.setAttribute("newzz", newzz);
			return new ModelAndView("news/jsonNews");
		}

		Person friend = personService.get(Long.parseLong(personId));
		if (!person.isFriends(personId)) {
			/** no permission return empty **/
			return new ModelAndView("news/jsonNews");
		}

		// String prevOrNext = request.getParameter("prevOrNext");

		/** Get friend's news **/
		// if (StringUtils.trimToNull(start) == null) {
		request.setAttribute("total", newsService.findOneFriendsNewsCount(
				friend, person));
		newzz = newsService.findOneFriendsNews(friend, person, start);
		request.setAttribute("start", start);
		/*
		 * } else { int thestart = Integer.parseInt(start); newzz =
		 * newsService.findOneFriendsNews(friend, person, thestart); if
		 * (prevOrNext != null && prevOrNext.equals("prev")) { thestart =
		 * thestart - 20 > 0 ? thestart - 20 : 0; } else { thestart = thestart +
		 * 20; } request.setAttribute("start", start); }
		 */
		request.setAttribute("newzz", newzz);
		bindObject(request, newzz);
		return new ModelAndView("news/jsonNews");
	}

	public ModelAndView world(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String personId = request.getParameter("personId");
		List<News> newzz = null;

		String startStr = request.getParameter("start");
		int start = 0;
		if (StringUtils.trimToNull(startStr) != null) {
			start = Integer.parseInt(request.getParameter("start"));
		}

		Person person = fetchPerson();
		if (StringUtils.trimToNull(personId) == null
				|| person.getId().equals(Long.parseLong(personId))) {
			/** Get news for person logged in **/
			request.setAttribute("total", newsService
					.findWorldNewsCount(person));
			newzz = newsService.findWorldNews(person, start);
			request.setAttribute("newzz", newzz);
			return new ModelAndView("news/jsonNews");
		}

		Person friend = personService.get(Long.parseLong(personId));
		if (!person.isFriends(personId)) {
			/** no permission return empty **/
			return new ModelAndView("news/jsonNews");
		}
		// String start = request.getParameter("start");
		// String prevOrNext = request.getParameter("prevOrNext");

		// if (StringUtils.trimToNull(start) == null) {
		request.setAttribute("total", newsService.findOneFriendsNewsCount(
				friend, person));
		newzz = newsService.findOneFriendsNews(friend, person, start);
		// request.setAttribute("start", start);

		// request.setAttribute("start", 20);
		/*
		 * } else { int thestart = Integer.parseInt(start); newzz =
		 * newsService.findOneFriendsNews(friend, person, 0); if (prevOrNext !=
		 * null && prevOrNext.equals("prev")) { thestart = thestart - 20 > 0 ?
		 * thestart - 20 : 0; } else { thestart = thestart + 20; }
		 * request.setAttribute("start", thestart); }
		 */
		request.setAttribute("newzz", newzz);
		// bindObject(request, newzz);
		return new ModelAndView("news/jsonNews");
	}

	public ModelAndView friends(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<News> newzz = null;

		String startStr = request.getParameter("start");
		int start = 0;
		if (StringUtils.trimToNull(startStr) != null) {
			start = Integer.parseInt(request.getParameter("start"));
		}

		Person person = fetchPerson();

		request.setAttribute("total", newsService
				.findAllFriendsNewsCount(person));

		newzz = newsService.findAllFriendsNews(person, 0);

		request.setAttribute("newzz", newzz);
		// bindObject(request, newzz);
		return new ModelAndView("news/jsonNews");
	}

	public ModelAndView self(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String personId = request.getParameter("personId");
		List<News> newzz = null;

		String startStr = request.getParameter("start");
		int start = 0;
		if (StringUtils.trimToNull(startStr) != null) {
			start = Integer.parseInt(request.getParameter("start"));
		}

		Person person = fetchPerson();
		if (StringUtils.trimToNull(personId) == null
				|| person.getId().equals(Long.parseLong(personId))) {
			/** Get news for person logged in **/
			request.setAttribute("total", newsService.findOwnNewsCount(person));
			newzz = newsService.findOwnNews(person, 0);
			request.setAttribute("newzz", newzz);
			return new ModelAndView("news/jsonNews");
		}

		Person friend = personService.get(Long.parseLong(personId));
		if (!person.isFriends(personId)) {
			/** no permission return empty **/
			return new ModelAndView("news/jsonNews");
		}

		// String start = request.getParameter("start");
		// String prevOrNext = request.getParameter("prevOrNext");

		// if (StringUtils.trimToNull(start) == null) {
		request.setAttribute("total", newsService.findOneFriendsNewsCount(
				friend, person));
		newzz = newsService.findOneFriendsNews(friend, person, start);
		//request.setAttribute("start", start);
		/*
		 * } else { int thestart = Integer.parseInt(start); newzz =
		 * newsService.findOneFriendsNews(friend, person, thestart); if
		 * (prevOrNext != null && prevOrNext.equals("prev")) { thestart =
		 * thestart - 20 > 0 ? thestart - 20 : 0; } else { thestart = thestart +
		 * 20; } request.setAttribute("start", thestart); }
		 */
		request.setAttribute("newzz", newzz);
		bindObject(request, newzz);
		return new ModelAndView("news/jsonNews");
	}

	public ModelAndView afriends(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Person person = fetchPerson();
		List<News> newzz = null;

		String startStr = request.getParameter("start");
		int start = 0;
		if (StringUtils.trimToNull(startStr) != null) {
			start = Integer.parseInt(request.getParameter("start"));
		}

		String friendId = request.getParameter("friendId");
		if (StringUtils.trimToNull(friendId) == null
				|| person.getId().equals(Long.parseLong(friendId))) {
			/** Get news for person logged in **/

			request.setAttribute("total", newsService.findOwnNewsCount(person));
			newzz = newsService.findOwnNews(person, start);
			request.setAttribute("newzz", newzz);
			return new ModelAndView("news/jsonNews");
		}

		Person friend = personService.get(Long.parseLong(friendId));
		if (!person.isFriends(friendId)) {
			request.setAttribute("person", person);
			request.setAttribute("results", Arrays.asList(friend));
			return new ModelAndView("home/noPermission");
		}

		// String start = request.getParameter("start");
		// String prevOrNext = request.getParameter("prevOrNext");

		// if (StringUtils.trimToNull(start) == null) {
		request.setAttribute("total", newsService.findOneFriendsNewsCount(
				friend, person));
		newzz = newsService.findOneFriendsNews(friend, person, start);
		//request.setAttribute("start", start);
		/*
		 * } else { int thestart = Integer.parseInt(start); newzz =
		 * newsService.findOneFriendsNews(friend, person, thestart); if
		 * (prevOrNext != null && prevOrNext.equals("prev")) { thestart =
		 * thestart - 20 > 0 ? thestart - 20 : 0; } else { thestart = thestart +
		 * 20; } request.setAttribute("start", thestart); }
		 */
		request.setAttribute("newzz", newzz);
		return new ModelAndView("news/jsonNews");
	}

}
