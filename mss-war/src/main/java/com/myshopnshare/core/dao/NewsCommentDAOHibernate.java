package com.myshopnshare.core.dao;

import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.NewsComment;
@Repository("newsCommentDAO")
public class NewsCommentDAOHibernate extends
		GenericDAOHibernate<NewsComment, Long> implements NewsCommentDAO {

}
