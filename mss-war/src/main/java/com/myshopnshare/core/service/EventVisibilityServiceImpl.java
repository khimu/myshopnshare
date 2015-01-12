package com.myshopnshare.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.EventVisibilityDAO;
import com.myshopnshare.core.domain.EventVisibility;

@Service("eventVisibilityService")
@Transactional
public class EventVisibilityServiceImpl extends
		GenericServiceImpl<EventVisibility, Long> implements
		EventVisibilityService {
	public EventVisibilityDAO eventVisibilityDAO;

	@Autowired
    public EventVisibilityServiceImpl(EventVisibilityDAO genericDao) {
    	super(genericDao);
        this.eventVisibilityDAO = genericDao;
    }
}
