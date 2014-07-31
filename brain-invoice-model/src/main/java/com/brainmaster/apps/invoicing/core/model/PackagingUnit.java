package com.brainmaster.apps.invoicing.core.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.brainmaster.apps.invoicing.core.model.credential.Account;
import com.brainmaster.apps.invoicing.core.model.credential.User;
import com.brainmaster.apps.invoicing.core.model.id.PackagingAccountKeys;

@Entity
@Table(name = "packaging")
@IdClass(PackagingAccountKeys.class)
public class PackagingUnit extends AbstractUpdateBy implements Serializable {

  private static final long serialVersionUID = 1297099907148015361L;

  @Id
  @ManyToOne
  @JoinColumn(name = "account_id")
  private Account account;

  @Id
  @Column(name = "packaging_id")
  private String packagingId;

  @Column(name = "packaging_name")
  private String packagingName;

  @Deprecated
  public PackagingUnit() {
    super(null, null);
  }

  public PackagingUnit(Account account, String packagingId, String packagingName, User createdBy,
      User updatedBy) {
    super(createdBy, updatedBy);
    this.account = account;
    this.packagingId = packagingId;
    this.packagingName = packagingName;
  }

  public PackagingUnit(PackagingAccountKeys keys, String packagingName, User createdBy,
      User updatedBy) {
    super(createdBy, updatedBy);
    this.account = keys.getAccount();
    this.packagingId = keys.getPackagingId();
    this.packagingName = packagingName;
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    PackagingUnit other = (PackagingUnit) obj;
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

  public Account getAccount() {
    return account;
  }

  public String getPackagingId() {
    return packagingId;
  }

  public String getPackagingName() {
    return packagingName;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((account == null) ? 0 : account.hashCode());
    result = prime * result + ((packagingId == null) ? 0 : packagingId.hashCode());
    return result;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public void setPackagingId(String packagingId) {
    this.packagingId = packagingId;
  }

  public void setPackagingName(String packagingName) {
    this.packagingName = packagingName;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("PackagingUnit [");
    if (account != null) {
      builder.append("account=");
      builder.append(account);
      builder.append(", ");
    }
    if (packagingId != null) {
      builder.append("packagingId=");
      builder.append(packagingId);
      builder.append(", ");
    }
    if (packagingName != null) {
      builder.append("packagingName=");
      builder.append(packagingName);
    }
    builder.append("]");
    return builder.toString();
  }

}
