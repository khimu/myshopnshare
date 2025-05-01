# CreditCardServiceImpl

```java
package com.myshopnshare.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.CreditCardDAO;
import com.myshopnshare.core.domain.CreditCard;
import com.myshopnshare.core.domain.Person;

@Service("creditCardService")
@Transactional
public class CreditCardServiceImpl extends
		GenericServiceImpl<CreditCard, Long> implements
		CreditCardService {
	
	CreditCardDAO creditCardDAO;
	
	@Autowired
    public CreditCardServiceImpl(CreditCardDAO genericDao) {
    	super(genericDao);
        this.creditCardDAO = genericDao;
    }
	
	public CreditCard getCreditCardForPerson(Person person, Long id){
		return creditCardDAO.getCreditCardForPerson(person, id);
	}
	
	public List<CreditCard> getCardsForPerson(Person person){
		return creditCardDAO.getCardsForPerson(person);
	}
}
```
