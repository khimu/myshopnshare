package com.myshopnshare.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.VendorDAO;
import com.myshopnshare.core.domain.Vendor;

@Service("vendorService")
@Transactional
public class VendorServiceImpl extends GenericServiceImpl<Vendor, Long>
		implements VendorService {
	private VendorDAO vendorDAO;

	@Autowired
	public VendorServiceImpl(VendorDAO genericDao) {
		super(genericDao);
		this.vendorDAO = genericDao;
	}
	
	public List<Vendor> findBySearchString(String first, String last) {
		return ((VendorDAO)this.dao).findBySearchString(first, last);
	}

	
	public Vendor findVendorByUsername(String username) {
		return ((VendorDAO)this.dao).findVendorByUsername(username);
	}
	public Vendor findByEmail(String email){
		return ((VendorDAO)this.dao).findByEmail(email);
	}

}
