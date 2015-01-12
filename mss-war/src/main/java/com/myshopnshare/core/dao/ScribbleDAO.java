package com.myshopnshare.core.dao;

import java.util.List;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.Scribble;

public interface ScribbleDAO extends GenericDAO<Scribble, Long> {
	public Scribble getScribbleForPerson(Person person, Long id);
	
	public List<Scribble> findWorldScribble(Person person, String category, List<String> tags, int start);
	public List<Scribble> findWorldScribble(Person person, List<String> tags, int start);
	public List<Scribble> findWorldScribble(Person person, String category,  int start);
	public List<Scribble> findWorldScribble(Person person, int start);

	public List<Scribble> findOneFriendsScribble(Person friend, Person person, String category, List<String> tags, int start);
	public List<Scribble> findOneFriendsScribble(Person friend, Person person, List<String> tags, int start);
	public List<Scribble> findOneFriendsScribble(Person friend, Person person, String category, int start);
	public List<Scribble> findOneFriendsScribble(Person friend, Person person, int start);

	public List<Scribble> findAllFriendsScribble(Person person, String category, List<String> tags,int start);
	public List<Scribble> findAllFriendsScribble(Person person,  List<String> tags,int start);
	public List<Scribble> findAllFriendsScribble(Person person, String category, int start);
	public List<Scribble> findAllFriendsScribble(Person person, int start);
}
