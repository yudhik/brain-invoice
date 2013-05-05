package com.brainmaster.apps.invoicing.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.brainmaster.apps.invoicing.model.id.PackagingAccountKeys;

@Entity
@Table(name = "packaging")
public class PackagingUnit implements Serializable {

	private static final long serialVersionUID = 1297099907148015361L;

	@EmbeddedId
	private PackagingAccountKeys keys;

	@Column(name = "packaging_name")
	private String packagingName;

	public PackagingUnit() {
	}

	public PackagingUnit(PackagingAccountKeys keys, String packagingName) {
		this.keys = keys;
		this.packagingName = packagingName;
	}

	public PackagingAccountKeys getKeys() {
		return keys;
	}

	public void setKeys(PackagingAccountKeys keys) {
		this.keys = keys;
	}

	public String getPackagingName() {
		return packagingName;
	}

	public void setPackagingName(String packagingName) {
		this.packagingName = packagingName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((keys == null) ? 0 : keys.hashCode());
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
		PackagingUnit other = (PackagingUnit) obj;
		if (keys == null) {
			if (other.keys != null)
				return false;
		} else if (!keys.equals(other.keys))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Packaging [keys=" + keys + ", packagingName=" + packagingName
				+ "]";
	}

}
