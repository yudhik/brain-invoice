package com.brainmaster.apps.invoicing.model.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.brainmaster.apps.invoicing.model.Account;

@Embeddable
public class ProductAccountKeys implements Serializable {

	private static final long serialVersionUID = -7094180747931635859L;
	
	@ManyToOne(targetEntity = Account.class)
	@JoinColumn(name = "account_id")
	private Account account;
	
	@Column(name = "product_code")
	private String productCode;
	
	public ProductAccountKeys() {
	}

	public ProductAccountKeys(Account account, String productCode) {
		this.account = account;
		this.productCode = productCode;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result
				+ ((productCode == null) ? 0 : productCode.hashCode());
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
		ProductAccountKeys other = (ProductAccountKeys) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (productCode == null) {
			if (other.productCode != null)
				return false;
		} else if (!productCode.equals(other.productCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProductAccountKeys [account=" + account.getAccountId() + ", productCode="
				+ productCode + "]";
	}
	
	

}
