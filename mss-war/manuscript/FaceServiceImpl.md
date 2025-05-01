# FaceServiceImpl

```java
package com.myshopnshare.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.FaceDAO;
import com.myshopnshare.core.domain.Face;
import com.myshopnshare.core.domain.Person;

@Service("faceService")
@Transactional
public class FaceServiceImpl extends GenericServiceImpl<Face, Long>
		implements FaceService {
	
	public FaceDAO faceDAO;

	@Autowired
    public FaceServiceImpl(FaceDAO genericDao) {
    	super(genericDao);
        this.faceDAO = genericDao;
    }
	
	public Face getFaceForPerson(Person person, Long id){
		return ((FaceDAO) this.dao).getFaceForPerson(person, id);
	}
	
	public List<Face> getFaces(Person person){
		return ((FaceDAO) this.dao).getFaces(person);
	}
	
	public Face getDefaultFace(Person person){
		return ((FaceDAO) this.dao).getDefaultFace(person);
	}
	
	public void saveDefaultFace(Face face, Person person){
		faceDAO.getDeleteDefaultFace(person);
		faceDAO.save(face);
	}
}
```
