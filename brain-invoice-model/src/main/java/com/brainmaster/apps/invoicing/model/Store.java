package com.brainmaster.apps.invoicing.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.brainmaster.apps.invoicing.model.ext.StoreType;
import com.brainmaster.apps.invoicing.model.id.StoreAccountKeys;
import com.brainmaster.util.helper.uuid.UUIDHelper;

@Entity
@Table(name = "store")
public class Store implements Serializable {

	private static final long serialVersionUID = 5947838295063055068L;

	@EmbeddedId
	private StoreAccountKeys keys;

	@NotNull
	@NotBlank
	@Column(name = "store_name")
	private String storeName;

	@NotNull
	@Column(name = "store_type")
	private StoreType storeType;

	@NotNull
	@NotBlank
	@Column(name = "contact_first_name")
	private String contactFirstName;
	
	@Column(name = "contact_last_name")
	private String contactLastName;
	
	@OneToOne(mappedBy = "store", fetch = FetchType.LAZY)
	private StoreDetail storeDetail;

	@ManyToOne(targetEntity = Store.class, optional = true, fetch = FetchType.LAZY)
	private Store parentStore;
	
	@OneToMany(mappedBy = "parentStore", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Store> childStores = new ArrayList<Store>();

	@OneToMany(mappedBy = "userStoreAccountKeys.userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<UserStore> userStoreList = new ArrayList<UserStore>();
//
//	@OneToMany(mappedBy = "id.store", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<ProductStore> products = new ArrayList<ProductStore>();

	public Store() {
	}

	public Store(Account account, UUID uuid, String storeName, StoreType storeType, String contactFirstName, String contactLastName) {
		keys = new StoreAccountKeys(account, uuid);
		this.storeName = storeName;
		this.storeType = storeType;
		this.contactFirstName = contactFirstName;
		this.contactLastName = contactLastName;
	}

	@Transient
	public String getStoreId() {
		if(keys != null)
			return UUIDHelper.uuidToString(keys.getUuid());
		return null;
	}

	public Store getParentStore() {
		return parentStore;
	}

	public StoreAccountKeys getKeys() {
		return keys;
	}

	public void setKeys(StoreAccountKeys keys) {
		this.keys = keys;
	}

	public void setParentStore(Store parentStore) {
		this.parentStore = parentStore;
	}

	public List<Store> getChildStores() {
		return childStores;
	}

	public void setChildStores(List<Store> childStores) {
		this.childStores = childStores;
	}

	public List<UserStore> getUserStoreList() {
		return userStoreList;
	}

	public void setUserStoreList(List<UserStore> userStoreList) {
		this.userStoreList = userStoreList;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreName() {
		return storeName;
	}

//	public void setProducts(List<ProductStore> products) {
//		this.products = products;
//	}
//
//	public List<ProductStore> getProducts() {
//		return products;
//	}
	
	public StoreType getStoreType() {
		return storeType;
	}
	
	public void setStoreType(StoreType storeType) {
		this.storeType = storeType;
	}

	public String getContactFirstName() {
		return contactFirstName;
	}

	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}

	public String getContactLastName() {
		return contactLastName;
	}

	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
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
		Store other = (Store) obj;
		if (keys == null) {
			if (other.keys != null)
				return false;
		} else if (!keys.equals(other.keys))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Store [keys=" + keys + ", storeName=" + storeName
				+ ", storeType=" + storeType + ", contactFirstName="
				+ contactFirstName + ", contactLastName=" + contactLastName
				+ ", storeDetail=" + storeDetail + ", parentStore="
				+ parentStore + ", childStores="+ childStores + "]";
	}
}