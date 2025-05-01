# AuditLogManagerImpl

```java
package com.myshopnshare.core.service;

import java.util.Date;
import java.util.List;

import com.myshopnshare.core.dao.AuditLogDAO;
import com.myshopnshare.core.domain.AuditLog;
import com.myshopnshare.core.domain.AuditLogList;
import com.myshopnshare.core.domain.AuditLogOperation;

public class AuditLogManagerImpl implements AuditLogManager {
	private AuditLogDAO dao = null;

	public AuditLogDAO getDao() {
		return dao;
	}

	public void setDao(AuditLogDAO dao) {
		this.dao = dao;
	}

	// public void saveAuditLog(AuditLog auditLog) {
	// dao.save(auditLog);
	// }

	public synchronized void saveAuditLogList(AuditLogList logs) {
		dao.save(logs);
	}

	public List<AuditLog> search(String userName, String className,
			Long recordId, Date from, Date to, AuditLogOperation action,
			String fieldName, Long auditLogId) {
		return dao.search(userName, className, recordId, from, to, action,
				fieldName, auditLogId);
	}

	public AuditLog getCreateLog(String className, Long recordId) {
		List<AuditLog> auditLogs = dao.getAuditLogs(className, recordId,
				AuditLogOperation.insert);
		if (auditLogs == null || auditLogs.size() == 0)
			return null;
		return auditLogs.get(0);
	}

	public AuditLog getLastUpdateLog(String className, Long recordId) {
		List<AuditLog> auditLogs = dao.getAuditLogsDescend(className, recordId,
				AuditLogOperation.update);
		if (auditLogs == null || auditLogs.size() == 0)
			return null;
		return auditLogs.get(0);
	}
}
```
