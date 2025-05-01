# RequestFriendServiceImpl

```java
package com.myshopnshare.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.RequestFriendDAO;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.RequestFriend;

@Service("requestFriendService")
@Transactional
public class RequestFriendServiceImpl extends
		GenericServiceImpl<RequestFriend, Long> implements
		RequestFriendService {
	
	private RequestFriendDAO requestFriendDAO;

	@Autowired
	public RequestFriendServiceImpl(RequestFriendDAO genericDao) {
		super(genericDao);
		this.requestFriendDAO = genericDao;
	}

	public List<RequestFriend> findAllRequestFor(Person person) {
		return requestFriendDAO.findAllRequestFor(person);
	}


	public RequestFriend findFriendRequestBy(Person person) {
		return requestFriendDAO.findFriendRequestBy(person);
	}

}
```
