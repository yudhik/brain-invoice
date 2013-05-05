package com.brainmaster.apps.invoicing.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.brainmaster.apps.invoicing.model.id.UserAccountKeys;
import com.brainmaster.util.DatabaseColumnConstant;

@Entity
@Table(name = "user")
@NamedQueries({ @NamedQuery(name = "user-with-email", query = "from User a where a.emailAddress = :email") })
public class User implements Serializable {

	private static final long serialVersionUID = 6862888645550391162L;

	@EmbeddedId
	private UserAccountKeys keys;

	@NotNull
	@Email
	@Column(name = "email_address", unique = true)
	private String emailAddress;

	@NotNull
	@Column(name = "password")
	private Byte[] password;

	@NotBlank
	@Column(name = "first_name", length = DatabaseColumnConstant.SIZE_FIRSTNAME)
	private String firstName;

	@Column(name = "last_name", length = DatabaseColumnConstant.SIZE_LASTNAME)
	private String lastName;

	public User() {
	}

	public User(UserAccountKeys keys, String emailAddress, Byte[] password,
			String firstName, String lastName) {
		this.keys = keys;
		this.emailAddress = emailAddress;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public UserAccountKeys getKeys() {
		return keys;
	}

	public void setKeys(UserAccountKeys keys) {
		this.keys = keys;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Byte[] getPassword() {
		return password;
	}

	public void setPassword(Byte[] password) {
		this.password = password;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((keys == null) ? 0 : keys.hashCode());
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
		User other = (User) obj;
		if (keys == null) {
			if (other.keys != null)
				return false;
		} else if (!keys.equals(other.keys))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [keys=" + keys + ", emailAddress=" + emailAddress
				+ ", password=" + Arrays.toString(password) + ", firstName="
				+ firstName + ", lastName=" + lastName + "]";
	}

}
