package com.brainmaster.apps.invoicing.core.model.credential;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.picketlink.idm.model.AbstractAttributedType;
import org.picketlink.idm.model.Relationship;

import com.brainmaster.apps.invoicing.core.model.id.UserRoleKeys;

@Entity
@AssociationOverrides({
    @AssociationOverride(name = "userRoleId.user", joinColumns = @JoinColumn(name = "user_id")),
    @AssociationOverride(name = "userRoleId.role", joinColumns = @JoinColumn(name = "role_id"))})
@Table(name = "userrole")
public class UserRole extends AbstractAttributedType implements Relationship {

  private static final long serialVersionUID = 959920300849148232L;

  @EmbeddedId
  private UserRoleKeys userRoleId;

  // private IdentityType assignee;
  // private Role role;

  public UserRole() {}

  public UserRole(UserRoleKeys id) {
    this.userRoleId = id;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    UserRole other = (UserRole) obj;
    if (userRoleId == null) {
      if (other.userRoleId != null)
        return false;
    } else if (!userRoleId.equals(other.userRoleId))
      return false;
    return true;
  }

  // public IdentityType getAssignee() {
  // return assignee;
  // }
  //
  // public Role getRole() {
  // return role;
  // }

  public UserRoleKeys getUserRoleId() {
    return userRoleId;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((userRoleId == null) ? 0 : userRoleId.hashCode());
    return result;
  }

  // public void setAssignee(IdentityType assignee) {
  // this.assignee = assignee;
  // }
  //
  // public void setRole(Role role) {
  // this.role = role;
  // }

  public void setUserRoleId(UserRoleKeys userRoleId) {
    this.userRoleId = userRoleId;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("UserRole [id=").append(userRoleId).append("]");
    return builder.toString();
  }

}
