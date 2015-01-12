package com.myshopnshare.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.MerchantDAO;
import com.myshopnshare.core.domain.Merchant;

@Service("merchantService")
@Transactional
public class MerchantServiceImpl extends
		GenericServiceImpl<Merchant, Long> implements
		MerchantService {

	private MerchantDAO merchantDAO;

	@Autowired
	public MerchantServiceImpl(MerchantDAO genericDao) {
		super(genericDao);
		this.merchantDAO = genericDao;
	}
}
