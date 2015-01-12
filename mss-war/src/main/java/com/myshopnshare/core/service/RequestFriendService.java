package com.myshopnshare.core.service;

import java.util.List;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.RequestFriend;

public interface RequestFriendService extends
		GenericService<RequestFriend, Long> {
	public List<RequestFriend> findAllRequestFor(Person person);
	public RequestFriend findFriendRequestBy(Person person);
}
