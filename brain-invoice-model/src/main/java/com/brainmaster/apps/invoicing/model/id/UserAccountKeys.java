package com.brainmaster.apps.invoicing.model.id;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.annotations.Type;

import com.brainmaster.apps.invoicing.model.Account;
import com.brainmaster.util.DatabaseColumnConstant;

@Embeddable
public class UserAccountKeys extends AccountKeys implements Serializable {

	private static final long serialVersionUID = 2200280623134095887L;

	@Type(type = "uuid")
	@Column(name = "user_id", length = DatabaseColumnConstant.SIZE_UUID)
	private UUID userId;

	@Deprecated
	public UserAccountKeys() {
	}
	
	public UserAccountKeys(Account account, UUID userId) {
		super(account);
		this.userId = userId;
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
		UserAccountKeys other = (UserAccountKeys) obj;
		return new EqualsBuilder().appendSuper(super.equals(other))
				.append(userId, other.getUserId()).isEquals();
	}

	@Override
	public String toString() {
		return "UserAccountKeys [account=" + getAccount().getAccountId()
				+ ",userId=" + userId + "]";
	}

}
