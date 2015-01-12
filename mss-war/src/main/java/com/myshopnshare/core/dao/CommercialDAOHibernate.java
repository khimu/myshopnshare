package com.myshopnshare.core.dao;

import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Commercial;
@Repository("commercialDAO")
public class CommercialDAOHibernate extends GenericDAOHibernate<Commercial, Long> implements CommercialDAO{

}
