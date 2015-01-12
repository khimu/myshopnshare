package com.myshopnshare.core.dao;

import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.EventVisibility;
@Repository("eventVisibilityDAO")
public class EventVisibilityDAOHibernate extends
		GenericDAOHibernate<EventVisibility, Long> implements
		EventVisibilityDAO {

}
