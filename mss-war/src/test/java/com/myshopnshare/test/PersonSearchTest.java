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

import com.myshopnshare.core.dao.EmailDAO;
import com.myshopnshare.core.dao.PersonDAO;
import com.myshopnshare.core.dao.UserDAO;
import com.myshopnshare.core.domain.EmailAddress;
import com.myshopnshare.core.domain.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "applicationContext.xml",
		"applicationContext-dao.xml", "applicationContext-service.xml" })
@Transactional
public class PersonSearchTest {
	@Autowired
	private EmailDAO emailDAO;

	@Autowired
	private PersonDAO personDAO;

	@Autowired
	private UserDAO userDAO;

	private MockScenario mock = new MockScenario();
	
	@Test
	public void testFindPersonByEmail(){
		Person person = mock.person();
		EmailAddress email = person.getEmails().get(0);
		email.setEmail("kim2kim@gmail.com");
		personDAO.save(person);
		
		Person actualPerson = personDAO.get(person.getId());
		assertNotNull(actualPerson);
		assertNotNull(actualPerson.getEmails());
		assertEquals("kim2kim@gmail.com", actualPerson.getEmails().get(0).getEmail());		
		
		EmailAddress actualEmail = emailDAO.get(email.getId());
		assertNotNull(actualEmail);
		assertEquals("kim2kim@gmail.com", actualEmail.getEmail());
		
		List retval = personDAO.findBySearchString("kim2kim@gmail.com", "");
		assertNotNull(actualPerson);
		assertEquals(1, retval.size());
		assertNotNull(actualPerson.getEmails());
		assertEquals("kim2kim@gmail.com", actualPerson.getEmails().get(0).getEmail());	
	}
	
	@Test
	public void testFindPersonByFullName(){
		Person person = mock.person();
		person.setFirstName("khim");
		person.setLastName("ung");
		personDAO.save(person);
		
		Person actualPerson = personDAO.get(person.getId());
		assertNotNull(actualPerson);

		List retval = personDAO.findBySearchString("KHIM", "Ung");
		assertNotNull(actualPerson);
		assertEquals(1, retval.size());
		assertEquals("khim", actualPerson.getFirstName());	
	}
	
	@Test
	public void testFindSearchString(){
		Person person = mock.person();
		person.setFirstName("khim");
		person.setLastName("ung");
		personDAO.save(person);
		
		Person actualPerson = personDAO.get(person.getId());
		assertNotNull(actualPerson);

		List retval = personDAO.findBySearchString("KHIM");
		assertNotNull(actualPerson);
		assertEquals(1, retval.size());
		assertEquals("khim", actualPerson.getFirstName());	
	}
}
