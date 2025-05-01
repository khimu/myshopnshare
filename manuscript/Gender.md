# Gender

```java
package com.myshopnshare.core.domain;


public enum Gender {

	FEMALE, MALE;

	private String gender;

	private Gender() {
		this.gender = this.name();
	}

	public String getLabel() {
		return this.toString();
	}

	public String getValue() {
		return this.name();
	}

}
```
