# PhoneServiceImpl

```java
package com.myshopnshare.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.PhoneDAO;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.Phone;

@Service("phoneService")
@Transactional
public class PhoneServiceImpl extends GenericServiceImpl<Phone, Long>
		implements PhoneService {
	
	private PhoneDAO phoneDAO;

	@Autowired
	public PhoneServiceImpl(PhoneDAO genericDao) {
		super(genericDao);
		this.phoneDAO = genericDao;
	}
	
	public Phone getPhoneForPerson(Person person, Long id){
		return ((PhoneDAO) this.dao).getPhoneForPerson(person, id);
	}
	
	public List<Phone> getPhonesForPerson(Person person){
		return phoneDAO.getPhonesForPerson(person);
	}
	
	public List<Phone> getPublicPhonesForPerson(Person person){
		return phoneDAO.getPublicPhonesForPerson(person);
	}
}
```
