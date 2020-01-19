package model;

import java.io.Serializable;

public class Company implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4654106567792556946L;
	private String organization;
	private String legalAddress;
	private String contactPerson;
	private String phone;

	public Company() {
	}

	public final String getOrganization() {
		return organization;
	}

	public final void setOrganization(String organization) {
		this.organization = organization;
	}

	public final String getLegalAddress() {
		return legalAddress;
	}

	public final void setLegalAddress(String legalAddress) {
		this.legalAddress = legalAddress;
	}

	public final String getContactPerson() {
		return contactPerson;
	}

	public final void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public final String getPhone() {
		return phone;
	}

	public final void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contactPerson == null) ? 0 : contactPerson.hashCode());
		result = prime * result + ((legalAddress == null) ? 0 : legalAddress.hashCode());
		result = prime * result + ((organization == null) ? 0 : organization.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		if (contactPerson == null) {
			if (other.contactPerson != null)
				return false;
		} else if (!contactPerson.equals(other.contactPerson))
			return false;
		if (legalAddress == null) {
			if (other.legalAddress != null)
				return false;
		} else if (!legalAddress.equals(other.legalAddress))
			return false;
		if (organization == null) {
			if (other.organization != null)
				return false;
		} else if (!organization.equals(other.organization))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}

}
