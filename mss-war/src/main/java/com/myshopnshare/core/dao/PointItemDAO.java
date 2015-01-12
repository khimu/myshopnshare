package com.myshopnshare.core.dao;

import java.util.List;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.PointItem;

public interface PointItemDAO extends GenericDAO<PointItem, Long> {
	public List<PointItem> findItems(Long vendorId);
	public List<PointItem> findItems(Long vendorId, List<String> tags);
	public List<PointItem> findItems(List<String> tags);
	public List<PointItem> findItems();
	public PointItem getPointItemForPerson(Person person, Long id);
}
