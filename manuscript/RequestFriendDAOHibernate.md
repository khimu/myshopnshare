# RequestFriendDAOHibernate

```java
package com.myshopnshare.core.dao;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.RequestFriend;
@Repository("requestFriendDAO")
public class RequestFriendDAOHibernate extends
		GenericDAOHibernate<RequestFriend, Long> implements RequestFriendDAO {

	public List<RequestFriend> findAllRequestFor(Person person) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.add(GregorianCalendar.DAY_OF_MONTH, 5);
		Date expiredDate = calendar.getTime();
		
		//String hql = "FROM RequestFriend r join fetch r.potentialFriend WHERE r.potentialFriend = :person and r.requestDate < :expiredDate and r.confirmFriend = false and r.rejectFriend = false AND r.active = true ORDER BY r.enteredDate ASC";
		String hql = "FROM RequestFriend r join fetch r.potentialFriend WHERE r.potentialFriend = :person and r.requestDate < :expiredDate AND r.active = true ORDER BY r.enteredDate DESC";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		//q.setParameter("confirmFriend", Boolean.FALSE);
		q.setParameter("expiredDate", expiredDate);
		q.setMaxResults(50);
		q.setFirstResult(0);
		q.setFetchSize(50);
		q.setCacheable(true);
		return q.list();
	}
	
	public RequestFriend findFriendRequestBy(Person person){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.add(GregorianCalendar.DAY_OF_MONTH, 5);
		Date expiredDate = calendar.getTime();
		
		String hql = "FROM RequestFriend r where r.requester = :person and r.requestDate < :expiredDate and r.active = true";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("expiredDate", expiredDate);
		q.setCacheable(true);
		return (RequestFriend) q.uniqueResult();	
	}
}
```
