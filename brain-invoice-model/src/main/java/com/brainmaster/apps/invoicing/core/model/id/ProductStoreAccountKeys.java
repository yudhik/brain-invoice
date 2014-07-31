package com.brainmaster.apps.invoicing.core.model.id;

import javax.persistence.Embeddable;

import com.brainmaster.apps.invoicing.core.model.Product;
import com.brainmaster.apps.invoicing.core.model.Store;
import com.brainmaster.apps.invoicing.core.model.credential.Account;

@Embeddable
public class ProductStoreAccountKeys extends AccountKeys {

  private static final long serialVersionUID = -3416492226717224142L;

  private Product product;

  private Store store;

  @Deprecated
  public ProductStoreAccountKeys() {}

  public ProductStoreAccountKeys(Account account, Product product, Store store) {
    super(account);
    this.product = product;
    this.store = store;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    ProductStoreAccountKeys other = (ProductStoreAccountKeys) obj;
    if (product == null) {
      if (other.product != null)
        return false;
    } else if (!product.equals(other.product))
      return false;
    if (store == null) {
      if (other.store != null)
        return false;
    } else if (!store.equals(other.store))
      return false;
    return true;
  }

  public Product getProduct() {
    return product;
  }

  public Store getStore() {
    return store;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((product == null) ? 0 : product.hashCode());
    result = prime * result + ((store == null) ? 0 : store.hashCode());
    return result;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public void setStore(Store store) {
    this.store = store;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("ProductStoreAccountKeys [");
    if (product != null) {
      builder.append("product=");
      builder.append(product);
      builder.append(", ");
    }
    if (store != null) {
      builder.append("store=");
      builder.append(store);
    }
    builder.append("]");
    return builder.toString();
  }

}
