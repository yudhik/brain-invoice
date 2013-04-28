package com.brainmaster.apps.invoicing.model.id;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.brainmaster.apps.invoicing.model.Account;

@MappedSuperclass
public abstract class AccountKeys {

	@ManyToOne(targetEntity = Account.class)
	@JoinColumn(name = "account_id")
	private Account account;

	public AccountKeys(Account account) {
		this.account = account;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
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
		AccountKeys other = (AccountKeys) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AccountKeys [account=" + account + "]";
	}
	
	
}
