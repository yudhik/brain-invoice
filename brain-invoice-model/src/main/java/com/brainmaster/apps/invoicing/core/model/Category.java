package com.brainmaster.apps.invoicing.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.brainmaster.apps.invoicing.core.model.credential.Account;
import com.brainmaster.apps.invoicing.core.model.credential.User;
import com.brainmaster.apps.invoicing.core.model.id.CategoryAccountKeys;

@Entity
@Table(name = "category")
public class Category extends AbstractUpdateBy implements Serializable {

    private static final long serialVersionUID = -4114451169235211979L;

    @EmbeddedId
    private CategoryAccountKeys keys;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({
	    @JoinColumn(name = "parent_category_account_id", referencedColumnName = "account_id"),
	    @JoinColumn(name = "parent_category_name", referencedColumnName = "category_name") })
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.LAZY)
    private List<Category> childCategories = new ArrayList<Category>();

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<Product>();

    @Deprecated
    public Category() {
	super(null, null);
    }
    
    public Category(Account account, String categoryName, User createdBy, User updatedBy) {
	super(createdBy, updatedBy);
	this.keys = new CategoryAccountKeys(account, categoryName);
    }
    
    public Category(CategoryAccountKeys keys, User createdBy, User updatedBy) {
	super(createdBy, updatedBy);
	this.keys = keys;
    }

    public Category(Account account, String categoryName, Category parentCategory,
	    User createdBy, User updatedBy) {
	super(createdBy, updatedBy);
	this.keys = new CategoryAccountKeys(account, categoryName);
	this.parentCategory = parentCategory;
    }
    
    public Category(CategoryAccountKeys keys, Category parentCategory,
	    User createdBy, User updatedBy) {
	super(createdBy, updatedBy);
	this.keys = keys;
	this.parentCategory = parentCategory;
    }

    public CategoryAccountKeys getKeys() {
	return keys;
    }

    public void setKeys(CategoryAccountKeys keys) {
	this.keys = keys;
    }

    public Category getParentCategory() {
	return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
	this.parentCategory = parentCategory;
    }

    public List<Category> getChildCategories() {
	return childCategories;
    }

    public void setChildCategories(List<Category> childCategories) {
	this.childCategories = childCategories;
    }

    public void setProducts(List<Product> products) {
	this.products = products;
    }

    public List<Product> getProducts() {
	return products;
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
	Category other = (Category) obj;
	if (keys == null) {
	    if (other.keys != null)
		return false;
	} else if (!keys.equals(other.keys))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return new ToStringBuilder(this).append(keys).append(parentCategory)
		.toString();
    }
}
