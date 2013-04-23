package com.brainmaster.apps.invoicing.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.brainmaster.apps.invoicing.model.ext.BankInformation;
import com.brainmaster.apps.invoicing.model.ext.CompanyInformation;
import com.brainmaster.util.DatabaseColumnConstant;
import com.brainmaster.util.helper.uuid.UUIDHelper;

@Entity
@Table(name = "store_detail")
public class StoreDetail implements Serializable {

	private static final long serialVersionUID = -1462474216780036654L;

	@Id
	@Type(type = "uuid")
	@Column(length = DatabaseColumnConstant.SIZE_UUID)
	private UUID uuid;

	@OneToOne(targetEntity = Store.class)
	@JoinColumn(name = "uuid", referencedColumnName = "uuid")
	private Store store;

	@Embedded
	private CompanyInformation companyInformation;

	@Embedded
	private BankInformation bankInformation;

	public StoreDetail() {
	}

	public StoreDetail(UUID uuid, Store store,
			CompanyInformation companyInformation,
			BankInformation bankInformation) {
		this.uuid = uuid;
		this.store = store;
		this.companyInformation = companyInformation;
		this.bankInformation = bankInformation;
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

}
