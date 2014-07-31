package com.brainmaster.apps.invoicing.core.model.id;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.brainmaster.apps.invoicing.core.model.credential.Account;

public abstract class AccountKeys implements Serializable {

  private static final long serialVersionUID = -4948208035246922556L;

  private Account account;

  @Deprecated
  public AccountKeys() {}

  public AccountKeys(Account account) {
    this.account = account;
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

  public Account getAccount() {
    return account;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((account == null) ? 0 : account.hashCode());
    return result;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}
