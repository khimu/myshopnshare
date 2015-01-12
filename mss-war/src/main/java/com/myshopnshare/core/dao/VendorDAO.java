package com.myshopnshare.core.dao;

import java.util.List;

import com.myshopnshare.core.domain.Vendor;

public interface VendorDAO extends GenericDAO<Vendor, Long> {
	public Vendor findVendorByUsername(String username);

	public List<Vendor> findBySearchString(String first, String last);
	public Vendor findByEmail(String email);
}
