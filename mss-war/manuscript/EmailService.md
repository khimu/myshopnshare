# EmailService

```java
package com.myshopnshare.core.service;

import java.util.List;

import com.myshopnshare.core.domain.EmailAddress;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.EmailAddressType;

public interface EmailService extends GenericService<EmailAddress, Long> {
	public EmailAddress getEmailAddressForPerson(Person person, Long id);

	public Person findPersonByEmail(String email);

	public List<EmailAddress> findAllActiveEmailsFor(Person person);

	public Person findInactivePersonByEmail(String email);

	public List<EmailAddress> getPublicEmailsForPerson(Person person);

	public EmailAddress getPrimaryEmailForPerson(Person person);

	public EmailAddress getEmailWithType(Person person, EmailAddressType type);
}
```
