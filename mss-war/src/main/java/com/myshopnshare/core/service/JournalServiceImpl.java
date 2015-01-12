package com.myshopnshare.core.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.JournalDAO;
import com.myshopnshare.core.domain.Journal;
import com.myshopnshare.core.domain.Person;

@Service("journalService")
@Transactional
public class JournalServiceImpl extends
		GenericServiceImpl<Journal, Long> implements JournalService {

	private JournalDAO journalDAO;

	@Autowired
    public JournalServiceImpl(JournalDAO genericDao) {
    	super(genericDao);
        this.journalDAO = genericDao;
    }
	public Journal getJournalForPerson(Person person, Long id){
		return journalDAO.getJournalForPerson(person, id);
	}
	
	public List<Journal> findOwnJournal(Person person, String category,
			String searchString, int start) {
		if (StringUtils.trimToNull(searchString) == null) {
			if (StringUtils.trimToNull(category) == null) {
				return journalDAO.findOwnJournal(person, start);
			}
			return journalDAO.findOwnJournal(person, category,
					start);
		}
		if (StringUtils.trimToNull(category) == null) {
			return journalDAO.findOwnJournal(person,
					tokenize(searchString), start);
		}
		return journalDAO.findOwnJournal(person, category,
				tokenize(searchString), start);

	}

	public List<Journal> findWorldJournal(Person person, String category,
			String searchString, int start) {
		if (StringUtils.trimToNull(searchString) == null) {
			if (StringUtils.trimToNull(category) == null) {
				return journalDAO.findWorldJournal(person, start);
			}
			return journalDAO.findWorldJournal(person, category,
					start);
		}
		if (StringUtils.trimToNull(category) == null) {
			return journalDAO.findWorldJournal(person,
					tokenize(searchString), start);
		}
		return journalDAO.findWorldJournal(person, category,
				tokenize(searchString), start);
	}

	public List<Journal> findOneFriendsJournal(Person friend, Person person,
			String category, String searchString, int start) {
		if (StringUtils.trimToNull(searchString) == null) {
			if (StringUtils.trimToNull(category) == null) {
				return journalDAO.findOneFriendsJournal(friend,
						person, start);
			}
			return journalDAO.findOneFriendsJournal(friend,
					person, category, start);
		}
		if (StringUtils.trimToNull(category) == null) {
			return journalDAO.findOneFriendsJournal(friend,
					person, tokenize(searchString), start);
		}
		return journalDAO.findOneFriendsJournal(friend, person,
				category, tokenize(searchString), start);
	}

	public List<Journal> findAllFriendsJournal(Person person, String category,
			String searchString, int start) {
		if (StringUtils.trimToNull(searchString) == null) {
			if (StringUtils.trimToNull(category) == null) {
				return journalDAO.findAllFriendsJournal(person,
						start);
			}
			return journalDAO.findAllFriendsJournal(person,
					category, start);
		}
		if (StringUtils.trimToNull(category) == null) {
			return journalDAO.findAllFriendsJournal(person,
					tokenize(searchString), start);
		}
		return journalDAO.findAllFriendsJournal(person, category,
				tokenize(searchString), start);
	}
}
