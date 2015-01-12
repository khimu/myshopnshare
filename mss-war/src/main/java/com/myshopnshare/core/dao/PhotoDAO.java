package com.myshopnshare.core.dao;

import com.myshopnshare.core.domain.Photo;

public interface PhotoDAO extends GenericDAO<Photo, Long> {

	public Photo findPhotoByName(String path);
}
