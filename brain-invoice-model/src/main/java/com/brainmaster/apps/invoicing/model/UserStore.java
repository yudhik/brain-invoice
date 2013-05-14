package com.brainmaster.apps.invoicing.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;
import javax.persistence.Table;

import com.brainmaster.apps.invoicing.model.id.UserStoreAccountKeys;

@Entity
@Table(name = "userstore")
//@AssociationOverrides({
//		@AssociationOverride(name = "store.keys", joinColumns = {
//				@JoinColumn(name = "uuid", referencedColumnName = "uuid"),
//				@JoinColumn(name = "account_id", referencedColumnName = "account_id") }),
//		@AssociationOverride(name = "user.keys", joinColumns = {
//				@JoinColumn(name = "user_id", referencedColumnName = "user_id"),
//				@JoinColumn(name = "account_id", referencedColumnName = "account_id") }),
//		@AssociationOverride(name = "account.keys", joinColumns = { @JoinColumn(name = "account_id", referencedColumnName = "account_id") }),

//})
@PrimaryKeyJoinColumns({
		@PrimaryKeyJoinColumn(name = "account_id", referencedColumnName = "account_id"),
		@PrimaryKeyJoinColumn(name = "uuid", referencedColumnName = "uuid"),
		@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "user_id"),
})
public class UserStore implements Serializable {

	private static final long serialVersionUID = 8313639751190388533L;

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "account_id", column = @Column(name = "account_id")),
			@AttributeOverride(name = "uuid", column = @Column(name = "uuid")),
			@AttributeOverride(name = "user_id", column = @Column(name = "user_id")) })
	private UserStoreAccountKeys keys;

	public UserStore() {
	}

	public UserStore(UserStoreAccountKeys keys) {
		this.keys = keys;
	}

	public UserStoreAccountKeys getKeys() {
		return keys;
	}

	public void setKeys(
			UserStoreAccountKeys keys) {
		this.keys = keys;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((keys == null) ? 0 : keys
						.hashCode());
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
		if (keys == null) {
			if (other.keys != null)
				return false;
		} else if (!keys.equals(other.keys))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserStore [userStoreAccountKeys=" + keys + "]";
	}
}
