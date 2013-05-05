package com.brainmaster.apps.invoicing.model.id;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Type;

import com.brainmaster.apps.invoicing.model.Account;
import com.brainmaster.apps.invoicing.model.Store;
import com.brainmaster.util.DatabaseColumnConstant;

@Embeddable
public class UserStoreAccountKeys extends StoreAccountKeys {

	private static final long serialVersionUID = 5422311344417738754L;

	@Type(type = "uuid")
	@Column(name = "user_id", length = DatabaseColumnConstant.SIZE_UUID)
	private UUID userId;
	
	@SuppressWarnings("deprecation")
	public UserStoreAccountKeys() {
	}
	
	public UserStoreAccountKeys(Account account, Store store) {
		super(account, store.getKeys().getUuid());
		this.userId = UUID.randomUUID();
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		UserStoreAccountKeys other = (UserStoreAccountKeys) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserStoreAccountKeys [userId=" + userId + ", StoreAccountKeys = "+ super.toString() + "]";
	}
	
	
}
