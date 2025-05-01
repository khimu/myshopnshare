# FaceDAO

```java
package com.myshopnshare.core.dao;

import java.util.List;

import org.hibernate.Query;

import com.myshopnshare.core.domain.Face;
import com.myshopnshare.core.domain.Person;

public interface FaceDAO extends GenericDAO<Face, Long>{
	public Face getFaceForPerson(Person person, Long id);
	public List<Face> getFaces(Person person);
	public Face getDefaultFace(Person person);
	public void getDeleteDefaultFace(Person person);
}
```
