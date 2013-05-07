package com.brainmaster.apps.invoicing.model;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.brainmaster.apps.invoicing.model.id.UserStoreAccountKeys;

@Entity
@Table(name = "userstore")
//@AssociationOverrides({
//		@AssociationOverride(name = "store.keys", joinColumns = {
//				@JoinColumn(name = "uuid", referencedColumnName = "uuid"),
//				@JoinColumn(name = "account_id", referencedColumnName = "account_id") 
//		}),
//		@AssociationOverride(name = "user.keys", joinColumns = {
//				@JoinColumn(name = "user_id", referencedColumnName = "user_id"),
//				@JoinColumn(name = "account_id", referencedColumnName = "account_id") 
//		})
//
//})
public class UserStore implements Serializable {

	private static final long serialVersionUID = 8313639751190388533L;

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "account_id", column = @Column(name = "account_id")),
			@AttributeOverride(name = "uuid", column = @Column(name = "uuid")),
			@AttributeOverride(name = "user_id", column = @Column(name = "user_id")) })
	private UserStoreAccountKeys userStoreAccountKeys;

	@ManyToOne
	@AssociationOverride(name = "store.keys", joinColumns = {
			@JoinColumn(name = "uuid", referencedColumnName = "uuid"),
			@JoinColumn(name = "account_id", referencedColumnName = "account_id") 
	})
	// @PrimaryKeyJoinColumns({
	// @PrimaryKeyJoinColumn(name = "account_id"),
	// @PrimaryKeyJoinColumn(name = "uuid")
	// })
	private Store store;

	@ManyToOne
	@AssociationOverride(name = "user.keys", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "user_id"),
			@JoinColumn(name = "account_id", referencedColumnName = "account_id") 
	})
	// @PrimaryKeyJoinColumns({
	// @PrimaryKeyJoinColumn(name = "account_id"),
	// @PrimaryKeyJoinColumn(name = "user_id")
	// })
	private User user;

	public UserStore() {
	}

	public UserStore(UserStoreAccountKeys userStoreAccountKeys) {
		this.userStoreAccountKeys = userStoreAccountKeys;
	}

	public UserStoreAccountKeys getUserStoreAccountKeys() {
		return userStoreAccountKeys;
	}

	public void setUserStoreAccountKeys(
			UserStoreAccountKeys userStoreAccountKeys) {
		this.userStoreAccountKeys = userStoreAccountKeys;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((userStoreAccountKeys == null) ? 0 : userStoreAccountKeys
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
		if (userStoreAccountKeys == null) {
			if (other.userStoreAccountKeys != null)
				return false;
		} else if (!userStoreAccountKeys.equals(other.userStoreAccountKeys))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserStore [userStoreAccountKeys=" + userStoreAccountKeys + "]";
	}
}
