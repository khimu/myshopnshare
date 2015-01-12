package com.myshopnshare.core.dao;

import com.myshopnshare.core.domain.Headline;
import com.myshopnshare.core.domain.Person;

public interface HeadlineDAO extends GenericDAO<Headline, Long> {
	public Headline findLatestHeadlineFor(Person person);
}
