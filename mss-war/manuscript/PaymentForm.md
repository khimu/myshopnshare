# PaymentForm

```java
package com.myshopnshare.webapp.form;

import com.myshopnshare.core.domain.Address;
import com.myshopnshare.core.domain.CreditCard;
import com.myshopnshare.core.domain.EmailAddress;
import com.myshopnshare.core.domain.Phone;

public class PaymentForm {

	private boolean sameAsBilling = false;
	private Address billingAddress = new Address();
	private Address shippingAddress = new Address();
	private Phone contact = new Phone();

	private EmailAddress paypalAccount = new EmailAddress();

	private CreditCard creditCard = new CreditCard();

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Phone getContact() {
		return contact;
	}

	public void setContact(Phone contact) {
		this.contact = contact;
	}

	public EmailAddress getPaypalAccount() {
		return paypalAccount;
	}

	public void setPaypalAccount(EmailAddress paypalAccount) {
		this.paypalAccount = paypalAccount;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public boolean isSameAsBilling() {
		return sameAsBilling;
	}

	public void setSameAsBilling(boolean sameAsBilling) {
		this.sameAsBilling = sameAsBilling;
	}

}
```
