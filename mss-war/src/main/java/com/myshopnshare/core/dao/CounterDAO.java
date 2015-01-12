package com.myshopnshare.core.dao;

import com.myshopnshare.core.domain.Counter;

public interface CounterDAO extends GenericDAO<Counter, Long> {
	public Counter findGeneralCounter();
}
