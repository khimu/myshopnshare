# HeadlineDAOHibernate

```java
package com.myshopnshare.core.dao;


import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Headline;
import com.myshopnshare.core.domain.Person;
@Repository("headlineDAO")
public class HeadlineDAOHibernate extends GenericDAOHibernate<Headline, Long>
		implements HeadlineDAO {

	/**
	 * 
	 // compute max mod date Calendar cal = Calendar.getInstance();
	 * cal.add(Calendar.DAY_OF_YEAR, -15); Date maxModDate = cal.getTime();
	 * 
	 * // query database Query query =
	 * session.createQuery("from data where lst_mod_date <= :maxModDate");
	 * query.setParameter("maxModDate", maxModDate"); List result =
	 * query.list();
	 */
	/**
	 * Select * from APPL_DATA App WHERE TO_DAYS(NOW()) - TO_DAYS(LST_MOD_DATE)
	 * <= 15
	 **/
	public Headline findLatestHeadlineFor(Person person) {
		String hql = "FROM Headline h WHERE h.person = :person ORDER BY h.enteredDate DESC";
		Query q = getSession().createQuery(hql.toString());
		q.setParameter("person", person);
		q.setFirstResult(0);
		q.setFetchSize(50);
		q.setMaxResults(50);
		q.setCacheable(true);
		List<Headline> results = q.list();
		if(results.size() > 0){
			return results.get(0);
		}
		return null;
	}

}
```
