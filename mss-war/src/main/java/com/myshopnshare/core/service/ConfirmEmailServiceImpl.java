package com.myshopnshare.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.providers.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.ConfirmEmailDAO;
import com.myshopnshare.core.domain.ConfirmEmail;

@Service("confirmEmailService")
@Transactional
public class ConfirmEmailServiceImpl extends
		GenericServiceImpl<ConfirmEmail, Long> implements
		ConfirmEmailService {

	@Autowired
    private PasswordEncoder passwordEncoder;

	private ConfirmEmailDAO confirmEmailDAO;

	@Autowired
    public ConfirmEmailServiceImpl(ConfirmEmailDAO genericDao) {
    	super(genericDao);
        this.confirmEmailDAO = genericDao;
    }


	public ConfirmEmail findByKey(String key) {
		return this.confirmEmailDAO.findByKey(key);
	}
	
	@Override
	public void save(ConfirmEmail confirmEmail){
        // If password was changed (or new user), encrypt it
        //confirmEmail.setPassword(passwordEncoder.encodePassword(confirmEmail.getPassword(), null));
        this.confirmEmailDAO.save(confirmEmail);
	}
}
