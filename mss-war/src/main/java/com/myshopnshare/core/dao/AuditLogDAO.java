package com.myshopnshare.core.dao;

import java.util.Date;
import java.util.List;

import com.myshopnshare.core.domain.AuditLog;
import com.myshopnshare.core.domain.AuditLogList;
import com.myshopnshare.core.domain.AuditLogOperation;

public interface AuditLogDAO {
    //public void save(AuditLog auditLog);
    public void save(AuditLogList auditLogList);

    public List<AuditLog> search(String userName, String className, Long recordId, Date from, Date to, AuditLogOperation action, String fieldName, Long auditLogId);

    public List<AuditLog> getAuditLogs(String clazz, Long recordId, AuditLogOperation action);
    public List<AuditLog> getAuditLogsDescend(String clazz, Long recordId, AuditLogOperation action);
}
