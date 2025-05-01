# AdsDAOHibernate

```java
package com.myshopnshare.core.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Ads;

@Repository("adsDAO")
public class AdsDAOHibernate extends GenericDAOHibernate<Ads, Long> implements
		AdsDAO {
	
	public List<Ads> getAdsForKids(){
		String hql = "FROM Ads WHERE ageRange < 13 AND active = true";
		Query q = getSession().createQuery(hql);
		q.setFirstResult(0);
		q.setFetchSize(50);
		q.setMaxResults(50);
		q.setCacheable(true);
		return q.list();
	}
	
	public List<Ads> getAdsForTeens(){
		String hql = "FROM Ads WHERE ageRange BETWEEN 13 AND 18 AND active = true";
		Query q = getSession().createQuery(hql);
		q.setCacheable(true);
		q.setFirstResult(0);
		q.setFetchSize(50);
		q.setMaxResults(50);
		return q.list();
	}
	
	public List<Ads> getCollegeAds(){
		String hql = "FROM Ads WHERE ageRange BETWEEN 13 AND 18 AND active = true";
		Query q = getSession().createQuery(hql);
		q.setFirstResult(0);
		q.setFetchSize(50);
		q.setMaxResults(50);
		q.setCacheable(true);
		return q.list();
	}
	
	public List<Ads> getAdultAds(){
		String hql = "FROM Ads WHERE ageRange BETWEEN 13 AND 18 AND active = true";
		Query q = getSession().createQuery(hql);
		q.setCacheable(true);
		q.setFirstResult(0);
		q.setFetchSize(50);
		q.setMaxResults(50);
		return q.list();
	}
	
	public List<Ads> getSeniorAds(){
		String hql = "FROM Ads WHERE ageRange BETWEEN 13 AND 18 AND active = true";
		Query q = getSession().createQuery(hql);
		q.setFirstResult(0);
		q.setFetchSize(50);
		q.setMaxResults(50);
		q.setCacheable(true);
		return q.list();
	}
	
	public List<Ads> getCategoryAds(String category){
		String hql = "FROM Ads a WHERE a in (SELECT a.ads FROM AdsTag t WHERE t.tag LIKE :category) AND active = true";
		Query q = getSession().createQuery(hql);
		q.setParameter("category", category);
		q.setFirstResult(0);
		q.setFetchSize(50);
		q.setMaxResults(50);
		q.setCacheable(true);
		return q.list();
	}
	
	public List<Ads> getAdsByTags(List<String> tags){
		String hql = "FROM Ads  WHERE ageRange < 13 AND active = false";
		Query q = getSession().createQuery(hql);
		q.setFirstResult(0);
		q.setFetchSize(50);
		q.setMaxResults(50);
		q.setCacheable(true);
		return q.list();
	}
}
```
