package com.brainmaster.apps.invoicing.model.id;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import com.brainmaster.apps.invoicing.model.Account;
import com.brainmaster.apps.invoicing.model.Store;
import com.brainmaster.apps.invoicing.model.User;

@Embeddable
public class UserStoreAccountKeys implements Serializable {

	private static final long serialVersionUID = 5422311344417738754L;

	@ManyToOne(targetEntity = Store.class, cascade = CascadeType.ALL)
//	@JoinColumns({
//		@JoinColumn(name = "uuid"),
//		@JoinColumn(name = "account_id")
//	})
	private Store store;
	
	@ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
//	@JoinColumns({
//		@JoinColumn(name = "user_id"),
//		@JoinColumn(name = "account_id")
//	})
	private User user;
	
//	@SuppressWarnings("deprecation")
	public UserStoreAccountKeys() {
	}

	public UserStoreAccountKeys(Account account, Store store, User user) {
//		super(account);
		this.store = store;
		this.user = user;
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
		int result = super.hashCode();
		result = prime * result + ((store == null) ? 0 : store.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
//		if (!super.equals(obj))
//			return false;
		if (getClass() != obj.getClass())
			return false;
		UserStoreAccountKeys other = (UserStoreAccountKeys) obj;
		if (store == null) {
			if (other.store != null)
				return false;
		} else if (!store.equals(other.store))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserStoreAccountKeys [store=").append(store.getStoreId())
				.append(", user=").append(user.getKeys().getUserId()).append(", getAccount()=")
//				.append(getAccount().getAccountId())
				.append("]");
		return builder.toString();
	}
	
	
	
}
