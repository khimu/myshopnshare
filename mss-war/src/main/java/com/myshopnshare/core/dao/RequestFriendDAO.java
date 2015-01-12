package com.myshopnshare.core.dao;

import java.util.List;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.RequestFriend;

public interface RequestFriendDAO extends GenericDAO<RequestFriend, Long> {
	public List<RequestFriend> findAllRequestFor(Person person);
	public RequestFriend findFriendRequestBy(Person person);
}
