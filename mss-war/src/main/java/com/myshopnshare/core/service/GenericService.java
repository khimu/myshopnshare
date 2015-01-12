package com.myshopnshare.core.service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA. User: khim_ung Date: Apr 25, 2007 Time: 5:55:46 AM
 */
public interface GenericService<T, ID extends Serializable> {
	public T get(ID id);

	public List<T> getAll();
	
	public void batchDelete(List<Long> ids);

	public void save(T instance);
	
	public List<T> getAllActive();

	public List<T> getAllSortBy(String... parameters);

	public void update(T instance);

	public void saveOrUpdate(T instance);

	public void delete(T instance);

	public void delete(ID id);
	
	public List<String> tokenize(String searchString);
}
