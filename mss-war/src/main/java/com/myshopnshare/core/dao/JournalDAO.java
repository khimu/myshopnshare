package com.myshopnshare.core.dao;

import java.util.List;

import com.myshopnshare.core.domain.Journal;
import com.myshopnshare.core.domain.Person;

public interface JournalDAO extends GenericDAO<Journal, Long> {
	public Journal getJournalForPerson(Person person, Long id);
	
	public List<Journal> findOwnJournal(Person person, String category, List<String> tags, int start);
	public List<Journal> findOwnJournal(Person person, List<String> tags, int start);
	public List<Journal> findOwnJournal(Person person, String category, int start);
	public List<Journal> findOwnJournal(Person person, int start);

	public List<Journal> findWorldJournal(Person person, String category, List<String> tags, int start);
	public List<Journal> findWorldJournal(Person person, List<String> tags, int start);
	public List<Journal> findWorldJournal(Person person, String category, int start);
	public List<Journal> findWorldJournal(Person person, int start);
	
	public List<Journal> findOneFriendsJournal(Person friend, Person person, String category, List<String> tags, int start);
	public List<Journal> findOneFriendsJournal(Person friend, Person person, List<String> tags, int start);
	public List<Journal> findOneFriendsJournal(Person friend, Person person, String category, int start);
	public List<Journal> findOneFriendsJournal(Person friend, Person person, int start);
	
	public List<Journal> findAllFriendsJournal(Person person, String category, List<String> tags,int start);
	public List<Journal> findAllFriendsJournal(Person person, List<String> tags,int start);
	public List<Journal> findAllFriendsJournal(Person person, String category, int start);
	public List<Journal> findAllFriendsJournal(Person person, int start);
}
