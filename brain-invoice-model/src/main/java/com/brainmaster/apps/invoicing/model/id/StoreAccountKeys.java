package com.brainmaster.apps.invoicing.model.id;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

import com.brainmaster.apps.invoicing.model.Account;
import com.brainmaster.util.DatabaseColumnConstant;

@Embeddable
@MappedSuperclass
public class StoreAccountKeys extends AccountKeys implements Serializable {

	private static final long serialVersionUID = 1388325146281613599L;
	
	@Column(length = DatabaseColumnConstant.SIZE_UUID)
	private UUID uuid;

	@Deprecated
	public StoreAccountKeys() {
	}

	public StoreAccountKeys(Account account, UUID uuid) {
		super(account);
		this.uuid = uuid;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
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
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StoreAccountKeys [uuid=" + uuid + "]";
	}
}
