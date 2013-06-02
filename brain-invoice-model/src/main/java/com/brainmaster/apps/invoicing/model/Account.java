package com.brainmaster.apps.invoicing.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.brainmaster.util.DatabaseColumnConstant;
import com.brainmaster.util.helper.uuid.UUIDHelper;
import com.brainmaster.util.types.UUIDType;

@Entity
@TypeDefs({ @TypeDef(name = "uuid", typeClass = UUIDType.class) })
@Table(name = "account")
public class Account implements Serializable {

    private static final long serialVersionUID = 5856204102955357752L;

    @Id
    @Type(type = "uuid")
    @Column(name = "account_uuid", length = DatabaseColumnConstant.SIZE_UUID)
    private UUID accountUuid;

    @NotNull
    @Email
    @Column(name = "registration_address", unique = true)
    private String registrationEmailAddress;

    @NotBlank
    @Column(name = "registration_first_name", length = DatabaseColumnConstant.SIZE_FIRSTNAME)
    private String resitrationFirstName;

    @Column(name = "registration_last_name", length = DatabaseColumnConstant.SIZE_LASTNAME)
    private String registrationLastName;

    @OneToMany(mappedBy = "keys.account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Brand> brands = new ArrayList<Brand>();

    @OneToMany(mappedBy = "keys.account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Category> categories = new ArrayList<Category>();

    @OneToMany(mappedBy = "keys.account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PackagingUnit> packagingUnits = new ArrayList<PackagingUnit>();

    @OneToMany(mappedBy = "keys.account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<Product>();

    @OneToMany(mappedBy = "keys.account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Store> stores = new ArrayList<Store>();

//    @OneToMany(mappedBy = "keys.account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<User> users = new ArrayList<User>();
    
    
    public Account() {
    }
    
    public Account(UUID accountUuid, String emailAddress, String firstName, String lastName) {
	this.accountUuid = accountUuid;
	this.registrationEmailAddress = emailAddress;
	this.resitrationFirstName = firstName;
	this.registrationLastName = lastName;
    }

    public Account(String emailAddress, String firstName, String lastName) {
	this.accountUuid = UUID.randomUUID();
	this.registrationEmailAddress = emailAddress;
	this.resitrationFirstName = firstName;
	this.registrationLastName = lastName;
    }

    @Transient
    public String getAccountId() {
	return UUIDHelper.uuidToString(accountUuid);
    }

    public String getEmailAddress() {
	return registrationEmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
	this.registrationEmailAddress = emailAddress;
    }

    public UUID getAccountUuid() {
	return accountUuid;
    }

    public void setAccountUuid(UUID accountUuid) {
	this.accountUuid = accountUuid;
    }

    public String getRegistrationEmailAddress() {
	return registrationEmailAddress;
    }

    public void setRegistrationEmailAddress(String registrationEmailAddress) {
	this.registrationEmailAddress = registrationEmailAddress;
    }

    public String getResitrationFirstName() {
	return resitrationFirstName;
    }

    public void setResitrationFirstName(String resitrationFirstName) {
	this.resitrationFirstName = resitrationFirstName;
    }

    public String getRegistrationLastName() {
	return registrationLastName;
    }

    public void setRegistrationLastName(String registrationLastName) {
	this.registrationLastName = registrationLastName;
    }

    public List<Brand> getBrands() {
	return brands;
    }

    public void setBrands(List<Brand> brands) {
	this.brands = brands;
    }

    public List<Category> getCategories() {
	return categories;
    }

    public void setCategories(List<Category> categories) {
	this.categories = categories;
    }

    public List<PackagingUnit> getPackagingUnits() {
	return packagingUnits;
    }

    public void setPackagingUnits(List<PackagingUnit> packagingUnits) {
	this.packagingUnits = packagingUnits;
    }

    public List<Product> getProducts() {
	return products;
    }

    public void setProducts(List<Product> products) {
	this.products = products;
    }

    public List<Store> getStores() {
	return stores;
    }

    public void setStores(List<Store> stores) {
	this.stores = stores;
    }

//    public List<User> getUsers() {
//	return users;
//    }
//
//    public void setUsers(List<User> users) {
//	this.users = users;
//    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((accountUuid == null) ? 0 : accountUuid.hashCode());
	result = prime
		* result
		+ ((registrationEmailAddress == null) ? 0
			: registrationEmailAddress.hashCode());
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
	Account other = (Account) obj;
	if (accountUuid == null) {
	    if (other.accountUuid != null)
		return false;
	} else if (!accountUuid.equals(other.accountUuid))
	    return false;
	if (registrationEmailAddress == null) {
	    if (other.registrationEmailAddress != null)
		return false;
	} else if (!registrationEmailAddress
		.equals(other.registrationEmailAddress))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Account [emailAddress=" + registrationEmailAddress
		+ ", accountId=" + getAccountId() + "]";
    }
}
