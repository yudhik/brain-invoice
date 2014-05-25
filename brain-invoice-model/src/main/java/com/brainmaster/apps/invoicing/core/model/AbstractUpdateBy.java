package com.brainmaster.apps.invoicing.core.model;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.brainmaster.apps.invoicing.core.model.credential.User;

public abstract class AbstractUpdateBy extends AbstractCreateByEntity {

  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "updated_by", nullable = true)
  private final User updatedBy;

  public AbstractUpdateBy(User createdBy, User updatedBy) {
    super(createdBy);
    this.updatedBy = updatedBy;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    AbstractUpdateBy other = (AbstractUpdateBy) obj;
    if (updatedBy == null) {
      if (other.updatedBy != null)
        return false;
    } else if (!updatedBy.equals(other.updatedBy))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((updatedBy == null) ? 0 : updatedBy.hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("AbstractUpdateBy [updatedBy=");
    builder.append(updatedBy);
    builder.append(", createdBy =");
    builder.append(super.toString());
    builder.append("]");
    return builder.toString();
  }

}
