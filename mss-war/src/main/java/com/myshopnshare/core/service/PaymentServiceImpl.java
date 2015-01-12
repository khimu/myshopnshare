package com.myshopnshare.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.PaymentDAO;
import com.myshopnshare.core.domain.Payment;
import com.myshopnshare.core.domain.Person;

@Service("paymentService")
@Transactional
public class PaymentServiceImpl extends
		GenericServiceImpl<Payment, Long> implements PaymentService {
	
	private PaymentDAO paymentDAO;

	@Autowired
	public PaymentServiceImpl(PaymentDAO genericDao) {
		super(genericDao);
		this.paymentDAO = genericDao;
	}
	
	public Payment getPaymentForPerson(Person person, Long id){
		return paymentDAO.getPaymentForPerson(person, id);
	}
}
