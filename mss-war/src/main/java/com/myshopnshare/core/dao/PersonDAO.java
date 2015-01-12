package com.myshopnshare.core.dao;

import java.util.List;

import com.myshopnshare.core.domain.Person;

public interface PersonDAO extends GenericDAO<Person, Long>{
	public Person findPersonByUsername(String username);
	public List<Person> findBySearchString(String first, String last);
	public Person findByEmail(String email);
	public List<Person> findBySearchString(String searchString);
	public List<Person> findMerchants();
}
