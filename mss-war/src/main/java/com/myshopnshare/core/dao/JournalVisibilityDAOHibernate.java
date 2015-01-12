package com.myshopnshare.core.dao;

import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.JournalVisibility;
@Repository("journalVisibilityDAO")
public class JournalVisibilityDAOHibernate extends
		GenericDAOHibernate<JournalVisibility, Long> implements JournalVisibilityDAO {

}
