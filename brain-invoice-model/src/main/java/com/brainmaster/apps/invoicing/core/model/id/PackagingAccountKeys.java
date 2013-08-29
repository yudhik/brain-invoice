package com.brainmaster.apps.invoicing.core.model.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.brainmaster.apps.invoicing.core.model.credential.Account;

@Embeddable
public class PackagingAccountKeys extends AccountKeys implements Serializable {

    private static final long serialVersionUID = 5616285605407339616L;

    // @Type(type = "uuid")
    @Column(name = "packaging_id")
    private String packagingId;

    @Deprecated
    public PackagingAccountKeys() {
    }

    public PackagingAccountKeys(Account account, String packagingId) {
	super(account);
	this.packagingId = packagingId;
    }

    public String getPackagingId() {
	return packagingId;
    }

    public void setPackagingId(String packagingId) {
	this.packagingId = packagingId;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = super.hashCode();
	result = prime * result
		+ ((packagingId == null) ? 0 : packagingId.hashCode());
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
	PackagingAccountKeys other = (PackagingAccountKeys) obj;
	return new EqualsBuilder().appendSuper(super.equals(other))
		.append(packagingId, other.getPackagingId()).isEquals();
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }
}
