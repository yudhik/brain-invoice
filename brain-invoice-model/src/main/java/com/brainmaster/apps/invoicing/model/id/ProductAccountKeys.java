package com.brainmaster.apps.invoicing.model.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.brainmaster.apps.invoicing.model.Account;

@Embeddable
public class ProductAccountKeys extends AccountKeys implements Serializable {

    private static final long serialVersionUID = -7094180747931635859L;

    @Column(name = "product_code")
    private String productCode;

    @Deprecated
    public ProductAccountKeys() {
    }

    public ProductAccountKeys(Account account, String productCode) {
	super(account);
	this.productCode = productCode;
    }

    public String getProductCode() {
	return productCode;
    }

    public void setProductCode(String productCode) {
	this.productCode = productCode;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = super.hashCode();
	result = prime * result
		+ ((productCode == null) ? 0 : productCode.hashCode());
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
	ProductAccountKeys other = (ProductAccountKeys) obj;
	return new EqualsBuilder().appendSuper(super.equals(other))
		.append(productCode, other.getProductCode()).isEquals();
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }

}
