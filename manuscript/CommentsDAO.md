# CommentsDAO

```java
package com.myshopnshare.core.dao;

import com.myshopnshare.core.domain.Comment;
import com.myshopnshare.core.domain.Person;

public interface CommentsDAO extends GenericDAO<Comment, Long>{
	public Comment getCommentForPerson(Person person, Long id);
}
```
