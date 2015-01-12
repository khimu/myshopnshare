package com.myshopnshare.core.service;

import com.myshopnshare.core.domain.Headline;
import com.myshopnshare.core.domain.Person;

public interface HeadlineService extends
		GenericService<Headline, Long> {
	public Headline findLatestHeadlineFor(Person person);
}
