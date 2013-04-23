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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;

import com.brainmaster.apps.invoicing.model.ext.StoreType;
import com.brainmaster.util.DatabaseColumnConstant;
import com.brainmaster.util.helper.uuid.UUIDHelper;

@Entity
@Table(name = "store")
public class Store implements Serializable {

	private static final long serialVersionUID = 5947838295063055068L;

	@Id
	@Type(type = "uuid")
	@Column(length = DatabaseColumnConstant.SIZE_UUID)
	private UUID uuid;

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

	public Store(UUID uuid, String storeName, StoreType storeType) {
		this.uuid = uuid;
		this.storeName = storeName;
		this.storeType = storeType;
	}

	public Store(UUID uuid, String storeName, StoreType storeType, String contactFirstName, String contactLastName) {
		this.uuid = uuid;
		this.storeName = storeName;
		this.storeType = storeType;
		this.contactFirstName = contactFirstName;
		this.contactLastName = contactLastName;
	}

	@Transient
	public String getId() {
		return UUIDHelper.uuidToString(uuid);
	}
	
	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public Store getParentStore() {
		return parentStore;
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
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
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
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	
	
}