package com.myshopnshare.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.CounterDAO;
import com.myshopnshare.core.domain.Counter;

@Service("counterService")
@Transactional
public class CounterServiceImpl extends
		GenericServiceImpl<Counter, Long> implements CounterService{
	
	private CounterDAO counterDAO;

	@Autowired
    public CounterServiceImpl(CounterDAO genericDao) {
    	super(genericDao);
        this.counterDAO = genericDao;
    }

	public Counter findGeneralCounter(){
		
		Counter counter = this.counterDAO.findGeneralCounter();

		if(counter == null){
			counter = new Counter("GENERAL");
		}
		return counter;
	}
}
