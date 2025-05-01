# PersonServiceImpl

```java
package com.myshopnshare.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.FaceDAO;
import com.myshopnshare.core.dao.HeadlineDAO;
import com.myshopnshare.core.dao.ItemCategoryDAO;
import com.myshopnshare.core.dao.NewsDAO;
import com.myshopnshare.core.dao.PersonDAO;
import com.myshopnshare.core.dao.VisibilityDomainPersonDAO;
import com.myshopnshare.core.domain.Action;
import com.myshopnshare.core.domain.Headline;
import com.myshopnshare.core.domain.ItemCategory;
import com.myshopnshare.core.domain.News;
import com.myshopnshare.core.domain.NewsVisibilityDomain;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.VisibilityDomain;
import com.myshopnshare.core.domain.VisibilityDomainPerson;
import com.myshopnshare.core.enums.CategoryType;
import com.myshopnshare.core.enums.PermissionType;
import com.myshopnshare.core.enums.UserType;

@Service("personService")
@Transactional
public class PersonServiceImpl extends
		GenericServiceImpl<Person, Long> implements PersonService {

	@Autowired
	private ItemCategoryDAO itemCategoryDao;

	private PersonDAO personDAO;
	
	@Autowired
	private HeadlineDAO headlineDao;
	
	@Autowired
	private NewsDAO newsDao;
	
	@Autowired
	private FaceDAO faceDao;

	@Autowired
	private VisibilityDomainPersonDAO visibilityDomainPersonDao;
	
	@Autowired
	public PersonServiceImpl(PersonDAO genericDao) {
		super(genericDao);
		this.personDAO = genericDao;
	}

	public Person findPersonByUsername(String username) {
		return personDAO.findPersonByUsername(username);
	}
	
	public List<Headline> getHeadlines(String username) {
		Person person = personDAO.findPersonByUsername(username);
		return person.getHeadlines();
	}	

	public List<Person> findPersonsBySearchString(String searchString) {
		String[] fullname = searchString.split(" ");
		if (fullname.length > 1) {
			return personDAO.findBySearchString(fullname[0], fullname[1]);
		} else {
			return personDAO.findBySearchString(searchString, "");
		}
	}

	public Person findByEmail(String email) {
		return personDAO.findByEmail(email);
	}
	public List<Person> findBySearchString(String searchString){
		return personDAO.findBySearchString(searchString);
	}
	
	public boolean isMaxSelling(Person person) {
		if (person.getUserType() == UserType.SLEEKSWAP) {
			return false;
		}
		if (getCategoryCount(person, CategoryType.SELLING) < person.getSubscriptionType().getMax()) {
			return false;
		}		
		return true;
	}

	public int getCategoryCount(Person person, CategoryType category) {
		List<ItemCategory> items = itemCategoryDao.findOwnItemCategorys(person, category, 0);
		if(items == null){
			return 0;
		}
		return items.size();
	}
	
	public void updateHeadline(Person person, String message){
		Headline headline = new Headline();
		headline.setHeadline(message);
		headline.setPerson(person);
		headlineDao.save(headline);

		person.setFaces(faceDao.getFaces(person));
		
		News news = new News();
		news.setPerson(person);
		news.setMessage(Action.UPDATE.convert(person, message));
		news.setWorld(Action.UPDATE.isWorld());
		news.setAction(Action.UPDATE); // This should be an update
		this.getNewsPermission(person, news);
		//person.getNewsPermission(news);
		newsDao.save(news);
		
		personDAO.update(person);
	}
	
	public void getNewsPermission(Person person, News news) {
		news.getNewsVisibility().add(
				new NewsVisibilityDomain(news,
						getVisibilityForType(person, PermissionType.NEWS)));
		news.getNewsVisibility().add(
				new NewsVisibilityDomain(news, getDefaultVisibility(person)));
	}

	public VisibilityDomain getVisibilityForType(Person person, PermissionType type) {
		for (VisibilityDomainPerson v : visibilityDomainPersonDao.getVisibilityGroups(person)) {
			if (v.getVisibilityDomain().getActivityType() == type) {
				return v.getVisibilityDomain();
			}
		}
		// Will never happen. Its a controlled field
		return null;
	}
	
	public VisibilityDomain getDefaultVisibility(Person person) {
		for (VisibilityDomainPerson v : visibilityDomainPersonDao.getVisibilityGroups(person)) {
			if (v.getVisibilityDomain().isDefaultVisibility()) {
				return v.getVisibilityDomain();
			}
		}
		// Should never happen unless someone accidentally edited the default
		// group
		throw new RuntimeException("There is no default visibility");
	}

	public List<Person> findMerchants() {
		return personDAO.findMerchants();
	}
}
```
