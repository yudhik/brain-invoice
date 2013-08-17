package com.brainmaster.apps.invoicing.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;

import com.brainmaster.util.DatabaseColumnConstant;

@Entity
@Table(name = "product_store", uniqueConstraints = @UniqueConstraint(columnNames = {
	"account_id", "product_id", "store_id" }))
public class ProductStore implements Serializable {

    private static final long serialVersionUID = -7826385577001790718L;

    @Id
    @Type(type = "uuid")
    @Column(name = "product_store_id", length = DatabaseColumnConstant.SIZE_UUID)
    private UUID productStoreId;

    @ManyToOne(targetEntity = Account.class)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(targetEntity = Store.class)
    @JoinColumn(name = "store_id")
    private Store store;

    public ProductStore() {
	this.productStoreId = UUID.randomUUID();
    }

    public ProductStore(Account account, Product product, Store store) {
	this.productStoreId = UUID.randomUUID();
	this.account = account;
	this.product = product;
	this.store = store;
    }

    public UUID getProductStoreId() {
	return productStoreId;
    }

    public void setProductStoreId(UUID productStoreId) {
	this.productStoreId = productStoreId;
    }

    public Account getAccount() {
	return account;
    }

    public void setAccount(Account account) {
	this.account = account;
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
	result = prime * result + ((account == null) ? 0 : account.hashCode());
	result = prime * result + ((product == null) ? 0 : product.hashCode());
	result = prime * result
		+ ((productStoreId == null) ? 0 : productStoreId.hashCode());
	result = prime * result + ((store == null) ? 0 : store.hashCode());
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
	ProductStore other = (ProductStore) obj;
	if (account == null) {
	    if (other.account != null)
		return false;
	} else if (!account.equals(other.account))
	    return false;
	if (product == null) {
	    if (other.product != null)
		return false;
	} else if (!product.equals(other.product))
	    return false;
	if (productStoreId == null) {
	    if (other.productStoreId != null)
		return false;
	} else if (!productStoreId.equals(other.productStoreId))
	    return false;
	if (store == null) {
	    if (other.store != null)
		return false;
	} else if (!store.equals(other.store))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("ProductStore [productStoreId=");
	builder.append(productStoreId);
	builder.append(", account=");
	builder.append(account);
	builder.append(", product=");
	builder.append(product);
	builder.append(", store=");
	builder.append(store);
	builder.append("]");
	return builder.toString();
    }
}
