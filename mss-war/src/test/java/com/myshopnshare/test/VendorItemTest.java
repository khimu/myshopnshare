package com.myshopnshare.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.EmailDAO;
import com.myshopnshare.core.dao.FriendDAO;
import com.myshopnshare.core.dao.ItemCategoryDAO;
import com.myshopnshare.core.dao.ItemVisibilityDomainDAO;
import com.myshopnshare.core.dao.PersonDAO;
import com.myshopnshare.core.dao.UserDAO;
import com.myshopnshare.core.dao.VendorItemDAO;
import com.myshopnshare.core.dao.VisibilityDomainDAO;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.VendorItem;
import com.myshopnshare.core.domain.VisibilityDomain;
import com.myshopnshare.core.enums.CategoryType;
import com.myshopnshare.core.enums.ItemType;
import com.myshopnshare.core.enums.UserType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "applicationContext.xml",
		"applicationContext-dao.xml", "applicationContext-service.xml" })
@Transactional
public class VendorItemTest {
	@Autowired
	private EmailDAO emailDAO;

	@Autowired
	private PersonDAO personDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private VendorItemDAO vendorItemDAO;

	@Autowired
	private FriendDAO friendDAO;

	@Autowired
	private ItemCategoryDAO itemCategoryDAO;

	@Autowired
	private ItemVisibilityDomainDAO itemVisibilityDomainDAO;

	@Autowired
	private VisibilityDomainDAO visibilityDAO;

	private final static SimpleDateFormat sdf = new SimpleDateFormat();
	static {
		sdf.applyPattern("MM/dd/yyyy");
	}

	private MockScenario mock = new MockScenario();

	@Test
	public void testHasHottest() {
		Person person = mock.person();
		personDAO.save(person);

		VisibilityDomain vd = mock.makeVisibility();
		visibilityDAO.save(vd);

		VendorItem item = mock.addVendorItem(person);
		mock.addVendorItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addPublicVisibility(vd, item);
		vendorItemDAO.save(item);

		List s = personDAO.getAll();
		assertNotNull(s);
		assertEquals(1, s.size());

		List s3 = vendorItemDAO.getAll();
		assertNotNull(s3);
		assertEquals(1, s3.size());

		List s2 = itemCategoryDAO.getAll();
		assertNotNull(s2);
		assertEquals(3, s2.size());

		// THE TEST
		List l2 = vendorItemDAO.findHotestItems(0);
		assertNotNull(l2);
		assertEquals(1, l2.size());
		assertEquals(VendorItem.class, l2.get(0).getClass());
		
		VendorItem t = vendorItemDAO.findSleekSwapItem("Khim");
	}

	@Test
	public void testHasHottestByCategory() {
		Person person = mock.person();
		personDAO.save(person);

		VisibilityDomain vd = mock.makeVisibility();
		visibilityDAO.save(vd);

		VendorItem item = mock.addVendorItem(person);
		mock.addVendorItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addPublicVisibility(vd, item);
		mock.addTag(item);
		vendorItemDAO.save(item);

		List s = personDAO.getAll();
		assertNotNull(s);
		assertEquals(1, s.size());

		List s3 = vendorItemDAO.getAll();
		assertNotNull(s3);
		assertEquals(1, s3.size());

		List s2 = itemCategoryDAO.getAll();
		assertNotNull(s2);
		assertEquals(3, s2.size());

		// THE TEST
		List l2 = vendorItemDAO.findHotestItems(Arrays.asList(new String[] {"Khim"}), 0);
		assertNotNull(l2);
		assertEquals(1, l2.size());
		assertEquals(VendorItem.class, l2.get(0).getClass());
	}
	
	@Test
	public void testHasHottestByTag() {
		Person person = mock.person();
		personDAO.save(person);

		VisibilityDomain vd = mock.makeVisibility();
		visibilityDAO.save(vd);

		VendorItem item = mock.addVendorItem(person);
		mock.addVendorItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item, CategoryType.ADVICE);
		mock.addPublicVisibility(vd, item);
		mock.addTag(item);
		vendorItemDAO.save(item);

		List s = personDAO.getAll();
		assertNotNull(s);
		assertEquals(1, s.size());

