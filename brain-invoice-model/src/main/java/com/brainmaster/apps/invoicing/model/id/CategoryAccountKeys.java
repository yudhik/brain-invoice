package com.brainmaster.apps.invoicing.model.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.brainmaster.apps.invoicing.model.Account;

public class CategoryAccountKeys implements Serializable {

	private static final long serialVersionUID = 6422484821577173408L;

	@ManyToOne(targetEntity = Account.class)
	@JoinColumn(name = "account_id")
	private Account account;

	@Column(name = "category_name")
	private String categoryName;

	public CategoryAccountKeys() {
	}

	public CategoryAccountKeys(Account account, String categoryName) {
		this.account = account;
		this.categoryName = categoryName;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result
				+ ((categoryName == null) ? 0 : categoryName.hashCode());
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
		CategoryAccountKeys other = (CategoryAccountKeys) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CategoryAccountKeys [account=" + account.getAccountId()
				+ ", categoryName=" + categoryName + "]";
	}

}
