package com.myshopnshare.core.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.myshopnshare.core.domain.AuditLog;
import com.myshopnshare.core.domain.AuditLogList;
import com.myshopnshare.core.domain.AuditLogOperation;
import com.myshopnshare.utils.IdUtil;

public class AuditLogDAOHibernate extends HibernateDaoSupport implements
		AuditLogDAO {
	// public void save(AuditLog auditLog) {
	// getHibernateTemplate().save(auditLog);
	// }
	private HibernateTemplate hiberanteTemplate = null;

	private void initHibernateTemplate() {
		if (null != hiberanteTemplate) {
			return;
		}
		hiberanteTemplate = new HibernateTemplate(this.getSessionFactory(),
				true);
		hiberanteTemplate.setAlwaysUseNewSession(true);
	}

	public void save(AuditLogList auditLogList) {
		initHibernateTemplate();
		//hiberanteTemplate.save(auditLogList);
	}

	public List<AuditLog> search(String userName, String className,
			Long recordId, Date from, Date to, AuditLogOperation action,
			String fieldName, Long auditLogId) {
		Criteria auditLogCriteria = getSession().createCriteria(AuditLog.class);
		if (null != userName && !"".equals(userName)) {
			auditLogCriteria.add(Restrictions.eq("username", userName));
		}
		if (null != className && !"".equals(className)) {
			if (className.startsWith("com.sustain.")) {
				auditLogCriteria.add(Restrictions.eq("clazz", className));
			} else {
				auditLogCriteria.add(Restrictions.like("clazz",
						"com.sustain.%." + className));
			}
		}
		if (IdUtil.isValidId(recordId)) {
			auditLogCriteria.add(Restrictions.eq("recordId", recordId));
		}
		if (null != from) {
			auditLogCriteria.add(Restrictions.ge("timeStamp", from));
		}
		if (null != to) {
			auditLogCriteria.add(Restrictions.le("timeStamp", to));
		}
		if (action != null) {
			auditLogCriteria.add(Restrictions.ge("action", action.val()));
		}
		if (null != fieldName && !"".equals(fieldName)) {
			auditLogCriteria.add(Restrictions.like("updatedFields", "%, "
					+ fieldName + ", %"));
		}
		if (IdUtil.isValidId(auditLogId)) {
			auditLogCriteria.add(Restrictions.eq("auditLogId", auditLogId));
		}
		auditLogCriteria.addOrder(Order.asc("timeStamp"));
		return auditLogCriteria.list();
	}

	public List<AuditLog> getAuditLogs(String clazz, Long recordId,
			AuditLogOperation action) {
		if (clazz == null)
			return null;
		Criteria auditLogCriteria = getSession().createCriteria(AuditLog.class);
		auditLogCriteria.add(Restrictions.eq("clazz", clazz));
		if (IdUtil.isValidId(recordId))
			auditLogCriteria.add(Restrictions.eq("recordId", recordId));
		if (null != action) {
			auditLogCriteria.add(Restrictions.eq("action", action.val()));
		}
		auditLogCriteria.addOrder(Order.asc("timeStamp"));
		return auditLogCriteria.list();
	}

	public List<AuditLog> getAuditLogsDescend(String clazz, Long recordId,
			AuditLogOperation action) {
		if (clazz == null)
			return null;
		Criteria auditLogCriteria = getSession().createCriteria(AuditLog.class);
		auditLogCriteria.add(Restrictions.eq("clazz", clazz));
		if (IdUtil.isValidId(recordId))
			auditLogCriteria.add(Restrictions.eq("recordId", recordId));
		if (null != action) {
			auditLogCriteria.add(Restrictions.eq("action", action.val()));
		}
		auditLogCriteria.addOrder(Order.desc("timeStamp"));
		return auditLogCriteria.list();
	}

}
