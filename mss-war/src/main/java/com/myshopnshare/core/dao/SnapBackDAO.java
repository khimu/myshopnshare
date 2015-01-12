package com.myshopnshare.core.dao;

import java.util.List;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.SnapBack;

public interface SnapBackDAO extends GenericDAO<SnapBack, Long> {
	public List<SnapBack> findAllMessages(Person person);
}
