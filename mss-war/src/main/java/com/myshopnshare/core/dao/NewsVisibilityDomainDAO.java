package com.myshopnshare.core.dao;

import java.util.List;

import com.myshopnshare.core.domain.News;
import com.myshopnshare.core.domain.NewsVisibilityDomain;
import com.myshopnshare.core.domain.Person;

public interface NewsVisibilityDomainDAO extends
		GenericDAO<NewsVisibilityDomain, Long> {
	public List<News> findAllVisibleNewsFor(Person person, int start);

	// Extra step, make sure that person is friend's with item owner
	public List<News> findAllFriendVisibleNewsFor(Person person, int start);
	
	public List<News> findFriendsVisibleNews(Person friend, Person person, int start);
}
