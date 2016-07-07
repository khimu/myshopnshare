package com.myshopnshare.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.EmailDAO;
import com.myshopnshare.core.dao.FriendDAO;
import com.myshopnshare.core.dao.ItemDAO;
import com.myshopnshare.core.dao.PersonDAO;
import com.myshopnshare.core.dao.VisibilityDomainDAO;
import com.myshopnshare.core.domain.Friend;
import com.myshopnshare.core.domain.FriendVisibility;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.VisibilityDomain;
import com.myshopnshare.dao.UserDao;

//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "applicationContext.xml",
		"applicationContext-dao.xml", "applicationContext-service.xml" })
@Transactional
public class FriendTest {
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
	private VisibilityDomainDAO visibilityDAO;

	private MockScenario mock = new MockScenario();

	@Test
	public void testHas() {
		Person friendsFriend = mock.person();
		personDAO.save(friendsFriend);

		Person person = mock.person();
		Friend ff = mock.addFriends(person, friendsFriend);
		personDAO.save(person);

		Long expectedFriendId = ff.getId();

		VisibilityDomain vd = mock.makeVisibility();
		visibilityDAO.save(vd);
		FriendVisibility fv = new FriendVisibility();
		fv.setFriend(ff);
		fv.setVisibilityDomain(vd);
		ff.getFriendVisibilities().add(fv);

		friendDAO.update(ff);

		Person authenticated = mock.person();
		Friend f = mock.addFriends(authenticated, person);
		personDAO.save(authenticated);

		VisibilityDomain vd2 = mock.makeVisibility();
		visibilityDAO.save(vd2);
		FriendVisibility fv2 = new FriendVisibility();
		fv2.setFriend(f);
		fv2.setVisibilityDomain(vd2);
		f.getFriendVisibilities().add(fv2);

		friendDAO.update(f);

		List s = personDAO.getAll();
		assertNotNull(s);
		assertEquals(3, s.size());

		List s4 = friendDAO.getAll();
		assertNotNull(s4);
		assertEquals(2, s4.size());
		assertNotNull(s4.get(0));
		assertNotNull(((Friend) s4.get(0)).getFriendVisibilities());
		assertNotNull(((Friend) s4.get(0)).getFriendVisibilities().get(0));
		assertEquals(1, ((Friend) s4.get(0)).getFriendVisibilities().size());

		List friends = friendDAO.findOwnFriend(authenticated, 0);
		assertNotNull(friends);
		assertEquals(1, friends.size());

		List friends2 = friendDAO.findOwnFriend(authenticated, "red", 0);
		assertNotNull(friends2);
		assertEquals(1, friends2.size());

		List friends3 = friendDAO.findOwnFriend(person, 0);
		assertNotNull(friends3);
		assertEquals(1, friends3.size());

		List friends4 = friendDAO
				.findOneFriendsFriend(person, authenticated, 0);
		assertNotNull(friends4);
		assertEquals(1, friends4.size());

		List friends5 = friendDAO
				.findOneFriendsFriend(person, authenticated, 0);
		assertNotNull(friends5);
		assertEquals(1, friends5.size());

		assertEquals(expectedFriendId, ((Friend) friends5.get(0)).getId());

		/** YOURSELF IS YOUR FRIEND **/
		List friends6 = friendDAO.findOneFriendsFriend(person, person, 0);
		assertNotNull(friends6);
		assertEquals(0, friends6.size());
	}

}
