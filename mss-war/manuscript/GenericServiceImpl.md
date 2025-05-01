# GenericServiceImpl

```java
package com.myshopnshare.core.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.GenericDAO;
import com.myshopnshare.core.domain.DomainObject;

/**
 * Created by IntelliJ IDEA. User: khim_ung Date: May 1, 2007 Time: 10:54:58 AM
 */
public abstract class GenericServiceImpl<T extends DomainObject, PK extends Serializable>
		implements GenericService<T, PK> {

	protected GenericDAO<T, PK> dao;

    public GenericServiceImpl() {}
    
    public GenericServiceImpl(GenericDAO<T, PK> genericDao) {
        this.dao = genericDao;
    }
	
	public void batchDelete(List<Long> ids){
		this.dao.batchDelete(ids);
	}

	@Transactional(readOnly = true)
	public T get(PK id) {
		return (T) this.dao.get(id);
	}
	
	public List<T> getAllActive(){
		return this.dao.getAllActive();
	}

	@Transactional(readOnly = true)
	public List<T> getAll() {
		return this.dao.getAll();
	}

	public List<T> getAllSortBy(String... parameters) {
		return this.dao.getAllSortBy(parameters);
	}

	@Transactional
	public void save(T domainObject) {
		this.dao.save(domainObject);
	}

	@Transactional
	public void update(T domainObject) {
		this.dao.save(domainObject);
	}

	@Transactional
	public void saveOrUpdate(T domainObject) {
		this.dao.saveOrUpdate(domainObject);
	}

	@Transactional
	public void delete(T domainObject) {
		this.dao.delete(domainObject);
	}

	@Transactional
	public void delete(PK id) {
		this.dao.delete(id);
	}
	
	public List<String> tokenize(String searchString) {
		List<String> tags = new ArrayList<String>();
		StringTokenizer tokenizerComma = new StringTokenizer(searchString, ",");
		while (tokenizerComma.hasMoreTokens()) {
			String tag = tokenizerComma.nextToken();
			StringTokenizer tokenizerSpace = new StringTokenizer(tag, " ");
			while (tokenizerSpace.hasMoreTokens()) {
				tags.add("%" + tokenizerSpace.nextToken().trim() + "%");
			}
		}
		return tags;
	}

}
```
