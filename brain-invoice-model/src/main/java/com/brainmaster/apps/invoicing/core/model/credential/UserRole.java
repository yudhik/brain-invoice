package com.brainmaster.apps.invoicing.core.model.credential;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.picketlink.idm.model.AbstractAttributedType;
import org.picketlink.idm.model.Relationship;

import com.brainmaster.apps.invoicing.core.model.id.UserRoleKeys;

@Entity
@IdClass(UserRoleKeys.class)
@Table(name = "user_role")
public class UserRole extends AbstractAttributedType implements Relationship {

  private static final long serialVersionUID = 959920300849148232L;

  @Id
  @ManyToOne(targetEntity = User.class)
  @JoinColumn(name = "user_id")
  private User user;

  @Id
  @ManyToOne(targetEntity = Role.class)
  @JoinColumn(name = "role_id")
  private Role role;

  public UserRole() {}

  public UserRole(User user, Role role) {
    this.user = user;
    this.role = role;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    UserRole other = (UserRole) obj;
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

  public Role getRole() {
    return role;
  }

  public User getUser() {
    return user;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((role == null) ? 0 : role.hashCode());
    result = prime * result + ((user == null) ? 0 : user.hashCode());
    return result;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("UserRole [");
    if (user != null) {
      builder.append("user=");
      builder.append(user);
      builder.append(", ");
    }
    if (role != null) {
      builder.append("role=");
      builder.append(role);
    }
    builder.append("]");
    return builder.toString();
  }



}
