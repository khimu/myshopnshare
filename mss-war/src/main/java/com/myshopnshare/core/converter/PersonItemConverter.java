package com.myshopnshare.core.converter;

import org.apache.commons.lang.StringUtils;

import com.myshopnshare.core.domain.Item;
import com.myshopnshare.core.domain.Person;

public class PersonItemConverter extends AbstractBaseConverter implements MessageConverter {

	public String converter(String template, Object... objects) {
		Person person = (Person) objects[0];
		Item item = (Item) objects[1];
		
		String message = StringUtils.replace(template, "{1}", person.getAlias());
		message = StringUtils.replace(message, "{2}", item.getItemName());
		
		StringBuilder sb = new StringBuilder();

		personhtml(sb, person, message);
		longmsghtml(sb, message);
		itemhtml(sb, item);

		return sb.toString();
	}


}
