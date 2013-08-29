package com.brainmaster.apps.invoicing.core.model.id;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.brainmaster.apps.invoicing.core.model.credential.Role;
import com.brainmaster.apps.invoicing.core.model.credential.User;

@Embeddable
public class UserRoleKeys implements Serializable {

    private static final long serialVersionUID = 5936871522488890719L;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user", columnDefinition = "user_id")
    private User user;

    @ManyToOne(targetEntity = Role.class)
    @JoinColumn(name = "role", columnDefinition = "role_id")
    private Role role;

    public UserRoleKeys() {
    }

    public UserRoleKeys(User user, Role role) {
	this.user = user;
	this.role = role;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public Role getRole() {
	return role;
    }

    public void setRole(Role role) {
	this.role = role;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((role == null) ? 0 : role.hashCode());
	result = prime * result + ((user == null) ? 0 : user.hashCode());
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
	UserRoleKeys other = (UserRoleKeys) obj;
	if (role == null) {
	    if (other.role != null)
		return false;
	} else if (!role.equals(other.role))
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
	builder.append("UserRoleKeys [user=").append(user).append(", role=")
		.append(role).append("]");
	return builder.toString();
    }

}
