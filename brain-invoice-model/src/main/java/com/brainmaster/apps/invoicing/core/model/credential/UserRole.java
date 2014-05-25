package com.brainmaster.apps.invoicing.core.model.credential;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.brainmaster.apps.invoicing.core.model.id.UserRoleKeys;

@Entity
@AssociationOverrides({
    @AssociationOverride(name = "id.user", joinColumns = @JoinColumn(name = "user_id")),
    @AssociationOverride(name = "id.role", joinColumns = @JoinColumn(name = "role_id"))})
@Table(name = "userrole")
public class UserRole implements Serializable {

  private static final long serialVersionUID = 959920300849148232L;

  @EmbeddedId
  private UserRoleKeys id;

  public UserRole() {}

  public UserRole(UserRoleKeys id) {
    this.id = id;
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
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  public UserRoleKeys getId() {
    return id;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  public void setId(UserRoleKeys id) {
    this.id = id;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("UserRole [id=").append(id).append("]");
    return builder.toString();
  }

}
