package com.myshopnshare.core.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Photo;
@Repository("photoDAO")
public class PhotoDAOHibernate extends GenericDAOHibernate<Photo, Long>
		implements PhotoDAO {

	public Photo findPhotoByName(String path) {
		String hql = "FROM Photo WHERE path = :path";
		Query q = getSession().createQuery(hql);
		q.setParameter("path", path);
		return (Photo) q.uniqueResult();
	}
}
