package com.myshopnshare.core.service;

import java.util.List;

import com.myshopnshare.core.domain.Friend;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.VisibilityDomain;

public interface FriendService extends GenericService<Friend, Long> {
	public Friend getFriendForPerson(Person person, Long id);
	public List<Person> findAllFriends(Person person);

	public List<Long> findAllFriendsID(Person owner);
	
	public List<Long> getFriends(Person person, int start);
	
	public List<Person> findAllFriendWithVisibility(VisibilityDomain vd);
	
	public List<Friend> findOwnFriend(Person person, int start);

	public List<Friend> findOwnFriend(Person person, String color, int start);

	public List<Friend> findWorldFriend(Person person, String color, int start);

	public List<Friend> findWorldFriend(Person person, int start);

	public List<Friend> findOneFriendsFriend(Person friend, Person person,
			String color, int start);

	public List<Friend> findOneFriendsFriend(Person friend, Person person,
			int start);

	public List<Friend> findAllFriendsFriend(Person person, String color,
			int start);

	public List<Friend> findAllFriendsFriend(Person person, int start);
	
	public boolean isFriends(Person person, String friendId);

	public boolean isFriends(Person person, Long friendId);

	public Friend findFriend(Person person, Long friendId);
}
