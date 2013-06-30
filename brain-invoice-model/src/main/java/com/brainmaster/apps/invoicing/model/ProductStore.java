package com.brainmaster.apps.invoicing.model;

import com.brainmaster.apps.invoicing.model.id.ProductStoreKeys;
import java.io.Serializable;
import javax.persistence.EmbeddedId;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product_store")
public class ProductStore implements Serializable {

    private static final long serialVersionUID = -7826385577001790718L;
    
    @EmbeddedId
    private ProductStoreKeys keys;

    public ProductStore() {
    }

    public ProductStore(Account account, Product product, Store store) {
        this.keys = new ProductStoreKeys(account, product, store);
    }

    public ProductStoreKeys getKeys() {
        return keys;
    }

    public void setKeys(ProductStoreKeys keys) {
        this.keys = keys;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + (this.keys != null ? this.keys.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductStore other = (ProductStore) obj;
        if (this.keys != other.keys && (this.keys == null || !this.keys.equals(other.keys))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProductStore{" + "keys=" + keys + '}';
    }
}
