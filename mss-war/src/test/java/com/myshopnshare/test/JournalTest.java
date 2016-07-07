package com.myshopnshare.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.JournalDAO;
import com.myshopnshare.core.dao.NewsDAO;
import com.myshopnshare.core.dao.PersonDAO;
import com.myshopnshare.core.dao.VisibilityDomainDAO;
import com.myshopnshare.core.domain.Journal;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.VisibilityDomain;
import com.myshopnshare.core.domain.VisibilityDomainPerson;

//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "applicationContext.xml",
		"applicationContext-dao.xml", "applicationContext-service.xml" })
@Transactional
public class JournalTest {
	@Autowired
	private NewsDAO newsDAO;

	@Autowired
	private PersonDAO personDAO;

	@Autowired
	private JournalDAO journalDAO;

	@Autowired
	private VisibilityDomainDAO visibilityDAO;

	private MockScenario mock = new MockScenario();

	@Test
	public void testOwnFinds() {
		VisibilityDomain vd = mock.makeVisibility();

		
		Person person = mock.person();
		
		VisibilityDomainPerson vdp = new VisibilityDomainPerson();
		vdp.setVisibilityDomain(vd);
		vdp.setPerson(person);
		
		person.getVisibilityGroups().add(vdp);
		personDAO.save(person);

		Journal journal = mock.addJournal(person);
		person.getJournalPermission(journal);
		journalDAO.save(journal);

		Journal actualJournal = journalDAO.get(journal.getId());
		assertNotNull(actualJournal);
		assertEquals(2, actualJournal.getJournalVisibilities().size());

		List<Journal> retval = journalDAO.findOwnJournal(person, 0);
		
		assertNotNull(retval);
		assertEquals(1, retval.size());
		
		Person friend = mock.person();
		mock.addFriends(friend, person);
		personDAO.save(friend);
	}
	
	@Test
	public void testWorldFinds() {
		VisibilityDomain vd = mock.makeVisibility();
		Person person = mock.person();
		
		VisibilityDomainPerson vdp = new VisibilityDomainPerson();
		vdp.setVisibilityDomain(vd);
		vdp.setPerson(person);
		
		person.getVisibilityGroups().add(vdp);
		personDAO.save(person);

		Journal journal = mock.addJournal(person);
		person.getJournalPermission(journal);
		journalDAO.save(journal);

		Journal actualJournal = journalDAO.get(journal.getId());
		assertNotNull(actualJournal);
		assertEquals(2, actualJournal.getJournalVisibilities().size());

		List<Journal> retval = journalDAO.findOwnJournal(person, 0);
		
		assertNotNull(retval);
		assertEquals(1, retval.size());
	}
}
