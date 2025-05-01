# RewardCartDAOHibernate

```java
package com.myshopnshare.core.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.RewardCart;

@Repository("rewardCartDaoHibernate")
public class RewardCartDAOHibernate extends GenericDAOHibernate<RewardCart, Long> implements RewardCartDAO {
	public List<RewardCart> getRewardCartItems(Person person){
		String hql = "FROM RewardCart WHERE person = :person and active = :active";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("active", true);
		q.setCacheable(true);
		return q.list();		
	}
}
```
