package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataContract implements Serializable {

	private static final long serialVersionUID = -5784657253801287876L;

	private List<Contract> dataContract = new ArrayList<>();

	public List<Contract> getDataOrganization() {
		return dataContract;
	}

	public void setDataOrganization(List<Contract> dataOrganization) {
		this.dataContract = dataOrganization;
	}

	@Override
	public String toString() {
		return "DataOrganization [dataOrganization=" + dataContract + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataContract == null) ? 0 : dataContract.hashCode());
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
		DataContract other = (DataContract) obj;
		if (dataContract == null) {
			if (other.dataContract != null)
				return false;
		} else if (!dataContract.equals(other.dataContract))
			return false;
		return true;
	}

}
