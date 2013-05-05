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
import javax.persistence.JoinColumn;
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

	@ManyToOne(targetEntity = Store.class, optional = true)
	private Store parentStore;
	
	@ManyToOne(targetEntity = Account.class)
	@JoinColumn(name = "emailAddress")
	private Account account;

	@OneToMany(mappedBy = "parentStore", cascade = CascadeType.ALL)
	private List<Store> childStores = new ArrayList<Store>();

//	@OneToMany(mappedBy = "id.store", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<UserStore> users = new ArrayList<UserStore>();
//
//	@OneToMany(mappedBy = "id.store", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<ProductStore> products = new ArrayList<ProductStore>();

	public Store() {
	}

	public Store(Account account, UUID uuid, String storeName, StoreType storeType) {
		keys = new StoreAccountKeys(account, uuid);
		this.storeName = storeName;
		this.storeType = storeType;
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

//	public List<UserStore> getUsers() {
//		return users;
//	}
//
//	public void setUsers(List<UserStore> users) {
//		this.users = users;
//	}

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
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result
				+ ((childStores == null) ? 0 : childStores.hashCode());
		result = prime
				* result
				+ ((contactFirstName == null) ? 0 : contactFirstName.hashCode());
		result = prime * result
				+ ((contactLastName == null) ? 0 : contactLastName.hashCode());
		result = prime * result + ((keys == null) ? 0 : keys.hashCode());
		result = prime * result
				+ ((parentStore == null) ? 0 : parentStore.hashCode());
		result = prime * result
				+ ((storeDetail == null) ? 0 : storeDetail.hashCode());
		result = prime * result
				+ ((storeName == null) ? 0 : storeName.hashCode());
		result = prime * result
				+ ((storeType == null) ? 0 : storeType.hashCode());
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
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (childStores == null) {
			if (other.childStores != null)
				return false;
		} else if (!childStores.equals(other.childStores))
			return false;
		if (contactFirstName == null) {
			if (other.contactFirstName != null)
				return false;
		} else if (!contactFirstName.equals(other.contactFirstName))
			return false;
		if (contactLastName == null) {
			if (other.contactLastName != null)
				return false;
		} else if (!contactLastName.equals(other.contactLastName))
			return false;
		if (keys == null) {
			if (other.keys != null)
				return false;
		} else if (!keys.equals(other.keys))
			return false;
		if (parentStore == null) {
			if (other.parentStore != null)
				return false;
		} else if (!parentStore.equals(other.parentStore))
			return false;
		if (storeDetail == null) {
			if (other.storeDetail != null)
				return false;
		} else if (!storeDetail.equals(other.storeDetail))
			return false;
		if (storeName == null) {
			if (other.storeName != null)
				return false;
		} else if (!storeName.equals(other.storeName))
			return false;
		if (storeType != other.storeType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Store [keys=" + keys + ", storeName=" + storeName
				+ ", storeType=" + storeType + ", contactFirstName="
				+ contactFirstName + ", contactLastName=" + contactLastName
				+ ", storeDetail=" + storeDetail + ", parentStore="
				+ parentStore + ", account=" + account + ", childStores="
				+ childStores + "]";
	}
}