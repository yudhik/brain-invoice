package com.brainmaster.apps.invoicing.model.ext;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CompanyInformation implements Serializable {

    private static final long serialVersionUID = -1241904448293531135L;

    @Column(name = "store_address1")
    private String address1;

    @Column(name = "store_address2")
    private String address2;

    @Column(name = "store_address3")
    private String address3;

    @Column(name = "store_postal_code")
    private String postalCode;

    @Column(name = "store_city")
    private String city;

    @Column(name = "store_province")
    private String province;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "fax_number")
    private String faxNumber;

    @Column(name = "mobile_number")
    private String mobileNumber;

    public CompanyInformation() {
    }

    public CompanyInformation(String address1, String address2,
	    String address3, String postalCode, String city, String province,
	    String emailAddress, String phoneNumber, String faxNumber,
	    String mobileNumber) {
	this.address1 = address1;
	this.address2 = address2;
	this.address3 = address3;
	this.postalCode = postalCode;
	this.city = city;
	this.province = province;
	this.emailAddress = emailAddress;
	this.phoneNumber = phoneNumber;
	this.faxNumber = faxNumber;
	this.mobileNumber = mobileNumber;
    }

    public String getAddress1() {
	return address1;
    }

    public void setAddress1(String address1) {
	this.address1 = address1;
    }

    public String getAddress2() {
	return address2;
    }

    public void setAddress2(String address2) {
	this.address2 = address2;
    }

    public String getAddress3() {
	return address3;
    }

    public void setAddress3(String address3) {
	this.address3 = address3;
    }

    public String getPostalCode() {
	return postalCode;
    }

    public void setPostalCode(String postalCode) {
	this.postalCode = postalCode;
    }

    public String getCity() {
	return city;
    }

    public void setCity(String city) {
	this.city = city;
    }

    public String getProvince() {
	return province;
    }

    public void setProvince(String province) {
	this.province = province;
    }

    public String getEmailAddress() {
	return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
	this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
	return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
    }

    public String getFaxNumber() {
	return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
	this.faxNumber = faxNumber;
    }

    public String getMobileNumber() {
	return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
	this.mobileNumber = mobileNumber;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((address1 == null) ? 0 : address1.hashCode());
	result = prime * result
		+ ((address2 == null) ? 0 : address2.hashCode());
	result = prime * result
		+ ((address3 == null) ? 0 : address3.hashCode());
	result = prime * result + ((city == null) ? 0 : city.hashCode());
	result = prime * result
		+ ((emailAddress == null) ? 0 : emailAddress.hashCode());
	result = prime * result
		+ ((faxNumber == null) ? 0 : faxNumber.hashCode());
	result = prime * result
		+ ((mobileNumber == null) ? 0 : mobileNumber.hashCode());
	result = prime * result
		+ ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
	result = prime * result
		+ ((postalCode == null) ? 0 : postalCode.hashCode());
	result = prime * result
		+ ((province == null) ? 0 : province.hashCode());
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
	CompanyInformation other = (CompanyInformation) obj;
	if (address1 == null) {
	    if (other.address1 != null)
		return false;
	} else if (!address1.equals(other.address1))
	    return false;
	if (address2 == null) {
	    if (other.address2 != null)
		return false;
	} else if (!address2.equals(other.address2))
	    return false;
	if (address3 == null) {
	    if (other.address3 != null)
		return false;
	} else if (!address3.equals(other.address3))
	    return false;
	if (city == null) {
	    if (other.city != null)
		return false;
	} else if (!city.equals(other.city))
	    return false;
	if (emailAddress == null) {
	    if (other.emailAddress != null)
		return false;
	} else if (!emailAddress.equals(other.emailAddress))
	    return false;
	if (faxNumber == null) {
	    if (other.faxNumber != null)
		return false;
	} else if (!faxNumber.equals(other.faxNumber))
	    return false;
	if (mobileNumber == null) {
	    if (other.mobileNumber != null)
		return false;
	} else if (!mobileNumber.equals(other.mobileNumber))
	    return false;
	if (phoneNumber == null) {
	    if (other.phoneNumber != null)
		return false;
	} else if (!phoneNumber.equals(other.phoneNumber))
	    return false;
	if (postalCode == null) {
	    if (other.postalCode != null)
		return false;
	} else if (!postalCode.equals(other.postalCode))
	    return false;
	if (province == null) {
	    if (other.province != null)
		return false;
	} else if (!province.equals(other.province))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "CompanyInformation [address1=" + address1 + ", address2="
		+ address2 + ", address3=" + address3 + ", postalCode="
		+ postalCode + ", city=" + city + ", province=" + province
		+ ", emailAddress=" + emailAddress + ", phoneNumber="
		+ phoneNumber + ", faxNumber=" + faxNumber + ", mobileNumber="
		+ mobileNumber + "]";
    }

}
