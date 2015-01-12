package com.myshopnshare.core.service;

import java.util.Date;
import java.util.List;

import com.myshopnshare.core.dao.AuditLogDAO;
import com.myshopnshare.core.domain.AuditLog;
import com.myshopnshare.core.domain.AuditLogList;
import com.myshopnshare.core.domain.AuditLogOperation;

public interface AuditLogManager {
    public static final String BEAN_NAME = "auditLogManager";

    //public void saveAuditLog(AuditLog auditLog);
    AuditLogDAO getDao();

    public void saveAuditLogList(AuditLogList logs);

    public List<AuditLog> search(String userName, String className, Long recordId, Date from, Date to, AuditLogOperation action, String fieldName, Long auditLogId);

    public AuditLog getCreateLog(String className, Long recordId);
    public AuditLog getLastUpdateLog(String className,Long recordId);
}
