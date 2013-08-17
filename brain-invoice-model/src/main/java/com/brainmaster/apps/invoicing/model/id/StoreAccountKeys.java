package com.brainmaster.apps.invoicing.model.id;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Type;

import com.brainmaster.apps.invoicing.model.Account;
import com.brainmaster.util.DatabaseColumnConstant;

@Embeddable
public class StoreAccountKeys extends AccountKeys implements Serializable {

    private static final long serialVersionUID = 1388325146281613599L;

    @Type(type = "uuid")
    @Column(name = "store_id", length = DatabaseColumnConstant.SIZE_UUID)
    private UUID storeId;

    @Deprecated
    public StoreAccountKeys() {
    }

    public StoreAccountKeys(Account account, UUID uuid) {
	super(account);
	this.storeId = uuid;
    }

    public UUID getUuid() {
	return storeId;
    }

    public void setUuid(UUID uuid) {
	this.storeId = uuid;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((storeId == null) ? 0 : storeId.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	StoreAccountKeys other = (StoreAccountKeys) obj;
	if (storeId == null) {
	    if (other.storeId != null)
		return false;
	} else if (!storeId.equals(other.storeId))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "StoreAccountKeys [uuid=" + storeId + "]";
    }
}
