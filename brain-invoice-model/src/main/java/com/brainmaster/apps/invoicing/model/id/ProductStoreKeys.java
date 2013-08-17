package com.brainmaster.apps.invoicing.model.id;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;

import com.brainmaster.apps.invoicing.model.Account;
import com.brainmaster.apps.invoicing.model.Product;
import com.brainmaster.apps.invoicing.model.Store;
import com.brainmaster.util.helper.uuid.UUIDHelper;

@Embeddable
public class ProductStoreKeys extends AccountKeys implements Serializable {

    private static final long serialVersionUID = 5178347163876248031L;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumns({
	    @JoinColumn(name = "product_code"),
	    @JoinColumn(name = "account_id", insertable = false, updatable = false) })
    // @JoinColumnsOrFormulas(value = {
    // @JoinColumnOrFormula(column = @JoinColumn(name = "product_code",
    // referencedColumnName = "product_code")),
    // @JoinColumnOrFormula(column = @JoinFormula(value = "account_id",
    // referencedColumnName = "account_id"))
    // })
    private Product product;

    @ManyToOne(targetEntity = Store.class)
    // @JoinColumnsOrFormulas(value = {
    // @JoinColumnOrFormula(column = @JoinColumn(name = "keys.uuid",
    // referencedColumnName = "uuid")),
    // @JoinColumnOrFormula(column = @JoinColumn(name = "keys.account",
    // referencedColumnName = "account_id", insertable = false, updatable =
    // false))
    // })
    // @JoinColumns({
    // @JoinColumn(name = "uuid"),
    // @JoinColumn(name = "account_id", insertable = false, updatable = false)
    // })
    // @PrimaryKeyJoinColumn(name = "account_id")
    private Store store;

    @Deprecated
    public ProductStoreKeys() {
    }

    public ProductStoreKeys(Account account, Product product, Store store) {
	super(account);
	this.product = product;
	this.store = store;
    }

    public Product getProduct() {
	return product;
    }

    public void setProduct(Product product) {
	this.product = product;
    }

    public Store getStore() {
	return store;
    }

    public void setStore(Store store) {
	this.store = store;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((product == null) ? 0 : product.hashCode());
	result = prime * result + ((store == null) ? 0 : store.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	ProductStoreKeys other = (ProductStoreKeys) obj;
	if (product == null) {
	    if (other.product != null) {
		return false;
	    }
	} else if (!product.equals(other.product)) {
	    return false;
	}
	if (store == null) {
	    if (other.store != null) {
		return false;
	    }
	} else if (!store.equals(other.store)) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "store[id :"
//		+ UUIDHelper.uuidToString(store.getKeys().getUuid())
		+ ", name: " + store.getStoreName() + " ], product[id :"
//		+ product.getKeys().getProductCode() + ", name : "
		+ product.getProductName() + "]";
    }
}
