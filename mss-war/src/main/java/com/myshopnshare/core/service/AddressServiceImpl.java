package com.myshopnshare.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.AddressDAO;
import com.myshopnshare.core.domain.Address;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.AddressType;

@Service("addressService")
@Transactional
public class AddressServiceImpl extends GenericServiceImpl<Address, Long>
		implements AddressService {

	private AddressDAO addressDAO;

	@Autowired
	public AddressServiceImpl(AddressDAO genericDao) {
		super(genericDao);
		this.addressDAO = genericDao;
	}

	public Address getAddressForPerson(Person person, Long id) {
		return ((AddressDAO) this.dao).getAddressForPerson(person, id);
	}

	public List<Address> getAddressesForPerson(Person person) {
		return addressDAO.getAddressesForPerson(person);
	}

	public List<Address> getPublicAddressesForPerson(Person person) {
		return addressDAO.getPublicAddressesForPerson(person);
	}

	public Address getAddressWithTypeForPerson(Person person, AddressType type) {
		return addressDAO.getAddressWithTypeForPerson(person, type);
	}

	public Address getPrimaryAddressForPerson(Person person) {
		return addressDAO.getPrimaryAddressForPerson(person);
	}
}
