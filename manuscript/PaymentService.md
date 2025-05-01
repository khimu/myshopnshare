# PaymentService

```java
package com.myshopnshare.core.service;

import com.myshopnshare.core.domain.Payment;
import com.myshopnshare.core.domain.Person;

public interface PaymentService extends
		GenericService<Payment, Long> {
	public Payment getPaymentForPerson(Person person, Long id);
}
```
