package com.myshopnshare.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.NewsVisibilityDomainDAO;
import com.myshopnshare.core.domain.News;
import com.myshopnshare.core.domain.NewsVisibilityDomain;
import com.myshopnshare.core.domain.Person;

@Service("newsVisibilityDomainService")
@Transactional
public class NewsVisibilityDomainServiceImpl extends GenericServiceImpl<NewsVisibilityDomain, Long> implements NewsVisibilityDomainService{
	private NewsVisibilityDomainDAO newsVisibilityDomainDAO;

	@Autowired
	public NewsVisibilityDomainServiceImpl(NewsVisibilityDomainDAO genericDao) {
		super(genericDao);
		this.newsVisibilityDomainDAO = genericDao;
	}
	
	/* Call this for reading world news */
	public List<News> findAllVisibleNewsFor(Person person, int start){
		return ((NewsVisibilityDomainDAO)this.dao).findAllVisibleNewsFor(person, start);
	}
	
	// Extra step, make sure that person is friend's with item owner
	public List<News> findAllFriendVisibleNewsFor(Person person, int start){
		return ((NewsVisibilityDomainDAO)this.dao).findAllFriendVisibleNewsFor(person, start);
	}


	public List<News> findFriendsVisibleNews(Person friend, Person person,
			int start) {
		// TODO Auto-generated method stub
		return null;
	}
}
