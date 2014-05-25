package com.brainmaster.apps.invoicing.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;

import com.brainmaster.apps.invoicing.core.model.credential.Account;
import com.brainmaster.apps.invoicing.core.model.credential.User;
import com.brainmaster.apps.invoicing.core.model.ext.StoreType;
import com.brainmaster.util.DatabaseColumnConstant;
import com.brainmaster.util.helper.uuid.UUIDHelper;

@Entity
@Table(name = "store", uniqueConstraints = @UniqueConstraint(columnNames = {"store_id",
    "account_id"}))
public class Store extends AbstractUpdateBy implements Serializable {

  private static final long serialVersionUID = 5947838295063055068L;

  @Id
  @Type(type = "uuid")
  @Column(name = "store_id", length = DatabaseColumnConstant.SIZE_UUID)
  private UUID storeId;

  @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id")
  private Account account;

  @NotNull
  @NotBlank
  @Column(name = "store_name")
  private String storeName;

  @NotNull
  @Column(name = "store_type", nullable = false)
  private StoreType storeType;

  @NotNull
  @NotBlank
  @Column(name = "contact_first_name")
  private String contactFirstName;

  @Column(name = "contact_last_name")
  private String contactLastName;

  @OneToOne(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private StoreDetail storeDetail;

  @ManyToOne(targetEntity = Store.class, optional = true, fetch = FetchType.LAZY)
  private Store parentStore;

  @OneToMany(mappedBy = "parentStore", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Store> childStores = new ArrayList<Store>();

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<UserStore> userStores = new ArrayList<UserStore>();

  @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<ProductStore> products = new ArrayList<ProductStore>();

  @Deprecated
  public Store() {
    super(null, null);
  }

  public Store(Account account, UUID storeId, String storeName, StoreType storeType,
      String contactFirstName, String contactLastName, User createdBy, User updatedBy) {
    super(createdBy, updatedBy);
    this.account = account;
    this.storeId = storeId;
    this.storeName = storeName;
    this.storeType = storeType;
    this.contactFirstName = contactFirstName;
    this.contactLastName = contactLastName;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Store other = (Store) obj;
    if (storeId == null) {
      if (other.storeId != null)
        return false;
    } else if (!storeId.equals(other.storeId))
      return false;
    return true;
  }

  public Account getAccount() {
    return account;
  }

  public List<Store> getChildStores() {
    return childStores;
  }

  public String getContactFirstName() {
    return contactFirstName;
  }

  public String getContactLastName() {
    return contactLastName;
  }

  public Store getParentStore() {
    return parentStore;
  }

  public List<ProductStore> getProducts() {
    return products;
  }

  public StoreDetail getStoreDetail() {
    return storeDetail;
  }

  public UUID getStoreId() {
    return storeId;
  }

  @Transient
  public String getStoreIdInString() {
    if (storeId != null) {
      return UUIDHelper.uuidToString(storeId);
    }
    return null;
  }

  public String getStoreName() {
    return storeName;
  }

  public StoreType getStoreType() {
    return storeType;
  }

  public List<UserStore> getUserStores() {
    return userStores;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((storeId == null) ? 0 : storeId.hashCode());
    return result;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public void setChildStores(List<Store> childStores) {
    this.childStores = childStores;
  }

  public void setContactFirstName(String contactFirstName) {
    this.contactFirstName = contactFirstName;
  }

  public void setContactLastName(String contactLastName) {
    this.contactLastName = contactLastName;
  }

  public void setParentStore(Store parentStore) {
    this.parentStore = parentStore;
  }

  public void setProducts(List<ProductStore> products) {
    this.products = products;
  }

  public void setStoreDetail(StoreDetail storeDetail) {
    this.storeDetail = storeDetail;
  }

  public void setStoreId(UUID storeId) {
    this.storeId = storeId;
  }

  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }

  public void setStoreType(StoreType storeType) {
    this.storeType = storeType;
  }

  public void setUsers(List<UserStore> userStores) {
    this.userStores = userStores;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Store [storeId=");
    builder.append(getStoreIdInString());
    builder.append(", account=");
    builder.append(account);
    builder.append(", storeName=");
    builder.append(storeName);
    builder.append(", storeType=");
    builder.append(storeType);
    builder.append(", contactFirstName=");
    builder.append(contactFirstName);
    builder.append(", contactLastName=");
    builder.append(contactLastName);
    builder.append(", storeDetail=");
    builder.append(storeDetail);
    builder.append(", parentStore=");
    builder.append(parentStore);
    builder.append(", users=");
    builder.append(userStores);
    builder.append("]");
    return builder.toString();
  }


}
