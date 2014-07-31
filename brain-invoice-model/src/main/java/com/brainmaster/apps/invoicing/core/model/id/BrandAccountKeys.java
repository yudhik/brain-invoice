package com.brainmaster.apps.invoicing.core.model.id;

import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.brainmaster.apps.invoicing.core.model.credential.Account;

@Embeddable
public class BrandAccountKeys extends AccountKeys {

  private static final long serialVersionUID = -5852654524371945799L;

  private String brandName;

  @Deprecated
  public BrandAccountKeys() {}

  public BrandAccountKeys(Account account, String brandName) {
    super(account);
    this.brandName = brandName;
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
    return new EqualsBuilder().appendSuper(super.equals(other))
        .append(brandName, other.getBrandName()).isEquals();
  }

  public String getBrandName() {
    return brandName;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = super.hashCode();
    result = prime * result + ((brandName == null) ? 0 : brandName.hashCode());
    return result;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("BrandAccountKeys [");
    if (brandName != null) {
      builder.append("brandName=");
      builder.append(brandName);
    }
    builder.append("]");
    return builder.toString();
  }

}
