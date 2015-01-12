package com.myshopnshare.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.NewsCommentDAO;
import com.myshopnshare.core.domain.NewsComment;

@Service("newsCommentService")
@Transactional
public class NewsCommentServiceImpl extends
		GenericServiceImpl<NewsComment, Long> implements
		NewsCommentService {
	
	private NewsCommentDAO newsCommentDAO;

	@Autowired
	public NewsCommentServiceImpl(NewsCommentDAO genericDao) {
		super(genericDao);
		this.newsCommentDAO = genericDao;
	}
}
