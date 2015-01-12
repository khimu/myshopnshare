package com.myshopnshare.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.BankDAO;
import com.myshopnshare.core.domain.Bank;

@Service("bankService")
@Transactional
public class BankServiceImpl extends GenericServiceImpl<Bank, Long> implements BankService{

	private BankDAO bankDAO;

	@Autowired
	public BankServiceImpl(BankDAO bankDAO) {
		super(bankDAO);
		this.bankDAO = bankDAO;
	}

	public void updateBank(Bank bank) {
		if (bank.isRecentlyLoggedIn()) {
			bank.updateBalance();
			bank.updateLastLoggedIn();
			this.dao.save(bank);
		}
	}
}
