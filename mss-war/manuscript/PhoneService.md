# PhoneService

```java
package com.myshopnshare.core.service;

import java.util.List;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.Phone;

public interface PhoneService extends GenericService<Phone, Long> {
	public Phone getPhoneForPerson(Person person, Long id);
	public List<Phone> getPhonesForPerson(Person person);
	public List<Phone> getPublicPhonesForPerson(Person person);
}
```
