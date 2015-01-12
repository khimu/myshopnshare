package com.myshopnshare.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.ScribbleVisibilityDomainDAO;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.Scribble;
import com.myshopnshare.core.domain.ScribbleVisibilityDomain;

@Service("scribbleVisibilityDomainService")
@Transactional
public class ScribbleVisibilityDomainServiceImpl extends GenericServiceImpl<ScribbleVisibilityDomain, Long> implements ScribbleVisibilityDomainService {

	public ScribbleVisibilityDomainDAO scribbleVisibilityDomainDAO;

	@Autowired
	public ScribbleVisibilityDomainServiceImpl(ScribbleVisibilityDomainDAO scribbleVisibilityDomainDAO) {
		super(scribbleVisibilityDomainDAO);
		this.scribbleVisibilityDomainDAO = scribbleVisibilityDomainDAO;
	}

	public List<Scribble> findAllFriendVisibleScribblesFor(Person person, int start){
		return ((ScribbleVisibilityDomainDAO)this.dao).findAllFriendVisibleScribblesFor(person, start);
	}

	public List<Scribble> findAllVisibleScribblesFor(Person person, int start) {
		return ((ScribbleVisibilityDomainDAO)this.dao).findAllVisibleScribblesFor(person, start);
	}

}
