package com.myshopnshare.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.VisibilityDomainPersonDAO;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.VisibilityDomainPerson;
import com.myshopnshare.webapp.controller.VisibilityService;

@Service("visibilityService")
@Transactional
public class VisibilityServiceImpl implements VisibilityService {

	@Autowired
	private VisibilityDomainPersonDAO visibilityDomainPersonDao;
	
	public List<VisibilityDomainPerson> getVisibilityGroups(Person person){
		return visibilityDomainPersonDao.getVisibilityGroups(person);
	}
	
}
