package com.brainmaster.apps.invoicing.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.brainmaster.apps.invoicing.core.model.credential.Account;
import com.brainmaster.apps.invoicing.core.model.credential.User;
import com.brainmaster.apps.invoicing.core.model.id.BrandAccountKeys;

@Entity
@Table(name = "brand")
@IdClass(BrandAccountKeys.class)
@NamedQueries({@NamedQuery(name = "brandFromAccount",
    query = "select a from Brand a where a.account = :account")})
public class Brand extends AbstractUpdateBy implements Serializable {

  private static final long serialVersionUID = -1603771716730111235L;

  @Id
  @ManyToOne
  @JoinColumn(name = "account_id")
  private Account account;

  @Id
  @Column(name = "brand_name")
  private String brandName;

  @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Product> products = new ArrayList<Product>();

  @Deprecated
  public Brand() {
    super(null, null);
  }

  public Brand(Account account, String brandName, User createdBy, User updatedBy) {
    super(createdBy, updatedBy);
    this.setAccount(account);
    this.setBrandName(brandName);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    Brand other = (Brand) obj;
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


  public Account getAccount() {
    return account;
  }

  public String getBrandName() {
    return brandName;
  }

  public List<Product> getProducts() {
    return products;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((account == null) ? 0 : account.hashCode());
    result = prime * result + ((brandName == null) ? 0 : brandName.hashCode());
    return result;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Brand [");
    if (account != null) {
      builder.append("account=");
      builder.append(account);
      builder.append(", ");
    }
    if (brandName != null) {
      builder.append("brandName=");
      builder.append(brandName);
      builder.append(", ");
    }
    if (products != null) {
      builder.append("products=");
      builder.append(products);
    }
    builder.append("]");
    return builder.toString();
  }


}
