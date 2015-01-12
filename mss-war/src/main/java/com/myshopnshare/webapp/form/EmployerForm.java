package com.myshopnshare.webapp.form;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.myshopnshare.core.domain.Address;
import com.myshopnshare.core.domain.EmailAddress;
import com.myshopnshare.core.domain.Employer;

public class EmployerForm {
	private static transient final Log log = LogFactory
			.getLog(EmployerForm.class);

	private Employer employer = new Employer();
	private Address address = new Address();
	private EmailAddress email = new EmailAddress();
	private String password;
	private String confirmPassword;
	private String addressType;
	private String tags;
	private String visibility;

	private String month;
	private String day;
	private String year;
	
	private String userType;

	public EmployerForm() {
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

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public boolean validate() {
		if (StringUtils.trimToNull(this.email.getEmail()) == null) {
			log.debug(this.email.getEmail());
			return false;
		}
		if (StringUtils.trimToNull(this.addressType) == null) {
			log.debug(this.addressType);
			return false;
		}
		if (StringUtils.trimToNull(this.address.getCity()) == null) {
			log.debug(this.address.getCity());
			return false;
		}
		if (StringUtils.trimToNull(this.address.getStateOrProvince()) == null) {
			log.debug(this.getAddress().getStateOrProvince());
			return false;
		}
		if (StringUtils.trimToNull(this.address.getCountry()) == null) {
			log.debug(this.address.getCountry());
			return false;
		}
		if (StringUtils.trimToNull(this.address.getPostalCode()) == null) {
			log.debug(this.address.getPostalCode());
			return false;
		}
		if (StringUtils.trimToNull(this.address.getStreet1()) == null) {
			log.debug(this.address.getStreet1());
			return false;
		}
		if (StringUtils.trimToNull(this.employer.getCompanyName()) == null) {
			log.debug(this.employer.getCompanyName());
			return false;
		}
		if (StringUtils.trimToNull(this.password) == null) {
			log.debug("password " + this.password);
			return false;
		}
		if (StringUtils.trimToNull(this.confirmPassword) == null) {
			log.debug("confirmPassword " + this.confirmPassword);
			return false;
		}
		if (StringUtils.trimToNull(this.day) == null) {
			log.debug("day " + this.day);
			return false;
		}
		if (StringUtils.trimToNull(this.month) == null) {
			log.debug("month " + this.month);
			return false;
		}
		if (StringUtils.trimToNull(this.year) == null) {
			log.debug("year " + this.year);
			return false;
		}
		if (StringUtils.trimToNull(this.visibility) == null) {
			log.debug("visibility " + this.visibility);
			return false;
		}		
		if (!StringUtils.trimToNull(this.password).equals(
				StringUtils.trimToNull(this.confirmPassword))) {
			log.debug("password: " + this.password);
			return false;
		}
		return true;
	}

}
