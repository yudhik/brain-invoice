package com.brainmaster.apps.invoicing.model.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.brainmaster.apps.invoicing.model.Account;

@Embeddable
public class PackagingAccountKeys implements Serializable {

	private static final long serialVersionUID = 5616285605407339616L;

	@ManyToOne(targetEntity = Account.class)
	@JoinColumn(name = "account_id")
	private Account account;

//	@Type(type = "uuid")
	@Column(name = "packaging_id")
	private String packagingId;

	public PackagingAccountKeys() {
	}

	public PackagingAccountKeys(Account account, String packagingId) {
		this.account = account;
		this.packagingId = packagingId;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getPackagingId() {
		return packagingId;
	}

	public void setPackagingId(String packagingId) {
		this.packagingId = packagingId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result
				+ ((packagingId == null) ? 0 : packagingId.hashCode());
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
		PackagingAccountKeys other = (PackagingAccountKeys) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (packagingId == null) {
			if (other.packagingId != null)
				return false;
		} else if (!packagingId.equals(other.packagingId))
			return false;
		return true;
	}

}
