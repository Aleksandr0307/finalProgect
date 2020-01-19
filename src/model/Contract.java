package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Contract extends Company implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3445097661878667091L;

	public Contract() {
	}

	private String contractNumber;
	private LocalDate beginningContract;
	private LocalDate endContract;
	private String rentalAddress;
	private int rentalArea;
	private RoomType roomType;
	private Boolean validityContract;
	private Enum<?> ReductionFactor;
	private String dueDate;

	public static class Builder {
		private String organization;
		private String legalAddress;
		private String contactPerson;
		private String phone;
		private String contractNumber;
		private LocalDate beginningContract;
		private LocalDate endContract;
		private String rentalAddress;
		private int rentalArea;
		private RoomType roomType;
		private Boolean validityContract;
		private Enum<?> ReductionFactor;
		private String dueDate;

		public Builder() {
		}

		public Builder organization(String organization) {
			this.organization = organization;
			return this;

		}

		public Builder legalAddress(String legalAddress) {
			this.legalAddress = legalAddress;
			return this;

		}

		public Builder phone(String phone) {
			this.phone = phone;
			return this;

		}

		public Builder contactPerson(String contactPerson) {
			this.contactPerson = contactPerson;
			return this;

		}

		public Builder contractNumber(String contractNumber) {
			this.contractNumber = contractNumber;
			return this;
		}

		public Builder beginningContract(LocalDate beginningContract) {
			this.beginningContract = beginningContract;
			return this;
		}

		public Builder endContract(LocalDate endContract) {
			this.endContract = endContract;
			return this;
		}

		public Builder rentalAddress(String rentalAddress) {

			this.rentalAddress = rentalAddress;
			return this;
		}

		public Builder rentalArea(int rentalArea) {
			this.rentalArea = rentalArea;
			return this;
		}

		public Builder roomType(RoomType roomType) {
			this.roomType = roomType;
			return this;
		}

		public Builder validityContract(Boolean validityContract) {
			this.validityContract = validityContract;
			return this;
		}

		public Builder reductionFactor(Enum<?> ReductionFactor) {
			this.ReductionFactor = ReductionFactor;
			return this;
		}

		public Builder dueDate(String dueDate) {
			this.dueDate = dueDate;
			return this;
		}

		public Contract build() {
			return new Contract(this);
		}

	}

	public Contract(Builder builder) {
		setOrganization(builder.organization);
		setLegalAddress(builder.legalAddress);
		setContactPerson(builder.contactPerson);
		setPhone(builder.phone);
		contractNumber = builder.contractNumber;
		beginningContract = builder.beginningContract;
		endContract = builder.endContract;
		rentalAddress = builder.rentalAddress;
		rentalArea = builder.rentalArea;
		roomType = builder.roomType;
		validityContract = builder.validityContract;
		ReductionFactor = builder.ReductionFactor;
		dueDate = builder.dueDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public LocalDate getBeginningContract() {
		return beginningContract;
	}

	public void setBeginningContract(LocalDate beginningContract) {
		this.beginningContract = beginningContract;
	}

	public LocalDate getEndContract() {
		return endContract;
	}

	public void setEndContract(LocalDate endContract) {
		this.endContract = endContract;
	}

	public String getRentalAddress() {
		return rentalAddress;
	}

	public void setRentalAddress(String rentalAddress) {
		this.rentalAddress = rentalAddress;
	}

	public int getRentalArea() {
		return rentalArea;
	}

	public void setRentalArea(int rentalArea) {
		this.rentalArea = rentalArea;
	}

	public void setRentalArea(String rentalArea) {
		this.rentalArea = Integer.parseInt(rentalArea);
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public Boolean getValidityContract() {
		return validityContract;
	}

	public void setValidityContract(Boolean validityContract) {
		this.validityContract = validityContract;
	}

	public String getReductionFactor() {
		return ReductionFactor.toString();
	}

	public void setReductionFactor(Enum<?> reductionFactor) {
		this.ReductionFactor = reductionFactor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((ReductionFactor == null) ? 0 : ReductionFactor.hashCode());
		result = prime * result + ((beginningContract == null) ? 0 : beginningContract.hashCode());
		result = prime * result + ((contractNumber == null) ? 0 : contractNumber.hashCode());
		result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
		result = prime * result + ((endContract == null) ? 0 : endContract.hashCode());
		result = prime * result + ((rentalAddress == null) ? 0 : rentalAddress.hashCode());
		result = prime * result + rentalArea;
		result = prime * result + ((roomType == null) ? 0 : roomType.hashCode());
		result = prime * result + ((validityContract == null) ? 0 : validityContract.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contract other = (Contract) obj;
		if (ReductionFactor == null) {
			if (other.ReductionFactor != null)
				return false;
		} else if (!ReductionFactor.equals(other.ReductionFactor))
			return false;
		if (beginningContract == null) {
			if (other.beginningContract != null)
				return false;
		} else if (!beginningContract.equals(other.beginningContract))
			return false;
		if (contractNumber == null) {
			if (other.contractNumber != null)
				return false;
		} else if (!contractNumber.equals(other.contractNumber))
			return false;
		if (dueDate == null) {
			if (other.dueDate != null)
				return false;
		} else if (!dueDate.equals(other.dueDate))
			return false;
		if (endContract == null) {
			if (other.endContract != null)
				return false;
		} else if (!endContract.equals(other.endContract))
			return false;
		if (rentalAddress == null) {
			if (other.rentalAddress != null)
				return false;
		} else if (!rentalAddress.equals(other.rentalAddress))
			return false;
		if (rentalArea != other.rentalArea)
			return false;
		if (roomType != other.roomType)
			return false;
		if (validityContract == null) {
			if (other.validityContract != null)
				return false;
		} else if (!validityContract.equals(other.validityContract))
			return false;
		return true;
	}

}
