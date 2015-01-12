package com.myshopnshare.core.service;

import java.util.List;

import com.myshopnshare.core.domain.Action;

public interface ActionService {
	public Action get(Long id);

	public List<Action> getAll();

	public void save(Action instance);

	public void update(Action instance);

	public void saveOrUpdate(Action instance);

	public void delete(Action instance);
}
