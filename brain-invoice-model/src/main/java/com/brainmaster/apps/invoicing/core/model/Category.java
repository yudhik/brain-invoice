package com.brainmaster.apps.invoicing.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;
import javax.persistence.Table;

import com.brainmaster.apps.invoicing.core.model.credential.Account;
import com.brainmaster.apps.invoicing.core.model.credential.User;
import com.brainmaster.apps.invoicing.core.model.id.CategoryAccountKeys;

@Entity
@Table(name = "category")
@IdClass(CategoryAccountKeys.class)
@NamedQueries({@NamedQuery(name = "categoryFromAccount",
    query = "select a from Category a where a.account = :account")})
public class Category extends AbstractUpdateBy implements Serializable {

  private static final long serialVersionUID = -4114451169235211979L;

  @Id
  @ManyToOne
  @JoinColumn(name = "account_id")
  private Account account;

  @Id
  @Column(name = "category_name")
  private String categoryName;

  @ManyToOne(cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumns({
      @PrimaryKeyJoinColumn(name = "account_id", referencedColumnName = "account_id"),
      @PrimaryKeyJoinColumn(name = "category_name", referencedColumnName = "category_name")})
  private Category parentCategory;

  @OneToMany(mappedBy = "parentCategory", fetch = FetchType.LAZY)
  private List<Category> childCategories = new ArrayList<Category>();

  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Product> products = new ArrayList<Product>();

  @Deprecated
  public Category() {
    super(null, null);
  }

  public Category(Account account, String categoryName, Category parentCategory, User createdBy,
      User updatedBy) {
    super(createdBy, updatedBy);
    this.account = account;
    this.categoryName = categoryName;
    this.parentCategory = parentCategory;
  }

  public Category(Account account, String categoryName, User createdBy, User updatedBy) {
    super(createdBy, updatedBy);
    this.account = account;
    this.categoryName = categoryName;
  }

  public Category(CategoryAccountKeys keys, Category parentCategory, User createdBy, User updatedBy) {
    super(createdBy, updatedBy);
    this.account = keys.getAccount();
    this.categoryName = keys.getCategoryName();
    this.parentCategory = parentCategory;
  }

  public Category(CategoryAccountKeys keys, User createdBy, User updatedBy) {
    super(createdBy, updatedBy);
    this.account = keys.getAccount();
    this.categoryName = keys.getCategoryName();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    Category other = (Category) obj;
    if (account == null) {
      if (other.account != null)
        return false;
    } else if (!account.equals(other.account))
      return false;
    if (categoryName == null) {
      if (other.categoryName != null)
        return false;
    } else if (!categoryName.equals(other.categoryName))
      return false;
    return true;
  }

  public Account getAccount() {
    return account;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public List<Category> getChildCategories() {
    return childCategories;
  }

  public Category getParentCategory() {
    return parentCategory;
  }

  public List<Product> getProducts() {
    return products;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((account == null) ? 0 : account.hashCode());
    result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
    return result;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public void setChildCategories(List<Category> childCategories) {
    this.childCategories = childCategories;
  }

  public void setParentCategory(Category parentCategory) {
    this.parentCategory = parentCategory;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Category [");
    if (account != null) {
      builder.append("account=");
      builder.append(account);
      builder.append(", ");
    }
    if (categoryName != null) {
      builder.append("categoryName=");
      builder.append(categoryName);
      builder.append(", ");
    }
    if (parentCategory != null) {
      builder.append("parentCategory=");
      builder.append(parentCategory);
      builder.append(", ");
    }
    if (childCategories != null) {
      builder.append("childCategories=");
      builder.append(childCategories);
      builder.append(", ");
    }
    if (products != null) {
      builder.append("products=");
      builder.append(products);
    }
    builder.append("]");
    return builder.toString();
  }


}
