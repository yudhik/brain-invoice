package com.brainmaster.apps.invoicing.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.brainmaster.apps.invoicing.core.model.credential.Account;
import com.brainmaster.apps.invoicing.core.model.credential.User;
import com.brainmaster.apps.invoicing.core.model.id.BrandAccountKeys;

@Entity
@Table(name = "brand")
public class Brand extends AbstractUpdateBy implements Serializable {

  private static final long serialVersionUID = -1603771716730111235L;

  @EmbeddedId
  private BrandAccountKeys keys;

  @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Product> products = new ArrayList<Product>();

  @Deprecated
  public Brand() {
    super(null, null);
  }

  public Brand(Account account, String brandName, User createdBy, User updatedBy) {
    super(createdBy, updatedBy);
    this.keys = new BrandAccountKeys(account, brandName);
  }

  public Brand(BrandAccountKeys keys, User createdBy, User updatedBy) {
    super(createdBy, updatedBy);
    this.keys = keys;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Brand other = (Brand) obj;
    if (keys == null) {
      if (other.keys != null)
        return false;
    } else if (!keys.equals(other.keys))
      return false;
    return true;
  }

  public BrandAccountKeys getKeys() {
    return keys;
  }

  public List<Product> getProducts() {
    return products;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((keys == null) ? 0 : keys.hashCode());
    return result;
  }

  public void setKeys(BrandAccountKeys keys) {
    this.keys = keys;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append(keys.toString()).toString();
  }

}
