# NewsServiceImpl

```java
package com.myshopnshare.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.NewsDAO;
import com.myshopnshare.core.domain.News;
import com.myshopnshare.core.domain.Person;

@Service("newsService")
@Transactional
public class NewsServiceImpl extends GenericServiceImpl<News, Long>
		implements NewsService {
	
	private NewsDAO newsDAO;

	@Autowired
	public NewsServiceImpl(NewsDAO genericDao) {
		super(genericDao);
		this.newsDAO = genericDao;
	}
	
	public News getNewsForPerson(Person person, Long id) {
		return newsDAO.getNewsForPerson(person, id);
	}

	public List<News> findOwnNews(Person person, int start) {
		return newsDAO.findOwnNews(person, start);
	}

	public List<News> findWorldNews(Person person, int start) {
		return newsDAO.findWorldNews(person, start);
	}

	public List<News> findOneFriendsNews(Person friend, Person person, int start) {
		return newsDAO.findOneFriendsNews(friend, person, start);
	}

	public List<News> findAllFriendsNews(Person person, int start) {
		return newsDAO.findAllFriendsNews(person, start);
	}


	public Long findAllFriendsNewsCount(Person person) {
		return newsDAO.findAllFriendsNewsCount(person);
	}


	public Long findOneFriendsNewsCount(Person friend, Person person) {
		return newsDAO.findOneFriendsNewsCount(friend, person);
	}

	public Long findOwnNewsCount(Person person) {
		return newsDAO.findOwnNewsCount(person);
	}

	public Long findWorldNewsCount(Person person) {
		return newsDAO.findWorldNewsCount(person);
	}

	public List<News> findWorldNews(int start) {
		return this.newsDAO.findWorldNews(start);
	}

	public Long findWorldNewsCount() {
		return this.newsDAO.findWorldNewsCount();
	}
}
```
