# FriendServiceImpl

```java
package com.myshopnshare.core.service;

import java.util.List;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.FriendDAO;
import com.myshopnshare.core.domain.Friend;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.VisibilityDomain;

@Service("friendService")
@Transactional
public class FriendServiceImpl extends
		GenericServiceImpl<Friend, Long> implements FriendService {
	// @Autowired
	// @Qualifier("friendDAO")
	public FriendDAO friendDAO;

	@Autowired
    public FriendServiceImpl(FriendDAO genericDao) {
    	super(genericDao);
        this.friendDAO = genericDao;
    }
	
	public Friend getFriendForPerson(Person person, Long id){
		return ((FriendDAO) this.dao).getFriendForPerson(person, id);
	}
	
	public List<Person> findAllFriends(Person person) {
		return friendDAO.findAllFriends(person);
	}

	public List<Long> findAllFriendsID(Person owner) {
		return friendDAO.findAllFriendsID(owner);
	}

	public List<Long> getFriends(Person person, int start) {
		return friendDAO.getFriends(person, start);
	}

	public List<Person> findAllFriendWithVisibility(VisibilityDomain vd) {
		return friendDAO.findAllFriendWithVisibility(vd);
	}


	public List<Friend> findAllFriendsFriend(Person person, String color,
			int start) {
		return friendDAO.findAllFriendsFriend(person, color, start);
	}

	
	public List<Friend> findAllFriendsFriend(Person person, int start) {
		return friendDAO.findAllFriendsFriend(person, start);
	}

	public List<Friend> findOneFriendsFriend(Person friend, Person person,
			String color, int start) {
		return friendDAO.findOneFriendsFriend(friend, person, color, start);
	}

	public List<Friend> findOneFriendsFriend(Person friend, Person person,
			int start) {
		return friendDAO.findOneFriendsFriend(friend, person, start);
	}

	public List<Friend> findOwnFriend(Person person, int start) {
		return friendDAO.findOwnFriend(person, start);
	}

	public List<Friend> findOwnFriend(Person person, String color, int start) {
		return friendDAO.findOwnFriend(person, color, start);
	}

	public List<Friend> findWorldFriend(Person person, String color, int start) {
		return friendDAO.findWorldFriend(person, color, start);
	}


	public List<Friend> findWorldFriend(Person person, int start) {
		return friendDAO.findWorldFriend(person, start);
	}
	
	public boolean isFriends(Person person, String friendId) {
		List<Friend> friends = this.friendDAO.findFriends(person);
		if (friends == null) {
			return false;
		}
		for (Friend friend : friends) {
			if (friend.getFriend().getId().equals(Long.parseLong(friendId))) {
				return true;
			}
		}
		return false;
	}

	public boolean isFriends(Person person, Long friendId) {
		List<Friend> friends = this.friendDAO.findFriends(person);
		if (friends == null) {
			return false;
		}
		for (Friend friend : friends) {
			if (friend.getFriend().getId().equals(friendId)) {
				return true;
			}
		}
		return false;
	}

	public Friend findFriend(Person person, Long friendId) {
		List<Friend> friends = this.friendDAO.findFriends(person);
		for (Friend friend : friends) {
			if (friend.getFriend().getId().equals(friendId)) {
				return friend;
			}
		}
		return null;
	}

}
```
