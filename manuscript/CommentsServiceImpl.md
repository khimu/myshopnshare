# CommentsServiceImpl

```java
package com.myshopnshare.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.CommentsDAO;
import com.myshopnshare.core.domain.Comment;
import com.myshopnshare.core.domain.Person;

@Service("commentsService")
@Transactional
public class CommentsServiceImpl extends
		GenericServiceImpl<Comment, Long> implements CommentsService {

	private CommentsDAO commentsDAO;

	@Autowired
	public CommentsServiceImpl(CommentsDAO commentsDAO) {
		super(commentsDAO);
		this.commentsDAO = commentsDAO;
	}
	
	public Comment getCommentForPerson(Person person, Long id){
		return ((CommentsDAO)this.dao).getCommentForPerson(person, id);
	}
}
```
