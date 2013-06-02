package com.brainmaster.apps.invoicing.model.id;

import java.io.Serializable;

import javax.persistence.Column;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.brainmaster.apps.invoicing.model.Account;

public class CategoryAccountKeys extends AccountKeys implements Serializable {

    private static final long serialVersionUID = 6422484821577173408L;

    @Column(name = "category_name")
    private String categoryName;

    @Deprecated
    public CategoryAccountKeys() {
    }

    public CategoryAccountKeys(Account account, String categoryName) {
	super(account);
	this.categoryName = categoryName;
    }

    public String getCategoryName() {
	return categoryName;
    }

    public void setCategoryName(String categoryName) {
	this.categoryName = categoryName;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = super.hashCode();
	result = prime * result
		+ ((categoryName == null) ? 0 : categoryName.hashCode());
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
	CategoryAccountKeys other = (CategoryAccountKeys) obj;
	return new EqualsBuilder().appendSuper(super.equals(other))
		.append(categoryName, other.getCategoryName()).isEquals();
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }
}
