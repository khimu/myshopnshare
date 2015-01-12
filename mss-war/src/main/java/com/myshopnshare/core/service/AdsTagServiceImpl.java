package com.myshopnshare.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.AdsTagDAO;
import com.myshopnshare.core.domain.AdsTag;

@Service("adsTagService")
@Transactional
public class AdsTagServiceImpl extends
		GenericServiceImpl<AdsTag, Long> implements AdsTagService{

    private AdsTagDAO adsTagDao;

    @Autowired
    public AdsTagServiceImpl(AdsTagDAO adsTagDao) {
        super(adsTagDao);
        this.adsTagDao = adsTagDao;
    }

}
