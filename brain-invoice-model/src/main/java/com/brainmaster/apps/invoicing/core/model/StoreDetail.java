package com.brainmaster.apps.invoicing.core.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.brainmaster.apps.invoicing.core.model.ext.BankInformation;
import com.brainmaster.apps.invoicing.core.model.ext.CompanyInformation;
import com.brainmaster.util.DatabaseColumnConstant;

@Entity
@Table(name = "store_detail")
public class StoreDetail implements Serializable {

    private static final long serialVersionUID = -1462474216780036654L;

    @Id
    @Type(type = "uuid")
    @Column(name = "store_id", length = DatabaseColumnConstant.SIZE_UUID)
    private UUID storeId;
    
    @OneToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "store_id")
    private Store store;

    @Embedded
    private CompanyInformation companyInformation;

    @Embedded
    private BankInformation bankInformation;

    public StoreDetail() {
    }

    public StoreDetail(Store store, CompanyInformation companyInformation,
	    BankInformation bankInformation) {
	this.storeId = store.getStoreId();
	this.store = store;
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

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((store == null) ? 0 : store.hashCode());
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
	if (store == null) {
	    if (other.store != null)
		return false;
	} else if (!store.equals(other.store))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("StoreDetail [companyInformation=");
	builder.append(companyInformation);
	builder.append(", bankInformation=");
	builder.append(bankInformation);
	builder.append("]");
	return builder.toString();
    }

}