		List s3 = vendorItemDAO.getAll();
		assertNotNull(s3);
		assertEquals(1, s3.size());

		List s2 = itemCategoryDAO.getAll();
		assertNotNull(s2);
		assertEquals(4, s2.size());

		// THE TEST
		List l2 = vendorItemDAO.findHotestItems(CategoryType.ADVICE,0);
		assertNotNull(l2);
		assertEquals(1, l2.size());
		assertEquals(VendorItem.class, l2.get(0).getClass());
	}

	@Test
	public void testHasHottestByBoth() {
		Person person = mock.person();
		personDAO.save(person);

		VisibilityDomain vd = mock.makeVisibility();
		visibilityDAO.save(vd);

		VendorItem item = mock.addVendorItem(person);
		mock.addVendorItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item, CategoryType.ADVICE);
		mock.addPublicVisibility(vd, item);
		mock.addTag(item);
		vendorItemDAO.save(item);
		
		VendorItem item2 = mock.addVendorItem(person);
		mock.addVendorItemCategory(person, item2);
		mock.addItemCategory(person, item2);
		mock.addItemCategory(person, item2);
		mock.addItemCategory(person, item2, CategoryType.ADVICE);
		mock.addPublicVisibility(vd, item2);
		mock.addTag(item2);
		vendorItemDAO.save(item2);

		List s = personDAO.getAll();
		assertNotNull(s);
		assertEquals(1, s.size());

		List s3 = vendorItemDAO.getAll();
		assertNotNull(s3);
		assertEquals(2, s3.size());

		List s2 = itemCategoryDAO.getAll();
		assertNotNull(s2);
		assertEquals(8, s2.size());

		// THE TEST
		List l2 = vendorItemDAO.findHotestItems(Arrays.asList(new String[] {"Khim"}), CategoryType.ADVICE, 0);
		assertNotNull(l2);
		assertEquals(2, l2.size());
		assertEquals(VendorItem.class, l2.get(0).getClass());
	}
	
	/** CHEAPEST **/
	@Test
	public void testHasCheapest() {
		Person person = mock.person();
		personDAO.save(person);

		VisibilityDomain vd = mock.makeVisibility();
		visibilityDAO.save(vd);

		VendorItem item = mock.addVendorItem(person);
		mock.addVendorItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addPublicVisibility(vd, item);
		vendorItemDAO.save(item);

		List s = personDAO.getAll();
		assertNotNull(s);
		assertEquals(1, s.size());

		List s3 = vendorItemDAO.getAll();
		assertNotNull(s3);
		assertEquals(1, s3.size());

		List s2 = itemCategoryDAO.getAll();
		assertNotNull(s2);
		assertEquals(3, s2.size());

		// THE TEST
		List l2 = vendorItemDAO.findCheapest(0);
		assertNotNull(l2);
		assertEquals(1, l2.size());
		assertEquals(VendorItem.class, l2.get(0).getClass());
	}

	@Test
	public void testHasCheapestByCategory() {
		Person person = mock.person();
		personDAO.save(person);

		VisibilityDomain vd = mock.makeVisibility();
		visibilityDAO.save(vd);

		VendorItem item = mock.addVendorItem(person);
		mock.addVendorItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addPublicVisibility(vd, item);
		mock.addTag(item);
		vendorItemDAO.save(item);

		List s = personDAO.getAll();
		assertNotNull(s);
		assertEquals(1, s.size());

		List s3 = vendorItemDAO.getAll();
		assertNotNull(s3);
		assertEquals(1, s3.size());

		List s2 = itemCategoryDAO.getAll();
		assertNotNull(s2);
		assertEquals(3, s2.size());

		// THE TEST
		List l2 = vendorItemDAO.findCheapest(Arrays.asList(new String[] {"Khim"}), 0);
		assertNotNull(l2);
		assertEquals(1, l2.size());
		assertEquals(VendorItem.class, l2.get(0).getClass());
	}
	
	@Test
	public void testHasCheapestByTag() {
		Person person = mock.person();
		personDAO.save(person);

		VisibilityDomain vd = mock.makeVisibility();
		visibilityDAO.save(vd);

		VendorItem item = mock.addVendorItem(person);
		mock.addVendorItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item, CategoryType.ADVICE);
		mock.addPublicVisibility(vd, item);
		mock.addTag(item);
		vendorItemDAO.save(item);

		List s = personDAO.getAll();
		assertNotNull(s);
		assertEquals(1, s.size());

		List s3 = vendorItemDAO.getAll();
		assertNotNull(s3);
		assertEquals(1, s3.size());

		List s2 = itemCategoryDAO.getAll();
		assertNotNull(s2);
		assertEquals(4, s2.size());

		// THE TEST
		List l2 = vendorItemDAO.findCheapest(CategoryType.ADVICE, 0);
		assertNotNull(l2);
		assertEquals(1, l2.size());
		assertEquals(VendorItem.class, l2.get(0).getClass());
	}

	@Test
	public void testHasCheapestByBoth() {
		Person person = mock.person();
		personDAO.save(person);

		VisibilityDomain vd = mock.makeVisibility();
		visibilityDAO.save(vd);

		VendorItem item = mock.addVendorItem(person);
		mock.addVendorItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item, CategoryType.ADVICE);
		mock.addPublicVisibility(vd, item);
		mock.addTag(item);
		vendorItemDAO.save(item);
		
		VendorItem item2 = mock.addVendorItem(person);
		mock.addVendorItemCategory(person, item2);
		mock.addItemCategory(person, item2);
		mock.addItemCategory(person, item2);
		mock.addItemCategory(person, item2, CategoryType.ADVICE);
		mock.addPublicVisibility(vd, item2);
		mock.addTag(item2);
		vendorItemDAO.save(item2);

		List s = personDAO.getAll();
		assertNotNull(s);
		assertEquals(1, s.size());

		List s3 = vendorItemDAO.getAll();
		assertNotNull(s3);
		assertEquals(2, s3.size());

		List s2 = itemCategoryDAO.getAll();
		assertNotNull(s2);
		assertEquals(8, s2.size());

		// THE TEST
		List l2 = vendorItemDAO.findCheapest(Arrays.asList(new String[] {"Khim"}), CategoryType.ADVICE, 0);
		assertNotNull(l2);
		assertEquals(2, l2.size());
		assertEquals(VendorItem.class, l2.get(0).getClass());
	}
	
	//** REBATES ** //
	
	@Test
	public void testHasRebates() {
		Person person = mock.person();
		personDAO.save(person);

		VisibilityDomain vd = mock.makeVisibility();
		visibilityDAO.save(vd);

		VendorItem item = mock.addVendorItem(person);
		mock.addVendorItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addPublicVisibility(vd, item);
		vendorItemDAO.save(item);

		List s = personDAO.getAll();
		assertNotNull(s);
		assertEquals(1, s.size());

		List s3 = vendorItemDAO.getAll();
		assertNotNull(s3);
		assertEquals(1, s3.size());

		List s2 = itemCategoryDAO.getAll();
		assertNotNull(s2);
		assertEquals(3, s2.size());

		// THE TEST
		List l2 = vendorItemDAO.findRebates(0);
		assertNotNull(l2);
		assertEquals(1, l2.size());
		assertEquals(VendorItem.class, l2.get(0).getClass());
	}

	@Test
	public void testHasRebatesByCategory() {
		Person person = mock.person();
		personDAO.save(person);

		VisibilityDomain vd = mock.makeVisibility();
		visibilityDAO.save(vd);

		VendorItem item = mock.addVendorItem(person);
		mock.addVendorItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addPublicVisibility(vd, item);
		mock.addTag(item);
		vendorItemDAO.save(item);

		List s = personDAO.getAll();
		assertNotNull(s);
		assertEquals(1, s.size());

		List s3 = vendorItemDAO.getAll();
		assertNotNull(s3);
		assertEquals(1, s3.size());

		List s2 = itemCategoryDAO.getAll();
		assertNotNull(s2);
		assertEquals(3, s2.size());

		// THE TEST
		List l2 = vendorItemDAO.findRebates(Arrays.asList(new String[] {"Khim"}), 0);
		assertNotNull(l2);
		assertEquals(1, l2.size());
		assertEquals(VendorItem.class, l2.get(0).getClass());
	}
	
	@Test
	public void testHasRebatesByTag() {
		Person person = mock.person();
		personDAO.save(person);

		VisibilityDomain vd = mock.makeVisibility();
		visibilityDAO.save(vd);

		VendorItem item = mock.addVendorItem(person);
		mock.addVendorItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item, CategoryType.ADVICE);
		mock.addPublicVisibility(vd, item);
		mock.addTag(item);
		vendorItemDAO.save(item);

		List s = personDAO.getAll();
		assertNotNull(s);
		assertEquals(1, s.size());

		List s3 = vendorItemDAO.getAll();
		assertNotNull(s3);
		assertEquals(1, s3.size());

		List s2 = itemCategoryDAO.getAll();
		assertNotNull(s2);
		assertEquals(4, s2.size());

		// THE TEST
		List l2 = vendorItemDAO.findRebates(CategoryType.ADVICE, 0);
		assertNotNull(l2);
		assertEquals(1, l2.size());
		assertEquals(VendorItem.class, l2.get(0).getClass());
	}

	@Test
	public void testHasRebatesByBoth() {
		Person person = mock.person();
		personDAO.save(person);

		VisibilityDomain vd = mock.makeVisibility();
		visibilityDAO.save(vd);

		VendorItem item = mock.addVendorItem(person);
		mock.addVendorItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item, CategoryType.ADVICE);
		mock.addPublicVisibility(vd, item);
		mock.addTag(item);
		vendorItemDAO.save(item);
		
		VendorItem item2 = mock.addVendorItem(person);
		mock.addVendorItemCategory(person, item2);
		mock.addItemCategory(person, item2);
		mock.addItemCategory(person, item2);
		mock.addItemCategory(person, item2, CategoryType.ADVICE);
		mock.addPublicVisibility(vd, item2);
		mock.addTag(item2);
		vendorItemDAO.save(item2);

		List s = personDAO.getAll();
		assertNotNull(s);
		assertEquals(1, s.size());

		List s3 = vendorItemDAO.getAll();
		assertNotNull(s3);
		assertEquals(2, s3.size());

		List s2 = itemCategoryDAO.getAll();
		assertNotNull(s2);
		assertEquals(8, s2.size());

		// THE TEST
		List l2 = vendorItemDAO.findRebates(Arrays.asList(new String[] {"Khim"}), CategoryType.ADVICE, 0);
		assertNotNull(l2);
		assertEquals(2, l2.size());
		assertEquals(VendorItem.class, l2.get(0).getClass());
	}
	
	// CLEARANCE //
	@Test
	public void testHasClearance() {
		Person person = mock.person();
		personDAO.save(person);

		VisibilityDomain vd = mock.makeVisibility();
		visibilityDAO.save(vd);

		VendorItem item = mock.addVendorItem(person);
		mock.addVendorItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addPublicVisibility(vd, item);
		vendorItemDAO.save(item);

		List s = personDAO.getAll();
		assertNotNull(s);
		assertEquals(1, s.size());

		List s3 = vendorItemDAO.getAll();
		assertNotNull(s3);
		assertEquals(1, s3.size());

		List s2 = itemCategoryDAO.getAll();
		assertNotNull(s2);
		assertEquals(3, s2.size());

		// THE TEST
		List l2 = vendorItemDAO.findClearance(0);
		assertNotNull(l2);
		assertEquals(1, l2.size());
		assertEquals(VendorItem.class, l2.get(0).getClass());
	}

	@Test
	public void testHasClearanceByCategory() {
		Person person = mock.person();
		personDAO.save(person);

		VisibilityDomain vd = mock.makeVisibility();
		visibilityDAO.save(vd);

		VendorItem item = mock.addVendorItem(person);
		mock.addVendorItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addPublicVisibility(vd, item);
		mock.addTag(item);
		vendorItemDAO.save(item);

		List s = personDAO.getAll();
		assertNotNull(s);
		assertEquals(1, s.size());

		List s3 = vendorItemDAO.getAll();
		assertNotNull(s3);
		assertEquals(1, s3.size());

		List s2 = itemCategoryDAO.getAll();
		assertNotNull(s2);
		assertEquals(3, s2.size());

		// THE TEST
		List l2 = vendorItemDAO.findClearance(Arrays.asList(new String[] {"Khim"}), 0);
		assertNotNull(l2);
		assertEquals(1, l2.size());
		assertEquals(VendorItem.class, l2.get(0).getClass());
	}
	
	@Test
	public void testHasClearanceByTag() {
		Person person = mock.person();
		personDAO.save(person);

		VisibilityDomain vd = mock.makeVisibility();
		visibilityDAO.save(vd);

		VendorItem item = mock.addVendorItem(person);
		mock.addVendorItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item, CategoryType.ADVICE);
		mock.addPublicVisibility(vd, item);
		mock.addTag(item);
		vendorItemDAO.save(item);

		List s = personDAO.getAll();
		assertNotNull(s);
		assertEquals(1, s.size());

		List s3 = vendorItemDAO.getAll();
		assertNotNull(s3);
		assertEquals(1, s3.size());

		List s2 = itemCategoryDAO.getAll();
		assertNotNull(s2);
		assertEquals(4, s2.size());

		// THE TEST
		List l2 = vendorItemDAO.findClearance(CategoryType.ADVICE, 0);
		assertNotNull(l2);
		assertEquals(1, l2.size());
		assertEquals(VendorItem.class, l2.get(0).getClass());
	}

	@Test
	public void testHasClearanceByBoth() {
		Person person = mock.person();
		personDAO.save(person);

		VisibilityDomain vd = mock.makeVisibility();
		visibilityDAO.save(vd);

		VendorItem item = mock.addVendorItem(person);
		mock.addVendorItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item, CategoryType.ADVICE);
		mock.addPublicVisibility(vd, item);
		mock.addTag(item);
		vendorItemDAO.save(item);
		
		VendorItem item2 = mock.addVendorItem(person);
		mock.addVendorItemCategory(person, item2);
		mock.addItemCategory(person, item2);
		mock.addItemCategory(person, item2);
		mock.addItemCategory(person, item2, CategoryType.ADVICE);
		mock.addPublicVisibility(vd, item2);
		mock.addTag(item2);
		vendorItemDAO.save(item2);

		List s = personDAO.getAll();
		assertNotNull(s);
		assertEquals(1, s.size());

		List s3 = vendorItemDAO.getAll();
		assertNotNull(s3);
		assertEquals(2, s3.size());

		List s2 = itemCategoryDAO.getAll();
		assertNotNull(s2);
		assertEquals(8, s2.size());

		// THE TEST
		List l2 = vendorItemDAO.findClearance(Arrays.asList(new String[] {"Khim"}), CategoryType.ADVICE, 0);
		assertNotNull(l2);
		assertEquals(2, l2.size());
		assertEquals(VendorItem.class, l2.get(0).getClass());
	}
	
	/** FREE **/
	@Test
	public void testHasFree() {
		Person person = mock.person();
		personDAO.save(person);

		VisibilityDomain vd = mock.makeVisibility();
		visibilityDAO.save(vd);

		VendorItem item = mock.addVendorItem(person);
		item.setPrice(Float.valueOf(0));
		mock.addVendorItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addPublicVisibility(vd, item);
		vendorItemDAO.save(item);

		List s = personDAO.getAll();
		assertNotNull(s);
		assertEquals(1, s.size());

		List s3 = vendorItemDAO.getAll();
		assertNotNull(s3);
		assertEquals(1, s3.size());

		List s2 = itemCategoryDAO.getAll();
		assertNotNull(s2);
		assertEquals(3, s2.size());

		// THE TEST
		List l2 = vendorItemDAO.findFree(0);
		assertNotNull(l2);
		assertEquals(1, l2.size());
		assertEquals(VendorItem.class, l2.get(0).getClass());
	}

	@Test
	public void testHasFreeByCategory() {
		Person person = mock.person();
		personDAO.save(person);

		VisibilityDomain vd = mock.makeVisibility();
		visibilityDAO.save(vd);

		VendorItem item = mock.addVendorItem(person);
		item.setPrice(Float.valueOf(0));
		mock.addVendorItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addPublicVisibility(vd, item);
		mock.addTag(item);
		vendorItemDAO.save(item);

		List s = personDAO.getAll();
		assertNotNull(s);
		assertEquals(1, s.size());

		List s3 = vendorItemDAO.getAll();
		assertNotNull(s3);
		assertEquals(1, s3.size());

		List s2 = itemCategoryDAO.getAll();
		assertNotNull(s2);
		assertEquals(3, s2.size());

		// THE TEST
		List l2 = vendorItemDAO.findFree(Arrays.asList(new String[] {"Khim"}), 0);
		assertNotNull(l2);
		assertEquals(1, l2.size());
		assertEquals(VendorItem.class, l2.get(0).getClass());
	}
	
	@Test
	public void testHasFreeByTag() {
		Person person = mock.person();
		personDAO.save(person);

		VisibilityDomain vd = mock.makeVisibility();
		visibilityDAO.save(vd);

		VendorItem item = mock.addVendorItem(person);
		item.setPrice(Float.valueOf(0));
		mock.addVendorItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item, CategoryType.ADVICE);
		mock.addPublicVisibility(vd, item);
		mock.addTag(item);
		vendorItemDAO.save(item);

		List s = personDAO.getAll();
		assertNotNull(s);
		assertEquals(1, s.size());

		List s3 = vendorItemDAO.getAll();
		assertNotNull(s3);
		assertEquals(1, s3.size());

		List s2 = itemCategoryDAO.getAll();
		assertNotNull(s2);
		assertEquals(4, s2.size());

		// THE TEST
		List l2 = vendorItemDAO.findFree(CategoryType.ADVICE, 0);
		assertNotNull(l2);
		assertEquals(1, l2.size());
		assertEquals(VendorItem.class, l2.get(0).getClass());
	}

	@Test
	public void testHasFreeByBoth() {
		Person person = mock.person();
		personDAO.save(person);

		VisibilityDomain vd = mock.makeVisibility();
		visibilityDAO.save(vd);

		VendorItem item = mock.addVendorItem(person);
		item.setPrice(Float.valueOf(0));
		mock.addVendorItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item, CategoryType.ADVICE);
		mock.addPublicVisibility(vd, item);
		mock.addTag(item);
		vendorItemDAO.save(item);
		
		VendorItem item2 = mock.addVendorItem(person);
		mock.addVendorItemCategory(person, item2);
		mock.addItemCategory(person, item2);
		mock.addItemCategory(person, item2);
		mock.addItemCategory(person, item2, CategoryType.ADVICE);
		mock.addPublicVisibility(vd, item2);
		mock.addTag(item2);
		vendorItemDAO.save(item2);

		List s = personDAO.getAll();
		assertNotNull(s);
		assertEquals(1, s.size());

		List s3 = vendorItemDAO.getAll();
		assertNotNull(s3);
		assertEquals(2, s3.size());

		List s2 = itemCategoryDAO.getAll();
		assertNotNull(s2);
		assertEquals(8, s2.size());

		// THE TEST
		List l2 = vendorItemDAO.findFree(Arrays.asList(new String[] {"Khim"}), CategoryType.ADVICE, 0);
		assertNotNull(l2);
		assertEquals(1, l2.size());
		assertEquals(VendorItem.class, l2.get(0).getClass());
	}
	
	/** LATEST **/
	@Test
	public void testHasLatest() {
		Person person = mock.person();
		personDAO.save(person);

		VisibilityDomain vd = mock.makeVisibility();
		visibilityDAO.save(vd);

		VendorItem item = mock.addVendorItem(person);
		mock.addVendorItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addPublicVisibility(vd, item);
		vendorItemDAO.save(item);

		List s = personDAO.getAll();
		assertNotNull(s);
		assertEquals(1, s.size());

		List s3 = vendorItemDAO.getAll();
		assertNotNull(s3);
		assertEquals(1, s3.size());

		List s2 = itemCategoryDAO.getAll();
		assertNotNull(s2);
		assertEquals(3, s2.size());

		// THE TEST
		List l2 = vendorItemDAO.findWorldVendorItems(0);
		assertNotNull(l2);
		assertEquals(1, l2.size());
		assertEquals(VendorItem.class, l2.get(0).getClass());
	}

	@Test
	public void testHasLatestByTag() {
		Person person = mock.person();
		personDAO.save(person);

		VisibilityDomain vd = mock.makeVisibility();
		visibilityDAO.save(vd);

		VendorItem item = mock.addVendorItem(person);
		item.setType(ItemType.VENDOR);
		mock.addVendorItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addPublicVisibility(vd, item);
		mock.addTag(item);
		vendorItemDAO.save(item);

		List s = personDAO.getAll();
		assertNotNull(s);
		assertEquals(1, s.size());

		List s3 = vendorItemDAO.getAll();
		assertNotNull(s3);
		assertEquals(1, s3.size());

		List s2 = itemCategoryDAO.getAll();
		assertNotNull(s2);
		assertEquals(3, s2.size());

		// THE TEST
		List l2 = vendorItemDAO.findWorldVendorItems(Arrays.asList(new String[] {"Khim"}), 0);
		assertNotNull(l2);
		assertEquals(1, l2.size());
		assertEquals(VendorItem.class, l2.get(0).getClass());
	}
	
	@Test
	public void testHasLatestByCategory() {
		Person person = mock.person();
		personDAO.save(person);

		VisibilityDomain vd = mock.makeVisibility();
		visibilityDAO.save(vd);

		VendorItem item = mock.addVendorItem(person);
		mock.addVendorItemCategory(person, item);
		item.setType(ItemType.VENDOR);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item, CategoryType.ADVICE);
		mock.addPublicVisibility(vd, item);
		mock.addTag(item);
		vendorItemDAO.save(item);
		
		VendorItem item2 = mock.addVendorItem(person);
		mock.addVendorItemCategory(person, item2);
		item2.setType(ItemType.VENDOR);
		mock.addItemCategory(person, item2);
		mock.addItemCategory(person, item2);
		mock.addItemCategory(person, item2, CategoryType.ADVICE);
		mock.addPublicVisibility(vd, item2);
		mock.addTag(item2);
		vendorItemDAO.save(item2);

		List s = personDAO.getAll();
		assertNotNull(s);
		assertEquals(1, s.size());

		List s3 = vendorItemDAO.getAll();
		assertNotNull(s3);
		assertEquals(2, s3.size());

		List s2 = itemCategoryDAO.getAll();
		assertNotNull(s2);
		assertEquals(8, s2.size());

		// THE TEST
		List l2 = vendorItemDAO.findWorldVendorItems(CategoryType.ADVICE, 0);
		assertNotNull(l2);
		assertEquals(2, l2.size());
		assertEquals(VendorItem.class, l2.get(0).getClass());
	}

	@Test
	public void testHasLatestByBoth() {
		Person person = mock.person();
		personDAO.save(person);

		VisibilityDomain vd = mock.makeVisibility();
		visibilityDAO.save(vd);

		VendorItem item = mock.addVendorItem(person);
		mock.addVendorItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item);
		mock.addItemCategory(person, item, CategoryType.ADVICE);
		mock.addPublicVisibility(vd, item);
		mock.addTag(item);
		vendorItemDAO.save(item);
		
		VendorItem item2 = mock.addVendorItem(person);
		mock.addVendorItemCategory(person, item2);
		mock.addItemCategory(person, item2);
		mock.addItemCategory(person, item2);
		mock.addItemCategory(person, item2, CategoryType.ADVICE);
		mock.addPublicVisibility(vd, item2);
		mock.addTag(item2);
		vendorItemDAO.save(item2);

		List s = personDAO.getAll();
		assertNotNull(s);
		assertEquals(1, s.size());

		List s3 = vendorItemDAO.getAll();
		assertNotNull(s3);
		assertEquals(2, s3.size());

		List s2 = itemCategoryDAO.getAll();
		assertNotNull(s2);
		assertEquals(8, s2.size());

		// THE TEST
		List l2 = vendorItemDAO.findWorldVendorItems(Arrays.asList(new String[] {"Khim"}), CategoryType.ADVICE, 0);
		assertNotNull(l2);
		assertEquals(2, l2.size());
		assertEquals(VendorItem.class, l2.get(0).getClass());
	}
	
}
