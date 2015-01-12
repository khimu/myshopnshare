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

import com.myshopnshare.core.dao.InstitutionDAO;
import com.myshopnshare.core.dao.NewsDAO;
import com.myshopnshare.core.dao.PersonDAO;
import com.myshopnshare.core.dao.VisibilityDomainDAO;
import com.myshopnshare.core.domain.News;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.VisibilityDomain;
import com.myshopnshare.core.domain.VisibilityDomainPerson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "applicationContext.xml",
		"applicationContext-dao.xml", "applicationContext-service.xml" })
@Transactional
public class NewsTest {
	@Autowired
	private NewsDAO newsDAO;

	@Autowired
	private PersonDAO personDAO;

	@Autowired
	private InstitutionDAO institutionDAO;

	@Autowired
	private VisibilityDomainDAO visibilityDAO;

	private MockScenario mock = new MockScenario();

	@Test
	public void testSearchByAll() {
		VisibilityDomain vd = mock.makeVisibility();
		vd.setDefaultVisibility(true);
		visibilityDAO.save(vd);

		Person person = mock.person();
		
		VisibilityDomainPerson vdp = new VisibilityDomainPerson();
		vdp.setVisibilityDomain(vd);
		vdp.setPerson(person);
		
		
		person.getVisibilityGroups().add(vdp);
		personDAO.save(person);
		
		Person authenticated = mock.person();
		mock.addFriends(authenticated, person);
		personDAO.save(authenticated);

		News newzz = mock.addNews(person);
		person.getNewsPermission(newzz);
		newsDAO.save(newzz);

		News actualNews = newsDAO.get(newzz.getId());
		assertNotNull(actualNews);
		assertNotNull(newzz.getNewsVisibility());
		assertEquals(2, newzz.getNewsVisibility().size());
		
		List<News> list = newsDAO.findAllFriendsNews(authenticated, 0);
		assertNotNull(list);
		assertEquals(1, list.size());
	}
}
