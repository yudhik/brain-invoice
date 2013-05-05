package com.brainmaster.apps.invoicing.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;
import javax.persistence.Table;

import com.brainmaster.apps.invoicing.model.ext.BankInformation;
import com.brainmaster.apps.invoicing.model.ext.CompanyInformation;
import com.brainmaster.apps.invoicing.model.id.StoreAccountKeys;

@Entity
@Table(name = "store_detail")
public class StoreDetail implements Serializable {

	private static final long serialVersionUID = -1462474216780036654L;
	
	@EmbeddedId
	@AttributeOverrides({
        @AttributeOverride(name="account_id", column=@Column(name="account_id")),
        @AttributeOverride(name="uuid", column=@Column(name="uuid"))
    })
	private StoreAccountKeys storeAccountKeys;

	@OneToOne(optional=true, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumns({
		@PrimaryKeyJoinColumn(name = "account_id"),
		@PrimaryKeyJoinColumn(name = "uuid")
	})
	private Store store;

	@Embedded
	private CompanyInformation companyInformation;

	@Embedded
	private BankInformation bankInformation;

	public StoreDetail() {
	}

	public StoreDetail(Store store,
			CompanyInformation companyInformation,
			BankInformation bankInformation) {
		this.store = store;
		this.storeAccountKeys = store.getKeys();
		this.companyInformation = companyInformation;
		this.bankInformation = bankInformation;
	}
	
	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public CompanyInformation getCompanyInformation() {
		return companyInformation;
	}

	public void setCompanyInformation(CompanyInformation companyInformation) {
		this.companyInformation = companyInformation;
	}

	public BankInformation getBankInformation() {
		return bankInformation;
	}

	public void setBankInformation(BankInformation bankInformation) {
		this.bankInformation = bankInformation;
	}

	public StoreAccountKeys getStoreAccountKeys() {
		return storeAccountKeys;
	}

	public void setStoreAccountKeys(StoreAccountKeys storeAccountKeys) {
		this.storeAccountKeys = storeAccountKeys;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((storeAccountKeys == null) ? 0 : storeAccountKeys.hashCode());
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
		StoreDetail other = (StoreDetail) obj;
		if (storeAccountKeys == null) {
			if (other.storeAccountKeys != null)
				return false;
		} else if (!storeAccountKeys.equals(other.storeAccountKeys))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StoreDetail [storeAccountKeys=" + storeAccountKeys + ", store="
				+ store + ", companyInformation=" + companyInformation
				+ ", bankInformation=" + bankInformation + "]";
	}

	
}
