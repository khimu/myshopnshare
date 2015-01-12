package com.myshopnshare.webapp.form;

import org.apache.commons.lang.StringUtils;

import com.myshopnshare.core.domain.Address;
import com.myshopnshare.core.domain.EmailAddress;
import com.myshopnshare.core.domain.Institution;

public class InstitutionForm {

	private Institution institution = new Institution();
	private Address address = new Address();
	private EmailAddress email = new EmailAddress();
	private String password;
	private String confirmPassword;
	private String addressType;
	private String institutionType;
	private String visibility;

	private String tags;

	private String month;
	private String day;
	private String year;

	public InstitutionForm() {
		address.setCountry("US");
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getInstitutionType() {
		return institutionType;
	}

	public void setInstitutionType(String institutionType) {
		this.institutionType = institutionType;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	public EmailAddress getEmail() {
		return email;
	}

	public void setEmail(EmailAddress email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public boolean validate() {
		if (StringUtils.trimToNull(this.email.getEmail()) == null) {
			return false;
		}
		if (StringUtils.trimToNull(this.addressType) == null) {
			return false;
		}
		if (StringUtils.trimToNull(this.address.getCity()) == null) {
			return false;
		}
		if (StringUtils.trimToNull(this.address.getStateOrProvince()) == null) {
			return false;
		}
		if (StringUtils.trimToNull(this.address.getCountry()) == null) {
			return false;
		}
		if (StringUtils.trimToNull(this.address.getPostalCode()) == null) {
			return false;
		}
		if (StringUtils.trimToNull(this.address.getStreet1()) == null) {
			return false;
		}
		if (StringUtils.trimToNull(this.institution.getInstitutionName()) == null) {
			return false;
		}
		if (StringUtils.trimToNull(this.password) == null) {
			return false;
		}
		if (StringUtils.trimToNull(this.day) == null) {
			return false;
		}
		if (StringUtils.trimToNull(this.visibility) == null) {
			return false;
		}			
		if (StringUtils.trimToNull(this.month) == null) {
			return false;
		}
		if (StringUtils.trimToNull(this.year) == null) {
			return false;
		}
		return true;
	}

}
