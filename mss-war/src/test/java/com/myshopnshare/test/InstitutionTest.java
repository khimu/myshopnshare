package com.myshopnshare.test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.EmailDAO;
import com.myshopnshare.core.dao.InstitutionDAO;
import com.myshopnshare.core.dao.PersonDAO;
import com.myshopnshare.core.domain.Institution;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "applicationContext.xml",
		"applicationContext-dao.xml", "applicationContext-service.xml" })
@Transactional
public class InstitutionTest {
	@Autowired
	private EmailDAO emailDAO;

	@Autowired
	private PersonDAO personDAO;

	@Autowired
	private InstitutionDAO institutionDAO;

	private MockScenario mock = new MockScenario();

	@Test
	public void testSearchByAll() {
		Institution inst = mock.Institution();
		institutionDAO.save(inst);
		
		Institution actualInst = institutionDAO.get(inst.getId());
		assertNotNull(actualInst);
		
		Map searchKeys = new HashMap();
		searchKeys.put("address.city", "los angeles");
		
		List<Institution> retval = institutionDAO.findBySearchString(searchKeys);
		
		assertNotNull(retval);
		assertEquals(1, retval.size());
		
	}
}
