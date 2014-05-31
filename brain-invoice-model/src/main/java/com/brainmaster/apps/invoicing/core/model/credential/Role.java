package com.brainmaster.apps.invoicing.core.model.credential;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.picketlink.idm.model.AbstractIdentityType;
import org.picketlink.idm.model.annotation.AttributeProperty;
import org.picketlink.idm.model.annotation.Unique;

@Entity
@Table(name = "role")
public class Role extends AbstractIdentityType {

  private static final long serialVersionUID = 3440554143752137475L;

  @Id
  @Column(name = "role_id", length = 150, nullable = false)
  private String roleId;

  @Column(length = 75, nullable = false)
  private String description;

  @OneToMany(mappedBy = "userRoleId.role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<UserRole> userRoles = new ArrayList<UserRole>();

  public Role() {}

  public Role(String roleId, String description) {
    this.roleId = roleId;
    this.description = description;
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

  public String getDescription() {
    return description;
  }

  @AttributeProperty
  @Unique
  public String getRoleId() {
    return roleId;
  }

  public List<UserRole> getUserRoles() {
    return userRoles;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((description == null) ? 0 : description.hashCode());
    result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
    return result;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }

  public void setUserRoles(List<UserRole> userRoles) {
    this.userRoles = userRoles;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Role [roleId=").append(roleId).append(", description=").append(description)
        .append("]");
    return builder.toString();
  }

}
