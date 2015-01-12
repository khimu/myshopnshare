package com.myshopnshare.core.dao;

import java.io.Serializable;
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

import com.myshopnshare.core.domain.DomainObject;
import com.myshopnshare.core.domain.Person;

/**
 * Created by IntelliJ IDEA. User: khim_ung Date: May 1, 2007 Time: 11:25:42 AM
 */
// @Transactional(propagation = Propagation.REQUIRED, timeout = 20)
public abstract class GenericDAOHibernate<T extends DomainObject, ID extends Serializable> implements GenericDAO<T, ID> {

	protected Class<T> persistentClass;

    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Log log = LogFactory.getLog(getClass());
    private HibernateTemplate hibernateTemplate;
    private SessionFactory sessionFactory;

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

	public GenericDAOHibernate() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public void batchDelete(List<Long> ids){
		String hql = "DELETE FROM "+this.persistentClass.getName()+" WHERE id in (:ids)";
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		q.setParameter("ids", ids);
		q.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public T get(ID id) {
		return (T) getHibernateTemplate().get(this.persistentClass, id);
	}

	public List<T> getAll() {
		String hql = "FROM " + this.persistentClass.getName() + " ORDER BY enteredDate DESC";
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		q.setFirstResult(0);
		q.setFetchSize(50);
		q.setMaxResults(50);
		q.setCacheable(true);
		return q.list();
	}

	public List<T> getAllActive() {
		String hql = "FROM " + this.persistentClass.getName() + " WHERE active = true ORDER BY enteredDate DESC";
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		q.setFirstResult(0);
		q.setFetchSize(50);
		q.setMaxResults(50);
		q.setCacheable(true);
		return q.list();
	}

	public List<T> getAllSortBy(String... parameters) {
		StringBuilder hql = new StringBuilder("from " + this.persistentClass.getName() + " ORDER BY");
		for(String parameter : parameters){
			hql.append(" " + parameter + ", ");
		}
		hql.deleteCharAt(hql.lastIndexOf(","));
		Query q = sessionFactory.getCurrentSession().createQuery(hql.toString());
		q.setFirstResult(0);
		q.setFetchSize(50);
		q.setMaxResults(50);
		q.setCacheable(true);
		return q.list();
	}
	
	public void save(T domainObject) {
		getHibernateTemplate().save(domainObject);
	}

	public void update(T domainObject) {
		getHibernateTemplate().update(domainObject);
	}

	public void saveOrUpdate(T domainObject) {
		getHibernateTemplate().saveOrUpdate(domainObject);
	}

	public void delete(T domainObject) {
		getHibernateTemplate().delete(domainObject);
	}

	public void delete(ID id){
		getHibernateTemplate().delete(id);
	}
	
	public T getForPerson(Person person, Long id){
		String hql = "select e FROM " + this.persistentClass.getName()  + "  e WHERE e.person = :person and e.id = :id";
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("id", id);
		q.setCacheable(true);
		return (T) q.uniqueResult();		
	}

	
	public int appendTag(List<String> tags, StringBuilder hql) {
		int index = 0;

		for (String tag : tags) {
			hql.append("(lower(t.tag) LIKE lower(:tag" + index++ + ") )");
			hql.append(" OR ");
		}
		hql = hql.delete(hql.toString().lastIndexOf("O"), hql.toString()
				.length());
		return index;
	}

}
