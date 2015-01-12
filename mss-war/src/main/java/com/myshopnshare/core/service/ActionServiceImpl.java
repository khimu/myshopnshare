package com.myshopnshare.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.ActionDAO;
import com.myshopnshare.core.domain.Action;

@Service("actionService")
@Transactional
public class ActionServiceImpl implements ActionService {

    private ActionDAO actionDAO;

    @Autowired
    public ActionServiceImpl(ActionDAO actionDAO) {
        this.actionDAO = actionDAO;
    }


	@Transactional(readOnly = true)
	public Action get(Long id) {
		return (Action) this.actionDAO.get(id);
	}

	@Transactional(readOnly = true)
	public List<Action> getAll() {
		return this.actionDAO.getAll();
	}

	@Transactional
	public void save(Action domainObject) {
		this.actionDAO.save(domainObject);
	}

	@Transactional
	public void update(Action domainObject) {
		this.actionDAO.save(domainObject);
	}

	@Transactional
	public void saveOrUpdate(Action domainObject) {
		this.actionDAO.saveOrUpdate(domainObject);
	}

	@Transactional
	public void delete(Action domainObject) {
		// Cannot delete anything here
		//this.actionDao.delete(domainObject);
	}

}
