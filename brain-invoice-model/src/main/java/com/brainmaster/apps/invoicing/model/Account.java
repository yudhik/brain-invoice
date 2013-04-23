package com.brainmaster.apps.invoicing.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.brainmaster.util.DatabaseColumnConstant;
import com.brainmaster.util.helper.uuid.UUIDHelper;
import com.brainmaster.util.types.UUIDType;

@Entity
@TypeDefs({@TypeDef(name = "uuid", typeClass = UUIDType.class)})
@Table(name = "account")
@NamedQueries({
		@NamedQuery(name = "account-with-email", query = "from Account a where a.emailAddress = :email"),
})
public class Account implements Serializable {

	private static final long serialVersionUID = 5856204102955357752L;
	
	@Id
	@Type(type = "uuid")
	@Column(name = "account_uuid", length = DatabaseColumnConstant.SIZE_UUID)
	private UUID accountUuid;
	
	@NotNull
	@Email
	@Column(name = "email_address", unique = true)
	private String emailAddress;
	
	@NotBlank
	@Column(name = "first_name", length = DatabaseColumnConstant.SIZE_FIRSTNAME)
	private String firstName;
	
	@Column(name = "last_name", length = DatabaseColumnConstant.SIZE_LASTNAME)
	private String lastName;
	
//	@OneToMany(mappedBy = "account_uuid", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<Store> stores = new ArrayList<Store>();
	
	
	public Account() {
	}
	
	public Account(String emailAddress, String firstName, String lastName) {
		this.accountUuid = UUID.randomUUID();
		this.emailAddress = emailAddress;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Transient
	public String getAccountId() {
		return UUIDHelper.uuidToString(accountUuid);
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public UUID getAccountUuid() {
		return accountUuid;
	}

	public void setAccountUuid(UUID accountUuid) {
		this.accountUuid = accountUuid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

//	public List<Store> getStores() {
//		return stores;
//	}
//
//	public void setStores(List<Store> stores) {
//		this.stores = stores;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountUuid == null) ? 0 : accountUuid.hashCode());
		result = prime * result
				+ ((emailAddress == null) ? 0 : emailAddress.hashCode());
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
		Account other = (Account) obj;
		if (accountUuid == null) {
			if (other.accountUuid != null)
				return false;
		} else if (!accountUuid.equals(other.accountUuid))
			return false;
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [emailAddress=" + emailAddress + ", accountId="
				+ getAccountId() + "]";
	}
}
