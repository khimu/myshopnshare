package com.myshopnshare.core.dao;

import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.AdsTag;

@Repository("adsTagDAO")
public class AdsTagDAOHibernate extends GenericDAOHibernate<AdsTag, Long>
		implements AdsTagDAO {

}
