package com.myshopnshare.core.dao;

import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Bank;

@Repository("bankDAO")
public class BankDAOHibernate extends GenericDAOHibernate<Bank, Long> implements BankDAO{
	
}
