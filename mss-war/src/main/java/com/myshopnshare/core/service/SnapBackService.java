package com.myshopnshare.core.service;

import java.util.List;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.SnapBack;

public interface SnapBackService extends
		GenericService<SnapBack, Long> {
	public List<SnapBack> findAllMessages(Person person);
}
