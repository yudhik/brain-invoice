package com.brainmaster.apps.invoicing.model.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.brainmaster.apps.invoicing.model.Account;

@Embeddable
public class BrandAccountKeys extends AccountKeys implements Serializable {

    private static final long serialVersionUID = -5852654524371945799L;

    @Column(name = "brand_name")
    private String brandName;

    @Deprecated
    public BrandAccountKeys() {
    }

    public BrandAccountKeys(Account account, String brandName) {
	super(account);
	this.brandName = brandName;
    }

    public String getBrandName() {
	return brandName;
    }

    public void setBrandName(String brandName) {
	this.brandName = brandName;
    }

    // TODO: implement hashcode, equals and toString into a proper
    // implementation

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = super.hashCode();
	result = prime * result
		+ ((brandName == null) ? 0 : brandName.hashCode());
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
	BrandAccountKeys other = (BrandAccountKeys) obj;
	return new EqualsBuilder().appendSuper(super.equals(other))
		.append(brandName, other.getBrandName()).isEquals();
    }

    @Override
    public String toString() {
	return "BrandAccountKeys [account=" + getAccount().getAccountId()
		+ ", brandName=" + brandName + "]";
    }

}
