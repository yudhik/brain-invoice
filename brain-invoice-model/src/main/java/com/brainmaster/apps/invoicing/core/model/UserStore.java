package com.brainmaster.apps.invoicing.core.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;

import com.brainmaster.apps.invoicing.core.model.credential.Account;
import com.brainmaster.util.DatabaseColumnConstant;
import com.brainmaster.util.helper.uuid.UUIDHelper;

@Entity
@Table(name = "user_store", uniqueConstraints = @UniqueConstraint(columnNames = {
	"account_id", "store_id" }))
public class UserStore implements Serializable {

    private static final long serialVersionUID = 8313639751190388533L;

    @Id
    @Type(type = "uuid")
    @Column(name = "user_store_id", length = DatabaseColumnConstant.SIZE_UUID)
    private UUID userStoreId;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(targetEntity = Store.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
    
    public UserStore() {
    }

    public UserStore(Account account, Store store) {
	this.userStoreId = UUID.randomUUID();
	this.account = account;
	this.store = store;
    }

    public UUID getUserStoreId() {
	return userStoreId;
    }

    public void setUserStoreId(UUID userStoreId) {
	this.userStoreId = userStoreId;
    }

    @Transient
    public String getUserStoreIdInString() {
	if (userStoreId != null)
	    return UUIDHelper.uuidToString(userStoreId);
	return null;
    }

    public Account getAccount() {
	return account;
    }

    public void setAccount(Account account) {
	this.account = account;
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
	result = prime * result + ((store == null) ? 0 : store.hashCode());
	result = prime * result
		+ ((userStoreId == null) ? 0 : userStoreId.hashCode());
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
	UserStore other = (UserStore) obj;
	if (account == null) {
	    if (other.account != null)
		return false;
	} else if (!account.equals(other.account))
	    return false;
	if (store == null) {
	    if (other.store != null)
		return false;
	} else if (!store.equals(other.store))
	    return false;
	if (userStoreId == null) {
	    if (other.userStoreId != null)
		return false;
	} else if (!userStoreId.equals(other.userStoreId))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("UserStore [userStoreId=");
	builder.append(getUserStoreIdInString());
	builder.append(", account=");
	builder.append(account);
	builder.append(", store=");
	builder.append(store);
	builder.append("]");
	return builder.toString();
    }

    
}
