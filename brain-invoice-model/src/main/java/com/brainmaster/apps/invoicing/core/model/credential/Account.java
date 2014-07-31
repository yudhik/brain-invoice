package com.brainmaster.apps.invoicing.core.model.credential;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.picketlink.idm.model.AbstractIdentityType;
import org.picketlink.idm.model.annotation.AttributeProperty;
import org.picketlink.idm.model.annotation.Unique;

import com.brainmaster.apps.invoicing.core.model.Brand;
import com.brainmaster.apps.invoicing.core.model.Category;
import com.brainmaster.apps.invoicing.core.model.PackagingUnit;
import com.brainmaster.apps.invoicing.core.model.Product;
import com.brainmaster.apps.invoicing.core.model.Store;
import com.brainmaster.util.DatabaseColumnConstant;
import com.brainmaster.util.helper.uuid.UUIDHelper;
import com.brainmaster.util.types.UUIDType;

@Entity
@TypeDefs({@TypeDef(name = "uuid", typeClass = UUIDType.class)})
@Table(name = "account")
@NamedQuery(name = "all-account", query = "select a from Account a")
public class Account extends AbstractIdentityType {

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
  @AttributeProperty
  private String resitrationFirstName;

  @Column(name = "registration_last_name", length = DatabaseColumnConstant.SIZE_LASTNAME)
  @AttributeProperty
  private String registrationLastName;

  @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Brand> brands = new ArrayList<Brand>();

  @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Category> categories = new ArrayList<Category>();

  @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<PackagingUnit> packagingUnits = new ArrayList<PackagingUnit>();

  @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Product> products = new ArrayList<Product>();

  @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Store> stores = new ArrayList<Store>();

  @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<User> users = new ArrayList<User>();

  public Account() {}

  public Account(String emailAddress, String firstName, String lastName) {
    this.accountUuid = UUID.randomUUID();
    this.registrationEmailAddress = emailAddress;
    this.resitrationFirstName = firstName;
    this.registrationLastName = lastName;
  }

  public Account(UUID accountUuid, String emailAddress, String firstName, String lastName) {
    this.accountUuid = accountUuid;
    this.registrationEmailAddress = emailAddress;
    this.resitrationFirstName = firstName;
    this.registrationLastName = lastName;
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
    } else if (!registrationEmailAddress.equals(other.registrationEmailAddress))
      return false;
    return true;
  }

  @Transient
  public String getAccountId() {
    return UUIDHelper.uuidToString(accountUuid);
  }

  @AttributeProperty
  @Unique
  public String getAccountName() {
    return getResitrationFirstName() + " " + getRegistrationLastName();
  }

  public UUID getAccountUuid() {
    return accountUuid;
  }

  public List<Brand> getBrands() {
    return brands;
  }

  public List<Category> getCategories() {
    return categories;
  }

  public String getEmailAddress() {
    return registrationEmailAddress;
  }

  public List<PackagingUnit> getPackagingUnits() {
    return packagingUnits;
  }

  public List<Product> getProducts() {
    return products;
  }

  public String getRegistrationEmailAddress() {
    return registrationEmailAddress;
  }

  public String getRegistrationLastName() {
    return registrationLastName;
  }

  public String getResitrationFirstName() {
    return resitrationFirstName;
  }

  public List<Store> getStores() {
    return stores;
  }

  public List<User> getUsers() {
    return users;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((accountUuid == null) ? 0 : accountUuid.hashCode());
    result =
        prime * result
            + ((registrationEmailAddress == null) ? 0 : registrationEmailAddress.hashCode());
    return result;
  }

  public void setAccountUuid(UUID accountUuid) {
    this.accountUuid = accountUuid;
  }

  public void setBrands(List<Brand> brands) {
    this.brands = brands;
  }

  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }

  public void setEmailAddress(String emailAddress) {
    this.registrationEmailAddress = emailAddress;
  }

  public void setPackagingUnits(List<PackagingUnit> packagingUnits) {
    this.packagingUnits = packagingUnits;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  // public List<User> getUsers() {
  // return users;
  // }
  //
  // public void setUsers(List<User> users) {
  // this.users = users;
  // }

  public void setRegistrationEmailAddress(String registrationEmailAddress) {
    this.registrationEmailAddress = registrationEmailAddress;
  }

  public void setRegistrationLastName(String registrationLastName) {
    this.registrationLastName = registrationLastName;
  }

  public void setResitrationFirstName(String resitrationFirstName) {
    this.resitrationFirstName = resitrationFirstName;
  }

  public void setStores(List<Store> stores) {
    this.stores = stores;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  @Override
  public String toString() {
    return "Account [emailAddress=" + registrationEmailAddress + ", accountId=" + getAccountId()
        + "]";
  }
}
