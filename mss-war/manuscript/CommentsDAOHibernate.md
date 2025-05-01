# CommentsDAOHibernate

```java
package com.myshopnshare.core.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Comment;
import com.myshopnshare.core.domain.Person;
@Repository("commentsDAO")
public class CommentsDAOHibernate extends GenericDAOHibernate<Comment, Long>
		implements CommentsDAO {
	public Comment getCommentForPerson(Person person, Long id) {
		String hql = "select e FROM Comment e WHERE e.person = :person and e.id = :id";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("id", id);
		q.setCacheable(true);
		return (Comment) q.uniqueResult();
	}
}
```
