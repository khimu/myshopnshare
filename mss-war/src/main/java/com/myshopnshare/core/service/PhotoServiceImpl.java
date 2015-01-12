package com.myshopnshare.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.PhotoDAO;
import com.myshopnshare.core.domain.Photo;

@Service("photoService")
@Transactional
public class PhotoServiceImpl extends GenericServiceImpl<Photo, Long>
		implements PhotoService {
	
	private PhotoDAO photoDAO;

	@Autowired
	public PhotoServiceImpl(PhotoDAO genericDao) {
		super(genericDao);
		this.photoDAO = genericDao;
	}
	
	public Photo findPhotoByName(String path){
		return ((PhotoDAO)this.dao).findPhotoByName(path);
	}
}
