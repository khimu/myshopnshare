package com.myshopnshare.core.service;

import java.util.List;

import com.myshopnshare.core.domain.Face;
import com.myshopnshare.core.domain.Person;

public interface FaceService extends GenericService<Face, Long> {
	public Face getFaceForPerson(Person person, Long id);
	public List<Face> getFaces(Person person);
	public Face getDefaultFace(Person person);
	public void saveDefaultFace(Face face, Person person);
}
