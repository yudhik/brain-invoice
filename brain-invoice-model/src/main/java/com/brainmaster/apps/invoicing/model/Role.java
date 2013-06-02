package com.brainmaster.apps.invoicing.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role implements Serializable {

    private static final long serialVersionUID = 3440554143752137475L;

    @Id
    @Column(name = "role_id", length = 150, nullable = false)
    private String roleId;

    @Column(length = 75, nullable = false)
    private String description;

    public Role() {
    }

    public Role(String roleId, String description) {
	this.roleId = roleId;
	this.description = description;
    }

    public String getRoleId() {
	return roleId;
    }

    public void setRoleId(String roleId) {
	this.roleId = roleId;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((description == null) ? 0 : description.hashCode());
	result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
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
	Role other = (Role) obj;
	if (description == null) {
	    if (other.description != null)
		return false;
	} else if (!description.equals(other.description))
	    return false;
	if (roleId == null) {
	    if (other.roleId != null)
		return false;
	} else if (!roleId.equals(other.roleId))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Role [roleId=").append(roleId).append(", description=")
		.append(description).append("]");
	return builder.toString();
    }

}
