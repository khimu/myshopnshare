package com.myshopnshare.core.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Action;
import com.myshopnshare.core.exception.SleekSwapDBException;

@Repository("actionDAO")
public class ActionDAOHibernate implements
		ActionDAO {

    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Log log = LogFactory.getLog(getClass());
    private HibernateTemplate hibernateTemplate;
    private SessionFactory sessionFactory;
    
	public ActionDAOHibernate() {
	}

    public HibernateTemplate getHibernateTemplate() {
        return this.hibernateTemplate;
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }
    
    public Session getSession(){
    	return this.sessionFactory.getCurrentSession();
    }

    @Autowired
    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }


	@SuppressWarnings("unchecked")
	public Action get(Long id) {
		return (Action) getHibernateTemplate().get(Action.class, id);
	}

	public List<Action> getAll() throws SleekSwapDBException {
		String hql = "FROM Action WHERE active = true";
		Query q = getSession().createQuery(hql);
		q.setFirstResult(0);
		q.setFetchSize(50);
		q.setMaxResults(50);
		q.setCacheable(true);
		return q.list();
	}

	public List<Action> getAllSortBy(String... parameters) throws SleekSwapDBException{
		StringBuilder hql = new StringBuilder("from Action ORDER BY");
		for (String parameter : parameters) {
			hql.append(" " + parameter + ", ");
		}
		hql.deleteCharAt(hql.lastIndexOf(","));
		Query q = getSession().createQuery(hql.toString());
		q.setFirstResult(0);
		q.setFetchSize(50);
		q.setMaxResults(50);
		q.setCacheable(true);
		return q.list();
	}

	public void save(Action domainObject) throws SleekSwapDBException{
		getHibernateTemplate().save(domainObject);
	}

	public void update(Action domainObject) throws SleekSwapDBException{
		getHibernateTemplate().update(domainObject);
	}

	public void saveOrUpdate(Action domainObject) throws SleekSwapDBException{
		getHibernateTemplate().saveOrUpdate(domainObject);
	}

	/**
	 * This method should be disabled here
	 */
	public void delete(Action domainObject) throws SleekSwapDBException{
		getHibernateTemplate().delete(domainObject);
	}

}
