package com.myshopnshare.core.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshopnshare.core.dao.ScribbleDAO;
import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.Scribble;

@Service("scribbleService")
@Transactional
public class ScribbleServiceImpl extends
		GenericServiceImpl<Scribble, Long> implements
		ScribbleService {

	public ScribbleDAO scribbleDAO;

	@Autowired
	public ScribbleServiceImpl(ScribbleDAO genericDao) {
		super(genericDao);
		this.scribbleDAO = genericDao;
	}
/*
	private List<String> tokenize(String searchString) {
		if(StringUtils.trimToNull(searchString) == null){
			return null;
		}
		List<String> tags = new ArrayList<String>();
		StringTokenizer tokenizerComma = new StringTokenizer(searchString, ",");
		while (tokenizerComma.hasMoreTokens()) {
			String tag = tokenizerComma.nextToken();
			StringTokenizer tokenizerSpace = new StringTokenizer(tag, " ");
			while (tokenizerSpace.hasMoreTokens()) {
				tags.add(tokenizerSpace.nextToken());
			}
		}
		return tags;
	}
	*/
	
	public Scribble getScribbleForPerson(Person person, Long id){
		return ((ScribbleDAO) this.dao).getScribbleForPerson(person, id);
	}


	public List<Scribble> findAllFriendsScribble(Person person,
			String category, String searchString, int start) {
		if (StringUtils.trimToNull(searchString) == null) {
			if (StringUtils.trimToNull(category) == null) {
				return ((ScribbleDAO)this.dao).findAllFriendsScribble(person, category, start);
			}
			return ((ScribbleDAO)this.dao).findAllFriendsScribble(person, category, start);
		}
		if (StringUtils.trimToNull(category) == null) {
			return ((ScribbleDAO)this.dao).findAllFriendsScribble(person, tokenize(searchString), start);
		}

		return ((ScribbleDAO)this.dao).findAllFriendsScribble(person, category, tokenize(searchString), start);
	}


	public List<Scribble> findOneFriendsScribble(Person friend, Person person,
			String category, String searchString, int start) {
		if (StringUtils.trimToNull(searchString) == null) {
			if (StringUtils.trimToNull(category) == null) {
				return ((ScribbleDAO)this.dao).findOneFriendsScribble(friend, person, start);

			}
			return ((ScribbleDAO)this.dao).findOneFriendsScribble(friend, person, category, start);

		}
		if (StringUtils.trimToNull(category) == null) {
			return ((ScribbleDAO)this.dao).findOneFriendsScribble(friend, person,tokenize(searchString), start);
		}

		return ((ScribbleDAO)this.dao).findOneFriendsScribble(friend, person, category, tokenize(searchString), start);
	}


	public List<Scribble> findWorldScribble(Person person, String category,
			String searchString, int start) {
		if (StringUtils.trimToNull(searchString) == null) {
			if (StringUtils.trimToNull(category) == null) {
				return ((ScribbleDAO)this.dao).findWorldScribble(person, start);
			}
			return ((ScribbleDAO)this.dao).findWorldScribble(person, category, start);
		}
		if (StringUtils.trimToNull(category) == null) {
			return ((ScribbleDAO)this.dao).findWorldScribble(person, tokenize(searchString), start);
		}

		return ((ScribbleDAO)this.dao).findWorldScribble(person, category, tokenize(searchString), start);
	}

}
