package com.brainmaster.apps.invoicing.model.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.brainmaster.apps.invoicing.model.Account;

@Embeddable
public class BrandAccountKeys implements Serializable {

	private static final long serialVersionUID = -5852654524371945799L;
	
	@ManyToOne(targetEntity = Account.class)
	@JoinColumn(name = "account_id")
	private Account account;
	
	@Column(name = "brand_name")
	private String brandName;
	
	public BrandAccountKeys() {
	}

	public BrandAccountKeys(Account account, String brandName) {
		this.account = account;
		this.brandName = brandName;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result
				+ ((brandName == null) ? 0 : brandName.hashCode());
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
		BrandAccountKeys other = (BrandAccountKeys) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (brandName == null) {
			if (other.brandName != null)
				return false;
		} else if (!brandName.equals(other.brandName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BrandAccountKeys [account=" + account.getAccountId() + ", brandName="
				+ brandName + "]";
	}
	
	
	
}
