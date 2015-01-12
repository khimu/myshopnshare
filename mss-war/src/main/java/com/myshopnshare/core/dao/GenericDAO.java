package com.myshopnshare.core.dao;

import java.io.Serializable;
import java.util.List;

import com.myshopnshare.core.domain.DomainObject;

/**
 * Created by IntelliJ IDEA. User: khim_ung Date: Apr 26, 2007 Time: 3:32:17 AM
 */
public interface GenericDAO<T extends DomainObject, ID extends Serializable> {  
	
	public T get(ID id);

	public List<T> getAll();
	
	public List<T> getAllActive();
	
	public void batchDelete(List<Long> ids);
	
	public List<T> getAllSortBy(String... parameters);

	public void save(T instance);

	public void update(T instance);

	public void saveOrUpdate(T instance);

	public void delete(T instance);
	
	public void delete(ID id);
	
	public int appendTag(List<String> tags, StringBuilder hql);
}
