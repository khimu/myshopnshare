package com.myshopnshare.test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.EmailDAO;
import com.myshopnshare.core.dao.FriendDAO;
import com.myshopnshare.core.dao.ItemDAO;
import com.myshopnshare.core.dao.PersonDAO;
import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.CategoryType;
import com.myshopnshare.dao.UserDao;


//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"applicationContext.xml", "applicationContext-dao.xml", "applicationContext-service.xml"})
@Transactional
public class ItemTest extends AbstractDependencyInjectionSpringContextTests {

	@Autowired
	private EmailDAO emailDAO;
		
	@Autowired
	private PersonDAO personDAO;
	
	@Autowired
	private UserDao userDAO;
	
	@Autowired
	private ItemDAO itemDAO;
		
	@Autowired
	private FriendDAO friendDAO;
	
	private final static SimpleDateFormat sdf = new SimpleDateFormat();
	static {
		sdf.applyPattern("MM/dd/yyyy");
	}
	
	private MockScenario mock = new MockScenario();

	@Test
	public void testQuery() {	
		Person person = personDAO.get(new Long(1));
		
		String[] tags = {"deck", "forest"};
		
		List<Item> items = itemDAO.findOwnItems(person, CategoryType.ADVICE, 0);
		assertEquals(0, items.size());
		items = itemDAO.findOwnItems(person, CategoryType.WANT, 0);
		assertEquals(0, items.size());
		items = itemDAO.findOwnItems(person, CategoryType.SELLING, 0);
		assertEquals(0, items.size());
		items = itemDAO.findOwnItems(person, CategoryType.BOUGHT, 0);
		assertEquals(0, items.size());
		items = itemDAO.findOwnItems(person, CategoryType.RECOMMEND, 0);
		assertEquals(0, items.size());
		
		items = itemDAO.findOwnItems(person, Arrays.asList(tags), CategoryType.ADVICE, 0);
		assertEquals(0, items.size());
		items = itemDAO.findOwnItems(person, Arrays.asList(tags), CategoryType.WANT, 0);
		assertEquals(0, items.size());
		items = itemDAO.findOwnItems(person, Arrays.asList(tags), CategoryType.SELLING, 0);
		assertEquals(0, items.size());
		items = itemDAO.findOwnItems(person, Arrays.asList(tags), CategoryType.BOUGHT, 0);
		assertEquals(0, items.size());
		items = itemDAO.findOwnItems(person, Arrays.asList(tags), CategoryType.RECOMMEND, 0);
		assertEquals(0, items.size());
		
		items = itemDAO.findOwnItems(person, 0);
		assertEquals(2, items.size());

	}

}
