package com.myshopnshare.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.EmailDAO;
import com.myshopnshare.core.dao.ItemOrderDetailDAO;
import com.myshopnshare.core.dao.PersonDAO;
import com.myshopnshare.core.domain.ItemOrderDetail;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.enums.OrderStatus;
//
//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "applicationContext.xml",
		"applicationContext-dao.xml", "applicationContext-service.xml" })
@Transactional
public class ItemOrderTest {
	@Autowired
	private EmailDAO emailDAO;

	@Autowired
	private PersonDAO personDAO;

	@Autowired
	private ItemOrderDetailDAO itemOrderDetailDAO;

	private MockScenario mock = new MockScenario();

	@Test
	public void testSearchByAll() {
		Person person = mock.person();
		
		ItemOrderDetail iod = new ItemOrderDetail();
		iod.setInvoice("111");
		iod.setPerson(person);
		iod.setStatus(OrderStatus.PENDING_APPROVAL);
		
		person.getItemOrderDetails().add(iod);
		personDAO.save(person);
		
		List<ItemOrderDetail> iods = itemOrderDetailDAO.findAllPendingOrderFor(person);
		assertNotNull(iods);
		assertEquals(1, iods.size());
		assertEquals(ItemOrderDetail.class, iods.get(0).getClass());
	}
}
