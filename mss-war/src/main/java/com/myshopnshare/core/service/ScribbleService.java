package com.myshopnshare.core.service;

import java.util.List;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.Scribble;

public interface ScribbleService extends
		GenericService<Scribble, Long> {
	public Scribble getScribbleForPerson(Person person, Long id);
	public List<Scribble> findWorldScribble(Person person, String category, String searchString, int start);
	public List<Scribble> findOneFriendsScribble(Person friend, Person person, String category, String searchString, int start);
	public List<Scribble> findAllFriendsScribble(Person person, String category, String searchString,int start);
}
