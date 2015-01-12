package com.myshopnshare.core.service;

import com.myshopnshare.core.domain.Comment;
import com.myshopnshare.core.domain.Person;

public interface CommentsService extends GenericService<Comment, Long>{
	public Comment getCommentForPerson(Person person, Long id);
}
