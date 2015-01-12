package com.myshopnshare.core.dao;

import java.util.List;

import com.myshopnshare.core.domain.Address;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.AddressType;

public interface AddressDAO extends GenericDAO<Address, Long>{
	public Address getAddressForPerson(Person person, Long id);
	public List<Address> getAddressesForPerson(Person person);
	public List<Address> getPublicAddressesForPerson(Person person);
	public Address getAddressWithTypeForPerson(Person person, AddressType type);
	public Address getPrimaryAddressForPerson(Person person);
}
