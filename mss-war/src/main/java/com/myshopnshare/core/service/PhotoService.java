package com.myshopnshare.core.service;

import com.myshopnshare.core.domain.Photo;

public interface PhotoService extends GenericService<Photo, Long> {
	public Photo findPhotoByName(String path);
}
