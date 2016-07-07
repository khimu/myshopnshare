package com.myshopnshare.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.EmailDAO;
import com.myshopnshare.core.dao.FriendDAO;
import com.myshopnshare.core.dao.ItemCategoryDAO;
import com.myshopnshare.core.dao.ItemDAO;
import com.myshopnshare.core.dao.PersonDAO;
import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.domain.ItemCategory;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.CategoryType;
import com.myshopnshare.dao.UserDao;

//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "applicationContext.xml",
		"applicationContext-dao.xml", "applicationContext-service.xml" })
@Transactional
public class FindOwnItems {
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
	
	@Autowired
	private ItemCategoryDAO itemCategoryDAO;
	
	private final static SimpleDateFormat sdf = new SimpleDateFormat();
	static {
		sdf.applyPattern("MM/dd/yyyy");
	}

	private MockScenario mock = new MockScenario();

	@Test
	public void testHas() {
		Person person = mock.person();
		personDAO.save(person);
		Item item = mock.addItem(person);
		ItemCategory ic = mock.addItemCategory(person, item);
		mock.addItemCategory(person, item);
		itemDAO.save(item);

		List s = personDAO.getAll();
		assertNotNull(s);
		assertEquals(1, s.size());

		List s3 = itemDAO.getAll();
		assertNotNull(s3);
		assertEquals(1, s3.size());
		
		List s2 = itemCategoryDAO.getAll();
		assertNotNull(s2);
		assertEquals(2, s2.size());	
		
		// No item category
		List l2 = itemDAO.findOwnItems(person, 0);
		assertNotNull(l2);
		assertEquals(1, l2.size());
		assertEquals(Item.class, l2.get(0).getClass());
	}

	@Test
	public void testHasByCategory() {
		Person person = mock.person();
		personDAO.save(person);
		Item item = mock.addItem(person);
		ItemCategory ic = mock.addItemCategory(person, item);
		itemDAO.save(item);

		List s = personDAO.getAll();
		assertNotNull(s);
		assertEquals(1, s.size());

		List s3 = itemDAO.getAll();
		assertNotNull(s3);
		assertEquals(1, s3.size());
		
		List s2 = itemCategoryDAO.getAll();
		assertNotNull(s2);
		assertEquals(1, s2.size());	
		
		// No item category
		List l2 = itemDAO.findOwnItems(person, CategoryType.RECOMMEND, 0);
		assertNotNull(l2);
		assertEquals(1, l2.size());
		assertEquals(Item.class, l2.get(0).getClass());
	}

	@Test
	public void testDontHaveByCategory() {
		Person person = mock.person();
		Item item = mock.addItem(person);
		itemDAO.save(item);
		personDAO.save(person);

		List l = personDAO.getAll();
		assertNotNull(l);
		assertEquals(1, l.size());

		// No item category
		List l2 = itemDAO.findOwnItems(person, CategoryType.RECOMMEND, 0);
		assertNotNull(l2);
		assertEquals(0, l2.size());
	}

	@Test
	public void testHasByTag() {
		Person person = mock.person();
		personDAO.save(person);
		Item item = mock.addItem(person);
		ItemCategory ic = mock.addItemCategory(person, item);
		mock.addTag(item);
		itemDAO.save(item);

		List l = personDAO.getAll();
		assertNotNull(l);
		assertEquals(1, l.size());

		// No item category
		List l2 = itemDAO.findOwnItems(person, Arrays
				.asList(new String[] { "Khim" }), 0);
		assertNotNull(l2);
		assertEquals(1, l2.size());
	}

	@Test
	public void testDontHaveByTag() {
		Person person = mock.person();
		personDAO.save(person);
		Item item = mock.addItem(person);
		ItemCategory ic = mock.addItemCategory(person, item);
		itemDAO.save(item);

		List l = personDAO.getAll();
		assertNotNull(l);
		assertEquals(1, l.size());

		// No item category
		List l2 = itemDAO.findOwnItems(person, Arrays
				.asList(new String[] { "Khim" }), 0);
		assertNotNull(l2);
		assertEquals(0, l2.size());
	}

	@Test
	public void testHasByCategoryAndTag() {
		Person person = mock.person();
		personDAO.save(person);
		Item item = mock.addItem(person);
		ItemCategory ic = mock.addItemCategory(person, item);
		mock.addTag(item);
		itemDAO.save(item);

		List l = personDAO.getAll();
		assertNotNull(l);
		assertEquals(1, l.size());

		// No item category
		List l2 = itemDAO.findOwnItems(person, Arrays
				.asList(new String[] { "Khim" }), CategoryType.RECOMMEND, 0);
		assertNotNull(l2);
		assertEquals(1, l2.size());
	}

	@Test
	public void testDontHaveByCategoryAndTag() {
		Person person = mock.person();
		personDAO.save(person);
		Item item = mock.addItem(person);
		ItemCategory ic = mock.addItemCategory(person, item);
		itemDAO.save(item);

		List l = personDAO.getAll();
		assertNotNull(l);
		assertEquals(1, l.size());

		// No item category
		List l2 = itemDAO.findOwnItems(person, Arrays
				.asList(new String[] { "Khim" }), CategoryType.RECOMMEND, 0);
		assertNotNull(l2);
		assertEquals(0, l2.size());
	}

}
