# PaymentDAO

```java
package com.myshopnshare.core.dao;

import com.myshopnshare.core.domain.Payment;
import com.myshopnshare.core.domain.Person;

public interface PaymentDAO extends GenericDAO<Payment, Long> {
	public Payment getPaymentForPerson(Person person, Long id);
}
```
