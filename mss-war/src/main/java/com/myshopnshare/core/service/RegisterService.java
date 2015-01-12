package com.myshopnshare.core.service;

import com.myshopnshare.core.domain.ConfirmEmail;
import com.myshopnshare.model.User;
import com.myshopnshare.webapp.form.RegisterForm;

public interface RegisterService {
	public String register(RegisterForm form, String username);
	public User completeRegister(ConfirmEmail confirmEmail);
}
