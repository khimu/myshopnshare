package com.myshopnshare.core.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.security.GrantedAuthority;

import com.myshopnshare.core.enums.Authority;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role extends DomainObject implements GrantedAuthority {

	@Enumerated(EnumType.STRING)
	private Authority mappedAuthority;

	@Transient
	public String getAuthority() {
		return mappedAuthority.toString();
	}

	public Authority getMappedAuthority() {
		return mappedAuthority;
	}

	public void setMappedAuthority(Authority mappedAuthority) {
		this.mappedAuthority = mappedAuthority;
	}

	@Override
	public int hashCode() {
		return getAuthority().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj.getClass() == String.class)
			return obj.equals(getAuthority());
		if (getClass() != obj.getClass())
			return false;
		final Role other = (Role) obj;
		if (getAuthority() == null) {
			if (other.getAuthority() != null)
				return false;
		} else if (!getAuthority().equals(other.getAuthority()))
			return false;
		return true;
	}

	public int compareTo(Object o) {
		if (this == o)
			return 0;
		if (o == null)
			return -1;
		if (o.getClass() == String.class)
			return getAuthority().compareTo((String) o);
		if (getClass() != o.getClass())
			return -1;
		final Role other = (Role) o;
		if (getAuthority() == null) {
			if (other.getAuthority() != null)
				return 1;
		} else
			return getAuthority().compareTo(other.getAuthority());
		return -1;
	}

}
