package com.brainmaster.apps.invoicing.model;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.brainmaster.apps.invoicing.model.id.UserRoleKeys;

@Entity
@AssociationOverrides({
		@AssociationOverride(name = "id.user", joinColumns = @JoinColumn(name = "user_id")),
		@AssociationOverride(name = "id.role", joinColumns = @JoinColumn(name = "role_id"))})
@Table(name = "userrole")
public class UserRole implements Serializable {

	private static final long serialVersionUID = 959920300849148232L;

	@EmbeddedId
	private UserRoleKeys id;

	public UserRole() {
	}

	public UserRole(UserRoleKeys id) {
		this.id = id;
	}

	public UserRoleKeys getId() {
		return id;
	}

	public void setId(UserRoleKeys id) {
		this.id = id;
	}

	@Transient
	public User getUser() {
		return getId().getUser();
	}

	public void setUser(User user) {
		getId().setUser(user);
	}

	public Role getRole() {
		return getId().getRole();
	}

	public void setRole(Role role) {
		getId().setRole(role);
	}

}
