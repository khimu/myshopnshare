package com.myshopnshare.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.VisibilityDomainDAO;
import com.myshopnshare.core.domain.Permission;
import com.myshopnshare.core.domain.VisibilityDomain;

@Service("visibilityDomainService")
@Transactional
public class VisibilityDomainServiceImpl extends
		GenericServiceImpl<VisibilityDomain, Long>
		implements VisibilityDomainService {

	private VisibilityDomainDAO visibilityDomainDAO;

	@Autowired
	public VisibilityDomainServiceImpl(VisibilityDomainDAO genericDao) {
		super(genericDao);
		this.visibilityDomainDAO = genericDao;
	}
	
	public List<VisibilityDomain> findAllGroupsForPerson(Permission permission){
		return ((VisibilityDomainDAO) this.dao).findAllGroupsForPerson(permission);
	}

}
